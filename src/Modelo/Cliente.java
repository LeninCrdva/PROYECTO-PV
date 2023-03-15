/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author ERIKA
 */
public class Cliente extends Persona {

    private int id_cli;
    private int id_per;
    private int id_tip;

    public Cliente() {
        super();
    }

    public Cliente(int id_cli, int id_per, int id_tip) {
        this.id_cli = id_cli;
        this.id_per = id_per;
        this.id_tip = id_tip;
    }

    public Cliente(int id_cli, int id_per, String numeroIdentificacion, String nombre_per, String apellido_per) {
        super(numeroIdentificacion, nombre_per, apellido_per);
        this.id_cli = id_cli;
        this.id_per = id_per;
    }

    public Cliente(int id_cli, int id_per, int id_tip, String numeroidentificacion_per, String nombre_per, String apellido_per, int tipo_doc, String direccion_per, String telefono_per, String email_per, Date fecha_nac, String genero_per) {
        super(id_per, numeroidentificacion_per, nombre_per, apellido_per, tipo_doc, direccion_per, telefono_per, email_per, fecha_nac, genero_per);
        this.id_cli = id_cli;
        this.id_per = id_per;
        this.id_tip = id_tip;
    }

    public int getId_cli() {
        return id_cli;
    }

    public void setId_cli(int id_cli) {
        this.id_cli = id_cli;
    }

    @Override
    public int getId_per() {
        return id_per;
    }

    @Override
    public void setId_per(int id_per) {
        this.id_per = id_per;
    }

    public int getId_tip() {
        return id_tip;
    }

    public void setId_tip(int id_tip) {
        this.id_tip = id_tip;
    }
    
    
    
    
    
    
    
    
            
    
    
    
    
    
    
    
    
    
}
