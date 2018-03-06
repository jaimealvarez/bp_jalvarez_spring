package co.com.bancopopular.cuentas.controladores.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.bancopopular.cuentas.dominio.Cuenta;
import co.com.bancopopular.cuentas.servicios.ServicioCuentas;

@RestController
@RequestMapping(value="/cuentas")
public class ControladorCuentas {

	private ServicioCuentas servicioCuentas;
	
	@Autowired
	public void setServicioCuentas(ServicioCuentas servicioCuentas) {
		this.servicioCuentas = servicioCuentas;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Cuenta> getCuentas() {
		return servicioCuentas.listar();
	}
	
	@RequestMapping(value="/id", method = RequestMethod.GET)
	public Cuenta getCuenta(@PathVariable Integer id) {
		return servicioCuentas.obtenerPorId(id);
	}

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Cuenta> guardarOActualizarCuenta(@RequestBody Cuenta cuenta) {
        Cuenta _cuenta = null;
        if(cuenta .getId() != null) {
            _cuenta = servicioCuentas.obtenerPorId(cuenta.getId());
            if(_cuenta != null)
            {
                if (cuenta.getNúmero() != null) {
                    _cuenta.setNúmero(cuenta.getNúmero());
                }
                if (cuenta.getSaldo() != null) {
                    _cuenta.setSaldo(cuenta.getSaldo());
                }
                if (cuenta.getUsuario() != null) {
                	_cuenta.setUsuario(cuenta.getUsuario());
                }
            }else{
            	_cuenta = cuenta; 
            }
        } else {
            _cuenta = cuenta;
        }
        return ResponseEntity.ok(servicioCuentas.guardarOActualizar(_cuenta));
    }
}
