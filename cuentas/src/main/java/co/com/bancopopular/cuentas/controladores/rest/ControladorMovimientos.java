package co.com.bancopopular.cuentas.controladores.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.bancopopular.cuentas.dominio.Auditoría;
import co.com.bancopopular.cuentas.dominio.Movimiento;
import co.com.bancopopular.cuentas.repositorios.RepositorioAuditoría;
import co.com.bancopopular.cuentas.servicios.ServicioMovimientos;

@RestController
@RequestMapping(value = "/movimientos")
public class ControladorMovimientos {

	private ServicioMovimientos servicioMovimientos;
	
	private RepositorioAuditoría repositorioAuditoría;
	
	@Autowired
	public void setServicioMovimientos(ServicioMovimientos servicioMovimientos) {
		this.servicioMovimientos = servicioMovimientos;
	}
	
	@Autowired
	public void setRepositorioAuditoría(RepositorioAuditoría repositorioAuditoría) {
		this.repositorioAuditoría = repositorioAuditoría;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Movimiento> getMovimientos()  {
		return servicioMovimientos.listar();
	}
	
	@RequestMapping(value = "/id", method = RequestMethod.GET)
	public Movimiento getMovimiento(@PathVariable Integer id) {
		return servicioMovimientos.obtenerPorId(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Movimiento> crearOActualizar(@RequestBody Movimiento movimiento) {
		Movimiento _movimiento = null;
        if(movimiento.getId() != null) {
            _movimiento = servicioMovimientos.obtenerPorId(movimiento.getId());
            if(_movimiento != null)
            {
            	//Datos que se pueden modificar de un usuario existente
                if (movimiento.getValor() != null) {
                    _movimiento.setValor(movimiento.getValor());
                }
                if (movimiento.getCuentaDebito() != null) {
                    _movimiento.setCuentaDebito(movimiento.getCuentaDebito());
                }
                if (movimiento.getCuentaCredito() != null) {
                	_movimiento.setCuentaCredito(movimiento.getCuentaCredito());
                }
                if (movimiento.getFechaHora() != null) {
                	_movimiento.setFechaHora(movimiento.getFechaHora());
                }
            }else{
            	_movimiento = movimiento; 
            }
        } else {
            _movimiento = movimiento;
        }
        
        Auditoría auditoría = new Auditoría();
        auditoría.setAcción("Movimiento.guardarOActualizar");
        auditoría.setUsuario("jalvarez");
        auditoría.setFechaHora(new Date());
        try {
			auditoría.setParámetros(new ObjectMapper().writeValueAsString(_movimiento));
			repositorioAuditoría.save(auditoría);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return ResponseEntity.ok(servicioMovimientos.guardarOActualizar(_movimiento));
	}
}
