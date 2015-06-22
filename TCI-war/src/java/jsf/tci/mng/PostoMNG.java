/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.tci.mng;

import java.util.List;
import javax.ejb.EJB;
//import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.validation.constraints.Pattern;
import jpa.tci.bean.Combustivel;
import jpa.tci.bean.Endereco;
import jpa.tci.bean.Valor;
import jpa.tci.bean.Posto;
import jpa.ejb.tci.dao.PostoDAORemote;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Luma Borges
 */
@Named(value = "postoMNG")
@RequestScoped
public class PostoMNG {

    @EJB
    PostoDAORemote dao;
    private String cod;
    @Pattern(regexp = "(.+)", message = "{invalid.posto.endereco}")
    private String nome;
//    private Combustivel combustivel;
    private Valor valor;

    /**
     * Creates a new instance of ModeloMNG
     */
    public PostoMNG() {
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

//    public Combustivel getCombustivel() {
//        return combustivel;
//    }
//
//    public void setCombustivel(Combustivel combustivel) {
//        this.combustivel = combustivel;
//    }

    public Valor getValor() {
        return valor;
    }

    public void setValor(Valor valor) {
        this.valor = valor;
    }

    public List<Posto> getLista() {
        return dao.listateste();
    }

    public void clear(AjaxBehaviorEvent event) {
        this.cod = null;
    }

    public String save() {
        Posto value = new Posto();
//        value.setValorCombustivel((List<Valor>) this.valor);
        value.setNome(this.nome);
        dao.create(value);
        return "Ok";
    }

    public void remove() {
        int index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codExcluir").toString());
        Posto value = new Posto();
        value.setCod(index);
        dao.delete(value);
        this.clear(null);
    }

    public String prepUpdate() {
        int index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar").toString());
        Posto value = new Posto();
        value.setCod(index);
        value = dao.retrive(value);
        this.cod = Integer.toString(value.getCod());
        this.nome = getNome();
//        this.valor = (Valor) value.getValorCombustivel();
        return "postoUpdate";
    }

    public String update() {
        Posto value = new Posto();
        value.setCod(Integer.valueOf(cod));
//        value.setValorCombustivel((List<Valor>) valor);
        value.setNome(nome);
        dao.update(value);
        return "ok";
    }
}
