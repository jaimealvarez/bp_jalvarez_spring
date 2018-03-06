package co.com.bancopopular.cuentas.servicios.seguridad;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import co.com.bancopopular.cuentas.dominio.Usuario;

@Component
public class ConvertidorDetallesUsuario implements Converter<Usuario, UserDetails>{

	@Override
	public UserDetails convert(Usuario usuario) {

        DetallesUsuarioImpl detallesUsuario = new DetallesUsuarioImpl();

        if (usuario!= null) {
            detallesUsuario.setUsuario(usuario.getUsuario());
            detallesUsuario.setClave(usuario.getClave());
        }

        return detallesUsuario;
	}

}
