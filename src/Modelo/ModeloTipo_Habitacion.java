package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloTipo_Habitacion extends Tipo_Habitacion{

    public ModeloTipo_Habitacion() {
    }

    public ModeloTipo_Habitacion(int id_tha, String nombre_tha, int numeroCamas_tha, int capacidad_tha, double precio_tha) {
        super(id_tha, nombre_tha, numeroCamas_tha, capacidad_tha, precio_tha);
    }
    
    public List<Tipo_Habitacion> ListTipoHab(){
        List<Tipo_Habitacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM \"Tipo_Habitacion\"";
        ConnectionPG conpg = new ConnectionPG();
        ResultSet rs = conpg.Consulta(sql);
        try {
            while(rs.next()){
                Tipo_Habitacion tip_hab = new Tipo_Habitacion();
                tip_hab.setId_tha(rs.getInt(1));
                tip_hab.setNombre_tha(rs.getString(2));
                tip_hab.setNumeroCamas_tha(rs.getInt(3));
                tip_hab.setCapacidad_tha(rs.getInt(4));
                tip_hab.setPrecio_tha(rs.getDouble(5));
                lista.add(tip_hab);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloTipo_Habitacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Tipo_Habitacion> ListTipHabSearch(String columna, String dato){
        List<Tipo_Habitacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM \"Tipo_Habitacion\" WHERE " + columna + " = " + dato;
        ConnectionPG conpg = new ConnectionPG();
        ResultSet rs = conpg.Consulta(sql);
        try {
            while(rs.next()){
                Tipo_Habitacion tha = new Tipo_Habitacion();
                tha.setId_tha(rs.getInt(1));
                tha.setNombre_tha(rs.getString(2));
                tha.setNumeroCamas_tha(rs.getInt(3));
                tha.setCapacidad_tha(rs.getInt(4));
                tha.setPrecio_tha(rs.getDouble(5));
                lista.add(tha);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloTipo_Habitacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public SQLException CrearTipoHabitacionBD(){
        String sql = "INSERT INTO \"Tipo_Habitacion\" (id_tha, nombre_tha, numerocamas_tha, capacidad_tha, precio_tha)"
                + " VALUES (" + getId_tha() + ", " + getNombre_tha() + ", " + getNumeroCamas_tha() + ","
                + " " + getCapacidad_tha() + ", " + getPrecio_tha() +")";
        
        ConnectionPG conpg = new ConnectionPG();
        SQLException ex = conpg.Accion(sql);
        return ex;
    }
    
    public SQLException EditTipHab(){
        String sql = "UPDATE \"Tipo_Habitacion\" SET nombre_tha = " + getNombre_tha() + ", numeroCamas_tha = " + getNumeroCamas_tha()
                + ", capacidad_tha = " + getCapacidad_tha() + ", " + getPrecio_tha();
        
        ConnectionPG conpg = new ConnectionPG();
        SQLException ex = conpg.Accion(sql);
        return ex;
    }
    
    public SQLException DeleteTipHab(){
        String sql = "DELETE \"Tipo_Habitacion\" WHERE id_tha = " + getId_tha();
        
        ConnectionPG conpg = new ConnectionPG();
        SQLException ex = conpg.Accion(sql);
        return ex;
    }
}