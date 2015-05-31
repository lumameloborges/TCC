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
import jpa.tci.bean.Endereco;

/**
 *
 * @author Luma Borges
 */

@Stateless

public class EnderecoDAO implements EnderecoDAORemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Endereco create(Endereco value) {
        if (this.valida(value)) {
            em.persist(value);
            return value;
        } else {
            return null;
        }
    }

    @Override
    public Endereco retrive(Endereco value) {
        Endereco valueRet = em.find(Endereco.class, value.getCod());
        return valueRet;
    }

    @Override
    public void update(Endereco value) {
        if (this.valida(value)) {
            em.merge(value);
        }
    }

    @Override
    public void delete(Endereco value) {
        value = this.retrive(value);
        em.remove(value);
    }

    @Override
    public List<Endereco> listaTodos() {
        return (List<Endereco>) em.createNamedQuery("Endereco.findAll").getResultList();
    }

    @Override
    public boolean valida(Endereco value) {
        boolean ret = false;
        if (value != null) {
            ret = true;
        }
        return ret;
    }
    
    @Override
    public Endereco buscar(Endereco endereco) throws SQLException {

        String sql = "SELECT * FROM endeerco where rua=?";
        Endereco retorno = null;

        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {

            pst.setString(1, endereco.getRua());
            ResultSet res = pst.executeQuery();

            if (res.next()) {
                retorno = new Endereco();
                retorno.setCod(Integer.parseInt(res.getString("cod")));
                retorno.setRua(res.getString("rua"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PostoDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

        return retorno;

    }

}
