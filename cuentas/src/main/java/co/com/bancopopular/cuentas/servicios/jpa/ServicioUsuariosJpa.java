package co.com.bancopopular.cuentas.servicios.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Service;

import co.com.bancopopular.cuentas.dominio.Usuario;
import co.com.bancopopular.cuentas.servicios.ServicioUsuarios;

@Service
public class ServicioUsuariosJpa implements ServicioUsuarios {

    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
	@Override
	public List<Usuario> listar() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Usuario", Usuario.class).getResultList();
	}

	@Override
	public Usuario obtenerPorId(Integer id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Usuario.class, id);
	}

	@Override
	public Usuario guardarOActualizar(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Usuario usuarioGuardado = em.merge(usuario);
		em.getTransaction().commit();
		return usuarioGuardado;
	}

	@Override
	public void borrar(Integer id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(Usuario.class, id));
		em.getTransaction().commit();
	}
}
