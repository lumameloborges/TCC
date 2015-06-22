/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.tci.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Luma Borges
 */
@Entity
@Table(name = "posto")
@NamedQuery(name = "Posto.findAll", query = "select o from Posto o order by o.cod")
@SequenceGenerator(name = "seqPosto", sequenceName = "SEQPOSTO", allocationSize = 1)

public class Posto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqPosto")
    private int cod;
    private String nome;
    private String tipoCombustivel;
    private double valor;
    private String rua;
    
    @OneToMany
     private List<Valor> valorList = new ArrayList<Valor>();

    public Posto() {
    }

    public Posto(int cod, String nome, String tipoCombustivel, double valor, String rua) {
        this.cod = cod;
        this.nome = nome;
        this.tipoCombustivel = tipoCombustivel;
        this.valor = valor;
        this.rua = rua;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public List<Valor> getValorList() {
        return valorList;
    }

    public void setValorList(List<Valor> valorList) {
        this.valorList = valorList;
    }

    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.cod;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Posto other = (Posto) obj;
        if (this.cod != other.cod) {
            return false;
        }
        return true;
    }
}
