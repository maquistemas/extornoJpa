package pe.com.maquistemas.extornoJpa.oauth.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.com.maquistemas.extornoJpa.entity.MqUsuario;



public interface IUsuarioDao extends CrudRepository<MqUsuario, Long>{
	
	public MqUsuario findByUsername(String username);
	
	@Query("select u from MqUsuario u where u.username=?1")
	public MqUsuario findByUsername2(String username);

}
