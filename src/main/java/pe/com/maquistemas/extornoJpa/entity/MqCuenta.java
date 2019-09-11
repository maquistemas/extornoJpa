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
@Table(name = "mqcuentas")
public class MqCuenta implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_mqcuenta")
	@SequenceGenerator(name="seq_mqcuenta", allocationSize = 1, sequenceName="seq_mqcuentas")
	private Long id;
	private BigDecimal saldo; 
	
	@NotNull(message="la persona no puede ser vacia")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mqpersonas_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private MqPersona mqpersona;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public MqPersona getMqpersona() {
		return mqpersona;
	}

	public void setMqpersona(MqPersona mqpersona) {
		this.mqpersona = mqpersona;
	}
	
	
	private static final long serialVersionUID = 1L;
}
