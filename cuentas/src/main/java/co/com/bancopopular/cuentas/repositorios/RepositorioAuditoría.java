package co.com.bancopopular.cuentas.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.bancopopular.cuentas.dominio.Auditoría;

public interface RepositorioAuditoría extends MongoRepository<Auditoría, Integer>{

}
