package pe.com.maquistemas.extornoJpa.services;

import pe.com.maquistemas.extornoJpa.entity.MqUsuario;

public interface IUsuarioService {

	public MqUsuario findByUsername(String username);
}
