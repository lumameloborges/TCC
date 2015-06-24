/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.ejb.tci.dao;

import java.util.List;
import javax.ejb.Remote;
import jpa.tci.bean.Cadastros;

/**
 *
 * @author Luma
 */
@Remote
public interface CadastroDAORemote {
    
    Cadastros create(Cadastros value);

    Cadastros retrive(Cadastros value);

    void update(Cadastros value);

    void delete(Cadastros value);

    List<Cadastros> listaTodos();

    boolean valida(Cadastros vlaue);
    
    boolean valida(String username, String passwords);

    Cadastros achaLogin(String username);
}
