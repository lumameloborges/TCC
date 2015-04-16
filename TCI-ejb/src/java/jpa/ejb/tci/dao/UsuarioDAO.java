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
import jpa.tci.bean.Usuario;

/**
 *
 * @author Luma Borges
 */
@Stateless
@LocalBean
public class UsuarioDAO implements UsuarioDAORemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Usuario create(Usuario value) {
        if (this.valida(value)) {
            em.persist(value);
            return value;
        } else {
            return null;
        }
    }

    @Override
    public Usuario retrive(Usuario value) {
        Usuario valueRet = em.find(Usuario.class, value.getCod());
        return valueRet;
    }

    @Override
    public void update(Usuario value) {
        if (this.valida(value)) {
            em.merge(value);
        }
    }

    @Override
    public void delete(Usuario value) {
        value = this.retrive(value);
        em.remove(value);
    }

    @Override
    public List<Usuario> listaTodos() {
        return (List<Usuario>) em.createNamedQuery("Usuario.findAll").getResultList();
    }

    @Override
    public boolean valida(Usuario value) {
        boolean ret = false;
        if (value != null) {
            ret = true;
        }
        return ret;
    }
    
    

    @Override
    public Usuario achaLogin(String username) {
        Query query = em.createQuery("select o from Usuario o where o.username = :username");
        try {
            Usuario value = (Usuario) query.setParameter("username", username).getSingleResult();
            return value;
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public boolean valida(String username, String passwords) {
        Usuario usuario = this.achaLogin(username);
        if (usuario!=null){
            return usuario.validasenha(passwords);
        }else{
            return false;
        }
    }
}
