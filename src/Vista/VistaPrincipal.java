/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;

/**
 *
 * @author Lenin
 */
public class VistaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VistaPrincipal
     */
    public VistaPrincipal() {
        initComponents();
    }

    public JButton getBtnCliente() {
        return btnCliente;
    }

    public void setBtnCliente(JButton btnCliente) {
        this.btnCliente = btnCliente;
    }

    public JButton getBtnEmpleado() {
        return btnEmpleado;
    }

    public void setBtnEmpleado(JButton btnEmpleado) {
        this.btnEmpleado = btnEmpleado;
    }

    public JButton getBtnFactura() {
        return btnFactura;
    }

    public void setBtnFactura(JButton btnFactura) {
        this.btnFactura = btnFactura;
    }

    public JButton getBtnHabitacion() {
        return btnHabitacion;
    }

    public void setBtnHabitacion(JButton btnHabitacion) {
        this.btnHabitacion = btnHabitacion;
    }

    public JButton getBtnHome() {
        return btnHome;
    }

    public void setBtnHome(JButton btnHome) {
        this.btnHome = btnHome;
    }

    public JButton getBtnReserva() {
        return btnReserva;
    }

    public void setBtnReserva(JButton btnReserva) {
        this.btnReserva = btnReserva;
    }

    public JButton getBtnServicio() {
        return btnServicio;
    }

    public void setBtnServicio(JButton btnServicio) {
        this.btnServicio = btnServicio;
    }

    public JLabel getLblNameUser() {
        return lblNameUser;
    }

    public void setLblNameUser(JLabel lblNameUser) {
        this.lblNameUser = lblNameUser;
    }

    public JLabel getLblfechaDiaria() {
        return lblfechaDiaria;
    }

    public void setLblfechaDiaria(JLabel lblfechaDiaria) {
        this.lblfechaDiaria = lblfechaDiaria;
    }

    public JDesktopPane getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JDesktopPane panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JButton getBtnLabor() {
        return btnLabor;
    }

    public void setBtnLabor(JButton btnLabor) {
        this.btnLabor = btnLabor;
    }

    public JButton getBtnCuenta() {
        return btnCuenta;
    }

    public void setBtnCuenta(JButton btnCuenta) {
        this.btnCuenta = btnCuenta;
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btnHome = new javax.swing.JButton();
        btnEmpleado = new javax.swing.JButton();
        btnCliente = new javax.swing.JButton();
        btnHabitacion = new javax.swing.JButton();
        btnReserva = new javax.swing.JButton();
        btnFactura = new javax.swing.JButton();
        btnServicio = new javax.swing.JButton();
        btnLabor = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblfechaDiaria = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblNameUser = new javax.swing.JLabel();
        btnCuenta = new javax.swing.JButton();
        panelPrincipal = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1 = new javax.swing.JPanel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                int width = getWidth();
                int height = getHeight();

                Color color1 = new Color(255,196,0);
                Color color2 = new Color(198,11,29);

                GradientPaint gp = new GradientPaint(
                    0, 0, color1,
                    width , height / 3, color2);

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar1.setRollover(true);

        btnHome.setBackground(new java.awt.Color(255, 255, 255));
        btnHome.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/HomePV.png"))); // NOI18N
        btnHome.setText("Inicio");
        btnHome.setBorderPainted(false);
        btnHome.setFocusable(false);
        btnHome.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnHome.setIconTextGap(40);
        btnHome.setMargin(new java.awt.Insets(5, 5, 5, 45));
        btnHome.setMaximumSize(new java.awt.Dimension(185, 35));
        jToolBar1.add(btnHome);

        btnEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        btnEmpleado.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        btnEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/empleados.png"))); // NOI18N
        btnEmpleado.setText("Empleado");
        btnEmpleado.setBorderPainted(false);
        btnEmpleado.setFocusable(false);
        btnEmpleado.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEmpleado.setIconTextGap(40);
        btnEmpleado.setMargin(new java.awt.Insets(5, 5, 5, 20));
        btnEmpleado.setMaximumSize(new java.awt.Dimension(185, 35));
        jToolBar1.add(btnEmpleado);

        btnCliente.setBackground(new java.awt.Color(255, 255, 255));
        btnCliente.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        btnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/Clientes.png"))); // NOI18N
        btnCliente.setText("Cliente");
        btnCliente.setBorderPainted(false);
        btnCliente.setFocusable(false);
        btnCliente.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCliente.setIconTextGap(40);
        btnCliente.setMargin(new java.awt.Insets(5, 5, 5, 35));
        btnCliente.setMaximumSize(new java.awt.Dimension(185, 35));
        btnCliente.setMinimumSize(new java.awt.Dimension(150, 35));
        jToolBar1.add(btnCliente);

        btnHabitacion.setBackground(new java.awt.Color(255, 255, 255));
        btnHabitacion.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        btnHabitacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/Habitaciones.png"))); // NOI18N
        btnHabitacion.setText("Habitación");
        btnHabitacion.setBorderPainted(false);
        btnHabitacion.setFocusable(false);
        btnHabitacion.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnHabitacion.setIconTextGap(40);
        btnHabitacion.setMargin(new java.awt.Insets(5, 5, 5, 10));
        btnHabitacion.setMaximumSize(new java.awt.Dimension(185, 35));
        jToolBar1.add(btnHabitacion);

        btnReserva.setBackground(new java.awt.Color(255, 255, 255));
        btnReserva.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        btnReserva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/Reservas.png"))); // NOI18N
        btnReserva.setText("Reserva");
        btnReserva.setBorderPainted(false);
        btnReserva.setFocusable(false);
        btnReserva.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnReserva.setIconTextGap(40);
        btnReserva.setMargin(new java.awt.Insets(5, 5, 5, 30));
        btnReserva.setMaximumSize(new java.awt.Dimension(185, 35));
        jToolBar1.add(btnReserva);

        btnFactura.setBackground(new java.awt.Color(255, 255, 255));
        btnFactura.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        btnFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/Facturas.png"))); // NOI18N
        btnFactura.setText("Factura");
        btnFactura.setBorderPainted(false);
        btnFactura.setFocusable(false);
        btnFactura.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnFactura.setIconTextGap(40);
        btnFactura.setMargin(new java.awt.Insets(5, 5, 5, 35));
        btnFactura.setMaximumSize(new java.awt.Dimension(185, 35));
        jToolBar1.add(btnFactura);

        btnServicio.setBackground(new java.awt.Color(255, 255, 255));
        btnServicio.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        btnServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/Servicios.png"))); // NOI18N
        btnServicio.setText("Servicio");
        btnServicio.setBorderPainted(false);
        btnServicio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnServicio.setFocusable(false);
        btnServicio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnServicio.setIconTextGap(40);
        btnServicio.setMargin(new java.awt.Insets(5, 5, 5, 30));
        btnServicio.setMaximumSize(new java.awt.Dimension(185, 35));
        jToolBar1.add(btnServicio);

        btnLabor.setBackground(new java.awt.Color(255, 255, 255));
        btnLabor.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        btnLabor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/Labor.png"))); // NOI18N
        btnLabor.setText("Labor");
        btnLabor.setBorderPainted(false);
        btnLabor.setFocusable(false);
        btnLabor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLabor.setIconTextGap(40);
        btnLabor.setMargin(new java.awt.Insets(5, 5, 5, 45));
        btnLabor.setMaximumSize(new java.awt.Dimension(185, 35));
        btnLabor.setMinimumSize(new java.awt.Dimension(153, 39));
        jToolBar1.add(btnLabor);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/LogoHotel.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/Calendario.png"))); // NOI18N
        jLabel2.setToolTipText("Fecha de hoy");

        lblfechaDiaria.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/Usuario.png"))); // NOI18N

        btnCuenta.setBackground(new java.awt.Color(255, 255, 255));
        btnCuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/configuracion.png"))); // NOI18N
        btnCuenta.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnCuenta.setBorderPainted(false);
        btnCuenta.setFocusPainted(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 451, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblfechaDiaria, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblNameUser, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))
                    .addComponent(btnCuenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblfechaDiaria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblNameUser, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btnCuenta))
                .addContainerGap())
        );

        panelPrincipal = new javax.swing.JDesktopPane(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                int width = getWidth();
                int height = getHeight();

                Color color1 = new Color(255,196,0);
                Color color2 = new Color(198,11,29);

                GradientPaint gp = new GradientPaint(
                    0, 0, color1,
                    width / 2, height , color2);

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };
        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 865, Short.MAX_VALUE)
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelPrincipal))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelPrincipal)))
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
    private javax.swing.JButton btnCliente;
    private javax.swing.JButton btnCuenta;
    private javax.swing.JButton btnEmpleado;
    private javax.swing.JButton btnFactura;
    private javax.swing.JButton btnHabitacion;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLabor;
    private javax.swing.JButton btnReserva;
    private javax.swing.JButton btnServicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblNameUser;
    private javax.swing.JLabel lblfechaDiaria;
    private javax.swing.JDesktopPane panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
