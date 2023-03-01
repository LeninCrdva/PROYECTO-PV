package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPG {

    Connection con;
    String CadenaCon = "jdbc:postgresql://localhost:5432/OrquideaHotel";
    String UsuarioPG = "postgres";
    String PasswordPG = "1234";

    public ConnectionPG() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionPG.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection(CadenaCon, UsuarioPG, PasswordPG);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionPG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet Consulta(String sql) {
        try {
            Statement st = con.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionPG.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionPG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public SQLException Accion(String sql) {
        try {
            try (Statement st = con.createStatement()) {
                st.execute(sql);
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionPG.class.getName()).log(Level.SEVERE, null, ex);
            return ex;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionPG.class.getName()).log(Level.SEVERE, null, ex);
                    return ex;
                }
            }
        }
    }

    public Connection getCon() {
        return con;
    }
}
