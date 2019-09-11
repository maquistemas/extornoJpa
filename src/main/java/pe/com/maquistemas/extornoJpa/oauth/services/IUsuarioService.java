package pe.com.maquistemas.extornoJpa.oauth.services;

import pe.com.maquistemas.extornoJpa.entity.MqUsuario;

public interface IUsuarioService {

	public MqUsuario findByUsername(String username);
}
