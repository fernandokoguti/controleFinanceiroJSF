package modelo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import managedBean.Sessao;

@Entity
@Table(name="movimentacao")
//@NamedQueries({
//	@NamedQuery(
//			name="pegarMovimentacaoPorId",
//			query="FROM Movimentacao AS m WHERE m.id = :id"
//			),
//	@NamedQuery(
//			name="pegarTodosMovimentacao",
//			query="FROM Movimentacao"
//			)
//})
@ManagedBean
@ViewScoped
public class Movimentacao {

	@Id
	@GeneratedValue(generator="tobias")
	@GenericGenerator(name="tobias", strategy="increment")
	private int id;
	private int mes;
	private int ano;
	private double valor;
	@OneToOne
	@JoinColumn(name = "fk_tipo_id")
	private TipoMovimentacao tipoMovimentacao;
	
	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public String msgMes() {
		String meses="";
		switch(mes) {
		case 1:
			meses="Janeiro";
			break;
		case 2:
			meses="Fevereiro";
			break;
		case 3:
			meses="Março";
			break;
		case 4:
			meses="Abril";
			break;
		case 5:
			meses="Maio";
			break;
		case 6:
			meses="Junho";
			break;
		case 7:
			meses="Julho";
			break;
		case 8:
			meses="Agosto";
			break;
		case 9:
			meses="Setembro";
			break;
		case 10:
			meses="Outubro";
			break;
		case 11:
			meses="Novembro";
			break;
		case 12:
			meses="Dezembro";
			break;
		}
		return meses;
	}
	
    public String nomeUsuario() {
    	String nome = Sessao.getInstance().getUsuarioLogado().getNome();
    	return nome;
    }
	
}
