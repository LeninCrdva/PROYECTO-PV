package Controlador;

import Modelo.*;
import Vista.*;
import Vista.VistaCuenta;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.mindrot.jbcrypt.BCrypt;

public class ControladorEmpleado {

    private final ModeloEmpleado me;
    private final VistaEmpleados ve;
    private final VistaTipoDocumento vd;
    private final VistaCuenta vcc;
    private final VistaLabor vl;
    private final ModeloLabor ml;
    private final ModeloTipoDocumento mtd;

    public ControladorEmpleado(ModeloEmpleado me, VistaEmpleados ve, VistaTipoDocumento vd, VistaCuenta vcc, VistaLabor vl, ModeloLabor ml, ModeloTipoDocumento mtd) {
        this.me = me;
        this.ve = ve;
        this.vd = vd;
        this.vcc = vcc;
        this.vl = vl;
        this.ml = ml;
        this.mtd = mtd;
        ve.setVisible(true);
    }

    public void IniciarControl() {
        CargaEmpleados();
        LlenaComboLabor();
        LlenaComboTipoDoc();
        LlenaComboempleado();
        IncrementaIDE();
        ValidarCampos();
        ve.getBtncrear().addActionListener(l -> AbreDialogo(1));
        ve.getBtncrear().addActionListener(l -> InitClean());
        ve.getBtneditar().addActionListener(l -> AbreDialogo(2));
        ve.getBtneliminar().addActionListener(l -> AbreDialogo(3));
        ve.getBtnaceptar().addActionListener(l -> crearEditarEliminarEmpleado());
        ve.getBtncancelar().addActionListener(l -> ve.getDlgcrudempleado().dispose());
        ve.getLblidper().setText(Integer.toString(IncrementaID()));
        ve.getBtnprint().addActionListener(l -> Reportes());
        /*Actions subviews*/
        ve.getBtnaddtipodoc().addActionListener(l -> AbreSubDialogo(1));
        ve.getBtnaddlab().addActionListener(l -> AbreSubDialogo(2));
        vd.getBtnaceptar().addActionListener(l -> UsaSubDialogo());
        vl.getBtnaceptar().addActionListener(l -> UsaSubDialogo());
        vd.getBtncancelar().addActionListener(l -> vd.getDlgtipodoc().dispose());
        vl.getBtncancelar().addActionListener(l -> vl.getDlgcrudlabor().dispose());
        ve.getTxtbuscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BuscarEmpleadosBD();
            }
        });
    }

    private void ValidarCampos() {
        ve.getDtefechanac().setDateFormatString("yyyy-MM-dd");
        ve.getDtefechaingreso().setDateFormatString("yyyy-MM-dd");
        addTextKeyListenerNotNumber(ve.getTxtnombreper(), 50);
        addTextKeyListenerNotNumber(ve.getTxtapellidoper(), 50);
        MaxLengthOnly(ve.getTxtdireccion(), 250);
        MaxLengthOnly(ve.getTxtemail(), 250);
        addTextKeyListenerNotText(ve.getTxttelefono(), 15);
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

    private void AbreSubDialogo(int ce) {
        if (ce == 1) {
            vd.getDlgtipodoc().setName("documento");
            vd.getDlgtipodoc().setSize(290, 200);
            vd.getDlgtipodoc().setModal(true);
            vd.getDlgtipodoc().setLocationRelativeTo(ve);
            vd.getDlgtipodoc().setVisible(true);
        } else {
            vl.getDlgcrudlabor().setName("labor");
            vl.getDlgcrudlabor().setSize(580, 280);
            vl.getDlgcrudlabor().setModal(true);
            vl.getDlgcrudlabor().setLocationRelativeTo(ve);
            vl.getDlgcrudlabor().setVisible(true);
        }
    }

    private void AbreDialogCuenta() {
        vcc.getDlgcrudcuenta().setSize(320, 350);
        vcc.getDlgcrudcuenta().setLocationRelativeTo(vcc);
        vcc.getDlgcrudcuenta().setModal(true);
        vcc.getDlgcrudcuenta().setVisible(true);
    }

    private void UsaSubDialogo() {
        if (vl.getDlgcrudlabor().getName().equals("labor")) {
            try {
                vl.getLblidlab().setText(Integer.toString(IncrementaIDlabor()));

                int id_lab = Integer.parseInt(vl.getLblidlab().getText());
                String nombre = vl.getTxtnombrelab().getText().toUpperCase().trim();
                int horas_laborales = vl.getSldhoras().getValue();
                double sueldo = Double.parseDouble(vl.getTxtsueldo().getText());

                if (!nombre.isEmpty()) {
                    if (!String.valueOf(sueldo).isEmpty()) {
                        if (!ml.ExisteNombreLaborBD(nombre)) {
                            ModeloLabor labor = new ModeloLabor();
                            labor.setId_lab(id_lab);
                            labor.setNombre_lab(nombre);
                            labor.setHoraslaborales_lab(horas_laborales);
                            labor.setSueldo_lab(sueldo);
                            if (labor.InsertarLaborBD() == null) {
                                JOptionPane.showMessageDialog(vl, "Registro de labor añadido correctamente");
                                LlenaComboLabor();
                                vl.getDlgcrudlabor().dispose();
                            } else {
                                JOptionPane.showMessageDialog(vl, "No se pudo añadir el registro");
                            }
                        } else {
                            JOptionPane.showMessageDialog(vl, "El nombre que intenta registrar ya existe.");
                        }
                        //}
                    } else {
                        JOptionPane.showMessageDialog(vl, "El campo de sueldo no puede estar vacío.");
                    }
                } else {
                    JOptionPane.showMessageDialog(vl, "El campo de nombre no puede estar vacío.");
                }
            } catch (NullPointerException | NumberFormatException e) {
                System.out.println(e);
            }
        } else if (vd.getDlgtipodoc().getName().equals("documento")) {

            vd.getLblnumdoc().setText(Integer.toString(IncrementaIDtipodoc()));
            int id_doc = IncrementaIDtipodoc();
            String nombre_doc = vd.getTxtdocname().getText();
            if (!nombre_doc.isEmpty()) {
                if (!mtd.ExisteNombreTipoDocBD(nombre_doc)) {
                    ModeloTipoDocumento mt = new ModeloTipoDocumento();
                    mt.setId_tip(id_doc);
                    mt.setNombre_doc(nombre_doc);
                    if (mt.InsertaTipoDocBD() == null) {
                        vd.getDlgtipodoc().dispose();
                        LlenaComboTipoDoc();
                        JOptionPane.showMessageDialog(ve, "Tipo de documento añadido correctamente");
                    } else {
                        JOptionPane.showMessageDialog(ve, "No se pudo añadir el documento");
                    }
                } else {
                    JOptionPane.showMessageDialog(ve, "El nombre que intenta ingresar ya existe");
                }
            } else {
                JOptionPane.showMessageDialog(ve, "El campo del nombre no puede estar vacío");
            }
        }
    }

    private void UsaSubDialogoCuenta(int id_emp) {
        try {
            System.out.println(id_emp);
            //int id_emp = vcc.getCbempleado().getItemAt(vcc.getCbempleado().getSelectedIndex()).
            System.out.println(vcc.getCbempleado().getSelectedIndex());
            String username = vcc.getTxtusername().getText().toUpperCase().trim();
            char[] passwordkey = vcc.getTxtpassword().getPassword();
            String password = new String(passwordkey);
            char[] passwordkeyconfirm = vcc.getTxtconfirmpassword().getPassword();
            String passwordconfirm = new String(passwordkeyconfirm);
            if (!username.isEmpty() && !password.isEmpty()) {
                if (!username.isEmpty()) {
                    if (!password.isEmpty()) {
                        if (ControladorCuenta.SecurePassword(password)) {
                            if (password.equals(passwordconfirm)) {
                                if (!ModeloCuenta.ExisteUserNameBD(username)) {
                                    String hashpassword = BCrypt.hashpw(password, BCrypt.gensalt());
                                    ModeloCuenta cuenta = new ModeloCuenta();
                                    cuenta.setId_cue(IncrementaIDCuenta());
                                    cuenta.setId_emp(id_emp);
                                    cuenta.setUsername_cue(username);
                                    cuenta.setPassword_cue(hashpassword);
                                    if (cuenta.InsertaCuentaBD() == null) {
                                        vcc.getDlgcrudcuenta().dispose();
                                        JOptionPane.showMessageDialog(vl, "Cuenta añadida correctamente");
                                    } else {
                                        JOptionPane.showMessageDialog(vl, "No se pudo añadir la cuenta");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(ve, "El Nombre de usuario ya se encuentra registrado");
                                }
                            } else {
                                JOptionPane.showMessageDialog(ve, "Las contraseñas no coinciden");
                            }
                        } else {
                            JOptionPane.showMessageDialog(vcc, "La contraseña debe contener:\n"
                                    + "• Al menos una letra en mayúscula, una mínuscula, un número\n"
                                    + "• Un tamaño mínimo de 8 dígitos", "Contraseña inválida",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(ve, "El campo de la contraseña no puede estar vacío");
                    }
                } else {
                    JOptionPane.showConfirmDialog(ve, "El campo de nombre de usuario no puede estar vacío");
                }
            } else {
                JOptionPane.showMessageDialog(ve, "Llene todos los campos");
            }
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    private void CargaEmpleados() {
        List<Persona> listaper = me.ListarPersonasEmpleadosBD();
        List<Empleado> listaemp = me.ListarEmpleadosBD();
        List<Labor> listalab = ml.ListaLaborBD();
        DefaultTableModel df;
        df = (DefaultTableModel) ve.getTblempleados().getModel();
        df.setNumRows(0);

        listaper.stream().forEach(pe -> {
            listaemp.stream().forEach(em -> {
                listalab.stream().forEach(lab -> {
                    if (pe.getId_per() == em.getId_per()) {
                        if (em.getIdlabor_emp() == lab.getId_lab()) {
                            String[] FilaNueva = {
                                Integer.toString(em.getId_per()),
                                pe.getNumeroidentificacion_per(),
                                pe.getNombre_per(),
                                pe.getApellido_per(),
                                pe.getDireccion_per(),
                                pe.getTelefono_per(),
                                pe.getEmail_per(),
                                pe.getGenero_per(),
                                String.valueOf(pe.getFecha_nac()),
                                lab.getNombre_lab(),
                                String.valueOf(em.getFecha_contratacion_emp())
                            };
                            df.addRow(FilaNueva);
                        }
                    }
                });
            });
        });
    }

    private void BuscarEmpleadosBD() {
        String search = ve.getTxtbuscar().getText().trim();
        List<Persona> listaper = me.BuscarEmpleadosBD(search);
        List<Empleado> listaemp = me.ListarEmpleadosBD();
        List<Labor> listalab = ml.ListaLaborBD();
        DefaultTableModel df;
        df = (DefaultTableModel) ve.getTblempleados().getModel();
        df.setNumRows(0);
        listaper.stream().forEach(pe -> {
            listaemp.stream().forEach(em -> {
                if (pe.getId_per() == em.getId_per()) {
                    listalab.stream().forEach(lab -> {
                        if (em.getIdlabor_emp() == lab.getId_lab()) {
                            String[] FilaNueva = {
                                Integer.toString(em.getId_per()),
                                pe.getNumeroidentificacion_per(),
                                pe.getNombre_per(),
                                pe.getApellido_per(),
                                pe.getDireccion_per(),
                                pe.getTelefono_per(),
                                pe.getEmail_per(),
                                pe.getGenero_per(),
                                String.valueOf(pe.getFecha_nac()),
                                lab.getNombre_lab(),
                                String.valueOf(em.getFecha_contratacion_emp())
                            };
                            df.addRow(FilaNueva);
                        }
                    });
                }
            });
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

    private void LlenaComboempleado() {
        vcc.getCbempleado().removeAllItems();
        List<Empleado> list = me.LlenaComboBD();
        list.stream().forEach(em -> {
            vcc.getCbempleado().addItem(new Empleado(em.getId_emp()));
        });
    }

    private void AbreDialogo(int ce) {
        String title = null;
        boolean RowSelected = true;
        switch (ce) {
            case 1:
                title = "Añadir un nuevo empleado";
                EnableFields(InitClean());
                ve.getLblidper().setText(Integer.toString(IncrementaID()));
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

                int id_per = Integer.parseInt(ve.getTblempleados().getValueAt(ve.getTblempleados().getSelectedRow(), 0).toString());
                System.out.println(id_per);
                ModeloPersona mp = new ModeloPersona();
                int id_doc = mp.ObtieneID(id_per);
                System.out.println(id_doc);
                ve.getCbtipodoc().setSelectedIndex(id_doc - 1);
                int id_lab = ml.ConsultaIDBD((ve.getTblempleados().getValueAt(ve.getTblempleados().getSelectedRow(), 9).toString()));
                System.out.println(id_lab);
                ve.getCblabor().setSelectedIndex(id_lab - 1);

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

    private int IncrementaIDCuenta() {
        ModeloCuenta mc = new ModeloCuenta();
        int id_cue = mc.ObtenerIDCuentaBD() + 1;
        return id_cue;
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
                ((JDateChooser) component).setEnabled(false);
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
            ve.getDtefechaingreso(),
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

    private boolean validateCorreo() {
        boolean valid = false;
        String email = ve.getTxtemail().getText().trim();
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            valid = true;
        } catch (AddressException ex) {
            JOptionPane.showMessageDialog(null, "El correo ingresado no existe");
        }
        return valid;
    }

    private boolean ValidData(String data) {
        boolean isvalid = true;
        if (data.isEmpty()) {
            isvalid = false;
        }
        return isvalid;
    }

    private boolean validateDate(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);

        Calendar caltoday = Calendar.getInstance();

        int anio = caltoday.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        int month = caltoday.get(Calendar.MONTH) - cal.get(Calendar.MONTH);
        int day = caltoday.get(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_MONTH);

        if (month < 0 || (month == 0 && day < 0)) {
            anio--;
        }

        return anio >= 18 && anio <= 80;
    }

    private void Reportes() {
        ConnectionPG con = new ConnectionPG();
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/Vista/Reportes/Empleados.jasper"));
            //Map<String, Object> parametros = Parametros();
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con.getCon());
            JasperViewer jv = new JasperViewer(jp, false);

            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //SQL PARA BORRAR TABLAS RELACIONADAS
    /*
    ALTER TABLE EMPLEADO DROP CONSTRAINT fk_persona,
	ADD CONSTRAINT fk_persona
	FOREIGN KEY (id_per) REFERENCES PERSONA (id_per)
	ON DELETE CASCADE
     */
    private void crearEditarEliminarEmpleado() {
        if (ve.getDlgcrudempleado().getName().equals("crear")) {
            try {
                int id_per = Integer.parseInt(ve.getLblidper().getText());
                String numeroidentificacion = ve.getTxtdocnum().getText().trim();
                String nombre = ve.getTxtnombreper().getText().trim();
                String apellido = ve.getTxtapellidoper().getText().trim();
                Date fechanac = ve.getDtefechanac().getDate();
                String telefono = ve.getTxttelefono().getText().trim();
                String sexo = null;
                if (ve.getRdmasculino().isSelected()) {
                    sexo = "Masculino";
                } else if (ve.getRdfemenino().isSelected()) {
                    sexo = "Femenino";
                }
                TipoDocumento tipodoc = (TipoDocumento) ve.getCbtipodoc().getSelectedItem();
                int id_tipo = mtd.ConsultaIDBD(tipodoc.toString());
                String direccion = ve.getTxtdireccion().getText().trim();
                String email = ve.getTxtemail().getText().toLowerCase().trim();

                if (!ValidData(numeroidentificacion)) {
                    JOptionPane.showMessageDialog(ve, "El campo de número de documento no puede quedar vacío");
                    return;
                }

                if (!ValidData(nombre)) {
                    JOptionPane.showMessageDialog(ve, "El campo del nombre no puede quedar vacío");
                    return;
                }

                if (!ValidData(apellido)) {
                    JOptionPane.showMessageDialog(ve, "El campo del apellido no puede quedar vacío");
                    return;
                }
                if (!ValidData(telefono)) {
                    JOptionPane.showMessageDialog(ve, "El campo de teléfono no puede quedar vacío");
                    return;
                }
                if (!ValidData(sexo)) {
                    JOptionPane.showMessageDialog(ve, "Elija el sexo");
                    return;
                }
                if (!validateDate(fechanac)) {
                    JOptionPane.showMessageDialog(ve, "Ingrese una fecha de nacimiento válida. \n"
                            + "Recuerde que la persona debe ser mayor de edad.");
                    return;
                }
                if (!ValidData(direccion)) {
                    JOptionPane.showMessageDialog(ve, "El campo de la dirección no puede quedar vacío");
                    return;
                }
                if (!ValidData(email)) {
                    JOptionPane.showMessageDialog(ve, "El campo del email no es válido");
                    return;
                }
                if (!validateCorreo()) {
                    JOptionPane.showMessageDialog(ve, "Proporcione correo válido\n");
                    return;
                }
                if (!Cedula.validarCedula(numeroidentificacion)) {
                    JOptionPane.showMessageDialog(ve, "Proporcione un número de identificación válido");
                    return;
                }

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
                    Date fecha_cont = ve.getDtefechaingreso().getDate();

                    empleado.setIdlabor_emp(labor);
                    empleado.setId_per(id_per);
                    empleado.setFecha_contratacion_emp(fecha_cont);
                    if (empleado.InsertaEmpleadoBD() == null) {
                        ve.getDlgcrudempleado().setVisible(false);
                        int create_account = JOptionPane.showConfirmDialog(ve, "Empleado añadido correctamente.\n¿Desea crear un cuenta para este usuario?",
                                "Creación de cuenta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                        if (create_account == JOptionPane.YES_OPTION) {
                            vcc.getLblidcuenta().setText(Integer.toString(IncrementaIDCuenta()));
                            LlenaComboempleado();
                            System.out.println(empleado.getId_emp());
                            vcc.getBtnaceptar().addActionListener(l -> UsaSubDialogoCuenta(empleado.getId_emp()));
                            vcc.getBtncancelar().addActionListener(l -> vcc.getDlgcrudcuenta().dispose());
                            AbreDialogCuenta();
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

                    if (!ValidData(numeroidentificacion)) {
                        JOptionPane.showMessageDialog(ve, "El campo de número de documento no puede quedar vacío");
                        return;
                    }

                    if (!ValidData(nombre)) {
                        JOptionPane.showMessageDialog(ve, "El campo del nombre no puede quedar vacío");
                        return;
                    }

                    if (!ValidData(apellido)) {
                        JOptionPane.showMessageDialog(ve, "El campo del apellido no puede quedar vacío");
                        return;
                    }
                    if (!ValidData(telefono)) {
                        JOptionPane.showMessageDialog(ve, "El campo de teléfono no puede quedar vacío");
                        return;
                    }
                    if (!ValidData(sexo)) {
                        JOptionPane.showMessageDialog(ve, "Elija el sexo");
                        return;
                    }
                    if (!validateDate(fechanac)) {
                        JOptionPane.showMessageDialog(ve, "Ingrese una fecha de nacimiento válida. \n"
                                + "Recuerde que la persona debe ser mayor de edad.");
                        return;
                    }
                    if (!ValidData(direccion)) {
                        JOptionPane.showMessageDialog(ve, "El campo de la dirección no puede quedar vacío");
                        return;
                    }
                    if (!ValidData(email)) {
                        JOptionPane.showMessageDialog(ve, "El campo del email no es válido");
                        return;
                    }
                    if (!validateCorreo()) {
                        JOptionPane.showMessageDialog(ve, "Proporcione correo válido\n");
                        return;
                    }
                    if (!Cedula.validarCedula(numeroidentificacion)) {
                        JOptionPane.showMessageDialog(ve, "Proporcione un número de identificación válido");
                        return;
                    }

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
                        ModeloEmpleado empleado = new ModeloEmpleado();
                        empleado.setId_emp(me.ObtieneIDBD(id_per));
                        Labor lab = (Labor) ve.getCblabor().getSelectedItem();

                        int labor = ml.ConsultaIDBD(lab.toString());
                        Date fecha_cont = ve.getDtefechaingreso().getDate();

                        empleado.setIdlabor_emp(labor);
                        empleado.setId_per(id_per);
                        empleado.setFecha_contratacion_emp(fecha_cont);
                        if (empleado.ModificarEmpleadoBD(empleado.getId_emp()) == null) {
                            ve.getDlgcrudempleado().setVisible(false);
                            JOptionPane.showMessageDialog(ve, "Empleado editado correctamente");
                        } else {
                            JOptionPane.showMessageDialog(ve, "No se pudo editar al empleado");
                        }
                    } else {
                        JOptionPane.showMessageDialog(ve, "No se pudo editar el registro");
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
