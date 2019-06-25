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
	
}
