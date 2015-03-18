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
import jpa.ejb.tci.dao.CombustivelDAORemote;
import jpa.tci.bean.Combustivel;
import jpa.tci.bean.Valor;

/**
 *
 * @author Luma Borges
 */
@Named(value = "combustivelMNG")
@RequestScoped
public class CombustivelMNG {

    @EJB
    CombustivelDAORemote dao;
    private String cod;
    @Pattern(regexp = "(.+)", message = "{invalid.ombustivel.tipocombustivel}")
    private String tipocombustivel, descricao;
    private Valor valor;

    /**
     * Creates a new instance of ModeloMNG
     */
    public CombustivelMNG() {
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getTipoCombustivel() {
        return tipocombustivel;
    }

    public void setTipoCombustivel(String TipoCombustivel) {
        this.tipocombustivel = tipocombustivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Valor getValor() {
        return valor;
    }

    public void setValor(Valor valor) {
        this.valor = valor;
    }

    public List<Combustivel> getLista() {
        return dao.listaTodos();
    }

    public void clear(AjaxBehaviorEvent event) {
        this.cod = null;
    }

    public String save() {
        Combustivel value = new Combustivel();
        value.setValor(this.valor);
        value.setDescricao(this.descricao);
        value.setTipoCombustivel(this.tipocombustivel);
        dao.create(value);
        return "Ok";
    }

    public void remove() {
        int index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codExcluir").toString());
        Combustivel value = new Combustivel();
        value.setCod(index);
        dao.delete(value);
        this.clear(null);
    }

    public String prepUpdate() {
        int index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar").toString());
        Combustivel value = new Combustivel();
        value.setCod(index);
        value = dao.retrive(value);
        this.cod = Integer.toString(value.getCod());
        this.valor = value.getValor();
        this.descricao = getDescricao();
        this.tipocombustivel = getTipoCombustivel();
        return "modeloUpdate";
    }

    public String update() {
        Combustivel value = new Combustivel();
        value.setCod(Integer.valueOf(cod));
        value.setValor(valor);
        value.setDescricao(descricao);
        value.setTipoCombustivel(tipocombustivel);
        dao.update(value);
        return "ok";
    }
}
