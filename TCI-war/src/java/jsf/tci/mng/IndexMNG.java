/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf.tci.mng;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Luma Borges
 */
@Named(value = "indexMNG")
@RequestScoped

public class IndexMNG {

    public IndexMNG() {
    }

    public String redirectIndex() {
        return  "index";
    }

    public String redirectLogin() {
        return "login";
    }
}

