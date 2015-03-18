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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Luma Borges
 */

@Entity
@Table(name = "Endeerco")
@NamedQuery(name = "Endereco.findAll", query = "select o from Endereco o order by o.cod")
@SequenceGenerator(name = "seqEdereco", sequenceName = "SEQENDERECO", allocationSize = 1)


public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqEndereco")
    private int cod;
    private String bairro, rua;
    @ManyToOne
    @JoinColumn(name = "cod_posto", referencedColumnName = "cod")
    private Posto posto;


    public Endereco() {
    }

    public Endereco(int cod, String bairro, String rua, Posto posto) {
        this.cod = cod;
        this.bairro = bairro;
        this.rua= rua;
        this.posto = posto;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
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

    public Posto getPosto() {
        return posto;
    }

    public void setPosto(Posto posto) {
        this.posto = posto;
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
        final Endereco other = (Endereco) obj;
        if (this.cod != other.cod) {
            return false;
        }
        return true;
    }
}

