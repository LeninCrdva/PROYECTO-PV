/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.VistaClientes;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ModeloCliente extends Cliente{

    public ModeloCliente() {
    }

    public ModeloCliente(int id_cli, int id_per, int id_tip) {
        super(id_cli, id_per, id_tip);
    }

    public ModeloCliente(int id_per, String numeroidentificacion_per, String nombre_per, String apellido_per, int tipo_doc, String direccion_per, String telefono_per, String email_per, Date fecha_nac, String genero_per) {
        super(id_per, numeroidentificacion_per, nombre_per, apellido_per, tipo_doc, direccion_per, telefono_per, email_per, fecha_nac, genero_per);
    }

    
    public List<Cliente> listarclientes (){
        List<Cliente> lista =new ArrayList<>();
        String sql = "";
        ConnectionPG con =new  ConnectionPG();
        ResultSet rs =con.Consulta(sql);
        
        
        try {
            while(rs.next()){
                Cliente clie =new Cliente();
                
            }
            
            
        } catch (SQLException ie) {
            System.err.println(ie);
        }
        return lista;
        
    }
    
    public SQLException InsertarCliente(){
        
        String sql = "INSERT INTO cliente(id_cli,id_per,id_tip) VALUES"
                + "("+getId_cli()+","+getId_per()+","+getId_tip()+")";
        
        ConnectionPG con=new ConnectionPG();
        SQLException op = con.Accion(sql);
        return   op;
    }
    public int ObtenerID(){
    int id_cli=0;
    String sql ="SELECT max(id_cli) from cliente ";
    ConnectionPG con = new ConnectionPG();
    ResultSet rs = con.Consulta(sql);
        try {
            if (rs.next()) {
                id_cli = rs.getInt(1);
                
            }
            rs.close();
            return  id_cli;
        } catch (SQLException e) {
            return id_cli;
        }
    }
    
    
    
}
