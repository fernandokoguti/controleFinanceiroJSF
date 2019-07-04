package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.hibernate.Session;
import org.hibernate.query.Query;

import conexao.ConexaoUsuario;
import managedBean.Sessao;
import modelo.Movimentacao;
	  
	public class MovimentacaoDAO {
		private Movimentacao m;
		private List<Movimentacao> movimentacoes;
		private Sessao session = new Sessao();
		private ConexaoUsuario conexao = new ConexaoUsuario();
		private Session sessao = conexao.getSessionFactory(session.getUsuarioLogado().getNomeBase()).openSession();;

	public List<Movimentacao> buscarTodas() {
		try {
	    	Query<Movimentacao> q = sessao.createQuery("from Movimentacao m order by m.ano desc");
	    	return q.list();
			} catch (Exception e) {
				return null;
			}
		}
	
		public List<SelectItem> listarMovimentacoesCombo() throws SQLException {
		final List<SelectItem> listaComboBoxMovimentacoes = new ArrayList<SelectItem>(0);
		movimentacoes.clear();
		movimentacoes = buscarTodas();
		listaComboBoxMovimentacoes.add(new SelectItem(0, "Selecione"));
		for (int i = 1; i <= (movimentacoes.size()); i++) {
			SelectItem item = new SelectItem();
			item.setValue(movimentacoes.get(i -1).getTipoMovimentacao().getNome());
			item.setValue(movimentacoes.get(i -1).getMes());
			item.setValue(movimentacoes.get(i -1).getAno());
			item.setValue(movimentacoes.get(i -1).getValor());
			listaComboBoxMovimentacoes.add(item);
		}

		return listaComboBoxMovimentacoes;

	}

	
	public Movimentacao buscarPorId(int id) {
		try {
			Query<Movimentacao> query = sessao.createQuery("FROM Movimentacao as m WHERE m.id = :id");
			query.setParameter("id", id);
			return query.uniqueResult();
			} catch (Exception e) {
				return null;
			}
		}
	 
	  public boolean inserirMovimentacao(Movimentacao movimentacao) {
		  try {
				sessao.beginTransaction();
				sessao.save(movimentacao);
				sessao.getTransaction().commit();
				return true;
	          } catch (Exception e) {
	        	  e.printStackTrace();
	        	  return false;
	          }
	    }
	     
	    public boolean deletarMovimentacao(Movimentacao movimentacao) {
	    	try {
	    		sessao.beginTransaction();
	    		sessao.remove(movimentacao);
	    		sessao.getTransaction().commit();
	    		return true;
	          } catch (Exception e) {
	        	  e.printStackTrace();
	        	  return false;
	          }
	    }	  
}
