/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.tci.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import jsf.tci.mng.UsuarioMNG;

/**
 *
 * @author Luma Borges
 */

public class Autenticar implements PhaseListener {

    public Autenticar() {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext fc = event.getFacesContext();

        if (permite(fc)) {
        } else {
            // Verifica se esta na página principal
            boolean paginaLogin = fc.getViewRoot().getViewId().lastIndexOf("login.xhtml") > -1;
            if (!paginaLogin) {
                NavigationHandler nh = fc.getApplication().getNavigationHandler();
            }
        }
    }

    private boolean permite(FacesContext context) {
        ExternalContext extContext = context.getExternalContext();
        return (extContext.getSessionMap().containsKey(UsuarioMNG.USER_SESSION_KEY));
    }
}
