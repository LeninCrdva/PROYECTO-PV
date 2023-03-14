package Modelo;

public class Cedula {

    public static boolean validarCedula(String cedula) {
        if (cedula == null || cedula.length() != 10) {
            return false;
        }

        try {
            Integer.parseInt(cedula);
        } catch (NumberFormatException e) {
            return false;
        }

        int digitoVerificador = Integer.parseInt(cedula.substring(9));
        int suma = 0;
        int coeficiente = 2;
        for (int i = 8; i >= 0; i--) {
            int valor = Character.getNumericValue(cedula.charAt(i));
            int producto = valor * coeficiente;
            suma += producto > 9 ? producto - 9 : producto;
            coeficiente = coeficiente == 2 ? 1 : coeficiente + 1;
        }

        int residuo = suma % 10;
        int numeroVerificador = residuo == 0 ? 0 : 10 - residuo;

        return numeroVerificador == digitoVerificador;
    }
}
