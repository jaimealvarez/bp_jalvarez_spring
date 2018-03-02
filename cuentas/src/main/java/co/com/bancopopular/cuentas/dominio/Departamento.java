package co.com.bancopopular.cuentas.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Departamento {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEPARTAMENTO_SEQ")
	@SequenceGenerator(sequenceName = "departamento_seq", allocationSize = 1, name = "DEPARTAMENTO_SEQ")
	private Integer id;
	
	private String nombre;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "departamento", orphanRemoval = true)
	private List<Municipio> municipios = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}
}
