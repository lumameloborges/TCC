/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.tci.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Luma Borges
 */
@Entity
@Table(name = "Combustivel")
@NamedQuery(name = "Combustivel.findAll", query = "select o from Combustivel o order by o.cod")
@SequenceGenerator(name = "seqCombustivel", sequenceName = "SEQCOMBUSTIVEL", allocationSize = 1)

public class Combustivel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqCombustivel")
    public int cod;
    private String descricao;
    private String tipocombustivel;

    public Combustivel() {
    }

    public Combustivel(int cod, String descricao, String tipocombustivel, Valor valor) {
        this.cod = cod;
        this.descricao = descricao;
        this.tipocombustivel = tipocombustivel;
    }
    
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoCombustivel() {
        return tipocombustivel;
    }

    public void setTipoCombustivel(String tipocombustivel) {
        this.tipocombustivel = tipocombustivel;
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
        final Combustivel other = (Combustivel) obj;
        if (this.cod != other.cod) {
            return false;
        }
        return true;
    }
}
