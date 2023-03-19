package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloLabor extends Labor {

    public ModeloLabor() {
    }

    public ModeloLabor(int id_lab, String nombre_lab, int horaslaborales_lab, double sueldo_lab) {
        super(id_lab, nombre_lab, horaslaborales_lab, sueldo_lab);
    }

    public List<Labor> ListaLaborBD() {
        List<Labor> lista = new ArrayList<>();
        String sql = "SELECT id_lab, nombre_lab, horaslaborales_lab, sueldo FROM labor";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        try {
            while (rs.next()) {
                Labor lab = new Labor();
                lab.setId_lab(rs.getInt(1));
                lab.setNombre_lab(rs.getString(2));
                lab.setHoraslaborales_lab(rs.getInt(3));
                lab.setSueldo_lab(rs.getDouble(4));

                lista.add(lab);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return lista;
    }

    public int ObtenerID() {
        int id_lab = 0;
        String sql = "SELECT MAX(id_lab) from labor";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        try {
            if (rs.next()) {
                id_lab = rs.getInt(1);
            }
            rs.close();
            return id_lab;
        } catch (SQLException e) {
            System.err.print(e);
            return id_lab;
        }
    }

    public List<Labor> LlenaComboLabBD() {
        List<Labor> lista = new ArrayList<>();
        String sql = "SELECT id_lab, nombre_lab FROM labor ORDER BY 1";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        try {
            while (rs.next()) {
                Labor lab = new Labor();
                lab.setId_lab(rs.getInt(1));
                lab.setNombre_lab(rs.getString(2));
                lista.add(lab);
            }
            rs.close();
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

    public SQLException InsertarLaborBD() {
        String sql = "INSERT INTO labor (id_lab, nombre_lab, horaslaborales_lab, sueldo) "
                + "VALUES (" + getId_lab() + ", '" + getNombre_lab() + "', " + getHoraslaborales_lab() + ", " + getSueldo_lab() + ")";
        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    public List<Labor> BuscaLaborBD(String search) {
        List<Labor> lista = new ArrayList<>();
        String sql = "SELECT id_lab, nombre_lab, horaslaborales_lab, sueldo FROM labor WHERE "
                + "nombre_lab LIKE '%" + search + "%'";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);

        try {
            while (rs.next()) {
                Labor lab = new Labor();
                lab.setId_lab(rs.getInt(1));
                lab.setNombre_lab(rs.getString(2));
                lab.setHoraslaborales_lab(rs.getInt(3));
                lab.setSueldo_lab(rs.getDouble(4));
                lista.add(lab);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean ExisteNombreLaborBD(String nombre_lab) {
        String sql = "SELECT COUNT(*) FROM labor WHERE nombre_lab = '" + nombre_lab + "'";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        try {
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloLabor.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }
    }

    public Labor ObtieneLaborBD(String nombre_lab) {
        Labor lab = new Labor();
        String sql = "SELECT id_lab, nombre_lab FROM labor WHERE nombre_lab = '" + nombre_lab + "'";
         ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        try {
            if (rs.next()) {
                lab.setId_lab(rs.getInt(1));
                lab.setNombre_lab(rs.getString(2));
            }
            rs.close();
            return lab;
        } catch (SQLException e) {
            return lab;
        }
    }

    public String GetNombreBD(int id_lab) {
        String sql = "SELECT nombre_lab FROM labor WHERE id_lab = " + id_lab;
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        String nombre_lab = null;
        try {
            if (rs.next()) {
                nombre_lab = rs.getString(1);
            }
            rs.close();
            return nombre_lab;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloLabor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public SQLException ModificaLaborBD(int id_lab) {
        String sql = "UPDATE labor SET nombre_lab = '" + getNombre_lab() + "', horaslaborales_lab = " + getHoraslaborales_lab()
                + ", sueldo = " + getSueldo_lab() + " WHERE id_lab = " + id_lab;
        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    public SQLException EliminarLaborBD(int id_lab) {
        String sql = "DELETE FROM labor WHERE id_lab = " + id_lab;
        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    public boolean LaborAsociadaBD(int id_lab) {
        String sql = "SELECT COUNT(idlabor_emp) FROM empleado  WHERE idlabor_emp = " + id_lab;
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
}
