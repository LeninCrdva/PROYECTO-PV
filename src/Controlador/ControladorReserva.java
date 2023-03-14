package Controlador;

import Modelo.Cliente;
import Modelo.ConnectionPG;
import Modelo.Det_reserva;
import Modelo.Enc_reserva;
import Modelo.Habitacion;
import Modelo.ModeloCliente;
import Modelo.ModeloDetReserva;
import Modelo.ModeloEncReserva;
import Modelo.ModeloHabitación;
import Modelo.ModeloPersona;
import Modelo.ModeloTipo_Habitacion;
import Modelo.Persona;
import Modelo.Tipo_Habitacion;
import Vista.VistaReserva;
import java.awt.event.MouseAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ControladorReserva {

    private ModeloEncReserva mdEnc;
    private ModeloDetReserva mdDet;
    private VistaReserva vista;
    ModeloCliente mcl = new ModeloCliente();
    List<Cliente> listacliente = new ArrayList<>();
    private int valorSeleccionado;

    public ControladorReserva() {
    }

    public ControladorReserva(ModeloEncReserva mdEnc, ModeloDetReserva mdDet, VistaReserva vista) {
        this.mdEnc = mdEnc;
        this.mdDet = mdDet;
        this.vista = vista;
        vista.setVisible(true);
        vista.getComboHabitacion().setEnabled(true);
        vista.getCheckEdit().setVisible(false);
        vista.getBtnSave().setVisible(false);
    }

    public void iniciarControl() {
        cargarEncabezado();
        cargarClientes();
        cargarHabitacion();
        activarCombo();
        disableDates();
        vista.getComboHabitacion().addActionListener(l -> calcularTotal());
        showDetails(vista.getTblClientes());
        showDetails(vista.getTablaClientes());
        showDetails(vista.getTablaReserva());
        vista.getBtnCrear().addActionListener(l -> abrirCrearHab(1));
        vista.getBtnEditar().addActionListener(l -> abrirCrearHab(2));
        vista.getBtnAceptar().addActionListener(l -> crearEditarHab());
        vista.getBtnCancelar().addActionListener(l -> cancelDialog());
        vista.getBtnAddClient().addActionListener(l -> showListHues());
        vista.getBtnPrint().addActionListener(l -> imprimirReserva());
        vista.getBtnConfirm().addActionListener(l -> vista.getDialogDetalles().dispose());
    }

    private void cargarEncabezado() {
        List<Enc_reserva> lista = mdEnc.ListEnc();
        
        DefaultComboBoxModel<Cliente> modelo = new DefaultComboBoxModel<>();
        vista.getComboClientes().setModel(modelo);

        DefaultTableModel mTabla;
        mTabla = (DefaultTableModel) vista.getTablaReserva().getModel();
        mTabla.setNumRows(0); //Limpio la tabla

        lista.stream().forEach(ec -> {
            String[] fila = {String.valueOf(ec.getId_res()), String.valueOf(ec.getIdCliente_res()), String.valueOf(ec.getFechaIngreso_res()), String.valueOf(ec.getFechaSalida_res()), String.valueOf(ec.getTotal_res()), String.valueOf(ec.isEstado_res())};
            mTabla.addRow(fila);
        });
    }

    private void abrirCrearHab(int ce) {
        String title;
        boolean openwindow = false;
        if (ce == 1) {
            title = "Crear Reserva";
            vista.getDialogReserva().setName("crear");
            openwindow = true;
        } else {
            title = "Editar Reserva";
            vista.getDialogReserva().setName("editar");
            try {
                openwindow = uploadDates(vista.getTablaReserva());
            } catch (ParseException ex) {
                Logger.getLogger(ControladorHabitacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (openwindow) {
            vista.getDialogReserva().setLocationRelativeTo(vista.getBtnCrear());
            vista.getDialogReserva().setSize(465, 515);
            vista.getDialogReserva().setTitle(title);
            vista.getDialogReserva().setVisible(true);
        }
    }

    private void crearEditarHab() {
        if (vista.getDialogReserva().getName().equals("crear")) {
            //INSERTAR
            boolean msg = false;

            try {
                java.sql.Date fechaen = new java.sql.Date(vista.getDtchoEntrada().getDate().getTime());
                java.sql.Date fechasal = new java.sql.Date(vista.getDtchSalida().getDate().getTime());
                Cliente numtipo = (Cliente) vista.getComboClientes().getSelectedItem();
                double total = Double.parseDouble(vista.getTxtTotalRes().getText());

                ModeloEncReserva hb = new ModeloEncReserva();
                ModeloDetReserva dt = new ModeloDetReserva();
                Det_reserva det = new Det_reserva();
                hb.setIdCliente_res(numtipo.getId_cli());
                hb.setFechaIngreso_res(fechaen);
                hb.setFechaSalida_res(fechasal);
                hb.setTotal_res(total);

                if (success()) {
                    if (fechaValidator()) {
                        try {
                            int id_reserva = hb.CrearReserva();
                            Habitacion hab = (Habitacion) vista.getComboHabitacion().getSelectedItem();
                            if (id_reserva != 0) {
                                for (int i = 0; i < vista.getTablaClientes().getRowCount(); i++) {
                                    det.setIdCliente_rha(Integer.parseInt(vista.getTablaClientes().getValueAt(i, 0).toString()));
                                    det.setIdHabitacion_rha(hab.getId_hab());
                                    det.setIdReserva_rha(id_reserva);
                                    msg = dt.crearDetail(det) == null;
                                }
                            }
                        } catch (NullPointerException | NumberFormatException ex) {
                            msg = false;
                        }
                    } else {
                        msg = false;
                        JOptionPane.showMessageDialog(null, "La fecha de salida debe ser al menos mayor que la de entrada");
                    }
                } else {
                    msg = false;
                }

                if (msg) {
                    JOptionPane.showMessageDialog(null, "SE HA CREADO LA RESERVA CON ÉXITO");
                } else {
                    JOptionPane.showMessageDialog(null, "NO SE HA PODIDO CREAR LA RESERVA");
                }
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Asegúrese que haya elegido la fecha correctamenete");
            }
        } else if (vista.getDialogReserva().getName().equals("editar")) {
            //EDITAR
            int id = Integer.parseInt(vista.getLabelId().getText());
            Cliente numtipo = (Cliente) vista.getComboClientes().getSelectedItem();
            Date entrada = vista.getDtchoEntrada().getDate();
            Date salida = vista.getDtchSalida().getDate();
            double total = Double.parseDouble(vista.getTxtTotalRes().getText());

            ModeloEncReserva hb = new ModeloEncReserva();
            hb.setId_res(id);
            hb.setIdCliente_res(numtipo.getId_cli());
            hb.setFechaIngreso_res(entrada);
            hb.setFechaSalida_res(salida);
            hb.setTotal_res(total);

            if (hb.EditarReserva() == null) {
                JOptionPane.showMessageDialog(null, "SE HA EDITADO LOS DATOS DE LA HABITACIÓN CON ÉXITO");
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO EDITAR LA HABITACIÓN SELECCIONADA");
            }
        }
        cleanCamps();
        cargarEncabezado();
        cargarClientes();
        vista.getDialogReserva().dispose();
    }

    private void cancelDialog() {
        cleanCamps();
        vista.getDialogReserva().dispose();
    }

    private void cargarClientes() {
        vista.getComboClientes().removeAllItems();
        ModeloCliente modeloc = new ModeloCliente();
        ModeloPersona modelop = new ModeloPersona();

        List<Persona> listap = modelop.ListarPersonas();
        List<Cliente> listac = modeloc.listarclientes();

        DefaultTableModel mTabla;
        mTabla = (DefaultTableModel) vista.getTblClientes().getModel();
        mTabla.setNumRows(0); //Limpio la tabla

        listap.stream().forEach(pe -> {
            listac.stream().forEach(cl -> {
                if (pe.getId_per() == cl.getId_per()) {
                    vista.getComboClientes().addItem(new Cliente(cl.getId_cli(), cl.getId_tip(), pe.getNumeroidentificacion_per(), pe.getNombre_per(), pe.getApellido_per()));
                    String[] fila = {String.valueOf(cl.getId_cli()), String.valueOf(cl.getNumeroidentificacion_per()), cl.getNombre_per(), cl.getApellido_per()};
                    mTabla.addRow(fila);
                }
            });
        });
    }

    private void cargarHabitacion() {
        vista.getComboHabitacion().removeAllItems();
        ModeloHabitación mha = new ModeloHabitación();

        List<Habitacion> listah = mha.ListHabitacion();

        listah.stream().forEach(ha -> {
            vista.getComboHabitacion().addItem(new Habitacion(ha.getId_hab(), ha.getIdTipo_hab(), ha.getNumero_hab(), ha.isEstado_hab()));
        });
    }

    private boolean uploadDates(JTable table) throws ParseException {
        boolean a = false;
        if (table.getSelectedRowCount() == 1) {
            a = true;
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            vista.getLabelId().setText(vista.getTablaReserva().getValueAt(vista.getTablaReserva().getSelectedRow(), 0).toString());
            vista.getComboClientes().setSelectedIndex(Integer.parseInt(vista.getTablaReserva().getValueAt(vista.getTablaReserva().getSelectedRow(), 1).toString()) - 1);
            vista.getDtchoEntrada().setDate(formato.parse(vista.getTablaReserva().getValueAt(vista.getTablaReserva().getSelectedRow(), 2).toString()));
            vista.getDtchSalida().setDate(formato.parse(vista.getTablaReserva().getValueAt(vista.getTablaReserva().getSelectedRow(), 3).toString()));
            vista.getTxtTotalRes().setText(vista.getTablaReserva().getValueAt(vista.getTablaReserva().getSelectedRow(), 4).toString());
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA DE LA TABLA");
        }
        return a;
    }

    private void cleanCamps() {
        vista.getLabelId().setText("");
        vista.getComboClientes().setSelectedItem(null);
        vista.getDtchoEntrada().setDate(null);
        vista.getDtchSalida().setDate(null);
    }

    private void showDetails(JTable table) {
        switch (table.getName()) {
            case "listClientes":
                table.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        if (evt.getClickCount() == 2) {
                            valorSeleccionado = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                            mcl.setId_cli(valorSeleccionado);
                            Cliente cl = mcl.searchClient();
                            if (cl != null) {
                                if (!listacliente.isEmpty()) {

                                    boolean encontrado = false;
                                    for (Cliente cli : listacliente) {
                                        if (cl.getId_cli() == cli.getId_cli()) {
                                            encontrado = true;
                                            break;
                                        }
                                    }

                                    if (!encontrado) {
                                        listacliente.add(cl);
                                    } else {
                                        vista.getDialogClientes().dispose();
                                        JOptionPane.showMessageDialog(null, "El cliente seleccionado ya ha sido agregado");
                                    }
                                    DefaultTableModel mTabla = (DefaultTableModel) vista.getTablaClientes().getModel();
                                    mTabla.setNumRows(0);

                                    listacliente.stream().forEach(cli -> {
                                        String[] fila = {String.valueOf(cli.getId_cli()), cli.getNumeroidentificacion_per(), cli.getNombre_per(), cli.getApellido_per()};
                                        mTabla.addRow(fila);
                                    });
                                } else {
                                    DefaultTableModel mTabla = (DefaultTableModel) vista.getTablaClientes().getModel();
                                    mTabla.setNumRows(0);

                                    listacliente.add(cl);
                                    String[] fila = {String.valueOf(cl.getId_cli()), cl.getNumeroidentificacion_per(), cl.getNombre_per(), cl.getApellido_per()};
                                    mTabla.addRow(fila);
                                }
                            }
                            vista.getDialogClientes().dispose();
                        }
                    }
                });
                break;
            case "listSelectClientes":
                table.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        if (evt.getClickCount() == 2) {

                        }
                    }
                });
                break;
            case "listEncRes":
                table.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        if (evt.getClickCount() == 2) {
                            valorSeleccionado = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                            valorSeleccionado = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                            Map<String, Object> det = mdDet.detailComplete(valorSeleccionado);

                            if (!det.isEmpty()) {
                                vista.getTxtCliente().setText(det.get("nombre").toString() + det.get("apellido").toString());
                                vista.getTxtDiaLleg().setText(det.get("entrada").toString());
                                vista.getTxtDiaSal().setText(det.get("salida").toString());
                                vista.getTxtCantNoc().setText(det.get("noches").toString());
                                vista.getTxtNoHab().setText(det.get("numero").toString());
                                vista.getTxtCantP().setText(det.get("cantidad").toString());
                                vista.getTxtPrecio().setText(det.get("precio").toString());
                                vista.getTxtTotal().setText(String.valueOf(Double.parseDouble(det.get("precio").toString()) * Integer.parseInt(det.get("noches").toString())));

                                vista.getDialogDetalles().setLocationRelativeTo(null);
                                vista.getDialogDetalles().setSize(510, 455);
                                vista.getDialogDetalles().setTitle("Detalles de la reserva");
                                vista.getDialogDetalles().setVisible(true);
                            }
                        }
                    }
                });
                break;
        }
    }

    private void showListHues() {
        vista.getDialogClientes().setLocationRelativeTo(vista.getComboClientes());
        vista.getDialogClientes().setSize(485, 290);
        vista.getDialogClientes().setTitle("Seleccionar Cliente");
        vista.getDialogClientes().setAlwaysOnTop(true);
        vista.getDialogClientes().setModal(true);
        vista.getDialogClientes().setVisible(true);
    }

    private void activarCombo() {
        if (vista.getDtchoEntrada().getDate() != null && vista.getDtchSalida().getDate() != null) {
            vista.getComboHabitacion().setEnabled(true);
        }
    }

    private void calcularTotal() {
        ModeloTipo_Habitacion tp = new ModeloTipo_Habitacion();
        Habitacion ha = (Habitacion) vista.getComboHabitacion().getSelectedItem();

        List<Tipo_Habitacion> listath = tp.ListTipHabSearch("id_tha", String.valueOf(ha.getIdTipo_hab()));

        listath.stream().forEach(tha -> {
            if (tha.getId_tha() == ha.getIdTipo_hab()) {
                Double valor = tha.getPrecio_tha();
                int dif = vista.getDtchSalida().getDate().getDay() - vista.getDtchoEntrada().getDate().getDay();
                vista.getTxtTotalRes().setText(String.valueOf(valor * dif));
            }
        });
    }

    private void imprimirReserva() {
        if (vista.getTablaReserva().getSelectedRowCount() == 1) {
            try {
                ConnectionPG con = new ConnectionPG();
                JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/Vista/Reportes/Reserva.jasper"));
                //DECLARACION DEL MAP Y AGREGACIÓN DE LOS DATOS, SIN EMBARGO, SE DEBEN CAMBIAR POR DATOS DINAMICOS
                Map<String, Object> parametro = new HashMap<>();
                int id = Integer.parseInt(vista.getTablaReserva().getValueAt(vista.getTablaReserva().getSelectedRow(), 0).toString());
                parametro.put("idCliente", id);
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

    private void disableDates() {
        vista.getDtchSalida().getDateEditor().setEnabled(false);
        vista.getDtchoEntrada().getDateEditor().setEnabled(false);
    }

    private boolean success() {
        boolean clienteValido = vista.getComboClientes().getSelectedItem() != null;
        boolean habitacionValida = vista.getComboHabitacion().getSelectedItem() != null;
        boolean fechaEntradaValida = vista.getDtchoEntrada().getDate() != null;
        boolean fechaSalidaValida = vista.getDtchSalida().getDate() != null;
        boolean total = vista.getTxtTotalRes().getText() != null;

        return clienteValido && habitacionValida && fechaEntradaValida && fechaSalidaValida && total;
    }

    private boolean fechaValidator() {
        return vista.getDtchoEntrada().getDate().before(vista.getDtchSalida().getDate());
    }
}
