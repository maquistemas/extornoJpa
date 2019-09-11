package pe.com.maquistemas.extornoJpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "mqroles")
public class MqRol implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_mqrol")
	@SequenceGenerator(name="seq_mqrol", allocationSize = 1, sequenceName="seq_mqroles")
	private Long id;
	
	@Column(unique = true, length = 20)
	private String nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	private static final long serialVersionUID = 1L;

}
