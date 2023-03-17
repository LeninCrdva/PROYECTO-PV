package Controlador;

import Modelo.*;
import Vista.VistaCuenta;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.mindrot.jbcrypt.BCrypt;

public class ControladorCuenta {

    private final ModeloCuenta mc;
    private final VistaCuenta vc;

    public ControladorCuenta(ModeloCuenta mc, VistaCuenta vc) {
        this.mc = mc;
        this.vc = vc;
        this.vc.setVisible(true);
    }

    public void IniciarControl() {
        CargaCuentas();
        ModelForCombo();
        LlenaComboEmpleado();
        vc.getBtncrearcuenta().addActionListener(l -> AbreDialogo(1));
        vc.getBtneditcuenta().addActionListener(l -> AbreDialogo(2));
        vc.getBtndeletecuenta().addActionListener(l -> AbreDialogo(3));
        vc.getBtnaceptar().addActionListener(l -> CrearEditarEliminarCuenta());
        vc.getBtncancelar().addActionListener(l -> vc.getDlgcrudcuenta().dispose());
        vc.getTxtbuscarcuenta().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                BuscaCuentas();
            }
        });
    }

    private void ModelForCombo() {
        DefaultComboBoxModel<Persona> model = new DefaultComboBoxModel<>();
        vc.getCbempleado().setModel(model);
    }

    private void CargaCuentas() {
        ModeloEmpleado me = new ModeloEmpleado();
        List<Cuenta> lista = mc.ListarCuentasBD();
        List<Persona> listaper = me.ListarPersonasEmpleadosBD();
        List<Empleado> listaemp = me.ListarEmpleadosBD();
        DefaultTableModel df = (DefaultTableModel) vc.getTblcuenta().getModel();
        df.setRowCount(0);
        vc.getTblcuenta().getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        vc.getTblcuenta().getTableHeader().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        vc.getTblcuenta().getTableHeader().setOpaque(false);
        vc.getTblcuenta().getTableHeader().setBackground(new Color(32, 136, 203));
        vc.getTblcuenta().getTableHeader().setForeground(new Color(255, 255, 255));
        vc.getTblcuenta().setRowHeight(25);

        listaper.stream().forEach(per -> {
            listaemp.stream().forEach(em -> {
                lista.stream().forEach(ct -> {
                    if (per.getId_per() == em.getId_per()) {
                        if (em.getId_emp() == ct.getId_emp()) {
                            String[] FilaNuueva = {
                                Integer.toString(ct.getId_cue()),
                                ct.getUsername_cue(),
                                per.getNumeroidentificacion_per(),
                                per.getNombre_per(),
                                per.getApellido_per()
                            };
                            df.addRow(FilaNuueva);

                        }
                    }

                });
            });
        });
    }

    public void LlenaComboEmpleado() {
        ModeloEmpleado mp = new ModeloEmpleado();
        vc.getCbempleado().removeAllItems();
        List<Empleado> list = mp.LlenarComboEmBD();
        list.stream().forEach(em -> {
            vc.getCbempleado().addItem(new Empleado(em.getId_emp(), em.getNombre_per(), em.getApellido_per(), em.getNumeroidentificacion_per()));
        });
    }

    private void BuscaCuentas() {
        ModeloEmpleado me = new ModeloEmpleado();
        List<Cuenta> lista = mc.BuscarCuentasBD(vc.getTxtbuscarcuenta().getText());
        List<Persona> listaper = me.ListarPersonasEmpleadosBD();
        List<Empleado> listaemp = me.ListarEmpleadosBD();
        DefaultTableModel df = (DefaultTableModel) vc.getTblcuenta().getModel();
        df.setRowCount(0);
        vc.getTblcuenta().getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        vc.getTblcuenta().getTableHeader().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        vc.getTblcuenta().getTableHeader().setOpaque(false);
        vc.getTblcuenta().getTableHeader().setBackground(new Color(32, 136, 203));
        vc.getTblcuenta().getTableHeader().setForeground(new Color(255, 255, 255));
        vc.getTblcuenta().setRowHeight(25);

        listaper.stream().forEach(per -> {
            listaemp.stream().forEach(em -> {
                lista.stream().forEach(ct -> {
                    if (per.getId_per() == em.getId_per()) {
                        if (em.getId_emp() == ct.getId_emp()) {
                            String[] FilaNuueva = {
                                Integer.toString(ct.getId_cue()),
                                ct.getUsername_cue(),
                                per.getNumeroidentificacion_per(),
                                per.getNombre_per(),
                                per.getApellido_per()
                            };
                            df.addRow(FilaNuueva);
                        }
                    }
                });
            });
        });

    }

    private int IncrementaIDCuenta() {
        int id_cue = mc.ObtenerIDCuentaBD();
        id_cue++;
        return id_cue;
    }

    protected void AbreDialogo(int ce) {
        String title = null;
        boolean RowSelected = true;

        switch (ce) {
            case 1:
                title = "Crear una nueva cuenta";
                vc.getDlgcrudcuenta().setName("crear");
                vc.getLblidcuenta().setText(Integer.toString(IncrementaIDCuenta()));
                vc.getLblemp().setVisible(true);
                vc.getCbempleado().setVisible(true);
                CleanFields(InitComponents());
                EnableFields(InitComponents());
                break;
            case 2:
                title = "Editar datos de la cuenta";
                vc.getDlgcrudcuenta().setName("editar");
                RowSelected = MousePressed(vc.getTblcuenta());
                EnableFields(InitComponents());
                vc.getLblemp().setVisible(false);
                vc.getCbempleado().setVisible(false);
                break;
            case 3:
                title = "Eliminar la cuenta";
                vc.getDlgcrudcuenta().setName("eliminar");
                RowSelected = MousePressed(vc.getTblcuenta());
                DisableFields(InitComponents());
                vc.getLblemp().setVisible(false);
                vc.getCbempleado().setVisible(false);

                break;
        }

        if (RowSelected) {
            vc.getDlgcrudcuenta().setTitle(title);
            vc.getDlgcrudcuenta().setSize(350, 350);
            vc.getDlgcrudcuenta().setLocationRelativeTo(vc);
            vc.getDlgcrudcuenta().setVisible(true);
        }
    }

    private boolean MousePressed(JTable table) {
        boolean press = false;
        try {
            if (table.getSelectedRowCount() == 1) {
                press = true;
                vc.getLblidcuenta().setText(vc.getTblcuenta().getValueAt(vc.getTblcuenta().getSelectedRow(), 0).toString());
                vc.getTxtusername().setText(vc.getTblcuenta().getValueAt(vc.getTblcuenta().getSelectedRow(), 1).toString());

            } else {
                JOptionPane.showMessageDialog(vc, "Seleccione una fila primero");
            }
        } catch (NullPointerException e) {
            System.err.print(e);
        }
        return press;
    }

    protected static boolean SecurePassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    private void CleanFields(Component[] components) {
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(null);
            } else if (component instanceof JPasswordField) {
                ((JPasswordField) component).setText(null);
            }
        }
    }

    private void EnableFields(Component[] components) {
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setEnabled(true);
            } else if (component instanceof JPasswordField) {
                ((JPasswordField) component).setEnabled(true);
            }
        }
    }

    private void DisableFields(Component[] components) {
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setEnabled(false);
                ((JTextField) component).setDisabledTextColor(component.getForeground());
            } else if (component instanceof JPasswordField) {
                ((JPasswordField) component).setEnabled(false);
            }
        }
    }

    private Component[] InitComponents() {
        Component[] com = {
            vc.getTxtusername(),
            vc.getTxtpassword(),
            vc.getTxtconfirmpassword()
        };
        return com;
    }

    private void CrearEditarEliminarCuenta() {
        String name = vc.getDlgcrudcuenta().getName();
        switch (name) {
            case "crear":
                try {
                int id_cuenta = Integer.parseInt(vc.getLblidcuenta().getText());
                String user = vc.getTxtusername().getText().toUpperCase().trim();
                char[] passwordkey = vc.getTxtpassword().getPassword();

                String password = new String(passwordkey);

                char[] passwordkeyconfirm = vc.getTxtpassword().getPassword();
                String confirm_password = new String(passwordkeyconfirm);

                Empleado emp = (Empleado) vc.getCbempleado().getSelectedItem();
                int id_emp = emp.getId_emp();
                if (password.equals(confirm_password)) {
                    if (SecurePassword(password)) {
                        String encryptedpassword = BCrypt.hashpw(password, BCrypt.gensalt());
                        ModeloCuenta cuenta = new ModeloCuenta();
                        cuenta.setId_cue(id_cuenta);
                        cuenta.setUsername_cue(user);
                        cuenta.setPassword_cue(encryptedpassword);
                        cuenta.setId_emp(id_emp);
                        if (cuenta.InsertaCuentaBD() == null) {
                            JOptionPane.showMessageDialog(vc, "Cuenta creada correctamente");
                            vc.getDlgcrudcuenta().dispose();
                            LlenaComboEmpleado();
                        } else {
                            JOptionPane.showMessageDialog(vc, "No se pudo crear la cuenta");
                        }
                    } else {
                        JOptionPane.showMessageDialog(vc, "La contraseña debe contener:\n"
                                + "• Al menos una letra en mayúscula, una mínuscula, un número\n"
                                + "• Un tamaño mínimo de 8 dígitos", "Contraseña inválida",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showConfirmDialog(vc, "Las contraseñas no coinciden",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NullPointerException | NumberFormatException e) {
                System.err.println(e);
            }
            break;

            case "editar":
                try {
                ModeloEmpleado me = new ModeloEmpleado();
                int id_cuenta = Integer.parseInt(vc.getLblidcuenta().getText());
                String user = vc.getTxtusername().getText().toUpperCase().trim();
                int id_emp = me.ObtenerIDEMBD(user);
                System.out.println(id_emp);

                char[] passwordkey = vc.getTxtpassword().getPassword();

                String password = new String(passwordkey);

                char[] passwordkeyconfirm = vc.getTxtpassword().getPassword();
                String confirm_password = new String(passwordkeyconfirm);

                if (password.equals(confirm_password)) {

                    if (SecurePassword(password)) {
                        String encryptedpassword = BCrypt.hashpw(password, BCrypt.gensalt());
                        ModeloCuenta cuenta = new ModeloCuenta();
                        cuenta.setId_emp(id_emp);
                        cuenta.setId_cue(id_cuenta);
                        cuenta.setUsername_cue(user);
                        cuenta.setPassword_cue(encryptedpassword);
                        if (cuenta.ModificarCuentaBD(id_cuenta) == null) {
                            JOptionPane.showMessageDialog(vc, "Datos de la cuenta editados correctamente");
                            vc.getDlgcrudcuenta().dispose();
                            CleanFields(InitComponents());
                        } else {
                            JOptionPane.showMessageDialog(vc, "No se pudo editar el registro");
                        }
                    } else {
                        JOptionPane.showMessageDialog(vc, "La contraseña debe contener:\n"
                                + "• Al menos una letra en mayúscula, una mínuscula, un número\n"
                                + "• Un tamaño mínimo de 8 letras", "Contraseña inválida",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (NullPointerException | NumberFormatException e) {
                System.err.println(e);
            }
            break;
            case "eliminar":
                try {
                int id_cuenta = Integer.parseInt(vc.getLblidcuenta().getText());
                ModeloCuenta cuenta = new ModeloCuenta();
                if (cuenta.EliminarCuentaBD(id_cuenta) == null) {
                    JOptionPane.showMessageDialog(vc, "Cuenta eliminada correctamente");
                    vc.getDlgcrudcuenta().dispose();
                    LlenaComboEmpleado();
                } else {
                    JOptionPane.showMessageDialog(vc, "No se pudo eliminar la cuenta");
                }
            } catch (NullPointerException | NumberFormatException e) {
                System.err.println(e);
            }
            break;
        }
        CargaCuentas();
    }

}
