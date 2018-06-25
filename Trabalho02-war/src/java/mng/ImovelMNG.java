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
    private String codigo;
    @Pattern(regexp = "(.+)", message = "{invalid.imovel.descricao}")
    private String descricao;
    private Long id;
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
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
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
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setId(String codigo) {
        this.codigo = codigo;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Imovel getImovel(Long cod) {
        Imovel dept = new Imovel();
        dept.setId(cod);
        return dao.retrieve(dept);
        
    }
    
    public List<Imovel> getLista() {
        return dao.listaTodos(true, true);
    }
    
    public List<SelectItem> getListaSelectItem() {
        List<SelectItem> lista = new ArrayList<SelectItem>();
        lista.add(new SelectItem(null, ""));
        for (Imovel dept : dao.listaTodos(true, true)) {
            lista.add(new SelectItem(dept, dept.getMetragem().toString()));
        }
        return lista;
    }
    
    public void clear(AjaxBehaviorEvent event) {
        this.codigo = null;
        this.descricao = null;
        this.endereco = null;
        this.metragem = null;
        this.preco = null;        
    }
    
    public String save() {
        Imovel imv = new Imovel();
        imv.setAluguel(aluguel);
        imv.setVenda(venda);
        imv.setEndereco(endereco);
        imv.setNumGaragem(numGaragem);
        imv.setMetragem(metragem);
        imv.setNumSalas(numSalas);
        imv.setNumQuartos(numQuartos);
        imv.setPreco(preco);
        imv.setTipo(tipo);
        dao.create(imv);
        return "departamentoList";//envia pra tela imovlist
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
        this.codigo = imv.getId().toString();
        this.aluguel = imv.isAluguel();
        this.venda = imv.isVenda();
        this.endereco = imv.getEndereco();
        this.numGaragem = imv.getNumGaragem();
        this.metragem = imv.getMetragem();
        this.numSalas = imv.getNumSalas();
        this.numQuartos = imv.getNumQuartos();
        this.preco = imv.getPreco();
        this.tipo = imv.getTipo();
        return "departamentoUpdate";//envia tela departamento
    }
    
    public String update() {
        Imovel dept = new Imovel();
        dept.setId(Long.valueOf(codigo));
        dept.setAluguel(aluguel);
        dept.setEndereco(endereco);
        dept.setMetragem(metragem);
        dept.setNumGaragem(numGaragem);
        dept.setNumQuartos(numQuartos);
        dept.setNumSalas(numSalas);
        dept.setPreco(preco);
        dept.setTipo(tipo);
        dept.setVenda(venda);
        dao.update(dept);
        return "departamentoList";
    }
    
}
