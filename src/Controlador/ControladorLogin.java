package Controlador;

import Vista.VistaLogin;
import Modelo.ModeloCuenta;
import Vista.VistaPrincipal;
import java.awt.event.MouseAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

public class ControladorLogin {

    private final VistaLogin vl;
    private final ModeloCuenta mc;

    public ControladorLogin(VistaLogin vl, ModeloCuenta mc) {
        this.vl = vl;
        this.mc = mc;
        vl.setVisible(true);
        vl.setLocationRelativeTo(null);
    }

    public void InciarControl() {
        vl.getBtnlogin().addActionListener(l -> InformacionValida());
        vl.getLblhidepass().setVisible(false);
        vl.getLblviewpass().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                VerOcultarPassword();
            }

        });
        vl.getLblhidepass().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                VerOcultarPassword();
            }

        });
    }

    private void InformacionValida() {
        String username = vl.getTxtusername().getText().trim().toUpperCase();
        char[] passwordKey = vl.getTxtpassword().getPassword();
        String password = new String(passwordKey);
        String hashpassword = mc.ExistenDatosBD(username);
        if (username.isEmpty() && password.isEmpty()) {
            JOptionPane.showMessageDialog(vl, "Campos vacíos");
        } else {
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(vl, "Campo de usuario vacío");
            } else {
                if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(vl, "Campo de contraseña vacío");
                } else {
                    try {
                        if (BCrypt.checkpw(password, hashpassword)) {
                            VistaPrincipal vista = new VistaPrincipal();
                            ControladorPrincipal control = new ControladorPrincipal(vista);
                            LocalDate fechaActual = LocalDate.now();
                            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
                            String fechaFormateada = fechaActual.format(formato);
                            vista.getLblfechaDiaria().setText(fechaFormateada);
                            control.iniciaControl();
                            vl.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Datos erróneos, inténtelo otra vez", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NullPointerException ex) {
                        JOptionPane.showMessageDialog(null, "Datos erróneos, inténtelo otra vez", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    private void VerOcultarPassword() {
        if (vl.getLblviewpass().isVisible()) {
            vl.getLblviewpass().setVisible(false);
            vl.getLblhidepass().setVisible(true);
            vl.getTxtpassword().setEchoChar((char) 0);
        } else {
            vl.getLblviewpass().setVisible(true);
            vl.getLblhidepass().setVisible(false);
            vl.getTxtpassword().setEchoChar('•');
        }
    }
}
