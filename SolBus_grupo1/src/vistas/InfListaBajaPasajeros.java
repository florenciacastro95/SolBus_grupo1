/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

/**
 *
 * @author Usuario
 */
public class InfListaBajaPasajeros extends javax.swing.JInternalFrame {

    /**
     * Creates new form InfListaBajaPasajeros
     */
    public InfListaBajaPasajeros() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupete = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblTituloRegistroPasajero = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblApellido = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        lblDni = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        lblCorreo = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnActualizar1 = new javax.swing.JButton();
        spListarPasajeros = new javax.swing.JScrollPane();
        jtListarPasajeros = new javax.swing.JTable();
        btnBorrar = new javax.swing.JButton();
        lblTituloListarPasajeros = new javax.swing.JLabel();
        pnlDniNombre = new javax.swing.JPanel();
        rbDni = new javax.swing.JRadioButton();
        rbNombreApellido = new javax.swing.JRadioButton();
        rbVerTodo = new javax.swing.JRadioButton();
        jtDni = new javax.swing.JTextField();
        jtNombre = new javax.swing.JTextField();

        lblTituloRegistroPasajero.setText("REGISTRO DE PASAJEROS");

        lblTelefono.setText("TELEFONO");

        lblNombre.setText("NOMBRE");

        lblApellido.setText("APELLIDO");

        txtApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoActionPerformed(evt);
            }
        });

        lblDni.setText("DNI");

        lblCorreo.setText("CORREO");

        btnRegistrar.setText("REGISTRAR !");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lblTituloRegistroPasajero))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(lblNombre)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 83, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnRegistrar))
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellido)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDni)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCorreo)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelefono)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblTituloRegistroPasajero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombre)
                .addGap(8, 8, 8)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblApellido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(lblDni)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCorreo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTelefono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegistrar)
                .addGap(41, 41, 41))
        );

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnActualizar1.setText("ACTUALIZAR");
        jPanel3.add(btnActualizar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, -1, -1));

        jtListarPasajeros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        spListarPasajeros.setViewportView(jtListarPasajeros);

        jPanel3.add(spListarPasajeros, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 400, 125));

        btnBorrar.setText("BORRAR");
        jPanel3.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, -1));

        lblTituloListarPasajeros.setText("LISTAR PASAJEROS");
        jPanel3.add(lblTituloListarPasajeros, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        grupete.add(rbDni);
        rbDni.setText("DNI");

        grupete.add(rbNombreApellido);
        rbNombreApellido.setText("APELLIDO");

        grupete.add(rbVerTodo);
        rbVerTodo.setText("Ver Todo");

        javax.swing.GroupLayout pnlDniNombreLayout = new javax.swing.GroupLayout(pnlDniNombre);
        pnlDniNombre.setLayout(pnlDniNombreLayout);
        pnlDniNombreLayout.setHorizontalGroup(
            pnlDniNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDniNombreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDniNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbNombreApellido)
                    .addComponent(rbVerTodo)
                    .addComponent(rbDni))
                .addContainerGap())
        );
        pnlDniNombreLayout.setVerticalGroup(
            pnlDniNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDniNombreLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(rbDni)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbNombreApellido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbVerTodo)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.add(pnlDniNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 140));

        jtDni.setEnabled(false);
        jtDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtDniActionPerformed(evt);
            }
        });
        jPanel3.add(jtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 182, -1));

        jtNombre.setEnabled(false);
        jPanel3.add(jtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 182, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoActionPerformed

    private void jtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtDniActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizar1;
    public javax.swing.JButton btnBorrar;
    public javax.swing.JButton btnRegistrar;
    private javax.swing.ButtonGroup grupete;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JTextField jtDni;
    public javax.swing.JTable jtListarPasajeros;
    public javax.swing.JTextField jtNombre;
    public javax.swing.JLabel lblApellido;
    public javax.swing.JLabel lblCorreo;
    public javax.swing.JLabel lblDni;
    public javax.swing.JLabel lblNombre;
    public javax.swing.JLabel lblTelefono;
    public javax.swing.JLabel lblTituloListarPasajeros;
    public javax.swing.JLabel lblTituloRegistroPasajero;
    public javax.swing.JPanel pnlDniNombre;
    public javax.swing.JRadioButton rbDni;
    public javax.swing.JRadioButton rbNombreApellido;
    public javax.swing.JRadioButton rbVerTodo;
    public javax.swing.JScrollPane spListarPasajeros;
    public javax.swing.JTextField txtApellido;
    public javax.swing.JTextField txtCorreo;
    public javax.swing.JTextField txtDni;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
