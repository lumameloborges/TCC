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
@Path(value = "/servicoposto")
public class ServicoPosto {
    @EJB
    private PostoDAORemote postoDAO;
     
    @GET
    @Produces("application/json")
    @Path("Posto/get/{nome}")
    public PostWS getPosto(@PathParam("nome") String nome ) throws SQLException
    {
        Posto p = new Posto();
        p.setNome(nome);

        p = postoDAO.buscar(p);
        
        
        PostWS post=new PostWS();
        post.setId(p.getCod());
        post.setNome(p.getNome());

        return post;
        
    }
    
    //http://localhost:8080/TCI-war/meuservico/servicoposto/Posto/listatodos
     @GET
    @Produces("application/json")
    @Path("Posto/listatodos")   
    public List<PostWS> getListaTodos(){

        List<Posto> listaTodos = postoDAO.listateste();
        List<PostWS> lista=new ArrayList<>();
        for (Posto posto:listaTodos){
            PostWS post=new PostWS();
            post.setId(posto.getCod());
            post.setNome(posto.getNome());
            post.setRua(posto.getRua());
            post.setTipoCombustivel(posto.getTipoCombustivel());
            post.setValor(posto.getValor());
            lista.add(post);
        }
        return lista;
    }
}
