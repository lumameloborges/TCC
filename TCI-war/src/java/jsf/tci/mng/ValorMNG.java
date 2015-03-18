/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf.tci.mng;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.validation.constraints.Pattern;
import jpa.tci.bean.Valor;
import jpa.tci.bean.Usuario;
import jpa.tci.bean.Combustivel;
import jpa.tci.bean.Posto;
import jpa.ejb.tci.dao.ValorDAORemote;

/**
 *
 * @author Luma Borges
 */

@Named(value = "valorMNG")
@RequestScoped

public class ValorMNG {
    
    @EJB
    ValorDAORemote dao;
    private String cod;
    private double valorcombustivel;
    @Pattern(regexp = "(.+)", message = "{invalid.valor.combustivel}")
    private Date data;
    private Combustivel combustivel;
    private Usuario usuario;
    private Posto posto;

    /**
     * Creates a new instance of AluguelMNG
     */
    public ValorMNG() {
    }
    
    public String getCod() {
        return cod;
    }
    
    public void setValorCombustivel(Double valorcombustivel) {
        this.valorcombustivel = valorcombustivel;
    }
        public Double getValorCombustivel() {
        return valorcombustivel;
    }
    
    public void setCod(String cod) {
        this.cod = cod;
    }
    
    public Date getData() {
        return data;
    }
    
    public void setData(Date data) {
        this.data = data;
    }
    
    
    public Posto getPosto() {
        return posto;
    }
    
    public void setPosto(Posto posto) {
        this.posto = posto;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
   public Combustivel getCombustivel() {
        return combustivel;
    }
    
    public void setCombustivel(Combustivel combustivel) {
        this.combustivel = combustivel;
    }

    public List<Valor> getLista() {
        return dao.listaTodos();
    }
    
    public void clear(AjaxBehaviorEvent event) {
        this.cod = null;
    }
    
    public String save() {
        Valor value = new Valor();
        value.setValorCombustivel(this.valorcombustivel);
        value.setUsuario(this.usuario);
        value.setCombustivel(this.combustivel);
        value.setPosto(this.posto);
        value.setData(this.data);        
        dao.create(value);
        return "Ok";
    }
    
    public void remove() {
        int index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codExcluir").toString());
        Valor value = new Valor();
        value.setCod(index);
        dao.delete(value);
        this.clear(null);
    }
    
    public String prepUpdate() {
        int index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar").toString());
        Valor value = new Valor();
        value.setCod(index);
        value = dao.retrive(value);
        this.cod = Integer.toString(value.getCod());
        this.valorcombustivel = Double.valueOf(value.getValorCombustivel());
        this.usuario = getUsuario();
        this.posto = getPosto();
        this.combustivel = getCombustivel();
        this.data = getData();
        return "valorUpdate";
    }
    
    public String update() {
        Valor value = new Valor();
        value.setCod(Integer.valueOf(cod));
        value.setValorCombustivel(valorcombustivel);
        value.setUsuario(usuario);
        value.setPosto(posto);
        value.setCombustivel(combustivel);
        value.setData(data);
        dao.update(value);
        return "ok";
    }
}
