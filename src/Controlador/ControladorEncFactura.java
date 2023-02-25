/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.VistaFactura;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andrea
 */
public class ControladorEncFactura {
    private ModeloEncabezadoFactura modelo;
    private ModeloDetalleFactura modelo2;
    private VistaFactura vista;

    public ControladorEncFactura(ModeloEncabezadoFactura modelo, VistaFactura vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
    }
    public void iniciarControl(){
        cargaEnc();
        vista.getBtnCrear().addActionListener(l->ingresarFacturaDialog(0));
        vista.getBtnCrear().addActionListener(l->ingresarFacturaDialog(1));
        vista.getBtnAceptar().addActionListener(l->ingresarModificarFactura());
        
    }
    private void cargaEnc(){
        List<EncabezadoFactura> lista=modelo.listaEncabezadoFactura();
        DefaultTableModel mTabla=(DefaultTableModel) vista.getTblFactura().getModel();
        mTabla.setNumRows(0);   
        String[] columnas={"ID","CLIENTE","EMPLEADO","FECHA","TOTAL","ESTADO"};
        mTabla.setColumnIdentifiers(columnas);
        lista.stream().forEach(enc->{
            Object[] registro={enc.getId_enc(),enc.getIdCliente_enc(),enc.getIdEmpleado_enc(),enc.getFecha_enc(),enc.getTotal_enc(),enc.isEstado_enc()};
            mTabla.addRow(registro);
        });
    }
    private void cargaDet(String id){
        List<DetalleFactura> lista=modelo2.buscarDetalleF(id);
        DefaultTableModel mTabla=(DefaultTableModel) vista.getTblFactura().getModel();
        mTabla.setNumRows(0);   
        String[] columnas={"ID","ENCABEZADO","SERVICIO","RESERVA","OBSERVACIONES","COSTO"};
        mTabla.setColumnIdentifiers(columnas);
        lista.stream().forEach(det->{
            Object[] registro={det.getId_det(),det.getIdEncabezado_det(),det.getIdServicio_det(),det.getIdReserva_det(),det.getObservaciones_det(),det.getCosto_det()};
            mTabla.addRow(registro);
        });
    }
    private void ingresarFacturaDialog(int ce) {
        String titulo;
        try{
            if (ce==0) {
                titulo="Crear Factura";
                vista.getDlgCrudFactura().setTitle(titulo);
            }else{
                titulo="Modificar Factura";
                vista.getDlgCrudFactura().setTitle(titulo);
                if (vista.getTblFactura().getSelectedRow()<0) {
                    JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR EL ENCABEZADO A MODIFICAR");
                    return;
                }
                if (modelo.buscaEncabezadoF(vista.getTblFactura().getValueAt(vista.getTblFactura().getSelectedRow(), 0).toString())==false) {
                    JOptionPane.showMessageDialog(null, "NO SE ENCONTRO DICHO ENCABEZADO");
                    return;
                }
                cargaDet(vista.getTblFactura().getValueAt(vista.getTblFactura().getSelectedRow(), 0).toString());
                ConnectionPG con=new ConnectionPG();
                String sql="SELECT id_enc,idcliente_enc,idempl_enc,fecha_enc,total_enc,estado_enc FROM enc_factura WHERE id_enc="+vista.getTblFactura().getValueAt(vista.getTblFactura().getSelectedRow(), 0).toString()+"";
                ResultSet re=con.Consulta(sql);re.next();
                vista.getTxtCodigoE().setText(String.valueOf(re.getInt("id_enc")));
                vista.getTxtCliente().setText(String.valueOf(re.getInt("idcliente_enc")));
                vista.getTxtEmpleado().setText(String.valueOf(re.getString("idempl_enc")));
                vista.getTxtTotal().setText(String.valueOf(re.getDouble("idempl_enc")));
                int a=re.getDate("fecha_enc").getYear()-1900;
                int m=re.getDate("fecha_enc").getMonth();
                int d=re.getDate("fecha_enc").getDay();
                vista.getDcFecha().setDate(new Date(a,m,d));
                vista.getTxtCodigoE().setEnabled(false);
                vista.getTxtCliente().setEnabled(false);
                vista.getTxtEmpleado().setEnabled(false);
                vista.getTxtTotal().setEnabled(false);
            }
            
            vista.getDlgCrudFactura().setSize(600,600);
            vista.getDlgCrudFactura().setLocationRelativeTo(vista);
            vista.getDlgCrudFactura().setVisible(true);
        }catch (SQLException ex) {
            Logger.getLogger(ControladorEncFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void ingresarModificarFactura(){
        if (vista.getDlgCrudFactura().getTitle().equals("Crear Factura")) {
            if (vista.getTxtCodigoE().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE INGRESAR UN CODIGO");
                return;
            }
            if (!vista.getTxtCodigoE().getText().matches("[0-9]{1,20}")) {
                JOptionPane.showMessageDialog(null, "DEBE DE INGRESAR UN CODIGO BIEN");
                return;
            }
            if (vista.getTxtCliente().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE ELEGIR UN CLIENTE");
                return;
            }
            
            if (vista.getTxtEmpleado().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE ELEGIR UN EMPLEADO");
                return;
            }
            
            int d,m,a;
            Calendar cal=vista.getDcFecha().getCalendar();
            d=cal.get(Calendar.DAY_OF_MONTH);
            m=cal.get(Calendar.MONTH);
            a=cal.get(Calendar.YEAR);
            new ModeloEncabezadoFactura(Integer.parseInt(vista.getTxtCodigoE().getText()),Integer.parseInt(vista.getTxtCliente().getText()),Integer.parseInt(vista.getTxtEmpleado().getText()),new Date(a,m,d),Double.parseDouble(vista.getTxtTotal().getText()),true).grabarEncabezadoF();
            cargaEnc();
        }else{
            if (vista.getTxtCodigoE().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE INGRESAR UN CODIGO");
                return;
            }
            if (!vista.getTxtCodigoE().getText().matches("[0-9]{1,20}")) {
                JOptionPane.showMessageDialog(null, "DEBE DE INGRESAR UN CODIGO BIEN");
                return;
            }
            if (vista.getTxtCliente().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE ELEGIR UN CLIENTE");
                return;
            }
            
            if (vista.getTxtEmpleado().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE DE ELEGIR UN EMPLEADO");
                return;
            }
            
            int d,m,a;
            Calendar cal=vista.getDcFecha().getCalendar();
            d=cal.get(Calendar.DAY_OF_MONTH);
            m=cal.get(Calendar.MONTH);
            a=cal.get(Calendar.YEAR);
            new ModeloEncabezadoFactura(Integer.parseInt(vista.getTxtCodigoE().getText()),Integer.parseInt(vista.getTxtCliente().getText()),Integer.parseInt(vista.getTxtEmpleado().getText()),new Date(a,m,d),Double.parseDouble(vista.getTxtTotal().getText()),true).modificarEncabezadoF();
            cargaEnc();
        }
        limpiar();
    }
    private void limpiar(){
        vista.getTxtCodigoE().setText(null);
        vista.getTxtCliente().setText(null);
        vista.getTxtEmpleado().setText(null);
        vista.getDcFecha().setDate(null);
        vista.getTxtTotal().setText(null);
        vista.getTxtCodigoE().setEnabled(true);
        vista.getTxtCliente().setEnabled(true);
        vista.getTxtEmpleado().setEnabled(true);
    }
    
}
