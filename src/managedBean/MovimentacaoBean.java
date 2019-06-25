package managedBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DAO.MovimentacaoDAO;
import modelo.Movimentacao;

@ManagedBean(name="movimentacaoBean")
@SessionScoped
public class MovimentacaoBean {
    
    private Movimentacao movimentacao = new Movimentacao();      
    private MovimentacaoDAO dao = new MovimentacaoDAO();
    public List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
    
    
    public MovimentacaoBean()
    {    
    }
    
    public void adicionar() throws SQLException{
    	System.out.println("##############ENTREI NO MOVIMENTACAOBEAN.ADICIONAR()");
    	System.out.println("##############"+movimentacao.getMes());
        System.out.println("##############"+movimentacao.getAno());
        System.out.println("##############"+movimentacao.getValor());
        System.out.println("##############"+movimentacao.getTipoMovimentacao().getNome());
           dao.inserirMovimentacao(movimentacao);
           movimentacao = new Movimentacao();
    }
          
    public List<Movimentacao> listar() throws SQLException {
        movimentacoes = dao.buscarTodas();
        return movimentacoes;
    }
    
    public void selecionarEditar(Movimentacao m){
        Movimentacao mov = new Movimentacao();
        mov = m;
    }
    
    public void deletar(String id) throws SQLException{
    	System.out.println("##############ENTREI NO MOVIMENTACAOBEAN.DELETAR()");
	    List<Movimentacao> m = dao.buscarTodas();
	    int x =1;
    	for (Movimentacao movimentacao : m) {
			if(Integer.parseInt(id) == movimentacao.getId())
				m.remove(movimentacao);
			System.out.println("##############PASSOU "+x+" VEZ(ES) DO FOREACH");
			x++;
		}
    	System.out.println("##############SAIU DO FOREACH");
        movimentacao = dao.buscarPorId(Integer.parseInt(id));
        System.out.println("##############LEU A DAO POR ID");
        System.out.println("##############"+movimentacao.getId());
    	System.out.println("##############"+movimentacao.getValor());
        System.out.println("##############"+movimentacao.getTipoMovimentacao().getNome());
    	dao.deletarMovimentacao(movimentacao);
    	Movimentacao movimentacao = new Movimentacao();
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
