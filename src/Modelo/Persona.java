package Modelo;

import java.util.Date;

public class Persona {
    
    private int id_per;
    private String numeroidentificacion_per;
    private String nombre_per;
    private String apellido_per;
    private int tipo_doc;
    private String direccion_per;
    private String telefono_per;
    private String email_per;
    private Date fecha_nac;
    private String genero_per;

    public Persona() {
    }
    
    public Persona(String numeroIdentificacion, String nombre_per, String apellido_per){
        this.numeroidentificacion_per = numeroIdentificacion;
        this.nombre_per = nombre_per;
        this.apellido_per = apellido_per;
    }

    public Persona(int id_per, String numeroidentificacion_per, String nombre_per, String apellido_per, int tipo_doc, String direccion_per, String telefono_per, String email_per, Date fecha_nac, String genero_per) {
        this.id_per = id_per;
        this.numeroidentificacion_per = numeroidentificacion_per;
        this.nombre_per = nombre_per;
        this.apellido_per = apellido_per;
        this.tipo_doc = tipo_doc;
        this.direccion_per = direccion_per;
        this.telefono_per = telefono_per;
        this.email_per = email_per;
        this.fecha_nac = fecha_nac;
        this.genero_per = genero_per;
    }

    public int getId_per() {
        return id_per;
    }

    public void setId_per(int id_per) {
        this.id_per = id_per;
    }

    public String getNumeroidentificacion_per() {
        return numeroidentificacion_per;
    }

    public void setNumeroidentificacion_per(String numeroidentificacion_per) {
        this.numeroidentificacion_per = numeroidentificacion_per;
    }

    public String getNombre_per() {
        return nombre_per;
    }

    public void setNombre_per(String nombre_per) {
        this.nombre_per = nombre_per;
    }

    public String getApellido_per() {
        return apellido_per;
    }

    public void setApellido_per(String apellido_per) {
        this.apellido_per = apellido_per;
    }

    public int getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(int tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public String getDireccion_per() {
        return direccion_per;
    }

    public void setDireccion_per(String direccion_per) {
        this.direccion_per = direccion_per;
    }

    public String getTelefono_per() {
        return telefono_per;
    }

    public void setTelefono_per(String telefono_per) {
        this.telefono_per = telefono_per;
    }

    public String getEmail_per() {
        return email_per;
    }

    public void setEmail_per(String email_per) {
        this.email_per = email_per;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getGenero_per() {
        return genero_per;
    }

    public void setGenero_per(String genero_per) {
        this.genero_per = genero_per;
    }

    @Override
    public String toString() {
        return nombre_per + " " + apellido_per + ": " + numeroidentificacion_per;
    }
}
