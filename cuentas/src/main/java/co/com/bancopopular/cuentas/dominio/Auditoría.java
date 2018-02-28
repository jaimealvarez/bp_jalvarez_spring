package co.com.bancopopular.cuentas.dominio;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="auditoría")
public class Auditoría {

	private String acción;
	
	private String parámetros;
	
	private String usuario;
	
	private Date fechaHora;

	public String getAcción() {
		return acción;
	}

	public void setAcción(String acción) {
		this.acción = acción;
	}

	public String getParámetros() {
		return parámetros;
	}

	public void setParámetros(String parámetros) {
		this.parámetros = parámetros;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
}
