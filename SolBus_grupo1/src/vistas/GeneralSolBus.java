
package vistas;
import entidades.*;
import accesoDatos.*;
import controladores.*;
import java.awt.BorderLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GeneralSolBus extends javax.swing.JFrame {
    private Image imagenFondo;
    
    private JLabel backgroundLabel;
    
    public GeneralSolBus() {
        initComponents();
        cargarImagenFondo();
    }

    private void cargarImagenFondo() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/bg.jpg"));
        Image image = icon.getImage().getScaledInstance(dpEscritorio.getWidth(), dpEscritorio.getHeight(), Image.SCALE_SMOOTH);
        backgroundLabel = new JLabel(new ImageIcon(image));
        backgroundLabel.setSize(dpEscritorio.getWidth(), dpEscritorio.getHeight());
        dpEscritorio.add(backgroundLabel, new Integer(Integer.MIN_VALUE));
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
        miColectivos = new javax.swing.JMenuItem();
        MRutas = new javax.swing.JMenu();
        miGestRutHor = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        dpEscritorio.setPreferredSize(new java.awt.Dimension(1040, 640));

        javax.swing.GroupLayout dpEscritorioLayout = new javax.swing.GroupLayout(dpEscritorio);
        dpEscritorio.setLayout(dpEscritorioLayout);
        dpEscritorioLayout.setHorizontalGroup(
            dpEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );
        dpEscritorioLayout.setVerticalGroup(
            dpEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
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
        miHistorialAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miHistorialAnularActionPerformed(evt);
            }
        });
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

        miColectivos.setText("Gestión de colectivos");
        miColectivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miColectivosActionPerformed(evt);
            }
        });
        mColectivos.add(miColectivos);

        mbEscritorio.add(mColectivos);

        MRutas.setText("Rutas y horarios");

        miGestRutHor.setText("Gestion de rutas y horarios");
        miGestRutHor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miGestRutHorActionPerformed(evt);
            }
        });
        MRutas.add(miGestRutHor);

        mbEscritorio.add(MRutas);

        setJMenuBar(mbEscritorio);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpEscritorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpEscritorio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miGestionPasajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miGestionPasajesActionPerformed
        Pasaje p = new Pasaje();
        PasajeData pd = new PasajeData();
        vistas.InfGestionPasajes ip;
        try {
            ip = new vistas.InfGestionPasajes();
        
        ctrlCargaPasajes cp = new ctrlCargaPasajes(p, pd, ip);
        dpEscritorio.removeAll();
        dpEscritorio.repaint();
        dpEscritorio.add(backgroundLabel, new Integer(Integer.MIN_VALUE));
        ip.setVisible(true);
        ip.setLocation(00, 00);
        
        dpEscritorio.add(ip);
        dpEscritorio.moveToFront(ip);
        
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GeneralSolBus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_miGestionPasajesActionPerformed

    private void miGestRutHorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miGestRutHorActionPerformed
        // TODO add your handling code here:
        Ruta rR = new Ruta();
        Ruta rH = new Ruta();
        Horario h = new Horario();
        RutaData rd = new RutaData();
        HorarioData hd = new HorarioData();
        vistas.infGestionRutaHorario igrh = new vistas.infGestionRutaHorario();
        ctrlGestionRutaHorario cgrh = new ctrlGestionRutaHorario(rR, rH, h, rd, hd, igrh);
        dpEscritorio.removeAll();
        dpEscritorio.repaint();
        dpEscritorio.add(backgroundLabel, new Integer(Integer.MIN_VALUE));
        igrh.setVisible(true);
        igrh.setLocation(00, 00);
        dpEscritorio.add(igrh);
        dpEscritorio.moveToFront(igrh);
    }//GEN-LAST:event_miGestRutHorActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Pasajero pasajero = new Pasajero();
        PasajeroData pasajeroData = new PasajeroData();
        vistas.InfListaBajaPasajeros ibp = new vistas.InfListaBajaPasajeros();
        ctrlListarPasajeros clp = new ctrlListarPasajeros(pasajero, pasajeroData, ibp);
        dpEscritorio.removeAll();
        dpEscritorio.repaint();
        dpEscritorio.add(backgroundLabel, new Integer(Integer.MIN_VALUE));
        ibp.setVisible(true);
        ibp.setLocation(00, 00);
        dpEscritorio.add(ibp);
        dpEscritorio.moveToFront(ibp);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void miHistorialAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miHistorialAnularActionPerformed
       PasajeData pd = new PasajeData();
       RutaData rd = new RutaData();
       HorarioData hd = new HorarioData();
       ColectivoData cd = new ColectivoData();
       PasajeroData pasd = new PasajeroData();
       Pasaje pasaje = new Pasaje();
       InfHistorialPasajes ihp = new InfHistorialPasajes();
        try {
            ctrlHistorialPasajes chp = new ctrlHistorialPasajes(pasaje, pd, rd, hd, cd, ihp, pasd);
        } catch (IOException ex) {
            Logger.getLogger(GeneralSolBus.class.getName()).log(Level.SEVERE, null, ex);
        }
       dpEscritorio.removeAll();
       dpEscritorio.repaint();
       dpEscritorio.add(backgroundLabel, new Integer(Integer.MIN_VALUE));
       ihp.setVisible(true);
       ihp.setLocation(00, 00);
       dpEscritorio.add(ihp);
       dpEscritorio.moveToFront(ihp);
    }//GEN-LAST:event_miHistorialAnularActionPerformed

    private void miColectivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miColectivosActionPerformed
        // TODO add your handling code here:
        Colectivo c = new Colectivo();
        ColectivoData cD = new ColectivoData();
        infGestionColectivo cV = new infGestionColectivo();
        ctrlGestionColectivos cGC = new ctrlGestionColectivos(c, cD, cV);
        dpEscritorio.removeAll();
        dpEscritorio.repaint();
        cV.setVisible(true);
        cV.setLocation(00, 00);
        dpEscritorio.add(cV);
        dpEscritorio.moveToFront(cV);
    }//GEN-LAST:event_miColectivosActionPerformed

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
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenu mColectivos;
    private javax.swing.JMenu mPasajeros;
    private javax.swing.JMenu mPasajes;
    private javax.swing.JMenuBar mbEscritorio;
    private javax.swing.JMenuItem miColectivos;
    private javax.swing.JMenuItem miGestRutHor;
    private javax.swing.JMenuItem miGestionPasajes;
    private javax.swing.JMenuItem miHistorialAnular;
    // End of variables declaration//GEN-END:variables
}
//
//class Fondo extends JPanel {
//
//    private Image imagen;
//
//    @Override
//    public void paint(Graphics g) {
//
//        imagen = new ImageIcon(getClass().getResource("/imagenes/sol-bus-1.jpg")).getImage();
//
//        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
//
//        setOpaque(false);
//        super.paint(g);
//    }
//}

