package managedBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import DAO.MovimentacaoDAO;
import modelo.Movimentacao;

@ManagedBean(name = "movimentacaoBean")
@SessionScoped
public class MovimentacaoBean {
	private Movimentacao movimentacao = new Movimentacao();
	private MovimentacaoDAO dao = new MovimentacaoDAO();
	public List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();

	public MovimentacaoBean() {
	}

	public void adicionar() throws SQLException {
		System.out.println("##############ENTREI NO MOVIMENTACAOBEAN.ADICIONAR()");
		if (movimentacao.getTipoMovimentacao() != null && movimentacao.getAno() > 1900) {
			dao.inserirMovimentacao(movimentacao);
			System.out.println("##############" + movimentacao.getMes());
			System.out.println("##############" + movimentacao.getAno());
			System.out.println("##############" + movimentacao.getValor());
			System.out.println("##############" + movimentacao.getTipoMovimentacao().getNome());
			movimentacao = new Movimentacao();
		} else {
			FacesContext.getCurrentInstance().addMessage(":form:msg", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Campos Vazios ou Inválidos!", "Campo(s) Inválido(s)!"));
			System.out.println("Um ou mais campos errados");
		}
	}

	public List<Movimentacao> listar() throws SQLException {
		movimentacoes = dao.buscarTodas();
		return movimentacoes;
	}

	public void selecionarEditar(Movimentacao m) {
		Movimentacao mov = new Movimentacao();
		mov = m;
	}

	public void deletar(String id) throws SQLException {
		System.out.println("##############ENTREI NO MOVIMENTACAOBEAN.DELETAR()");
		int x = 1;
		List<Movimentacao> m = dao.buscarTodas();
		for (Movimentacao mov : m) {
			if (Integer.parseInt(id) == mov.getId()) {
				movimentacao = mov;
				dao.deletarMovimentacao(movimentacao);
				m.remove(mov);
				movimentacao = new Movimentacao();
			}
			System.out.println("##############PASSOU " + x + " VEZ(ES) NO FOREACH");
			x++;
		}

	}

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}
}
