package Modelo;

public class TipoDocumento {

    private int id_tip;
    private String nombre_doc;

    public TipoDocumento() {
    }

    public TipoDocumento(int id_tip, String nombre_doc) {
        this.id_tip = id_tip;
        this.nombre_doc = nombre_doc;
    }

    public int getId_tip() {
        return id_tip;
    }

    public void setId_tip(int id_tip) {
        this.id_tip = id_tip;
    }

    public String getNombre_doc() {
        return nombre_doc;
    }

    public void setNombre_doc(String nombre_doc) {
        this.nombre_doc = nombre_doc;
    }

    @Override
    public String toString() {
        return id_tip + " " + nombre_doc;
    }
}
