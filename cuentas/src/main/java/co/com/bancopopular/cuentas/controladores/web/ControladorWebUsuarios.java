package co.com.bancopopular.cuentas.controladores.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.bancopopular.cuentas.dominio.Usuario;
import co.com.bancopopular.cuentas.servicios.ServicioUsuarios;

@Controller
@RequestMapping("/usuario")
public class ControladorWebUsuarios {

    private ServicioUsuarios servicioUsuarios;

    @Autowired
    public void setServicioUsuarios(ServicioUsuarios servicioUsuarios) {
        this.servicioUsuarios = servicioUsuarios;
    }

    @RequestMapping({"/listar"})
    public String listarUsers(Model model){
        model.addAttribute("usuarios", servicioUsuarios.listar());
        return "publico/usuarios";
    }
    
    @RequestMapping({"/crear"})
    public String crearUsuario(Model model){
    	model.addAttribute("usuario", new Usuario());
        return "privado/crear_usuario";
    }
    
    @RequestMapping({"/mostrar/{id}"})
    public String mostrar(@PathVariable Integer id, Model model) {
    	model.addAttribute("usuario", servicioUsuarios.obtenerPorId(id));
    	return "publico/mostrar";
    }
    
    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardarOActualizar(Usuario usuario){
        Usuario usuarioGuardado = servicioUsuarios.guardarOActualizar(usuario);
        return "redirect:/usuario/mostrar/" + usuarioGuardado.getId();
    }
}
