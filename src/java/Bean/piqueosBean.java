/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

import Controladora.ControladoraPiqueo;
import Modelo.LineaDePiqueo;
import Modelo.Piqueos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author Leandro
 */
public class piqueosBean implements Serializable {
     private ControladoraPiqueo cPiqueo;
     private String msj;
     
    public piqueosBean() throws Exception {
       this.cPiqueo = new ControladoraPiqueo();
       this.msj="";
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
    public List<Piqueos> todosPiqueos() throws Exception
    {
        Hashtable listapiqueos = cPiqueo.ObtenerPiqueos();
        List<Piqueos> lista = new ArrayList<Piqueos>();
        Enumeration piq = listapiqueos.elements();
        while(piq.hasMoreElements())
        {
            Piqueos aux = (Piqueos) piq.nextElement();
            lista.add(aux);
        }
        return lista;
    }
    public List<LineaDePiqueo> obtenerLineasPiqueo(Hashtable linPiqueo) throws Exception
    {
        List<LineaDePiqueo> lista = new ArrayList<LineaDePiqueo>();
        Enumeration piq = linPiqueo.elements();
        while(piq.hasMoreElements())
        {
            LineaDePiqueo aux = (LineaDePiqueo) piq.nextElement();
            lista.add(aux);
        }
        return lista;
    }
    
    public String confirmarPiqueo(int id) throws Exception
    {
        if(cPiqueo.ConfirmarPiqueo(id))
        {
            msj = "Piqueo Confirmado";
        }else{
            msj = "Error al Confirmar Piqueo";
        }
        return "msj";
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
    
}
