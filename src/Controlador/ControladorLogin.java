package Controlador;

import Vista.VistaLogin;
import Modelo.ModeloCuenta;
import java.awt.event.MouseAdapter;
import javax.swing.JOptionPane;

public class ControladorLogin {

    private final VistaLogin vl;
    private final ModeloCuenta mc;

    public ControladorLogin(VistaLogin vl, ModeloCuenta mc) {
        this.vl = vl;
        this.mc = mc;
        vl.setVisible(true);
    }

    public void InciarControl() {
        vl.getBtnlogin().addActionListener(l -> InformacionValida());
        vl.getLblviewpass().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                VerOcultarPassword();
            }

        });
    }

    private void InformacionValida() {
        String username = vl.getTxtusername().getText().trim();
        char[] passwordKey = vl.getTxtpassword().getPassword();
        String password = new String(passwordKey);
        if (username.isEmpty() && password.isEmpty()) {
            JOptionPane.showMessageDialog(vl, "Campos vacíos");
        } else {
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(vl, "Campo de usuario vacío");
            } else {
                if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(vl, "Campo de contraseña vacío");
                } else {
                    if (mc.ExistenDatosBD(username, password)) {
                        //Llamar al main view
                    } else {
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
        } else { // password is currently shown
            vl.getLblviewpass().setVisible(true);
            vl.getLblhidepass().setVisible(false);
            vl.getTxtpassword().setEchoChar('*');
        }
    }
}
