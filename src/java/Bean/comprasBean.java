/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controladora.ControladoraCompras;
import Modelo.Compras;
import Modelo.LineaDeCompra;
import Modelo.Usuarios;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author Leandro
 */
public class comprasBean {

    private Compras comp;
    private LineaDeCompra linea;
    private ControladoraCompras cComp;
    private float costoUnit;
    private String txtcant;
    private int cantidad;
    private int id;
    private String nombre;
    private float precio;
    private int stock;
    private String img;
    private Usuarios usuario;
    private String msj;
    private int opcion;

    public comprasBean() throws Exception {
        this.cComp = new ControladoraCompras();
        this.comp = new Compras();
        this.linea = new LineaDeCompra();
        this.msj = "";
    }

    public String cantItems() {
        int cant = getComp().getLista().size();
        return String.valueOf(cant);
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

    public List<Compras> listarCompras() throws Exception {
        List<Compras> listaaux = new ArrayList<Compras>();
        Hashtable listacompras = cComp.ObtenerCabezeraCompras();
        Enumeration listcomp = listacompras.elements();
        while (listcomp.hasMoreElements()) {
            Compras aux1 = (Compras) listcomp.nextElement();
            listaaux.add(aux1);
        }

        return listaaux;
    }

    public List<LineaDeCompra> listarDetallesde(int id) throws Exception {
        List<LineaDeCompra> listaux = new ArrayList<LineaDeCompra>();
        Hashtable listacompras = cComp.ObtenerCabezeraCompras();
        Compras aux = (Compras) listacompras.get(id);
        Enumeration detalles = aux.getLista().elements();
        while (detalles.hasMoreElements()) {
            LineaDeCompra lin = (LineaDeCompra) detalles.nextElement();
            listaux.add(lin);
        }
        return listaux;

    }

    public List<Compras> listarComprasPendientes() throws Exception {
        List<Compras> listaaux = new ArrayList<Compras>();
        Hashtable listacompras = cComp.ObtenerCabezeraCompras();
        Enumeration listcomp = listacompras.elements();
        while (listcomp.hasMoreElements()) {
            Compras aux1 = (Compras) listcomp.nextElement();
            if (aux1.getEstado().equals("Pendiente")) {
                listaaux.add(aux1);
            }
        }

        return listaaux;
    }

    public void agregarProd() {
        if (comp.getLista().containsKey(id)) {
            LineaDeCompra aux = new LineaDeCompra();
            aux = (LineaDeCompra) comp.getLista().get(id);
            aux.setCantidad(aux.getCantidad() + cantidad);
            comp.getLista().put(aux.getId(), aux);
        } else {
            linea = new LineaDeCompra(id, nombre, costoUnit, cantidad);
            linea.setImg(img);
            linea.setPrecio(precio);
            linea.setStock(stock);
            linea.setCantidad(cantidad);
            getComp().getLista().put(getLinea().getId(), getLinea());

        }
        this.cantidad = 0;
    }

    public void modificarProd() {
        LineaDeCompra aux = new LineaDeCompra();
        aux = (LineaDeCompra) comp.getLista().get(id);
        aux.setCantidad(cantidad);
        comp.getLista().put(aux.getId(), aux);
        this.cantidad = 0;
    }

    public void eliminarProd() {
        comp.getLista().remove(id);
    }

    public String confirmarCompra() throws Exception {
        String rta = "";
        if (cComp.AltaCompra(getUsuario(), comp.getLista())) {
            comp = new Compras();
            rta = "history.xhtml";
        } else {
            rta = "error.xhtml";
        }
        return rta;
    }

    public List<LineaDeCompra> listarDetalles() {
        List<LineaDeCompra> linaux = new ArrayList<LineaDeCompra>();
        Enumeration detalles = comp.getLista().elements();
        while (detalles.hasMoreElements()) {
            LineaDeCompra lin = (LineaDeCompra) detalles.nextElement();
            linaux.add(lin);
        }
        return linaux;
    }

    public boolean hayItems() {
        int cant = getComp().getLista().size();
        return cant != 0;
    }
    
    public String modificarCompra(int id) throws Exception{
        
        if(opcion ==3)
        {
            if(cComp.ConfirmarCompra(id))
            {
                msj = "Compra Confirmada";
            }
            else
            {
                msj = "Error al Confirmar Compra";
            }
        }
        else if(opcion == 2)
        {
            if(cComp.RechazarCompra(id))
            {
                msj = "Compra Rechazada";
            }
            else
            {
                msj = "Error al Rechazar Compra";
            }
        }
        
        
        return "msj";
    }

    /**
     * @return the linea
     */
    public LineaDeCompra getLinea() {
        return linea;
    }

    /**
     * @param linea the linea to set
     */
    public void setLinea(LineaDeCompra linea) {
        this.linea = linea;
    }

    /**
     * @return the comp
     */
    public Compras getComp() {
        return comp;
    }

    /**
     * @param comp the comp to set
     */
    public void setComp(Compras comp) {
        this.comp = comp;
    }

    /**
     * @return the costoUnit
     */
    public float getCostoUnit() {
        return costoUnit;
    }

    /**
     * @param costoUnit the costoUnit to set
     */
    public void setCostoUnit(float costoUnit) {
        this.costoUnit = costoUnit;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the img
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * @return the txtcant
     */
    public String getTxtcant() {
        return txtcant;
    }

    /**
     * @param txtcant the txtcant to set
     */
    public void setTxtcant(String txtcant) {
        this.txtcant = txtcant;
    }

    /**
     * @return the user
     */
    public Usuarios getUsuario() {
        return usuario;
    }

    /**
     * @param user the user to set
     */
    public void setUsuario(Usuarios user) {
        this.usuario = user;
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
     * @return the opcion
     */
    public int getOpcion() {
        return opcion;
    }

    /**
     * @param opcion the opcion to set
     */
    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }


}
