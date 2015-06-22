/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.ejb.tci.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            }
            
            
        }catch (SQLException ex) {
            Logger.getLogger(PostoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return retorno;
 
    }
    
    @Override
    public List<Posto> listateste() {
        String sql = "select p.nome, v.valorcombustivel, e.rua, c.tipocombustivel from posto as p inner join posto_valor as pv on p.cod=pv.posto_cod inner join valor as v on pv.valor_cod=v.cod inner join endeerco as e on p.cod=e.cod_posto inner join combustivel as c on v.tipocombustivel=c.cod";
        //Posto retorno = null;
        List<Posto> retornoPosto = new ArrayList<Posto>();
    
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet res = pst.executeQuery();

            while (res.next()) {
                Posto retorno = new Posto();
                retorno.setNome(res.getString("nome"));
                retorno.setTipoCombustivel(res.getString("tipocombustivel"));
                retorno.setRua(res.getString("rua"));
                retorno.setValor(res.getDouble("valorcombustivel"));
                
                retornoPosto.add(retorno);
            }


        }catch (SQLException ex) {
            Logger.getLogger(PostoDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

        return retornoPosto;

    }
}
