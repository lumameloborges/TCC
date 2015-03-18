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
@Table(name = "posto")
@NamedQuery(name = "Posto.findAll", query = "select o from Posto o order by o.cod")
@SequenceGenerator(name = "seqPosto", sequenceName = "SEQPOSTO", allocationSize = 1)


public class Posto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqPosto")
    private int cod;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "cod_valor", referencedColumnName = "cod")
    private Valor valor;
    @ManyToOne
    @JoinColumn(name = "cod_combustivel", referencedColumnName = "cod")
    private Combustivel combustivel;
    @ManyToOne
    @JoinColumn(name = "cod_endereco", referencedColumnName = "cod")
    private Endereco endereco;

    public Posto() {
    }

    public Posto(int cod, String nome, Combustivel combustivel, Valor valor, Endereco endereco) {
        this.cod = cod;
        this.nome = nome;
        this.combustivel = combustivel;
        this.valor = valor;
        this.endereco = endereco;
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
    
    public Combustivel getCombustivel() {
    return combustivel;
    }

    public void setCombustivel(Combustivel combustivel) {
        this.combustivel = combustivel;
    }

    public Valor getValor() {
        return valor;
    }

    public void setValor(Valor valor) {
        this.valor = valor;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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
