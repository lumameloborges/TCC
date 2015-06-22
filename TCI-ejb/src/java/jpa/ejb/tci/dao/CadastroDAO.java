/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.ejb.tci.dao;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import jpa.tci.bean.Cadastro;
import jpa.tci.bean.Usuario;

/**
 *
 * @author User
 */
@Stateless
@LocalBean
public class CadastroDAO implements CadastroDAORemote{
    @PersistenceContext
    private EntityManager em;


    @Override
    public boolean valida(String username, String passwords) {
        Cadastro cadastro = this.achaLogin(username);
        if (cadastro!=null){
            return cadastro.validasenha(passwords);
        }else{
            return false;
        }
    }

    @Override
    public Cadastro create(Cadastro value) {
           if (this.valida(value)) {
            em.persist(value);
            return value;
        } else {
            return null;
        }
    }

    @Override
    public Cadastro retrive(Cadastro value) {
         Cadastro valueRet = em.find(Cadastro.class, value.getCod());
        return valueRet;
    }

    @Override
    public void update(Cadastro value) {
         if (this.valida(value)) {
            em.merge(value);
        }
    }

    @Override
    public void delete(Cadastro value) {
        value = this.retrive(value);
        em.remove(value);
    }

    @Override
    public List<Cadastro> listaTodos() {
        return (List<Cadastro>) em.createNamedQuery("Cadastro.findAll").getResultList();
    }

    @Override
    public boolean valida(Cadastro value) {
        boolean ret = false;
        if (value != null) {
            ret = true;
        }
        return ret;
    }

    @Override
    public Cadastro achaLogin(String username) {
        Query query = em.createQuery("select o from Cadastro o where o.username = :username");
        try {
            Cadastro value = (Cadastro) query.setParameter("username", username).getSingleResult();
            return value;
        } catch (NoResultException nre) {
            return null;
        }
    }
}
