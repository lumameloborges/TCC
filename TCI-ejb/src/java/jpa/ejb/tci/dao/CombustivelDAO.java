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
//import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.tci.bean.Combustivel;
import jpa.tci.bean.Conexao;

/**
 *
 * @author Luma Borges
 */
@Stateless

public class CombustivelDAO implements CombustivelDAORemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Combustivel create(Combustivel value) {
        if (this.valida(value)) {
            em.persist(value);
            return value;
        } else {
            return null;
        }
    }

    @Override
    public Combustivel retrive(Combustivel value) {
        Combustivel valueRet = em.find(Combustivel.class, value.getCod());
        return valueRet;
    }

    @Override
    public void update(Combustivel value) {
        if (this.valida(value)) {
            em.merge(value);
        }
    }

    @Override
    public void delete(Combustivel value) {
        value = this.retrive(value);
        em.remove(value);
    }

    @Override
    public List<Combustivel> listaTodos() {
        return (List<Combustivel>) em.createNamedQuery("Combustivel.findAll").getResultList();
    }

    @Override
    public boolean valida(Combustivel value) {
        boolean ret = false;
        if (value != null) {
            ret = true;
        }
        return ret;
    }

    @Override
    public Combustivel buscar(Combustivel combustivel) throws SQLException {

        String sql = "SELECT * FROM combustivel where tipocombustivel=?";
        Combustivel retorno = null;

        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {

            pst.setString(1, combustivel.getTipoCombustivel());
            ResultSet res = pst.executeQuery();

            if (res.next()) {
                retorno = new Combustivel();
                retorno.setCod(Integer.parseInt(res.getString("cod")));
                retorno.setTipoCombustivel(res.getString("tipocombustivel"));
//                retorno.setSenha(res.getString("senha"));
//                retorno.setEmail(res.getString("email"));
//                retorno.setPerfil(res.getString("perfil"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(PostoDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

        return retorno;

    }
}
