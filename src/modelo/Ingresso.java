package modelo;

import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name="Ingresso.buscarTodos", query="SELECT i FROM Ingresso i")
})
public class Ingresso {
	
	@Id
	@GeneratedValue
	private long idIngresso;
	
	@Column(nullable=false)
	private String evento;
		
	@Column(nullable=false)
	private long idComprador;
	
	@Column(nullable=false)
	private String emailComprador;

	public long getIdIngresso() {
		return idIngresso;
	}

	public void setIdIngresso(long idIngresso) {
		this.idIngresso = idIngresso;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public long getIdComprador() {
		return idComprador;
	}

	public void setIdComprador(long idComprador) {
		this.idComprador = idComprador;
	}

	public String getEmailComprador() {
		return emailComprador;
	}

	public void setNomeComprador(String emailComprador) {
		this.emailComprador = emailComprador;
	}

	
	public Ingresso(long idIngresso, String evento, long idComprador,
			String emailComprador) {
		super();
		this.idIngresso = idIngresso;
		this.evento = evento;
		this.idComprador = idComprador;
		this.emailComprador = emailComprador;
	}

	public Ingresso() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	


}
