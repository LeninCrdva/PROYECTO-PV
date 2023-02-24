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
 * @author Andrea
 */
public class ModeloDetalleFactura extends DetalleFactura{

    public ModeloDetalleFactura() {
    }

    public ModeloDetalleFactura(int id_det, int idEncabezado_det, int idServicio_det, int idReserva_det, String observaciones_det, double costo_det) {
        super(id_det, idEncabezado_det, idServicio_det, idReserva_det, observaciones_det, costo_det);
    }
    public List<DetalleFactura> listaDetalleFactura(){
        try{
            List<DetalleFactura> lista=new ArrayList<>();
            String sql="SELECT id_det,idencabezado_det,idservicio_det,idreserva_enc,observaciones_det,costo_det FROM det_factura";
            ConnectionPG con=new ConnectionPG();
            ResultSet result=con.Consulta(sql);
            while (result.next()) {
                DetalleFactura enc=new DetalleFactura(result.getInt("id_det"),result.getInt("idencabezado_det"),result.getInt("idservicio_det"),result.getInt("idreserva_enc"),result.getString("observaciones_det"),result.getDouble("costo_det"));
                lista.add(enc);
            }
            result.close();
            return lista;
        }catch (Exception ex) {
            Logger.getLogger(ModeloDetalleFactura.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<DetalleFactura> buscarDetalleF(String id){
        try{
            List<DetalleFactura> lista=new ArrayList<>();
            String sql="SELECT id_det,idencabezado_det,idservicio_det,idreserva_enc,observaciones_det,costo_det FROM det_factura WHERE id_det LIKE "+id;
            ConnectionPG con=new ConnectionPG();
            ResultSet result=con.Consulta(sql);
            while (result.next()) {
                DetalleFactura enc=new DetalleFactura(result.getInt("id_det"),result.getInt("idencabezado_det"),result.getInt("idservicio_det"),result.getInt("idreserva_enc"),result.getString("observaciones_det"),result.getDouble("costo_det"));
                lista.add(enc);
            }
            result.close();
            return lista;
        }catch (Exception ex) {
            Logger.getLogger(ModeloDetalleFactura.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public SQLException grabarDetalleF(){
        ConnectionPG con=new ConnectionPG();
        String sql="INSERT INTO det_factura(id_det,idencabezado_det,idservicio_det,idreserva_enc,observaciones_det,costo_det) "
                + "VALUES("+getId_det()+","+getIdEncabezado_det()+","+getIdServicio_det()+","+getIdReserva_det()+",'"+getObservaciones_det()+"',"+getCosto_det()+")";
        SQLException ex=con.Accion(sql);
        return ex;
    }
    public SQLException eliminarDetalleF(String id){
        ConnectionPG con=new ConnectionPG();
        String sql="DELETE FROM det_factura WHERE id_det="+id+";";
        SQLException ex=con.Accion(sql);
        return ex;   
    }
    public SQLException modificarDetalleF(){
        ConnectionPG con=new ConnectionPG();
        String sql="UPDATE det_factura SET idencabezado_det="+getIdEncabezado_det()+",idservicio_det="+getIdServicio_det()+",idreserva_enc="+getIdReserva_det()+""
                + ",observaciones_det='"+getObservaciones_det()+"',costo_det="+getCosto_det()+" WHERE id_det="+getId_det()+";";
        SQLException ex=con.Accion(sql);
        return ex;
    }
    public boolean buscaDetalleF(String id) throws SQLException{
        boolean test=false;
        ConnectionPG con=new ConnectionPG();
        String sql="SELECT id_det FROM det_factura WHERE id_det="+id+";";
        ResultSet re=con.Consulta(sql);
        if (con.Consulta(sql).wasNull()) {
            test=false;
        }else{test=true;}
        return test;
    }
}
