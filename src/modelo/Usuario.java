package modelo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="usuario")
@NamedQueries({
	@NamedQuery(
			name="pegarUsuarioPorId",
			query="FROM Usuario AS p WHERE p.id = :id"
			),
	@NamedQuery(
			name="pegarTodosUsuarios",
			query="FROM Usuario"
			)
})
@ManagedBean
@ViewScoped
public class Usuario {

	@Id
	@GeneratedValue(generator="tobias")
	@GenericGenerator(name="tobias", strategy="increment")
	private int id;
	private String nome;
	private String login;
	private String senha;
	private String nomeBase;
	private int tipoUsuario;
	
	public Usuario(){
		
	}
	
	public Usuario(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNomeBase() {
		return nomeBase;
	}
	public void setNomeBase(String nomeBase) {
		this.nomeBase = nomeBase;
	}
	public int getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
