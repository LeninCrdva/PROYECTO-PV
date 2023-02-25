package Controlador;

import Modelo.ModeloDetReserva;
import Modelo.ModeloEncReserva;
import Modelo.ModeloHabitación;
import Modelo.ModeloTipo_Habitacion;
import Vista.VistaEmpleados;
import Vista.VistaHabitacion;
import Vista.VistaLabor;
import Vista.VistaPrincipal;
import Vista.VistaReserva;

public class ControladorPrincipal {
    VistaPrincipal vistaPrincipal;

    public ControladorPrincipal(VistaPrincipal vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        vistaPrincipal.setVisible(true);
        vistaPrincipal.setLocationRelativeTo(null);
    }

    public void iniciaControl(){
        vistaPrincipal.getBtnEmpleado().addActionListener(l -> crudEmpleado());
        vistaPrincipal.getBtnLabor().addActionListener(l -> crudLabor());
    }
    
    private void crudEmpleado(){
        ModeloEmpleado modelo = new ModeloEmpleado();
        VistaEmpleados vista = new VistaEmpleados();
        
        vistaPrincipal.getPanelPrincipal().add(vista);
        
        ControlEmpleado control = new ControlEmpleado(modelo, vista);
        control.iniciaControl();
    }
    
    private void crudLabor(){
        ModeloLabor modelo = new ModeloLabor();
        VistaLabor vista = new VistaLabor();
        
        vistaPrincipal.getPanelPrincipal().add(vista);
        
        ControladorLabor control = new ControladorLabor(modelo, vista);
        control.iniciaControl();
    }
    
    private void crudHabitacion(){
        ModeloHabitación modelohab = new ModeloHabitación();
        ModeloTipo_Habitacion modelotip = new ModeloTipo_Habitacion();
        VistaHabitacion vista = new VistaHabitacion();
        
        vistaPrincipal.getPanelPrincipal().add(vista);
    }
    
    private void crudReserva(){
        ModeloEncReserva modeloenc = new ModeloEncReserva();
        ModeloDetReserva modelodet = new ModeloDetReserva();
        VistaReserva vista = new VistaReserva();
        
        vistaPrincipal.getPanelPrincipal().add(vista);
    }
}
