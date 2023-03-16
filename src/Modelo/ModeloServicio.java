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

/**
 *
 * @author ERIKA
 */
public class ModeloServicio extends  Servicio{

    public ModeloServicio() {
    }

    public ModeloServicio(int Id_ser, String nombre_ser, String descripcion_ser, double precio_ser) {
        super(Id_ser, nombre_ser, descripcion_ser, precio_ser);
    }
  
    
    
    
    public List <Servicio> ListarServiciosBD(){
    List<Servicio> lista =new ArrayList<>();
    
    String sql ="select id_ser,nombre_ser,descripcion_ser,precio_ser from servicio";
    ConnectionPG con = new ConnectionPG();
    ResultSet rs=con.Consulta(sql);
    
        try {
            while (rs.next()) {                
                Servicio ser = new Servicio();
                
                ser.setId_ser(1);
                ser.setNombre_ser(rs.getString(2));
                ser.setDescripcion_ser(rs.getString(3));
                ser.setPrecio_ser(rs.getDouble(4));
                lista.add(ser);
                
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return lista;
    
    }
    public int ObtenerID(){
    int id_ser =0;
    String sql ="select max(id_ser)from servicio";
    ConnectionPG con = new ConnectionPG();
    ResultSet rs=con.Consulta(sql);
        try {
            if (rs.next()) {
                id_ser= rs.getInt(1);
            }
            rs.close();
            return id_ser;
        } catch (SQLException e) {
            System.err.println(e);
            return id_ser;
        }
    }
    
    
    public  List<Servicio> llenarCombo(){
    List<Servicio> lista = new ArrayList<>();
    String sql ="select id_ser, nombre_ser from servicio";
    ConnectionPG con =new ConnectionPG();
    ResultSet rs= con.Consulta(sql);
        try {
            while (rs.next()) {                
                Servicio ser =new Servicio();
                
                ser.setId_ser(rs.getInt(1));
                ser.setNombre_ser(rs.getString(1));
                 lista.add(ser);
                 
            }
            rs.close();
            return lista;
        } catch (SQLException e) {
            return null;
        }
    
    }
    
    public int ConsulaIDBD(String nombre ){
        int id_ser =0 ;
        String sql ="select id_ser from servicio where nombre_ser LIKE  '%"+nombre+"%'";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs= con.Consulta(sql);
        try {
            if (rs.next()) {
                id_ser =rs.getInt(1);
                
            }
            rs.close();
            return id_ser;
        } catch (SQLException e) {
            System.err.println(e);
            return id_ser;
        }
    
    }
     
    public SQLException InsertarServicioBD(){
    String sql="INSERT INTO  servicio (id_ser,nombre_ser,descripcion_ser,precio_ser)"
            +"VALUES ("+getId_ser()+",'"+getNombre_ser()+"','"+getDescripcion_ser()+"',"+getPrecio_ser()+")";
    ConnectionPG con =new ConnectionPG();
    SQLException e = con.Accion(sql);
    return e;
    
    }
    public  List<Servicio> BuscarServicioBD(String bus ){
        List <Servicio > lissta = new ArrayList<>();
        String sql ="SELECT  id_ser,nombre_ser,descripcion_ser,precio_ser from servicio where "
                + "nombre_ser LIKE '%"+bus+"%'";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs =con.Consulta(sql);
        try {
            while (rs.next()) {                
                Servicio ser =new Servicio();
                ser.setId_ser(rs.getInt(1));
                ser.setNombre_ser(rs.getString(2));
                ser.setDescripcion_ser(rs.getString(3));
                ser.setPrecio_ser(rs.getDouble(4));
                
                lissta.add(ser);
            }
            return lissta;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    
    }
    public  boolean  ExisteNombreServcioBD(String nombre_ser){
    String sql = "SELECT COUNT (*) FROM servicio WHERE nombre_ser = '"+nombre_ser+"'";
    ConnectionPG con =new ConnectionPG();
    ResultSet rs =con.Consulta(sql);
        try {
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
                
            }else {
            return false;
            
            }
        } catch (SQLException e) {
            Logger.getLogger(ModeloServicio.class.getName()).log(Level.SEVERE,null,e);
            return true ;
        }
    }
    
    public  SQLException ModificarServicioBD(int id_ser){
    String sql ="UPDATE  servicio SET nombre_ser = '"+getNombre_ser()+"', descripcion_ser = '"+getDescripcion_ser()
            +"',precio_ser = "+getPrecio_ser()+" WHERE  id_ser = "+ id_ser;
    ConnectionPG con = new ConnectionPG();
    SQLException ex =con.Accion(sql);
    return ex ;
    }
    
    
    public  SQLException EliminarSevicioBD(int id_ser){
        String sql = "DELETE FROM  servicio  WHERE id_ser = " + id_ser;
        ConnectionPG con= new ConnectionPG();
        SQLException ex =con.Accion(sql);
        return ex;
        
    }
    
    
    
    
    
    
}
