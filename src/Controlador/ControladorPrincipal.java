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

    public void iniciaControl() {
        vistaPrincipal.getBtnEmpleado().addActionListener(l -> crudEmpleado());
        vistaPrincipal.getBtnCliente().addActionListener(l -> crudCliente());
        vistaPrincipal.getBtnLabor().addActionListener(l -> crudLabor());
        vistaPrincipal.getBtnHabitacion().addActionListener(l -> crudHabitacion());
        vistaPrincipal.getBtnReserva().addActionListener(l -> crudReserva());
        vistaPrincipal.getBtnFactura().addActionListener(l -> CrudFactura());
        vistaPrincipal.getBtnCuenta().addActionListener(l -> crudCuenta());
        vistaPrincipal.getBtnServicio().addActionListener(l -> CrudServicios());
    }

    private void crudEmpleado() {
        ModeloEmpleado modelo = new ModeloEmpleado();
        ModeloPersona modelop = new ModeloPersona();
        ModeloLabor modelol = new ModeloLabor();
        ModeloTipoDocumento modelot = new ModeloTipoDocumento();
        VistaEmpleados vista = new VistaEmpleados();
        /*Add de dos vistas más vd y vl -- vcc*/
        VistaTipoDocumento vd = new VistaTipoDocumento();
        VistaLabor vl = new VistaLabor();
        VistaCuenta vcc = new VistaCuenta();
        vistaPrincipal.getPanelPrincipal().add(vista);

        //ControladorEmpleado control = new ControladorEmpleado(modelop, modelo, vista, modelol, modelot);
        ControladorEmpleado control = new ControladorEmpleado(modelo, vista, vd, vcc, vl, modelol, modelot);
        
        control.IniciarControl();
    }

    private void crudLabor() {
        ModeloLabor modelo = new ModeloLabor();
        VistaLabor vista = new VistaLabor();

        vistaPrincipal.getPanelPrincipal().add(vista);

        ControladorLabor control = new ControladorLabor(vista, modelo);
        control.IniciarControl();
    }

    private void crudCliente() {
        ModeloCliente modelo = new ModeloCliente();
        ModeloPersona modelop = new ModeloPersona();
        ModeloTipoCliente modelol = new ModeloTipoCliente();

        VistaClientes vista = new VistaClientes();
        VistaTipoCliente vc = new VistaTipoCliente();
        VistaTipoDocumento vt = new VistaTipoDocumento();
        ModeloTipoDocumento modelotipodoc = new ModeloTipoDocumento();

        vistaPrincipal.getPanelPrincipal().add(vista);

        ControladorCliente contro = new ControladorCliente(modelop, modelo, vista, modelol, vt, modelotipodoc, vc);
//        ControladorCliente control =new ControladorCliente(modelop, modelo, vista, modelol);
        contro.IniciarControl();
    }

    private void crudTipoCliente() {
        ModeloTipoCliente mtc = new ModeloTipoCliente();
        VistaTipoCliente vtc = new VistaTipoCliente();

        vistaPrincipal.getPanelPrincipal().add(vtc);

    }

    private void crudHabitacion() {
        ModeloHabitación modelohab = new ModeloHabitación();
        ModeloTipo_Habitacion modelotip = new ModeloTipo_Habitacion();
        VistaHabitacion vista = new VistaHabitacion();

        vistaPrincipal.getPanelPrincipal().add(vista);

        ControladorHabitacion control = new ControladorHabitacion(modelohab, modelotip, vista);
        control.iniciarControl();
    }

    private void crudReserva() {
        ModeloEncReserva modeloenc = new ModeloEncReserva();
        ModeloDetReserva modelodet = new ModeloDetReserva();
        VistaReserva vista = new VistaReserva();
        vista.setTitle(null);
        vistaPrincipal.getPanelPrincipal().add(vista);

        ControladorReserva control = new ControladorReserva(modeloenc, modelodet, vista);
        control.iniciarControl();
    }

    private void CrudFactura() {
        VistaFactura vistaF = new VistaFactura();
        ModeloEncabezadoFactura modeloE = new ModeloEncabezadoFactura();
        ModeloDetalleFactura modeloD = new ModeloDetalleFactura();

        ControladorFactura control = new ControladorFactura(modeloE, modeloD, vistaF);

        vistaPrincipal.getPanelPrincipal().add(vistaF);

        control.iniciarControl();
    }

    private void crudCuenta() {
        VistaCuenta vista = new VistaCuenta();
        ModeloCuenta modeloCuenta = new ModeloCuenta();

        ControladorCuenta control = new ControladorCuenta(modeloCuenta, vista);

        vistaPrincipal.getPanelPrincipal().add(vista);

        control.IniciarControl();
    }

    private void CrudServicios() {
        VistaServicio vistaS = new VistaServicio();
        ModeloServicio modeloS = new ModeloServicio();
        
        ControladorServicio control = new ControladorServicio(vistaS, modeloS);
        
        vistaPrincipal.getPanelPrincipal();

        control.IniciarControl();
    }
}
