package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DAO.TipoMovimentacaoDAO;
import modelo.TipoMovimentacao;

@ManagedBean(name="tipoMovimentacaoBean")
@SessionScoped
public class TipoMovimentacaoBean {
	private TipoMovimentacaoDAO tmd = new TipoMovimentacaoDAO();
	private TipoMovimentacao tm = new TipoMovimentacao();
	public List<TipoMovimentacao> tipos = new ArrayList<TipoMovimentacao>();
	
	public void inserirTipoMovimentacao(TipoMovimentacao tm) {
		tmd.inserirTipoMovimentacao(tm);
	}
	
	public TipoMovimentacao pegarPorId(int id) {
		tm = tmd.buscarPorId(id);
		return tm;
	}
	
	public void deletarTipoMovimentacao(TipoMovimentacao tm) {
		tmd.deletarTipoMovimentacao(tm);
	}
	
	public List<TipoMovimentacao> pegarTodos(){
		tipos = tmd.buscarTodas();
		return tipos;
	}
	
}
