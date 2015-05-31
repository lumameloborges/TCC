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
import jpa.ejb.tci.dao.EnderecoDAORemote;
import jpa.tci.bean.Endereco;

/**
 *
 * @author User
 */
@Stateless
@Path(value = "/servicoendereco")
public class ServicoEndereco {

    @EJB
    private EnderecoDAORemote enderecoDAO;

    @GET
    @Produces("application/json")
    @Path("Endereco/get/{rua}")
    public EndWS getPosto(@PathParam("rua") String rua) throws SQLException {
        Endereco e = new Endereco();
        e.setRua(rua);

        e = enderecoDAO.buscar(e);

        EndWS comb = new EndWS();
        comb.setId(e.getCod());
        comb.setRua(e.getRua());

        return comb;
    }

    @GET
    @Produces("application/json")
    @Path("Endereco/listatodos")
    public List<EndWS> getListaTodos() {

        List<Endereco> listaTodos = enderecoDAO.listaTodos();
        List<EndWS> lista = new ArrayList<>();
        for (Endereco endereco : listaTodos) {
            EndWS comb = new EndWS();
            comb.setId(endereco.getCod());
            comb.setRua(endereco.getRua());
            lista.add(comb);
        }
        return lista;
    }

}
