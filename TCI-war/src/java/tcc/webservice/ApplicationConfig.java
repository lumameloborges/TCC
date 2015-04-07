/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.webservice;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author aluno
 */
@javax.ws.rs.ApplicationPath("testeservico")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(TesteServico.class);
        return resources;
    }

}
