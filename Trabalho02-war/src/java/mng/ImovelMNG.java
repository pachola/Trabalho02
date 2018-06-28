/*NAO TERMINADO
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mng;

import bean.Endereco;
import bean.Imovel;
import dao.ImovelDaoLocal;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Marcos Pachola
 */
@Named(value = "imovelMNG")
@RequestScoped
public class ImovelMNG {

    @EJB
    ImovelDaoLocal dao;
    private String id;
    @Pattern(regexp = "(.+)", message = "{invalid.imovel.descricao}")
    private String descricao;
    private boolean aluguel;
    private boolean venda;
    private int numQuartos;
    private int numSalas;
    private int numGaragem;
    private String tipo;   //Apartamento, casa, casa-préfabricada, salao comercial //tava int
    private BigDecimal metragem;
    private BigDecimal preco;
    private Endereco endereco;

    public ImovelMNG() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAluguel() {
        return aluguel;
    }

    public void setAluguel(boolean aluguel) {
        this.aluguel = aluguel;
    }

    public boolean isVenda() {
        return venda;
    }

    public void setVenda(boolean venda) {
        this.venda = venda;
    }

    public int getNumQuartos() {
        return numQuartos;
    }

    public void setNumQuartos(int numQuartos) {
        this.numQuartos = numQuartos;
    }

    public int getNumSalas() {
        return numSalas;
    }

    public void setNumSalas(int numSalas) {
        this.numSalas = numSalas;
    }

    public int getNumGaragem() {
        return numGaragem;
    }

    public void setNumGaragem(int numGaragem) {
        this.numGaragem = numGaragem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getMetragem() {
        return metragem;
    }

    public void setMetragem(BigDecimal metragem) {
        this.metragem = metragem;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Imovel getImovel(Long cod) {
        Imovel imv = new Imovel();
        imv.setId(cod);
        return dao.retrieve(imv);

    }

    public List<Imovel> getLista() {
        return dao.listaTodos2();
    }


    public void clear(AjaxBehaviorEvent event) {
        this.id = null;
        this.descricao = null;
        this.endereco = null;
        this.metragem = null;
        this.preco = null;
    }

    public String save() {
        Imovel imv = new Imovel();
        imv.setAluguel(aluguel);
        imv.setVenda(venda);
        imv.setNumGaragem(numGaragem);
        imv.setMetragem(metragem);
        imv.setNumSalas(numSalas);
        imv.setNumQuartos(numQuartos);
        imv.setPreco(preco);
        imv.setTipo(tipo);
        imv.setEndereco(endereco);
        dao.create(imv);
        return "imovelList";//envia pra tela imovlist
    }

    public void remove() {
        Long index = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codExcluir").toString());
        Imovel dept = new Imovel();
        dept.setId(index);
        dao.delete(dept);
        this.clear(null);
    }

    public String prepUpdate() {
        Long index = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar").toString());
        System.out.println("alterar" + index);
        Imovel imv = new Imovel();
        imv.setId(index);
        imv = dao.retrieve(imv);
        this.id = imv.getId().toString();
        this.aluguel = imv.isAluguel();
        this.venda = imv.isVenda();
        this.numGaragem = imv.getNumGaragem();
        this.metragem = imv.getMetragem();
        this.numSalas = imv.getNumSalas();
        this.numQuartos = imv.getNumQuartos();
        this.preco = imv.getPreco();
        this.tipo = imv.getTipo();
        this.endereco = imv.getEndereco();
        return "imovelUpdate";//envia tela departamento
    }

    public String update() {
        Imovel imv = new Imovel();
        imv.setId(Long.valueOf(id));
        imv.setAluguel(aluguel);
        imv.setVenda(venda);
        imv.setMetragem(metragem);
        imv.setNumGaragem(numGaragem);
        imv.setNumQuartos(numQuartos);
        imv.setNumSalas(numSalas);
        imv.setPreco(preco);
        imv.setTipo(tipo);
        imv.setEndereco(endereco);
        dao.update(imv);
        return "imovelList";
    }

}
