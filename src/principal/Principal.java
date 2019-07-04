package principal;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import DAO.UsuarioDAO;
import conexao.ConexaoUsuario;
import managedBean.MovimentacaoBean;
import modelo.Movimentacao;
import modelo.Usuario;

public class Principal {
	public List pegarMovimentacoes() {
		List lista = new ArrayList<Movimentacao>();
		MovimentacaoBean mc = new MovimentacaoBean();
		lista = mc.movimentacoes;
		Movimentacao m = new Movimentacao();
		m.getTipoMovimentacao().getNome();
		return lista;
	}
	
	public static void main(String[] args) {
		UsuarioDAO udao = new UsuarioDAO();
		ConexaoUsuario conexao = new ConexaoUsuario();
		List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
		List<Movimentacao> todos = new ArrayList<Movimentacao>();
		try {
			List<Usuario> users = udao.buscarTodos();
			for (Usuario usuario : users) {
				String base = usuario.getNomeBase();
				String teste="";
				Session sessionAdm = conexao.getSessionFactory(base).openSession();
				Query<Movimentacao> q = sessionAdm.createQuery("from Movimentacao");
				movimentacoes = q.getResultList();
				for (Movimentacao movimentacao : movimentacoes) {
					System.out.println(movimentacoes.size()+ " ######");
					teste = usuario.getNome();
					
					System.out.println(movimentacao.nomeUsuario(teste)+ " UUUUU");
					todos.add(movimentacao);
				}
//				conexao.getSessionFactory(base).close();
//			}
//			return movimentacoesAdm;
				for (Movimentacao resultado : todos) {
				System.out.println(resultado.nomeUsuario(teste)+" - "+resultado.getId()+" - "+resultado.msgMes()+" - "+resultado.getTipoMovimentacao().getNome());
				}
			}	
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Pauleou valendo"+e.getStackTrace());// TODO: handle exception
		}
	}
		
//		try {
//			MovimentacaoDAO dao = new MovimentacaoDAO();
//			MovimentacaoBean mc = new MovimentacaoBean();
//			UsuarioBean controle = new UsuarioBean();
//			//INSERIR PESSOA
////			List<Usuario> u = new ArrayList<Usuario>();
////			Usuario p = new Usuario();
////			p.setNome("teste");
////			p.setTelefone("54613");
////			controle.inserirPessoa(p);
//			
//			//PEGAR PESSOA POR ID
////			u = controle.pegarTodos();
////			
////			if(u != null)
////				for(Usuario us : u)
////				System.out.println("Pessoa = "+us.getNome());
////			else
////				System.out.println("Pessoa não encontrada.");
////		
////			//PEGAR TODAS PESSOAS DA LISTA
////			String st = Sessao.VerificaLogin("admin", "admin");
////			System.out.println(st);
////			
//			//Movimentacao m = mc.pegarPorId(0);
//			Movimentacao movimentacao = new Movimentacao();
//			TipoMovimentacaoBean tpc = new TipoMovimentacaoBean();
//			TipoMovimentacao tp = new TipoMovimentacao();
//			tp.setId(2);
////			tp.setBasica(1);
////			tp.setEntrada(1);
////			tp.setNome("Teste");
////			tpc.inserirTipoMovimentacao(tp);
////			TipoMovimentacao tpMovimentacao= new TipoMovimentacao();
////			List<TipoMovimentacao> ltp = tpc.pegarTodos();
////			for (TipoMovimentacao tipoMovimentacao : ltp) {
////				System.out.println(tipoMovimentacao.getNome()+" - "+tipoMovimentacao.getId());
////				tpMovimentacao = tipoMovimentacao;
////			}
//			movimentacao.setMes(4);
//			movimentacao.setAno(2019);
//			movimentacao.setValor(342);
//			movimentacao.setTipoMovimentacao(tp);
//			//mc.adicionar(movimentacao);
//			//Movimentacao m = mc.getMovimentacao();
////			ConexaoUsuario u = new ConexaoUsuario();
////			Usuario user1=new Usuario();
////			u.getSessionFactory().openSession().getClass().cast(user1);
//			Movimentacao m = new Movimentacao();
//			m = dao.buscarPorId(4);
//			
//			if(m != null) {
//				System.out.println("deletou do banco: "+m.getMes() + m.getTipoMovimentacao().getNome());
//				dao.deletarMovimentacao(m);}
//			else
//			System.out.println("Erro ao buscar.");
//			//FECHAR CONEXAO
//			//ConexaoAdm.shutdown();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
}
