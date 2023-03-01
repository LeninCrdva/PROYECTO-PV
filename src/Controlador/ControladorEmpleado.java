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
    private final ModeloLabor ml;
    private final ModeloTipoDocumento mtd;

    public ControladorEmpleado(ModeloPersona mp, ModeloEmpleado me, VistaEmpleados ve, ModeloLabor ml, ModeloTipoDocumento mtd) {
        this.mp = mp;
        this.me = me;
        this.ve = ve;
        this.ml = ml;
        this.mtd = mtd;
        ve.setVisible(true);
    }

    public void IniciarControl() {
        CargaEmpleados();
        LlenaComboLabor();
        LlenaComboTipoDoc();
        IncrementaIDE();
        ve.getBtncrear().addActionListener(l -> AbreDialogo(1));
        ve.getBtneditar().addActionListener(l -> AbreDialogo(2));
        ve.getBtneliminar().addActionListener(l -> AbreDialogo(3));
        ve.getBtnaceptar().addActionListener(l -> crearEditarEliminarEmpleado());
        ve.getBtncancelar().addActionListener(l -> ve.getDlgcrudempleado().dispose());
        ve.getLblidper().setText(Integer.toString(IncrementaID()));
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

    private void LlenaComboLabor() {
        ve.getCblabor().removeAllItems();
        List<Labor> list = ml.LlenaComboBD();
        list.stream().forEach(la -> {
            ve.getCblabor().addItem(new Labor(la.getId_lab(), la.getNombre_lab()));
        });
    }

    private void LlenaComboTipoDoc() {
        ve.getCbtipodoc().removeAllItems();
        List<TipoDocumento> list = mtd.LlenaComboBD();
        list.stream().forEach(doc -> {
            ve.getCbtipodoc().addItem(new TipoDocumento(doc.getId_tip(), doc.getNombre_doc()));
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
            ve.getDlgcrudempleado().setSize(390, 540);
            ve.getDlgcrudempleado().setTitle(title);
            ve.getDlgcrudempleado().setVisible(true);
        }
    }

    private int IncrementaID() {
        ModeloPersona mp = new ModeloPersona();
        int id_per = mp.ObtieneID() + 1;
        return id_per;
    }
    
    private int IncrementaIDE() {
        ModeloEmpleado me = new ModeloEmpleado();
        int id_emp = me.ObtieneIDBD() + 1;
        return id_emp;
    }
    
    private void crearEditarEliminarEmpleado() {
        if (ve.getDlgcrudempleado().getName().equals("crear")) {
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
                TipoDocumento tipodoc = (TipoDocumento) ve.getCbtipodoc().getSelectedItem();
                int id_tipo = mtd.ConsultaIDBD(tipodoc.toString());
                String direccion = ve.getTxtdireccion().getText();
                String email = ve.getTxtemail().getText();
                int labor = ve.getCblabor().getSelectedIndex();
                
                System.out.println(id_tipo);
                ModeloPersona persona = new ModeloPersona();

                persona.setId_per(id_per);
                persona.setNumeroidentificacion_per(numeroidentificacion);
                persona.setNombre_per(nombre);
                persona.setApellido_per(apellido);
                persona.setFecha_nac(fechanac);
                persona.setTelefono_per(telefono);
                persona.setGenero_per(sexo);
                persona.setTipo_doc(id_tipo);
                persona.setDireccion_per(direccion);
                persona.setEmail_per(email);

                if (persona.InsertaPersonaBD() == null) {
                    ModeloEmpleado empleado = new ModeloEmpleado();
                    empleado.setId_emp(IncrementaIDE());
                    empleado.setIdlabor_emp(labor);
                    empleado.setId_per(id_per);
                    empleado.setIdcuenta_emp(1);
                    if (empleado.InsertaEmpleado() == null) {
                        ve.getDlgcrudempleado().setVisible(false);
                        int create_account = JOptionPane.showConfirmDialog(ve, "Empleado añadido correctamente.\n¿Desea crear un cuenta para este usuario?"
                        ,"Creación de cuenta" ,JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        
                        if (create_account == JOptionPane.YES_OPTION) {
                            
                        } else {
                            System.out.println("No");
                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(ve, "No se pudo añadir al empleado");
                    }
                } else {
                    JOptionPane.showMessageDialog(ve, "No se pudo añadir el registro");
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
