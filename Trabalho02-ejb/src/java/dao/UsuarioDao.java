package dao;

import static bean.Imovel_.endereco;
import bean.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Marcos Pachola
 */
@Stateless
public class UsuarioDao implements UsuarioDaoLocal {

    @PersistenceContext
    private EntityManager em;
    //Crud no banco 
    //create = persist
    //delete = remove
    //retrive = find
    //update = merge

    @Override
    public Usuario create(Usuario usuario) {
        if (this.valida(usuario)) {
            em.persist(usuario);
            return usuario;
        } else {
            return null;
        }
    }

    @Override
    public Usuario retrive(Usuario usuario) {
        Usuario usuRet = em.find(Usuario.class, usuario.getId());
        return usuRet;
    }

    @Override
    public Usuario retrieveLogin(String login, String senha) {
        Usuario user = null;
        if (!login.equals("") || !senha.equals("")) {   //senha = 123 login = pachola
            Query query = em.createQuery("SELECT usuario FROM Usuario usuario where usuario.login = :loginUsuario and usuario.senha = :senhaUsuario");
            //Buscar um usuario com id = 5
            //SELECT usuario FROM Usuario usuario where usuario.id = 5
            //Buscar cachorros raça FILA
            //Query query = em.createQuery("Select cachorro from Cachorro cachorro where cachorro.raca = :NOMEPARAMETRO");
            //query.setParameter("NOMEPARAMETRO", "Fila");

            query.setParameter("loginUsuario", login);
            query.setParameter("senhaUsuario", senha);
            try {
                user = (Usuario) query.getSingleResult();
            } catch (Exception e) {
                System.out.println("Não foi válido o Login");
            }
        }
        return user;
    }

       @Override
    public Usuario findByLogin(String login) {
        Query query = em.createNamedQuery("Usuario.findLogin");
        try {
            Usuario usuario = (Usuario) query.setParameter("login", login).getSingleResult();
            return usuario;
        } catch (NoResultException nre) {
            return null;
        }
    }
    
    
    @Override
    public void update(Usuario usuario) {
        if (this.valida(usuario)) {
            em.merge(usuario);
        }
    }
    
   
    @Override
    public void delete(Usuario usuario){
        em.remove(usuario);
    }
   
    public List<Usuario> listaTodos(){
        return(List<Usuario>) em.createNamedQuery("Usuario.findAll").getResultList();
    }
   
    @Override
        public boolean valida(Usuario usuario) {
        boolean ret = false;
        if (endereco != null) {
            ret = true;
        }
        return ret;
    }
}
