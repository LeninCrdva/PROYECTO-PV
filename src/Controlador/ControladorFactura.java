/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.VistaFactura;
import Vista.VistaPrincipal;
import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
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
    private VistaPrincipal vistaP;

    public ControladorFactura(ModeloEncabezadoFactura modelo, ModeloDetalleFactura modelo2, VistaFactura vista, VistaPrincipal vistaP) {
        this.modelo = modelo;
        this.modelo2 = modelo2;
        this.vista = vista;
        this.vistaP = vistaP;
        vista.setVisible(true);
    }

    public void iniciarControl() {
        cargaEnc();
        vista.getBtnCrear().addActionListener(l -> ingresarFacturaDialog(0));
        vista.getBtnEditar().addActionListener(l -> ingresarFacturaDialog(1));
        vista.getBtnAceptar().addActionListener(l -> ingresarModificarFactura());
        vista.getTxtBuscar().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscarFactura();
            }
        });
        vista.getBtnElegirCliente().addActionListener(l -> elegirCliente());
        vista.getTblClientes().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseCliked(java.awt.event.MouseEvent evt) {
                clickTblCliente();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                clickTblCliente();
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                clickTblCliente();
            }
        });
        vista.getBtnSalirCliente().addActionListener(l -> salirCliente());
        vista.getBtnBuscarCliente().addActionListener(l -> BuscaClientes());
        vista.getBtnElegirServicio().addActionListener(l -> elegirServicio());
        vista.getTblServicios().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseCliked(java.awt.event.MouseEvent evt) {
                clickTblServicio();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                clickTblServicio();
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                clickTblServicio();
            }
        });
        vista.getBtnSalirServicio().addActionListener(l -> salirServicio());
        vista.getBtnBuscarServicio().addActionListener(l -> BuscarServicio());
        vista.getBtnElegirReserva().addActionListener(l -> elegirReserva());
        vista.getTablaReserva().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseCliked(java.awt.event.MouseEvent evt) {
                clickTblReserva();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                clickTblReserva();
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                clickTblReserva();
            }
        });
        vista.getBtnSalirReserva().addActionListener(l -> salirReserva());
        vista.getBtnBuscarReserva().addActionListener(l -> BuscarReserva());
        vista.getBtnPrint().addActionListener(l -> imprimirFactura());
    }

    private void cargaEnc() {
        List<EncabezadoFactura> lista = modelo.listaEncabezadoFactura();
        DefaultTableModel mTabla = (DefaultTableModel) vista.getTblFactura().getModel();
        mTabla.setNumRows(0);
        vista.getTblFactura().getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        vista.getTblFactura().getTableHeader().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        vista.getTblFactura().getTableHeader().setOpaque(false);
        vista.getTblFactura().getTableHeader().setBackground(new Color(32, 136, 203));
        vista.getTblFactura().getTableHeader().setForeground(new Color(255, 255, 255));
        vista.getTblFactura().setRowHeight(25);
        String[] columnas = {"ID", "CLIENTE", "EMPLEADO", "FECHA", "TOTAL", "ESTADO"};
        mTabla.setColumnIdentifiers(columnas);
        lista.stream().forEach(enc -> {
            Object[] registro = {enc.getId_enc(), enc.getIdCliente_enc(), enc.getIdEmpleado_enc(), enc.getFecha_enc(), enc.getTotal_enc(), enc.isEstado_enc()};
            mTabla.addRow(registro);
        });
    }

    private void totalFactura() {
        Double total = modelo.totalEnc(Integer.parseInt(vista.getTblFactura().getValueAt(vista.getTblFactura().getSelectedRow(), 0).toString()), Integer.parseInt(vista.getTxtReserva().getText()));
        vista.getTxtTotal().setText(String.valueOf(total));
    }

    private void ingresarFacturaDialog(int ce) {
        String titulo;
        ModeloCuenta modeloC = new ModeloCuenta();

        vista.getTxtEmpleado().setText(String.valueOf(modeloC.BuscarCuentasBD(vistaP.getLblNameUser().getText()).get(0).getId_emp()));
        try {
            vista.getTxtEmpleado().setEnabled(false);
            vista.getTxtCliente().setEnabled(false);
            vista.getTxtReserva().setEnabled(false);
            vista.getTxtServicio().setEnabled(false);
            vista.getTxtCosto().setEnabled(false);
            if (ce == 0) {
                titulo = "Crear Factura";
                vista.getDlgCrudFactura().setTitle(titulo);
                vista.getTxtServicio().setEnabled(true);
                vista.getTxtReserva().setEnabled(true);
                vista.getTxtObservaciones().setEnabled(true);
                vista.getTxtCosto().setEnabled(true);
                vista.getBtnElegirReserva().setEnabled(true);
                vista.getBtnElegirServicio().setEnabled(true);
                vista.getLblID().setVisible(false);
            } else {
                titulo = "Modificar Factura";
                vista.getDlgCrudFactura().setTitle(titulo);
                if (vista.getTblFactura().getSelectedRow() < 0) {
                    JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR EL ENCABEZADO A MODIFICAR");
                    return;
                }
                JOptionPane.showMessageDialog(null, "Debido a las políticas, se han limitado los campos a los que puede acceder");
                vista.getLblID().setEnabled(true);
                vista.getLblID().setText(vista.getTblFactura().getValueAt(vista.getTblFactura().getSelectedRow(), 0).toString());
                vista.getTxtCliente().setText(vista.getTblFactura().getValueAt(vista.getTblFactura().getSelectedRow(), 1).toString());
                vista.getTxtEmpleado().setText(vista.getTblFactura().getValueAt(vista.getTblFactura().getSelectedRow(), 2).toString());
                vista.getTxtTotal().setText(vista.getTblFactura().getValueAt(vista.getTblFactura().getSelectedRow(), 4).toString());
                String[] fecha = vista.getTblFactura().getValueAt(vista.getTblFactura().getSelectedRow(), 3).toString().split("-");
                int a = Integer.parseInt(fecha[0]) - 1900;
                int m = Integer.parseInt(fecha[1]);
                int d = Integer.parseInt(fecha[2]);
                vista.getDcFecha().setDate(new Date(a, m, d));
                vista.getTxtCliente().setEnabled(false);
                vista.getTxtEmpleado().setEnabled(false);
                vista.getTxtTotal().setEnabled(false);

                vista.getTxtServicio().setEnabled(false);
                vista.getTxtReserva().setEnabled(false);
                vista.getTxtObservaciones().setEnabled(false);
                vista.getTxtCosto().setEnabled(false);
                vista.getBtnElegirReserva().setEnabled(false);
                vista.getBtnElegirServicio().setEnabled(false);
                
            }

            vista.getDlgCrudFactura().setSize(700, 370);
            vista.getDlgCrudFactura().setLocationRelativeTo(vista);
            vista.getDlgCrudFactura().setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(ControladorFactura.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void ingresarModificarFactura() {
        if (vista.getDlgCrudFactura().getTitle().equals("Crear Factura")) {
            if (vista.getTxtCliente().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE ELEGIR UN CLIENTE");
                return;
            }

            if (vista.getTxtEmpleado().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE ELEGIR UN EMPLEADO");
                return;
            }

            int d, m, a;
            Calendar cal = vista.getDcFecha().getCalendar();
            d = cal.get(Calendar.DAY_OF_MONTH);
            m = cal.get(Calendar.MONTH);
            a = cal.get(Calendar.YEAR) - 1900;
            ModeloEncabezadoFactura menc;
            menc = new ModeloEncabezadoFactura(Integer.parseInt(vista.getTxtCliente().getText()), Integer.parseInt(vista.getTxtEmpleado().getText()), new Date(a, m, d), Double.parseDouble(vista.getTxtTotal().getText()), true);
            int id = menc.grabarEncabezadoF();
            new ModeloDetalleFactura(id, Integer.parseInt(vista.getTxtServicio().getText()), Integer.parseInt(vista.getTxtReserva().getText()), vista.getTxtObservaciones().getText(), Double.parseDouble(vista.getTxtCosto().getText())).grabarDetalleF();
            cargaEnc();
        } else {
            if (vista.getTblFactura().getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR LA FACTURA A EDITAR");
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

            int d, m, a;
            Calendar cal = vista.getDcFecha().getCalendar();
            d = cal.get(Calendar.DAY_OF_MONTH);
            m = cal.get(Calendar.MONTH);
            a = cal.get(Calendar.YEAR) - 1900;
            new ModeloEncabezadoFactura(Integer.parseInt(vista.getTxtCliente().getText()), Integer.parseInt(vista.getTxtEmpleado().getText()), new Date(a, m, d), Double.parseDouble(vista.getTxtTotal().getText()), true).modificarEncabezadoF(vista.getLblID().getText());
            cargaEnc();
        }
        limpiar();
        vista.getDlgCrudFactura().dispose();
    }

    private void buscarFactura() {
        List<EncabezadoFactura> lista = modelo.buscarEncabezadoF(vista.getTxtBuscar().getText());
        DefaultTableModel mTabla = (DefaultTableModel) vista.getTblFactura().getModel();
        mTabla.setNumRows(0);
        vista.getTblFactura().getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        vista.getTblFactura().getTableHeader().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        vista.getTblFactura().getTableHeader().setOpaque(false);
        vista.getTblFactura().getTableHeader().setBackground(new Color(32, 136, 203));
        vista.getTblFactura().getTableHeader().setForeground(new Color(255, 255, 255));
        vista.getTblFactura().setRowHeight(25);
        String[] columnas = {"ID", "CLIENTE", "EMPLEADO", "FECHA", "TOTAL", "ESTADO"};
        mTabla.setColumnIdentifiers(columnas);
        lista.stream().forEach(enc -> {
            Object[] registro = {enc.getId_enc(), enc.getIdCliente_enc(), enc.getIdEmpleado_enc(), enc.getFecha_enc(), enc.getTotal_enc(), enc.isEstado_enc()};
            mTabla.addRow(registro);
        });
    }

    private void elegirCliente() {
        vista.getDlgSeleccionarCliente().setTitle("SELECCIONAR CLIENTE");
        vista.getDlgSeleccionarCliente().setSize(890, 270);
        vista.getDlgSeleccionarCliente().setLocationRelativeTo(vista);
        ModeloCliente modeloC = new ModeloCliente();
        List<Cliente> lista = modeloC.listarclientes();
        DefaultTableModel mTabla = (DefaultTableModel) vista.getTblClientes().getModel();
        mTabla.setNumRows(0);
        vista.getTblClientes().getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        vista.getTblClientes().getTableHeader().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        vista.getTblClientes().getTableHeader().setOpaque(false);
        vista.getTblClientes().getTableHeader().setBackground(new Color(32, 136, 203));
        vista.getTblClientes().getTableHeader().setForeground(new Color(255, 255, 255));
        vista.getTblClientes().setRowHeight(25);
        String[] columnas = {"ID CLIENTE", "DNI", "NOMBRE", "APELLIDO", "CORREO", "TELEFONO", "DIRECCION"};
        mTabla.setColumnIdentifiers(columnas);
        lista.stream().forEach(pe -> {
            Object[] registro = {String.valueOf(pe.getId_cli()), pe.getId_cli(), pe.getNombre_per(), pe.getApellido_per(), String.valueOf(pe.getEmail_per()), pe.getTelefono_per(), String.valueOf(pe.getDireccion_per())};
            mTabla.addRow(registro);
        });
        vista.getDlgSeleccionarCliente().setVisible(true);
    }

    private void clickTblCliente() {
        vista.getTxtCliente().setText(vista.getTblClientes().getValueAt(vista.getTblClientes().getSelectedRow(), 0).toString());
        vista.getDlgSeleccionarCliente().dispose();
    }

    private void elegirServicio() {
        vista.getDlgSeleccionarServicio().setTitle("SELECCIONAR SERVICIO");
        vista.getDlgSeleccionarServicio().setSize(600, 310);
        vista.getDlgSeleccionarServicio().setLocationRelativeTo(vista);
        ModeloServicio modeloS = new ModeloServicio();
        List<Servicio> lista = modeloS.ListarServiciosBD();
        DefaultTableModel mTabla = (DefaultTableModel) vista.getTblServicios().getModel();
        mTabla.setNumRows(0);
        vista.getTblServicios().getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        vista.getTblServicios().getTableHeader().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        vista.getTblServicios().getTableHeader().setOpaque(false);
        vista.getTblServicios().getTableHeader().setBackground(new Color(32, 136, 203));
        vista.getTblServicios().getTableHeader().setForeground(new Color(255, 255, 255));
        vista.getTblServicios().setRowHeight(25);
        String[] columnas = {"ID", "NOMBRE", "DESCRIPCION", "PRECIO"};
        mTabla.setColumnIdentifiers(columnas);
        lista.stream().forEach(pe -> {
            Object[] registro = {String.valueOf(pe.getId_ser()), pe.getNombre_ser(), pe.getDescripcion_ser(), pe.getPrecio_ser()};
            mTabla.addRow(registro);
        });
        vista.getDlgSeleccionarServicio().setVisible(true);
    }

    private void clickTblServicio() {
        vista.getTxtServicio().setText(vista.getTblServicios().getValueAt(vista.getTblServicios().getSelectedRow(), 0).toString());
        vista.getTxtCosto().setText(vista.getTblServicios().getValueAt(vista.getTblServicios().getSelectedRow(), 3).toString());
        vista.getDlgSeleccionarServicio().dispose();
    }

    private void salirServicio() {
        vista.getDlgSeleccionarServicio().dispose();
    }

    private void BuscarServicio() {
        String bus = vista.getTxtBuscarServicio().getText().trim();
        ModeloServicio modeloS = new ModeloServicio();
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

    private void elegirReserva() {
        vista.getDlgSeleccionarReserva().setTitle("SELECCIONAR RESERVA");
        vista.getDlgSeleccionarReserva().setSize(890, 270);
        vista.getDlgSeleccionarReserva().setLocationRelativeTo(vista);
        ModeloEncReserva mdEnc = new ModeloEncReserva();
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

    private void clickTblReserva() {
        vista.getTxtReserva().setText(vista.getTablaReserva().getValueAt(vista.getTablaReserva().getSelectedRow(), 0).toString());
        double costo = Double.parseDouble(vista.getTablaReserva().getValueAt(vista.getTablaReserva().getSelectedRow(), 4).toString()) + Double.parseDouble(vista.getTxtCosto().getText().trim());
        vista.getTxtCosto().setText(String.valueOf(costo));
        vista.getDlgSeleccionarReserva().dispose();
    }

    private void salirReserva() {
        vista.getDlgSeleccionarReserva().dispose();
    }

    private void BuscarReserva() {
        String bus = String.valueOf(vista.getTxtBuscarReserva().getText().trim());
        ModeloEncReserva modeloR = new ModeloEncReserva();
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

    private void limpiar() {
        vista.getTxtCliente().setText(null);
        vista.getTxtEmpleado().setText(null);
        vista.getDcFecha().setDate(null);
        vista.getTxtTotal().setText(null);
        vista.getTxtCliente().setEnabled(true);
        vista.getTxtEmpleado().setEnabled(true);
        vista.getTxtServicio().setText(null);
        vista.getTxtReserva().setText(null);
        vista.getTxtObservaciones().setText(null);
        vista.getTxtCosto().setText(null);
    }

    private void salirCliente() {
        vista.getDlgSeleccionarCliente().dispose();
    }

    private void BuscaClientes() {
        ModeloPersona modeloC = new ModeloPersona();
        List<Persona> listaclie = modeloC.BuscaPersonaDB(vista.getTxtBuscarCliente().getText());
        DefaultTableModel df;
        df = (DefaultTableModel) vista.getTblClientes().getModel();
        df.setNumRows(0);
        vista.getTblClientes().getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        vista.getTblClientes().getTableHeader().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        vista.getTblClientes().getTableHeader().setOpaque(false);
        vista.getTblClientes().getTableHeader().setBackground(new Color(32, 136, 203));
        vista.getTblClientes().getTableHeader().setForeground(new Color(255, 255, 255));
        vista.getTblClientes().setRowHeight(25);

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
