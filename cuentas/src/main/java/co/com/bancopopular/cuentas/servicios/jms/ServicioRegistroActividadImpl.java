package co.com.bancopopular.cuentas.servicios.jms;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import co.com.bancopopular.cuentas.servicios.ServicioRegistroActividad;

@Component
public class ServicioRegistroActividadImpl implements ServicioRegistroActividad {

    private Queue cola;
    private JmsTemplate jmsTemplate;

    @Autowired
    public void setTextMessageQueue(Queue cola) {
        this.cola = cola;
    }

    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

	@Override
	public void registrarSolicitud(String solicitud) {
        this.jmsTemplate.convertAndSend(this.cola, solicitud);

	}
}
