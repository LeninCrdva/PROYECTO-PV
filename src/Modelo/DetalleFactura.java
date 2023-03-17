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
public class DetalleFactura {
    private int id_det;
    private int idEncabezado_det;
    private int idServicio_det;
    private int idReserva_det;
    private String observaciones_det;
    private double costo_det;

    public DetalleFactura() {
    }

    public DetalleFactura(int idEncabezado_det, int idServicio_det, int idReserva_det, String observaciones_det, double costo_det) {
        this.idEncabezado_det = idEncabezado_det;
        this.idServicio_det = idServicio_det;
        this.idReserva_det = idReserva_det;
        this.observaciones_det = observaciones_det;
        this.costo_det = costo_det;
    }

    public int getId_det() {
        return id_det;
    }

    public void setId_det(int id_det) {
        this.id_det = id_det;
    }

    public int getIdEncabezado_det() {
        return idEncabezado_det;
    }

    public void setIdEncabezado_det(int idEncabezado_det) {
        this.idEncabezado_det = idEncabezado_det;
    }

    public int getIdServicio_det() {
        return idServicio_det;
    }

    public void setIdServicio_det(int idServicio_det) {
        this.idServicio_det = idServicio_det;
    }

    public int getIdReserva_det() {
        return idReserva_det;
    }

    public void setIdReserva_det(int idReserva_det) {
        this.idReserva_det = idReserva_det;
    }

    public String getObservaciones_det() {
        return observaciones_det;
    }

    public void setObservaciones_det(String observaciones_det) {
        this.observaciones_det = observaciones_det;
    }

    public double getCosto_det() {
        return costo_det;
    }

    public void setCosto_det(double costo_det) {
        this.costo_det = costo_det;
    }
    
    
}
