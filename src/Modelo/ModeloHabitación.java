package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloHabitación extends Habitacion{

    public ModeloHabitación() {
    }

    public ModeloHabitación(int id_hab, int idTipo_hab, int numero_hab, boolean estado_hab) {
        super(id_hab, idTipo_hab, numero_hab, estado_hab);
    }
    
    public List<Habitacion> ListHabitacion() {
        List<Habitacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM Habitacion";
        
        ConnectionPG conpq = new ConnectionPG();
        ResultSet rs = conpq.Consulta(sql);
        try {
            while (rs.next()) {
                Habitacion hab = new Habitacion();
                hab.setId_hab(rs.getInt(1));
                hab.setIdTipo_hab(rs.getInt(2));
                hab.setNumero_hab(rs.getInt(3));
                hab.setEstado_hab(rs.getBoolean(4));
                lista.add(hab);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Habitacion> SearchListHabitacion() {
        List<Habitacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM Habitacion WHERE numero_hab = " + getNumero_hab();
        
        ConnectionPG conpq = new ConnectionPG();
        ResultSet rs = conpq.Consulta(sql);
        try {
            while (rs.next()) {
                Habitacion hab = new Habitacion();
                hab.setId_hab(rs.getInt(1));
                hab.setIdTipo_hab(rs.getInt(2));
                hab.setNumero_hab(rs.getInt(3));
                hab.setEstado_hab(rs.getBoolean(4));
                lista.add(hab);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public SQLException CrearHabitacionDB() {
        String sql = "INSERT INTO Habitacion (idTipo_hab, numero_hab, estado_hab) VALUES (" +
                getIdTipo_hab() + ", "+ getNumero_hab() + ", " + isEstado_hab() +")";

        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(sql);
        return ex;
    }

    public SQLException EditHabitacionDB() {
        String sql = "UPDATE Habitacion SET idTipo_hab = " + getIdTipo_hab() +", "
                + " numero_hab = " + getNumero_hab()+ ", estado_hab = " + isEstado_hab() +" "
                + "WHERE id_hab = " + getId_hab() + "";
        
        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(sql);
        return ex;
    }
    
    public SQLException DeletePhisicHabitacion(){
        String sql = "DELETE FROM Habitacion WHERE id_hab = " + getId_hab()+ "";
        
        ConnectionPG con = new ConnectionPG();
        SQLException ex = con.Accion(sql);
        return ex;
    }
    
    public int existHab(){
        int cant = 0;
        String sql = "Select count(numero_hab) from habitacion where numero_hab = " + getNumero_hab();
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        try{
            rs.next();
            cant = rs.getInt(1);
            rs.close();
            return cant;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            return cant;
        }
    }
    
    public int cancelDelete(){
        int cant = 0;
        String sql = "Select count(idHabitacion_rha) from det_reserva where idHabitacion_rha = " + getId_hab();
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        try{
            rs.next();
            cant = rs.getInt(1);
            rs.close();
            return cant;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            return cant;
        }
    }
}
