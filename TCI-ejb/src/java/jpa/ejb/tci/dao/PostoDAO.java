/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.ejb.tci.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.tci.bean.Posto;

/**
 *
 * @author Luma Borges
 */


@Stateless

public class PostoDAO implements PostoDAORemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Posto create(Posto value) {
        if (this.valida(value)) {
            em.persist(value);
            return value;
        } else {
            return null;
        }
    }

    @Override
    public Posto retrive(Posto value) {
        Posto valueRet = em.find(Posto.class, value.getCod());
        return valueRet;
    }

    @Override
    public void update(Posto value) {
        if (this.valida(value)) {
            em.merge(value);
        }
    }

    @Override
    public void delete(Posto value) {
        value = this.retrive(value);
        em.remove(value);
    }

    @Override
    public List<Posto> listaTodos() {
        return (List<Posto>) em.createNamedQuery("Posto.findAll").getResultList();
    }

    @Override
    public boolean valida(Posto value) {
        boolean ret = false;
        if (value != null) {
            ret = true;
        }
        return ret;
    }
}
