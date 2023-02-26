package Modelo;


public class Labor {
    
    private int id_lab;
    private String nombre_lab;
    private int horaslaborales_lab;
    private double sueldo_lab;

    public Labor() {
    }

    public Labor(int id_lab, String nombre_lab) {
        this.id_lab = id_lab;
        this.nombre_lab = nombre_lab;
    }

    public Labor(int id_lab, String nombre_lab, int horaslaborales_lab, double sueldo_lab) {
        this.id_lab = id_lab;
        this.nombre_lab = nombre_lab;
        this.horaslaborales_lab = horaslaborales_lab;
        this.sueldo_lab = sueldo_lab;
    }

    public int getId_lab() {
        return id_lab;
    }

    public void setId_lab(int id_lab) {
        this.id_lab = id_lab;
    }

    public String getNombre_lab() {
        return nombre_lab;
    }

    public void setNombre_lab(String nombre_lab) {
        this.nombre_lab = nombre_lab;
    }

    public int getHoraslaborales_lab() {
        return horaslaborales_lab;
    }

    public void setHoraslaborales_lab(int horaslaborales_lab) {
        this.horaslaborales_lab = horaslaborales_lab;
    }

    public double getSueldo_lab() {
        return sueldo_lab;
    }

    public void setSueldo_lab(double sueldo_lab) {
        this.sueldo_lab = sueldo_lab;
    }

    @Override
    public String toString() {
        return id_lab + nombre_lab;
    }
}
