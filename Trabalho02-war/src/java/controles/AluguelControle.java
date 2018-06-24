package controles;

import bean.Endereco;
import dao.EnderecoDaoLocal;
import bean.Imovel;
import dao.ImovelDao;
import dao.ImovelDaoLocal;
import java.io.IOException;
import java.math.BigDecimal;
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
public class AluguelControle {

    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final ImovelDaoLocal daoImovel;
    private final EnderecoDaoLocal daoEndereco;

    public AluguelControle(HttpServletRequest req, HttpServletResponse resp, ImovelDaoLocal daoImovel, EnderecoDaoLocal daoEndereco) {
        this.req = req;
        this.resp = resp;
        this.daoImovel = daoImovel;
        this.daoEndereco = daoEndereco;
        // this.conn = conn;
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
        req.setAttribute("cadastro", "aluguel");
        RequestDispatcher dispatcher = req.getRequestDispatcher("formCadastro.jsp");
        dispatcher.forward(req, resp);
    }

    private void salvar() throws ServletException, IOException {
        String idImovel = req.getParameter("txtCodigo"); //pega uma informação da tela jsp e joga pra dentro de uma string
        String numQuartos = req.getParameter("txtNumQuartos");
        String numSalas = req.getParameter("txtNumSalas");
        String numGaragem = req.getParameter("txtNumGaragem");
        String metragem = req.getParameter("txtTamanho");
        String codendereco = req.getParameter("txtCodEndereco");
        String tipo = req.getParameter("cmbTipoImovel");
        String preco = req.getParameter("txtPreco");
        Imovel imovel = new Imovel();
        imovel.setAluguel(true);
        imovel.setVenda(false);
        imovel.setNumQuartos(Integer.parseInt(numQuartos));
        imovel.setNumSalas(Integer.parseInt(numSalas));
        imovel.setNumGaragem(Integer.parseInt(numGaragem));
        imovel.setMetragem(new BigDecimal(metragem));
        imovel.setPreco(new BigDecimal(preco));
        imovel.setTipo(Integer.parseInt(tipo));
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
        }else{
            req.setAttribute("EndNulo", true);
        }
       
    }

    private void carregar() throws ServletException, IOException {
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
        req.setAttribute("ListaImovel", daoImovel.listaTodos(true, false));
    }
}
