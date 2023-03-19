package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloEmpleado extends Empleado {

    public ModeloEmpleado() {
    }

    public ModeloEmpleado(int id_per, String numeroidentificacion_per, String nombre_per, String apellido_per, int tipo_doc, String direccion_per, String telefono_per, String email_per, Date fecha_nac, String genero_per) {
        super(id_per, numeroidentificacion_per, nombre_per, apellido_per, tipo_doc, direccion_per, telefono_per, email_per, fecha_nac, genero_per);
    }

    public ModeloEmpleado(int id_emp, int id_per, int idlabor_emp, Date fecha_contratacion_emp) {
        super(id_emp, id_per, idlabor_emp, fecha_contratacion_emp);
    }

    public List<Persona> ListarPersonasEmpleadosBD() {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT p.id_per, p.numeroidentificacion_per, p.nombre_per, p.apellido_per, p.tipo_doc, p.direccion_per, "
                + "p.telefono_per, p.email_per, p.fecha_nac, p.genero_per FROM persona p, empleado e WHERE p.id_per = e.id_per";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);

        try {
            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setId_per(rs.getInt(1));
                emp.setNumeroidentificacion_per(rs.getString(2));
                emp.setNombre_per(rs.getString(3));
                emp.setApellido_per(rs.getString(4));
                emp.setTipo_doc(rs.getInt(5));
                emp.setDireccion_per(rs.getString(6));
                emp.setTelefono_per(rs.getString(7));
                emp.setEmail_per(rs.getString(8));
                emp.setFecha_nac(rs.getDate(9));
                emp.setGenero_per(rs.getString(10));
                lista.add(emp);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.print(e);
        }
        return lista;
    }

    public List<Persona> BuscarEmpleadosBD(String search) {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT p.id_per, p.numeroidentificacion_per, p.nombre_per, p.apellido_per, p.tipo_doc, p.direccion_per, "
                + "p.telefono_per, p.email_per, p.fecha_nac, p.genero_per FROM persona p, empleado e WHERE p.id_per = e.id_per "
                + "AND (p.numeroidentificacion_per LIKE '%" + search + "%' OR p.nombre_per LIKE '%" + search + "%' OR "
                + "p.apellido_per LIKE '%" + search + "%')";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);

        try {
            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setId_per(rs.getInt(1));
                emp.setNumeroidentificacion_per(rs.getString(2));
                emp.setNombre_per(rs.getString(3));
                emp.setApellido_per(rs.getString(4));
                emp.setTipo_doc(rs.getInt(5));
                emp.setDireccion_per(rs.getString(6));
                emp.setTelefono_per(rs.getString(7));
                emp.setEmail_per(rs.getString(8));
                emp.setFecha_nac(rs.getDate(9));
                emp.setGenero_per(rs.getString(10));
                lista.add(emp);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.print(e);
        }
        return lista;
    }

    public List<Empleado> ListarEmpleadosBD() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT id_emp, id_per, idlabor_emp, fecha_contratacion_emp FROM empleado";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);

        try {
            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setId_emp(rs.getInt(1));
                emp.setId_per(rs.getInt(2));
                emp.setIdlabor_emp(rs.getInt(3));
                emp.setFecha_contratacion_emp(rs.getDate(4));
                lista.add(emp);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.print(e);
        }
        return lista;
    }

    public List<Empleado> LlenaComboBD() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT id_emp FROM empleado";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        try {
            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setId_emp(rs.getInt(1));
                lista.add(emp);
            }
            rs.close();
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Empleado> LlenarComboEmBD() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT e.id_emp, p.nombre_per, p.apellido_per, p.numeroidentificacion_per FROM persona p "
                + "INNER JOIN empleado e USING (id_per) LEFT JOIN cuenta c "
                + "ON e.id_emp = c.id_emp WHERE c.id_cue IS NULL";
        ConnectionPG conpq = new ConnectionPG();
        ResultSet rs = conpq.Consulta(sql);
        try {
            while (rs.next()) {
                Empleado per = new Empleado();
                per.setId_emp(rs.getInt(1));
                per.setNombre_per(rs.getString(2));
                per.setApellido_per(rs.getString(3));
                per.setNumeroidentificacion_per(rs.getString(4));
                lista.add(per);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public SQLException InsertaEmpleadoBD() {
        String sql = "INSERT INTO empleado (id_emp, id_per, idlabor_emp, fecha_contratacion_emp) VALUES "
                + "(" + getId_emp() + ", " + getId_per() + ", " + getIdlabor_emp() + ", '" + getFecha_contratacion_emp() + "')";
        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    public SQLException ModificarEmpleadoBD(int id_emp) {
        String sql = "UPDATE empleado SET idlabor_emp = " + getIdlabor_emp() + ","
                + " fecha_contratacion_emp = '" + getFecha_contratacion_emp() + "'" + "WHERE id_emp = " + id_emp;
        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    public int ObtieneIDBD() {
        int id_emp = 0;
        String sql = "SELECT max(id_emp) FROM empleado";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        try {
            if (rs.next()) {
                id_emp = rs.getInt(1);
            }
            rs.close();
            return id_emp;
        } catch (SQLException e) {
            return id_emp;
        }
    }

    public int ObtieneIDBD(int id_per) {
        int id_per_emp = 0;
        String sql = "SELECT id_emp FROM empleado  WHERE id_per = " + id_per;
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        try {
            if (rs.next()) {
                id_per_emp = rs.getInt(1);
            }
            rs.close();
            return id_per_emp;
        } catch (SQLException e) {
            return id_per_emp;
        }
    }

    public boolean EmpleadoRegistradoBD(int id_emp) {
        String sql = "SELECT COUNT(idEmpl_enc) FROM enc_factura  WHERE idEmpl_enc = " + id_emp;
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        boolean isrelated = false;
        try {
            if (rs.next()) {
                isrelated = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloTipoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isrelated;
    }

    public int ObtenerIDEMBD(String user) {
        int id_emp = 0;
        String sql = "SELECT e.id_emp from empleado e INNER JOIN persona p USING(id_per) "
                + "INNER JOIN cuenta c ON e.id_emp = c.id_emp WHERE c.username_cue = '" + user + "'";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        try {
            if (rs.next()) {
                id_emp = rs.getInt(1);
            }
            rs.close();
            return id_emp;
        } catch (SQLException e) {
            return id_emp;
        }
    }

}
