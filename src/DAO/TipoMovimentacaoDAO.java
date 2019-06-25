package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.hibernate.Session;
import org.hibernate.query.Query;

import conexao.ConexaoUsuario;
import modelo.TipoMovimentacao;

public class TipoMovimentacaoDAO {
			private TipoMovimentacao tm;
			private List<TipoMovimentacao> tipos;
			private Session sessao = ConexaoUsuario.getSessionFactory().openSession();;

		public List<TipoMovimentacao> buscarTodas() {
			try {
		    	Query<TipoMovimentacao> q = sessao.createQuery("from TipoMovimentacao");
		    	return q.list();
				} catch (Exception e) {
					return null;
				}
			}
		
		public List<SelectItem> listarTiposCombo() throws SQLException {
			final List<SelectItem> listaComboBoxTipos = new ArrayList<SelectItem>(0);
			tipos.clear();
			tipos = buscarTodas();
			listaComboBoxTipos.add(new SelectItem(0, "Selecione"));
			for (int i = 1; i <= (tipos.size()); i++) {
				SelectItem item = new SelectItem();
				item.setLabel(tipos.get(i -1).getNome());
				item.setValue(tipos.get(i -1).getEntrada());
				item.setValue(tipos.get(i -1).getBasica());
				listaComboBoxTipos.add(item);
			}
			return listaComboBoxTipos;
		}

		
		public TipoMovimentacao buscarPorId(int id) {
			try {
				Query<TipoMovimentacao> query = sessao.createQuery("FROM TipoMovimentacao as tm WHERE tm.id = :id");
				query.setParameter("id", id);
				return query.uniqueResult();

				} catch (Exception e) {
					return null;
				}
			}
		 
		  public boolean inserirTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
			  try {
					sessao.beginTransaction();
					sessao.save(tipoMovimentacao);
					sessao.getTransaction().commit();
					return true;
		          } catch (Exception e) {
		        	  e.printStackTrace();
		        	  return false;
		          }
		    }
		     
		    public boolean deletarTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		    	try {
		    		sessao.beginTransaction();
		    		sessao.remove(tipoMovimentacao);
		    		sessao.getTransaction().commit();
		    		return true;
		          } catch (Exception e) {
		        	  e.printStackTrace();
		        	  return false;
		          }
		    }	  
	}
