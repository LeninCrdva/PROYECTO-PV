/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import com.toedter.calendar.JDateChooser;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author santi
 */
public class VistaEmpleados extends javax.swing.JInternalFrame {

    /**
     * Creates new form VistaPersona
     */
    public VistaEmpleados() {
        initComponents();
    }

    public JButton getBtnaceptar() {
        return btnaceptar;
    }

    public void setBtnaceptar(JButton btnaceptar) {
        this.btnaceptar = btnaceptar;
    }

    public JButton getBtncancelar() {
        return btncancelar;
    }

    public void setBtncancelar(JButton btncancelar) {
        this.btncancelar = btncancelar;
    }

    public JButton getBtncrear() {
        return btncrear;
    }

    public void setBtncrear(JButton btncrear) {
        this.btncrear = btncrear;
    }

    public JButton getBtneditar() {
        return btneditar;
    }

    public void setBtneditar(JButton btneditar) {
        this.btneditar = btneditar;
    }

    public JButton getBtneliminar() {
        return btneliminar;
    }

    public void setBtneliminar(JButton btneliminar) {
        this.btneliminar = btneliminar;
    }

    public JButton getBtnprint() {
        return btnprint;
    }

    public void setBtnprint(JButton btnprint) {
        this.btnprint = btnprint;
    }

    public JTable getTblempleados() {
        return tblempleados;
    }

    public void setTblempleados(JTable tblempleados) {
        this.tblempleados = tblempleados;
    }

    public JTextField getTxtbuscar() {
        return txtbuscar;
    }

    public void setTxtbuscar(JTextField txtbuscar) {
        this.txtbuscar = txtbuscar;
    }

    public ButtonGroup getBtngpgenero() {
        return btngpgenero;
    }

    public void setBtngpgenero(ButtonGroup btngpgenero) {
        this.btngpgenero = btngpgenero;
    }

    public JComboBox getCbtipodoc() {
        return cbtipodoc;
    }

    public void setCbtipodoc(JComboBox cbtipodoc) {
        this.cbtipodoc = cbtipodoc;
    }

    public JCheckBox getChkdoctipo() {
        return chkdoctipo;
    }

    public void setChkdoctipo(JCheckBox chkdoctipo) {
        this.chkdoctipo = chkdoctipo;
    }

    public JDialog getDlgcrudempleado() {
        return dlgcrudempleado;
    }

    public void setDlgcrudempleado(JDialog dlgcrudempleado) {
        this.dlgcrudempleado = dlgcrudempleado;
    }

    public JDateChooser getDtefechanac() {
        return dtefechanac;
    }

    public void setDtefechanac(JDateChooser dtefechanac) {
        this.dtefechanac = dtefechanac;
    }

    public JLabel getLblidper() {
        return lblidper;
    }

    public void setLblidper(JLabel lblidper) {
        this.lblidper = lblidper;
    }

    public JRadioButton getRdfemenino() {
        return rdfemenino;
    }

    public void setRdfemenino(JRadioButton rdfemenino) {
        this.rdfemenino = rdfemenino;
    }

    public JRadioButton getRdmasculino() {
        return rdmasculino;
    }

    public void setRdmasculino(JRadioButton rdmasculino) {
        this.rdmasculino = rdmasculino;
    }

    public JTextField getTxtapellidoper() {
        return txtapellidoper;
    }

    public void setTxtapellidoper(JTextField txtapellidoper) {
        this.txtapellidoper = txtapellidoper;
    }

    public JTextField getTxtdireccion() {
        return txtdireccion;
    }

    public void setTxtdireccion(JTextField txtdireccion) {
        this.txtdireccion = txtdireccion;
    }

    public JTextField getTxtemail() {
        return txtemail;
    }

    public void setTxtemail(JTextField txtemail) {
        this.txtemail = txtemail;
    }

    public JTextField getTxtnombreper() {
        return txtnombreper;
    }

    public void setTxtnombreper(JTextField txtnombreper) {
        this.txtnombreper = txtnombreper;
    }

    public JTextField getTxttelefono() {
        return txttelefono;
    }

    public void setTxttelefono(JTextField txttelefono) {
        this.txttelefono = txttelefono;
    }

