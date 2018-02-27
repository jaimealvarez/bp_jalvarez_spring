package co.com.bancopopular.cuentas.dominio;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Movimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull
	private Long valor;
	
	@NotNull
	@OneToOne
	private Cuenta cuentaDebito;
	
	@NotNull
	@OneToOne
	private Cuenta cuentaCredito;
	
	@NotNull
	private Timestamp fechaHora;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public Cuenta getCuentaDebito() {
		return cuentaDebito;
	}

	public void setCuentaDebito(Cuenta cuentaDebito) {
		this.cuentaDebito = cuentaDebito;
	}

	public Cuenta getCuentaCredito() {
		return cuentaCredito;
	}

	public void setCuentaCredito(Cuenta cuentaCredito) {
		this.cuentaCredito = cuentaCredito;
	}

	public Timestamp getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Timestamp fechaHora) {
		this.fechaHora = fechaHora;
	}
}
