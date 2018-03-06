package co.com.bancopopular.cuentas.controladores.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import co.com.bancopopular.cuentas.dominio.Cuenta;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ServicioWebCuentas {

	@WebMethod
	Cuenta[] getCuentas();

	@WebMethod
	Cuenta getUsuario(@WebParam(name="id") Integer id);
	
	@WebMethod
	Cuenta guardarOActualizar(@WebParam(name="cuenta") Cuenta cuenta);
}
