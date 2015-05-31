/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.tci.ws;

import java.sql.SQLException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import jpa.ejb.tci.dao.EnderecoDAORemote;
import jpa.tci.bean.Posto;

/**
 *
 * @author User
 */
//@Stateless
//@Path(value = "/servicoendereco")
//public class ServicoEndereco {
//
//    @EJB
//    private EnderecoDAORemote enderecoDAO;
//
//    @GET
//    @Produces("application/json")
//    @Path("Endereco/get/{rua}")
//    public EndWS getPosto(@PathParam("rua") String rua) throws SQLException {
//         p = new Posto();
//        p.setNome(nome);
//
//        p = postoDAO.buscar(p);
//
//        PostWS post = new PostWS();
//        post.setId(p.getCod());
//        post.setNome(p.getNome());
//
//        return post;

//    }
//
//}
