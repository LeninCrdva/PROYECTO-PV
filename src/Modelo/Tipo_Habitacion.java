package Modelo;

public class Tipo_Habitacion {
    private int id_tha;
    private String nombre_tha;
    private int numeroCamas_tha;
    private int capacidad_tha;
    private double precio_tha;

    public Tipo_Habitacion() {
    }

    public Tipo_Habitacion(int id_tha, String nombre_tha) {
        this.id_tha = id_tha;
        this.nombre_tha = nombre_tha;
    }
    
    public Tipo_Habitacion(int id_tha, String nombre_tha, int numeroCamas_tha, int capacidad_tha, double precio_tha) {
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

    @Override
    public String toString() {
        return nombre_tha;
    }
}
