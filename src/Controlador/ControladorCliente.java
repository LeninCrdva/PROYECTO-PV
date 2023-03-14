/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ModeloCliente;
import Modelo.ModeloCuenta;
import Modelo.ModeloPersona;
import Modelo.ModeloTipoCliente;
import Modelo.ModeloTipoDocumento;
import Modelo.Persona;
import Modelo.TipoCliente;
import Modelo.TipoDocumento;
import Vista.VistaClientes;
import Vista.VistaCuenta;
import Vista.VistaTipoCliente;
import Vista.VistaTipoDocumento;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
    private final Vista.VistaClientes vc;
    private final ModeloTipoCliente mtc;
    private final ModeloTipoDocumento mtd;

    public ControladorCliente(ModeloPersona mp, ModeloCliente mc, VistaClientes vc, ModeloTipoCliente mtc, ModeloTipoDocumento mtd) {
        this.mp = mp;
        this.mc = mc;
        this.vc = vc;
        this.mtc = mtc;
        this.mtd = mtd;
        vc.setVisible(true);
    }

    public void IniciarControl() {
        CargarClientes();
        LlenarComboTipoCliente();
        LlenarComboTipoDoc();
        IncrementoIdC();
        ValidarCampo();
        vc.getBtnCrear().addActionListener(l -> AbrirDialogo(1));
        vc.getBtnEditar().addActionListener(l -> AbrirDialogo(2));
        vc.getBtnEliminar().addActionListener(l -> AbrirDialogo(3));
        vc.getBtnAceptar().addActionListener(l -> crearEditarEliminarCliente());
        vc.getBtnCancelar().addActionListener(l -> vc.getDlgCrudClientes().dispose());
        vc.getLblIdPer().setText(Integer.toString(IdIncremental()));
        vc.getBtnAgregarTipoCliente().addActionListener(l -> addCombo(1));
        vc.getBtnAgregarTipoDoc().addActionListener(l -> addCombo(2));
        
        vc.getTxtbuscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BuscaClientes();
            }
        });

    }
 

    private void ValidarCampo() {
        vc.getDatFechanaci().setDateFormatString("yyyy-MM-dd");
        vc.getTxtbuscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BuscaClientes();
            }
        });
        addTextKeyListenerNotNumber(vc.getTxtNombre(),50);
        addTextKeyListenerNotNumber(vc.getTxtApellido(), 50);
        MaxLengthOnly(vc.getTxtApellido(),200);
        MaxLengthOnly(vc.getTxtEmail(),200);
        addTextKeyListenerNotText(vc.getTxtTelefono(), 20);
        
        

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
        VistaTipoCliente vtc = new VistaTipoCliente();
        try {
            int id_tip = codigoIncrementalTipoCliente();
            String nombre = vtc.getTxtNombreTipoCliente().getText().toUpperCase().trim();

            ModeloTipoCliente tipo = new ModeloTipoCliente();
            tipo.setId_tip(id_tip);
            tipo.setNombre_tip(nombre);

            if (tipo.InsertarTipoCliente() == null) {
                vtc.getDlgCrudTipoCliente().setVisible(false);
                JOptionPane.showMessageDialog(vtc, "guardado correctamente ");

            } else {
                JOptionPane.showMessageDialog(vtc, "ERROR ... NO SE PUDO GUARDAR");
            }
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    private void CargarClientes() {
        List<Cliente> listaclientes = mc.listarclientes();
        DefaultTableModel dt;
        dt = (DefaultTableModel) vc.getTblClientes().getModel();
        dt.setNumRows(0);

        listaclientes.stream().forEach(cli -> {
            String[] nuevo = {
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
            dt.addRow(nuevo);

        });

    }

    private void LlenarComboTipoCliente() {
        vc.getCmbTipoCliente().removeAllItems();
        List<TipoCliente> list = mtc.LlenarCombo();
        list.stream().forEach(ti -> {
            vc.getCmbTipoCliente().addItem(new TipoCliente(ti.getId_tip(), ti.getNombre_tip().toString()));
        });
    }

    private void LlenarComboTipoDoc() {
        vc.getCmbTipoDoc().removeAllItems();
        List<TipoDocumento> list = mtd.LlenaComboBD();
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
        boolean RowSelected = false;
        switch (pe) {
            case 1:
                titulo = "Agregar un nuevo cliente";
                vc.getDlgCrudClientes().setName("crear");

                break;

            case 2:
                RowSelected = true;
                titulo = "Editar cliente";
                vc.getDlgCrudClientes().setName("editar");
                EnableFields(InitClean());
                RowSelected = MousePress(vc.getTblClientes());
                break;

            case 3:
                RowSelected = true;
                titulo = "Eliminar Cliente";
                vc.getDlgCrudClientes().setName("eliminar");
                DisableFields(InitClean());
                RowSelected = MousePress(vc.getTblClientes());
                break;
        }

        if (RowSelected) {
         
            vc.getDlgCrudClientes().setSize(600, 630);
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
                ((JDateChooser) component).setEnabled(true);
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
                ((JButton) component).setEnabled(true);

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

    private void crearEditarEliminarCliente() {
        if (vc.getDlgCrudClientes().getName().equals("crear")) {
            try {
                int id_per = Integer.parseInt(vc.getLblIdPer().getText());
                String numerodeindentificacion = vc.getTxtNumDoc().getText();
                String nombre = vc.getTxtNombre().getText();
                String apellido = vc.getTxtApellido().getText();
                Date fechanac = vc.getDatFechanaci().getDate();
                String telefono = vc.getTxtApellido().getText();
                String sexo = null;
                if (vc.getRdMasculino().isSelected()) {
                    sexo = "Masculino";
                } else if (vc.getRdFemenino().isSelected()) {
                    sexo = "Femenino";
                }
                TipoDocumento tipodoc = (TipoDocumento) vc.getCmbTipoDoc().getSelectedItem();
                int id_tipo = mtd.ConsultaIDBD(tipodoc.toString());
                String direccion = vc.getTxtDireccion().getText();
                String email = vc.getTxtEmail().getText();
//                TipoCliente tipoclie =(TipoCliente) vc.getCmbTipoCliente().getSelectedItem();
//                int tipocliente = vc.com
                System.out.println(id_tipo);

                ModeloPersona persona = new ModeloPersona();

                persona.setId_per(id_per);
                persona.setNumeroidentificacion_per(numerodeindentificacion);
                persona.setNombre_per(nombre);
                persona.setApellido_per(apellido);
                persona.setFecha_nac(fechanac);
                persona.setTelefono_per(telefono);
                persona.setGenero_per(sexo);
                persona.setTipo_doc(id_tipo);
                persona.setDireccion_per(direccion);
                persona.setEmail_per(email);

                if (persona.InsertaPersonaBD() == null) {
                    ModeloCliente cli = new ModeloCliente();
                    cli.setId_cli(IncrementoIdC());
//                    cli.setId_per(id_per);
//                    cli.setId_tip(1);
                    TipoCliente tipoclie = (TipoCliente) vc.getCmbTipoCliente().getSelectedItem();

                    int tipocliente = mtc.ConsularIDBD(tipoclie.toString());
                    cli.setId_tip(tipocliente);
                    cli.setId_per(id_per);

                    if (cli.InsertarCliente() == null) {
                        vc.getDlgCrudClientes().setVisible(false);
                        JOptionPane.showMessageDialog(vc, "cliente guardado correctamente ");
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
                    Date fechanac = vc.getDatFechanaci().getDate();
                    String telefono = vc.getTxtTelefono().getText();
                    String sexo = null;
                    if (vc.getRdMasculino().isSelected()) {
                        sexo = "Masculino";
                    } else if (vc.getRdFemenino().isSelected()) {
                        sexo = "Femenino";
                    }
                    TipoDocumento tipodoc = (TipoDocumento) vc.getCmbTipoDoc().getSelectedItem();
                    int id_tipo = mtd.ConsultaIDBD(tipodoc.toString());
                    String direccion = vc.getTxtDireccion().getText();
                    String email = vc.getTxtEmail().getText();
                    int tipocliente = vc.getCmbTipoCliente().getSelectedIndex();

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

                        ModeloPersona persona = new ModeloPersona();
                        persona.setId_per(id_per);
                        if (persona.EliminarPersonaDB(id_per) == null) {
                            vc.getDlgCrudClientes().setVisible(false);
                            JOptionPane.showMessageDialog(vc, "cliente  eliminado con éxito");
                        } else {
                            JOptionPane.showMessageDialog(vc, "No se pudo eliminar");
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
