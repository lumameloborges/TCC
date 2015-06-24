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
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Daniel
 */

@Entity
@Table(name = "Cadastros", uniqueConstraints = {
@UniqueConstraint(columnNames = {"usuario"})})
@NamedQuery(name = "Cadastros.findAll", query = "select o from Cadastros o")
@SequenceGenerator(name = "seqCadastros", sequenceName = "SEQCADASTROS", allocationSize = 1)
public class Cadastros implements Serializable { 
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqCadastros")
    private int id;
    private String nome;
    private String email;
    private String usuario;
    private String senha;

    public Cadastros() {
    }

    public Cadastros(int id, String nome, String email, String usuario, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.usuario = usuario;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public boolean validasenha(String senha) {
        boolean verf = false;
        try {
            if (this.senha.equals(senha)) {
                verf = true;
            }
        } catch (NullPointerException ex) {
        }
        return verf;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id;
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
        final Cadastros other = (Cadastros) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}