package co.com.bancopopular.cuentas.controladores.soap;

import java.util.List;

import javax.jws.WebService;

import co.com.bancopopular.cuentas.dominio.Usuario;
import co.com.bancopopular.cuentas.servicios.ServicioUsuarios;
import co.com.bancopopular.cuentas.util.BeanUtil;

@WebService(endpointInterface = "co.com.bancopopular.cuentas.controladores.soap.ServicioWebUsuarios")
public class ServicioWebUsuariosImpl implements ServicioWebUsuarios {

    private ServicioUsuarios servicioUsuarios;

    public ServicioUsuarios getServicioUsuarios() {
    	if (servicioUsuarios == null) {
    		this.servicioUsuarios = BeanUtil.getBean(ServicioUsuarios.class);
    	}
    	return this.servicioUsuarios;
    }

    @Override
    public Usuario[] getUsuarios() {
    	List<Usuario> usuarios = getServicioUsuarios().listar();
    	if (usuarios != null) {
    		return (Usuario[])usuarios.toArray(new Usuario[usuarios.size()]);
    	} else 
    		return new Usuario[] {};
    }

	@Override
	public Usuario getUsuario(Integer id) {
		Usuario usuario = getServicioUsuarios().obtenerPorId(id);
		return usuario;
	}

	@Override
	public Usuario guardarOActualizar(Usuario usuario) {
        Usuario _usuario = null;
    	ServicioUsuarios servicio = getServicioUsuarios();
        if(usuario.getId() != null) {
            _usuario = servicio.obtenerPorId(usuario.getId());
            if(_usuario != null)
            {
                if (usuario.getNombre() != null) {
                    _usuario.setNombre(usuario.getNombre());
                }
                if (usuario.getUsuario() != null) {
                	_usuario.setUsuario(usuario.getUsuario());
                }
                if (usuario.getClave() != null) {
                	_usuario.setClave(usuario.getClave());
                }
                if (usuario.getCuentas() != null) {
                    _usuario.setCuentas(usuario.getCuentas());
                }
            }else{
            	_usuario = usuario; 
            }
        } else {
            _usuario = usuario;
        }
        return servicio.guardarOActualizar(_usuario);
	}
}
