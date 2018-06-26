package dao;

import bean.Endereco;
import bean.Imovel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Marcos Pachola
 */
@Stateless
public class ImovelDao implements ImovelDaoLocal {

    @PersistenceContext
    private EntityManager em;
    //Crud no banco 
    //create = persist
    //delete = remove
    //retrive = find
    //update = merge
    //query = consulta

    @Override
    public Imovel create(Imovel imovel) {
        if (this.valida(imovel)) {
            em.persist(imovel);
            return imovel;
        } else {
            return null;
        }
    }

    @Override
    public Imovel retrieve(Imovel imovel) {
        Imovel usuRet = em.find(Imovel.class, imovel.getId());
        return usuRet;
    }

    @Override
    public void update(Imovel imovel) {
        if (this.valida(imovel)) {
            em.merge(imovel);
        }
    }

    @Override
    public void delete(Imovel imovel) {
        Query query = em.createQuery("DELETE FROM Imovel imovel WHERE imovel.id = :imovelId");
        query.setParameter("imovelId", imovel.getId());
        query.executeUpdate();
    }

    @Override
    public List<Imovel> listaTodos(boolean aluguel, boolean venda) {
        //List<Endereco> listEnd = null;
        Query query = em.createQuery("SELECT imovel FROM Imovel imovel WHERE imovel.aluguel = :Aluguel and imovel.venda = :Venda");
        query.setParameter("Aluguel", aluguel);
        query.setParameter("Venda", venda);
        //listEnd = query.getResultList();
        return query.getResultList();
    }
    
    public List<Imovel> listaTodos2() {
        Query query = em.createQuery("SELECT imovel FROM Imovel imovel");
        return query.getResultList();
    }
    

//Testar endereco repetido no banco
    @Override
    public Imovel enderecoRepetido(Endereco endereco) {
        Imovel imovel = null;
        Query query = em.createQuery("Select imovel from Imovel imovel where imovel.endereco = :end");
        query.setParameter("end", endereco);
        List<Imovel> listaImovel = (List<Imovel>) query.getResultList();
        if (!listaImovel.isEmpty()) {
            imovel = listaImovel.get(0);
        }
        return imovel;
    }

    @Override
    public boolean valida(Imovel imovel) {
        boolean ret = false;
        if (imovel != null) {
            ret = true;
        }
        return ret;
    }
}
