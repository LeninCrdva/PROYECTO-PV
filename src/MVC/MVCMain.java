package MVC;

import Controlador.ControladorLogin;
import Controlador.ControladorPrincipal;
import Modelo.ModeloCuenta;
import Vista.VistaLogin;
import Vista.VistaPrincipal;

public class MVCMain {
    public static void main(String[] args) {
        VistaLogin vs = new VistaLogin();
        ModeloCuenta mc = new ModeloCuenta();
        ControladorLogin ctr = new ControladorLogin(vs, mc);
        
        VistaPrincipal vista = new VistaPrincipal();
        ControladorPrincipal control = new ControladorPrincipal(vista);
        control.iniciaControl();
    }
}
