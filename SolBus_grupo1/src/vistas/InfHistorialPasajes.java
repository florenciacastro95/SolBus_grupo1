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
        lblTituloHistPasajes = new javax.swing.JLabel();
        pnlPasajero = new javax.swing.JPanel();
        rbPasajero = new javax.swing.JRadioButton();
        txtApellido = new javax.swing.JTextField();
        txtDNI = new javax.swing.JTextField();
        pnlRuta = new javax.swing.JPanel();
        cbRutas = new javax.swing.JComboBox<>();
        rbRuta = new javax.swing.JRadioButton();
        pnlHorario = new javax.swing.JPanel();
        rbHorario = new javax.swing.JRadioButton();
        cbHorarios = new javax.swing.JComboBox<>();
        jlPasajesT = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        dateChooser = new com.toedter.calendar.JDateChooser();
        btnVerHistorial = new javax.swing.JButton();

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

        lblTituloHistPasajes.setText("FILTRAR PASAJES");

        rbgPasRutaHor.add(rbPasajero);
        rbPasajero.setText("PASAJERO");

        javax.swing.GroupLayout pnlPasajeroLayout = new javax.swing.GroupLayout(pnlPasajero);
        pnlPasajero.setLayout(pnlPasajeroLayout);
        pnlPasajeroLayout.setHorizontalGroup(
            pnlPasajeroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPasajeroLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(rbPasajero))
            .addGroup(pnlPasajeroLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlPasajeroLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlPasajeroLayout.setVerticalGroup(
            pnlPasajeroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPasajeroLayout.createSequentialGroup()
                .addComponent(rbPasajero)
                .addGap(18, 18, 18)
                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        rbgPasRutaHor.add(rbRuta);
        rbRuta.setText("RUTA");

        javax.swing.GroupLayout pnlRutaLayout = new javax.swing.GroupLayout(pnlRuta);
        pnlRuta.setLayout(pnlRutaLayout);
        pnlRutaLayout.setHorizontalGroup(
            pnlRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRutaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(rbRuta))
            .addGroup(pnlRutaLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(cbRutas, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlRutaLayout.setVerticalGroup(
            pnlRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRutaLayout.createSequentialGroup()
                .addComponent(rbRuta)
                .addGap(18, 18, 18)
                .addComponent(cbRutas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlHorario.setPreferredSize(new java.awt.Dimension(198, 79));

        rbgPasRutaHor.add(rbHorario);
        rbHorario.setText("HORARIO");

        javax.swing.GroupLayout pnlHorarioLayout = new javax.swing.GroupLayout(pnlHorario);
        pnlHorario.setLayout(pnlHorarioLayout);
        pnlHorarioLayout.setHorizontalGroup(
            pnlHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHorarioLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbHorario)
                    .addComponent(cbHorarios, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnlHorarioLayout.setVerticalGroup(
            pnlHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHorarioLayout.createSequentialGroup()
                .addComponent(rbHorario)
                .addGap(18, 18, 18)
                .addComponent(cbHorarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jlPasajesT.setText("Pasajes por fecha");

        btnActualizar.setText("Actualizar");

        btnVerHistorial.setText("Imprimir historial de hoy");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addComponent(lblTituloHistPasajes))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(pnlPasajero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(pnlRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(pnlHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(jlPasajesT)
                        .addGap(27, 27, 27)
                        .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(spnHistPasajes, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(btnActualizar)
                        .addGap(103, 103, 103)
                        .addComponent(btnVerHistorial)))
                .addGap(2, 2, 2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTituloHistPasajes)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPasajero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlPasajesT)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(spnHistPasajes, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnVerHistorial;
    public javax.swing.JComboBox<Horario> cbHorarios;
    public javax.swing.JComboBox<Ruta> cbRutas;
    public com.toedter.calendar.JDateChooser dateChooser;
    public javax.swing.JLabel jlPasajesT;
    public javax.swing.JLabel lblTituloHistPasajes;
    public javax.swing.JPanel pnlHorario;
    public javax.swing.JPanel pnlPasajero;
    public javax.swing.JPanel pnlRuta;
    public javax.swing.JRadioButton rbHorario;
    public javax.swing.JRadioButton rbPasajero;
    public javax.swing.JRadioButton rbRuta;
    private javax.swing.ButtonGroup rbgPasRutaHor;
    public javax.swing.JScrollPane spnHistPasajes;
    public javax.swing.JTable tblHistPasajes;
    public javax.swing.JTextField txtApellido;
    public javax.swing.JTextField txtDNI;
    // End of variables declaration//GEN-END:variables
}
