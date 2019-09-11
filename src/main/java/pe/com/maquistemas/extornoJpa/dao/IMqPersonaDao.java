package pe.com.maquistemas.extornoJpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.maquistemas.extornoJpa.entity.MqPersona;
import pe.com.maquistemas.extornoJpa.entity.MqRegion;

public interface IMqPersonaDao extends JpaRepository<MqPersona, Long>{
	
	@Query("from MqRegion")
	public List<MqRegion> findAllMqRegiones();

}
