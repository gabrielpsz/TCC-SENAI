package modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "Usuario.buscarTodos", query = "SELECT u FROM Usuario u"),
		@NamedQuery(name = "Usuario.buscarPorCPF", query = "SELECT u FROM Usuario u WHERE u.cpf = :cpf"),
		@NamedQuery(name = "Usuario.buscarPorEmailSenha", query = "SELECT u FROM Usuario u WHERE u.email = :login AND u.senha = :senha") })
public class Usuario {

	@Id
	@GeneratedValue
	private long idUsuario;

	@Column(nullable = false, length = 150)
	private String nome;

	@Column(nullable = false, length = 14)
	private String cpf;

	@Column(nullable = false, length = 100)
	private String email;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataNascimento;

	@Column(length = 15)
	private String telefoneResidencial;

	@Column(length = 15, nullable = false)
	private String celular;

	@Column(nullable = false, length = 9)
	private String sexo;

	@Column(nullable = false, length = 20)
	private String senha;

	@Embedded
	private Endereco endereco;

	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdUsuarioPdf() {
		String idPdf = Integer.toString((int) this.idUsuario);
		return idPdf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario(long idUsuario, String nome, String cpf, String email,
			Date dataNascimento, String telefoneResidencial, String celular,
			String sexo, String senha, Endereco endereco) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.telefoneResidencial = telefoneResidencial;
		this.celular = celular;
		this.sexo = sexo;
		this.senha = senha;
		this.endereco = endereco;
	}

	public Usuario() {
		this.endereco = new Endereco();
		// TODO Auto-generated constructor stub
	}

}