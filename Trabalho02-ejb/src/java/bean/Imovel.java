/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Marcos Pachola
 */
@Entity
public class Imovel implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean aluguel;
    private boolean venda;
    private int numQuartos;
    private int numSalas;
    private int numGaragem;
    private String tipo;   //Apartamento, casa, casa-préfabricada, salao comercial
    private BigDecimal metragem;
    private BigDecimal preco;
    @OneToOne
    private Endereco endereco;
   
    
    //imovel.setaluguel(true);
    //imovel.setnumQuartos(2);
    //imovel.setEndereco(daoImovel.get..............
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
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imovel)) {
            return false;
        }
        Imovel other = (Imovel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Imovel[ id=" + id + " ]";
    }
    
}
