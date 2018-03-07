package co.com.bancopopular.cuentas.mensajería;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumidorRegistroActividad {

    @JmsListener(destination = "text.messagequeue")
    public void onMessage(String mensaje){
        System.out.println(mensaje);
    }
}
