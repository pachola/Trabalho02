package dao;

import bean.Endereco;
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
public class EnderecoDao implements EnderecoDaoLocal {

    @PersistenceContext
    private EntityManager em;
    //Crud no banco 
    //create = persist
    //delete = remove
    //retrive = find
    //update = merge
    //Query = consulta

    @Override
    public Endereco create(Endereco endereco) {
        if (this.valida(endereco)) {
            em.persist(endereco);
            return endereco;
        } else {
            return null;
        }
    }

    @Override
    public Endereco retrive(Endereco endereco) {
        Endereco usuRet = em.find(Endereco.class, endereco.getId());
        return usuRet;
    }

    @Override
    public void update(Endereco endereco) {
        if (this.valida(endereco)) {
            em.merge(endereco);
        }
    }

    @Override
    public void delete(Endereco endereco) {
        Query query = em.createQuery("DELETE FROM Endereco endereco WHERE endereco.id = :enderecoId");
        query.setParameter("enderecoId", endereco.getId());
        query.executeUpdate();
    }

    @Override
    public List<Endereco> listaTodos() {
        //List<Endereco> listEnd = null;
        Query query = em.createQuery("SELECT endereco FROM Endereco endereco");
        //listEnd = query.getResultList();
        return query.getResultList();
    }
    
    @Override
    public boolean valida(Endereco endereco) {
        boolean ret = false;
        if (endereco != null) {
            ret = true;
        }
        return ret;
    }
}
