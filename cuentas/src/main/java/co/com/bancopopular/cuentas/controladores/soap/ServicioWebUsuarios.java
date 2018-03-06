package co.com.bancopopular.cuentas.controladores.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import co.com.bancopopular.cuentas.dominio.Usuario;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ServicioWebUsuarios {

	@WebMethod
	Usuario[] getUsuarios();

	@WebMethod
	Usuario getUsuario(@WebParam(name="id") Integer id);
	
	@WebMethod
	Usuario guardarOActualizar(@WebParam(name="usuario") Usuario usuario);
}