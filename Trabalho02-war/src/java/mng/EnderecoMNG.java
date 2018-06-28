/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mng;

import bean.Endereco;
import dao.EnderecoDaoLocal;
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
@Named(value = "enderecoMNG")
@RequestScoped
public class EnderecoMNG {

    @EJB
    EnderecoDaoLocal dao;
    //CODIGO OU ID OU OS DOIS...
    
    @Pattern(regexp = "(.+)", message = "{invalid.endereco.descricao}")
    private String cod;
    private String descricao;
    private Long id;
    private int numero;
    private String cep;
    private String cidade;
    private String bairro;
    private String rua; //logradouro

    public EnderecoMNG() {
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public EnderecoDaoLocal getDao() {
        return dao;
    }

    public void setDao(EnderecoDaoLocal dao) {
        this.dao = dao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Endereco getEndereco(long id) {
        Endereco end = new Endereco();
        end.setId(id);
        return dao.retrive(end);
    }

    public List<Endereco> getLista() {
        return dao.listaTodos();
    }

    public List<SelectItem> getListaSelectItem() {
        List<SelectItem> lista = new ArrayList<SelectItem>();
        lista.add(new SelectItem(null, ""));
        for (Endereco end : dao.listaTodos()) {
            lista.add(new SelectItem(end, end.getDescricao()));
        }
        return lista;
    }

    public void clear(AjaxBehaviorEvent event) {
        this.id = null;
        this.descricao = null;
        this.rua = null;
        this.numero = 0;
        this.bairro = null;
        this.cidade = null;
        this.cep = null;

    }

    public void remove() {
        Long index = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codExcluir").toString());
        Endereco end = new Endereco();
        end.setId(index);
        dao.delete(end);
        this.clear(null);
    }

    public String save() {
        Endereco end = new Endereco();
        end.setId(id);
        end.setDescricao(descricao);
        end.setRua(rua);
        end.setNumero(numero);
        end.setBairro(bairro);
        end.setCidade(cidade);
        end.setCep(cep);
        dao.create(end);
        return "enderecoList";//envia pra tela endList
    }

    public String prepUpdate() {
        Long index = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar").toString());
        System.out.println("alterar" + index);
        Endereco end = new Endereco();
        end.setId(index);
        end = dao.retrive(end);
        this.id = end.getId();
        this.descricao = end.getDescricao();
        this.bairro = end.getBairro();
        this.cep = end.getCep();
        this.cidade = end.getCidade();
        this.numero = end.getNumero();
        this.rua = end.getRua();
        return "enderecoUpdate";
    }

    public String update() {
        Endereco end = new Endereco();
        end.setId(Long.valueOf(id));
        end.setDescricao(descricao);
        end.setBairro(bairro);
        end.setCep(cep);
        end.setCidade(cidade);
        end.setNumero(numero);
        end.setRua(rua);
        dao.update(end);
        return "enderecoList";
    }
}
