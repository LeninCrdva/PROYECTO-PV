package Controlador;

import Modelo.*;
import Vista.*;

public class ControladorPrincipal {
    VistaPrincipal vistaPrincipal;

    public ControladorPrincipal(VistaPrincipal vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        vistaPrincipal.setVisible(true);
        vistaPrincipal.setLocationRelativeTo(null);
    }

    public void iniciaControl(){
        vistaPrincipal.getBtnEmpleado().addActionListener(l -> crudEmpleado());
        vistaPrincipal.getBtnCliente().addActionListener(l -> crudCliente());
        vistaPrincipal.getBtnLabor().addActionListener(l -> crudLabor());
        vistaPrincipal.getBtnHabitacion().addActionListener(l-> crudHabitacion());
        vistaPrincipal.getBtnReserva().addActionListener(l -> crudReserva());
        vistaPrincipal.getBtnFactura().addActionListener(l -> CrudFactura());
        vistaPrincipal.getBtnCuenta().addActionListener(l -> crudCuenta());
    }
    
    private void crudEmpleado(){
        ModeloEmpleado modelo = new ModeloEmpleado();
        ModeloPersona modelop = new ModeloPersona();
        ModeloLabor modelol = new ModeloLabor();
        ModeloTipoDocumento modelot = new ModeloTipoDocumento();
        VistaEmpleados vista = new VistaEmpleados();
        
        vistaPrincipal.getPanelPrincipal().add(vista);
        
        ControladorEmpleado control = new ControladorEmpleado(modelop, modelo, vista, modelol, modelot);
        control.IniciarControl();
    }
    
    private void crudLabor(){
        ModeloLabor modelo = new ModeloLabor();
        VistaLabor vista = new VistaLabor();
        
        vistaPrincipal.getPanelPrincipal().add(vista);
        
        ControladorLabor control = new ControladorLabor(vista, modelo);
        control.IniciarControl();
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
    
    private void crudHabitacion(){
        ModeloHabitación modelohab = new ModeloHabitación();
        ModeloTipo_Habitacion modelotip = new ModeloTipo_Habitacion();
        VistaHabitacion vista = new VistaHabitacion();
        
        vistaPrincipal.getPanelPrincipal().add(vista);
        
        ControladorHabitacion control = new ControladorHabitacion(modelohab, modelotip, vista);
        control.iniciarControl();
    }
    
    private void crudReserva(){
        ModeloEncReserva modeloenc = new ModeloEncReserva();
        ModeloDetReserva modelodet = new ModeloDetReserva();
        VistaReserva vista = new VistaReserva();
        
        vistaPrincipal.getPanelPrincipal().add(vista);
        
        ControladorReserva control = new ControladorReserva(modeloenc, modelodet, vista);
        control.iniciarControl();
    }
    
    private void CrudFactura(){
        VistaFactura vistaF=new VistaFactura();
        ModeloEncabezadoFactura modeloE=new ModeloEncabezadoFactura();
        ModeloDetalleFactura modeloD=new ModeloDetalleFactura();
        
        ControladorFactura control=new ControladorFactura(modeloE,modeloD,vistaF);
        
        vistaPrincipal.getPanelPrincipal().add(vistaF);
        
        control.iniciarControl();
    }
    
    private void crudCuenta(){
        VistaCuenta vista = new VistaCuenta();
        ModeloCuenta modeloCuenta = new ModeloCuenta();
        
        ControladorCuenta control = new ControladorCuenta(modeloCuenta, vista);
        
        vistaPrincipal.getPanelPrincipal().add(vista);
        
        control.AbreDialogo(0);
    }
}