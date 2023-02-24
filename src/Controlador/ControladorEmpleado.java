package Controlador;

import Modelo.*;
import Vista.VistaEmpleados;
import java.awt.event.KeyAdapter;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorEmpleado {

    private final ModeloPersona mp;
    private final ModeloEmpleado me;
    private final VistaEmpleados ve;

    public ControladorEmpleado(ModeloPersona mp, ModeloEmpleado me, VistaEmpleados ve) {
        this.mp = mp;
        this.me = me;
        this.ve = ve;
        ve.setVisible(true);
    }

    public void IniciarControl() {
        CargaEmpleados();
        ve.getBtncrear().addActionListener(l -> AbreDialogo(1));
        ve.getBtneditar().addActionListener(l -> AbreDialogo(2));
        ve.getBtneliminar().addActionListener(l -> AbreDialogo(3));
        ve.getBtnaceptar().addActionListener(l -> crearEditarEliminarEmpleado());
        ve.getBtncancelar().addActionListener(l -> ve.getDlgcrudempleado().dispose());
        ve.getTxtbuscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BuscaEmpleados();
            } 
        });
    }

    private void CargaEmpleados() {
        List<Persona> listaemp = mp.ListarPersonas();
        DefaultTableModel df;
        df = (DefaultTableModel) ve.getTblempleados().getModel();
        df.setNumRows(0);

        listaemp.stream().forEach(em -> {
            String[] FilaNueva = {
                Integer.toString(em.getId_per()),
                em.getNumeroidentificacion_per(),
                em.getNombre_per(),
                em.getApellido_per(),
                em.getDireccion_per(),
                em.getTelefono_per(),
                em.getEmail_per(),
                em.getGenero_per(),
                String.valueOf(em.getFecha_nac())
            };
            df.addRow(FilaNueva);
        });
    }

    private void BuscaEmpleados() {
        List<Persona> listaemp = mp.BuscaPersonaDB(ve.getTxtbuscar().getText());
        DefaultTableModel df;
        df = (DefaultTableModel) ve.getTblempleados().getModel();
        df.setNumRows(0);

        listaemp.stream().forEach(em -> {
            String[] FilaNueva = {
                Integer.toString(em.getId_per()),
                em.getNumeroidentificacion_per(),
                em.getNombre_per(),
                em.getApellido_per(),
                em.getDireccion_per(),
                em.getTelefono_per(),
                em.getEmail_per(),
                em.getGenero_per(),
                String.valueOf(em.getFecha_nac())
            };
            df.addRow(FilaNueva);
        });
    }
    
    private void AbreDialogo(int ce) {
        String title = null;
        boolean RowSelected = false;
        switch (ce) {
            case 1:
                title = "Añadir un nuevo empleado";
                ve.getDlgcrudempleado().setName("crear");

                break;

            case 2:
                RowSelected = true;
                title = "Editar empleado";
                ve.getDlgcrudempleado().setName("editar");
                break;

            case 3:
                RowSelected = true;
                title = "Eliminar empleado";
                ve.getDlgcrudempleado().setName("eliminar");
                break;
        }

        if (RowSelected) {
            JOptionPane.showMessageDialog(ve, "Seleccione una fila de la tabla primero");
        } else {
            ve.getDlgcrudempleado().setLocationRelativeTo(ve);
            ve.getDlgcrudempleado().setSize(400, 600);
            ve.getDlgcrudempleado().setTitle(title);
            ve.getDlgcrudempleado().setVisible(true);
        }
    }

    private void crearEditarEliminarEmpleado() {
        if (ve.getDlgcrudempleado().getName().equals("crear")) {
            try {
                String numeroidentificacion = ve.getTxtdocnum().getText();
                String nombre = ve.getTxtnombreper().getText();
                String apellido = ve.getTxtapellidoper().getText();
                Date fechanac = ve.getDtefechanac().getDate();
                String telefono = ve.getTxttelefono().getText();
                String sexo = null;
                if (ve.getRdmasculino().isSelected()) {
                    sexo = "Masculino";
                } else if (ve.getRdfemenino().isSelected()) {
                    sexo = "Femenino";
                }
                int tipodoc = ve.getCbtipodoc().getSelectedIndex();
                String direccion = ve.getTxtdireccion().getText();
                String email = ve.getTxtemail().getText();
                int labor = ve.getCblabor().getSelectedIndex();

                ModeloPersona persona = new ModeloPersona();

                persona.setNumeroidentificacion_per(numeroidentificacion);
                persona.setNombre_per(nombre);
                persona.setApellido_per(apellido);
                persona.setFecha_nac(fechanac);
                persona.setTelefono_per(telefono);
                persona.setGenero_per(sexo);
                persona.setTipo_doc(tipodoc);
                persona.setDireccion_per(direccion);
                persona.setEmail_per(email);

                if (persona.InsertaPersonaBD() == null) {
                    ve.getDlgcrudempleado().setVisible(false);
                    JOptionPane.showMessageDialog(ve, "Empleado añadido correctamente");
                } else {
                    JOptionPane.showMessageDialog(ve, "No se pudo añadir al empleado");
                }
            } catch (NullPointerException | NumberFormatException e) {
                System.out.println(e);
            }
        } else {
            if (ve.getDlgcrudempleado().getName().equals("editar")) {
                try {
                    int id_per = Integer.parseInt(ve.getLblidper().getText());

                    String numeroidentificacion = ve.getTxtdocnum().getText();
                    String nombre = ve.getTxtnombreper().getText();
                    String apellido = ve.getTxtapellidoper().getText();
                    Date fechanac = ve.getDtefechanac().getDate();
                    String telefono = ve.getTxttelefono().getText();
                    String sexo = null;
                    if (ve.getRdmasculino().isSelected()) {
                        sexo = "Masculino";
                    } else if (ve.getRdfemenino().isSelected()) {
                        sexo = "Femenino";
                    }
                    int tipodoc = ve.getCbtipodoc().getSelectedIndex();
                    String direccion = ve.getTxtdireccion().getText();
                    String email = ve.getTxtemail().getText();
                    int labor = ve.getCblabor().getSelectedIndex();

                    ModeloPersona persona = new ModeloPersona();

                    persona.setNumeroidentificacion_per(numeroidentificacion);
                    persona.setNombre_per(nombre);
                    persona.setApellido_per(apellido);
                    persona.setFecha_nac(fechanac);
                    persona.setTelefono_per(telefono);
                    persona.setGenero_per(sexo);
                    persona.setTipo_doc(tipodoc);
                    persona.setDireccion_per(direccion);
                    persona.setEmail_per(email);

                    if (persona.ModficarPersonaDB(id_per) == null) {
                        ve.getDlgcrudempleado().setVisible(false);
                        JOptionPane.showMessageDialog(ve, "Empleado editado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(ve, "No se pudo editar al empleado");
                    }

                } catch (NumberFormatException | NullPointerException e) {
                    System.out.println(e);
                }
            } else {
                if (ve.getDlgcrudempleado().getName().equals("eliminar")) {
                    try {
                        int id_per = Integer.parseInt(ve.getLblidper().getText());

                        ModeloPersona persona = new ModeloPersona();
                        persona.setId_per(id_per);
                        if (persona.EliminarPersonaDB(id_per) == null) {
                            ve.getDlgcrudempleado().setVisible(false);
                            JOptionPane.showMessageDialog(ve, "Empleado eliminado con éxito");
                        } else {
                            JOptionPane.showMessageDialog(ve, "No se pudo eliminar al empleado");
                        }

                    } catch (NumberFormatException | NullPointerException e) {
                        System.out.print(e);
                    }
                }
            }
        }
        CargaEmpleados();
    }
}
