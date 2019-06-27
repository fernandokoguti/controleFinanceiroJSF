package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import DAO.TipoMovimentacaoDAO;
import modelo.TipoMovimentacao;

@ManagedBean(name="tipoMovimentacaoBean")
@SessionScoped
public class TipoMovimentacaoBean {
	private TipoMovimentacaoDAO tmd = new TipoMovimentacaoDAO();
	private TipoMovimentacao tipoMovimentacao = new TipoMovimentacao();
	public List<TipoMovimentacao> tipos = new ArrayList<TipoMovimentacao>();
	
	public void inserir() {
		System.out.println("##############ENTREI NO TIPOMOVIMENTACAOBEAN.INSERIR()");
		if (tipoMovimentacao.getNome() != null || tipoMovimentacao.getNome() != " " && String.valueOf(tipoMovimentacao.getEntrada()) != null) {
			tmd.inserirTipoMovimentacao(tipoMovimentacao);
			System.out.println("##############" + tipoMovimentacao.getBasica());
			System.out.println("##############" + tipoMovimentacao.getEntrada());
			System.out.println("##############" + tipoMovimentacao.getNome());
			tipoMovimentacao = new TipoMovimentacao();
		} else {
			FacesContext.getCurrentInstance().addMessage(":dialog:msg1", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Campos Vazios ou Inválidos!", "Campo(s) Inválido(s)!"));
			System.out.println("Um ou mais campos errados");
		}
	}
	
	public TipoMovimentacao pegarPorId(int id) {
		tipoMovimentacao = tmd.buscarPorId(id);
		return tipoMovimentacao;
	}
	
	public void deletarTipoMovimentacao(TipoMovimentacao tm) {
		tmd.deletarTipoMovimentacao(tm);
	}
	
	public List<TipoMovimentacao> pegarTodos(){
		tipos = tmd.buscarTodas();
		return tipos;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public List<TipoMovimentacao> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoMovimentacao> tipos) {
		this.tipos = tipos;
	}
	
}
