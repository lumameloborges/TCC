/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.tci.ws;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import jpa.ejb.tci.dao.ValorDAORemote;
import jpa.tci.bean.Posto;
import jpa.tci.bean.Valor;

/**
 *
 * @author User
 */
@Stateless
@Path(value = "/servicovalor")
public class ServicoValor {
    @EJB
    private ValorDAORemote valorDAO;
    

@GET
    @Produces("application/json")
    @Path("Valor/get/{valorcombustivel}")
    public ValWS getValor(@PathParam("valorcombustivel") double valorcombustivel ) throws SQLException
    {
        Valor v = new Valor();
        v.setValorCombustivel(valorcombustivel);

        v = valorDAO.buscar(v);
        
        
        ValWS val=new ValWS();
        val.setId(v.getCod());
        val.setValorcombustivel(v.getValorCombustivel());

        return val;
        
    }
     @GET
    @Produces("application/json")
    @Path("Valor/listatodos")   
    public List<ValWS> getListaTodos(){

        List<Valor> listaTodos = valorDAO.listaTodos();
        List<ValWS> lista=new ArrayList<>();
        for (Valor valor:listaTodos){
            ValWS val=new ValWS();
            val.setId(valor.getCod());
            val.setValorcombustivel(valor.getValorCombustivel());
            lista.add(val);
        }
        return lista;
    }
}