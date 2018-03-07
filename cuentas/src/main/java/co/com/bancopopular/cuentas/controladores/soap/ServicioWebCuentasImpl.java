package co.com.bancopopular.cuentas.controladores.soap;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import co.com.bancopopular.cuentas.dominio.Cuenta;
import co.com.bancopopular.cuentas.servicios.ServicioCuentas;
import co.com.bancopopular.cuentas.util.BeanUtil;

@WebService(endpointInterface = "co.com.bancopopular.cuentas.controladores.soap.ServicioWebCuentas")
public class ServicioWebCuentasImpl implements ServicioWebCuentas {

    private ServicioCuentas servicioCuentas;

    public ServicioCuentas getServicioCuentas() {
    	if (servicioCuentas == null) {
    		this.servicioCuentas = BeanUtil.getBean(ServicioCuentas.class);
    	}
    	return this.servicioCuentas;
    }

	@Override
	public Cuenta[] getCuentas() {
    	List<Cuenta> cuentas = getServicioCuentas().listar();
    	if (cuentas != null) {
    		return (Cuenta[])cuentas.toArray(new Cuenta[cuentas.size()]);
    	} else 
    		return new Cuenta[] {};
	}

	@Override
	public Cuenta getUsuario(@WebParam(name="id")Integer id) {
		Cuenta cuenta = getServicioCuentas().obtenerPorId(id);
		return cuenta;
	}

	@Override
	public Cuenta guardarOActualizar(Cuenta cuenta) {
        Cuenta _cuenta = null;
        ServicioCuentas servicio = getServicioCuentas();
        if(cuenta .getId() != null) {
            _cuenta = servicio.obtenerPorId(cuenta.getId());
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
		return servicio.guardarOActualizar(_cuenta);
	}

}
