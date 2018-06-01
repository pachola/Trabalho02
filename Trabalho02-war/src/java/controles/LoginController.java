package controles;

import bean.Usuario;
import dao.UsuarioDaoLocal;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.ServletPrincipal;

/**
 *
 * @author Marcos Pachola
 */
public class LoginController {

    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final  UsuarioDaoLocal daoUsuario;


    public LoginController(HttpServletRequest req, HttpServletResponse resp, UsuarioDaoLocal daoUsuario) {
        this.req = req;
        this.resp = resp;
        this.daoUsuario = daoUsuario;

    }

    public void processo() throws ServletException, IOException, SQLException {
        String login = req.getParameter("txtUsuario");
        String senha = req.getParameter("txtSenha");
        Usuario usuario = daoUsuario.retrieveLogin(login, senha); 
        if (usuario == null) {
            ServletPrincipal.dispatcherErro(req, resp, String.format("Usuário ou Senha Inválidos.[%s]", login));
        } else {
            req.getSession().setAttribute("UsuarioLogado", Boolean.TRUE);
            RequestDispatcher dispatcher = req.getRequestDispatcher("formCadastro.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
