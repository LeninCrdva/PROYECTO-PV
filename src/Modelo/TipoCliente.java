/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author ERIKA
 */
public class TipoCliente {
    private int id_tip;
    private String nombre_tip;

    public TipoCliente() {
    }

    public TipoCliente(int id_tip, String nombre_tip) {
        this.id_tip = id_tip;
        this.nombre_tip = nombre_tip;
    }

    public int getId_tip() {
        return id_tip;
    }

    public void setId_tip(int id_tip) {
        this.id_tip = id_tip;
    }

    public String getNombre_tip() {
        return nombre_tip;
    }

    public void setNombre_tip(String nombre_tip) {
        this.nombre_tip = nombre_tip;
    }

    @Override
    public String toString() {
        return nombre_tip ;
    }

  
    
}
