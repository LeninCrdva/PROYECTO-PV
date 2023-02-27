/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */

package Vista;

import Modelo.Tipo_Habitacion;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Lenin
 */
public class VistaHabitacion extends javax.swing.JInternalFrame {

    /** Creates new form VistaHabitacion */
    public VistaHabitacion() {
        initComponents();
    }

    public JButton getBtnAceptarHab() {
        return btnAceptarHab;
    }

    public void setBtnAceptarHab(JButton btnAceptarHab) {
        this.btnAceptarHab = btnAceptarHab;
    }

    public JButton getBtnAceptarTipo() {
        return btnAceptarTipo;
    }

    public void setBtnAceptarTipo(JButton btnAceptarTipo) {
        this.btnAceptarTipo = btnAceptarTipo;
    }

    public JButton getBtnCancelarHab() {
        return btnCancelarHab;
    }

    public void setBtnCancelarHab(JButton btnCancelarHab) {
        this.btnCancelarHab = btnCancelarHab;
    }

    public JButton getBtnCancelarTipo() {
        return btnCancelarTipo;
    }

    public void setBtnCancelarTipo(JButton btnCancelarTipo) {
        this.btnCancelarTipo = btnCancelarTipo;
    }

    public JButton getBtnCrear() {
        return btnCrear;
    }

