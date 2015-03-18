/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.ejb.tci.dao;

import java.util.List;
import javax.ejb.Remote;
import jpa.tci.bean.Usuario;

/**
 *
 * @author Luma Borges
 */


@Remote
public interface UsuarioDAORemote {

    Usuario create(Usuario value);

    Usuario retrive(Usuario value);

    void update(Usuario value);

    void delete(Usuario value);

    List<Usuario> listaTodos();

    boolean valida(Usuario value);

    Usuario achaLogin(String username);
}
