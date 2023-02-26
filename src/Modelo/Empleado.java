package Modelo;

import java.util.Date;

public class Empleado extends Persona {
    
    private int id_emp;
    private int id_per;
    private int idcuenta_emp;
    private int idlabor_emp;

    public Empleado() {
    }

    public Empleado(int id_per, String numeroidentificacion_per, String nombre_per, String apellido_per, int tipo_doc, String direccion_per, String telefono_per, String email_per, Date fecha_nac, String genero_per) {
        super(id_per, numeroidentificacion_per, nombre_per, apellido_per, tipo_doc, direccion_per, telefono_per, email_per, fecha_nac, genero_per);
    }
    
    public Empleado(int id_emp, int id_per, int idcuenta_emp, int idlabor_emp) {
        this.id_emp = id_emp;
        this.id_per = id_per;
        this.idcuenta_emp = idcuenta_emp;
        this.idlabor_emp = idlabor_emp;
    }

    public int getId_emp() {
        return id_emp;
    }

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }

    public int getId_per() {
        return id_per;
    }

    public void setId_per(int id_per) {
        this.id_per = id_per;
    }

    public int getIdcuenta_emp() {
        return idcuenta_emp;
    }

    public void setIdcuenta_emp(int idcuenta_emp) {
        this.idcuenta_emp = idcuenta_emp;
    }

    public int getIdlabor_emp() {
        return idlabor_emp;
    }

    public void setIdlabor_emp(int idlabor_emp) {
        this.idlabor_emp = idlabor_emp;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id_emp=" + id_emp + ", id_per=" + id_per + ", idcuenta_emp=" + idcuenta_emp + ", idlabor_emp=" + idlabor_emp + '}';
    }
}