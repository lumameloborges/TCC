/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.ejb.tci.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.tci.bean.Conexao;
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

     public Valor buscar(Valor valor) throws SQLException {
        String sql = "SELECT * FROM valor where valorcombustivel=?";
        Valor retorno = null;

        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {

            pst.setDouble(1, valor.getValorCombustivel());
            ResultSet res = pst.executeQuery();

            if (res.next()) {
                retorno = new Valor();
                retorno.setValorCombustivel(res.getDouble("valorcombustivel"));
            }
            
            
        }
        catch (SQLException ex) {
           Logger.getLogger(ValorDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return retorno;
 
    }
}
