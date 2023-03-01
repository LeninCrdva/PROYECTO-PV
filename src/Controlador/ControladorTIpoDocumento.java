package Controlador;

import Modelo.ModeloTipoDocumento;
import Modelo.TipoDocumento;
import Vista.VistaTipoDocumento;
import java.awt.event.KeyAdapter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControladorTIpoDocumento {

    private final VistaTipoDocumento vtd;
    private final ModeloTipoDocumento mtd;

    public ControladorTIpoDocumento(VistaTipoDocumento vtd, ModeloTipoDocumento mtd) {
        this.vtd = vtd;
        this.mtd = mtd;
    }

    public void IniciarControl() {
        
        CargaDocumento();
        vtd.getBtncreardoc().addActionListener(l -> AbreDialogo(1));
        vtd.getBtneditardoc().addActionListener(l -> AbreDialogo(2));
        vtd.getBtneliminardoc().addActionListener(l -> AbreDialogo(3));
        vtd.getTxtbuscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                BuscaDocumento();
            }
        });
    }

    private void CargaDocumento() {
        List<TipoDocumento> listaLab = mtd.ListarTipoDocumentos();
        DefaultTableModel df;
        df = (DefaultTableModel) vtd.getTbldocument().getModel();
        df.setNumRows(0);
        listaLab.stream().forEach(doc -> {
            String[] fila = {
                String.valueOf(doc.getId_tip()),
                doc.getNombre_doc(),    
            };
            df.addRow(fila);
        });
    }

    private void BuscaDocumento() {
        String search = vtd.getTxtbuscar().getText().trim();
        List<TipoDocumento> listaLab = mtd.BuscaTipoDocDB(search);
        DefaultTableModel df = (DefaultTableModel) vtd.getTbldocument().getModel();
        df.setNumRows(0);
        listaLab.stream().forEach(doc -> {

            String[] fila = {
                String.valueOf(doc.getId_tip()),
                doc.getNombre_doc()
            };
            df.addRow(fila);
        });
    }

    private boolean MousePressed(JTable tb) {
        boolean press = false;
        try {
            if (tb.getSelectedRowCount() == 1) {
                press = true;
                vtd.getLblnumdoc().setText(vtd.getTbldocument().getValueAt(vtd.getTbldocument().getSelectedRow(), 0).toString());
                vtd.getTxtdocname().setText(vtd.getTbldocument().getValueAt(vtd.getTbldocument().getSelectedRow(), 1).toString());   
            } else {
                JOptionPane.showMessageDialog(vtd, "Seleccione una fila primero");
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
                title = "Añadir un tipo de documento";
                vtd.getDlgtipodoc().setName("crear");
                break;
            case 2:
                title = "Editar tipo de documento";
                vtd.getDlgtipodoc().setName("editar");
                RowSelected = MousePressed(vtd.getTbldocument());
                break;
            case 3:
                title = "Eliminar tipo de documento";
                vtd.getDlgtipodoc().setName("eliminar");
                RowSelected = MousePressed(vtd.getTbldocument());
                break;
        }
        if (RowSelected) {
            vtd.getDlgtipodoc().setTitle(title);
            vtd.getDlgtipodoc().setSize(290, 180);
            vtd.getDlgtipodoc().setLocationRelativeTo(vtd);
            vtd.getDlgtipodoc().setVisible(true);
        }
    }

    private void CrearEditarEliminarTipoDoc() {
        String name = vtd.getDlgtipodoc().getName();
        switch (name) {
            case "crear":
                try {
                int id_lab = Integer.parseInt(vtd.getLblnumdoc().getText());
                String nombre = vtd.getTxtdocname().getText().trim();

                ModeloTipoDocumento documento = new ModeloTipoDocumento();
                documento.setId_tip(id_lab);
                documento.setNombre_doc(nombre);
                
                if (documento.InsertaTipoDocBD()== null) {
                    JOptionPane.showMessageDialog(vtd, "Registro de documento añadido correctamente");
                } else {
                    JOptionPane.showMessageDialog(vtd, "No se pudo añadir el registro");
                }
            } catch (NullPointerException | NumberFormatException e) {
                System.err.println(e);
            }
            break;

            case "editar":
                try {
                int id_lab = Integer.parseInt(vtd.getLblnumdoc().getText());
                String nombre = vtd.getTxtdocname().getText().trim();
                
                ModeloTipoDocumento documento = new ModeloTipoDocumento();
                documento.setId_tip(id_lab);
                documento.setNombre_doc(nombre);
                
                if (documento.ModificarTipoDocumentoDB(id_lab) == null) {
                    JOptionPane.showMessageDialog(vtd, "Registro de documento editado correctamente");
                } else {
                    JOptionPane.showMessageDialog(vtd, "No se pudo editar el registro");
                }
            } catch (NullPointerException | NumberFormatException e) {
                System.err.println(e);
            }
            break;
            case "eliminar":
                try {
                int id_lab = Integer.parseInt(vtd.getLblnumdoc().getText());
                ModeloTipoDocumento documento = new ModeloTipoDocumento();
                if (documento.EliminarTipoDocumentoDB(id_lab) == null) {
                    JOptionPane.showMessageDialog(vtd, "Registro de documento eliminado correctamente");
                } else {
                    JOptionPane.showMessageDialog(vtd, "No se pudo eliminar el registro");
                }
            } catch (NullPointerException | NumberFormatException e) {
                System.err.println(e);
            }
            break;
        }
        CargaDocumento();
    }
}
