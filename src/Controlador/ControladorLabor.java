package Controlador;

import Vista.VistaLabor;
import Modelo.ModeloLabor;
import Modelo.Labor;
import java.awt.event.KeyAdapter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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

        vl.getTxtbuscarlab().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BuscaLabor();
            }
        });
    }

    private void CargaLabor() {
        List<Labor> listaLab = ml.ListaLaborBD();
        DefaultTableModel df;
        df = (DefaultTableModel) vl.getTbllabor().getModel();
        df.setNumRows(0);
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
                break;
            case 2:
                title = "Editar labor";
                vl.getDlgcrudlabor().setName("editar");
                RowSelected = MousePress(vl.getTbllabor());
                break;
            case 3:
                title = "Eliminar labor";
                vl.getDlgcrudlabor().setName("eliminar");
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

    private void CrearEditarEliminarLabor() {
        String name = vl.getDlgcrudlabor().getName();
        switch (name) {
            case "crear":
                try {
                vl.getLblidlab().setText(Integer.toString(CreaID()));
                int id_lab = Integer.parseInt(vl.getLblidlab().getText());
                String nombre = vl.getTxtnombrelab().getText().trim();
                int horas_laborales = vl.getSldhoras().getValue();
                double sueldo = Double.parseDouble(vl.getTxtsueldo().getText());
                ModeloLabor labor = new ModeloLabor();
                labor.setId_lab(id_lab);
                labor.setNombre_lab(nombre);
                labor.setHoraslaborales_lab(horas_laborales);
                labor.setSueldo_lab(sueldo);
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
                String nombre = vl.getTxtnombrelab().getText().trim();
                int horas_laborales = vl.getSldhoras().getValue();
                double sueldo = Double.parseDouble(vl.getTxtsueldo().getText());
                ModeloLabor labor = new ModeloLabor();
                labor.setId_lab(id_lab);
                labor.setNombre_lab(nombre);
                labor.setHoraslaborales_lab(horas_laborales);
                labor.setSueldo_lab(sueldo);
                if (labor.ModificaLaborBD(id_lab) == null) {
                    JOptionPane.showMessageDialog(vl, "Registro de labor editado correctamente");
                    vl.getDlgcrudlabor().dispose();
                } else {
                    JOptionPane.showMessageDialog(vl, "No se pudo editar el registro");
                }
            } catch (NullPointerException | NumberFormatException e) {
                System.err.println(e);
            }
            break;
            case "eliminar":
                try {
                int id_lab = Integer.parseInt(vl.getLblidlab().getText());
                ModeloLabor labor = new ModeloLabor();
                if (labor.EliminarLaborBD(id_lab) == null) {
                    JOptionPane.showMessageDialog(vl, "Registro de labor eliminado correctamente");
                    vl.getDlgcrudlabor().dispose();
                } else {
                    JOptionPane.showMessageDialog(vl, "No se pudo eliminar el registro");
                }
            } catch (NullPointerException | NumberFormatException e) {
                System.err.println(e);
            }
            break;
        }
        CargaLabor();
    }

}
