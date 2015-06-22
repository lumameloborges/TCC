/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.tci.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Luma Borges
 */
@Entity
@Table(name = "valor")
@NamedQuery(name = "Valor.findAll", query = "select o from Valor o order by o.cod")
@SequenceGenerator(name = "seqValor", sequenceName = "SEQVALOR", allocationSize = 1)

public class Valor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqValor")
    private int cod;
    private double valorcombustivel;
    @Temporal(TemporalType.DATE)
    private Date data;
    @ManyToOne
    @JoinColumn(name = "tipocombustivel", referencedColumnName = "cod")
    private Combustivel combustivel;


    public Valor() {
    }

    public Valor(int cod, Double valorcombustivel, Date data, Combustivel combustivel) {
        this.cod = cod;
        this.valorcombustivel = valorcombustivel;
        this.data = data;
        this.combustivel = combustivel;
    }

    public Combustivel getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(Combustivel combustivel) {
        this.combustivel = combustivel;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Double getValorCombustivel() {
        return valorcombustivel;
    }

    public void setValorCombustivel(Double valorcombustivel) {
        this.valorcombustivel = valorcombustivel;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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
        final Valor other = (Valor) obj;
        if (this.cod != other.cod) {
            return false;
        }
        return true;
    }
}
