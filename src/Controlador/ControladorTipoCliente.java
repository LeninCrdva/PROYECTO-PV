/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloTipoCliente;
import Modelo.TipoCliente;
import Vista.VistaTipoCliente;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ERIKA
 */
public class ControladorTipoCliente {

    private final VistaTipoCliente vtc;
    private final ModeloTipoCliente mtc;

    public ControladorTipoCliente(VistaTipoCliente vtc, ModeloTipoCliente mtc) {
        this.vtc = vtc;
        this.mtc = mtc;
        vtc.setVisible(true);

    }

    private void CargarTipoCliente() {
        List<TipoCliente> listatipoclie = mtc.ListaTipoCliente();
        DefaultTableModel mol;
        mol = (DefaultTableModel) vtc.getTblTipoCliente().getModel();
        mol.setNumRows(0);
        vtc.getTblTipoCliente().getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        vtc.getTblTipoCliente().getTableHeader().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        vtc.getTblTipoCliente().getTableHeader().setOpaque(false);
        vtc.getTblTipoCliente().getTableHeader().setBackground(new Color(32, 136, 203));
        vtc.getTblTipoCliente().getTableHeader().setForeground(new Color(255, 255, 255));
        vtc.getTblTipoCliente().setRowHeight(25);

        listatipoclie.stream().forEach(tip -> {
            String[] tipo = {
                String.valueOf(tip.getId_tip()),
                tip.getNombre_tip(),
            };
            mol.addRow(tipo);

        });
    }

    private boolean Pulsar(JTable tbl) {
        boolean press = false;
        try {
            if (tbl.getSelectedRowCount() == 1) {
                press = true;
                vtc.getLblIdTipoCliente().setText(vtc.getTblTipoCliente().getValueAt(vtc.getTblTipoCliente().getSelectedRow(), 0).toString());
                vtc.getTxtNombreTipoCliente().setText(vtc.getTblTipoCliente().getValueAt(vtc.getTblTipoCliente().getSelectedRow(), 1).toString());

            } else {
                JOptionPane.showMessageDialog(vtc, "seleccione un dato ");
            }

        } catch (NullPointerException e) {
            System.err.println(e);
        }
        return press;

    }

    private void AbrirDialogoTipoCliente(int ce) {
        String titulo = null;
        boolean Seleccion = true;

        switch (ce) {
            case 1:
                titulo = "Agregar un tipo cliente";
                vtc.getDlgCrudTipoCliente().setName("Crear ");
                break;
            case 2:
                titulo = "Editar  tipo cliente ";
                vtc.getDlgCrudTipoCliente().setName("editar");
                Seleccion = Pulsar(vtc.getTblTipoCliente());
                break;
            case 3:
                titulo = "Eliminar tipo cliente ";
                vtc.getDlgCrudTipoCliente().setName("eliminar ");
                Seleccion = Pulsar(vtc.getTblTipoCliente());
                break;

        }
        if (Seleccion) {
            vtc.getDlgCrudTipoCliente().setTitle(titulo);
            vtc.getDlgCrudTipoCliente().setSize(300, 200);
            vtc.getDlgCrudTipoCliente().setLocationRelativeTo(vtc);
            vtc.getDlgCrudTipoCliente().setVisible(true);
        }

    }
    private int CrearID(){
        int id_tip = mtc.ObtenerIdTipoCliente();
        id_tip ++;
        return  id_tip;
    }

    private void CrearEditarEliminarTipoCliente() {
        String nomb = vtc.getDlgCrudTipoCliente().getName();
        switch (nomb) {
            case "crear":
            try {
                vtc.getLblIdTipoCliente().setText(Integer.toString(CrearID()));
                int id_tip = Integer.parseInt(vtc.getLblIdTipoCliente().getText());
                String nombre = vtc.getTxtNombreTipoCliente().getText();

                ModeloTipoCliente tipo = new ModeloTipoCliente();
                tipo.setId_tip(id_tip);
                tipo.setNombre_tip(nombre);
                if (tipo.InsertarTipoCliente() == null) {
                    JOptionPane.showMessageDialog(vtc, "    Se registro correctamente ");
                    vtc.getDlgCrudTipoCliente().dispose();
                } else {
                    JOptionPane.showMessageDialog(vtc, " Error ! .. no se puedo anadir");
                }

            } catch (NullPointerException | NumberFormatException e) {
                System.err.println(e);
            }
            break;
            case "editar":
            try {
                int id_tip = Integer.parseInt(vtc.getLblIdTipoCliente().getText());
                String nombre = vtc.getTxtNombreTipoCliente().getText();
                
                ModeloTipoCliente mol = new ModeloTipoCliente();
                mol.setId_tip(id_tip);
                mol.setNombre_tip(nombre);
                if (mol.modificarTipoCliente(id_tip) == null) {
                    JOptionPane.showMessageDialog(vtc, "Editado correctamente");
                    vtc.getDlgCrudTipoCliente().dispose();
                } else {
                    JOptionPane.showMessageDialog(vtc, "ERROR!..no se pudo editar ");
                }

            } catch (NullPointerException | NumberFormatException e) {
                System.err.println(e);
            }

            break;
            case "eliminar ":
            try {
                int id_tip = Integer.parseInt(vtc.getLblIdTipoCliente().getText());
                ModeloTipoCliente tipo = new ModeloTipoCliente();
                if (tipo.EliminarTipoCliente(id_tip) == null) {
                    JOptionPane.showMessageDialog(vtc, "Eliminado correctamente ");
                    vtc.getDlgCrudTipoCliente().dispose();
                } else {
                    JOptionPane.showMessageDialog(vtc, "ERROR!..no se pudo eliminar ");
                }
            } catch (NullPointerException | NumberFormatException e) {
                System.err.println(e);
            }
            break;

        }
        CargarTipoCliente();
    }

    private void BuscarTipoCliente() {
        String bus = vtc.getTxtBuscar().getText().trim();
        List<TipoCliente> listatipo = mtc.BuscarTipoClienteBD(bus);
        DefaultTableModel mol = (DefaultTableModel) vtc.getTblTipoCliente().getModel();
        mol.setNumRows(0);
        vtc.getTblTipoCliente().getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        vtc.getTblTipoCliente().getTableHeader().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        vtc.getTblTipoCliente().getTableHeader().setOpaque(false);
        vtc.getTblTipoCliente().getTableHeader().setBackground(new Color(32, 136, 203));
        vtc.getTblTipoCliente().getTableHeader().setForeground(new Color(255, 255, 255));
        vtc.getTblTipoCliente().setRowHeight(25);
        listatipo.stream().forEach(tipo -> {

            String[] fila = {
                String.valueOf(tipo.getId_tip()),
                tipo.getNombre_tip(),};
            mol.addRow(fila);

        });
    }

    public void IniciarControl() {
        CargarTipoCliente();
        vtc.getBtnCrear().addActionListener(l -> AbrirDialogoTipoCliente(1));
        vtc.getBtnEditar().addActionListener(l -> AbrirDialogoTipoCliente(2));
        vtc.getBtnEleminar().addActionListener(l -> AbrirDialogoTipoCliente(3));
        vtc.getBtnAceptar().addActionListener(l -> CrearEditarEliminarTipoCliente());
        vtc.getBtnCancelar().addActionListener(l -> vtc.getDlgCrudTipoCliente().dispose());
        vtc.getLblIdTipoCliente().setText(Integer.toString(CrearID()));
        vtc.getTxtBuscar().addKeyListener(new KeyAdapter() {
         
            public void KeyTyped(java.awt.event.KeyEvent evt) {
                BuscarTipoCliente(); 

            }
        });

    }

}
