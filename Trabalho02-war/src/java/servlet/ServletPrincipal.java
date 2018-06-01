package servlet;

import controles.AluguelControle;
import controles.LoginController;
import controles.VendaControle;
import dao.EnderecoDaoLocal;
import dao.ImovelDaoLocal;
import dao.UsuarioDaoLocal;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Marcos Pachola
 */
@WebServlet(name = "ServletPrincipal", urlPatterns = {"/ServletPrincipal"})
public class ServletPrincipal extends HttpServlet {

    @EJB
    UsuarioDaoLocal daoUsuario;
    @EJB
    ImovelDaoLocal daoImovel;
    @EJB
    EnderecoDaoLocal daoEndereco;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String acao = req.getParameter("acao");
            if ("aluguel".equals(acao.trim())) {
                AluguelControle ac = new AluguelControle(req, resp, daoImovel, daoEndereco);
                ac.processo();
            } else if ("venda".equals(acao.trim())) {
                VendaControle vc = new VendaControle(req, resp, daoImovel, daoEndereco);
                vc.processo();
            } else if ("login".equals(acao)) {
                LoginController lc = new LoginController(req, resp, daoUsuario);
                lc.processo();
            } else if ("logout".equals(acao)) {
                req.getSession().invalidate();
                RequestDispatcher dispatcher = req.getRequestDispatcher("admin.jsp");
                dispatcher.forward(req, resp);
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("pesqCliente.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (IOException | ServletException ex) {
            ServletPrincipal.dispatcherErro(req, resp, ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(ServletPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


public static void dispatcherErro(HttpServletRequest req, HttpServletResponse resp, String msg) throws ServletException, IOException {
        req.setAttribute("mensagem", msg);
        RequestDispatcher dispatcher = req.getRequestDispatcher("formerro.jsp");
        dispatcher.forward(req, resp);
    }
}
