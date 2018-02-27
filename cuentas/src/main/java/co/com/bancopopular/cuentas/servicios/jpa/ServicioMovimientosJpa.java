package co.com.bancopopular.cuentas.servicios.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.bancopopular.cuentas.dominio.Cuenta;
import co.com.bancopopular.cuentas.dominio.Movimiento;
import co.com.bancopopular.cuentas.servicios.ServicioMovimientos;

@Service
public class ServicioMovimientosJpa implements ServicioMovimientos {

	private EntityManagerFactory emf;
	
	@Autowired
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public List<Movimiento> listar() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Movimiento", Movimiento.class).getResultList();
	}

	@Override
	public Movimiento obtenerPorId(Integer id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Movimiento.class, id);
	}

	@Override
	public Movimiento guardarOActualizar(Movimiento movimiento) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Integer id = movimiento.getCuentaDebito().getId();
		Cuenta cuentaDebito = em.find(Cuenta.class, id);
		id = movimiento.getCuentaCredito().getId();
		Cuenta cuentaCredito = em.find(Cuenta.class, movimiento.getCuentaCredito().getId());
		cuentaDebito.setSaldo(cuentaDebito.getSaldo() - movimiento.getValor());
		em.merge(cuentaDebito);
		cuentaCredito.setSaldo(cuentaCredito.getSaldo() + movimiento.getValor());
		em.merge(cuentaCredito);
		Movimiento movimientoGuardado = em.merge(movimiento);
		em.getTransaction().commit();
		return movimientoGuardado;
	}

	@Override
	public void borrar(Integer id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(Movimiento.class, id));
		em.getTransaction().commit();
	}

}
