/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author Andrea
 */
public class EncabezadoFactura {
    private int id_enc;
    private int idCliente_enc;
    private int idEmpleado_enc;
    private Date fecha_enc;
    private double total_enc;
    private boolean estado_enc;

    public EncabezadoFactura() {
    }

    public EncabezadoFactura(int idCliente_enc, int idEmpleado_enc, Date fecha_enc, double total_enc, boolean estado_enc) {
        this.idCliente_enc = idCliente_enc;
        this.idEmpleado_enc = idEmpleado_enc;
        this.fecha_enc = fecha_enc;
        this.total_enc = total_enc;
        this.estado_enc = estado_enc;
    }

    public int getId_enc() {
        return id_enc;
    }

    public void setId_enc(int id_enc) {
        this.id_enc = id_enc;
    }

    public int getIdCliente_enc() {
        return idCliente_enc;
    }

    public void setIdCliente_enc(int idCliente_enc) {
        this.idCliente_enc = idCliente_enc;
    }

    public int getIdEmpleado_enc() {
        return idEmpleado_enc;
    }

    public void setIdEmpleado_enc(int idEmpleado_enc) {
        this.idEmpleado_enc = idEmpleado_enc;
    }

    public Date getFecha_enc() {
        return fecha_enc;
    }

    public void setFecha_enc(Date fecha_enc) {
        this.fecha_enc = fecha_enc;
    }

    public double getTotal_enc() {
        return total_enc;
    }

    public void setTotal_enc(double total_enc) {
        this.total_enc = total_enc;
    }

    public boolean isEstado_enc() {
        return estado_enc;
    }

    public void setEstado_enc(boolean estado_enc) {
        this.estado_enc = estado_enc;
    }
    
    
}
