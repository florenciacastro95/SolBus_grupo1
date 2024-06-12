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

        btnVerHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logos/logos32/005-transaction-history.png"))); // NOI18N
        btnVerHistorial.setText(" IMPRIMIR HISTORIAL ");

        lblTitutloPasaje.setText("Pasajes por fecha");

        lblTituloGestion.setText("GESTIÓN DE PASAJES");

        lblTituloFiltrar.setText("Filtrar pasajes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(lblTituloGestion))
            .addGroup(layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(lblTituloFiltrar))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(pnlPasajero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(pnlRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitutloPasaje)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(spnHistPasajes, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(btnVerHistorial))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblTituloGestion)
                .addGap(24, 24, 24)
                .addComponent(lblTituloFiltrar)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPasajero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitutloPasaje, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54)
                .addComponent(spnHistPasajes, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(btnVerHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnVerHistorial;
    public javax.swing.JComboBox<Ruta> cbRutas2;
    public com.toedter.calendar.JDateChooser dateChooser;
    public javax.swing.JLabel lblApellido;
    public javax.swing.JLabel lblDni;
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
