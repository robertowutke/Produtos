package fpu.si5p.produtos.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;;

@Entity
public class Usuarios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String senha;
	private int ativo = 1;
	@ManyToMany
	@JoinTable(name = "usuario_papel", 
				joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName="id"), 
				inverseJoinColumns = @JoinColumn(name = "papel_id", referencedColumnName="id"))
	private List<Papeis> papel;
	
	public Usuarios() {
		
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public List<Papeis> getPapel() {
		return papel;
	}



	public void setPapel(List<Papeis> papel) {
		this.papel = papel;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}	
}
