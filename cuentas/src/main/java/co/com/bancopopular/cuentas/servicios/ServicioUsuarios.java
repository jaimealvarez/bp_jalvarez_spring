package co.com.bancopopular.cuentas.servicios;

import java.util.List;

import co.com.bancopopular.cuentas.dominio.Usuario;

public interface ServicioUsuarios {

	List<Usuario> listar();

    Usuario obtenerPorId(Integer id);

    Usuario guardarOActualizar(Usuario usuario);

    void borrar(Integer id);
}
