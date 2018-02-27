package co.com.bancopopular.cuentas.servicios.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Service;

import co.com.bancopopular.cuentas.dominio.Cuenta;
import co.com.bancopopular.cuentas.servicios.ServicioCuentas;

@Service
public class ServicioCuentasJpa implements ServicioCuentas {

	private EntityManagerFactory emf;
	
	@PersistenceUnit
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public List<Cuenta> listar() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Cuenta", Cuenta.class).getResultList();
	}

	@Override
	public Cuenta obtenerPorId(Integer id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Cuenta.class, id);
	}

	@Override
	public Cuenta guardarOActualizar(Cuenta cuenta) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Cuenta cuentaGuardada = em.merge(cuenta);
		em.getTransaction().commit();
		return cuentaGuardada;
	}

	@Override
	public void borrar(Integer id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(Cuenta.class, id));
		em.getTransaction().commit();
	}
}
