/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.VistaFactura;
import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Andrea
 */
public class ControladorFactura {
    private ModeloEncabezadoFactura modelo;
    private ModeloDetalleFactura modelo2;
    private VistaFactura vista;

    public ControladorFactura(ModeloEncabezadoFactura modelo, ModeloDetalleFactura modelo2, VistaFactura vista) {
        this.modelo = modelo;
        this.modelo2 = modelo2;
        this.vista = vista;
        vista.setVisible(true);
    }

    
    public void iniciarControl(){
        cargaEnc();
        vista.getBtnCrear().addActionListener(l->ingresarFacturaDialog(0));
        vista.getBtnEditar().addActionListener(l->ingresarFacturaDialog(1));
        vista.getBtnAceptar().addActionListener(l->ingresarModificarFactura());
        vista.getBtnAgregar().addActionListener(l->ingresarModificarDetalle(0));
        vista.getBtnModificarD().addActionListener(l->ingresarModificarDetalle(1));
        vista.getBtnEliminar().addActionListener(l->eliminarFactura());
        vista.getTxtBuscar().addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyTyped(java.awt.event.KeyEvent evt){
                buscarFactura();
            }
        });
        vista.getBtnElegirCliente().addActionListener(l->elegirCliente());
        vista.getTblClientes().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseCliked(java.awt.event.MouseEvent evt){
                clickTblCliente();
            }
        });
        vista.getBtnSalirCliente().addActionListener(l->salirCliente());
        vista.getBtnBuscarCliente().addActionListener(l->BuscaClientes());
        vista.getBtnElegirServicio().addActionListener(l->elegirServicio());
        vista.getTblServicios().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseCliked(java.awt.event.MouseEvent evt){
                clickTblServicio();
            }
        });
        vista.getBtnSalirServicio().addActionListener(l->salirServicio());
        vista.getBtnBuscarServicio().addActionListener(l->BuscarServicio());
        vista.getBtnElegirReserva().addActionListener(l->elegirReserva());
        vista.getTablaReserva().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                clickTblReserva();
            }
        });
        vista.getBtnSalirReserva().addActionListener(l->salirReserva());
        vista.getBtnBuscarReserva().addActionListener(l->BuscarReserva());
    }
    
    private void cargaEnc(){
        List<EncabezadoFactura> lista=modelo.listaEncabezadoFactura();
        DefaultTableModel mTabla=(DefaultTableModel) vista.getTblFactura().getModel();
        mTabla.setNumRows(0);   
        String[] columnas={"ID","CLIENTE","EMPLEADO","FECHA","TOTAL","ESTADO"};
        mTabla.setColumnIdentifiers(columnas);
        lista.stream().forEach(enc->{
            Object[] registro={enc.getId_enc(),enc.getIdCliente_enc(),enc.getIdEmpleado_enc(),enc.getFecha_enc(),enc.getTotal_enc(),enc.isEstado_enc()};
            mTabla.addRow(registro);
        });
    }
    private void cargaDet(String id){
        List<DetalleFactura> lista=modelo2.buscarDetalleF(id);
        DefaultTableModel mTabla=(DefaultTableModel) vista.getTblFactura().getModel();
        mTabla.setNumRows(0);   
        String[] columnas={"ID","ENCABEZADO","SERVICIO","RESERVA","OBSERVACIONES","COSTO"};
        mTabla.setColumnIdentifiers(columnas);
        lista.stream().forEach(det->{
            Object[] registro={det.getId_det(),det.getIdEncabezado_det(),det.getIdServicio_det(),det.getIdReserva_det(),det.getObservaciones_det(),det.getCosto_det()};
            mTabla.addRow(registro);
        });
    }
    private void ingresarFacturaDialog(int ce) {
        String titulo;
        try{
            if (ce==0) {
                titulo="Crear Factura";
                vista.getDlgCrudFactura().setTitle(titulo);
            }else{
                titulo="Modificar Factura";
                vista.getDlgCrudFactura().setTitle(titulo);
                if (vista.getTblFactura().getSelectedRow()<0) {
                    JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR EL ENCABEZADO A MODIFICAR");
                    return;
                }
                if (modelo.buscaEncabezadoF(vista.getTblFactura().getValueAt(vista.getTblFactura().getSelectedRow(), 0).toString())==false) {
                    JOptionPane.showMessageDialog(null, "NO SE ENCONTRO DICHO ENCABEZADO");
                    return;
                }
                cargaDet(vista.getTblFactura().getValueAt(vista.getTblFactura().getSelectedRow(), 0).toString());
                ConnectionPG con=new ConnectionPG();
                String sql="SELECT id_enc,idcliente_enc,idempl_enc,fecha_enc,total_enc,estado_enc FROM enc_factura WHERE id_enc="+vista.getTblFactura().getValueAt(vista.getTblFactura().getSelectedRow(), 0).toString()+"";
                ResultSet re=con.Consulta(sql);re.next();
                vista.getTxtCodigoE().setText(String.valueOf(re.getInt("id_enc")));
                vista.getTxtCliente().setText(String.valueOf(re.getInt("idcliente_enc")));
                vista.getTxtEmpleado().setText(String.valueOf(re.getString("idempl_enc")));
                vista.getTxtTotal().setText(String.valueOf(re.getDouble("idempl_enc")));
                int a=re.getDate("fecha_enc").getYear()-1900;
                int m=re.getDate("fecha_enc").getMonth();
                int d=re.getDate("fecha_enc").getDay();
                vista.getDcFecha().setDate(new Date(a,m,d));
                vista.getTxtCodigoE().setEnabled(false);
                vista.getTxtCliente().setEnabled(false);
                vista.getTxtEmpleado().setEnabled(false);
                vista.getTxtTotal().setEnabled(false);
            }
            
            vista.getDlgCrudFactura().setSize(890,600);
            vista.getDlgCrudFactura().setLocationRelativeTo(vista);
            vista.getDlgCrudFactura().setVisible(true);
        }catch (SQLException ex) {
            Logger.getLogger(ControladorFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void ingresarModificarFactura(){
        if (vista.getDlgCrudFactura().getTitle().equals("Crear Factura")) {
            if (vista.getTxtCodigoE().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE INGRESAR UN CODIGO");
                return;
            }
            if (!vista.getTxtCodigoE().getText().matches("[0-9]{1,20}")) {
                JOptionPane.showMessageDialog(null, "DEBE DE INGRESAR UN CODIGO BIEN");
                return;
            }
            if (vista.getTxtCliente().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE ELEGIR UN CLIENTE");
                return;
            }
            
            if (vista.getTxtEmpleado().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE ELEGIR UN EMPLEADO");
                return;
            }
            
            int d,m,a;
            Calendar cal=vista.getDcFecha().getCalendar();
            d=cal.get(Calendar.DAY_OF_MONTH);
            m=cal.get(Calendar.MONTH);
            a=cal.get(Calendar.YEAR);
            new ModeloEncabezadoFactura(Integer.parseInt(vista.getTxtCodigoE().getText()),Integer.parseInt(vista.getTxtCliente().getText()),Integer.parseInt(vista.getTxtEmpleado().getText()),new Date(a,m,d),Double.parseDouble(vista.getTxtTotal().getText()),true).grabarEncabezadoF();
            cargaEnc();
        }else{
            if (vista.getTxtCodigoE().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE INGRESAR UN CODIGO");
                return;
            }
            if (!vista.getTxtCodigoE().getText().matches("[0-9]{1,20}")) {
                JOptionPane.showMessageDialog(null, "DEBE DE INGRESAR UN CODIGO BIEN");
                return;
            }
            if (vista.getTxtCliente().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE ELEGIR UN CLIENTE");
                return;
            }
            
            if (vista.getTxtEmpleado().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE ELEGIR UN EMPLEADO");
                return;
            }
            
            int d,m,a;
            Calendar cal=vista.getDcFecha().getCalendar();
            d=cal.get(Calendar.DAY_OF_MONTH);
            m=cal.get(Calendar.MONTH);
            a=cal.get(Calendar.YEAR);
            new ModeloEncabezadoFactura(Integer.parseInt(vista.getTxtCodigoE().getText()),Integer.parseInt(vista.getTxtCliente().getText()),Integer.parseInt(vista.getTxtEmpleado().getText()),new Date(a,m,d),Double.parseDouble(vista.getTxtTotal().getText()),true).modificarEncabezadoF();
            cargaEnc();
        }
        limpiar();
    }
    private void ingresarModificarDetalle(int c){
        if (c==0) {
            if (vista.getTxtCodigoD().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE INGRESAR UN CODIGO");
                return;
            }
            if (!vista.getTxtCodigoD().getText().matches("[0-9]{1,20}")) {
                JOptionPane.showMessageDialog(null, "DEBE DE INGRESAR UN CODIGO BIEN");
                return;
            }
            if (vista.getTxtServicio().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE ELEGIR UN SERVICIO");
                return;
            }
            
            if (vista.getTxtReserva().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE ELEGIR UNA RESERVA");
                return;
            }
            if (vista.getTxtObservaciones().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE INGRESAR UNA OBSERVACION");
                return;
            }
            if (vista.getTxtObservaciones().getText().trim().matches("[a-zA-Z0-9.,/-]{1,100}")==false) {
                JOptionPane.showMessageDialog(null, "DEBE DE INGRESAR UNA OBSERVACION BIEN");
                return;
            }
            new ModeloDetalleFactura(Integer.parseInt(vista.getTxtCodigoD().getText()),Integer.parseInt(vista.getTxtCodigoE().getText()),Integer.parseInt(vista.getTxtServicio().getText()),Integer.parseInt(vista.getTxtServicio().getText()),vista.getTxtObservaciones().getText(),Double.parseDouble(vista.getTxtCosto().getText())).grabarDetalleF();
            cargaDet(vista.getTxtCodigoE().getText());
        }else{
            if (vista.getTxtCodigoD().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE INGRESAR UN CODIGO");
                return;
            }
            if (!vista.getTxtCodigoD().getText().matches("[0-9]{1,20}")) {
                JOptionPane.showMessageDialog(null, "DEBE DE INGRESAR UN CODIGO BIEN");
                return;
            }
            if (vista.getTxtServicio().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE ELEGIR UN SERVICIO");
                return;
            }
            
            if (vista.getTxtReserva().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE ELEGIR UNA RESERVA");
                return;
            }
            if (vista.getTxtObservaciones().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE INGRESAR UNA OBSERVACION");
                return;
            }
            if (vista.getTxtObservaciones().getText().trim().matches("[a-zA-Z0-9.,/-]{1,100}")==false) {
                JOptionPane.showMessageDialog(null, "DEBE DE INGRESAR UNA OBSERVACION BIEN");
                return;
            }
            new ModeloDetalleFactura(Integer.parseInt(vista.getTxtCodigoD().getText()),Integer.parseInt(vista.getTxtCodigoE().getText()),Integer.parseInt(vista.getTxtServicio().getText()),Integer.parseInt(vista.getTxtServicio().getText()),vista.getTxtObservaciones().getText(),Double.parseDouble(vista.getTxtCosto().getText())).modificarDetalleF();
            cargaDet(vista.getTxtCodigoE().getText());
        }
        limpiar();
    }
    private void eliminarFactura() {
        try{
            if (vista.getTblFactura().getSelectedRow()<0) {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR LA FACTURA A ELIMINAR");
                return;
            }
            if (modelo.buscaEncabezadoF(vista.getTblFactura().getValueAt(vista.getTblFactura().getSelectedRow(), 0).toString())==false) {
                    JOptionPane.showMessageDialog(null, "NO SE ENCONTRO DICHA FACTURA");
                    return;
            }
            new ModeloEncabezadoFactura().eliminarEncabeazadoF(vista.getTblFactura().getValueAt(vista.getTblFactura().getSelectedRow(), 0).toString());
            cargaEnc();
        }catch (SQLException ex) {
            Logger.getLogger(ControladorFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    };
    private void buscarFactura(){
        List<EncabezadoFactura> lista=modelo.buscarEncabezadoF(vista.getTxtBuscar().getText());
        DefaultTableModel mTabla=(DefaultTableModel) vista.getTblFactura().getModel();
        mTabla.setNumRows(0);   
        String[] columnas={"ID","CLIENTE","EMPLEADO","FECHA","TOTAL","ESTADO"};
        mTabla.setColumnIdentifiers(columnas);
        lista.stream().forEach(enc->{
            Object[] registro={enc.getId_enc(),enc.getIdCliente_enc(),enc.getIdEmpleado_enc(),enc.getFecha_enc(),enc.getTotal_enc(),enc.isEstado_enc()};
            mTabla.addRow(registro);
        });
    }
    private void elegirCliente(){
        vista.getDlgSeleccionarCliente().setTitle("SELECCIONAR CLIENTE");
        vista.getDlgSeleccionarCliente().setSize(890,270);
        vista.getDlgSeleccionarCliente().setLocationRelativeTo(vista);
        ModeloCliente modeloC=new ModeloCliente();
        List<Cliente> lista=modeloC.listarclientes();
        DefaultTableModel mTabla=(DefaultTableModel) vista.getTblClientes().getModel();
        mTabla.setNumRows(0);   
        String[] columnas={"ID CLIENTE", "DNI", "NOMBRE", "APELLIDO", "CORREO", "TELEFONO", "DIRECCION"};
        mTabla.setColumnIdentifiers(columnas);
        lista.stream().forEach(pe->{
            Object[] registro={String.valueOf(pe.getId_cli()), pe.getId_cli(), pe.getNombre_per(), pe.getApellido_per(), String.valueOf(pe.getEmail_per()), pe.getTelefono_per(), String.valueOf(pe.getDireccion_per())};
            mTabla.addRow(registro);
        });
        vista.getDlgSeleccionarCliente().setVisible(true);
    }
    private void clickTblCliente(){
        vista.getTxtCliente().setText(vista.getTblClientes().getValueAt(vista.getTblClientes().getSelectedRow(), 0).toString());
        vista.getDlgSeleccionarCliente().dispose();
    }
    private void elegirServicio(){
        vista.getDlgSeleccionarServicio().setTitle("SELECCIONAR SERVICIO");
        vista.getDlgSeleccionarServicio().setSize(890,270);
        vista.getDlgSeleccionarServicio().setLocationRelativeTo(vista);
        ModeloServicio modeloS=new ModeloServicio();
        List<Servicio> lista=modeloS.ListarServiciosBD();
        DefaultTableModel mTabla=(DefaultTableModel) vista.getTblServicios().getModel();
        mTabla.setNumRows(0);   
        String[] columnas={"ID","NOMBRE","DESCRIPCION","PRECIO"};
        mTabla.setColumnIdentifiers(columnas);
        lista.stream().forEach(pe->{
            Object[] registro={String.valueOf(pe.getId_ser()), pe.getNombre_ser(), pe.getDescripcion_ser(), pe.getPrecio_ser()};
            mTabla.addRow(registro);
        });
        vista.getDlgSeleccionarServicio().setVisible(true);
    }
    private void clickTblServicio(){
        vista.getTxtServicio().setText(vista.getTblServicios().getValueAt(vista.getTblServicios().getSelectedRow(), 0).toString());
        vista.getDlgSeleccionarServicio().dispose();
    }
    private void salirServicio(){
        vista.getDlgSeleccionarServicio().dispose();
    }
    private void BuscarServicio() {
        String bus = vista.getTxtBuscarServicio().getText().trim();
        ModeloServicio modeloS=new ModeloServicio();
        List<Servicio> listaser = modeloS.BuscarServicioBD(bus);
        DefaultTableModel df = (DefaultTableModel) vista.getTblServicios().getModel();
        df.setNumRows(0);

        listaser.stream().forEach(ser -> {
            String[] fila = {
                String.valueOf(ser.getId_ser()),
                ser.getNombre_ser(),
                ser.getDescripcion_ser(),
                Double.toString(ser.getPrecio_ser())
            };
            df.addRow(fila);

        });

    }
    private void elegirReserva(){
        vista.getDlgSeleccionarReserva().setTitle("SELECCIONAR RESERVA");
        vista.getDlgSeleccionarReserva().setSize(890,270);
        vista.getDlgSeleccionarReserva().setLocationRelativeTo(vista);
        ModeloEncReserva mdEnc=new ModeloEncReserva();
        List<Enc_reserva> lista = mdEnc.ListEnc();

        DefaultTableModel mTabla;
        mTabla = (DefaultTableModel) vista.getTablaReserva().getModel();
        mTabla.setNumRows(0); //Limpio la tabla
        vista.getTablaReserva().getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        vista.getTablaReserva().getTableHeader().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        vista.getTablaReserva().getTableHeader().setOpaque(false);
        vista.getTablaReserva().getTableHeader().setBackground(new Color(32, 136, 203));
        vista.getTablaReserva().getTableHeader().setForeground(new Color(255, 255, 255));
        vista.getTablaReserva().setRowHeight(25);

        lista.stream().forEach(ec -> {
            String[] fila = {String.valueOf(ec.getId_res()), String.valueOf(ec.getIdCliente_res()), String.valueOf(ec.getFechaIngreso_res()), String.valueOf(ec.getFechaSalida_res()), String.valueOf(ec.getTotal_res()), String.valueOf(ec.isEstado_res())};
            mTabla.addRow(fila);
        });
        vista.getDlgSeleccionarReserva().setVisible(true);
    }
    private void clickTblReserva(){
        vista.getTxtReserva().setText(vista.getTablaReserva().getValueAt(vista.getTablaReserva().getSelectedRow(), 0).toString());
        vista.getDlgSeleccionarReserva().dispose();
    }
    private void salirReserva(){
        vista.getDlgSeleccionarReserva().dispose();
    }
    private void BuscarReserva() {
        String bus = String.valueOf(vista.getTxtBuscarReserva().getText().trim());
        ModeloEncReserva modeloR=new ModeloEncReserva();
        modeloR.setIdCliente_res(Integer.parseInt(bus));
        List<Enc_reserva> listares = modeloR.ListEncSearch();
        DefaultTableModel mTabla = (DefaultTableModel) vista.getTablaReserva().getModel();
        mTabla.setNumRows(0);
        vista.getTablaReserva().getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        vista.getTablaReserva().getTableHeader().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        vista.getTablaReserva().getTableHeader().setOpaque(false);
        vista.getTablaReserva().getTableHeader().setBackground(new Color(32, 136, 203));
        vista.getTablaReserva().getTableHeader().setForeground(new Color(255, 255, 255));
        vista.getTablaReserva().setRowHeight(25);
        listares.stream().forEach(ec -> {
            String[] fila = {String.valueOf(ec.getId_res()), String.valueOf(ec.getIdCliente_res()), String.valueOf(ec.getFechaIngreso_res()), String.valueOf(ec.getFechaSalida_res()), String.valueOf(ec.getTotal_res()), String.valueOf(ec.isEstado_res())};
            mTabla.addRow(fila);
        });

    }
    private void limpiar(){
        vista.getTxtCodigoE().setText(null);
        vista.getTxtCliente().setText(null);
        vista.getTxtEmpleado().setText(null);
        vista.getDcFecha().setDate(null);
        vista.getTxtTotal().setText(null);
        vista.getTxtCodigoE().setEnabled(true);
        vista.getTxtCliente().setEnabled(true);
        vista.getTxtEmpleado().setEnabled(true);
    }
    private void salirCliente(){
        vista.getDlgSeleccionarCliente().dispose();
    }
    private void BuscaClientes() {
        ModeloPersona modeloC=new ModeloPersona();
        List<Persona> listaclie = modeloC.BuscaPersonaDB(vista.getTxtBuscarCliente().getText());
        DefaultTableModel df;
        df = (DefaultTableModel) vista.getTblClientes().getModel();
        df.setNumRows(0);

        listaclie.stream().forEach(cli -> {
            String[] Nuevo = {
                Integer.toString(cli.getId_per()),
                cli.getNumeroidentificacion_per(),
                cli.getNombre_per(),
                cli.getApellido_per(),
                cli.getDireccion_per(),
                cli.getTelefono_per(),
                cli.getEmail_per(),
                cli.getGenero_per(),
                String.valueOf(cli.getFecha_nac())
            };
            df.addRow(Nuevo);
        });
    }
    private void imprimirFactura() {
        if (vista.getTblFactura().getSelectedRowCount() == 1) {
            try {
                ConnectionPG con = new ConnectionPG();
                JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/Vista/Reportes/FACTURA.jasper"));
                //DECLARACION DEL MAP Y AGREGACIÓN DE LOS DATOS, SIN EMBARGO, SE DEBEN CAMBIAR POR DATOS DINAMICOS
                Map<String, Object> parametro = new HashMap<>();
                int id = Integer.parseInt(vista.getTblFactura().getValueAt(vista.getTblFactura().getSelectedRow(), 0).toString());
                parametro.put("IDENC", id);
                JasperPrint jp = JasperFillManager.fillReport(jr, parametro, con.getCon());
                JasperViewer jv = new JasperViewer(jp, false);
                jv.setVisible(true);
            } catch (JRException ex) {
                Logger.getLogger(ControladorReserva.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA DE LA TABLA");
        }
    }
}
