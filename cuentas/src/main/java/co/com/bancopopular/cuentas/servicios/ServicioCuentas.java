package co.com.bancopopular.cuentas.servicios;

import java.util.List;

import co.com.bancopopular.cuentas.dominio.Cuenta;

public interface ServicioCuentas {

	List<Cuenta> listar();
	
	Cuenta obtenerPorId(Integer id);
	
	Cuenta guardarOActualizar(Cuenta cuenta);
	
	void borrar(Integer id);
}
