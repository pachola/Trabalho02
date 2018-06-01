package dao;

import bean.Endereco;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Marcos Pachola
 */
@Local
public interface EnderecoDaoLocal {
    
   public Endereco create(Endereco endereco);
   
   public Endereco retrive(Endereco endereco);
   
   public void update(Endereco endereco);
   
   public void delete(Endereco endereco);
   
   public List<Endereco> listaTodos();
   
    public boolean valida(Endereco endereco);
    
}
