/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloDetalleFactura;
import Modelo.ModeloEncabezadoFactura;
import Vista.VistaFactura;
import Vista.VistaPrincipal;

/**
 *
 * @author Andrea
 */
public class ControladorPrincipal {
    VistaPrincipal vp;

    public ControladorPrincipal(VistaPrincipal vp) {
        this.vp = vp;
        vp.setLocationRelativeTo(null);
        vp.setVisible(true);
    }
    public void IniciaControlVP(){
        vp.getBtnFactura().addActionListener(l->CrudFactura());
    }
    private void CrudFactura(){
        VistaFactura vistaF=new VistaFactura();
        ModeloEncabezadoFactura modeloE=new ModeloEncabezadoFactura();
        ModeloDetalleFactura modeloD=new ModeloDetalleFactura();
        ControladorFactura control=new ControladorFactura(modeloE,modeloD,vistaF);
        vp.getPanelPrincipal().add(vistaF);
        
        control.iniciarControl();
    }
}
