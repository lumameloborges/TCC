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
import jpa.ejb.tci.dao.CombustivelDAORemote;
import jpa.tci.bean.Combustivel;

/**
 *
 * @author User
 */
@Stateless
@Path(value = "/servicocombustivel")
public class ServicoCombustivel {
     @EJB
     private CombustivelDAORemote combustivelDAO;
     
    @GET
    @Produces("application/json")
    @Path("Combustivel/get/{tipocombustivel}")
    public CombWS getCombustivel(@PathParam("tipocombustivel") String tipocombustivel ) throws SQLException
    {
     Combustivel c = new Combustivel();
     c.setTipoCombustivel(tipocombustivel);

        c = combustivelDAO.buscar(c);
        
        
        CombWS comb=new CombWS();
        comb.setId(c.getCod());
        comb.setTipocombustivel(c.getTipoCombustivel());

        return comb;
 }   
 @GET
    @Produces("application/json")
    @Path("Combustivel/listatodos")   
    public List<CombWS> getListaTodos(){

        List<Combustivel> listaTodos = combustivelDAO.listaTodos();
        List<CombWS> lista=new ArrayList<>();
        for (Combustivel combustivel:listaTodos){
            CombWS comb=new CombWS();
            comb.setId(combustivel.getCod());
            comb.setTipocombustivel(combustivel.getTipoCombustivel());
            lista.add(comb);
        }
        return lista;
    }
}

