/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Andrea
 */
public class TipoHabitacion {
    private int id_tha;
    private String nombre_tha;
    private int numeroCamas_tha;
    private int capacidad_tha;
    private double precio_tha;

    public TipoHabitacion() {
    }

    public TipoHabitacion(int id_tha, String nombre_tha, int numeroCamas_tha, int capacidad_tha, double precio_tha) {
        this.id_tha = id_tha;
        this.nombre_tha = nombre_tha;
        this.numeroCamas_tha = numeroCamas_tha;
        this.capacidad_tha = capacidad_tha;
        this.precio_tha = precio_tha;
    }

    public int getId_tha() {
        return id_tha;
    }

    public void setId_tha(int id_tha) {
        this.id_tha = id_tha;
    }

    public String getNombre_tha() {
        return nombre_tha;
    }

    public void setNombre_tha(String nombre_tha) {
        this.nombre_tha = nombre_tha;
    }

    public int getNumeroCamas_tha() {
        return numeroCamas_tha;
    }

    public void setNumeroCamas_tha(int numeroCamas_tha) {
        this.numeroCamas_tha = numeroCamas_tha;
    }

    public int getCapacidad_tha() {
        return capacidad_tha;
    }

    public void setCapacidad_tha(int capacidad_tha) {
        this.capacidad_tha = capacidad_tha;
    }

    public double getPrecio_tha() {
        return precio_tha;
    }

    public void setPrecio_tha(double precio_tha) {
        this.precio_tha = precio_tha;
    }
    
}
