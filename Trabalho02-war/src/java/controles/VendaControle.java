package controles;

import bean.Endereco;
import bean.Imovel;
import dao.EnderecoDaoLocal;
import dao.ImovelDaoLocal;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.ServletPrincipal;

/**
 *
 * @author Marcos Pachola
 */
public class VendaControle {

    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final ImovelDaoLocal daoImovel;
    private final EnderecoDaoLocal daoEndereco;

    public VendaControle(HttpServletRequest req, HttpServletResponse resp, ImovelDaoLocal daoImovel, EnderecoDaoLocal daoEndereco) {
        this.req = req;
        this.resp = resp;
        this.daoImovel = daoImovel;
        this.daoEndereco = daoEndereco;
    }

    public void processo() throws ServletException, IOException, SQLException {
        String acaoCRUD = req.getParameter("acaoCRUD");

        if ("salvar".equals(acaoCRUD)) {
            this.salvar();
        } else if ("carregar".equals(acaoCRUD)) {
            this.carregar();
        } else if ("excluir".equals(acaoCRUD)) {
            this.excluir();
        }

        listaTodos();
        req.setAttribute("cadastro", "venda");
        RequestDispatcher dispatcher = req.getRequestDispatcher("formCadastro.jsp");
        dispatcher.forward(req, resp);
    }

    private void salvar() {
        String idImovel = req.getParameter("txtCodigo");
        String numQuartos = req.getParameter("txtNumQuartos");
        String numSalas = req.getParameter("txtNumSalas");
        String numGaragem = req.getParameter("txtNumGaragem");
        String metragem = req.getParameter("txtTamanho");
        String codendereco = req.getParameter("txtCodEndereco");
        String tipo = req.getParameter("cmbTipoImovel");
        String preco = req.getParameter("txtPreco");

        Imovel imovel = new Imovel();
        imovel.setAluguel(false);
        imovel.setVenda(true);
        imovel.setNumQuartos(Integer.parseInt(numQuartos));
        imovel.setNumSalas(Integer.parseInt(numSalas));
        imovel.setNumGaragem(Integer.parseInt(numGaragem));
        imovel.setMetragem(new BigDecimal(metragem));
        imovel.setPreco(new BigDecimal(preco));
       // imovel.setTipo(Integer.parseInt(tipo));
        boolean atualizar = false;
        if (!idImovel.equals("")) {
            atualizar = true;
            imovel.setId(Long.parseLong(idImovel));
            daoImovel.retrieve(imovel);
        }
        Endereco endereco = new Endereco();
        endereco.setId(Long.parseLong(codendereco));
        endereco = daoEndereco.retrive(endereco);
        if (endereco != null) {
            imovel.setEndereco(endereco);
            if (atualizar) {
                daoImovel.update(imovel);
            } else {
                daoImovel.create(imovel);
            }
        } else {
            req.setAttribute("EndNulo", true);
        }

    }

    private void carregar() {
        Long idImovel = Long.parseLong(req.getParameter("txtCodigo"));
        Imovel imovel = new Imovel();
        imovel.setId(idImovel);
        req.setAttribute("imovel", daoImovel.retrieve(imovel));
    }

    private void excluir() throws ServletException, IOException {
        Long idImovel = Long.parseLong(req.getParameter("txtCodigo"));
        Imovel imovel = new Imovel();
        imovel.setId(idImovel);
        daoImovel.delete(imovel);
    }

    private void listaTodos() throws ServletException, IOException, SQLException {
        req.setAttribute("ListaImovel", daoImovel.listaTodos(false, true));
    }
}
