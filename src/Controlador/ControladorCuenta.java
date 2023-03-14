package Controlador;

import Modelo.ModeloCuenta;
import Modelo.Cuenta;
import Vista.VistaCuenta;
import java.awt.event.KeyAdapter;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.mindrot.jbcrypt.BCrypt;

public class ControladorCuenta {

    private final ModeloCuenta mc;
    private final VistaCuenta vc;

    public ControladorCuenta(ModeloCuenta mc, VistaCuenta vc) {
        this.mc = mc;
        this.vc = vc;
    }

    private void IniciarControl() {
        CargaCuentas();
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

    private void CargaCuentas() {
        List<Cuenta> lista = mc.ListarCuentasBD();
        DefaultTableModel df = (DefaultTableModel) vc.getTblcuenta().getModel();
        df.setRowCount(0);

        lista.stream().forEach(ct -> {
            String[] FilaNuueva = {
                Integer.toString(ct.getId_cue()),
                ct.getUsername_cue()
            };
            df.addRow(FilaNuueva);
        });
    }

    private void BuscaCuentas() { //Incompleto
        List<Cuenta> lista = mc.BuscarCuentasBD(vc.getTxtbuscarcuenta().getText());
        DefaultTableModel df = (DefaultTableModel) vc.getTblcuenta().getModel();
        df.setRowCount(0);

        lista.stream().forEach(ct -> {
            String[] FilaNuueva = {
                Integer.toString(ct.getId_cue()),
                ct.getUsername_cue()
            };
            df.addRow(FilaNuueva);
        });
    }

    protected void AbreDialogo(int ce) {
        String title = null;
        boolean RowSelected = true;

        switch (ce) {
            case 1:
                title = "Crear una nueva cuenta";
                vc.getDlgcrudcuenta().setName("crear");
                break;
            case 2:
                title = "Editar datos de la cuenta";
                vc.getDlgcrudcuenta().setName("editar");
                RowSelected = MousePressed(vc.getTblcuenta());
                break;
            case 3:
                title = "Eliminar la cuenta";
                vc.getDlgcrudcuenta().setName("eliminar");
                RowSelected = MousePressed(vc.getTblcuenta());
                break;
        }

        if (RowSelected) {
            vc.getDlgcrudcuenta().setTitle(title);
            vc.getDlgcrudcuenta().setSize(295, 305);
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
                vc.getTxtpassword().setText(vc.getTblcuenta().getValueAt(vc.getTblcuenta().getSelectedRow(), 2).toString());
                vc.getTxtconfirmpassword().setText(vc.getTblcuenta().getValueAt(vc.getTblcuenta().getSelectedRow(), 3).toString());
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
    
    private void CrearEditarEliminarCuenta() {
        String name = vc.getDlgcrudcuenta().getName();
        switch (name) {
            case "crear":
                try {
                vc.getLblidcuenta().setText(Integer.toString(1));
                int id_cuenta = Integer.parseInt(vc.getLblidcuenta().getText());
                String user = vc.getTxtusername().getText().trim();
                char[] passwordkey = vc.getTxtpassword().getPassword();
                
                String password = new String(passwordkey);
                
                char[] passwordkeyconfirm = vc.getTxtpassword().getPassword();
                String confirm_password = new String(passwordkeyconfirm);
                                
                if (password.equals(confirm_password)) {
                    if (SecurePassword(password)) {
                        String encryptedpassword = BCrypt.hashpw(password, BCrypt.gensalt());
                        ModeloCuenta cuenta = new ModeloCuenta();
                        cuenta.setId_cue(id_cuenta);
                        cuenta.setUsername_cue(user);
                        cuenta.setPassword_cue(encryptedpassword);
                        if (cuenta.InsertaCuentaBD() == null) {
                            JOptionPane.showMessageDialog(vc, "Cuenta creada correctamente");
                            vc.getDlgcrudcuenta().dispose();
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
                int id_cuenta = Integer.parseInt(vc.getLblidcuenta().getText());
                String user = vc.getTxtusername().getText().trim();
                String password = Arrays.toString(vc.getTxtpassword().getPassword());
                if (SecurePassword(password)) {
                    ModeloCuenta cuenta = new ModeloCuenta();
                    cuenta.setId_cue(id_cuenta);
                    cuenta.setUsername_cue(user);
                    cuenta.setPassword_cue(password);
                    if (cuenta.ModificarCuentaBD(id_cuenta) == null) {
                        JOptionPane.showMessageDialog(vc, "Datos de la cuenta editados correctamente");
                        vc.getDlgcrudcuenta().dispose();
                    } else {
                        JOptionPane.showMessageDialog(vc, "No se pudo editar el registro");
                    }
                } else {
                    JOptionPane.showMessageDialog(vc, "La contraseña debe contener:\n"
                            + "• Al menos una letra en mayúscula, una mínuscula, un número\n"
                            + "• Un tamaño mínimo de 8 dígitos", "Contraseña inválida",
                            JOptionPane.INFORMATION_MESSAGE);
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
