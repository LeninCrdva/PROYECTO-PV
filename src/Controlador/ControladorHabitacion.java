package Controlador;

import Modelo.ConnectionPG;
import Modelo.Habitacion;
import Modelo.ModeloHabitación;
import Modelo.ModeloTipo_Habitacion;
import Modelo.Tipo_Habitacion;
import Vista.VistaHabitacion;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ControladorHabitacion {

    private ModeloHabitación mdHab;
    private ModeloTipo_Habitacion mdTip;
    private VistaHabitacion vista;

    public ControladorHabitacion() {
    }

    public ControladorHabitacion(ModeloHabitación mdHab, ModeloTipo_Habitacion mdTip, VistaHabitacion vista) {
        this.mdHab = mdHab;
        this.mdTip = mdTip;
        this.vista = vista;
        vista.setVisible(true);
    }

    public void iniciarControl() {
        addItem();
        automaticRd();
        cargarHabitacion();
        cargarTpHab();
        cargarComboTip();
        vista.getBtnCrear().addActionListener(l -> abrirDialogo(1));
        vista.getBtnEditar().addActionListener(l -> abrirDialogo(2));
        vista.getBtnAceptarHab().addActionListener(l -> crearEditarHab());
        vista.getBtnAceptarTipo().addActionListener(l -> crearEditarTipHab());
        vista.getBtnCancelarHab().addActionListener(l -> cancelDialog());
        vista.getBtnCancelarTipo().addActionListener(l -> cancelTpDialog());
        vista.getBtnEliminar().addActionListener(l -> eliminarDate());
        vista.getTxtBuscarHab().addActionListener(l -> buscarHabitacion());
        vista.getTxtBuscarTip().addActionListener(l -> buscarTpHabitacion());
        vista.getBtnInfo().addActionListener(l -> showHelp());
        vista.getBtnPrint().addActionListener(l -> Reportes());
    }

    private void cargarHabitacion() {
        List<Habitacion> lista = mdHab.ListHabitacion();
        List<Tipo_Habitacion> listat = mdTip.ListTipoHab();

        DefaultTableModel mTabla;
        mTabla = (DefaultTableModel) vista.getTableHabitacion().getModel();
        mTabla.setNumRows(0); //Limpio la tabla
        vista.getTableHabitacion().getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        vista.getTableHabitacion().getTableHeader().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        vista.getTableHabitacion().getTableHeader().setOpaque(false);
        vista.getTableHabitacion().getTableHeader().setBackground(new Color(32, 136, 203));
        vista.getTableHabitacion().getTableHeader().setForeground(new Color(255, 255, 255));
        vista.getTableHabitacion().setRowHeight(25);

        lista.stream().forEach(hb -> {
            listat.stream().forEach(tph -> {
                if (hb.getIdTipo_hab() == tph.getId_tha()) {
                    String[] fila = {String.valueOf(hb.getId_hab()), String.valueOf(hb.getNumero_hab()), tph.getNombre_tha(), String.valueOf(hb.isEstado_hab())};
                    mTabla.addRow(fila);
                }
            });
        });
    }

    private void abrirDialogo(int ce) {
        if (vista.getRdHab().isSelected() || vista.getRdTipHab().isSelected()) {
            EnableFields(InitClean());
            if (vista.getRdHab().isSelected()) {
                abrirCrearHab(ce);
            } else if (vista.getRdTipHab().isSelected()) {
                abrirCrearTipHab(ce);
            }
        } else {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN BOTÓN <<Tipo de Habitación - Habitación>>\nPARA CONTINUAR.");
        }
    }

    private void eliminarDate() {
        if (vista.getRdHab().isSelected() || vista.getRdTipHab().isSelected()) {
            if (vista.getRdHab().isSelected()) {
                eliminarHabitacion(vista.getTableHabitacion());
            } else if (vista.getRdTipHab().isSelected()) {
                eliminarTpHabitacion(vista.getTableTipoHabitacion());
            }
            cargarHabitacion();
            cargarTpHab();
        } else {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN BOTÓN <<Tipo de Habitación - Habitación>>\nPARA CONTINUAR.");
        }
    }

    private void abrirCrearHab(int ce) {
        String title;
        boolean openwindow = false;
        if (ce == 1) {
            title = "Crear Habitacion";
            vista.getDialogHabi().setName("crear");
            openwindow = true;
        } else {
            title = "Editar Habitacion";
            vista.getDialogHabi().setName("editar");
            try {
                openwindow = uploadDates(vista.getTableHabitacion());
            } catch (ParseException ex) {
                Logger.getLogger(ControladorHabitacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (openwindow) {
            vista.getDialogHabi().setLocationRelativeTo(null);
            vista.getDialogHabi().setSize(290, 275);
            vista.getDialogHabi().setTitle(title);
            vista.getDialogHabi().setVisible(true);
        }
    }

    private void crearEditarHab() {
        if (vista.getDialogHabi().getName().equals("crear")) {
            //INSERTAR
            if (successHab()) {
                Tipo_Habitacion tipo = (Tipo_Habitacion) vista.getComboTipoHab().getSelectedItem();
                int numhab = Integer.parseInt(vista.getTxtNumHab().getText().trim());
                boolean estado = vista.getCheckDisponible().isSelected();

                ModeloHabitación hb = new ModeloHabitación();
                hb.setIdTipo_hab(tipo.getId_tha());
                hb.setNumero_hab(numhab);
                hb.setEstado_hab(estado);

                SQLException ex;

                if (hb.existHab() == 0) {
                    if ((ex = hb.CrearHabitacionDB()) == null) {
                        JOptionPane.showMessageDialog(null, "Se ha creado correctamente la habitación");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ocurrió un error al crear la habitación:\n" + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El número de habitación ya ha sido registrado");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos");
            }

        } else if (vista.getDialogHabi().getName().equals("editar")) {
            //EDITAR
            if (successHab()) {
                int idhab = Integer.parseInt(vista.getLabelIDHabi().getText().trim());
                int idtip = ((Tipo_Habitacion) vista.getComboTipoHab().getSelectedItem()).getId_tha();
                int numhab = Integer.parseInt(vista.getTxtNumHab().getText().trim());
                boolean estado = vista.getCheckDisponible().isSelected();

                ModeloHabitación hb = new ModeloHabitación();
                hb.setIdTipo_hab(hb.getId(vista.getComboTipoHab().getSelectedItem().toString()));
                hb.setId_hab(idhab);
                hb.setNumero_hab(numhab);
                hb.setEstado_hab(estado);

                System.out.println(idtip);

                if (hb.existHab() == 0) {
                    if (hb.EditHabitacionDB() == null) {
                        JOptionPane.showMessageDialog(null, "SE HA EDITADO LOS DATOS DE LA HABITACIÓN CON ÉXITO");
                    } else {
                        JOptionPane.showMessageDialog(null, "NO SE HA PODIDO EDITAR LA HABITACIÓN SELECCIONADA");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El número de habitación ya ha sido registrado");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos");
            }

        }
        cleanCamps();
        cargarHabitacion();
        vista.getDialogHabi().dispose();
    }

    private void eliminarHabitacion(JTable table) {
        ModeloHabitación habitacion = new ModeloHabitación();
        if (table.getSelectedRowCount() == 1) {
            habitacion.setId_hab(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
            if (habitacion.cancelDelete() == 0) {
                if (habitacion.DeletePhisicHabitacion() == null) {
                    JOptionPane.showMessageDialog(null, "SE HA ELIMNADO LA HABITACIÓN CON ÉXITO");
                } else {
                    JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR LA HABITACIÓN");
                }
            } else {
                JOptionPane.showMessageDialog(null, "NO SE PERMITE ESTA ACCIÓN, YA HA SIDO UTILIZADO", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "NECESITA SELECCIONAR UNA FILA PRIMERO");
        }
    }

    private void cancelDialog() {
        cleanCamps();
        vista.getDialogHabi().dispose();
    }

    private void buscarHabitacion() {
        ModeloHabitación hab = new ModeloHabitación();
        ModeloTipo_Habitacion tip = new ModeloTipo_Habitacion();
        hab.setNumero_hab(Integer.parseInt(vista.getTxtBuscarHab().getText().trim()));

        List<Habitacion> listah = hab.SearchListHabitacion();

        List<Tipo_Habitacion> listat = tip.ListTipoHab();

        if (listah.isEmpty()) {
            JOptionPane.showMessageDialog(null, "NO SE HA ENCONTRADO UNA HABITACIÓN CON EL NÚMERO ESPECIFICADO");
        } else {
            DefaultTableModel mTabla;
            mTabla = (DefaultTableModel) vista.getTableHabitacion().getModel();
            mTabla.setNumRows(0); //Limpio la tabla
            vista.getTableHabitacion().getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
            vista.getTableHabitacion().getTableHeader().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            vista.getTableHabitacion().getTableHeader().setOpaque(false);
            vista.getTableHabitacion().getTableHeader().setBackground(new Color(32, 136, 203));
            vista.getTableHabitacion().getTableHeader().setForeground(new Color(255, 255, 255));
            vista.getTableHabitacion().setRowHeight(25);

            listah.stream().forEach(hb -> {
                listat.stream().forEach(tph -> {
                    if (hb.getIdTipo_hab() == tph.getId_tha()) {
                        String[] fila = {String.valueOf(hb.getNumero_hab()), tph.getNombre_tha(), String.valueOf(hb.isEstado_hab())};
                        mTabla.addRow(fila);
                    }
                });
            });
        }
    }

    private void cargarComboTip() {
        vista.getComboTipoHab().removeAllItems();

        DefaultComboBoxModel<Tipo_Habitacion> modelo = new DefaultComboBoxModel<>();
        vista.getComboTipoHab().setModel(modelo);

        ModeloTipo_Habitacion modelop = new ModeloTipo_Habitacion();
        List<Tipo_Habitacion> listap = modelop.ListTipoHab();

        listap.stream().forEach(pe -> {
            vista.getComboTipoHab().addItem(new Tipo_Habitacion(pe.getId_tha(), pe.getNombre_tha()));
        });
    }

    private boolean uploadDates(JTable table) throws ParseException {
        boolean a = false;
        if (table.getSelectedRowCount() == 1) {
            a = true;
            ModeloTipo_Habitacion tip = new ModeloTipo_Habitacion();
            tip.setNombre_tha(vista.getTableHabitacion().getValueAt(vista.getTableHabitacion().getSelectedRow(), 2).toString());
            vista.getLabelIDHabi().setText(vista.getTableHabitacion().getValueAt(vista.getTableHabitacion().getSelectedRow(), 0).toString());
            vista.getComboTipoHab().getModel().setSelectedItem(tip);
            vista.getTxtNumHab().setText(vista.getTableHabitacion().getValueAt(vista.getTableHabitacion().getSelectedRow(), 1).toString());
            vista.getCheckDisponible().setSelected(Boolean.parseBoolean(vista.getTableHabitacion().getValueAt(vista.getTableHabitacion().getSelectedRow(), 3).toString()));
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA DE LA TABLA");
        }
        return a;
    }

    private void cleanCamps() {
        vista.getLabelIDHabi().setText("");
        vista.getComboTipoHab().setSelectedItem(null);
        vista.getTxtNumHab().setText("");
        vista.getCheckDisponible().setSelected(false);
    }

    private void cargarTpHab() {
        List<Tipo_Habitacion> lista = mdTip.ListTipoHab();

        DefaultTableModel mTabla;
        mTabla = (DefaultTableModel) vista.getTableTipoHabitacion().getModel();
        mTabla.setNumRows(0);
        vista.getTableTipoHabitacion().getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        vista.getTableTipoHabitacion().getTableHeader().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        vista.getTableTipoHabitacion().getTableHeader().setOpaque(false);
        vista.getTableTipoHabitacion().getTableHeader().setBackground(new Color(32, 136, 203));
        vista.getTableTipoHabitacion().getTableHeader().setForeground(new Color(255, 255, 255));
        vista.getTableTipoHabitacion().setRowHeight(25);

        lista.stream().forEach(tph -> {
            String[] fila = {String.valueOf(tph.getId_tha()), tph.getNombre_tha(), String.valueOf(tph.getNumeroCamas_tha()), String.valueOf(tph.getCapacidad_tha()),
                String.valueOf(tph.getPrecio_tha())};
            mTabla.addRow(fila);
        });
    }

    private void abrirCrearTipHab(int ce) {
        String title;
        boolean openwindow = false;
        if (ce == 1) {
            title = "Crear Tipo de Habitacion";
            vista.getDialogTipo().setName("crear");
            openwindow = true;
        } else {
            title = "Editar Tipo de Habitacion";
            vista.getDialogTipo().setName("editar");
            try {
                openwindow = uploadTpDates(vista.getTableTipoHabitacion());
            } catch (ParseException ex) {
                Logger.getLogger(ControladorHabitacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (openwindow) {
            vista.getDialogTipo().setLocationRelativeTo(null);
            vista.getDialogTipo().setSize(310, 300);
            vista.getDialogTipo().setTitle(title);
            vista.getDialogTipo().setVisible(true);
        }
    }

    private void crearEditarTipHab() {
        if (vista.getDialogTipo().getName().equals("crear")) {
            //INSERTAR
            if (successTip()) {
                String nombre = vista.getTxtNombreTipo().getText().trim();
                int numcamas = Integer.parseInt(vista.getTxtNumCama().getText().trim());
                int capacidad = Integer.parseInt(vista.getTxtCapacidad().getText().trim());
                double precio = Double.parseDouble(vista.getTxtPrecio().getText().replace(',', '.').trim());

                ModeloTipo_Habitacion thb = new ModeloTipo_Habitacion();
                thb.setNombre_tha(nombre);
                thb.setNumeroCamas_tha(numcamas);
                thb.setCapacidad_tha(capacidad);
                thb.setPrecio_tha(precio);

                if (thb.existHab() == 0) {
                    if (thb.CrearTipoHabitacionBD() == null) {
                        JOptionPane.showMessageDialog(null, "SE HA CREADO EL TIPO DE HABITACIÓN CON ÉXITO");
                    } else {
                        JOptionPane.showMessageDialog(null, "NO SE HA PODIDO CREAR EL TIPO DE HABITACIÓN");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El nombre utilizado en concreto ya existe");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Asegúrese de completar todos los campos");
            }

        } else if (vista.getDialogTipo().getName().equals("editar")) {
            //EDITAR
            if (successTip()) {
                int id = Integer.parseInt(vista.getLabelIDTipo().getText().trim());
                String nombre = vista.getTxtNombreTipo().getText().trim();
                int numcamas = Integer.parseInt(vista.getTxtNumCama().getText().trim());
                int capacidad = Integer.parseInt(vista.getTxtCapacidad().getText().trim());
                double precio = Double.parseDouble(vista.getTxtPrecio().getText().replace(',', '.').trim());

                ModeloTipo_Habitacion thb = new ModeloTipo_Habitacion();
                thb.setId_tha(id);
                thb.setNombre_tha(nombre);
                thb.setNumeroCamas_tha(numcamas);
                thb.setCapacidad_tha(capacidad);
                thb.setPrecio_tha(precio);

                if (thb.existHabi() != 0) {
                    if (thb.EditTipHab() == null) {
                        JOptionPane.showMessageDialog(null, "Se han editado correctamente los datos");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo guardar los datos en cuestión");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El nombre utilizado ya existe");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Asegúrese de completar todos los campos");
            }

        }
        cleanTpCamps();
        cargarTpHab();
        cargarHabitacion();
        cargarComboTip();
        vista.getDialogTipo().dispose();
    }

    private void eliminarTpHabitacion(JTable table) {
        ModeloTipo_Habitacion habitacion = new ModeloTipo_Habitacion();
        if (table.getSelectedRowCount() == 1) {
            habitacion.setId_tha(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
            if (habitacion.cancelDelete() == 0) {
                if (habitacion.DeleteTipHab() == null) {
                    JOptionPane.showMessageDialog(null, "SE HA ELIMNADO EL TIPO DE HABITACION CON ÉXITO");
                } else {
                    JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL TIPO DE HABITACION");
                }
            } else {
                JOptionPane.showMessageDialog(null, "NO SE PERMITE ESTA ACCIÓN PORQUE ESTE TIPO HA SIDO UTILIZADO", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "NECESITA SELECCIONAR UNA FILA PRIMERO");
        }
    }

    private void cancelTpDialog() {
        cleanTpCamps();
        vista.getDialogHabi().dispose();
    }

    private void buscarTpHabitacion() {
        ModeloTipo_Habitacion tip = new ModeloTipo_Habitacion();
        String col = "";

        switch (vista.getComboTipo().getSelectedItem().toString()) {
            case "Nombre":
                col = "nombre_tha";
                break;
            case "Nº camas":
                col = "numerocamas_tha";
                break;
            case "Capacidad":
                col = "capacidad_tha";
                break;
            case "Precio":
                col = "precio_tha";
                break;
        }

        String valor = vista.getTxtBuscarTip().getText().trim();
        List<Tipo_Habitacion> listah;

        if (col.equals("nombre_tha")) {
            listah = tip.ListTipHabSearch(col, "\'" + valor + "\'");
        } else {
            listah = tip.ListTipHabSearch(col, valor);
        }

        if (listah.isEmpty()) {
            JOptionPane.showMessageDialog(null, "NO SE HA ENCONTRADO UN TIPO DE HABITACIÓN CON LOS PARÁMETROS ESPECIFICADOS");
        } else {
            DefaultTableModel mTabla;
            mTabla = (DefaultTableModel) vista.getTableTipoHabitacion().getModel();
            mTabla.setNumRows(0); //Limpio la tabla
            vista.getTableTipoHabitacion().getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
            vista.getTableTipoHabitacion().getTableHeader().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            vista.getTableTipoHabitacion().getTableHeader().setOpaque(false);
            vista.getTableTipoHabitacion().getTableHeader().setBackground(new Color(32, 136, 203));
            vista.getTableTipoHabitacion().getTableHeader().setForeground(new Color(255, 255, 255));
            vista.getTableTipoHabitacion().setRowHeight(25);

            listah.stream().forEach(tp -> {
                String[] fila = {String.valueOf(tp.getId_tha()), tp.getNombre_tha(), String.valueOf(tp.getNumeroCamas_tha()), String.valueOf(tp.getCapacidad_tha()), String.valueOf(tp.getPrecio_tha())};
                mTabla.addRow(fila);
            });
        }
    }

    private boolean uploadTpDates(JTable table) throws ParseException {
        boolean a = false;
        if (table.getSelectedRowCount() == 1) {
            a = true;
            ModeloTipo_Habitacion tip = new ModeloTipo_Habitacion();
            tip.setNombre_tha(vista.getTableTipoHabitacion().getValueAt(vista.getTableTipoHabitacion().getSelectedRow(), 1).toString());
            vista.getLabelIDHabi().setText(vista.getTableTipoHabitacion().getValueAt(vista.getTableTipoHabitacion().getSelectedRow(), 0).toString());
            vista.getComboTipoHab().setSelectedItem(tip);
            vista.getTxtNumHab().setText(vista.getTableTipoHabitacion().getValueAt(vista.getTableTipoHabitacion().getSelectedRow(), 1).toString());
            vista.getCheckDisponible().setSelected(Boolean.parseBoolean(vista.getTableTipoHabitacion().getValueAt(vista.getTableTipoHabitacion().getSelectedRow(), 3).toString()));

            vista.getLabelIDTipo().setText(table.getValueAt(table.getSelectedRow(), 0).toString());
            vista.getTxtNombreTipo().setText(table.getValueAt(table.getSelectedRow(), 1).toString());
            vista.getTxtNumCama().setText(table.getValueAt(table.getSelectedRow(), 2).toString());
            vista.getTxtCapacidad().setText(table.getValueAt(table.getSelectedRow(), 3).toString());
            vista.getTxtPrecio().setText(table.getValueAt(table.getSelectedRow(), 4).toString());
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA DE LA TABLA");
        }
        return a;
    }

    private void cleanTpCamps() {
        vista.getLabelIDTipo().setText("");
        vista.getTxtNombreTipo().setText("");
        vista.getTxtNumCama().setText("");
        vista.getTxtCapacidad().setText("");
        vista.getTxtPrecio().setText("");
    }

    private void automaticRd() {
        if (vista.getTableHabitacion().getSelectedRowCount() > 1) {
            vista.getRdHab().setSelected(true);
        } else if (vista.getTableTipoHabitacion().getSelectedRowCount() > 1) {
            vista.getRdTipHab().setSelected(true);
        }
    }

    private void CleanFields(Component[] components) {
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(null);
            } else if (component instanceof JDateChooser) {
                ((JDateChooser) component).setCalendar(null);
            } else if (component instanceof JComboBox) {
                ((JComboBox) component).setSelectedIndex(-1);
            } else if (component instanceof JCheckBox) {
                ((JCheckBox) component).setSelected(false);
            }
        }
    }

    private Component[] InitClean() {
        Component[] com = {
            vista.getLabelIDHabi(),
            vista.getLabelIDTipo(),
            vista.getTxtBuscarHab(),
            vista.getTxtBuscarTip(),
            vista.getTxtCapacidad(),
            vista.getTxtNombreTipo(),
            vista.getTxtNumCama(),
            vista.getTxtNumHab(),
            vista.getTxtPrecio(),
            vista.getComboTipo(),
            vista.getComboTipoHab()
        };
        CleanFields(com);
        vista.getLabelIDHabi().setText("");
        vista.getLabelIDTipo().setText("");
        return com;
    }

    private void EnableFields(Component[] components) {
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setEnabled(true);
            } else if (component instanceof JDateChooser) {
                ((JDateChooser) component).setEnabled(false);
                ((JDateChooser) component).getCalendarButton().setEnabled(true);
            } else if (component instanceof JComboBox) {
                ((JComboBox) component).setEnabled(true);
            }
        }
    }

    private boolean successHab() {
        boolean tipoValido = vista.getComboTipoHab().getSelectedItem() != null;
        boolean numHab = !vista.getTxtNumHab().getText().trim().isEmpty();

        return tipoValido && numHab;
    }

    private boolean successTip() {
        boolean nombre = !vista.getTxtNombreTipo().getText().trim().isEmpty();
        boolean camas = !vista.getTxtNumCama().getText().isEmpty();
        boolean capacidad = !vista.getTxtCapacidad().getText().isEmpty();
        boolean precio = !vista.getTxtPrecio().getText().isEmpty();

        return nombre && camas && capacidad && precio;
    }

    private void addItem() {
        JComboBox combo = vista.getComboTipo();
        DefaultComboBoxModel<String> mCombo = new DefaultComboBoxModel<>();

        combo.setModel(mCombo);

        combo.addItem("Nombre");
        combo.addItem("Nº camas");
        combo.addItem("Capacidad");
        combo.addItem("Precio");

        if (combo.getItemCount() > 0) {
            combo.setSelectedIndex(0);
        }
    }

    private void Reportes() {
        if (vista.getRdHab().isSelected()) {
            ConnectionPG con = new ConnectionPG();
            try {
                JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/Vista/Reportes/Habitacion.jasper"));
                JasperPrint jp = JasperFillManager.fillReport(jr, null, con.getCon());
                JasperViewer jv = new JasperViewer(jp, false);

                jv.setVisible(true);

            } catch (JRException | NumberFormatException ex) {
                Logger.getLogger(ControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Esta función está disponible únicamente para la habitación.","Información" ,JOptionPane.WARNING_MESSAGE);
        }

    }

    private void showHelp() {
        JOptionPane.showMessageDialog(null, "No olvide seleccionar uno de los botones de la izquierda para iniciar con el funcionamiento", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }
}
