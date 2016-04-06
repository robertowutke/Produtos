package fpu.si5p.produtos.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Papeis
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String papel;
	@ManyToMany
	@JoinTable(name = "usuario_papel", 
				joinColumns = @JoinColumn(name = "papel_id", referencedColumnName="id"), 
				inverseJoinColumns = @JoinColumn(name = "usuario_id", referencedColumnName="id"))
	private List<Usuarios> usuario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPapel() {
		return papel;
	}
	public void setPapel(String papel) {
		this.papel = papel;
	}
	public List<Usuarios> getUsuario() {
		return usuario;
	}
	public void setUsuario(List<Usuarios> usuario) {
		this.usuario = usuario;
	}
}
