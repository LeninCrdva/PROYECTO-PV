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

/**
 *
 * @author santi
 */
public class ModeloCuenta extends Cuenta {

    public ModeloCuenta() {
    }

    public ModeloCuenta(int id_cue, String username_cue, String password_cue) {
        super(id_cue, username_cue, password_cue);
    }
    
    public List<Cuenta> ListarCuentas() {
        List<Cuenta> lista = new ArrayList<>();
        String sql = "";
        ConnectionPG con = new ConnectionPG();
        ResultSet rs = con.Consulta(sql);
        
        try {
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                
            }
        } catch (SQLException e) {
        }
        return lista;
    }
    //Stand by
}
