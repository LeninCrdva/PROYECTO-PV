/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConnectionPG;
import Modelo.ModeloServicio;
import Modelo.Servicio;
import Vista.VistaServicio;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ERIKA
 */
public class ControladorServicio {

    private final VistaServicio vs;
    private final ModeloServicio ms;

    public ControladorServicio(VistaServicio vs, ModeloServicio ms) {
        this.vs = vs;
        this.ms = ms;
        vs.setVisible(true);
    }

    public void IniciarControl() {
        CargarServicio();
        vs.getBtnCrearServi().addActionListener(l -> AbrirDialogo(1));
        vs.getBtnEditarServi().addActionListener(l -> AbrirDialogo(2));
        vs.getBtnEliminarServi().addActionListener(l -> AbrirDialogo(3));
        vs.getBtnAceptarSer().addActionListener(l -> CrearEditarEliminarServicio());
        vs.getBtnCancelarSer().addActionListener(l -> vs.getDlgCrudServicios().dispose());
        vs.getLblIdSer().setText(Integer.toString(CrearID()));
        vs.getBtnImprimirServi().addActionListener(l -> Reporte());
        

        vs.getTxtBuscarServicio().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BuscarServicio();
            }
        });
        addTextKeyListenerNotNumber(vs.getTxtNombreSer(), 50);
    }

    private void addTextKeyListenerNotNumber(JTextField text, int maxLength) {
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char k = evt.getKeyChar();
                if (Character.isDigit(k) || text.getText().trim().length() >= maxLength) {
                    evt.consume();
                }
            }
        });
    }

    private void ValidarCampo() {

        vs.getTxtBuscarServicio().addKeyListener(new KeyAdapter() {

            public void KeyTyped(java.awt.event.KeyEvent evt) {
                BuscarServicio();
            }
        });

    }

    private void CargarServicio() {
        List<Servicio> listaser = ms.ListarServiciosBD();
        DefaultTableModel df;
        df = (DefaultTableModel) vs.getTblServicios().getModel();
        df.setNumRows(0);
        listaser.stream().forEach(ser -> {
            String[] fila = {
                String.valueOf(ser.getId_ser()),
                ser.getNombre_ser(),
                ser.getDescripcion_ser(),
                String.valueOf(ser.getPrecio_ser()),};
            df.addRow(fila);

        });

    }

    private void BuscarServicio() {
        String bus = vs.getTxtBuscarServicio().getText().trim();
        List<Servicio> listaser = ms.BuscarServicioBD(bus);
        DefaultTableModel df = (DefaultTableModel) vs.getTblServicios().getModel();
        df.setNumRows(0);

        listaser.stream().forEach(ser -> {
            String[] fila = {
                String.valueOf(ser.getId_ser()),
                ser.getNombre_ser(),
                ser.getDescripcion_ser(),
                Double.toString(ser.getPrecio_ser())
            };
            df.addRow(fila);

        });

    }

    private boolean MousePress(JTable tabla) {
        boolean press = false;
        try {
            if (tabla.getSelectedRowCount() == 1) {
                press = true;
                vs.getLblIdSer().setText(vs.getTblServicios().getValueAt(vs.getTblServicios().getSelectedRow(), 0).toString());
                vs.getTxtNombreSer().setText(vs.getTblServicios().getValueAt(vs.getTblServicios().getSelectedRow(), 1).toString());
                vs.getTxtDescripcionSer().setText(vs.getTblServicios().getValueAt(vs.getTblServicios().getSelectedRow(), 2).toString());
                vs.getTxtPrecioSer().setText(vs.getTblServicios().getValueAt(vs.getTblServicios().getSelectedRow(), 3).toString());

            } else {
                JOptionPane.showMessageDialog(vs, "SELECCIONE UNA FILA");
            }
        } catch (NullPointerException e) {
            System.err.println(e);

        }
        return press;

    }

    private void AbrirDialogo(int ce) {
        String titulo = null;
        boolean seleccion = true;
        switch (ce) {
            case 1:
                titulo = "  Añadir un nuevo servicio ";
                vs.getDlgCrudServicios().setName("crear");
                CleanFields(GiveComponent());
                EnableFields(GiveComponent());
                vs.getLblIdSer().setText(Integer.toString(CrearID()));
                break;

            case 2:
                titulo = "Editar Servicio ";
                vs.getDlgCrudServicios().setName("editar");
                EnableFields(GiveComponent());
                seleccion = MousePress(vs.getTblServicios());
                break;
            case 3:
                titulo = " eliminar servicio ";
                vs.getDlgCrudServicios().setName("eliminar");
                DisableFields(GiveComponent());
                seleccion = MousePress(vs.getTblServicios());
                break;

        }
        if (seleccion) {
            vs.getDlgCrudServicios().setTitle(titulo);
            vs.getDlgCrudServicios().setSize(500, 500);
            vs.getDlgCrudServicios().setLocationRelativeTo(vs);
            vs.getDlgCrudServicios().setVisible(true);

        }

    }

    private int CrearID() {
        int id_ser = ms.ObtenerID();
        id_ser++;
        return id_ser;

    }

    private void EnableFields(Component[] component) {
        for (Component com : component) {
            if (com instanceof JTextField) {
                ((JTextField) com).setEnabled(true);
            } else if (com instanceof JFormattedTextField) {
                ((JFormattedTextField) com).setEnabled(true);
            } else if (com instanceof JSlider) {
                ((JSlider) com).setEnabled(true);
            }
        }
    }

    private void DisableFields(Component[] component) {
        for (Component com : component) {
            if (com instanceof JTextField) {
                ((JTextField) com).setEnabled(false);
                ((JTextField) com).setDisabledTextColor(com.getForeground());
            } else if (com instanceof JFormattedTextField) {
                ((JFormattedTextField) com).setEnabled(false);
                ((JFormattedTextField) com).setDisabledTextColor(com.getForeground());
            } else if (com instanceof JSlider) {
                ((JSlider) com).setEnabled(false);
            }
        }
    }

    private void CleanFields(Component[] components) {
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(null);
            } else if (component instanceof JFormattedTextField) {
                ((JFormattedTextField) component).setText(null);
            } else if (component instanceof JSlider) {
                ((JSlider) component).setValue(0);
            }
        }
    }

    private Component[] GiveComponent() {
        Component[] com = {
            vs.getTxtNombreSer(),
            vs.getTxtPrecioSer(),
            vs.getTxtDescripcionSer()
        };
        return com;
    }

  private void CrearEditarEliminarServicio() {
        String nombre = vs.getDlgCrudServicios().getName();

        switch (nombre) {
            case "crear":
              try {
//                vs.getLblIdSer().setText(Integer.toString(CrearID()));
                int id_ser = Integer.parseInt(vs.getLblIdSer().getText());
                String nomb = vs.getTxtNombreSer().getText().toUpperCase().trim();
                String desc = vs.getTxtDescripcionSer().getText().trim();
                String precio_ser = vs.getTxtPrecioSer().getText();
                if (nomb.isEmpty()) {
                    JOptionPane.showMessageDialog(vs, "El campo del nombre no puede estar vacio  ");
                    return;
                }

                if (desc.isEmpty()) {
                    JOptionPane.showMessageDialog(vs, "El campo de la descripcion  no puede estar vacio  ");
                    return;
                }

                if (String.valueOf(precio_ser).isEmpty()) {
                    JOptionPane.showMessageDialog(vs, "el campo de precio no puede estar vacio  ");
                    return;
                }

                if (ms.ExisteNombreServcioBD(nomb)) {
                    JOptionPane.showMessageDialog(vs, "El nombre que ingresaste ya existe ");
                    return;
                }
                ModeloServicio servi = new ModeloServicio();

                servi.setId_ser(id_ser);
                servi.setNombre_ser(nomb);
                servi.setDescripcion_ser(desc);
                servi.setPrecio_ser(Double.parseDouble(precio_ser));
                if (servi.InsertarServicioBD() == null) {
                    JOptionPane.showMessageDialog(vs, "Registro de servicio añadido correctamente");
                    vs.getDlgCrudServicios().dispose();

                } else {
                    JOptionPane.showMessageDialog(vs, "no se pudo añadir al registro");
                }

            } catch (NullPointerException | NumberFormatException e) {
                System.err.println(e);
            }
            break;

            case "editar":
              try {
                int id_ser = Integer.parseInt(vs.getLblIdSer().getText());
                String nombr = vs.getTxtNombreSer().getText().toUpperCase().trim();
                String desc = vs.getTxtDescripcionSer().getText().trim();
                String precio_ser = vs.getTxtPrecioSer().getText();
                if (nombr.isEmpty()) {
                    JOptionPane.showMessageDialog(vs, "El campo del nombre no puede estar vacio  ");
                    return;
                }
                if (desc.isEmpty()) {
                    JOptionPane.showMessageDialog(vs, "El campo de la descripcion  no puede estar vacio  ");
                    return;
                }
                if (String.valueOf(precio_ser).isEmpty()) {
                    JOptionPane.showMessageDialog(vs, "el campo de precio no puede estar vacio  ");
                    return;
                }
                ModeloServicio serv = new ModeloServicio();
//
                serv.setId_ser(id_ser);
                serv.setNombre_ser(nombr);
                serv.setDescripcion_ser(desc);
                serv.setPrecio_ser(Double.parseDouble(precio_ser));

                if (ms.GetNombreBD(id_ser).equals(nombr)) {
                    if (serv.ModificarServicioBD(id_ser) == null) {
                        JOptionPane.showMessageDialog(vs, "Registro editado correctamente ");
                        vs.getDlgCrudServicios().dispose();

                    } else {
                        JOptionPane.showMessageDialog(vs, "No se pudo editar ");
                    }
                } else if(!ms.ExisteNombreServcioBD(nombr)){
                    if (serv.ModificarServicioBD(id_ser)== null) {
                        JOptionPane.showMessageDialog(vs, "Registro editado correctamente ");
                        vs.getDlgCrudServicios().dispose();

                        
                    }else{
                     JOptionPane.showMessageDialog(vs, "No se pudo editar ");
                    }

                }else{
                 JOptionPane.showMessageDialog(vs, "No se puedes ingresar un nombre que ya existe  ");
                }

//                
            } catch (NullPointerException | NumberFormatException e) {
                System.err.println(e);
            }
            break;

            case "eliminar":
              try {
                int id_ser = Integer.parseInt(vs.getLblIdSer().getText());
                ModeloServicio serv = new ModeloServicio();
                if (serv.EliminarSevicioBD(id_ser) == null) {
                    JOptionPane.showMessageDialog(vs, "Registro eliminado correctamente");
                    vs.getDlgCrudServicios().dispose();
                } else {
                    JOptionPane.showMessageDialog(vs, "Error .. no se pudo eliminar el servicio ");
                }
            } catch (NullPointerException | NumberFormatException e) {
                System.err.println(e);
            }
            break;

        }
        CargarServicio();
    }
    
    private void Reporte (){
    ConnectionPG con =new ConnectionPG();
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/Vista/Reportes/Servicio.jasper"));
            JasperPrint  jp = JasperFillManager.fillReport(jr, null,con.getCon());
            JasperViewer jv = new JasperViewer(jp,false);
            jv.setVisible(true);
        } catch (JRException e) {
            Logger.getLogger(ControladorServicio.class.getName()).log(Level.SEVERE,null,e);
        }
    }

}
