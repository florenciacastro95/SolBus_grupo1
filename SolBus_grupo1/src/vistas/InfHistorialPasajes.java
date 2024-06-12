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
        rbRuta = new javax.swing.JRadioButton();
        cbRutas = new javax.swing.JComboBox<>();
        pnlHorario = new javax.swing.JPanel();
        rbHorario = new javax.swing.JRadioButton();
        cbHorarios = new javax.swing.JComboBox<>();
        lblPrecio = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        dateChooser = new com.toedter.calendar.JDateChooser();
        btnVerHistorial = new javax.swing.JButton();
        txtNombreApellido = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtButaca = new javax.swing.JTextField();
        jlPasajesT1 = new javax.swing.JLabel();
        lblHorario = new javax.swing.JLabel();
        lblButaca = new javax.swing.JLabel();
        cbRutas2 = new javax.swing.JComboBox<>();
        cbHorarios2 = new javax.swing.JComboBox<>();
        jlPasajesT3 = new javax.swing.JLabel();
        lblRuta = new javax.swing.JLabel();

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

        getContentPane().add(spnHistPasajes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 749, 130));

        lblTituloHistPasajes.setText("FILTRAR PASAJES");
        getContentPane().add(lblTituloHistPasajes, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, -1));

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

        getContentPane().add(pnlPasajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        rbgPasRutaHor.add(rbRuta);
        rbRuta.setText("RUTA");

        javax.swing.GroupLayout pnlRutaLayout = new javax.swing.GroupLayout(pnlRuta);
        pnlRuta.setLayout(pnlRutaLayout);
        pnlRutaLayout.setHorizontalGroup(
            pnlRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRutaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(rbRuta)
                .addGap(98, 98, 98))
            .addGroup(pnlRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlRutaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(cbRutas, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(10, Short.MAX_VALUE)))
        );
        pnlRutaLayout.setVerticalGroup(
            pnlRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRutaLayout.createSequentialGroup()
                .addComponent(rbRuta)
                .addGap(52, 52, 52))
            .addGroup(pnlRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRutaLayout.createSequentialGroup()
                    .addContainerGap(48, Short.MAX_VALUE)
                    .addComponent(cbRutas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        getContentPane().add(pnlRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, 80));

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

        getContentPane().add(pnlHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, -1, 100));

        lblPrecio.setText("Nuevo precio       $");
        getContentPane().add(lblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 420, -1, -1));

        btnActualizar.setText("Actualizar");
        getContentPane().add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 380, -1, 50));
        getContentPane().add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, -1, -1));

        btnVerHistorial.setText("Imprimir historial ");
        getContentPane().add(btnVerHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 510, -1, 50));

        txtNombreApellido.setText("jTextField1");
        getContentPane().add(txtNombreApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, -1, -1));

        txtPrecio.setText("jTextField1");
        getContentPane().add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 420, -1, -1));

        txtButaca.setText("jTextField1");
        getContentPane().add(txtButaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 380, -1, -1));

        jlPasajesT1.setText("Pasajes por fecha");
        getContentPane().add(jlPasajesT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, -1, -1));

        lblHorario.setText("Nuevo Horario");
        getContentPane().add(lblHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, -1, -1));

        lblButaca.setText("Nueva butaca");
        getContentPane().add(lblButaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 380, -1, -1));

        getContentPane().add(cbRutas2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, 155, -1));

        getContentPane().add(cbHorarios2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 450, 185, -1));

        jlPasajesT3.setText("Nuevo nombre y apellido");
        getContentPane().add(jlPasajesT3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        lblRuta.setText("Nueva ruta");
        getContentPane().add(lblRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnVerHistorial;
    public javax.swing.JComboBox<Horario> cbHorarios;
    public javax.swing.JComboBox<Horario> cbHorarios2;
    public javax.swing.JComboBox<Ruta> cbRutas;
    public javax.swing.JComboBox<Ruta> cbRutas2;
    public com.toedter.calendar.JDateChooser dateChooser;
    public javax.swing.JLabel jlPasajesT1;
    public javax.swing.JLabel jlPasajesT3;
    public javax.swing.JLabel lblButaca;
    public javax.swing.JLabel lblHorario;
    public javax.swing.JLabel lblPrecio;
    public javax.swing.JLabel lblRuta;
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
    public javax.swing.JTextField txtButaca;
    public javax.swing.JTextField txtDNI;
    public javax.swing.JTextField txtNombreApellido;
    public javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
