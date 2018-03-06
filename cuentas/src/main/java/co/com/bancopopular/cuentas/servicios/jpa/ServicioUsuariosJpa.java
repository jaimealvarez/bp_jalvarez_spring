package co.com.bancopopular.cuentas.servicios.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
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
		if (usuario.getClave() != null) {
			usuario.setClave(passwordEncoder.encode(usuario.getClave()));
		}
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

	@Override
	public Usuario obtenerPorUsuario(String user) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("from Usuario where usuario = :user", Usuario.class).setParameter("user", user).getSingleResult();
		} catch (NoResultException nre) {
			System.out.println("No results");
			throw nre;
		}
	}
}
