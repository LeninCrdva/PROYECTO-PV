package Controlador;

import Modelo.Habitacion;
import Modelo.ModeloHabitación;
import Modelo.ModeloTipo_Habitacion;
import Modelo.Tipo_Habitacion;
import Vista.VistaHabitacion;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
        cargarHabitacion();
        cargarTpHab();
        cargarComboTip();
        vista.getBtnCrear().addActionListener(l -> abrirDialogo(1));
        vista.getBtnEditar().addActionListener(l -> abrirDialogo(2));
        vista.getBtnAceptarHab().addActionListener(l -> crearEditarHab());
        vista.getBtnAceptarTipo().addActionListener(l -> crearEditarTipHab());
        vista.getBtnCancelarHab().addActionListener(l -> cancelDialog());
        vista.getBtnCancelarTipo().addActionListener(l -> cancelTpDialog());
        vista.getBtnEliminar().addActionListener(l -> eliminarDate(vista.getTableHabitacion()));
        vista.getTxtBuscarHab().addActionListener(l -> buscarHabitacion());
        vista.getTxtBuscarTip().addActionListener(l -> buscarTpHabitacion());
    }

    private void cargarHabitacion() {
        List<Habitacion> lista = mdHab.ListHabitacion();
        List<Tipo_Habitacion> listat = mdTip.ListTipoHab();

        DefaultTableModel mTabla;
        mTabla = (DefaultTableModel) vista.getTableHabitacion().getModel();
        mTabla.setNumRows(0); //Limpio la tabla

        lista.stream().forEach(hb -> {
            listat.stream().forEach(tph -> {
                if (hb.getIdTipo_hab() == tph.getId_tha()) {
                    String[] fila = {String.valueOf(hb.getId_hab()), String.valueOf(hb.getNumero_hab()), tph.getNombre_tha(), String.valueOf(hb.isEstado_hab())};
                    mTabla.addRow(fila);
                }
            });
        });
    }
    
    private void abrirDialogo(int ce){
        if(vista.getRdHab().isSelected() || vista.getRdTipHab().isSelected()){
            if(vista.getRdHab().isSelected()){
                abrirCrearHab(ce);
            }else if(vista.getRdTipHab().isSelected()){
                abrirCrearTipHab(ce);
            }
        }else{
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN BOTÓN <<Tipo de Habitación - Habitación>>\nPARA CONTINUAR.");
        }
    }
    
    private void eliminarDate(JTable table){
        if(vista.getRdHab().isSelected() || vista.getRdTipHab().isSelected()){
            if(vista.getRdHab().isSelected()){
                eliminarHabitacion(table);
            }else if(vista.getRdTipHab().isSelected()){
                eliminarTpHabitacion(table);
            }
        }else{
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
        cargarComboTip();
        if (vista.getDialogHabi().getName().equals("crear")) {
            //INSERTAR
            int numtipo = vista.getCombTipSelc().getSelectedIndex() + 1;
            int numhab = Integer.parseInt(vista.getTxtNumHab().getText());
            boolean estado = vista.getCheckDisponible().isSelected();

            ModeloHabitación hb = new ModeloHabitación();
            hb.setIdTipo_hab(numtipo);
            hb.setNumero_hab(numhab);
            hb.setEstado_hab(estado);

            if (hb.CrearHabitacionDB() == null) {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO CREAR LA HABITACIÓN");
            } else {
                JOptionPane.showMessageDialog(null, "SE HA CREADO LA HABITACIÓN CON ÉXITO");
            }
        } else if (vista.getDialogHabi().getName().equals("editar")) {
            //EDITAR
            int idhab = Integer.parseInt(vista.getLabelIDHabi().getText());
            int numtipo = vista.getCombTipSelc().getSelectedIndex() + 1;
            int numhab = Integer.parseInt(vista.getTxtNumHab().getText());
            boolean estado = vista.getCheckDisponible().isSelected();

            ModeloHabitación hb = new ModeloHabitación();
            hb.setId_hab(idhab);
            hb.setIdTipo_hab(numtipo);
            hb.setNumero_hab(numhab);
            hb.setEstado_hab(estado);

            if (hb.EditHabitacionDB() == null) {
                JOptionPane.showMessageDialog(null, "SE HA EDITADO LOS DATOS DE LA HABITACIÓN CON ÉXITO");
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO EDITAR LA HABITACIÓN SELECCIONADA");
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
        } else {
            JOptionPane.showMessageDialog(null, "NECESITA SELECCIONAR UNA FILA PRIMERO");
        }

        if (habitacion.DeletePhisicHabitacion() == null) {
            JOptionPane.showMessageDialog(null, "SE HA ELIMNADO LA HABITACIÓN CON ÉXITO");
        } else {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR LA HABITACIÓN");
        }
    }

    private void cancelDialog() {
        cleanCamps();
        vista.getDialogHabi().dispose();
    }

    private void buscarHabitacion() {
        ModeloHabitación hab = new ModeloHabitación();
        ModeloTipo_Habitacion tip = new ModeloTipo_Habitacion();
        hab.setNumero_hab(Integer.parseInt(vista.getTxtBuscarHab().getText()));

        List<Habitacion> listah = hab.SearchListHabitacion();

        List<Tipo_Habitacion> listat = tip.ListTipoHab();

        if (listah.isEmpty()) {
            JOptionPane.showMessageDialog(null, "NO SE HA ENCONTRADO UNA HABITACIÓN CON EL NÚMERO ESPECIFICADO");
        } else {
            DefaultTableModel mTabla;
            mTabla = (DefaultTableModel) vista.getTableHabitacion().getModel();
            mTabla.setNumRows(0); //Limpio la tabla
            
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
        ModeloTipo_Habitacion modelop = new ModeloTipo_Habitacion();
        List<Tipo_Habitacion> listap = modelop.ListTipoHab();

        listap.stream().forEach(pe -> {
            vista.getCombTipSelc().addItem(new Tipo_Habitacion(pe.getId_tha(), pe.getNombre_tha()));
        });
    }

    private boolean uploadDates(JTable table) throws ParseException {
        boolean a = false;
        if (table.getSelectedRowCount() == 1) {
            a = true;
            ModeloTipo_Habitacion tip = new ModeloTipo_Habitacion();
            tip.setNombre_tha(vista.getTableHabitacion().getValueAt(vista.getTableHabitacion().getSelectedRow(), 2).toString());
            vista.getLabelIDHabi().setText(vista.getTableHabitacion().getValueAt(vista.getTableHabitacion().getSelectedRow(), 0).toString());
            vista.getCombTipSelc().setSelectedIndex(tip.getId() - 1);
            vista.getTxtNumHab().setText(vista.getTableHabitacion().getValueAt(vista.getTableHabitacion().getSelectedRow(), 1).toString());
            vista.getCheckDisponible().setSelected(Boolean.parseBoolean(vista.getTableHabitacion().getValueAt(vista.getTableHabitacion().getSelectedRow(), 3).toString()));
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA DE LA TABLA");
        }
        return a;
    }

    private void cleanCamps() {
        vista.getLabelIDHabi().setText("");
        vista.getCombTipSelc().setSelectedItem(null);
        vista.getTxtNumHab().setText("");
        vista.getCheckDisponible().setSelected(false);
    }

    private void cargarTpHab() {
        List<Tipo_Habitacion> lista = mdTip.ListTipoHab();

        DefaultTableModel mTabla;
        mTabla = (DefaultTableModel) vista.getTableTipoHabitacion().getModel();
        mTabla.setNumRows(0);

        lista.stream().forEach(tph -> {
            String[] fila = {String.valueOf(tph.getId_tha()),tph.getNombre_tha(), String.valueOf(tph.getNumeroCamas_tha()), String.valueOf(tph.getCapacidad_tha()),
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
            vista.getDialogHabi().setName("editar");
            try {
                openwindow = uploadTpDates(vista.getTableHabitacion());
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
            String nombre = vista.getTxtNombreTipo().getText();
            int numcamas = Integer.parseInt(vista.getTxtNumCama().getText());
            int capacidad = Integer.parseInt(vista.getTxtCapacidad().getText());
            double precio = Double.parseDouble(vista.getTxtPrecio().getText());

            ModeloTipo_Habitacion thb = new ModeloTipo_Habitacion();
            thb.setNombre_tha(nombre);
            thb.setNumeroCamas_tha(numcamas);
            thb.setCapacidad_tha(capacidad);
            thb.setPrecio_tha(precio);

            if (thb.CrearTipoHabitacionBD() == null) {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO CREAR LA HABITACIÓN");
            } else {
                JOptionPane.showMessageDialog(null, "SE HA CREADO LA HABITACIÓN CON ÉXITO");
            }
        } else if (vista.getDialogTipo().getName().equals("editar")) {
            //EDITAR
            int id = Integer.parseInt(vista.getLabelIDTipo().getText());
            String nombre = vista.getTxtNombreTipo().getText();
            int numcamas = Integer.parseInt(vista.getTxtNumCama().getText());
            int capacidad = Integer.parseInt(vista.getTxtCapacidad().getText());
            double precio = Double.parseDouble(vista.getTxtPrecio().getText());

            ModeloTipo_Habitacion thb = new ModeloTipo_Habitacion();
            thb.setId_tha(id);
            thb.setNombre_tha(nombre);
            thb.setNumeroCamas_tha(numcamas);
            thb.setCapacidad_tha(capacidad);
            thb.setPrecio_tha(precio);

            if (thb.EditTipHab() == null) {
                JOptionPane.showMessageDialog(null, "SE HA EDITADO LOS DATOS DE LA HABITACIÓN CON ÉXITO");
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO EDITAR LA HABITACIÓN SELECCIONADA");
            }
        }
        cleanTpCamps();
        cargarTpHab();
        vista.getDialogTipo().dispose();
    }

    private void eliminarTpHabitacion(JTable table) {
        ModeloTipo_Habitacion habitacion = new ModeloTipo_Habitacion();
        if (table.getSelectedRowCount() == 1) {
            habitacion.setId_tha(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
        } else {
            JOptionPane.showMessageDialog(null, "NECESITA SELECCIONAR UNA FILA PRIMERO");
        }

        if (habitacion.DeleteTipHab() == null) {
            JOptionPane.showMessageDialog(null, "SE HA ELIMNADO EL TIPO DE HABITACION CON ÉXITO");
        } else {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL TIPO DE HABITACION");
        }
    }

    private void cancelTpDialog() {
        cleanTpCamps();
        vista.getDialogHabi().dispose();
    }
    
    private void buscarTpHabitacion() {
        ModeloTipo_Habitacion tip = new ModeloTipo_Habitacion();
        String col ="";
        
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
        
        String valor = vista.getTxtBuscarTip().getText();

        List<Tipo_Habitacion> listah = tip.ListTipHabSearch(col, valor);

        if (listah.isEmpty()) {
            JOptionPane.showMessageDialog(null, "NO SE HA ENCONTRADO UN TIPO DE HABITACIÓN CON LOS PARÁMETROS ESPECIFICADOS");
        } else {
            DefaultTableModel mTabla;
            mTabla = (DefaultTableModel) vista.getTableHabitacion().getModel();
            mTabla.setNumRows(0); //Limpio la tabla
            
            listah.stream().forEach(tp -> {
                String[] fila = {String.valueOf(tp.getId_tha()), tp.getNombre_tha(), String.valueOf(tp.getNumeroCamas_tha()), String.valueOf(tp.getCapacidad_tha()), String.valueOf(tp.getPrecio_tha())};
                mTabla.addRow(fila);
            });
        }
    }

    private boolean uploadTpDates(JTable table)  throws ParseException  {
        boolean a = false;
        if (table.getSelectedRowCount() == 1) {
            a = true;
            ModeloTipo_Habitacion tip = new ModeloTipo_Habitacion();
            tip.setNombre_tha(vista.getTableTipoHabitacion().getValueAt(vista.getTableTipoHabitacion().getSelectedRow(), 2).toString());
            vista.getLabelIDHabi().setText(vista.getTableTipoHabitacion().getValueAt(vista.getTableTipoHabitacion().getSelectedRow(), 0).toString());
            vista.getCombTipSelc().setSelectedIndex(tip.getId() - 1);
            vista.getTxtNumHab().setText(vista.getTableTipoHabitacion().getValueAt(vista.getTableTipoHabitacion().getSelectedRow(), 1).toString());
            vista.getCheckDisponible().setSelected(Boolean.parseBoolean(vista.getTableTipoHabitacion().getValueAt(vista.getTableTipoHabitacion().getSelectedRow(), 3).toString()));
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
}
