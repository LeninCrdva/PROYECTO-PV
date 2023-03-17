package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloDetReserva extends Det_reserva {

    public Det_reserva ShowDetail() {
        Det_reserva det = new Det_reserva();
        String sql = "SELECT id_rha, idReserva_rha, idHabitacion_rha, COUNT(idCliente_rha) FROM Det_reserva WHERE id_rha = " + getId_rha() + " AND idReserva_rha = " + getIdReserva_rha() + "GROUP BY 4";
        ConnectionPG conpg = new ConnectionPG();
        ResultSet rs = conpg.Consulta(sql);
        try {
            while (rs.next()) {
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

    public SQLException crearDetail(Det_reserva detalle) {
        String sql = "INSERT INTO Det_reserva (idReserva_rha, idHabitacion_rha, idCliente_rha)"
                + " VALUES (" + detalle.getIdReserva_rha() + ", " + detalle.getIdHabitacion_rha() + ", " + detalle.getIdCliente_rha() + ")";
        ConnectionPG conpg = new ConnectionPG();
        SQLException ex = conpg.Accion(sql);
        return ex;
    }

    public SQLException EditDetail() {
        String sql = "UPDATE Det_reserva SET idReserva_rha = " + getIdReserva_rha() + ", idHabitacion_rha = " + getIdHabitacion_rha()
                + ", idCliente_rha = " + getIdCliente_rha();
        ConnectionPG conpg = new ConnectionPG();
        SQLException ex = conpg.Accion(sql);
        return ex;
    }

    public SQLException DeleteDetail() {
        String sql = "DELETE FROM Det_reserva WHERE id_rha = " + getId_rha();
        ConnectionPG conpg = new ConnectionPG();
        SQLException ex = conpg.Accion(sql);
        return ex;
    }

    public Habitacion getHab(int idEnc) {
        try {
            Habitacion hb = new Habitacion();
            String sql = "SELECT distinct h.id_hab,h.idTipo_hab, h.numero_hab, h.estado_hab "
                    + "from det_reserva d join habitacion h on h.id_Hab=d.idHabitacion_rha AND "
                    + "idReserva_rha = " + idEnc;
            ConnectionPG conpg = new ConnectionPG();
            ResultSet rs = conpg.Consulta(sql);

            rs.next();
            hb.setId_hab(rs.getInt(1));
            hb.setIdTipo_hab(rs.getInt(2));
            hb.setNumero_hab(rs.getInt(3));
            hb.setEstado_hab(rs.getBoolean(4));
            
            return hb;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDetReserva.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Map<String, Object> detailComplete(int valor) {
        Map<String, Object> detalle = new HashMap<>();
        String sql = "SELECT c.nombre_per, c.apellido_per, "
                + "re.fechaIngreso_res, re.fechaSalida_res, re.fechaSalida_res - re.fechaIngreso_res \"estadia\", "
                + "ha.numero_hab, tp.precio_tha, count(rd.idCliente_rha) from persona c join cliente cli on c.id_per=cli.id_per"
                + " join enc_reserva re on cli.id_cli=re.idCliente_res join det_reserva rd"
                + " on re.id_res = rd.idReserva_rha join habitacion ha on ha.id_hab=rd.idHabitacion_rha"
                + " and re.id_res=" + valor + "join tipo_habitacion tp on tp.id_tha=ha.idTipo_hab group by 1,2,3,4,5,6,7";
        ConnectionPG conpg = new ConnectionPG();
        ResultSet rs = conpg.Consulta(sql);
        try {
            while (rs.next()) {
                detalle.put("nombre", rs.getString(1));
                detalle.put("apellido", rs.getString(2));
                detalle.put("entrada", rs.getDate(3));
                detalle.put("salida", rs.getDate(4));
                detalle.put("noches", rs.getInt(5));
                detalle.put("numero", rs.getInt(6));
                detalle.put("cantidad", rs.getInt(8));
                detalle.put("precio", rs.getDouble(7));
            }
            return detalle;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDetReserva.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
