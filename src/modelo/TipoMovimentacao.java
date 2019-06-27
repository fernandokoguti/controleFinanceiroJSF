package modelo;

import java.io.Serializable;

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
@Table(name="tipomovimentacao")
@NamedQueries({
	@NamedQuery(
			name="pegarMovimentacaoPorId",
			query="FROM TipoMovimentacao AS tm WHERE tm.id = :id"
			),
	@NamedQuery(
			name="pegarTodosMovimentacoes",
			query="FROM TipoMovimentacao"
			)
})
@ManagedBean
@ViewScoped
public class TipoMovimentacao implements Serializable {

	@Id
	@GeneratedValue(generator="tobias")
	@GenericGenerator(name="tobias", strategy="increment")
	private int id;
	private String nome;
	private int entrada;
	private int basica;
	
	//private List tipos= new ArrayList<TipoMovimentacao>();
	
	public TipoMovimentacao(int id, String nome, int entrada, int basica) {
		super();
		this.id = id;
		this.nome = nome;
		this.entrada = entrada;
		this.basica = basica;
	}
	
	public TipoMovimentacao() {
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
	public int getEntrada() {
		return entrada;
	}
	public void setEntrada(int entrada) {
		this.entrada = entrada;
	}
	public int getBasica() {
		return basica;
	}
	public void setBasica(int basica) {
		this.basica = basica;
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((String.valueOf(id) == null) ? 0 : String.valueOf(id).hashCode());
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
        TipoMovimentacao other = (TipoMovimentacao) obj;
        if (String.valueOf(id) == null) {
            if (String.valueOf(other.id) != null)
                return false;
        } else if (!String.valueOf(id).equals(String.valueOf(other.id)))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Classe [id=" + id + ", nome=" + nome + "]";
    }
    
    public String msgBasica() {
		if(this.getBasica() == 0) {
			return "Básica";
		}else
		return "Desnecessária";
	}
    
    public String msgEntrada() {
		if(this.getEntrada() == 0) {
			return "Entrada";
		}else
		return "Saída";
	}
    
}
