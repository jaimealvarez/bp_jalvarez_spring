package co.com.bancopopular.cuentas.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import co.com.bancopopular.cuentas.dominio.Departamento;

@RepositoryRestResource
public interface RepositorioDepartamentos extends CrudRepository<Departamento, Integer>{

}
