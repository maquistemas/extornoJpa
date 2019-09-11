package pe.com.maquistemas.extornoJpa.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "mqauditorias")
public class MqAuditoria implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_mqauditoria")
	@SequenceGenerator(name="seq_mqauditoria", allocationSize = 1, sequenceName="seq_mqauditorias")
	private Long id;
	
	private Date fecha;
	
	private String schema;
	
	@OneToOne
	@JoinColumn(name = "mqtransferencias_id")
	private MqTransferencia mqtransferencia;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}
	
	
		
	public MqTransferencia getMqtransferencia() {
		return mqtransferencia;
	}

	public void setMqtransferencia(MqTransferencia mqtransferencia) {
		this.mqtransferencia = mqtransferencia;
	}




	private static final long serialVersionUID = 1L;

}