    public void setBtnCrear(JButton btnCrear) {
        this.btnCrear = btnCrear;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public void setBtnEditar(JButton btnEditar) {
        this.btnEditar = btnEditar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JButton getBtnInfo() {
        return btnInfo;
    }

    public void setBtnInfo(JButton btnInfo) {
        this.btnInfo = btnInfo;
    }

    public JButton getBtnPrint() {
        return btnPrint;
    }

    public void setBtnPrint(JButton btnPrint) {
        this.btnPrint = btnPrint;
    }

    public JButton getBtnReserva() {
        return btnReserva;
    }

    public void setBtnReserva(JButton btnReserva) {
        this.btnReserva = btnReserva;
    }

    public JCheckBox getCheckDisponible() {
        return checkDisponible;
    }

    public void setCheckDisponible(JCheckBox checkDisponible) {
        this.checkDisponible = checkDisponible;
    }

    public JComboBox<String> getComboTipo() {
        return comboTipo;
    }

    public void setComboTipo(JComboBox<String> comboTipo) {
        this.comboTipo = comboTipo;
    }

    public JDialog getDialogHabi() {
        return dialogHabi;
    }

    public void setDialogHabi(JDialog dialogHabi) {
        this.dialogHabi = dialogHabi;
    }

    public JDialog getDialogTipo() {
        return dialogTipo;
    }

    public void setDialogTipo(JDialog dialogTipo) {
        this.dialogTipo = dialogTipo;
    }

    public JLabel getLabelIDHabi() {
        return labelIDHabi;
    }

    public void setLabelIDHabi(JLabel labelIDHabi) {
        this.labelIDHabi = labelIDHabi;
    }

    public JLabel getLabelIDTipo() {
        return labelIDTipo;
    }

    public void setLabelIDTipo(JLabel labelIDTipo) {
        this.labelIDTipo = labelIDTipo;
    }

    public JRadioButton getRdHab() {
        return rdHab;
    }

    public void setRdHab(JRadioButton rdHab) {
        this.rdHab = rdHab;
    }

    public JRadioButton getRdTipHab() {
        return rdTipHab;
    }

    public void setRdTipHab(JRadioButton rdTipHab) {
        this.rdTipHab = rdTipHab;
    }

    public JTable getTableHabitacion() {
        return tableHabitacion;
    }

    public void setTableHabitacion(JTable tableHabitacion) {
        this.tableHabitacion = tableHabitacion;
    }

    public JTable getTableTipoHabitacion() {
        return tableTipoHabitacion;
    }

    public void setTableTipoHabitacion(JTable tableTipoHabitacion) {
        this.tableTipoHabitacion = tableTipoHabitacion;
    }

    public JTextField getTxtBuscarHab() {
        return txtBuscarHab;
    }

    public void setTxtBuscarHab(JTextField txtBuscarHab) {
        this.txtBuscarHab = txtBuscarHab;
    }

    public JTextField getTxtBuscarTip() {
        return txtBuscarTip;
    }

    public void setTxtBuscarTip(JTextField txtBuscarTip) {
        this.txtBuscarTip = txtBuscarTip;
    }

    public JFormattedTextField getTxtCapacidad() {
        return txtCapacidad;
    }

    public void setTxtCapacidad(JFormattedTextField txtCapacidad) {
        this.txtCapacidad = txtCapacidad;
    }

    public JTextField getTxtNombreTipo() {
        return txtNombreTipo;
    }

    public void setTxtNombreTipo(JTextField txtNombreTipo) {
        this.txtNombreTipo = txtNombreTipo;
    }

    public JFormattedTextField getTxtNumCama() {
        return txtNumCama;
    }

    public void setTxtNumCama(JFormattedTextField txtNumCama) {
        this.txtNumCama = txtNumCama;
    }

    public JTextField getTxtNumHab() {
        return txtNumHab;
    }

    public void setTxtNumHab(JTextField txtNumHab) {
        this.txtNumHab = txtNumHab;
    }

    public JFormattedTextField getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(JFormattedTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public JComboBox<Tipo_Habitacion> getCombTipSelc() {
        return combTipSelc;
    }

    public void setCombTipSelc(JComboBox<Tipo_Habitacion> combTipSelc) {
        this.combTipSelc = combTipSelc;
    }

    public ButtonGroup getGroupHab() {
        return groupHab;
    }

    public void setGroupHab(ButtonGroup groupHab) {
        this.groupHab = groupHab;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupHab = new javax.swing.ButtonGroup();
        dialogHabi = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        labelIDHabi = new javax.swing.JLabel();
        combTipSelc = new javax.swing.JComboBox<>();
        txtNumHab = new javax.swing.JTextField();
        checkDisponible = new javax.swing.JCheckBox();
        btnAceptarHab = new javax.swing.JButton();
        btnCancelarHab = new javax.swing.JButton();
        dialogTipo = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNumCama = new javax.swing.JFormattedTextField();
        txtCapacidad = new javax.swing.JFormattedTextField();
        txtPrecio = new javax.swing.JFormattedTextField();
        btnAceptarTipo = new javax.swing.JButton();
        btnCancelarTipo = new javax.swing.JButton();
        txtNombreTipo = new javax.swing.JTextField();
        labelIDTipo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHabitacion = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTipoHabitacion = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnInfo = new javax.swing.JButton();
        rdHab = new javax.swing.JRadioButton();
        rdTipHab = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        btnCrear = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        txtBuscarHab = new javax.swing.JTextField();
        txtBuscarTip = new javax.swing.JTextField();
        comboTipo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        btnReserva = new javax.swing.JButton();

        jLabel5.setText("ID:");

        jLabel6.setText("Tipo habitación:");

        jLabel7.setText("Nº habitación:");

        checkDisponible.setText("Disponible");

        btnAceptarHab.setText("Aceptar");

        btnCancelarHab.setText("Cancelar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(labelIDHabi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(combTipSelc, 0, 100, Short.MAX_VALUE)
                        .addComponent(txtNumHab))
                    .addComponent(checkDisponible))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(btnAceptarHab)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(btnCancelarHab)
                .addGap(26, 26, 26))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(labelIDHabi, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combTipSelc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNumHab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(checkDisponible)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptarHab)
                    .addComponent(btnCancelarHab))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dialogHabiLayout = new javax.swing.GroupLayout(dialogHabi.getContentPane());
        dialogHabi.getContentPane().setLayout(dialogHabiLayout);
        dialogHabiLayout.setHorizontalGroup(
            dialogHabiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        dialogHabiLayout.setVerticalGroup(
            dialogHabiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel8.setText("ID:");

        jLabel9.setText("Nombre tipo:");

        jLabel10.setText("Nº camas:");

        jLabel11.setText("Capacidad máx:");

        jLabel12.setText("Precio:");

        txtNumCama.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        txtCapacidad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        txtPrecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.##"))));

        btnAceptarTipo.setText("Aceptar");

        btnCancelarTipo.setText("Cancelar");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel11)
                                .addComponent(jLabel10)
                                .addComponent(jLabel9)
                                .addComponent(jLabel8))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNumCama, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                .addComponent(txtCapacidad, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                .addComponent(txtPrecio)
                                .addComponent(txtNombreTipo)
                                .addComponent(labelIDTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(btnAceptarTipo)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                            .addComponent(btnCancelarTipo)))
                    .addGap(29, 29, 29)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 276, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(labelIDTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtNombreTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtNumCama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(34, 34, 34)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAceptarTipo)
                        .addComponent(btnCancelarTipo))
                    .addContainerGap(32, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout dialogTipoLayout = new javax.swing.GroupLayout(dialogTipo.getContentPane());
        dialogTipo.getContentPane().setLayout(dialogTipoLayout);
        dialogTipoLayout.setHorizontalGroup(
            dialogTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dialogTipoLayout.setVerticalGroup(
            dialogTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogTipoLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tableHabitacion = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            };
        };
        tableHabitacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Habitación", "Numero Habitación", "Tipo habitación", "Disponibilidad"
            }
        ));
        tableHabitacion.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableHabitacion.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableHabitacion);

        tableTipoHabitacion = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tableTipoHabitacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Tipo habitación", "Nº camas", "Capacidad", "Precio"
            }
        ));
        tableTipoHabitacion.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableTipoHabitacion.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tableTipoHabitacion);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("HABITACIÓN:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("T. HABITACIÓN:");

        btnInfo.setText("?");

        rdHab.setBackground(new java.awt.Color(255, 255, 255));
        groupHab.add(rdHab);
        rdHab.setText("Habitación");

        rdTipHab.setBackground(new java.awt.Color(255, 255, 255));
        groupHab.add(rdTipHab);
        rdTipHab.setText("Tipo de habitación");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("INFORMACIÓN DE:");

        btnCrear.setText("Crear");

        btnEditar.setText("Editar");

        btnEliminar.setText("Eliminar");

        btnPrint.setText("Imprimir");

        txtBuscarHab.setToolTipText("Se buscará por número de habitación");

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Nº camas", "Capacidad", "Precio" }));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Seleccione uno para utilizar los siguientes botones:");

        btnReserva.setBackground(new java.awt.Color(255, 255, 255));
        btnReserva.setText("Realizar Reserva");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBuscarHab, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBuscarTip, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboTipo, 0, 1, Short.MAX_VALUE)))
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(rdHab)
                                    .addGap(38, 38, 38))
                                .addComponent(rdTipHab))
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(btnCrear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrint)
                        .addGap(31, 31, 31)
                        .addComponent(btnInfo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(278, 278, 278)
                .addComponent(btnReserva)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnInfo)
                            .addComponent(btnCrear)
                            .addComponent(btnEditar)
                            .addComponent(btnEliminar)
                            .addComponent(btnPrint))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(35, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdHab)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdTipHab)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtBuscarHab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarTip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(btnReserva)
                .addContainerGap())
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
    private javax.swing.JButton btnAceptarHab;
    private javax.swing.JButton btnAceptarTipo;
    private javax.swing.JButton btnCancelarHab;
    private javax.swing.JButton btnCancelarTipo;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInfo;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReserva;
    private javax.swing.JCheckBox checkDisponible;
    private javax.swing.JComboBox<Tipo_Habitacion> combTipSelc;
    private javax.swing.JComboBox<String> comboTipo;
    private javax.swing.JDialog dialogHabi;
    private javax.swing.JDialog dialogTipo;
    private javax.swing.ButtonGroup groupHab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelIDHabi;
    private javax.swing.JLabel labelIDTipo;
    private javax.swing.JRadioButton rdHab;
    private javax.swing.JRadioButton rdTipHab;
    private javax.swing.JTable tableHabitacion;
    private javax.swing.JTable tableTipoHabitacion;
    private javax.swing.JTextField txtBuscarHab;
    private javax.swing.JTextField txtBuscarTip;
    private javax.swing.JFormattedTextField txtCapacidad;
    private javax.swing.JTextField txtNombreTipo;
    private javax.swing.JFormattedTextField txtNumCama;
    private javax.swing.JTextField txtNumHab;
    private javax.swing.JFormattedTextField txtPrecio;
    // End of variables declaration//GEN-END:variables

}
