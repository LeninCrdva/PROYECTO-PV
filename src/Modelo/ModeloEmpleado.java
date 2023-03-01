package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModeloEmpleado extends Empleado {

    public ModeloEmpleado() {
    }

    public ModeloEmpleado(int id_per, String numeroidentificacion_per, String nombre_per, String apellido_per, int tipo_doc, String direccion_per, String telefono_per, String email_per, Date fecha_nac, String genero_per) {
        super(id_per, numeroidentificacion_per, nombre_per, apellido_per, tipo_doc, direccion_per, telefono_per, email_per, fecha_nac, genero_per);
    }

    public ModeloEmpleado(int id_emp, int id_per, int idcuenta_emp, int idlabor_emp) {
        super(id_emp, id_per, idcuenta_emp, idlabor_emp);
    }

    public List<Empleado> ListarEmpleados() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);

        try {
            while (rs.next()) {
                Empleado emp = new Empleado();
            }
        } catch (SQLException e) {
            System.err.print(e);
        }
        return lista;
    }

    public SQLException InsertaEmpleado() {
        String sql = "INSERT INTO empleado (id_emp, id_per, idcuenta_emp, idlabor_emp) VALUES "
                + "(" + getId_emp() + ", " + getId_per() + ", " + getIdcuenta_emp() + ", " + getIdlabor_emp() + ")";
        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    
    
}
