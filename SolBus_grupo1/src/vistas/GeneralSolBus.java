
package vistas;
import entidades.*;
import accesoDatos.*;
import controladores.*;

public class GeneralSolBus extends javax.swing.JFrame {

    
    public GeneralSolBus() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dpEscritorio = new javax.swing.JDesktopPane();
        mbEscritorio = new javax.swing.JMenuBar();
        mPasajes = new javax.swing.JMenu();
        miGestionPasajes = new javax.swing.JMenuItem();
        miHistorialAnular = new javax.swing.JMenuItem();
        mPasajeros = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        mColectivos = new javax.swing.JMenu();
        mHorarios = new javax.swing.JMenu();
        MRutas = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        miListarRutas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout dpEscritorioLayout = new javax.swing.GroupLayout(dpEscritorio);
        dpEscritorio.setLayout(dpEscritorioLayout);
        dpEscritorioLayout.setHorizontalGroup(
            dpEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        dpEscritorioLayout.setVerticalGroup(
            dpEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 646, Short.MAX_VALUE)
        );

        mPasajes.setText("Pasajes");

        miGestionPasajes.setText("Gestion de pasajes");
        miGestionPasajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miGestionPasajesActionPerformed(evt);
            }
        });
        mPasajes.add(miGestionPasajes);

        miHistorialAnular.setText("Historial de pasajes y anular ventas");
        mPasajes.add(miHistorialAnular);

        mbEscritorio.add(mPasajes);

        mPasajeros.setText("Pasajeros");

        jMenuItem2.setText("Listar Pasajeros");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        mPasajeros.add(jMenuItem2);

        mbEscritorio.add(mPasajeros);

        mColectivos.setText("Colectivos");
        mbEscritorio.add(mColectivos);

        mHorarios.setText("Horarios");
        mbEscritorio.add(mHorarios);

        MRutas.setText("Rutas");

        jMenuItem1.setText("Gestion de rutas");
        MRutas.add(jMenuItem1);

        miListarRutas.setText("Rutas disponibles");
        miListarRutas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miListarRutasActionPerformed(evt);
            }
        });
        MRutas.add(miListarRutas);

        mbEscritorio.add(MRutas);

        setJMenuBar(mbEscritorio);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpEscritorio, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpEscritorio, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miGestionPasajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miGestionPasajesActionPerformed
        Pasaje p = new Pasaje();
        PasajeData pd = new PasajeData();
        vistas.InfGestionPasajes ip = new vistas.InfGestionPasajes();
        ctrlCargaPasajes cp = new ctrlCargaPasajes(p, pd, ip);
        dpEscritorio.removeAll();
        dpEscritorio.repaint();
        ip.setVisible(true);
        ip.setLocation(00, 00);
        dpEscritorio.add(ip);
        dpEscritorio.moveToFront(ip);
    }//GEN-LAST:event_miGestionPasajesActionPerformed

    private void miListarRutasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miListarRutasActionPerformed
        // TODO add your handling code here:
        RutaData rd = new RutaData();
        vistas.InfListarRutas ilr = new vistas.InfListarRutas();
        ctrlListarRutas clr = new ctrlListarRutas(rd, ilr);
        dpEscritorio.removeAll();
        dpEscritorio.repaint();
        ilr.setVisible(true);
        ilr.setLocation(00, 00);
        dpEscritorio.add(ilr);
        dpEscritorio.moveToFront(ilr);
    }//GEN-LAST:event_miListarRutasActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Pasajero pasajero = new Pasajero();
        PasajeroData pasajeroData = new PasajeroData();
        vistas.InfListaBajaPasajeros ibp = new vistas.InfListaBajaPasajeros();
        ctrlListarPasajeros clp = new ctrlListarPasajeros(pasajero, pasajeroData, ibp);
        dpEscritorio.removeAll();
        dpEscritorio.repaint();
        ibp.setVisible(true);
        ibp.setLocation(00, 00);
        dpEscritorio.add(ibp);
        dpEscritorio.moveToFront(ibp);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GeneralSolBus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GeneralSolBus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GeneralSolBus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GeneralSolBus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GeneralSolBus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MRutas;
    private javax.swing.JDesktopPane dpEscritorio;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenu mColectivos;
    private javax.swing.JMenu mHorarios;
    private javax.swing.JMenu mPasajeros;
    private javax.swing.JMenu mPasajes;
    private javax.swing.JMenuBar mbEscritorio;
    private javax.swing.JMenuItem miGestionPasajes;
    private javax.swing.JMenuItem miHistorialAnular;
    private javax.swing.JMenuItem miListarRutas;
    // End of variables declaration//GEN-END:variables
}
