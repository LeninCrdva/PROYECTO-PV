/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloCliente extends Cliente {

    public ModeloCliente() {
    }

    public ModeloCliente(int id_cli, int id_per, int id_tip) {
        super(id_cli, id_per, id_tip);
    }

    public ModeloCliente(int id_cli, int id_per, int id_tip, String numeroidentificacion_per, String nombre_per, String apellido_per, int tipo_doc, String direccion_per, String telefono_per, String email_per, Date fecha_nac, String genero_per) {
        super(id_cli, id_per, id_tip, numeroidentificacion_per, nombre_per, apellido_per, tipo_doc, direccion_per, telefono_per, email_per, fecha_nac, genero_per);
    }

    public List<Persona> ListarPersonasClientesBD() {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT p.id_per, p.numeroidentificacion_per, p.nombre_per, p.apellido_per, p.tipo_doc, p.direccion_per, "
                + "p.telefono_per, p.email_per, p.fecha_nac, p.genero_per FROM persona p, cliente c WHERE p.id_per = c.id_per";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);

        try {
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setId_per(rs.getInt(1));
                cli.setNumeroidentificacion_per(rs.getString(2));
                cli.setNombre_per(rs.getString(3));
                cli.setApellido_per(rs.getString(4));
                cli.setTipo_doc(rs.getInt(5));
                cli.setDireccion_per(rs.getString(6));
                cli.setTelefono_per(rs.getString(7));
                cli.setEmail_per(rs.getString(8));
                cli.setFecha_nac(rs.getDate(9));
                cli.setGenero_per(rs.getString(10));
                lista.add(cli);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.print(e);
        }
        return lista;
    }

    public List<Cliente> listarclientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTE c JOIN PERSONA p ON (c.id_per=p.id_per)";

        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);

        try {
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setId_cli(rs.getInt(1));
                cl.setId_per(rs.getInt(2));
                cl.setId_tip(rs.getInt(3));
                cl.setNumeroidentificacion_per(rs.getString(5));
                cl.setNombre_per(rs.getString(6));
                cl.setApellido_per(rs.getString(7));
                cl.setTipo_doc(rs.getInt(8));
                cl.setDireccion_per(rs.getString(9));
                cl.setTelefono_per(rs.getString(10));
                cl.setEmail_per(rs.getString(11));
                cl.setGenero_per(rs.getString(12));
                cl.setFecha_nac(rs.getDate(13));
                lista.add(cl);
            }
            rs.close();
            return lista;
        } catch (SQLException ie) {
            System.err.println(ie);
            return null;
        }
    }

    public Cliente searchClient() {
        Cliente cl = new Cliente();
        String sql = "SELECT * FROM CLIENTE c JOIN PERSONA p ON (c.id_per=p.id_per AND c.id_cli = " + getId_cli() + ")";

        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);

        try {
            while (rs.next()) {
                cl.setId_cli(rs.getInt(1));
                cl.setId_per(rs.getInt(2));
                cl.setId_tip(rs.getInt(3));
                cl.setNumeroidentificacion_per(rs.getString(5));
                cl.setNombre_per(rs.getString(6));
                cl.setApellido_per(rs.getString(7));
                cl.setTipo_doc(rs.getInt(8));
                cl.setDireccion_per(rs.getString(9));
                cl.setTelefono_per(rs.getString(10));
                cl.setEmail_per(rs.getString(11));
                cl.setGenero_per(rs.getString(12));
                cl.setFecha_nac(rs.getDate(13));
            }
            rs.close();
            return cl;
        } catch (SQLException ie) {
            System.err.println(ie);
            return null;
        }
    }

    public SQLException InsertarCliente() {

        String sql = "INSERT INTO cliente(id_cli,id_per,id_tip) VALUES"
                + "(" + getId_cli() + "," + getId_per() + "," + getId_tip() + ")";

        ConnectionPG con = new ConnectionPG();
        SQLException op = con.Accion(sql);
        return op;
    }

    public boolean ClienteRegistrado(int id_cli) {
        String sql = "SELECT COUNT(idCliente_res) FROM enc_reserva  WHERE idCliente_res = " + id_cli;
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

    public int ObtenerID() {
        int id_cli = 0;
        String sql = "SELECT max(id_cli) from cliente";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        try {
            if (rs.next()) {
                id_cli = rs.getInt(1);

            }
            rs.close();
            return id_cli;
        } catch (SQLException e) {
            return id_cli;
        }
    }

    public Cliente getCliente(String nombre) {
        Cliente cli = new Cliente();
        String sql = "SELECT c.id_cli, p.numeroidentificacion_per, p.nombre_per, p.apellido_per from cliente c join persona p "
                + "on (c.id_per = p.id_per) where p.nombre_per || ' ' || p.apellido_per = '" + nombre + "'";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        try {
            rs.next();
            cli.setId_cli(rs.getInt(1));
            cli.setNumeroidentificacion_per(rs.getString(2));
            cli.setNombre_per(rs.getString(3));
            cli.setApellido_per(rs.getString(4));
            rs.close();
            return cli;
        } catch (SQLException e) {
            return cli;
        }
    }
}
