/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.ejb.tci.dao;

import java.util.List;
import jpa.tci.bean.Cadastro;

/**
 *
 * @author User
 */
interface CadastroDAORemote {
    Cadastro create(Cadastro value);

    Cadastro retrive(Cadastro value);

    void update(Cadastro value);

    void delete(Cadastro value);

    List<Cadastro> listaTodos();

    boolean valida(Cadastro value);

    boolean valida(String username, String passwords);

    Cadastro achaLogin(String username);

}
