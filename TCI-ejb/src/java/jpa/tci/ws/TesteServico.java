/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.tci.ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import jpa.ejb.tci.dao.UsuarioDAORemote;

/**
 *
 * @author aluno
 */

@Stateless
@Path(value = "/testeservico")
public class TesteServico {

    @EJB
    private UsuarioDAORemote usuarioDAO;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testeGetText() {
        return "teste do get TEXTO";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{usuario}/{senha}")
    public Boolean testeGetXML(@PathParam("usuario") String usuario, @PathParam("senha") String senha) {
//        UsuarioWs usuariows = new UsuarioWs();
//        usuariows.setUsername("teste usuario" + usuario);
//        usuariows.setSenha("teste senha" + senha);

        return usuarioDAO.valida(usuario, senha);

    }
}
