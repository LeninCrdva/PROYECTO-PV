package Modelo;

public class Cuenta {
    
    private int id_cue;
    private int id_emp;
    private String username_cue;
    private String password_cue;

    public Cuenta() {
    }

    public Cuenta(int id_cue, int id_emp, String username_cue, String password_cue) {
        this.id_cue = id_cue;
        this.id_emp = id_emp;
        this.username_cue = username_cue;
        this.password_cue = password_cue;
    }

    public int getId_cue() {
        return id_cue;
    }

    public void setId_cue(int id_cue) {
        this.id_cue = id_cue;
    }

    public String getUsername_cue() {
        return username_cue;
    }

    public void setUsername_cue(String username_cue) {
        this.username_cue = username_cue;
    }

    public String getPassword_cue() {
        return password_cue;
    }

    public void setPassword_cue(String password_cue) {
        this.password_cue = password_cue;
    }

    public int getId_emp() {
        return id_emp;
    }

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "id_cue=" + id_cue + ", username_cue=" + username_cue + ", password_cue=" + password_cue + '}';
    }
}
