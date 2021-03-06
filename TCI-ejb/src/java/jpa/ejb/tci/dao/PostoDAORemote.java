/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.ejb.tci.dao;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.Remote;
import jpa.tci.bean.Posto;

/**
 *
 * @author Luma Borges
 */

@Remote
public interface PostoDAORemote {

    Posto create(Posto value);

    Posto retrive(Posto value);

    void update(Posto value);

    void delete(Posto value);

    List<Posto> listaTodos();
    
    List<Posto> listateste();

    boolean valida(Posto value);
    
    Posto buscar(Posto posto) throws SQLException ;
}
