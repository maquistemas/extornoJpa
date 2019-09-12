package pe.com.maquistemas.extornoJpa.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.com.maquistemas.extornoJpa.entity.MqPersona;
import pe.com.maquistemas.extornoJpa.entity.MqRegion;


public interface IMqPersonaService {
	
public List<MqPersona> findAll();
	
	public Page<MqPersona> findAll(Pageable pageable);
	
	public MqPersona findById(Long id);
	
	public MqPersona save(MqPersona mqPersona);
	
	public void delete(Long id);
	
	public List<MqRegion> findAllMqRegiones();

}
