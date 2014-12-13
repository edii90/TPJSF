/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controladora.ControladoraProductos;
import Modelo.Productos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author Leandro
 */
public class productosBean implements Serializable {

    private Productos prod;
    private String nombre, img;
    private int stock;
    private float precio;
    private final ControladoraProductos cProd;
    private String msj;

    public productosBean() throws Exception {
        this.prod = new Productos();
        this.cProd = new ControladoraProductos();
        this.msj = "";
        this.img = "img/noimage.jpg";
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

    public List<Productos> todosProd() throws Exception {
        Hashtable prodaux = cProd.ObtenerProducto();
        List<Productos> lista = new ArrayList<Productos>();
        Enumeration prods = prodaux.elements();
        while (prods.hasMoreElements()) {
            Productos aux = (Productos) prods.nextElement();
            lista.add(aux);

        }

        return lista;
    }

    public String eliminarProd(int id) throws Exception {
        if (cProd.BajaProducto(id)) {
            msj = "Producto Eliminado";
        } else {
            msj = "Error al Eliminar Producto";
        }
        return "msj";
    }
    
    public String modificarProd(String nom, String pre, String st, String image) throws Exception
    {
        Productos aux = new Productos(prod.getId(),nom, Float.parseFloat(pre), Integer.parseInt(st), image);
        if(cProd.ModificarProducto(aux))
        {
            msj= "Producto Modificado";
        }
        else
        {
            msj= "Error al Modificar Producto";
        }
        return "msj";
    }
    
    public String registrarProd() throws Exception
    {
        if(cProd.AltaProducto(nombre, precio, stock, img))
        {
            msj= "Producto Registrado";
        }
        else{
            msj= "Error al Registrar Producto";
        }
        return "msj";
    }

    /**
     * @return the prod
     */
    public Productos getProd() {
        return prod;
    }

    /**
     * @param prod the prod to set
     */
    public void setProd(Productos prod) {
        this.prod = prod;
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

}
