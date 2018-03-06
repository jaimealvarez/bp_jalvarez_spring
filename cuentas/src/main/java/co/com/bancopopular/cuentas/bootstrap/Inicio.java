package co.com.bancopopular.cuentas.bootstrap;

import javax.xml.ws.Endpoint;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import co.com.bancopopular.cuentas.controladores.soap.ServicioWebCuentasImpl;
import co.com.bancopopular.cuentas.controladores.soap.ServicioWebUsuariosImpl;

@Component
public class Inicio implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		Endpoint.publish("http://localhost:8081/users", new ServicioWebUsuariosImpl());
		Endpoint.publish("http://localhost:8081/accounts", new ServicioWebCuentasImpl());
	}

}
