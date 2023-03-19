package Modelo;

import java.util.Date;

public class Empleado extends Persona {

    private int id_emp;
    private int id_per;
    private int idlabor_emp;
    private Date fecha_contratacion_emp;

    public Empleado() {
    }

    public Empleado(int id_emp) {
        this.id_emp = id_emp;
    }

    public Empleado(int id_per, String numeroidentificacion_per, String nombre_per, String apellido_per, int tipo_doc, String direccion_per, String telefono_per, String email_per, Date fecha_nac, String genero_per) {
        super(id_per, numeroidentificacion_per, nombre_per, apellido_per, tipo_doc, direccion_per, telefono_per, email_per, fecha_nac, genero_per);
    }

    public Empleado(int id_emp, int id_per, int idlabor_emp, Date fecha_contratacion_emp) {
        this.id_emp = id_emp;
        this.id_per = id_per;
        this.idlabor_emp = idlabor_emp;
        this.fecha_contratacion_emp = fecha_contratacion_emp;
    }

    public Empleado(int id_emp, String numeroIdentificacion, String nombre_per, String apellido_per) {
        super(numeroIdentificacion, nombre_per, apellido_per);
        this.id_emp = id_emp;
    }

    public int getId_emp() {
        return id_emp;
    }

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }

    public Date getFecha_contratacion_emp() {
        return fecha_contratacion_emp;
    }

    public void setFecha_contratacion_emp(Date fecha_contratacion_emp) {
        this.fecha_contratacion_emp = fecha_contratacion_emp;
    }

    @Override
    public void setNumeroidentificacion_per(String numeroidentificacion_per) {
        super.setNumeroidentificacion_per(numeroidentificacion_per); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNumeroidentificacion_per() {
        return super.getNumeroidentificacion_per(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getId_per() {
        return id_per;
    }

    @Override
    public void setId_per(int id_per) {
        this.id_per = id_per;
    }

    public int getIdlabor_emp() {
        return idlabor_emp;
    }

    public void setIdlabor_emp(int idlabor_emp) {
        this.idlabor_emp = idlabor_emp;
    }

    @Override
    public String toString() {
        return "" + getNumeroidentificacion_per() + " - " + getNombre_per() + " " + getApellido_per();
    }
}
