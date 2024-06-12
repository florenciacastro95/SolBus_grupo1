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
        lblTitutloPasaje = new javax.swing.JLabel();
        lblTituloGestion = new javax.swing.JLabel();
        lblTituloFiltrar = new javax.swing.JLabel();

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

        getContentPane().add(spnHistPasajes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 700, 200));

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
                .addGroup(pnlPasajeroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlPasajeroLayout.createSequentialGroup()
                        .addComponent(lblDni)
                        .addGap(44, 44, 44)
                        .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPasajeroLayout.createSequentialGroup()
                        .addComponent(lblApellido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(pnlPasajeroLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(rbPasajero)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        getContentPane().add(pnlPasajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        rbgPasRutaHor.add(rbRuta);
        rbRuta.setText("RUTA");

        javax.swing.GroupLayout pnlRutaLayout = new javax.swing.GroupLayout(pnlRuta);
        pnlRuta.setLayout(pnlRutaLayout);
        pnlRutaLayout.setHorizontalGroup(
            pnlRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRutaLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbRuta)
                    .addComponent(cbRutas2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnlRutaLayout.setVerticalGroup(
            pnlRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRutaLayout.createSequentialGroup()
                .addComponent(rbRuta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbRutas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(pnlRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, 80));
        getContentPane().add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 190, -1));

        btnVerHistorial.setText("Imprimir historial ");
        getContentPane().add(btnVerHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 510, -1, 50));

        lblTitutloPasaje.setText("Pasajes por fecha");
        getContentPane().add(lblTitutloPasaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, -1, 30));

        lblTituloGestion.setText("GESTIÓN DE PASAJES");
        getContentPane().add(lblTituloGestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, -1));

        lblTituloFiltrar.setText("Filtrar pasajes");
        getContentPane().add(lblTituloFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnVerHistorial;
    public javax.swing.JComboBox<Ruta> cbRutas2;
    public com.toedter.calendar.JDateChooser dateChooser;
    public javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblDni;
    public javax.swing.JLabel lblTituloFiltrar;
    public javax.swing.JLabel lblTituloGestion;
    public javax.swing.JLabel lblTitutloPasaje;
    public javax.swing.JPanel pnlPasajero;
    public javax.swing.JPanel pnlRuta;
    public javax.swing.JRadioButton rbPasajero;
    public javax.swing.JRadioButton rbRuta;
    private javax.swing.ButtonGroup rbgPasRutaHor;
    public javax.swing.JScrollPane spnHistPasajes;
    public javax.swing.JTable tblHistPasajes;
    public javax.swing.JTextField txtApellido;
    public javax.swing.JTextField txtDNI;
    // End of variables declaration//GEN-END:variables
}
