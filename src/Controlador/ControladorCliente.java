/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cedula;
import Modelo.Cliente;
import Modelo.ModeloCliente;
import Modelo.ModeloPersona;
import Modelo.ModeloTipoCliente;
import Modelo.ModeloTipoDocumento;
import Modelo.Persona;
import Modelo.TipoCliente;
import Modelo.TipoDocumento;
import Vista.VistaClientes;
import Vista.VistaTipoCliente;
import Vista.VistaTipoDocumento;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ERIKA
 */
public class ControladorCliente {

    private final ModeloPersona mp;
    private final ModeloCliente mc;
    private final VistaClientes vc;
    private final ModeloTipoCliente mtc;
    private final VistaTipoDocumento vtd;
    private final ModeloTipoDocumento mtd;
    private final VistaTipoCliente vtc;

    public ControladorCliente(ModeloPersona mp, ModeloCliente mc, VistaClientes vc, ModeloTipoCliente mtc, VistaTipoDocumento vtd, ModeloTipoDocumento mtd, VistaTipoCliente vtc) {
        this.mp = mp;
        this.mc = mc;
        this.vc = vc;
        this.mtc = mtc;
        this.vtd = vtd;
        this.mtd = mtd;
        this.vtc = vtc;
        vc.setVisible(true);
    }

