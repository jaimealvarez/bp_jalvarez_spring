package co.com.bancopopular.cuentas.servicios;

import java.util.List;

import co.com.bancopopular.cuentas.dominio.Movimiento;

public interface ServicioMovimientos {

	List<Movimiento> listar();

    Movimiento obtenerPorId(Integer id);

    Movimiento guardarOActualizar(Movimiento movimiento);

    void borrar(Integer id);
}
