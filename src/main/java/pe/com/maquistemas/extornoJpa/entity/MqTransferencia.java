package pe.com.maquistemas.extornoJpa.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "mqtransferencias")
public class MqTransferencia implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_mqtransferencia")
	@SequenceGenerator(name="seq_mqtransferencia", allocationSize = 1, sequenceName="seq_mqtransferencias")
	private Long id;
	
	@NotNull(message="el usuario no puede ser vacio")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mqusuarios_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private MqUsuario mqusuario;
	
	@NotNull(message="la cuenta origen no puede ser vacia")
	@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name="mqcuentas_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private MqCuenta mqcuentaOrigen;
	
	@NotNull(message="la cuenta destino no puede ser vacia")
	@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name="mqcuentas_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private MqCuenta mqcuentaDestino;
	
	
//	@NotNull(message="la auditoria no puede ser vacia")
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="mqAuditorias_id")
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	private MqAuditoria mqAuditoria;
	
	
	
	private BigDecimal monto;
	
	private String tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MqUsuario getMqusuario() {
		return mqusuario;
	}

	public void setMqusuario(MqUsuario mqusuario) {
		this.mqusuario = mqusuario;
	}

	public MqCuenta getMqcuentaOrigen() {
		return mqcuentaOrigen;
	}

	public void setMqcuentaOrigen(MqCuenta mqcuentaOrigen) {
		this.mqcuentaOrigen = mqcuentaOrigen;
	}

	public MqCuenta getMqcuentaDestino() {
		return mqcuentaDestino;
	}

	public void setMqcuentaDestino(MqCuenta mqcuentaDestino) {
		this.mqcuentaDestino = mqcuentaDestino;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
		
//	public MqAuditoria getMqAuditoria() {
//		return mqAuditoria;
//	}
//
//	public void setMqAuditoria(MqAuditoria mqAuditoria) {
//		this.mqAuditoria = mqAuditoria;
//	}




	private static final long serialVersionUID = 1L;

}
