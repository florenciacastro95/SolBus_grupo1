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
        btnBuscar = new javax.swing.JButton();

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

        btnBorrar.setText("BORRAR");

        lblTituloListarPasajeros.setText("LISTAR PASAJEROS");

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
                    .addComponent(rbDni)
                    .addComponent(rbVerTodo))
                .addContainerGap())
        );
        pnlDniNombreLayout.setVerticalGroup(
            pnlDniNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDniNombreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbDni)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbNombreApellido)
                .addGap(18, 18, 18)
                .addComponent(rbVerTodo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtDni.setEnabled(false);
        jtDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtDniActionPerformed(evt);
            }
        });

        jtNombre.setEnabled(false);

        btnBuscar.setText("BUSCAR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlDniNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtDni)
                                    .addComponent(jtNombre)))
                            .addComponent(spListarPasajeros, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(btnBorrar)
                        .addGap(97, 97, 97)
                        .addComponent(btnBuscar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(lblTituloListarPasajeros)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTituloListarPasajeros)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlDniNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spListarPasajeros, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrar)
                    .addComponent(btnBuscar))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtDniActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    public javax.swing.JButton btnBuscar;
    private javax.swing.ButtonGroup grupete;
    public javax.swing.JTextField jtDni;
    public javax.swing.JTable jtListarPasajeros;
    public javax.swing.JTextField jtNombre;
    private javax.swing.JLabel lblTituloListarPasajeros;
    private javax.swing.JPanel pnlDniNombre;
    public javax.swing.JRadioButton rbDni;
    public javax.swing.JRadioButton rbNombreApellido;
    public javax.swing.JRadioButton rbVerTodo;
    private javax.swing.JScrollPane spListarPasajeros;
    // End of variables declaration//GEN-END:variables
}
