package Controlador;

import Modelo.*;
import Vista.*;
import Vista.VistaCuenta;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ControladorEmpleado {

    private final ModeloPersona mp;
    private final ModeloEmpleado me;
    private final VistaEmpleados ve;
    private final ModeloLabor ml;
    private final ModeloTipoDocumento mtd;

    public ControladorEmpleado(ModeloPersona mp, ModeloEmpleado me, VistaEmpleados ve, ModeloLabor ml, ModeloTipoDocumento mtd) {
        this.mp = mp;
        this.me = me;
        this.ve = ve;
        this.ml = ml;
        this.mtd = mtd;
        ve.setVisible(true);
    }

    public void IniciarControl() {
        CargaEmpleados();
        LlenaComboLabor();
        LlenaComboTipoDoc();
        IncrementaIDE();
        ValidarCampos();
        ve.getBtncrear().addActionListener(l -> AbreDialogo(1));
        ve.getBtncrear().addActionListener(l -> InitClean());
        ve.getBtneditar().addActionListener(l -> AbreDialogo(2));
        ve.getBtneliminar().addActionListener(l -> AbreDialogo(3));
        ve.getBtnaceptar().addActionListener(l -> crearEditarEliminarEmpleado());
        ve.getBtncancelar().addActionListener(l -> ve.getDlgcrudempleado().dispose());
        ve.getLblidper().setText(Integer.toString(IncrementaID()));
        ve.getBtnaddtipodoc().addActionListener(l -> AddToCombo(1));
        ve.getBtnaddlab().addActionListener(l -> AddToCombo(2));
    }

    private void ValidarCampos() {
        ve.getDtefechanac().setDateFormatString("yyyy-MM-dd");
        ve.getTxtbuscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BuscaEmpleados();
            }
        });
        addTextKeyListenerNotNumber(ve.getTxtnombreper(), 50);
        addTextKeyListenerNotNumber(ve.getTxtapellidoper(), 50);
        MaxLengthOnly(ve.getTxtdireccion(), 250);
        MaxLengthOnly(ve.getTxtemail(), 250);
        addTextKeyListenerNotText(ve.getTxttelefono(), 20);
    }

    private void addTextKeyListenerNotNumber(JTextField text, int maxLength) {
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char k = evt.getKeyChar();
                if (Character.isDigit(k) || text.getText().trim().length() >= maxLength) {
                    evt.consume();
                }
            }
        });
    }

    private void addTextKeyListenerNotText(JTextField text, int maxLength) {
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char k = evt.getKeyChar();
                if (Character.isLetter(k) || text.getText().trim().length() >= maxLength) {
                    evt.consume();
                }
            }
        });
    }

    private void MaxLengthOnly(JTextField text, int maxLength) {
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {

                if (text.getText().trim().length() >= maxLength) {
                    evt.consume();
                }
            }
        });
    }

    private void AddToCombo(int ce) {
        if (ce == 1) {
            VistaTipoDocumento vt = new VistaTipoDocumento();
            vt.getBtnaceptar().addActionListener(l -> UsaDialogoTipoDoc());
            vt.getBtncancelar().addActionListener(l -> vt.getDlgtipodoc().dispose());
            vt.getDlgtipodoc().setSize(290, 180);
            vt.getDlgtipodoc().setLocationRelativeTo(ve);
            vt.getDlgtipodoc().setVisible(true);
        } else {
            VistaLabor vl = new VistaLabor();
            vl.getBtnaceptar().addActionListener(l -> UsaDialogoLabor());
            vl.getBtncancelar().addActionListener(l -> vl.getDlgcrudlabor().dispose());
            vl.getDlgcrudlabor().setSize(580, 280);
            vl.getDlgcrudlabor().setLocationRelativeTo(ve);
            vl.getDlgcrudlabor().setVisible(true);
        }
    }

    private int IncrementaIDtipodoc() {

        int id_tip = mtd.ObtenerIDTDBD();
        if (id_tip >= 1) {
            id_tip++;
        } else {
        }
        return id_tip;
    }

    private int IncrementaIDlabor() {
        int id_tip = ml.ObtenerID();
        if (id_tip >= 1) {
            id_tip++;
        } else {
        }
        return id_tip;
    }

    private void UsaDialogoTipoDoc() {
        VistaTipoDocumento vt = new VistaTipoDocumento();
        try {
            int id_tip = IncrementaIDtipodoc();
            String nombre = vt.getTxtdocname().getText().toUpperCase().trim();

            ModeloTipoDocumento doc = new ModeloTipoDocumento();
            doc.setId_tip(id_tip);
            doc.setNombre_doc(nombre);

            if (doc.InsertaTipoDocBD() == null) {
                vt.getDlgtipodoc().setVisible(false);
                JOptionPane.showMessageDialog(vt, "Tipo añadido correctamente");

            } else {
                JOptionPane.showMessageDialog(vt, "No se pudo añadir el tipo");
            }
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    private void UsaDialogoLabor() {
        VistaLabor vl = new VistaLabor();
        try {
            int id_lab = IncrementaIDlabor();
            String nombre = vl.getTxtnombrelab().getText().toUpperCase().trim();
            int horas_laborales = vl.getSldhoras().getValue();
            double sueldo = Double.parseDouble(vl.getTxtsueldo().getText());

            ModeloLabor labor = new ModeloLabor();
            labor.setId_lab(id_lab);
            labor.setNombre_lab(nombre);
            labor.setHoraslaborales_lab(horas_laborales);
            labor.setSueldo_lab(sueldo);

            if (labor.InsertarLaborBD() == null) {
                vl.getDlgcrudlabor().setVisible(false);
                JOptionPane.showMessageDialog(vl, "Labor añadido correctamente");

            } else {
                JOptionPane.showMessageDialog(vl, "No se pudo añadir la Labor");
            }
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    private void CargaEmpleados() {
        List<Persona> listaemp = mp.ListarPersonas();
        DefaultTableModel df;
        df = (DefaultTableModel) ve.getTblempleados().getModel();
        df.setNumRows(0);

        listaemp.stream().forEach(em -> {
            String[] FilaNueva = {
                Integer.toString(em.getId_per()),
                em.getNumeroidentificacion_per(),
                em.getNombre_per(),
                em.getApellido_per(),
                em.getDireccion_per(),
                em.getTelefono_per(),
                em.getEmail_per(),
                em.getGenero_per(),
                String.valueOf(em.getFecha_nac())
            };
            df.addRow(FilaNueva);
        });
    }

    private void LlenaComboLabor() {
        ve.getCblabor().removeAllItems();
        List<Labor> list = ml.LlenaComboBD();
        list.stream().forEach(la -> {
            ve.getCblabor().addItem(new Labor(la.getId_lab(), la.getNombre_lab()));
        });
    }

    private void LlenaComboTipoDoc() {
        ve.getCbtipodoc().removeAllItems();
        List<TipoDocumento> list = mtd.LlenaComboBD();
        list.stream().forEach(doc -> {
            ve.getCbtipodoc().addItem(new TipoDocumento(doc.getId_tip(), doc.getNombre_doc()));
        });
    }

    private void BuscaEmpleados() {
        List<Persona> listaemp = mp.BuscaPersonaDB(ve.getTxtbuscar().getText());
        DefaultTableModel df;
        df = (DefaultTableModel) ve.getTblempleados().getModel();
        df.setNumRows(0);

        listaemp.stream().forEach(em -> {
            String[] FilaNueva = {
                Integer.toString(em.getId_per()),
                em.getNumeroidentificacion_per(),
                em.getNombre_per(),
                em.getApellido_per(),
                em.getDireccion_per(),
                em.getTelefono_per(),
                em.getEmail_per(),
                em.getGenero_per(),
                String.valueOf(em.getFecha_nac())
            };
            df.addRow(FilaNueva);
        });
    }

    private void AbreDialogo(int ce) {
        String title = null;
        boolean RowSelected = true;
        switch (ce) {
            case 1:
                title = "Añadir un nuevo empleado";
                EnableFields(InitClean());
                ve.getDlgcrudempleado().setName("crear");

                break;

            case 2:
                title = "Editar empleado";
                ve.getDlgcrudempleado().setName("editar");
                EnableFields(InitClean());
                RowSelected = MousePressed(ve.getTblempleados());
                break;

            case 3:
                title = "Eliminar empleado";
                ve.getDlgcrudempleado().setName("eliminar");
                DisableFields(InitClean());
                RowSelected = MousePressed(ve.getTblempleados());
                break;
        }

        if (RowSelected) {
            ve.getDlgcrudempleado().setLocationRelativeTo(ve);
            ve.getDlgcrudempleado().setSize(425, 550);
            ve.getDlgcrudempleado().setTitle(title);
            ve.getDlgcrudempleado().setVisible(true);
        }
    }

    private boolean MousePressed(JTable table) {
        boolean press = false;
        try {
            if (table.getSelectedRowCount() == 1) {
                press = true;
                ve.getLblidper().setText(ve.getTblempleados().getValueAt(ve.getTblempleados().getSelectedRow(), 0).toString());
                ve.getTxtdocnum().setText(ve.getTblempleados().getValueAt(ve.getTblempleados().getSelectedRow(), 1).toString());

                ve.getTxtnombreper().setText(ve.getTblempleados().getValueAt(ve.getTblempleados().getSelectedRow(), 2).toString());
                ve.getTxtapellidoper().setText(ve.getTblempleados().getValueAt(ve.getTblempleados().getSelectedRow(), 3).toString());
                ve.getTxtdireccion().setText(ve.getTblempleados().getValueAt(ve.getTblempleados().getSelectedRow(), 4).toString());
                ve.getTxttelefono().setText(ve.getTblempleados().getValueAt(ve.getTblempleados().getSelectedRow(), 5).toString());
                ve.getTxtemail().setText(ve.getTblempleados().getValueAt(ve.getTblempleados().getSelectedRow(), 6).toString());
                String sexo = ve.getTblempleados().getValueAt(ve.getTblempleados().getSelectedRow(), 7).toString();
                if (sexo.equals("Masculino")) {
                    ve.getRdmasculino().setSelected(true);
                    ve.getRdfemenino().setSelected(false);
                } else if (sexo.equals("Femenino")) {
                    ve.getRdmasculino().setSelected(false);
                    ve.getRdfemenino().setSelected(true);
                }

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                ve.getDtefechanac().setDate((formatter.parse(ve.getTblempleados().getValueAt(ve.getTblempleados().getSelectedRow(), 8).toString())));

            } else {
                JOptionPane.showMessageDialog(ve, "Seleccione una fila primero");
            }
        } catch (NullPointerException | ParseException e) {
            System.err.print(e);
        }
        return press;
    }

    private int IncrementaID() {
        ModeloPersona mpe = new ModeloPersona();
        int id_per = mpe.ObtieneID() + 1;
        return id_per;
    }

    private int IncrementaIDE() {
        ModeloEmpleado mem = new ModeloEmpleado();
        int id_emp = mem.ObtieneIDBD() + 1;
        return id_emp;
    }

    private void CleanFields(Component[] components) {
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(null);
            } else if (component instanceof JDateChooser) {
                ((JDateChooser) component).setCalendar(null);
            } else if (component instanceof JComboBox) {
                ((JComboBox) component).setSelectedIndex(0);
            }
        }
    }

    private void EnableFields(Component[] components) {
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setEnabled(true);
            } else if (component instanceof JDateChooser) {
                ((JDateChooser) component).setEnabled(true);
                ((JDateChooser) component).getCalendarButton().setEnabled(true);
            } else if (component instanceof JComboBox) {
                ((JComboBox) component).setEnabled(true);
            } else if (component instanceof JButton) {
                ((JButton) component).setEnabled(true);
            }
            ve.getRdfemenino().setEnabled(true);
            ve.getRdmasculino().setEnabled(true);
            ve.getChkdoctipo().setEnabled(true);
        }
    }

    private void DisableFields(Component[] components) {
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setEnabled(false);
                ((JTextField) component).setDisabledTextColor(component.getForeground());
            } else if (component instanceof JDateChooser) {
                ((JDateChooser) component).setEnabled(false);
            } else if (component instanceof JComboBox) {
                ((JComboBox) component).setEnabled(false);
            } else if (component instanceof JButton) {
                ((JButton) component).setEnabled(false);
            }
            ve.getRdfemenino().setEnabled(false);
            ve.getRdmasculino().setEnabled(false);
            ve.getChkdoctipo().setEnabled(false);
        }
    }

    private Component[] InitClean() {
        Component[] com = {
            ve.getTxtdocnum(),
            ve.getTxtnombreper(),
            ve.getTxtapellidoper(),
            ve.getDtefechanac(),
            ve.getTxttelefono(),
            ve.getTxtemail(),
            ve.getCblabor(),
            ve.getCbtipodoc(),
            ve.getTxtdireccion(),
            ve.getBtnaddlab(),
            ve.getBtnaddtipodoc()
        };
        ve.getBtngpgenero().clearSelection();
        CleanFields(com);
        return com;
    }

    private void crearEditarEliminarEmpleado() {
        if (ve.getDlgcrudempleado().getName().equals("crear")) {
            try {

                int id_per = Integer.parseInt(ve.getLblidper().getText());
                String numeroidentificacion = ve.getTxtdocnum().getText();
                String nombre = ve.getTxtnombreper().getText();
                String apellido = ve.getTxtapellidoper().getText();
                Date fechanac = ve.getDtefechanac().getDate();
                String telefono = ve.getTxttelefono().getText();
                String sexo = null;
                if (ve.getRdmasculino().isSelected()) {
                    sexo = "Masculino";
                } else if (ve.getRdfemenino().isSelected()) {
                    sexo = "Femenino";
                }
                TipoDocumento tipodoc = (TipoDocumento) ve.getCbtipodoc().getSelectedItem();
                int id_tipo = mtd.ConsultaIDBD(tipodoc.toString());
                String direccion = ve.getTxtdireccion().getText();
                String email = ve.getTxtemail().getText();

                System.out.println(id_tipo);
                ModeloPersona persona = new ModeloPersona();

                persona.setId_per(id_per);
                persona.setNumeroidentificacion_per(numeroidentificacion);
                persona.setNombre_per(nombre);
                persona.setApellido_per(apellido);
                persona.setFecha_nac(fechanac);
                persona.setTelefono_per(telefono);
                persona.setGenero_per(sexo);
                persona.setTipo_doc(id_tipo);
                persona.setDireccion_per(direccion);
                persona.setEmail_per(email);

                if (persona.InsertaPersonaBD() == null) {
                    ModeloEmpleado empleado = new ModeloEmpleado();
                    empleado.setId_emp(IncrementaIDE());
                    Labor lab = (Labor) ve.getCblabor().getSelectedItem();

                    int labor = ml.ConsultaIDBD(lab.toString());
                    empleado.setIdlabor_emp(labor);
                    empleado.setId_per(id_per);
                    if (empleado.InsertaEmpleado() == null) {
                        ve.getDlgcrudempleado().setVisible(false);
                        int create_account = JOptionPane.showConfirmDialog(ve, "Empleado añadido correctamente.\n¿Desea crear un cuenta para este usuario?",
                                "Creación de cuenta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                        if (create_account == JOptionPane.YES_OPTION) {
                            VistaCuenta vc = new VistaCuenta();
                            ModeloCuenta mc = new ModeloCuenta();

                            ControladorCuenta cc = new ControladorCuenta(mc, vc);
                            cc.AbreDialogo(1);
                        }
                    } else {
                        JOptionPane.showMessageDialog(ve, "No se pudo añadir al empleado");
                    }
                } else {
                    JOptionPane.showMessageDialog(ve, "No se pudo añadir el registro");
                }
            } catch (NullPointerException | NumberFormatException e) {
                System.out.println(e);
            }
        } else {
            if (ve.getDlgcrudempleado().getName().equals("editar")) {
                try {
                    int id_per = Integer.parseInt(ve.getLblidper().getText());

                    String numeroidentificacion = ve.getTxtdocnum().getText();
                    String nombre = ve.getTxtnombreper().getText();
                    String apellido = ve.getTxtapellidoper().getText();
                    Date fechanac = ve.getDtefechanac().getDate();
                    String telefono = ve.getTxttelefono().getText();
                    String sexo = null;
                    if (ve.getRdmasculino().isSelected()) {
                        sexo = "Masculino";
                    } else if (ve.getRdfemenino().isSelected()) {
                        sexo = "Femenino";
                    }
                    TipoDocumento tipodoc = (TipoDocumento) ve.getCbtipodoc().getSelectedItem();
                    int id_tipo = mtd.ConsultaIDBD(tipodoc.toString());
                    String direccion = ve.getTxtdireccion().getText();
                    String email = ve.getTxtemail().getText();
                    int labor = ve.getCblabor().getSelectedIndex();

                    ModeloPersona persona = new ModeloPersona();

                    persona.setNumeroidentificacion_per(numeroidentificacion);
                    persona.setNombre_per(nombre);
                    persona.setApellido_per(apellido);
                    persona.setFecha_nac(fechanac);
                    persona.setTelefono_per(telefono);
                    persona.setGenero_per(sexo);
                    persona.setTipo_doc(id_tipo);
                    persona.setDireccion_per(direccion);
                    persona.setEmail_per(email);

                    if (persona.ModficarPersonaDB(id_per) == null) {
                        ve.getDlgcrudempleado().setVisible(false);
                        JOptionPane.showMessageDialog(ve, "Empleado editado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(ve, "No se pudo editar al empleado");
                    }

                } catch (NumberFormatException | NullPointerException e) {
                    System.out.println(e);
                }
            } else {
                if (ve.getDlgcrudempleado().getName().equals("eliminar")) {
                    try {
                        int id_per = Integer.parseInt(ve.getLblidper().getText());

                        ModeloPersona persona = new ModeloPersona();
                        persona.setId_per(id_per);
                        if (persona.EliminarPersonaDB(id_per) == null) {
                            ve.getDlgcrudempleado().setVisible(false);
                            JOptionPane.showMessageDialog(ve, "Empleado eliminado con éxito");
                        } else {
                            JOptionPane.showMessageDialog(ve, "No se pudo eliminar al empleado");
                        }

                    } catch (NumberFormatException | NullPointerException e) {
                        System.out.print(e);
                    }
                }
            }
        }
        CargaEmpleados();
    }
}
