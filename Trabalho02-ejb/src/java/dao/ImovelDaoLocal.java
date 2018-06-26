package dao;

import bean.Endereco;
import bean.Imovel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Marcos Pachola
 */
@Local
public interface ImovelDaoLocal {
    
    public Imovel create(Imovel imovel);
   
   public Imovel retrieve(Imovel imovel);
   
   public void update(Imovel imovel);
   
   public void delete(Imovel imovel);
   
   public List<Imovel> listaTodos(boolean aluguel, boolean venda);
   
   public List<Imovel> listaTodos2();
   
   public Imovel enderecoRepetido(Endereco endereco);
   
    public boolean valida(Imovel imovel);
}
