package MVC;

import Controlador.ControladorLogin;
import Modelo.ModeloCuenta;
import Vista.VistaLogin;

public class MVCMain {

    public static void main(String[] args) {
        VistaLogin vs = new VistaLogin();
        ModeloCuenta mc = new ModeloCuenta();
        ControladorLogin ctr = new ControladorLogin(vs, mc);
        ctr.InciarControl();
    }
}
