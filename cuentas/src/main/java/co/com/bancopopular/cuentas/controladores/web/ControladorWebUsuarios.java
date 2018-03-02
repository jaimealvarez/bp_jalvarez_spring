package co.com.bancopopular.cuentas.controladores.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "usuarios";
    }
}
