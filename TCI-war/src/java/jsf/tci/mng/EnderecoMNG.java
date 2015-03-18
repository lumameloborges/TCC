/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.tci.mng;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;

import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.validation.constraints.Pattern;
import jpa.ejb.tci.dao.EnderecoDAORemote;
import jpa.tci.bean.Endereco;
import jpa.tci.bean.Posto;

/**
 *
 * @author Luma Borges
 */
@Named(value = "enderecoMNG")
@RequestScoped
public class EnderecoMNG {

    @EJB
    EnderecoDAORemote dao;
    private String cod;
    @Pattern(regexp = "(.+)", message = "{invalid.endereco.posto}")
    private String bairro;
    private String rua;
    private Posto posto;

    /**
     * Creates a new instance of ModeloMNG
     */
    public EnderecoMNG() {
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Posto getPosto() {
        return posto;
    }

    public void setPosto(Posto posto) {
        this.posto = posto;
    }

    public List<Endereco> getLista() {
        return dao.listaTodos();
    }

    public void clear(AjaxBehaviorEvent event) {
        this.cod = null;
    }

    public String save() {
        Endereco value = new Endereco();
        value.setBairro(this.bairro);
        value.setRua(this.rua);
        value.setPosto(this.posto);
        dao.create(value);
        return "Ok";
    }

    public void remove() {
        int index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codExcluir").toString());
        Endereco value = new Endereco();
        value.setCod(index);
        dao.delete(value);
        this.clear(null);
    }

    public String prepUpdate() {
        int index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar").toString());
        Endereco value = new Endereco();
        value.setCod(index);
        value = dao.retrive(value);
        this.cod = Integer.toString(value.getCod());
        this.bairro = getBairro();
        this.rua = getRua();
        this.posto = value.getPosto();
        return "enderecoUpdate";
    }

    public String update() {
        Endereco value = new Endereco();
        value.setCod(Integer.valueOf(cod));
        value.setPosto(posto);
        value.setBairro(bairro);
        value.setRua(rua);
        dao.update(value);
        return "ok";
    }
}
