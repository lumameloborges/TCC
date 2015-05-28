/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.tci.ws;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import jpa.ejb.tci.dao.PostoDAORemote;
import jpa.tci.bean.Posto;

/**
 *
 * @author User
 */
@Stateless
@Path(value = "/testeservicoposto")
public class TesteServicoPosto {
    @EJB
    private PostoDAORemote postoDAO;
    
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String testeGetText() {
//        return "teste do get TEXTO";
//    }
    
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Post testeGetText(String nome) {
//        Post post = new Post();
////    @Produces(MediaType.APPLICATION_JSON)
////    @Path("/{nome}")
////    public User testeGetXML(@PathParam("nome") String nome) {
//        post.setNome(nome);
//        return post;
//    }
    
    @GET
    @Produces("application/json")
    @Path("Posto/get/{nome}")
    public Post getPosto(@PathParam("nome") String nome ) throws SQLException
    {
        Posto p = new Posto();
        p.setNome(nome);

        p = postoDAO.buscar(p);
        
        
        Post post=new Post();
        post.setId(p.getCod());
        post.setNome(p.getNome());

        return post;
        
    }
     @GET
    @Produces("application/json")
    @Path("Posto/listatodos")   
    public List<Post> getListaTodos(){

        List<Posto> listaTodos = postoDAO.listaTodos();
        List<Post> lista=new ArrayList<>();
        for (Posto posto:listaTodos){
            Post post=new Post();
            post.setId(posto.getCod());
            post.setNome(posto.getNome());
            lista.add(post);
        }
        return lista;
    }
    
//    @GET
//        Post post = new Post();
//        post.setNome(postoDAO.valida(nome));
//        return post;
//
//    }

}
