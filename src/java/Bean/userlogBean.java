/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controladora.ControladoraUsuarios;
import Modelo.Usuarios;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Leandro
 */
public class userlogBean implements Serializable {

    private Usuarios user;
    private final ControladoraUsuarios cUser;
    String msj;

    public userlogBean() throws Exception {
        user = new Usuarios();
        cUser = new ControladoraUsuarios();
        msj = "";
    }
    public String logueado()
    {
        String rta = "";
        
        if(user.getNombre().isEmpty())
        {
            rta= "nolog";
        }
        return rta;
    }

    public String login() throws Exception {
        String rta = "index";
        try {
            user = getcUser().Login(user.getUsuario(), user.getContrasenia());
            if (user != null) {
                HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.setAttribute("logueado", user);
                rta = "main";
            }
        } catch (Exception ex) {
            msj = "Usuario y/o Contraseña incorrecto";

            rta = "index";

        } finally {
            return rta;
        }
    }

    public String logout() {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
        return "index";
    }

    public String mostrarMsj() {
        String rta;
        rta = msj;
        if (msj.isEmpty()) {
        } else {
            msj = null;
        }
        return rta;
    }

    public boolean hayMsj() {
        return !msj.isEmpty();
    }

    public boolean validarAdmin() {
        return user.getTipoUsr() == 1;
    }

    /**
     * @return the user
     */
    public Usuarios getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Usuarios user) {
        this.user = user;
    }

    /**
     * @return the cUser
     */
    private ControladoraUsuarios getcUser() {
        return cUser;
    }

}
