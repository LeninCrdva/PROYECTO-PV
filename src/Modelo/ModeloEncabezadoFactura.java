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

    public ModeloEncabezadoFactura(int idCliente_enc, int idEmpleado_enc, Date fecha_enc, double total_enc, boolean estado_enc) {
        super(idCliente_enc, idEmpleado_enc, fecha_enc, total_enc, estado_enc);
    }
    
    public List<EncabezadoFactura> listaEncabezadoFactura(){
        try{
            List<EncabezadoFactura> lista=new ArrayList<>();
            String sql="SELECT id_enc,idcliente_enc,idempl_enc,fecha_enc,total_enc,estado_enc FROM enc_factura";
            ConnectionPG con=new ConnectionPG();
            ResultSet result=con.Consulta(sql);
            while (result.next()) {
                EncabezadoFactura enc=new EncabezadoFactura();
                enc.setId_enc(result.getInt("id_enc"));
                enc.setIdCliente_enc(result.getInt("idcliente_enc"));
                enc.setIdEmpleado_enc(result.getInt("idempl_enc"));
                enc.setFecha_enc(result.getDate("fecha_enc"));
                enc.setTotal_enc(result.getDouble("total_enc"));
                enc.setEstado_enc(result.getBoolean("estado_enc"));
                lista.add(enc);
            }
            result.close();
            return lista;
        }catch (Exception ex) {
            Logger.getLogger(ModeloEncabezadoFactura.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public double totalEnc(int idEnc,int idRes){
        String sql="SELECT SUM(costo_det) FROM det_factura WHERE idEncabezado_det="+idEnc;
        String sql2="SELECT total_res FROM enc_reserva WHERE id_res="+idRes;
        try{
            ConnectionPG con=new ConnectionPG();
            ResultSet result=con.Consulta(sql);
            ResultSet result2=con.Consulta(sql2);
            return result.getDouble("SUM(costo_det)")+result2.getDouble("total_res");
        }catch (SQLException ex) {
            return 0;
        }
    }
    public List<EncabezadoFactura> buscarEncabezadoF(String id){
        try{
            List<EncabezadoFactura> lista=new ArrayList<>();
            String sql="SELECT id_enc,idcliente_enc,idempl_enc,fecha_enc,total_enc,estado_enc FROM enc_factura WHERE id_enc LIKE "+id;
            ConnectionPG con=new ConnectionPG();
            ResultSet result=con.Consulta(sql);
            while (result.next()) {
                EncabezadoFactura enc=new EncabezadoFactura();
                enc.setId_enc(result.getInt("id_enc"));
                enc.setIdCliente_enc(result.getInt("idcliente_enc"));
                enc.setIdEmpleado_enc(result.getInt("idempl_enc"));
                enc.setFecha_enc(result.getDate("fecha_enc"));
                enc.setTotal_enc(result.getDouble("total_enc"));
                enc.setEstado_enc(result.getBoolean("estado_enc"));
                lista.add(enc);
            }
            result.close();
            return lista;
        }catch (Exception ex) {
            Logger.getLogger(ModeloEncabezadoFactura.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public int grabarEncabezadoF(){
        int id = 0;
        String sql="WITH new_enc AS (INSERT INTO enc_factura(idcliente_enc,idempl_enc,fecha_enc,total_enc,estado_enc) "
                + "VALUES("+getIdCliente_enc()+","+getIdEmpleado_enc()+",'"+getFecha_enc()+"',"+getTotal_enc()+","+false+") "
                + "RETURNING id_enc) SELECT * FROM new_enc";
        ConnectionPG conpg = new ConnectionPG();
        ResultSet ex = conpg.Consulta(sql);
        try {
            ex.next();
            id = ex.getInt(1);
            ex.close();
        } catch (SQLException ex1) {
            try {
                Logger.getLogger(ModeloEncReserva.class.getName()).log(Level.SEVERE, null, ex1);
                ex.close();
            } catch (SQLException ex2) {
                Logger.getLogger(ModeloEncReserva.class.getName()).log(Level.SEVERE, null, ex2);
            }
        }
        return id;
    }
    public SQLException eliminarEncabeazadoF(String id){
        ConnectionPG con=new ConnectionPG();
        String sql="DELETE FROM enc_factura WHERE id_enc='"+id+"';";
        SQLException ex=con.Accion(sql);
        return ex;   
    }
    public SQLException modificarEncabezadoF(String valor){
        ConnectionPG con=new ConnectionPG();
        String sql="UPDATE enc_factura SET idcliente_enc="+getIdCliente_enc()+",idempl_enc="+getIdEmpleado_enc()+",fecha_enc='"+getFecha_enc()+""
                + "',total_enc="+getTotal_enc()+",estado_enc='"+isEstado_enc()+"' WHERE id_enc="+valor+"";
        SQLException ex=con.Accion(sql);
        return ex;
    }
//    public boolean buscaEncabezadoF(String id) throws SQLException{
//        boolean test=false;
//        ConnectionPG con=new ConnectionPG();
//        String sql="SELECT id_enc FROM enc_factura WHERE id_enc="+id+";";
//        ResultSet re=con.Consulta(sql);
//        if (con.Consulta(sql).wasNull()) {
//            test=false;
//        }else{test=true;}
//        return test;
//    }
}
