package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloTipoDocumento extends TipoDocumento {

    public ModeloTipoDocumento() {
    }

    public ModeloTipoDocumento(int id_tip, String nombre_doc) {
        super(id_tip, nombre_doc);
    }

    public List<TipoDocumento> ListarTipoDocumentos() {
        List<TipoDocumento> lista = new ArrayList<>();
        String sql = "SELECT id_tip, nombre_doc FROM tipo_doc";
        ConnectionPG conpq = new ConnectionPG();
        ResultSet rs = conpq.Consulta(sql);
        try {
            while (rs.next()) {
                TipoDocumento tip = new TipoDocumento();
                tip.setId_tip(rs.getInt(1));
                tip.setNombre_doc(rs.getString(2));
                lista.add(tip);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public int ObtenerIDTDBD() {
        int id_lab = 0;
        String sql = "SELECT MAX(id_tip) from tipo_doc";
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
     public int ConsultaIDBD(String name) {
        int id_tip = 0;
        String sql = "SELECT id_tip FROM tipo_doc WHERE nombre_doc LIKE '%" + name + "%'";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        
        try {
            if (rs.next()) {
                id_tip = rs.getInt(1);
            }
            rs.close();
            return id_tip;
        } catch (SQLException e) {
            System.err.print(e);
            return id_tip;
        }
    }
    public List<TipoDocumento> LlenaComboBD() {
        List<TipoDocumento> lista = new ArrayList<>();
        String sql = "SELECT nombre_doc FROM tipo_doc";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        try {
            while (rs.next()) {
                TipoDocumento tip = new TipoDocumento();
                tip.setNombre_doc(rs.getString(1));
                lista.add(tip);
            }
            rs.close();
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

    public SQLException InsertaTipoDocBD() {
        String sql = "INSERT INTO tipo_doc (id_tip, nombre_doc) VALUES (" + getId_tip() + ", '" + getNombre_doc() + "')";

        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    public List<TipoDocumento> BuscaTipoDocDB(String search) {
        List<TipoDocumento> lista = new ArrayList<>();
        String sql = "SELECT id_tip, nombre_doc FROM tipo_doc "
                + "WHERE nombre_doc LIKE '%" + search + "%'";
        ConnectionPG conpq = new ConnectionPG();
        ResultSet rs = conpq.Consulta(sql);
        try {
            while (rs.next()) {
                TipoDocumento tip = new TipoDocumento();
                tip.setId_tip(rs.getInt(1));
                tip.setNombre_doc(rs.getString(2));
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public SQLException ModificarTipoDocumentoDB(int id_tip) {
        String sql = "UPDATE tipo_doc SET  nombre_doc = '" + getNombre_doc() + "' WHERE id_tip = " + id_tip;
        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    public SQLException EliminarTipoDocumentoDB(int id_tip) {
        String sql = "DELETE FROM tipo_doc WHERE id_tip = " + id_tip;

        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(sql);
        return ex;
    }

}
