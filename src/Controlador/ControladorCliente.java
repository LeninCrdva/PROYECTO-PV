/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ModeloCliente;
import Modelo.ModeloPersona;
import Modelo.ModeloTipoCliente;
import Modelo.Persona;
import Modelo.TipoCliente;
import Vista.VistaClientes;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ERIKA
 */
public class ControladorCliente {

    private final ModeloPersona mp;
    private final ModeloCliente mc;
    private final Vista.VistaClientes vc;
    private final ModeloTipoCliente mtc;

    public ControladorCliente(ModeloPersona mp, ModeloCliente mc, VistaClientes vc, ModeloTipoCliente mtc) {
        this.mp = mp;
        this.mc = mc;
        this.vc = vc;
        this.mtc = mtc;
        vc.setVisible(true);
    }

    public void IniciarControl() {
        CargarClientes();
        LlenarComboTipoCliente();
        vc.getBtnCrear().addActionListener(l -> AbrirDialogo(1));
        vc.getBtnEditar().addActionListener(l -> AbrirDialogo(2));
        vc.getBtnEliminar().addActionListener(l -> AbrirDialogo(3));
        vc.getBtnAceptar().addActionListener(l -> crearEditarEliminarCliente());
        vc.getBtnCancelar().addActionListener(l -> vc.getDlgCrudClientes().dispose());
        vc.getLblIdPer().setText(Integer.toString(IdIncremental()));
        vc.getTxtbuscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BuscaClientes();
            }
        });

    }

    private void CargarClientes() {
        List<Persona> listaclientes = mp.ListarPersonas();
        DefaultTableModel dt;
        dt = (DefaultTableModel) vc.getTblClientes().getModel();
        dt.setNumRows(0);

        listaclientes.stream().forEach(cli -> {
            String[] nuevo = {
                Integer.toString(cli.getId_per()),
                cli.getNumeroidentificacion_per(),
                cli.getNombre_per(),
                cli.getApellido_per(),
                cli.getDireccion_per(),
                cli.getTelefono_per(),
                cli.getEmail_per(),
                cli.getGenero_per(),
                String.valueOf(cli.getFecha_nac())
            };
            dt.addRow(nuevo);

        });

    }

    private void LlenarComboTipoCliente() {
        vc.getCmbTipoCliente().removeAllItems();
        List<TipoCliente> list = mtc.LlenarCombo();
        list.stream().forEach(ti -> {
            vc.getCmbTipoCliente().addItem(new TipoCliente(ti.getId_tip(), ti.getNombre_tip().toString()));
        });
    }

    private void BuscaClientes() {
        List<Persona> listaclie = mp.BuscaPersonaDB(vc.getTxtbuscar().getText());
        DefaultTableModel df;
        df = (DefaultTableModel) vc.getTblClientes().getModel();
        df.setNumRows(0);

        listaclie.stream().forEach(cli -> {
            String[] Nuevo = {
                Integer.toString(cli.getId_per()),
                cli.getNumeroidentificacion_per(),
                cli.getNombre_per(),
                cli.getApellido_per(),
                cli.getDireccion_per(),
                cli.getTelefono_per(),
                cli.getEmail_per(),
                cli.getGenero_per(),
                String.valueOf(cli.getFecha_nac())
            };
            df.addRow(Nuevo);
        });
    }

    private void AbrirDialogo(int pe) {
        String titulo = null;
        boolean RowSelected = false;
        switch (pe) {
            case 1:
                titulo = "Agregar un nuevo cliente";
                vc.getDlgCrudClientes().setName("crear");

                break;

            case 2:
                RowSelected = true;
                titulo = "Editar cliente";
                vc.getDlgCrudClientes().setName("editar");
                break;

            case 3:
                RowSelected = true;
                titulo = "Eliminar Cliente";
                vc.getDlgCrudClientes().setName("eliminar");
                break;
        }

        if (RowSelected) {
            JOptionPane.showMessageDialog(vc, "Seleccione un dato");
        } else {
            vc.getDlgCrudClientes().setSize(600, 630);
            vc.getDlgCrudClientes().setLocationRelativeTo(vc);
            vc.getDlgCrudClientes().setTitle(titulo);
            vc.getDlgCrudClientes().setVisible(true);
        }
    }

    private int IdIncremental() {
        ModeloPersona mod = new ModeloPersona();
        int id_per = mod.ObtieneID() + 1;
        return id_per;
    }

    private void crearEditarEliminarCliente() {
        if (vc.getDlgCrudClientes().getName().equals("crear")) {
            try {
                int id_per = Integer.parseInt(vc.getLblIdPer().getText());
                String numerodeindentificacion = vc.getTxtNumDoc().getText();
                int numeroI ;
                if (vc.getRdCedula().isSelected()) {
                    numeroI = 1;
                } else if (vc.getRdPasaporte().isSelected()) {
                    numeroI = 1;

                }

                String nombre = vc.getTxtNombre().getText();
                String apellido = vc.getTxtApellido().getText();
                Date fechanac = vc.getDatFechanaci().getDate();
                String telefono = vc.getTxtApellido().getText();
                String sexo = null;
                if (vc.getRdMasculino().isSelected()) {
                    sexo = "Masculino";
                } else if (vc.getRdFemenino().isSelected()) {
                    sexo = "Femenino";
                }
                String direccion = vc.getTxtDireccion().getText();
                String email = vc.getTxtEmail().getText();
                int tipocliente = vc.getCmbTipoCliente().getSelectedIndex();

                ModeloPersona persona = new ModeloPersona();

                persona.setId_per(id_per);
                persona.setNumeroidentificacion_per(numerodeindentificacion);
                persona.setNombre_per(nombre);
                persona.setApellido_per(apellido);
                persona.setFecha_nac(fechanac);
                persona.setTelefono_per(telefono);
                persona.setGenero_per(sexo);
//               persona.setTipo_doc(tipodoc);
                persona.setDireccion_per(direccion);
                persona.setEmail_per(email);

                if (persona.InsertaPersonaBD() == null) {
                    ModeloCliente cli = new ModeloCliente();
                    cli.setId_cli(1);
                    cli.setId_per(id_per);
                    cli.setId_tip(1);

                    if (cli.InsertarCliente() == null) {
                        vc.getDlgCrudClientes().setVisible(false);
                        JOptionPane.showMessageDialog(vc, "cliente añadido correctamente");
                    } else {
                        JOptionPane.showMessageDialog(vc, "No se pudo añadir al cliente");
                    }
                } else {
                    JOptionPane.showMessageDialog(vc, "No se pudo añadir el registro");
                }
            } catch (NullPointerException | NumberFormatException e) {
                System.out.println(e);
            }
        } else {
            if (vc.getDlgCrudClientes().getName().equals("editar")) {
                try {
                    int id_per = Integer.parseInt(vc.getLblIdPer().getText());

                    String numeroidentificacion = vc.getTxtNumDoc().getText();
                    String nombre = vc.getTxtNombre().getText();
                    String apellido = vc.getTxtApellido().getText();
                    Date fechanac = vc.getDatFechanaci().getDate();
                    String telefono = vc.getTxtTelefono().getText();
                    String sexo = null;
                    if (vc.getRdMasculino().isSelected()) {
                        sexo = "Masculino";
                    } else if (vc.getRdFemenino().isSelected()) {
                        sexo = "Femenino";
                    }
                   int  numeroI =0;
                    if (vc.getRdCedula().isSelected()) {
                        numeroI = 1;
                    } else if (vc.getRdPasaporte().isSelected()) {
                        numeroI = 2;

                    }
                 
                    String direccion = vc.getTxtDireccion().getText();
                    String email = vc.getTxtEmail().getText();
                    int tipocliente = vc.getCmbTipoCliente().getSelectedIndex();

                    ModeloPersona persona = new ModeloPersona();

                    persona.setNumeroidentificacion_per(numeroidentificacion);
                    persona.setNombre_per(nombre);
                    persona.setApellido_per(apellido);
                    persona.setFecha_nac(fechanac);
                    persona.setTelefono_per(telefono);
                    persona.setGenero_per(sexo);
                    persona.setTipo_doc(numeroI);
                    persona.setDireccion_per(direccion);
                    persona.setEmail_per(email);

                    if (persona.ModficarPersonaDB(id_per) == null) {
                        vc.getDlgCrudClientes().setVisible(false);
                        JOptionPane.showMessageDialog(vc, "Cliente editado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(vc, "No se pudo editar al cliente");
                    }

                } catch (NumberFormatException | NullPointerException e) {
                    System.out.println(e);
                }
            } else {
                if (vc.getDlgCrudClientes().getName().equals("eliminar")) {
                    try {
                        int id_per = Integer.parseInt(vc.getLblIdPer().getText());

                        ModeloPersona persona = new ModeloPersona();
                        persona.setId_per(id_per);
                        if (persona.EliminarPersonaDB(id_per) == null) {
                            vc.getDlgCrudClientes().setVisible(false);
                            JOptionPane.showMessageDialog(vc, "cliente  eliminado con éxito");
                        } else {
                            JOptionPane.showMessageDialog(vc, "No se pudo eliminar");
                        }

                    } catch (NumberFormatException | NullPointerException e) {
                        System.out.print(e);
                    }
                }
            }
        }
        CargarClientes();
    }

}
