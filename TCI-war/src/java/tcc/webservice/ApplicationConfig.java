/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.webservice;

import java.util.Set;
import javax.ws.rs.core.Application;
import jpa.tci.ws.TesteServico;
import jpa.tci.ws.TesteServicoPosto;

/**
 *
 * @author aluno
 */
@javax.ws.rs.ApplicationPath("meuservico")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(TesteServico.class);
        resources.add(TesteServicoPosto.class);
        return resources;
    }
    
}
