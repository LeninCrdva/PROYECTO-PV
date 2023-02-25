package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            Labor lab = new Labor();
            lab.setId_lab(rs.getInt(1));
            lab.setNombre_lab(rs.getString(2));
            lab.setHoraslaborales_lab(rs.getInt(3));
            lab.setSueldo_lab(rs.getDouble(4));

            lista.add(lab);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return lista;
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
            Labor lab = new Labor();
            lab.setId_lab(rs.getInt(1));
            lab.setNombre_lab(rs.getString(2));
            lab.setHoraslaborales_lab(rs.getInt(3));
            lab.setSueldo_lab(rs.getDouble(4));
            lista.add(lab);
            return lista;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public SQLException ModificaLaborBD(int id_lab) {
        String slq = "UPDATE labor SET nombre_lab = '"+getNombre_lab() + "', horaslaborales_lab = " + getHoraslaborales_lab() + 
                ", sueldo = " + getSueldo_lab() + " WHERE id_lab = " + id_lab;
        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(slq);
        return ex;
    }

    public SQLException EliminarLaborBD(int id_lab) {
        String sql = "DELETE FROM labor WHERE id_lab = " + id_lab;
        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(sql);
        return ex;
    }
}
