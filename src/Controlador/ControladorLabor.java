package Controlador;

import Modelo.ConnectionPG;
import Vista.VistaLabor;
import Modelo.ModeloLabor;
import Modelo.Labor;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
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

public class ControladorLabor {

    private final VistaLabor vl;
    private final ModeloLabor ml;

    public ControladorLabor(VistaLabor vl, ModeloLabor ml) {
        this.vl = vl;
        this.ml = ml;
        vl.setVisible(true);
    }

    public void IniciarControl() {
        CargaLabor();
        vl.getBtncrearlab().addActionListener(l -> AbreDialogo(1));
        vl.getBtneditarlab().addActionListener(l -> AbreDialogo(2));
        vl.getBtneliminarlab().addActionListener(l -> AbreDialogo(3));
        vl.getBtnaceptar().addActionListener(l -> CrearEditarEliminarLabor());
        vl.getBtncancelar().addActionListener(l -> vl.getDlgcrudlabor().dispose());
        vl.getLblidlab().setText(Integer.toString(CreaID()));
        vl.getBtnimprimirlab().addActionListener(l -> Reportes());

        vl.getTxtbuscarlab().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BuscaLabor();
            }
        });

        addTextKeyListenerNotNumber(vl.getTxtnombrelab(), 50);

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

    private void CargaLabor() {
        List<Labor> listaLab = ml.ListaLaborBD();
        DefaultTableModel df;
        df = (DefaultTableModel) vl.getTbllabor().getModel();
        df.setNumRows(0);
        vl.getTbllabor().getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        vl.getTbllabor().getTableHeader().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        vl.getTbllabor().getTableHeader().setOpaque(false);
        vl.getTbllabor().getTableHeader().setBackground(new Color(32, 136, 203));
        vl.getTbllabor().getTableHeader().setForeground(new Color(255, 255, 255));
        vl.getTbllabor().setRowHeight(25);
        listaLab.stream().forEach(lab -> {
            String[] fila = {
                String.valueOf(lab.getId_lab()),
                lab.getNombre_lab(),
                String.valueOf(lab.getHoraslaborales_lab()),
                String.valueOf(lab.getSueldo_lab())
            };
            df.addRow(fila);
        });
    }

    private void BuscaLabor() {
        String search = vl.getTxtbuscarlab().getText().trim();
        List<Labor> listaLab = ml.BuscaLaborBD(search);
        DefaultTableModel df = (DefaultTableModel) vl.getTbllabor().getModel();
        df.setNumRows(0);
        vl.getTbllabor().getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        vl.getTbllabor().getTableHeader().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        vl.getTbllabor().getTableHeader().setOpaque(false);
        vl.getTbllabor().getTableHeader().setBackground(new Color(32, 136, 203));
        vl.getTbllabor().getTableHeader().setForeground(new Color(255, 255, 255));
        vl.getTbllabor().setRowHeight(25);
        listaLab.stream().forEach(lab -> {

            String[] fila = {
                String.valueOf(lab.getId_lab()),
                lab.getNombre_lab(),
                Integer.toString(lab.getHoraslaborales_lab()),
                Double.toString(lab.getSueldo_lab())
            };
            df.addRow(fila);
        });
    }

    private boolean MousePress(JTable tb) {
        boolean press = false;
        try {
            if (tb.getSelectedRowCount() == 1) {
                press = true;
                vl.getLblidlab().setText(vl.getTbllabor().getValueAt(vl.getTbllabor().getSelectedRow(), 0).toString());
                vl.getTxtnombrelab().setText(vl.getTbllabor().getValueAt(vl.getTbllabor().getSelectedRow(), 1).toString());
                vl.getSldhoras().setValue(Integer.parseInt(vl.getTbllabor().getValueAt(vl.getTbllabor().getSelectedRow(), 2).toString()));
                vl.getTxtsueldo().setText(vl.getTbllabor().getValueAt(vl.getTbllabor().getSelectedRow(), 3).toString());
            } else {
                JOptionPane.showMessageDialog(vl, "Seleccione una fila primero");
            }
        } catch (NullPointerException e) {
            System.err.print(e);
        }
        return press;
    }

    private void AbreDialogo(int ce) {
        String title = null;
        boolean RowSelected = true;
        switch (ce) {
            case 1:
                title = "Añadir una nueva labor";
                vl.getDlgcrudlabor().setName("crear");
                CleanFields(GiveComponent());
                EnableFields(GiveComponent());
                vl.getLblidlab().setText(Integer.toString(CreaID()));

                break;
            case 2:
                title = "Editar labor";
                vl.getDlgcrudlabor().setName("editar");
                EnableFields(GiveComponent());
                RowSelected = MousePress(vl.getTbllabor());
                break;
            case 3:
                title = "Eliminar labor";
                vl.getDlgcrudlabor().setName("eliminar");
                DisableFields(GiveComponent());
                RowSelected = MousePress(vl.getTbllabor());
                break;
        }
        if (RowSelected) {
            vl.getDlgcrudlabor().setTitle(title);
            vl.getDlgcrudlabor().setSize(580, 280);
            vl.getDlgcrudlabor().setLocationRelativeTo(vl);
            vl.getDlgcrudlabor().setVisible(true);
        }
    }

    private int CreaID() {
        int id_lab = ml.ObtenerID();
        id_lab++;
        return id_lab;
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
            vl.getTxtnombrelab(),
            vl.getTxtsueldo(),
            vl.getSldhoras()
        };
        return com;
    }

    private void Reportes() {
        ConnectionPG con = new ConnectionPG();
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/Vista/Reportes/Labor.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con.getCon());
            JasperViewer jv = new JasperViewer(jp, false);

            jv.setVisible(true);

        } catch (JRException | NumberFormatException ex) {
            Logger.getLogger(ControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void CrearEditarEliminarLabor() {
        String name = vl.getDlgcrudlabor().getName();
        switch (name) {
            case "crear":
                try {
                int id_lab = Integer.parseInt(vl.getLblidlab().getText());
                String nombre = vl.getTxtnombrelab().getText().toUpperCase().trim();
                int horas_laborales = vl.getSldhoras().getValue();
                String sueldo = vl.getTxtsueldo().getText();

                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(vl, "El campo de nombre no puede estar vacío");
                    return;
                }

                if (String.valueOf(sueldo).isEmpty()) {
                    JOptionPane.showMessageDialog(vl, "El campo de sueldo no puede estar vacío");
                    return;
                }
                if (ml.ExisteNombreLaborBD(nombre)) {
                    JOptionPane.showMessageDialog(vl, "El nombre que intenta registrar ya existe");
                    return;
                }

                double sal = Double.parseDouble(sueldo);
                if (sal < 100 || sal > 5000) {
                    JOptionPane.showMessageDialog(vl, "Ingrese un salario razonable\n"
                            + "Rango de: 100 a 5000 $");
                    return;
                }
                ModeloLabor labor = new ModeloLabor();
                labor.setId_lab(id_lab);
                labor.setNombre_lab(nombre);
                labor.setHoraslaborales_lab(horas_laborales);
                labor.setSueldo_lab(sal);
                if (labor.InsertarLaborBD() == null) {
                    JOptionPane.showMessageDialog(vl, "Registro de labor añadido correctamente");
                    vl.getDlgcrudlabor().dispose();
                } else {
                    JOptionPane.showMessageDialog(vl, "No se pudo añadir el registro");
                }
            } catch (NullPointerException | NumberFormatException e) {
                System.err.println(e);
            }
            break;

            case "editar":
                try {
                int id_lab = Integer.parseInt(vl.getLblidlab().getText());
                String nombre = vl.getTxtnombrelab().getText().toUpperCase().trim();
                int horas_laborales = vl.getSldhoras().getValue();
                String sueldo = vl.getTxtsueldo().getText();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(vl, "El campo de nombre no puede estar vacío.");
                    return;
                }
                if (String.valueOf(sueldo).isEmpty()) {
                    JOptionPane.showMessageDialog(vl, "El campo de sueldo no puede estar vacío.");
                    return;
                }
                double sal = Double.parseDouble(sueldo);
                if (sal < 100 || sal > 5000) {
                    JOptionPane.showMessageDialog(vl, "Ingrese un salario razonable\n"
                            + "Rango de: 100 a 5000 $");
                    return;
                }

                ModeloLabor labor = new ModeloLabor();
                labor.setId_lab(id_lab);
                labor.setNombre_lab(nombre);
                labor.setHoraslaborales_lab(horas_laborales);
                labor.setSueldo_lab(Double.parseDouble(sueldo));

                if (ml.GetNombreBD(id_lab).equals(nombre)) {
                    if (labor.ModificaLaborBD(id_lab) == null) {
                        JOptionPane.showMessageDialog(vl, "Registro de labor editado correctamente");
                        vl.getDlgcrudlabor().dispose();
                    } else {
                        JOptionPane.showMessageDialog(vl, "No se pudo editar el registro");
                    }
                } else if (!ml.ExisteNombreLaborBD(nombre)) {
                    if (labor.ModificaLaborBD(id_lab) == null) {
                        JOptionPane.showMessageDialog(vl, "Registro de labor editado correctamente");
                        vl.getDlgcrudlabor().dispose();
                    } else {
                        JOptionPane.showMessageDialog(vl, "No se pudo editar el registro");
                    }
                } else {
                    JOptionPane.showMessageDialog(vl, "No puede ingresar un nombre que ya existe");
                }

            } catch (NumberFormatException | NullPointerException e) {
                System.out.println(e);
            }
            break;

            case "eliminar":
                
                try {
                int id_lab = Integer.parseInt(vl.getLblidlab().getText());
                ModeloLabor labor = new ModeloLabor();
                if (!labor.LaborAsociadaBD(id_lab)) {
                    if (labor.EliminarLaborBD(id_lab) == null) {
                        JOptionPane.showMessageDialog(vl, "Registro de labor eliminado correctamente");
                        vl.getDlgcrudlabor().dispose();
                    } else {
                        JOptionPane.showMessageDialog(vl, "No se pudo eliminar el registro");
                    }
                } else {
                    JOptionPane.showMessageDialog(vl, "La actividad que intenta eliminar está asignada a uno o varios empleados");
                }
            } catch (NullPointerException | NumberFormatException e) {
                System.err.println(e);
            }
            break;
        }
        CargaLabor();
    }

}
