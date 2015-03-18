/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf.tci.mng;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import jpa.tci.bean.Usuario;
import jpa.ejb.tci.dao.UsuarioDAORemote;
/**
 *
 * @author Luma Borges
 */

@Named(value = "UsuarioMNG")
@RequestScoped

public class UsuarioMNG {


    public static final String USER_SESSION_KEY = "sessaousuario";
    @EJB
    UsuarioDAORemote dao;
    private String cod;
    //@Pattern(regexp = "(.+)", message = "{invalid.cliente.nome}")
    private String nome, username, senha, email, endereco, bairro, cidade, uf;
    private String complemento;

    /**
     * Creates a new instance of UsuarioMNG
     */
    public UsuarioMNG() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }


    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public List<Usuario> getLista() {
        return dao.listaTodos();
    }

    public void clear(AjaxBehaviorEvent event) {
        this.cod = null;
    }

    public String save() {
        Usuario value = new Usuario();
        value.setNome(this.nome);
        value.setUsername(this.username);
        value.setSenha(this.senha);
        value.setEmail(this.email);
        value.setEndereco(this.endereco);
        value.setBairro(this.bairro);
        value.setComplemento(this.complemento);
        value.setCidade(this.cidade);
        value.setUf(this.uf);
        dao.create(value);
        return "cadastrado.xhtml";
    }

    public void remove() {
        int index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codExcluir").toString());
        Usuario value = new Usuario();
        value.setCod(index);
        dao.delete(value);
        this.clear(null);
    }

    public String prepUpdate() {
        int index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEditar").toString());
        Usuario value = new Usuario();
        value.setCod(index);
        value = dao.retrive(value);
        this.cod = Integer.toString(value.getCod());
        this.nome = getNome();
        this.username = getUsername();
        this.senha = getSenha();
        this.email = getEmail();
        this.endereco = getEndereco();
        this.bairro = getBairro();
        this.complemento = getComplemento();
        this.cidade = getCidade();
        this.uf = getUf();
        return "usuarioUpdate";
    }

    public String update() {
        Usuario value = new Usuario();
        value.setCod(Integer.valueOf(cod));
        value.setNome(nome);
        value.setUsername(username);
        value.setSenha(senha);
        value.setEmail(email);
        value.setEndereco(endereco);
        value.setBairro(bairro);
        value.setComplemento(complemento);
        value.setCidade(cidade);
        value.setUf(uf);
        dao.update(value);
        return "ok";
    }

    public String valida() {
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario value = dao.achaLogin(username);
        if (value != null) {
            if (!value.getSenha().equals(senha)) {
                this.msgInvalidLogin(context);
                return null;
            }
            context.getExternalContext().getSessionMap().put(USER_SESSION_KEY, value);
            return "index";
        } else {
            this.msgInvalidLogin(context);
            return null;
        }
    }

    private void msgInvalidLogin(FacesContext context) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha na autenticação!", "Usuário ou senha inválidos");
        context.addMessage(null, message);
    }
}
