package modelo;

import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name="Local.buscarTodos", query="SELECT l FROM Local l"),
		@NamedQuery(name="Local.buscarPorCidade", query="SELECT l FROM Local l WHERE l.cidade LIKE :cidade"),
		@NamedQuery(name="Local.buscarPorCep", query="SELECT l FROM Local l WHERE l.cep = :cep"),
		
})		
public class Local {
	
	@Id
	@GeneratedValue
	private long idLocal;
	
	@Column(nullable = false, length=50)
	private String cidade;
	
	@Column(nullable = false, length=50)
	private String bairro;
	
	@Column(nullable = false)
	private String cep;

	@Column(nullable = false, length=50)
	private String nome;
	
	@OneToMany(mappedBy="local")
	private List<Evento> evento;

	public Local() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Local(Long idLocal, String cidade, String bairro, String cep,
			String nome) {
		super();
		this.idLocal = idLocal;
		this.cidade = cidade;
		this.bairro = bairro;
		this.cep = cep;
		this.nome = nome;
	}

	public Long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(Long idLocal) {
		this.idLocal = idLocal;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNome() {
		
		return nome;
	}
	
	public String getIdLocalPdf() {
		String idPdf = Integer.toString((int) this.idLocal);
		return idPdf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Evento> getEvento() {
		return evento;
	}

	public void setEvento(List<Evento> evento) {
		this.evento = evento;
	}

	public void setIdLocal(long idLocal) {
		this.idLocal = idLocal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((evento == null) ? 0 : evento.hashCode());
		result = prime * result + (int) (idLocal ^ (idLocal >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Local other = (Local) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (evento == null) {
			if (other.evento != null)
				return false;
		} else if (!evento.equals(other.evento))
			return false;
		if (idLocal != other.idLocal)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	

}
