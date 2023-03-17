package Modelo;

import java.util.Date;

public class Enc_reserva {
    private int id_res;
    private int idCliente_res;
    private Date fechaIngreso_res;
    private Date fechaSalida_res;
    private double total_res;
    private boolean estado_res;

    public Enc_reserva() {
    }

    public Enc_reserva(int id_res, int idCliente_res, Date fechaIngreso_res, Date fechaSalida_res, double total_res, boolean estado_res) {
        this.id_res = id_res;
        this.idCliente_res = idCliente_res;
        this.fechaIngreso_res = fechaIngreso_res;
        this.fechaSalida_res = fechaSalida_res;
        this.total_res = total_res;
        this.estado_res = estado_res;
    }

    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public int getIdCliente_res() {
        return idCliente_res;
    }

    public void setIdCliente_res(int idCliente_res) {
        this.idCliente_res = idCliente_res;
    }

    public Date getFechaIngreso_res() {
        return fechaIngreso_res;
    }

    public void setFechaIngreso_res(Date fechaIngreso_res) {
        this.fechaIngreso_res = fechaIngreso_res;
    }

    public Date getFechaSalida_res() {
        return fechaSalida_res;
    }

    public void setFechaSalida_res(Date fechaSalida_res) {
        this.fechaSalida_res = fechaSalida_res;
    }

    public double getTotal_res() {
        return total_res;
    }

    public void setTotal_res(double total_res) {
        this.total_res = total_res;
    }

    public boolean isEstado_res() {
        return estado_res;
    }

    public void setEstado_res(boolean estado_res) {
        this.estado_res = estado_res;
    }
}
