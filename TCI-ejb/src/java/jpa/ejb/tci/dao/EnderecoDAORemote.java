/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.ejb.tci.dao;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.Remote;
import jpa.tci.bean.Endereco;

/**
 *
 * @author Luma Borges
 */

@Remote

public interface EnderecoDAORemote {

    Endereco create(Endereco value);

    Endereco retrive(Endereco value);

    void update(Endereco value);

    void delete(Endereco value);

    List<Endereco> listaTodos();

    boolean valida(Endereco value);
    
    Endereco buscar(Endereco endereco) throws SQLException;
}