    public JFormattedTextField getTxtdocnum() {
        return txtdocnum;
    }

    public void setTxtdocnum(JFormattedTextField txtdocnum) {
        this.txtdocnum = txtdocnum;
    }

    public JComboBox getCblabor() {
        return cblabor;
    }

    public void setCblabor(JComboBox cblabor) {
        this.cblabor = cblabor;
    }

    public JButton getBtnaddlab() {
        return btnaddlab;
    }

    public void setBtnaddlab(JButton btnaddlab) {
        this.btnaddlab = btnaddlab;
    }

    public JButton getBtnaddtipodoc() {
        return btnaddtipodoc;
    }

    public void setBtnaddtipodoc(JButton btnaddtipodoc) {
        this.btnaddtipodoc = btnaddtipodoc;
    }

    public JDateChooser getDtefechaingreso() {
        return dtefechaingreso;
    }

    public void setDtefechaingreso(JDateChooser dtefechaingreso) {
        this.dtefechaingreso = dtefechaingreso;
    }

    public JDialog getDlgreporteemp() {
        return dlgreporteemp;
    }

    public void setDlgreporteemp(JDialog dlgreporteemp) {
        this.dlgreporteemp = dlgreporteemp;
    }

    public JTextArea getTxtdescripción() {
        return txtdescripción;
    }

    public void setTxtdescripción(JTextArea txtdescripción) {
        this.txtdescripción = txtdescripción;
    }

    public JFormattedTextField getTxtsueldo() {
        return txtsueldo;
    }

    public void setTxtsueldo(JFormattedTextField txtsueldo) {
        this.txtsueldo = txtsueldo;
    }

    public JTextField getTxttitle() {
        return txttitle;
    }

    public void setTxttitle(JTextField txttitle) {
        this.txttitle = txttitle;
    }

    public JButton getBtnacceptreport() {
        return btnacceptreport;
    }

    public void setBtnacceptreport(JButton btnacceptreport) {
        this.btnacceptreport = btnacceptreport;
    }

    public JButton getBtncancelreport() {
        return btncancelreport;
    }

