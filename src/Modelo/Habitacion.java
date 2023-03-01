package Modelo;

public class Habitacion {
    private int id_hab;
    private int idTipo_hab;
    private int numero_hab;
    private boolean estado_hab;

    public Habitacion() {
    }

    public Habitacion(int id_hab, int idTipo_hab, int numero_hab, boolean estado_hab) {
        this.id_hab = id_hab;
        this.idTipo_hab = idTipo_hab;
        this.numero_hab = numero_hab;
        this.estado_hab = estado_hab;
    }

    public int getId_hab() {
        return id_hab;
    }

    public void setId_hab(int id_hab) {
        this.id_hab = id_hab;
    }

    public int getIdTipo_hab() {
        return idTipo_hab;
    }

    public void setIdTipo_hab(int idTipo_hab) {
        this.idTipo_hab = idTipo_hab;
    }

    public int getNumero_hab() {
        return numero_hab;
    }

    public void setNumero_hab(int numero_hab) {
        this.numero_hab = numero_hab;
    }

    public boolean isEstado_hab() {
        return estado_hab;
    }

    public void setEstado_hab(boolean estado_hab) {
        this.estado_hab = estado_hab;
    }
}
