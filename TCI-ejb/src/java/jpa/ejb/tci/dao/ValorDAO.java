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
import jpa.tci.bean.Valor;

/**
 *
 * @author Luma Borges
 */

@Stateless
public class ValorDAO implements ValorDAORemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Valor create(Valor value) {
        if (this.valida(value)) {
            em.persist(value);
            return value;
        } else {
            return null;
        }
    }

    @Override
    public Valor retrive(Valor value) {
        Valor valueRet = em.find(Valor.class, value.getCod());
        return valueRet;
    }

    @Override
    public void update(Valor value) {
        if (this.valida(value)) {
            em.merge(value);
        }
    }

    @Override
    public void delete(Valor value) {
        value = this.retrive(value);
        em.remove(value);
    }

    @Override
    public List<Valor> listaTodos() {
        return (List<Valor>) em.createNamedQuery("Valor.findAll").getResultList();
    }

    @Override
    public boolean valida(Valor value) {
        boolean ret = false;
        if (value != null) {
            ret = true;
        }
        return ret;
    }
}