    public void setBtncancelreport(JButton btncancelreport) {
        this.btncancelreport = btncancelreport;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlgcrudempleado = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblidper = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtnombreper = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtapellidoper = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        rdmasculino = new javax.swing.JRadioButton();
        rdfemenino = new javax.swing.JRadioButton();
        dtefechanac = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        btnaceptar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        cbtipodoc = new javax.swing.JComboBox();
        cblabor = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        txtdocnum = new javax.swing.JFormattedTextField();
        chkdoctipo = new javax.swing.JCheckBox();
        btnaddtipodoc = new javax.swing.JButton();
        btnaddlab = new javax.swing.JButton();
        dtefechaingreso = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        btngpgenero = new javax.swing.ButtonGroup();
        dlgreporteemp = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtsueldo = new javax.swing.JFormattedTextField();
        txttitle = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtdescripción = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        btnacceptreport = new javax.swing.JButton();
        btncancelreport = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        btncrear = new javax.swing.JButton();
        btneditar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnprint = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblempleados = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();

        dlgcrudempleado.setResizable(false);

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("N°:");

        jLabel4.setText("Nombre:");

        jLabel5.setText("Apellido:");

        jLabel6.setText("N° Documento:");

        jLabel7.setText("Tipo documento:");

        jLabel8.setText("Dirección:");

        jLabel9.setText("Teléfono:");

        jLabel10.setText("E-mail:");

        jLabel12.setText("Género:");

        btngpgenero.add(rdmasculino);
        rdmasculino.setText("Masculino");

        btngpgenero.add(rdfemenino);
        rdfemenino.setText("Femenino");

        jLabel13.setText("Fecha nacimiento:");

        btnaceptar.setText("Aceptar");

        btncancelar.setText("Cancelar");

        jLabel14.setText("Labor:");

        try {
            txtdocnum.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        chkdoctipo.setText("Autmático");

        btnaddtipodoc.setText("+");

        btnaddlab.setText("+");

        jLabel1.setText("Fecha ingreso:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnaceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btncancelar))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtefechaingreso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dtefechanac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtnombreper)
                            .addComponent(txtapellidoper)
                            .addComponent(lblidper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtdireccion)
                            .addComponent(txttelefono)
                            .addComponent(txtemail)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(rdmasculino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(rdfemenino))
                            .addComponent(txtdocnum)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(cbtipodoc, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnaddtipodoc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chkdoctipo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(cblabor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnaddlab)))))
                .addGap(20, 20, 20))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblidper, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtnombreper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtapellidoper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dtefechanac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(13, 13, 13)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbtipodoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkdoctipo)
                    .addComponent(btnaddtipodoc))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtdocnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdmasculino)
                    .addComponent(rdfemenino)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dtefechaingreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cblabor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnaddlab))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnaceptar)
                    .addComponent(btncancelar))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout dlgcrudempleadoLayout = new javax.swing.GroupLayout(dlgcrudempleado.getContentPane());
        dlgcrudempleado.getContentPane().setLayout(dlgcrudempleadoLayout);
        dlgcrudempleadoLayout.setHorizontalGroup(
            dlgcrudempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgcrudempleadoLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        dlgcrudempleadoLayout.setVerticalGroup(
            dlgcrudempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgcrudempleadoLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("REPORTE PERSONALIZADO");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Sueldo:");

        txtsueldo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtsueldo.setToolTipText("Ingrese la base a tomar en cuenta");

        txttitle.setToolTipText("El título del documento");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Título:");

        txtdescripción.setColumns(20);
        txtdescripción.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtdescripción.setRows(5);
        jScrollPane2.setViewportView(txtdescripción);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Descripción:");

        btnacceptreport.setText("Aceptar");

        btncancelreport.setText("Cancelar");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnacceptreport)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btncancelreport))
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(txttitle, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(jLabel17)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(txtsueldo, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                        .addGap(21, 21, 21))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnacceptreport)
                    .addComponent(btncancelreport))
                .addContainerGap())
        );

        javax.swing.GroupLayout dlgreporteempLayout = new javax.swing.GroupLayout(dlgreporteemp.getContentPane());
        dlgreporteemp.getContentPane().setLayout(dlgreporteempLayout);
        dlgreporteempLayout.setHorizontalGroup(
            dlgreporteempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgreporteempLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        dlgreporteempLayout.setVerticalGroup(
            dlgreporteempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setClosable(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setText("Buscar:");

        btncrear.setText("Crear");

        btneditar.setText("Editar");

        btneliminar.setText("Eliminar");

        btnprint.setText("Imprimir");

        tblempleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Cédula", "Nombre", "Apellido", "Dirección", "Teléfono", "E-mail", "Género", "Fecha Nac.", "Puesto", "Fecha Ingreso"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblempleados.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblempleados);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
                        .addComponent(btncrear, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnprint)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnprint)
                    .addComponent(btncrear)
                    .addComponent(btneditar)
                    .addComponent(btneliminar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnacceptreport;
    private javax.swing.JButton btnaceptar;
    private javax.swing.JButton btnaddlab;
    private javax.swing.JButton btnaddtipodoc;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btncancelreport;
    private javax.swing.JButton btncrear;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.ButtonGroup btngpgenero;
    private javax.swing.JButton btnprint;
    private javax.swing.JComboBox cblabor;
    private javax.swing.JComboBox cbtipodoc;
    private javax.swing.JCheckBox chkdoctipo;
    private javax.swing.JDialog dlgcrudempleado;
    private javax.swing.JDialog dlgreporteemp;
    private com.toedter.calendar.JDateChooser dtefechaingreso;
    private com.toedter.calendar.JDateChooser dtefechanac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblidper;
    private javax.swing.JRadioButton rdfemenino;
    private javax.swing.JRadioButton rdmasculino;
    private javax.swing.JTable tblempleados;
    private javax.swing.JTextField txtapellidoper;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextArea txtdescripción;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JFormattedTextField txtdocnum;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtnombreper;
    private javax.swing.JFormattedTextField txtsueldo;
    private javax.swing.JTextField txttelefono;
    private javax.swing.JTextField txttitle;
    // End of variables declaration//GEN-END:variables
}
