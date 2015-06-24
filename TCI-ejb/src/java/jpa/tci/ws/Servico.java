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
import jpa.ejb.tci.dao.CadastroDAORemote;
import jpa.ejb.tci.dao.UsuarioDAORemote;

/**
 *
 * @author aluno
 */
@Stateless
@Path(value = "/servico")
public class Servico {

    @EJB
    private UsuarioDAORemote usuarioDAO;
    private CadastroDAORemote cadastroDAO;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserWS testeGetText() {
        UserWS user = new UserWS();
        user.setLogado(true);
        return user;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{usuario}/{senha}")
    public UserWS testeGetXML(@PathParam("usuario") String usuario, @PathParam("senha") String senha) {
//        UsuarioWs usuariows = new UsuarioWs();
//        usuariows.setUsername("teste usuario" + usuario);
//        usuariows.setSenha("teste senha" + senha);
        UserWS user = new UserWS();
        user.setLogado(usuarioDAO.valida(usuario, senha));
        return user;

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/teste/{usuario}")
    public UserWS testeGetXML(@PathParam("usuario") String usuario) {
        UserWS user = new UserWS();
        user.setLogado(usuarioDAO.valida(usuario, "101010"));
        return user;
    }
    
    
}
