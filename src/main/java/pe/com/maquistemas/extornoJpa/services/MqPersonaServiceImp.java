package pe.com.maquistemas.extornoJpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.maquistemas.extornoJpa.dao.IMqPersonaDao;
import pe.com.maquistemas.extornoJpa.entity.MqPersona;
import pe.com.maquistemas.extornoJpa.entity.MqRegion;

@Service
public class MqPersonaServiceImp implements IMqPersonaService {
	@Autowired
	IMqPersonaDao iMqPersonaDao;

	@Override
	@Transactional(readOnly = true)
	public List<MqPersona> findAll() {
		return (List<MqPersona>)iMqPersonaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<MqPersona> findAll(Pageable pageable) {
		return iMqPersonaDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public MqPersona findById(Long id) {
		return iMqPersonaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public MqPersona save(MqPersona mqPersona) {
		return iMqPersonaDao.save(mqPersona);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		iMqPersonaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MqRegion> findAllMqRegiones() {
		return iMqPersonaDao.findAllMqRegiones();
	}

}
