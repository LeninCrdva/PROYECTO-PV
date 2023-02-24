/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.List;
import java.sql.Date;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Andrea
 */
public class ModeloEncabezadoFactura extends EncabezadoFactura{

    public ModeloEncabezadoFactura() {
    }

    public ModeloEncabezadoFactura(int id_enc, int idCliente_enc, int idEmpleado_enc, Date fecha_enc, double total_enc, boolean estado_enc) {
        super(id_enc, idCliente_enc, idEmpleado_enc, fecha_enc, total_enc, estado_enc);
    }
    
    public List<EncabezadoFactura> listaEncabezadoFactura(){
        try{
            List<EncabezadoFactura> lista=new ArrayList<>();
            String sql="SELECT id_enc,idcliente_enc,idempl_enc,fecha_enc,total_enc,estado_enc FROM enc_factura";
            ConnectionPG con=new ConnectionPG();
            ResultSet result=con.Consulta(sql);
            while (result.next()) {
                EncabezadoFactura enc=new EncabezadoFactura(result.getInt("id_enc"),result.getInt("idcliente_enc"),result.getInt("idempl_enc"),result.getDate("fecha_enc"),result.getDouble("total_enc"),result.getBoolean("estado_enc"));
                lista.add(enc);
            }
            result.close();
            return lista;
        }catch (Exception ex) {
            Logger.getLogger(ModeloEncabezadoFactura.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<EncabezadoFactura> buscarEncabezadoF(String id){
        try{
            List<EncabezadoFactura> lista=new ArrayList<>();
            String sql="SELECT id_enc,idcliente_enc,idempl_enc,fecha_enc,total_enc,estado_enc FROM enc_factura WHERE id_enc LIKE "+id;
            ConnectionPG con=new ConnectionPG();
            ResultSet result=con.Consulta(sql);
            while (result.next()) {
                EncabezadoFactura enc=new EncabezadoFactura(result.getInt("id_enc"),result.getInt("idcliente_enc"),result.getInt("idempl_enc"),result.getDate("fecha_enc"),result.getDouble("total_enc"),result.getBoolean("estado_enc"));
                lista.add(enc);
            }
            result.close();
            return lista;
        }catch (Exception ex) {
            Logger.getLogger(ModeloEncabezadoFactura.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public SQLException grabarEncabezadoF(){
        ConnectionPG con=new ConnectionPG();
        String sql="INSERT INTO enc_factura(id_enc,idcliente_enc,idempl_enc,fecha_enc,total_enc,estado_enc) "
                + "VALUES("+getId_enc()+","+getIdCliente_enc()+","+getIdEmpleado_enc()+",'"+getFecha_enc()+"',"+getTotal_enc()+",'"+isEstado_enc()+"')";
        SQLException ex=con.Accion(sql);
        return ex;
    }
    public SQLException eliminarEncabeazadoF(String id){
        ConnectionPG con=new ConnectionPG();
        String sql="DELETE FROM enc_factura WHERE id_enc='"+id+"';";
        SQLException ex=con.Accion(sql);
        return ex;   
    }
    public SQLException modificarEncabezadoF(){
        ConnectionPG con=new ConnectionPG();
        String sql="UPDATE enc_factura SET idcliente_enc="+getIdCliente_enc()+",idempl_enc="+getIdEmpleado_enc()+",fecha_enc='"+getFecha_enc()+""
                + "',total_enc="+getTotal_enc()+",estado_enc='"+isEstado_enc()+" WHERE id_enc="+getId_enc()+";";
        SQLException ex=con.Accion(sql);
        return ex;
    }
    public boolean buscaEncabezadoF(String id) throws SQLException{
        boolean test=false;
        ConnectionPG con=new ConnectionPG();
        String sql="SELECT id_enc FROM enc_factura WHERE id_enc="+id+";";
        ResultSet re=con.Consulta(sql);
        if (con.Consulta(sql).wasNull()) {
            test=false;
        }else{test=true;}
        return test;
    }
}
