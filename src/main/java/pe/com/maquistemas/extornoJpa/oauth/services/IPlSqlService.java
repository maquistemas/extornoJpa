package pe.com.maquistemas.extornoJpa.oauth.services;

import java.math.BigDecimal;

public interface IPlSqlService {
	
	public String sp_transferencia(Integer PAR_TRANSF_USUARIO_ID , Integer PAR_TRANSF_CUENTA_ORIGEN,  Integer PAR_TRANSF_CUENTA_DESTINO, BigDecimal PAR_TRANSF_MONTO);
	public String sp_extorno(Integer PAR_TRANSF_ID, Integer PAR_USUARIO_ID);
	public Integer sf_suma(Integer a, Integer b);

}
