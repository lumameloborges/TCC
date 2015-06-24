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
import jpa.tci.bean.Cadastros;
import jpa.tci.bean.Usuario;

/**
 *
 * @author Luma
 */
@Stateless
@LocalBean
public class CadastroDAO implements CadastroDAORemote {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public Cadastros create(Cadastros value) {
        if (this.valida(value)) {
            em.persist(value);
            return value;
        } else {
            return null;
        }
    }

    @Override
    public Cadastros retrive(Cadastros value) {
        Cadastros valueRet = em.find(Cadastros.class, value.getId());
        return valueRet;
    }

    @Override
    public void update(Cadastros value) {
        if (this.valida(value)) {
            em.merge(value);
        }
    }

    @Override
    public void delete(Cadastros value) {
         value = this.retrive(value);
        em.remove(value);
    }

    @Override
    public List<Cadastros> listaTodos() {
        return (List<Cadastros>) em.createNamedQuery("Cadastros.findAll").getResultList();
    }

    @Override
    public boolean valida(Cadastros value) {
         boolean ret = false;
        if (value != null) {
            ret = true;
        }
        return ret;
    }
    
    @Override
    public Cadastros achaLogin(String usuario) {
        Query query = em.createQuery("select o from Cadastros o where o.usuario = :usuario");
        try {
            Cadastros value = (Cadastros) query.setParameter("usuario", usuario).getSingleResult();
            return value;
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public boolean valida(String usuario, String senha) {
        Cadastros cadastros = this.achaLogin(usuario);
        if (cadastros!=null){
            return cadastros.validasenha(senha);
        }else{
            return false;
        }
    }
}
