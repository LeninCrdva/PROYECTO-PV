/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModeloCuenta extends Cuenta {

    public ModeloCuenta() {
    }

    public ModeloCuenta(int id_cue, int id_emp, String username_cue, String password_cue) {
        super(id_cue, id_emp, username_cue, password_cue);
    }

    public List<Cuenta> ListarCuentasBD() {
        List<Cuenta> lista = new ArrayList<>();
        String sql = "SELECT id_cue, id_emp, username_cue FROM cuenta";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);

        try {
            while (rs.next()) {
                Cuenta ct = new Cuenta();
                ct.setId_cue(rs.getInt(1));
                ct.setId_emp(rs.getInt(2));
                ct.setUsername_cue(rs.getString(3));
                lista.add(ct);
            }
            rs.close();
            return lista;
        } catch (SQLException e) {
            System.err.print(e);
        }
        return lista;
    }

    public List<Cuenta> BuscarCuentasBD(String search) {
        List<Cuenta> lista = new ArrayList<>();
        String sql = "SELECT id_cue, id_emp, username_cue FROM cuenta WHERE username_cue LIKE '%" + search + "%'";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);

        try {
            while (rs.next()) {
                Cuenta ct = new Cuenta();
                ct.setId_cue(rs.getInt(1));
                ct.setId_emp(rs.getInt(2));
                ct.setUsername_cue(rs.getString(3));
                lista.add(ct);
            }
            rs.close();
            return lista;
        } catch (SQLException e) {
            System.err.print(e);
        }
        return lista;
    }

    public SQLException InsertaCuentaBD() {
        String sql = "INSERT INTO cuenta (id_cue, id_emp, username_cue, password_cue ) VALUES "
                + "(" + getId_cue() + ", " + getId_emp() + ", '" + getUsername_cue() + "', '" + getPassword_cue() + "')";
        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    public SQLException ModificarCuentaBD(int id_cue) {
        String sql = "UPDATE  cuenta SET username_cue = '" + getUsername_cue() + "', password_cue = '" + getPassword_cue() + "'"
                + "WHERE id_cue = " + id_cue;
        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    public SQLException EliminarCuentaBD(int id_cue) {
        String sql = "DELETE FROM  cuenta WHERE id_cue = " + id_cue;
        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    public boolean ExistenDatosBD(String username, String password) {
        String sql = "SELECT COUNT(*) FROM cuenta WHERE id_cue LIKE '%" + username + "%' "
                + "AND password cue '%" + password + "%'";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        
        try {
            if (rs.next()) {
                return true;
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return false;
    }
}
