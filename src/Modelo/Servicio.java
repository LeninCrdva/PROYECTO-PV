/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author ERIKA
 */
public class Servicio {
    
    private int Id_ser;
    private String nombre_ser ;
    private String descripcion_ser;
    private double  precio_ser;

    public Servicio() {
    }

    public Servicio(int Id_ser, String nombre_ser, String descripcion_ser, double precio_ser) {
        this.Id_ser = Id_ser;
        this.nombre_ser = nombre_ser;
        this.descripcion_ser = descripcion_ser;
        this.precio_ser = precio_ser;
    }

    public int getId_ser() {
        return Id_ser;
    }

    public void setId_ser(int Id_ser) {
        this.Id_ser = Id_ser;
    }

    public String getNombre_ser() {
        return nombre_ser;
    }

    public void setNombre_ser(String nombre_ser) {
        this.nombre_ser = nombre_ser;
    }

    public String getDescripcion_ser() {
        return descripcion_ser;
    }

    public void setDescripcion_ser(String descripcion_ser) {
        this.descripcion_ser = descripcion_ser;
    }

    public double getPrecio_ser() {
        return precio_ser;
    }

    public void setPrecio_ser(double precio_ser) {
        this.precio_ser = precio_ser;
    }

    @Override
    public String toString() {
        return "Servicios{" + "Id_ser=" + Id_ser + ", nombre_ser=" + nombre_ser + ", descripcion_ser=" + descripcion_ser + ", precio_ser=" + precio_ser + '}';
    }
    
    
    
    
}
