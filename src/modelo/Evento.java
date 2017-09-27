package modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name="Evento.buscarTodos", query="SELECT e FROM Evento e"),
	@NamedQuery(name="Evento.buscarPorNome", query="SELECT e FROM Evento e WHERE nome LIKE :nome")
})
public class Evento {
	
	@Id
	@GeneratedValue
	private long idEvento;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private Date dataInicio;
	
	@Column(nullable=false)
	private Date dataTermino;
	
	@Column(nullable=false)
	private String empresaResponsavel;
	
	@ManyToOne(optional=false)
	private Local local;
	
	@ManyToOne(optional=false)
	private Modalidade modalidade;
	
	@OneToMany(mappedBy="evento")
	List<Ingresso> ingresso;
	
	private  int valorIngressoEvento;
	
	public String getIdEventoPdf() {
		String idPdf = Integer.toString((int) this.idEvento);
		return idPdf;
	}

	public long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(long idEvento) {
		this.idEvento = idEvento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getEmpresaResponsavel() {
		return empresaResponsavel;
	}

	public void setEmpresaResponsavel(String empresaResponsavel) {
		this.empresaResponsavel = empresaResponsavel;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

	public List<Ingresso> getIngresso() {
		return ingresso;
	}

	public void setIngresso(List<Ingresso> ingresso) {
		this.ingresso = ingresso;
	}

	public int getValorIngressoEvento() {
		return valorIngressoEvento;
	}

	public void setValorIngressoEvento(int valorIngressoEvento) {
		this.valorIngressoEvento = valorIngressoEvento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result
				+ ((dataTermino == null) ? 0 : dataTermino.hashCode());
		result = prime
				* result
				+ ((empresaResponsavel == null) ? 0 : empresaResponsavel
						.hashCode());
		result = prime * result + (int) (idEvento ^ (idEvento >>> 32));
		result = prime * result
				+ ((ingresso == null) ? 0 : ingresso.hashCode());
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result
				+ ((modalidade == null) ? 0 : modalidade.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + valorIngressoEvento;
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
		Evento other = (Evento) obj;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (dataTermino == null) {
			if (other.dataTermino != null)
				return false;
		} else if (!dataTermino.equals(other.dataTermino))
			return false;
		if (empresaResponsavel == null) {
			if (other.empresaResponsavel != null)
				return false;
		} else if (!empresaResponsavel.equals(other.empresaResponsavel))
			return false;
		if (idEvento != other.idEvento)
			return false;
		if (ingresso == null) {
			if (other.ingresso != null)
				return false;
		} else if (!ingresso.equals(other.ingresso))
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (modalidade == null) {
			if (other.modalidade != null)
				return false;
		} else if (!modalidade.equals(other.modalidade))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (valorIngressoEvento != other.valorIngressoEvento)
			return false;
		return true;
	}

	public Evento(long idEvento, String nome, Date dataInicio,
			Date dataTermino, String empresaResponsavel, Local local,
			Modalidade modalidade, List<Ingresso> ingresso,
			int valorIngressoEvento) {
		super();
		this.idEvento = idEvento;
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.empresaResponsavel = empresaResponsavel;
		this.local = local;
		this.modalidade = modalidade;
		this.ingresso = ingresso;
		this.valorIngressoEvento = valorIngressoEvento;
	}

	public Evento() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
