package dao;

import bean.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Marcos Pachola
 */
@Local
public interface UsuarioDaoLocal {

public Usuario retrive(Usuario usuario);

public Usuario create(Usuario usuario);

public Usuario retrieveLogin(String login, String senha);

public void update(Usuario usuario);

public void delete(Usuario usuario);

public List<Usuario> listaTodos();

public Usuario findByLogin(String login);

public boolean valida(Usuario usuario);

}
