/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controladora.ControladoraUsuarios;
import Modelo.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author Leandro
 */
public class usuariosBean implements Serializable{

    private int ID, dni, tipouser;
    private String usuario, pass, nombre, apellido;   
    private Usuarios userMod;
    private List<Usuarios> listaUser;
    private final ControladoraUsuarios cUser;
    private String msj = "";

    public usuariosBean() throws Exception {
        cUser = new ControladoraUsuarios();
        listaUser = new ArrayList<Usuarios>();
        userMod = new Usuarios();
    }

    public List<Usuarios> todosUser() throws Exception {
        Hashtable useraux = cUser.ObtenerUsuarios();
        Enumeration prods = useraux.elements();
        listaUser = new ArrayList<Usuarios>();
        while (prods.hasMoreElements()) {
            Usuarios aux = (Usuarios) prods.nextElement();
            listaUser.add(aux);
        }
        return listaUser;
    }

    public String mostrarMsj() {
        String rta;
        rta = msj;
        if (msj != null) {
            msj = "";
        }
        return rta;
    }

    public boolean hayMsj() {
        return !msj.isEmpty();
    }

    public String eliminarUser() throws Exception {
        if (cUser.BajaUsuario(ID)) {
            msj = "Usuario eliminado correctamente";
        } else {
            msj = "Error al eliminar Usuario";
        }
        return "msj";
    }

    public String registrarUser() throws Exception {

        if (cUser.AltaUsuario(usuario, pass, dni, nombre, apellido, tipouser)) {
            msj = "Usuario registrado correctamente";
        } else {
            msj = "Error al registrar Usuario";
        }
        return "msj";
    }
    public String modificarUser() throws Exception
    {
        if (cUser.ModificarUsuario(userMod)) {
            msj = "Usuario modificado correctamente";
        } else {
            msj = "Error al modificar Usuario";
        }
        return "msj";
    }


    /**
     * @return the listaUser
     */
    public List<Usuarios> getListaUser() {
        return listaUser;
    }

    /**
     * @param listaUser the listaUser to set
     */
    public void setListaUser(List<Usuarios> listaUser) {
        this.listaUser = listaUser;
    }

    /**
     * @return the cUser
     */
    public ControladoraUsuarios getcUser() {
        return cUser;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the msj
     */
    public String getMsj() {
        return msj;
    }

    /**
     * @param msj the msj to set
     */
    public void setMsj(String msj) {
        this.msj = msj;
    }


    /**
     * @return the dni
     */
    public int getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(int dni) {
        this.dni = dni;
    }

    /**
     * @return the tipouser
     */
    public int getTipouser() {
        return tipouser;
    }

    /**
     * @param tipouser the tipouser to set
     */
    public void setTipouser(int tipouser) {
        this.tipouser = tipouser;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the userMod
     */
    public Usuarios getUserMod() {
        return userMod;
    }

    /**
     * @param userMod the mod to set
     */
    public void setUserMod(Usuarios mod) {
        this.userMod = mod;
    }

}
