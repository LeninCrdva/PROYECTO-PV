package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloPersona extends Persona {

    public ModeloPersona() {
    }

    public ModeloPersona(int id_per, String numeroidentificacion_per, String nombre_per, String apellido_per, int tipo_doc, String direccion_per, String telefono_per, String email_per, Date fecha_nac, String genero_per) {
        super(id_per, numeroidentificacion_per, nombre_per, apellido_per, tipo_doc, direccion_per, telefono_per, email_per, fecha_nac, genero_per);
    }

    public List<Persona> ListarPersonas() {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT id_per, numeroidentificacion_per, nombre_per, apellido_per, tipo_doc, direccion_per, "
                + "telefono_per, email_per, fecha_nac, genero_per FROM persona";
        ConnectionPG conpq = new ConnectionPG();
        ResultSet rs = conpq.Consulta(sql);
        try {
            while (rs.next()) {
                Persona per = new Persona();
                per.setId_per(rs.getInt(1));
                per.setNumeroidentificacion_per(rs.getString(2));
                per.setNombre_per(rs.getString(3));
                per.setApellido_per(rs.getString(4));
                per.setTipo_doc(rs.getInt(5));
                per.setDireccion_per(rs.getString(6));
                per.setTelefono_per(rs.getString(7));
                per.setEmail_per(rs.getString(8));
                per.setFecha_nac(rs.getDate(9));
                per.setGenero_per(rs.getString(10));
                lista.add(per);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public SQLException InsertaPersonaBD() {
        String sql = "INSERT INTO persona (id_per, numeroidentificacion_per, nombre_per, apellido_per, tipo_doc, direccion_per, "
                + "telefono_per, email_per, fecha_nac, genero_per) VALUES (" + getId_per() + ", '" + getNumeroidentificacion_per() + "','" + getNombre_per() + "', "
                + "'" + getApellido_per() + "', " + getTipo_doc() + ", '" + getDireccion_per() + "', '" + getTelefono_per() + "', '" + getEmail_per() + "', "
                + "'" + getFecha_nac() + "', '" + getGenero_per() + "')";

        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    public List<Persona> BuscaPersonaDB(String search) {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT id_per, numeroidentificacion_per, nombre_per, apellido_per, tipo_doc, direccion_per, "
                + "telefono_per, email_per, fecha_nac, genero_per FROM persona "
                + "WHERE nombre_per LIKE '%" + search + "%'";
        ConnectionPG conpq = new ConnectionPG();
        ResultSet rs = conpq.Consulta(sql);
        try {
            while (rs.next()) {
                Persona per = new Persona();
                per.setId_per(rs.getInt(1));
                per.setNumeroidentificacion_per(rs.getString(2));
                per.setNombre_per(rs.getString(3));
                per.setApellido_per(rs.getString(4));
                per.setTipo_doc(rs.getInt(5));
                per.setDireccion_per(rs.getString(6));
                per.setTelefono_per(rs.getString(7));
                per.setEmail_per(rs.getString(8));
                per.setFecha_nac(rs.getDate(9));
                per.setGenero_per(rs.getString(10));
                lista.add(per);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public SQLException ModficarPersonaDB(int bus) {
        String sql = "UPDATE persona SET  nombre_per = '" + getNombre_per() + "', apellido_per = '" + getApellido_per() + "', tipo_doc = " + getTipo_doc()
                + ", direccion_per = '" + getDireccion_per() + "', telefono_per = '" + getTelefono_per() + "', email_per = '" + getEmail_per() + "', fecha_nac = '" + getFecha_nac() + "', genero_per ="
                + " '" + getGenero_per() + "' " + "WHERE id_per = " + bus;
        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    public SQLException EliminarPersonaDB(int id_per) {
        String sql = "DELETE FROM persona WHERE id_per = " + id_per;

        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    public int ObtieneID() {
        String sql = "SELECT max(id_per) from persona";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        int id_per = 0;
        try {
            if (rs.next()) {
                id_per = rs.getInt(1);
            }
            return id_per;
        } catch (SQLException e) {
            System.err.print(e);
        }
        return id_per;
    }

    public TipoDocumento ObtieneDocBD(int id_per) {
        TipoDocumento td = new ModeloTipoDocumento();
        String sql = "SELECT d.id_tip, d.nombre_doc FROM persona p "
                + "INNER JOIN tipo_doc d ON p.tipo_doc = d.id_tip WHERE p.id_per = " + id_per;
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        try {
            while (rs.next()) {
                td.setId_tip(rs.getInt(1));
                td.setNombre_doc(rs.getString(2));
            }
            rs.close();
            return td;
        } catch (SQLException e) {
            return td;
        }
    }

    public String GetNumeroIdentificacionBD(int id_per) {
        try {
            String num = null;
            String sql = "SELECT numeroidentificacion_per FROM persona "
                    + "WHERE id_per = " + id_per;
            ConnectionPG con = new ConnectionPG();
            try (ResultSet rs = con.Consulta(sql)) {
                if (rs.next()) {

                    num = rs.getString(1);
                }
            }
            return num;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String GetPhoneBD(int id_per) {
        try {
            String num = null;
            String sql = "SELECT telefono_per FROM persona "
                    + "WHERE id_per = " + id_per;
            ConnectionPG con = new ConnectionPG();
            try (ResultSet rs = con.Consulta(sql)) {
                if (rs.next()) {

                    num = rs.getString(1);
                }
            }
            return num;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String GetEmailBD(int id_per) {
        try {
            String num = null;
            String sql = "SELECT email_per FROM persona "
                    + "WHERE id_per = " + id_per;
            ConnectionPG con = new ConnectionPG();
            try (ResultSet rs = con.Consulta(sql)) {
                if (rs.next()) {

                    num = rs.getString(1);
                }
            }
            return num;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean ExisteNumeroIdentificacionBD(String numero_identifiacion) {
        String sql = "SELECT COUNT(*) FROM persona WHERE numeroidentificacion_per = '" + numero_identifiacion + "'";
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

    public boolean ExisteTelefonoBD(String telefono) {
        String sql = "SELECT COUNT(*) FROM persona WHERE telefono_per = '" + telefono + "'";
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

    public boolean ExisteEmailBD(String email) {
        String sql = "SELECT COUNT(*) FROM persona WHERE email_per = '" + email + "'";
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

    public boolean ExistenDatosBD(String email, String numide, String telefono) {
        boolean exist = false;
        String sql = "SELECT * FROM persona WHERE email_per = '" + email + "' "
                + "OR telefono_per = '" + telefono + "' OR numeroidentificacion_per = '" + numide + "'";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        try {
            if (rs.next()) {
                exist = true;
            } else {
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloLabor.class.getName()).log(Level.SEVERE, null, ex);
            return exist;
        }
        return exist;
    }
}
