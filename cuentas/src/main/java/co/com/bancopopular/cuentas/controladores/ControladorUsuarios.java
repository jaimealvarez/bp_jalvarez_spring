package co.com.bancopopular.cuentas.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.bancopopular.cuentas.dominio.Usuario;
import co.com.bancopopular.cuentas.servicios.ServicioUsuarios;

@RestController
@RequestMapping(value="/usuarios")
public class ControladorUsuarios {

    private ServicioUsuarios servicioUsuarios;

    @Autowired
    public void setUsuarioService(ServicioUsuarios servicioUsuarios) {
        this.servicioUsuarios = servicioUsuarios;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Usuario> getUsuarios() {
        return servicioUsuarios.listar();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Usuario obtenerUsuario(@PathVariable Integer id) {
    	Usuario usuario = servicioUsuarios.obtenerPorId(id);
        return usuario;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Usuario> guardarOActualizarUsuario(@RequestBody Usuario usuario) {
        Usuario _usuario = null;
        if(usuario.getId() != null) {
            _usuario = servicioUsuarios.obtenerPorId(usuario.getId());
            if(_usuario != null)
            {
            	//Datos que se pueden modificar de un usuario existente
                if (usuario.getNombre() != null) {
                    _usuario.setNombre(usuario.getNombre());
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
        return ResponseEntity.ok(servicioUsuarios.guardarOActualizar(_usuario));
    }
}
