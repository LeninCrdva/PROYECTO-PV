package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloEncReserva extends Enc_reserva{

    public ModeloEncReserva() {
    }

    public ModeloEncReserva(int id_res, int idCliente_res, Date fechaIngreso_res, Date fechaSalida_res, double total_res, boolean estado_res) {
        super(id_res, idCliente_res, fechaIngreso_res, fechaSalida_res, total_res, estado_res);
    }
    
    public List<Enc_reserva> ListEnc(){
        List<Enc_reserva> lista = new ArrayList<>();
        String sql = "SELECT * FROM \"Enc_reserva\"";
        ConnectionPG conpg = new ConnectionPG();
        ResultSet rs = conpg.Consulta(sql);
        try {
            while(rs.next()){
                Enc_reserva encReserva = new Enc_reserva();
                encReserva.setId_res(rs.getInt(1));
                encReserva.setIdCliente_res(rs.getInt(2));
                encReserva.setFechaIngreso_res(rs.getDate(3));
                encReserva.setFechaSalida_res(rs.getDate(4));
                encReserva.setTotal_res(rs.getDouble(5));
                encReserva.setEstado_res(rs.getBoolean(6));
                lista.add(encReserva);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloEncReserva.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Enc_reserva> ListEncSearch(){
        List<Enc_reserva> lista = new ArrayList<>();
        String sql = "SELECT * FROM \"Enc_reserva\" WHERE idCliente_res = " + getIdCliente_res();
        ConnectionPG conpg = new ConnectionPG();
        ResultSet rs = conpg.Consulta(sql);
        try {
            while(rs.next()){
                Enc_reserva encReserva = new Enc_reserva();
                encReserva.setId_res(rs.getInt(1));
                encReserva.setIdCliente_res(rs.getInt(2));
                encReserva.setFechaIngreso_res(rs.getDate(3));
                encReserva.setFechaSalida_res(rs.getDate(4));
                encReserva.setTotal_res(rs.getDouble(5));
                encReserva.setEstado_res(rs.getBoolean(6));
                lista.add(encReserva);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloEncReserva.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public SQLException CrearReserva(Det_reserva detalle){
        String sql = "WITH new_cliente AS("
                + " INSERT INTO Enc_reserva(id_res, idCliente_res, fechaIngreso_res, fechaSalida_res, total_res, estado_res)"
                + " VALUES ("+ getId_res() +", "+ getIdCliente_res() + ", '" + getFechaIngreso_res() + "', '" + getFechaSalida_res() +"', "
                + getTotal_res() +", " + false + ")"
                + "RETURNING id_res)"
                + "INSERT INTO Det_reserva (id_rha, idReserva_rha, idHabitacion_rha, idCliente_rha)"
                + " VALUES (" + detalle.getId_rha() + ", " + detalle.getIdReserva_rha() + ", "+ detalle.getIdHabitacion_rha() + ", " + detalle.getIdCliente_rha() +")";
        ConnectionPG conpg = new ConnectionPG();
        SQLException ex = conpg.Accion(sql);
        return ex;
    }
    
    public SQLException EditarReserva(){
        String sql = "UPDATE Enc_reserva SET idCliente_res = " + getIdCliente_res() + ", fechaIngreso_res = '" + getFechaIngreso_res() +"', "
                + "fechaSalida_res = '" + getFechaSalida_res() + "', total_res = " + getTotal_res() + ", estado_res = " + isEstado_res();
        ConnectionPG conpg = new ConnectionPG();
        SQLException ex = conpg.Accion(sql);
        return ex;
    }
    
    public SQLException EliminarReserva(){
        String sql = "DELETE FROM Enc_reserva WHERE id_res = " + getId_res() ;
        ConnectionPG conpg = new ConnectionPG();
        SQLException ex = conpg.Accion(sql);
        return ex;
    }
}