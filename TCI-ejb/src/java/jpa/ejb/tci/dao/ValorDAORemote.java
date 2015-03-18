/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.ejb.tci.dao;

import java.util.List;
import javax.ejb.Remote;
import jpa.tci.bean.Valor;

/**
 *
 * @author Luma Borges
 */

@Remote
public interface ValorDAORemote {

    Valor create(Valor value);

    Valor retrive(Valor value);

    void update(Valor value);

    void delete(Valor value);

    List<Valor> listaTodos();

    boolean valida(Valor vlaue);
}
