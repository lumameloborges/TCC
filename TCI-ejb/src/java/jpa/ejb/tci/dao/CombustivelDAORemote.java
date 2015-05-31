/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.ejb.tci.dao;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.Remote;
import jpa.tci.bean.Combustivel;

/**
 *
 * @author Luma Borges
 */
@Remote

public interface CombustivelDAORemote {

    Combustivel create(Combustivel value);

    Combustivel retrive(Combustivel value);

    void update(Combustivel value);

    void delete(Combustivel value);

    List<Combustivel> listaTodos();

    boolean valida(Combustivel value);

    Combustivel buscar(Combustivel combustivel) throws SQLException;
}
