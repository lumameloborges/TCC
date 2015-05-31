    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.webservice;

import java.util.Set;
import javax.ws.rs.core.Application;
import jpa.tci.ws.Servico;
import jpa.tci.ws.ServicoCombustivel;
import jpa.tci.ws.ServicoPosto;
import jpa.tci.ws.ServicoValor;

/**
 *
 * @author aluno
 */
@javax.ws.rs.ApplicationPath("meuservico")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(Servico.class);
        resources.add(ServicoPosto.class);
        resources.add(ServicoValor.class);
        resources.add(ServicoCombustivel.class);
        return resources;
    }
    
}