    public void IniciarControl() {
        CargarClientes();
        SetModelCombos();
        LlenarComboTipoCliente();
        LlenarComboTipoDoc();
        IncrementoIdC();
        ValidarCampos();
        vc.getBtnCrear().addActionListener(l -> AbrirDialogo(1));
        vc.getBtnEditar().addActionListener(l -> AbrirDialogo(2));
        vc.getBtnEliminar().addActionListener(l -> AbrirDialogo(3));
        vc.getBtnAceptar().addActionListener(l -> crearEditarEliminarCliente());
        vc.getBtnCancelar().addActionListener(l -> vc.getDlgCrudClientes().dispose());
        vc.getLblIdPer().setText(Integer.toString(IdIncremental()));
        /*Cliente*/
        vc.getBtnAgregarTipoDoc().addActionListener(l -> AbreSubDialogo(1));
        vc.getBtnAgregarTipoCliente().addActionListener(l -> AbreSubDialogo(2));
        vtd.getBtnaceptar().addActionListener(l -> UsaSubDialogo());
        vtd.getBtncancelar().addActionListener(l -> vtd.getDlgtipodoc().dispose());
        vtc.getBtnAceptar().addActionListener(l -> UsaSubDialogo());
        vtc.getBtnCancelar().addActionListener(l -> vtc.getDlgCrudTipoCliente().dispose());
        vc.getTxtbuscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BuscaClientes();
            }
        });
    }

    private void ValidarCampos() {
        vc.getDatFechanaci().setDateFormatString("yyyy-MM-dd");
        vc.getTxtbuscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BuscaClientes();
            }
        });
        addTextKeyListenerNotNumber(vc.getTxtNombre(), 50);
        addTextKeyListenerNotNumber(vc.getTxtApellido(), 50);
        MaxLengthOnly(vc.getTxtNumDoc(), 20);
        MaxLengthOnly(vc.getTxtApellido(), 30);
        MaxLengthOnly(vc.getTxtEmail(), 200);
        addTextKeyListenerNotText(vc.getTxtTelefono(), 20);
        addTextKeyListenerNotNumber(vtd.getTxtdocname(), 20);
        addTextKeyListenerNotNumber(vtc.getTxtNombreTipoCliente(), 50);
    }

    private void AbreSubDialogo(int ce) {
        if (ce == 1) {
            vtd.getDlgtipodoc().setName("documento");
            vtd.getLblnumdoc().setText(Integer.toString(IncrementaIDtipodoc()));
            vtd.getDlgtipodoc().setSize(300, 210);
            vtd.getDlgtipodoc().setModal(true);
            vtd.getDlgtipodoc().setLocationRelativeTo(vc);
            vtd.getDlgtipodoc().setVisible(true);
        } else {
            vtc.getDlgCrudTipoCliente().setName("tipo_emp");
            vtc.getLblIdTipoCliente().setText(Integer.toString(IncrementaIDtipoCliente()));
            vtc.getDlgCrudTipoCliente().setSize(330, 215);
            vtc.getDlgCrudTipoCliente().setModal(true);
            vtc.getDlgCrudTipoCliente().setLocationRelativeTo(vc);
            vtc.getDlgCrudTipoCliente().setVisible(true);
        }
    }

    public void UsaSubDialogo() {
        if (vtc.getDlgCrudTipoCliente().getName().equals("tipo_emp")) {
            int id_tipoC = Integer.parseInt(vtc.getLblIdTipoCliente().getText());
            String nombre_tipoCliente = vtc.getTxtNombreTipoCliente().getText().trim();
            if (nombre_tipoCliente.isEmpty()) {
                JOptionPane.showMessageDialog(vtc, "El campo de nombre no puede quedar vacío");
                return;
            }
            if (!mtc.ExisteNombreTipoDocBD(nombre_tipoCliente)) {
                ModeloTipoCliente mtcli = new ModeloTipoCliente();
                mtcli.setId_tip(id_tipoC);
                mtcli.setNombre_tip(nombre_tipoCliente);

                if (mtcli.InsertarTipoCliente() == null) {
                    vtc.getDlgCrudTipoCliente().dispose();
                    LlenarComboTipoCliente();
                    JOptionPane.showMessageDialog(vc, "Tipo de cliente añadido correctamente");
                } else {
                    JOptionPane.showMessageDialog(vc, "No se puedo añadir  el tipo cliente");
                }
            } else {
                JOptionPane.showMessageDialog(vc, "El nombre que ingresado ya existe");
            }
        } else if (vtd.getDlgtipodoc().getName().equals("documento")) {
            int id_doc = Integer.parseInt(vtd.getLblnumdoc().getText());
            String nombre_doc = vtd.getTxtdocname().getText().trim();

            if (!nombre_doc.isEmpty()) {
                if (!mtd.ExisteNombreTipoDocBD(nombre_doc)) {
                    ModeloTipoDocumento mt = new ModeloTipoDocumento();
                    mt.setId_tip(id_doc);
                    mt.setNombre_doc(nombre_doc);
                    if (mt.InsertaTipoDocBD() == null) {
                        vtd.getDlgtipodoc().dispose();
                        LlenarComboTipoDoc();
                        JOptionPane.showMessageDialog(vc, "Tipo de documento añadido correctamente");
                    } else {
                        JOptionPane.showMessageDialog(vc, "No se pudo añadir el documento");
                    }
                } else {
                    JOptionPane.showMessageDialog(vc, "El nombre que intenta ingresar ya existe");
                }
            } else {
                JOptionPane.showMessageDialog(vc, "El campo del nombre no puede estar vacío");
            }
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

    private int IncrementaIDtipoCliente() {
        int id_tip = mtc.ObtenerIdTipoCliente();
        if (id_tip >= 1) {
            id_tip++;
        } else {
        }
        return id_tip;
    }

    private void addTextKeyListenerNotNumber(JTextField text, int maxLength) {
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char m = evt.getKeyChar();
                if (Character.isDigit(m) || text.getText().trim().length() >= maxLength) {
                    evt.consume();
                }
            }
        });
    }

    private void addTextKeyListenerNotText(JTextField text, int maxlength) {
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char m = evt.getKeyChar();
                if (Character.isLetter(m) || text.getText().trim().length() >= maxlength) {
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

    private void SetModelCombos() {
        DefaultComboBoxModel<TipoDocumento> modeltipodoc = new DefaultComboBoxModel<>();
        vc.getCmbTipoDoc().setModel(modeltipodoc);
        DefaultComboBoxModel<TipoCliente> modeltipocli = new DefaultComboBoxModel<>();
        vc.getCmbTipoCliente().setModel(modeltipocli);
    }

    private void addCombo(int ce) {
        if (ce == 1) {
            VistaTipoDocumento vistadoc = new VistaTipoDocumento();
            vistadoc.getBtnaceptar().addActionListener(l -> UsarDialogoTipoDoc());
            vistadoc.getBtncancelar().addActionListener(l -> vistadoc.getDlgtipodoc().dispose());
            vistadoc.getDlgtipodoc().setSize(290, 200);
            vistadoc.getDlgtipodoc().setLocationRelativeTo(vc);
            vistadoc.setVisible(true);
        } else {
            VistaTipoCliente ticli = new VistaTipoCliente();
            ticli.getBtnAceptar().addActionListener(l -> UsarDialogoTipoCliente());
            ticli.getBtnCancelar().addActionListener(l -> ticli.getDlgCrudTipoCliente().dispose());
            ticli.getDlgCrudTipoCliente().setSize(400, 400);
            ticli.getDlgCrudTipoCliente().setLocationRelativeTo(vc);
            ticli.getDlgCrudTipoCliente().setVisible(true);
        }
    }

    private void UsarDialogoTipoDoc() {
        VistaTipoDocumento vt = new VistaTipoDocumento();
        try {
            int id_tip = CodigoIncrementoIdTipoDoc();
            String nombre = vt.getTxtdocname().getText().toUpperCase().trim();

            ModeloTipoDocumento doc = new ModeloTipoDocumento();
            doc.setId_tip(id_tip);
            doc.setNombre_doc(nombre);

            if (doc.InsertaTipoDocBD() == null) {
                vt.getDlgtipodoc().setVisible(false);
                JOptionPane.showMessageDialog(vt, "tipo de documento añadido correctamente ");
            } else {
                JOptionPane.showMessageDialog(vt, "no se pudo guardar ");
            }
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    private int CodigoIncrementoIdTipoDoc() {
        int id_tip = mtd.ObtenerIDTDBD();
        if (id_tip >= 1) {
            id_tip++;

        } else {
        }
        return id_tip;
    }

    private int codigoIncrementalTipoCliente() {
        int id_tip = mtc.ObtenerIdTipoCliente();
        if (id_tip >= 1) {
            id_tip++;
        } else {
        }
        return id_tip;
    }

    private void UsarDialogoTipoCliente() {
        try {
            int id_tip = codigoIncrementalTipoCliente();
            String nombre = vtc.getTxtNombreTipoCliente().getText().toUpperCase().trim();

            ModeloTipoCliente tipo = new ModeloTipoCliente();
            tipo.setId_tip(id_tip);
            tipo.setNombre_tip(nombre);

            if (tipo.InsertarTipoCliente() == null) {
                vtc.getDlgCrudTipoCliente().setVisible(false);
                JOptionPane.showMessageDialog(vtc, "Registo exitoso");
            } else {
                JOptionPane.showMessageDialog(vtc, "No se pudo añadir el registro");
            }
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    private void CargarClientes() {
        List<Cliente> listacli = mc.listarclientes();
        List<TipoCliente> listatipc = mtc.ListaTipoCliBD();
        DefaultTableModel dt;
        dt = (DefaultTableModel) vc.getTblClientes().getModel();
        dt.setNumRows(0);
        listacli.stream().forEach(cli -> {
            listatipc.stream().forEach(tpc -> {
                if (cli.getId_tip() == tpc.getId_tip()) {
                    String[] FilaNueva = {
                        Integer.toString(cli.getId_per()),
                        cli.getNumeroidentificacion_per(),
                        cli.getNombre_per(),
                        cli.getApellido_per(),
                        cli.getDireccion_per(),
                        cli.getTelefono_per(),
                        cli.getEmail_per(),
                        cli.getGenero_per(),
                        String.valueOf(cli.getFecha_nac()),
                        tpc.getNombre_tip()
                    };
                    dt.addRow(FilaNueva);
                }
            });
        });
    }

    private void LlenarComboTipoCliente() {
        vc.getCmbTipoCliente().removeAllItems();
        List<TipoCliente> list = mtc.LlenarComboTipoCliBD();
        list.stream().forEach(ti -> {
            vc.getCmbTipoCliente().addItem(new TipoCliente(ti.getId_tip(), ti.getNombre_tip()));
        });
    }

    private void LlenarComboTipoDoc() {
        ModeloTipoDocumento mtdc = new ModeloTipoDocumento();
        vc.getCmbTipoDoc().removeAllItems();
        List<TipoDocumento> list = mtdc.LlenaComboBD();
        list.stream().forEach(doc -> {
            vc.getCmbTipoDoc().addItem(new TipoDocumento(doc.getId_tip(), doc.getNombre_doc()));
        });
    }

    private void BuscaClientes() {
        List<Persona> listaclie = mp.BuscaPersonaDB(vc.getTxtbuscar().getText());
        DefaultTableModel df;
        df = (DefaultTableModel) vc.getTblClientes().getModel();
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

    private void AbrirDialogo(int pe) {
        String titulo = null;
        boolean RowSelected = true;
        switch (pe) {
            case 1:
                titulo = "Agregar un nuevo cliente";
                vc.getDlgCrudClientes().setName("crear");
                EnableFields(InitClean());
                vc.getLblIdPer().setText(Integer.toString(IdIncremental()));
                break;

            case 2:
                titulo = "Editar cliente";
                vc.getDlgCrudClientes().setName("editar");
                EnableFields(InitClean());
                vc.getTxtNumDoc().setEnabled(false);
                vc.getTxtTelefono().setEnabled(false);
                vc.getTxtEmail().setEnabled(false);
                vc.getCmbTipoCliente().setEnabled(false);
                RowSelected = MousePress(vc.getTblClientes());
                break;

            case 3:
                titulo = "Eliminar Cliente";
                vc.getDlgCrudClientes().setName("eliminar");
                DisableFields(InitClean());
                RowSelected = MousePress(vc.getTblClientes());
                break;
        }
        if (RowSelected) {
            vc.getDlgCrudClientes().setSize(400, 560);
            vc.getDlgCrudClientes().setLocationRelativeTo(vc);
            vc.getDlgCrudClientes().setTitle(titulo);
            vc.getDlgCrudClientes().setVisible(true);
        }
    }

    private boolean MousePress(JTable tabla) {
        boolean press = false;
        try {
            if (tabla.getSelectedRowCount() == 1) {
                press = true;

                vc.getLblIdPer().setText(vc.getTblClientes().getValueAt(vc.getTblClientes().getSelectedRow(), 0).toString());
                vc.getTxtNumDoc().setText(vc.getTblClientes().getValueAt(vc.getTblClientes().getSelectedRow(), 1).toString());
                vc.getTxtNombre().setText(vc.getTblClientes().getValueAt(vc.getTblClientes().getSelectedRow(), 2).toString());
                vc.getTxtApellido().setText(vc.getTblClientes().getValueAt(vc.getTblClientes().getSelectedRow(), 3).toString());
                vc.getTxtDireccion().setText(vc.getTblClientes().getValueAt(vc.getTblClientes().getSelectedRow(), 4).toString());
                vc.getTxtTelefono().setText(vc.getTblClientes().getValueAt(vc.getTblClientes().getSelectedRow(), 5).toString());
                vc.getTxtEmail().setText(vc.getTblClientes().getValueAt(vc.getTblClientes().getSelectedRow(), 6).toString());
                String sexo = vc.getTblClientes().getValueAt(vc.getTblClientes().getSelectedRow(), 7).toString();
                if (sexo.equals("Masculino")) {
                    vc.getRdMasculino().setSelected(true);
                    vc.getRdFemenino().setSelected(false);
                } else if (sexo.equals("Femenino")) {
                    vc.getRdMasculino().setSelected(false);
                    vc.getRdFemenino().setSelected(true);
                }

                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                vc.getDatFechanaci().setDate(formato.parse(vc.getTblClientes().getValueAt(vc.getTblClientes().getSelectedRow(), 8).toString()));

                int id_per = Integer.parseInt(vc.getTblClientes().getValueAt(vc.getTblClientes().getSelectedRow(), 0).toString());

                TipoDocumento tp;

                tp = mp.ObtieneDocBD(id_per);
                vc.getCmbTipoDoc().getModel().setSelectedItem(tp);

                TipoCliente tc;
                tc = mtc.ObtieneTipoClienteBD(vc.getTblClientes().getValueAt(vc.getTblClientes().getSelectedRow(), 9).toString());
                vc.getCmbTipoCliente().getModel().setSelectedItem(tc);

            } else {
                JOptionPane.showMessageDialog(vc, "Seleccione una fila primero");
            }
        } catch (NullPointerException | ParseException e) {
            System.err.println(e);

        }
        return press;
    }

    private int IdIncremental() {
        ModeloPersona mod = new ModeloPersona();
        int id_per = mod.ObtieneID() + 1;
        return id_per;
    }

    private int IncrementoIdC() {
        ModeloCliente mce = new ModeloCliente();
        int id_cli = mce.ObtenerID() + 1;
        return id_cli;

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
            vc.getRdMasculino().setEnabled(true);
            vc.getRdFemenino().setEnabled(true);
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
            vc.getRdMasculino().setEnabled(false);
            vc.getRdFemenino().setEnabled(false);
        }
    }

    private Component[] InitClean() {
        Component[] com = {
            vc.getTxtNumDoc(),
            vc.getTxtNombre(),
            vc.getTxtApellido(),
            vc.getDatFechanaci(),
            vc.getTxtTelefono(),
            vc.getTxtEmail(),
            vc.getCmbTipoCliente(),
            vc.getCmbTipoDoc(),
            vc.getTxtDireccion(),
            vc.getBtnAgregarTipoCliente(),
            vc.getBtnAgregarTipoDoc(),};
        vc.getGrpGenero().clearSelection();
        CleanFields(com);
        return com;
    }

    private boolean ValidarCorreo() {
        boolean valida = false;
        String email = vc.getTxtEmail().getText().trim();

        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            valida = true;

        } catch (AddressException e) {
            JOptionPane.showMessageDialog(null, "El correo proporcionado es incorrecto\n"
                    + "-Recuerde que debe contener un @ y un punto\n"
                    + "+'ejemplo1@gmail.com'");
        }
        return valida;
    }

    private boolean ValidaData(String data) {
        boolean isva = true;
        if (data.isEmpty()) {
            isva = false;

        }
        return isva;
    }

    private boolean validadate(Date fecha) {
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

    private void crearEditarEliminarCliente() {
        if (vc.getDlgCrudClientes().getName().equals("crear")) {
            try {
                int id_per = Integer.parseInt(vc.getLblIdPer().getText());
                String numerodeindentificacion = vc.getTxtNumDoc().getText();
                String nombre = vc.getTxtNombre().getText();
                String apellido = vc.getTxtApellido().getText();
                java.sql.Date fechanac = new java.sql.Date(vc.getDatFechanaci().getDate().getTime());
                String telefono = vc.getTxtTelefono().getText();
                String sexo = null;
                if (vc.getRdMasculino().isSelected()) {
                    sexo = "Masculino";
                } else if (vc.getRdFemenino().isSelected()) {
                    sexo = "Femenino";
                }
                TipoDocumento tipodoc = (TipoDocumento) vc.getCmbTipoDoc().getSelectedItem();
                int id_tipdoc = tipodoc.getId_tip();
                String direccion = vc.getTxtDireccion().getText();
                String email = vc.getTxtEmail().getText();
                TipoCliente tipoclie = (TipoCliente) vc.getCmbTipoCliente().getSelectedItem();
                int id_tipcli = tipoclie.getId_tip();

                if (tipodoc.toString().equalsIgnoreCase("Cédula")) {
                    if (!Cedula.validarCedula(numerodeindentificacion)) {
                        JOptionPane.showMessageDialog(vc, "ingrese un número de cedula válido");
                        return;
                    }
                }
                if (!ValidaData(numerodeindentificacion)) {
                    JOptionPane.showMessageDialog(vc, "el numero de documento no puede quedar vacio");
                    return;
                }
                if (!ValidaData(nombre)) {
                    JOptionPane.showMessageDialog(vc, "el campo del nombre no puede quedar vacio  ");
                    return;
                }
                if (!ValidaData(apellido)) {
                    JOptionPane.showMessageDialog(vc, "el campo del apellido no puede quedar vacio ");
                    return;
                }
                if (!ValidaData(sexo)) {
                    JOptionPane.showMessageDialog(vc, "Elija el sexo ");
                    return;
                }
                if (!validadate(fechanac)) {
                    JOptionPane.showMessageDialog(vc, "Ingrese la fecha de nacimiento valida, recuerde que debe ser mayor de edad ");
                    return;
                }
                if (!ValidaData(direccion)) {
                    JOptionPane.showMessageDialog(vc, "el campo de la dirrecion esta vacío");
                    return;
                }
                if (!ValidaData(email)) {
                    JOptionPane.showMessageDialog(vc, "el email no es válido");
                    return;
                }
                if (!ValidarCorreo()) {
                    JOptionPane.showMessageDialog(vc, "ingrese un correo válido");
                    return;
                }

                ModeloPersona persona = new ModeloPersona();
                if (telefono.isEmpty()) {
                    telefono = "No registra";
                }
                persona.setId_per(id_per);
                persona.setNumeroidentificacion_per(numerodeindentificacion);
                persona.setNombre_per(nombre);
                persona.setApellido_per(apellido);
                persona.setFecha_nac(fechanac);
                persona.setTelefono_per(telefono);
                persona.setGenero_per(sexo);
                persona.setTipo_doc(id_tipdoc);
                persona.setDireccion_per(direccion);
                persona.setEmail_per(email);

                if (persona.InsertaPersonaBD() == null) {
                    ModeloCliente cli = new ModeloCliente();
                    cli.setId_cli(IncrementoIdC());
                    cli.setId_tip(id_tipcli);
                    cli.setId_per(id_per);

                    if (cli.InsertarCliente() == null) {
                        vc.getDlgCrudClientes().setVisible(false);
                        JOptionPane.showMessageDialog(vc, "Cliente guardado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(vc, "No se pudo añadir al cliente");
                    }
                } else {
                    JOptionPane.showMessageDialog(vc, "No se pudo añadir el registro");
                }
            } catch (NullPointerException | NumberFormatException e) {
                System.out.println(e);
            }
        } else {
            if (vc.getDlgCrudClientes().getName().equals("editar")) {
                try {
                    int id_per = Integer.parseInt(vc.getLblIdPer().getText());
                    String numeroidentificacion = vc.getTxtNumDoc().getText();
                    String nombre = vc.getTxtNombre().getText();
                    String apellido = vc.getTxtApellido().getText();
                    java.sql.Date fechanac = new java.sql.Date(vc.getDatFechanaci().getDate().getTime());
                    String telefono = vc.getTxtTelefono().getText();
                    String sexo = null;
                    if (vc.getRdMasculino().isSelected()) {
                        sexo = "Masculino";
                    } else if (vc.getRdFemenino().isSelected()) {
                        sexo = "Femenino";
                    }
                    TipoDocumento tipodoc = (TipoDocumento) vc.getCmbTipoDoc().getSelectedItem();
                    int id_tipo = tipodoc.getId_tip();
                    String direccion = vc.getTxtDireccion().getText();
                    String email = vc.getTxtEmail().getText();

                    if (!ValidaData(numeroidentificacion)) {
                        JOptionPane.showMessageDialog(vc, "el numero de documento no puede quedar vacio");
                        return;
                    }
                    if (!ValidaData(nombre)) {
                        JOptionPane.showMessageDialog(vc, "el campo del nombre no puede quedar vacio");
                        return;
                    }
                    if (!ValidaData(apellido)) {
                        JOptionPane.showMessageDialog(vc, "el campo del apellido no puede quedar vacío");
                        return;
                    }
                    if (!ValidaData(telefono)) {
                        JOptionPane.showMessageDialog(vc, "El campo del telefono no puede quedar vacio");
                        return;
                    }
                    if (!ValidaData(sexo)) {
                        JOptionPane.showMessageDialog(vc, "Elija el sexo");
                        return;
                    }
                    if (!validadate(fechanac)) {
                        JOptionPane.showMessageDialog(vc, "Ingrese la fecha de nacimiento validad , recuerde que debe ser mayor de edad ");
                        return;
                    }
                    if (!ValidaData(direccion)) {
                        JOptionPane.showMessageDialog(vc, "el campo de la dirrecion esta vacio ");
                        return;
                    }
                    if (!ValidaData(email)) {
                        JOptionPane.showMessageDialog(vc, "el email no es valido");
                        return;
                    }
                    if (!ValidarCorreo()) {
                        JOptionPane.showMessageDialog(vc, "ingrese un correo valido ");
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

                        vc.getDlgCrudClientes().setVisible(false);
                        JOptionPane.showMessageDialog(vc, "Cliente editado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(vc, "No se pudo editar al cliente");
                    }

                } catch (NumberFormatException | NullPointerException e) {
                    System.out.println(e);
                }
            } else {
                if (vc.getDlgCrudClientes().getName().equals("eliminar")) {
                    try {
                        int id_per = Integer.parseInt(vc.getLblIdPer().getText());
                        if (!mc.ClienteRegistrado(id_per)) {
                            ModeloPersona persona = new ModeloPersona();
                            persona.setId_per(id_per);
                            if (persona.EliminarPersonaDB(id_per) == null) {
                                vc.getDlgCrudClientes().setVisible(false);
                                JOptionPane.showMessageDialog(vc, "cliente  eliminado con éxito");
                            } else {
                                JOptionPane.showMessageDialog(vc, "No se pudo eliminar");
                            }
                        } else {
                            JOptionPane.showMessageDialog(vc, "Imposible borrar el registro\n"
                                    + "Este cliente tiene reservas y facturas a su nombre");
                        }
                    } catch (NumberFormatException | NullPointerException e) {
                        System.out.print(e);
                    }
                }
            }
        }
        CargarClientes();
    }
}
