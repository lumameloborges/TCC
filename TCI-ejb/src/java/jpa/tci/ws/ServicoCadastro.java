/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.tci.ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import jpa.ejb.tci.dao.CadastroDAORemote;
import jpa.tci.bean.CadastroTeste;
import jpa.tci.bean.Cadastros;

/**
 * REST Web Service
 *
 * @author sti
 */
@Stateless
@Path("cadastro")
public class ServicoCadastro {
    
    @EJB
    private CadastroDAORemote cadastroDAOR;
    private Cadastros cadastroWS;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CadastroWS
     */
    public ServicoCadastro() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{nome}/{email}/{login}/{senha}")
    public CadastroTeste testeGetXML(@PathParam("nome") String nome, 
                              @PathParam("email") String email, 
                              @PathParam("login") String login, 
                              @PathParam("senha") String senha) {
//        UsuarioWs usuariows = new UsuarioWs();
//        usuariows.setUsername("teste usuario" + usuario);
//        usuariows.setSenha("teste senha" + senha);
            CadastroTeste ct = new CadastroTeste();
        try{
            Cadastros cadastrosInsert = new Cadastros();
            cadastrosInsert.setNome(nome);
            cadastrosInsert.setEmail(email);
            cadastrosInsert.setUsuario(login);
            cadastrosInsert.setSenha(senha);

            cadastroDAOR.create(cadastrosInsert);
            
            ct.setCadastro(true);
            return ct;
        }
        catch(Exception ex){
            ct.setCadastro(false); 
            return ct;
        }
    }
}
