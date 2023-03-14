package MVC;

import Controlador.ControladorLogin;
import Controlador.ControladorPrincipal;
import Modelo.ModeloCuenta;
import Vista.VistaLogin;
import Vista.VistaPrincipal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class MVCMain {

    public static void main(String[] args) {
        VistaLogin vs = new VistaLogin();
        ModeloCuenta mc = new ModeloCuenta();
        ControladorLogin ctr = new ControladorLogin(vs, mc);
        ctr.InciarControl();

        VistaPrincipal vista = new VistaPrincipal();
        ControladorPrincipal control = new ControladorPrincipal(vista);
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
        String fechaFormateada = fechaActual.format(formato);
        vista.getLblfechaDiaria().setText(fechaFormateada);
        control.iniciaControl();
    }
}
