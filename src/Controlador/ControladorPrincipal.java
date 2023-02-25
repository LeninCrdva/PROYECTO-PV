package Controlador;

import Modelo.ModeloDetReserva;
import Modelo.ModeloEmpleado;
import Modelo.ModeloEncReserva;
import Modelo.ModeloHabitación;
import Modelo.ModeloLabor;
import Modelo.ModeloPersona;
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
        vistaPrincipal.getBtnHabitacion().addActionListener(l-> crudHabitacion());
        vistaPrincipal.getBtnReserva().addActionListener(l -> crudReserva());
    }
    
    private void crudEmpleado(){
        ModeloEmpleado modelo = new ModeloEmpleado();
        ModeloPersona modelop = new ModeloPersona();
        VistaEmpleados vista = new VistaEmpleados();
        
        vistaPrincipal.getPanelPrincipal().add(vista);
        
        ControladorEmpleado control = new ControladorEmpleado(modelop, modelo, vista);
        control.IniciarControl();
    }
    
    private void crudLabor(){
        ModeloLabor modelo = new ModeloLabor();
        VistaLabor vista = new VistaLabor();
        
        vistaPrincipal.getPanelPrincipal().add(vista);
        
        ControladorLabor control = new ControladorLabor(vista, modelo);
        control.IniciarControl();
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