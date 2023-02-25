package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloDetReserva extends Det_reserva {
    
    public Det_reserva ShowDetail(){
        Det_reserva det = new Det_reserva();
        String sql = "SELECT * FROM Det_reserva WHERE id_rha = " + getId_rha() + " AND idReserva_rha = " + getIdReserva_rha();
        ConnectionPG conpg = new ConnectionPG();
        ResultSet rs = conpg.Consulta(sql);
        try {
            while(rs.next()){
                det.setId_rha(rs.getInt(1));
                det.setIdReserva_rha(rs.getInt(2));
                det.setIdHabitacion_rha(rs.getInt(3));
                det.setIdCliente_rha(rs.getInt(4));
            }
            return det;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDetReserva.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public SQLException EditDetail(){
        String sql = "UPDATE Det_reserva SET idReserva_rha = " + getIdReserva_rha() + ", idHabitacion_rha = " + getIdHabitacion_rha()
                + ", idCliente_rha = " + getIdCliente_rha();
        ConnectionPG conpg = new ConnectionPG();
        SQLException ex = conpg.Accion(sql);
        return ex;
    }
    
    public SQLException DeleteDetail(){
        String sql = "DELETE FROM Det_reserva WHERE id_rha = " + getId_rha();
        ConnectionPG conpg = new ConnectionPG();
        SQLException ex = conpg.Accion(sql);
        return ex;
    }
}
