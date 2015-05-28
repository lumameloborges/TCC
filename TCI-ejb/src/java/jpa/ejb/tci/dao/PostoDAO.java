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
    
    public Posto buscar(Posto posto) throws SQLException {
        String sql = "SELECT * FROM posto where nome=?";
        Posto retorno = null;

        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {

            pst.setString(1, posto.getNome());
            ResultSet res = pst.executeQuery();

            if (res.next()) {
                retorno = new Posto();
                retorno.setNome(res.getString("nome"));
//                retorno.setSenha(res.getString("senha"));
//                retorno.setEmail(res.getString("email"));
//                retorno.setPerfil(res.getString("perfil"));

            }
            
            
        }catch (SQLException ex) {
            Logger.getLogger(PostoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return retorno;
 
    }
}
