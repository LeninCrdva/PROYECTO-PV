package Controlador;

import Modelo.Det_reserva;
import Modelo.Empleado;
import Modelo.Enc_reserva;
import Modelo.ModeloDetReserva;
import Modelo.ModeloEncReserva;
import Vista.VistaReserva;
import java.awt.event.MouseAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControladorReserva {
    private ModeloEncReserva mdEnc;
    private ModeloDetReserva mdDet;
    private VistaReserva vista;

    public ControladorReserva() {
    }

    public ControladorReserva(ModeloEncReserva mdEnc, ModeloDetReserva mdDet, VistaReserva vista) {
        this.mdEnc = mdEnc;
        this.mdDet = mdDet;
        this.vista = vista;
        vista.setVisible(true);
    }
    
    public void iniciarControl(){
        showDetails(vista.getTablaReserva());
        cargarEncabezado();
        vista.getBtnCrear().addActionListener(l -> abrirCrearHab(1));
        vista.getBtnEditar().addActionListener(l -> abrirCrearHab(2));
        vista.getBtnAceptar().addActionListener(l -> crearEditarHab());
        vista.getBtnCancelar().addActionListener(l -> cancelDialog());
    }
    
    private void cargarEncabezado() {
        List<Enc_reserva> lista = mdEnc.ListEnc();

        DefaultTableModel mTabla;
        mTabla = (DefaultTableModel) vista.getTablaReserva().getModel();
        mTabla.setNumRows(0); //Limpio la tabla

        lista.stream().forEach(ec -> {
            String [] fila = {String.valueOf(ec.getId_res()), String.valueOf(ec.getIdCliente_res()), String.valueOf(ec.getFechaIngreso_res()), String.valueOf(ec.getFechaSalida_res()), String.valueOf(ec.getTotal_res()), String.valueOf(ec.isEstado_res())};
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
            vista.getDialogReserva().setLocationRelativeTo(null);
            vista.getDialogReserva().setSize(460, 400);
            vista.getDialogReserva().setTitle(title);
            vista.getDialogReserva().setVisible(true);
        }
    }

    private void crearEditarHab() {
        //cargarComboTip();
        if (vista.getDialogReserva().getName().equals("crear")) {
            //INSERTAR
            boolean msg = false;
            //Cambiar de Empleado a cliente
            Empleado numtipo = (Empleado) vista.getComboClientes().getSelectedItem();
            Date entrada = vista.getDtchoEntrada().getDate();
            Date salida = vista.getDtchSalida().getDate();
            double total = Double.parseDouble(vista.getTxtTotalRes().getText());

            ModeloEncReserva hb = new ModeloEncReserva();
            ModeloDetReserva dt = new ModeloDetReserva();
            Det_reserva det = new Det_reserva();
            hb.setIdCliente_res(numtipo.getId_emp());
            hb.setFechaIngreso_res(entrada);
            hb.setFechaSalida_res(salida);
            hb.setTotal_res(total);
            
            for(int i = 0; i < vista.getTablaClientes().getRowCount(); i++){
                det.setIdCliente_rha(Integer.parseInt(vista.getTablaClientes().getValueAt(i, 0).toString()));
                det.setIdHabitacion_rha(Integer.parseInt(vista.getTablaClientes().getValueAt(i, 2).toString()));
                det.setIdReserva_rha(hb.CrearReserva());
                msg = dt.crearDetail(det) == null;
            }

            if (msg) {
                JOptionPane.showMessageDialog(null, "SE HA CREADO LA RESERVA CON ÉXITO");
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO CREAR LA RESERVA");
            }
        } else if (vista.getDialogReserva().getName().equals("editar")) {
            //EDITAR
            int id = Integer.parseInt(vista.getLabelId().getText());
            Empleado numtipo = (Empleado) vista.getComboClientes().getSelectedItem();
            Date entrada = vista.getDtchoEntrada().getDate();
            Date salida = vista.getDtchSalida().getDate();
            double total = Double.parseDouble(vista.getTxtTotalRes().getText());

            ModeloEncReserva hb = new ModeloEncReserva();
            hb.setId_res(id);
            hb.setIdCliente_res(numtipo.getId_emp());
            hb.setFechaIngreso_res(entrada);
            hb.setFechaSalida_res(salida);
            hb.setTotal_res(total);

            if (hb.EditarReserva()== null) {
                JOptionPane.showMessageDialog(null, "SE HA EDITADO LOS DATOS DE LA HABITACIÓN CON ÉXITO");
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO EDITAR LA HABITACIÓN SELECCIONADA");
            }
        }
        cleanCamps();
        cargarEncabezado();
        vista.getDialogReserva().dispose();
    }

    private void cancelDialog() {
        cleanCamps();
        vista.getDialogReserva().dispose();
    }

//    private void cargarComboTip() {
//        ModeloTipo_Habitacion modelop = new ModeloTipo_Habitacion();
//        List<Tipo_Habitacion> listap = modelop.ListTipoHab();
//
//        listap.stream().forEach(pe -> {
//            vista.getCombTipSelc().addItem(new Tipo_Habitacion(pe.getId_tha(), pe.getNombre_tha()));
//        });
//    }

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
    
    private void showDetails(JTable table){
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt){
                if(evt.getClickCount() == 2){
                    JOptionPane.showMessageDialog(null, "Logrado");
                }
            }
        });
    }
}
