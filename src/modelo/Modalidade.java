package modelo;

import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name="Modalidade.buscarTodos", query="SELECT m FROM Modalidade m"),
	@NamedQuery(name="Modalidade.buscarPorTipo", query="SELECT m FROM Modalidade m WHERE m.tipo LIKE :tipo")
})
public class Modalidade {
	
	@Id
	@GeneratedValue
	private long idModalidade;
	
	@Column(nullable=false)
	private String tipo;
	
	
	@OneToMany(mappedBy="modalidade")
	private List<Evento> evento; 
	
	public String getIdModalidadePdf() {
		String idPdf = Integer.toString((int) this.idModalidade);
		return idPdf;
	}
	
	public List<Evento> getEvento() {
		return evento;
	}

	public void setEvento(List<Evento> evento) {
		this.evento = evento;
	}

	public long getIdModalidade() {
		return idModalidade;
	}

	public void setIdModalidade(long idModalidade) {
		this.idModalidade = idModalidade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Modalidade(long idModalidade, String tipo) {
		super();
		this.idModalidade = idModalidade;
		this.tipo = tipo;
	}

	public Modalidade() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((evento == null) ? 0 : evento.hashCode());
		result = prime * result + (int) (idModalidade ^ (idModalidade >>> 32));
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Modalidade other = (Modalidade) obj;
		if (evento == null) {
			if (other.evento != null)
				return false;
		} else if (!evento.equals(other.evento))
			return false;
		if (idModalidade != other.idModalidade)
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	
}
