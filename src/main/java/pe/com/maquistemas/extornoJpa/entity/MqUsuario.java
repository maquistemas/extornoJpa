package pe.com.maquistemas.extornoJpa.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "mqusuarios")
public class MqUsuario implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_mqusuario")
	@SequenceGenerator(name="seq_mqusuario", allocationSize = 1, sequenceName="seq_mqusuarios")
	private Long id;
	
	@Column(unique = true, length = 20)
	private String username;
	
	@Column(length = 60)
	private String password;
	private Boolean enabled;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="mqusuarios_mqroles", joinColumns= @JoinColumn(name="mqusuarios_id"),
	inverseJoinColumns=@JoinColumn(name="mqroles_id"),
	uniqueConstraints= {@UniqueConstraint(columnNames= {"mqusuarios_id", "mqroles_id"})})
	private List<MqRol> mqroles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<MqRol> getMqroles() {
		return mqroles;
	}

	public void setMqroles(List<MqRol> mqroles) {
		this.mqroles = mqroles;
	}
	
	
	private static final long serialVersionUID = 1L;
	

}
