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
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloTipoCliente extends TipoCliente {

    public ModeloTipoCliente() {

    }

    public ModeloTipoCliente(int id_tip, String nombre_tip) {
        super(id_tip, nombre_tip);
    }

    public List<TipoCliente> ListaTipoCliente() {
        List<TipoCliente> lista = new ArrayList<>();
        String sql = "SELECT id_tip,nombre_tip from tipo_cliente";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        try {
            while (rs.next()) {
                TipoCliente tipcli = new TipoCliente();
                tipcli.setId_tip(rs.getInt(1));
                tipcli.setNombre_tip(rs.getString(2));
                lista.add(tipcli);

            }

        } catch (SQLException e) {
            System.err.println(e);
        }
        return lista;

    }

    public List<TipoCliente> ListaTipoCliBD() {
        List<TipoCliente> lista = new ArrayList<>();
        String sql = "SELECT id_tip, nombre_tip FROM tipo_cliente";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        try {
            while (rs.next()) {
                TipoCliente tipc = new TipoCliente();
                tipc.setId_tip(rs.getInt(1));
                tipc.setNombre_tip(rs.getString(2));
                lista.add(tipc);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return lista;
    }

    public int ObtenerIdTipoCliente() {
        int id_tip = 0;
        String sql = "select max(id_tip) from tipo_cliente";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);

        try {
            if (rs.next()) {
                id_tip = rs.getInt(1);

            }
            rs.close();
            return id_tip;
        } catch (SQLException e) {
            System.err.println(e);
            return id_tip;
        }
    }

    public TipoCliente ObtieneTipoClienteBD(String nombre_tip) {
        TipoCliente tc = new TipoCliente();
        String sql = "SELECT id_tip, nombre_tip FROM tipo_cliente WHERE nombre_tip = '" + nombre_tip + "'";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        try {
            while (rs.next()) {
                tc.setId_tip(rs.getInt(1));
                tc.setNombre_tip(rs.getString(2));
            }
            rs.close();
            return tc;
        } catch (SQLException e) {
            return tc;
        }
    }

    public int ConsularIDBDTipocliente(String nombre) {
        int id_tip = 0;
        String sql = "select id_tip  from tipo_cliente where nombre_tip like '%" + nombre + "%'";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);

        try {
            if (rs.next()) {
                id_tip = rs.getInt(1);
            }
            rs.close();
            return id_tip;
        } catch (SQLException e) {
            System.err.println();
            return id_tip;
        }

    }

    public List<TipoCliente> LlenarComboTipoCliBD() {
        List<TipoCliente> lista = new ArrayList<>();
        String sql = "SELECT id_tip, nombre_tip FROM tipo_cliente";

        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        try {
            while (rs.next()) {
                TipoCliente clitip = new TipoCliente();
                clitip.setId_tip(rs.getInt(1));
                clitip.setNombre_tip(rs.getString(2));
                lista.add(clitip);
            }
            rs.close();
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

    public SQLException InsertarTipoCliente() {
        String sql = " INSERT INTO  tipo_cliente (id_tip,nombre_tip )"
                + "VALUES (" + getId_tip() + ",'" + getNombre_tip() + "')";
        ConnectionPG con = new ConnectionPG();
        SQLException ie = con.Accion(sql);
        return ie;

    }

    public List<TipoCliente> BuscarTipoClienteBD(String bus) {
        List<TipoCliente> lista = new ArrayList<>();
        String sql = "SELECT  id_tip, nombre_tip FROM  tipo_cliente where  nombre_tip LIKE '%" + bus + "%'";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);

        try {
            while (rs.next()) {
                TipoCliente tipcli = new TipoCliente();
                tipcli.setId_tip(rs.getInt(1));
                tipcli.setNombre_tip(rs.getString(2));
                lista.add(tipcli);

            }
            return lista;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }

    }

    public SQLException modificarTipoCliente(int id_tip) {
        String sql = "UPDATE  tipo_cliente  SET  nombre_tip = '" + getNombre_tip() + "' WHERE  id_tip = " + id_tip;
        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(sql);
        return ex;

    }

    public SQLException EliminarTipoCliente(int id_tip) {
        String sql = "DELETE FROM tipo_cliente  WHERE  id_tip = " + id_tip;
        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    public boolean ExisteNombreTipoDocBD(String nombre_tipocl) {
        String sql = "SELECT COUNT(*) FROM tipo_cliente  WHERE nombre_tip  = '" + nombre_tipocl + "'";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        try {
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;

            } else {
                return false;

            }
        } catch (SQLException e) {
            Logger.getLogger(ModeloTipoCliente.class.getName()).log(Level.SEVERE, null, e);
            return true;
        }

    }

}
