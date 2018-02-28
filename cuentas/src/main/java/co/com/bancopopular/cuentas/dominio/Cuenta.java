package co.com.bancopopular.cuentas.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cuenta {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUENTA_SEQ")
	@SequenceGenerator(sequenceName = "cuenta_seq", allocationSize = 1, name = "CUENTA_SEQ")
	private Integer id;
	
	@NotNull
	@Size(min=8,max=12)
	private String número;
	
	@NotNull
	@Min(value=100000)
	private Long saldo=100000L;
	
	@NotNull
	@ManyToOne
	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNúmero() {
		return número;
	}

	public void setNúmero(String número) {
		this.número = número;
	}

	public Long getSaldo() {
		return saldo;
	}

	public void setSaldo(Long saldo) {
		this.saldo = saldo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
