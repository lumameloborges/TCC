/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.webservice;

import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import tcc.bean.UsuarioWs;

/**
 *
 * @author aluno
 */
public class TesteServico {
     @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("{usuario/senha}")
    public UsuarioWs testeGetXML(@PathParam("usuario") String usuario, @PathParam("senha") String senha) {
        UsuarioWs usuariows = new UsuarioWs();
        usuariows.setUsername("teste usuario" + usuario);
        usuariows.setSenha("teste senha" + senha);
        return usuariows;
    }
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean testPostXMLRetPlain(UsuarioWs usuariows) {
        System.out.println(usuariows);
        return true;   
        }  
}
