package MVC;

import Controlador.ControladorPrincipal;
import Vista.VistaPrincipal;

public class MVCMain {
    public static void main(String[] args) {
        VistaPrincipal vista = new VistaPrincipal();
        ControladorPrincipal controlador=new ControladorPrincipal(vista);
        controlador.IniciaControlVP();
    }
}
