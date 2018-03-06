package co.com.bancopopular.cuentas.servicios.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.com.bancopopular.cuentas.dominio.Usuario;
import co.com.bancopopular.cuentas.servicios.ServicioUsuarios;

@Service
public class ServicioDetallesUsuarioImpl implements UserDetailsService {

	private Converter<Usuario, UserDetails> convertidorDetallesUsuario;
	private ServicioUsuarios servicioUsuarios;
	
	@Autowired
	public void setServicioUsuarios(ServicioUsuarios servicioUsuarios) {
		this.servicioUsuarios = servicioUsuarios;
	}
	
	@Autowired
	public void setConvertidorDetallesUsuario(Converter<Usuario, UserDetails> convertidorDetallesUsuario) {
		this.convertidorDetallesUsuario = convertidorDetallesUsuario;
	}
	
	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		return convertidorDetallesUsuario.convert(servicioUsuarios.obtenerPorUsuario(usuario));
	}

}
