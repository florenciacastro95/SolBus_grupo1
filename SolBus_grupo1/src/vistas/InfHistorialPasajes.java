/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import entidades.Horario;
import entidades.Ruta;

public class InfHistorialPasajes extends javax.swing.JInternalFrame {

    
    public InfHistorialPasajes() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbgPasRutaHor = new javax.swing.ButtonGroup();
        spnHistPasajes = new javax.swing.JScrollPane();
        tblHistPasajes = new javax.swing.JTable();
        pnlPasajero = new javax.swing.JPanel();
        rbPasajero = new javax.swing.JRadioButton();
        txtApellido = new javax.swing.JTextField();
        txtDNI = new javax.swing.JTextField();
        lblDni = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        pnlRuta = new javax.swing.JPanel();
        rbRuta = new javax.swing.JRadioButton();
        cbRutas2 = new javax.swing.JComboBox<>();
        dateChooser = new com.toedter.calendar.JDateChooser();
        btnVerHistorial = new javax.swing.JButton();
        lblTituloGestion = new javax.swing.JLabel();
        lblTituloFiltrar = new javax.swing.JLabel();
        rbFecha = new javax.swing.JRadioButton();
        rbVerTodo = new javax.swing.JRadioButton();
        lblSugerencia = new javax.swing.JLabel();
        lblGanador1 = new javax.swing.JLabel();
        btnEnviarMail = new javax.swing.JButton();
        btnMolestar = new javax.swing.JButton();

        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblHistPasajes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        spnHistPasajes.setViewportView(tblHistPasajes);

        getContentPane().add(spnHistPasajes, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 192, 700, 176));

        rbgPasRutaHor.add(rbPasajero);
        rbPasajero.setText("PASAJERO");

        lblDni.setText("DNI");

        lblApellido.setText("APELLIDO");

        javax.swing.GroupLayout pnlPasajeroLayout = new javax.swing.GroupLayout(pnlPasajero);
        pnlPasajero.setLayout(pnlPasajeroLayout);
        pnlPasajeroLayout.setHorizontalGroup(
            pnlPasajeroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPasajeroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPasajeroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlPasajeroLayout.createSequentialGroup()
                        .addComponent(lblDni)
                        .addGap(44, 44, 44)
                        .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPasajeroLayout.createSequentialGroup()
                        .addComponent(lblApellido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtApellido))))
            .addGroup(pnlPasajeroLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(rbPasajero))
        );
        pnlPasajeroLayout.setVerticalGroup(
            pnlPasajeroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPasajeroLayout.createSequentialGroup()
                .addComponent(rbPasajero)
                .addGap(14, 14, 14)
                .addGroup(pnlPasajeroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDni)
                    .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(pnlPasajeroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(pnlPasajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, -1));

        rbgPasRutaHor.add(rbRuta);
        rbRuta.setText("RUTA");

        javax.swing.GroupLayout pnlRutaLayout = new javax.swing.GroupLayout(pnlRuta);
        pnlRuta.setLayout(pnlRutaLayout);
        pnlRutaLayout.setHorizontalGroup(
            pnlRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRutaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRutaLayout.createSequentialGroup()
                        .addComponent(rbRuta)
                        .addGap(92, 92, 92))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRutaLayout.createSequentialGroup()
                        .addComponent(cbRutas2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        pnlRutaLayout.setVerticalGroup(
            pnlRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRutaLayout.createSequentialGroup()
                .addComponent(rbRuta)
                .addGap(18, 18, 18)
                .addComponent(cbRutas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(pnlRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, -1));
        getContentPane().add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 153, -1));

        btnVerHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logos/logos32/005-transaction-history.png"))); // NOI18N
        btnVerHistorial.setText(" IMPRIMIR HISTORIAL ");
        getContentPane().add(btnVerHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 450, -1, 50));

        lblTituloGestion.setText("LISTADO DE PASAJES");
        getContentPane().add(lblTituloGestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, -1, -1));

        lblTituloFiltrar.setText("Filtrar pasajes");
        getContentPane().add(lblTituloFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, -1, -1));

        rbgPasRutaHor.add(rbFecha);
        rbFecha.setText("PASAJES POR FECHA");
        getContentPane().add(rbFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, -1, 42));

        rbgPasRutaHor.add(rbVerTodo);
        rbVerTodo.setText("VER TODO");
        getContentPane().add(rbVerTodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 386, -1, -1));

        lblSugerencia.setText("El décimo pasaje es gratis*");
        getContentPane().add(lblSugerencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, -1, -1));

        lblGanador1.setText("Soy invicible :D");
        lblGanador1.setVisible(false);
        getContentPane().add(lblGanador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 380, 209, 60));

        btnEnviarMail.setText("Enviarle un mail");
        getContentPane().add(btnEnviarMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 440, 160, 40));
        btnEnviarMail.setVisible(false);

        btnMolestar.setText("Molestar a Juanjo");
        getContentPane().add(btnMolestar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, 160, 40));
        btnEnviarMail.setVisible(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnEnviarMail;
    public javax.swing.JButton btnMolestar;
    public javax.swing.JButton btnVerHistorial;
    public javax.swing.JComboBox<Ruta> cbRutas2;
    public com.toedter.calendar.JDateChooser dateChooser;
    public javax.swing.JLabel lblApellido;
    public javax.swing.JLabel lblDni;
    public javax.swing.JLabel lblGanador1;
    public javax.swing.JLabel lblSugerencia;
    public javax.swing.JLabel lblTituloFiltrar;
    public javax.swing.JLabel lblTituloGestion;
    public javax.swing.JPanel pnlPasajero;
    public javax.swing.JPanel pnlRuta;
    public javax.swing.JRadioButton rbFecha;
    public javax.swing.JRadioButton rbPasajero;
    public javax.swing.JRadioButton rbRuta;
    public javax.swing.JRadioButton rbVerTodo;
    private javax.swing.ButtonGroup rbgPasRutaHor;
    public javax.swing.JScrollPane spnHistPasajes;
    public javax.swing.JTable tblHistPasajes;
    public javax.swing.JTextField txtApellido;
    public javax.swing.JTextField txtDNI;
    // End of variables declaration//GEN-END:variables
}
