/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.tci.ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import jpa.ejb.tci.dao.CadastroDAORemote;
import jpa.ejb.tci.dao.UsuarioDAORemote;
import jpa.tci.bean.Cadastro;

/**
 *
 * @author User
 */
@Stateless
@Path(value = "/servicoCadastro")
public class ServicoCadastro {

    @EJB
    private UsuarioDAORemote usuarioDAO;
    @EJB
    private CadastroDAORemote cadastroDAO;
    
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public UserWS testeGetText() {
//        UserWS user = new UserWS();
//        user.setLogado(true);
//        return user;
//    }
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/{usuario}/{senha}")
//    public UserWS testeGetXML(@PathParam("usuario") String usuario, @PathParam("senha") String senha) {
////        UsuarioWs usuariows = new UsuarioWs();
////        usuariows.setUsername("teste usuario" + usuario);
////        usuariows.setSenha("teste senha" + senha);
//        UserWS user = new UserWS();
//        user.setLogado(usuarioDAO.valida(usuario, senha));
//        return user;
//
//    }
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/teste/{usuario}")
//    public UserWS testeGetXML(@PathParam("usuario") String usuario) {
//        UserWS user = new UserWS();
//        user.setLogado(usuarioDAO.valida(usuario, "101010"));
//        return user;
//    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cadastro createDepartamento(Cadastro cadastro) {
        return cadastroDAO.create(cadastro);
//        return "Departamento incluido";
    }

    
    
}