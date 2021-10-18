/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControllerUser;

import ModelUser.ModelUser;
import com.google.common.base.CharMatcher;
import org.apache.commons.lang3.StringUtils;
/**
 *
 * @author pedro
 */
public class ControllerUser {
    
    public boolean createUser(String nome, String usuario, String senha, String cpf) {
        ModelUser model = new ModelUser();
        if (validaUsuario(usuario, senha)) {
            return model.addUsuario(nome, usuario, senha, cpf);
        } else {
            return false;
        }
    }

    private boolean validaUsuario(String usuario, String senha) {
        if (CharMatcher.whitespace().matchesAnyOf(usuario) && CharMatcher.whitespace().matchesAnyOf(senha)) {
            return false;
        } else {
            if (!StringUtils.isBlank(usuario) && usuario.length() > 8) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean logarUser(String usuario, String senha) {
        ModelUser modelLogar = new ModelUser();
        if (validaUsuario(usuario, senha)) {
            return modelLogar.logar(usuario, senha);
        } else {
            return false;
        }
    }
}
