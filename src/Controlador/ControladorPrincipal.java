/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloCliente;
import Modelo.ModeloPersona;
import Modelo.ModeloTipoCliente;
import Vista.VistaClientes;
import Vista.VistaPrincipal;
import Vista.VistaTipoCliente;

/**
 *
 * @author ERIKA
 */
public class ControladorPrincipal {
    VistaPrincipal vistaPrincipal;

    public ControladorPrincipal(VistaPrincipal vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        vistaPrincipal.setVisible(true);
        vistaPrincipal.setLocationRelativeTo(null);
    }

    public void iniciaControl(){
        vistaPrincipal.getBtnCliente().addActionListener(l -> crudCliente());
     

    }
    
    private void crudCliente(){
        ModeloCliente modelo = new ModeloCliente();
        ModeloPersona modelop = new ModeloPersona();
        ModeloTipoCliente modelol = new ModeloTipoCliente();
       
        VistaClientes vista = new VistaClientes();
        
        vistaPrincipal.getPanelPrincipal().add(vista);
        
      
        
        ControladorCliente control =new ControladorCliente(modelop, modelo, vista, modelol);
        control.IniciarControl();
    }
    
    private void crudTipoCliente(){
        ModeloTipoCliente modelo = new ModeloTipoCliente();
        VistaTipoCliente vista = new VistaTipoCliente();
        
        vistaPrincipal.getPanelPrincipal().add(vista);
        
        
    }
    
  

    
}
