
package vistas;
import entidades.*;
import accesoDatos.*;
import controladores.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
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
                try {
            Font montserratFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/Montserrat-Regular.ttf")).deriveFont(Font.PLAIN, 18);
            Font montserratFont1 = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/Montserrat-Regular.ttf")).deriveFont(Font.BOLD, 14);
            Font montserratFontTitulo = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/Montserrat-Regular.ttf")).deriveFont(Font.BOLD, 28);
            this.jLabel1.setFont(montserratFontTitulo);
            this.jLabel2.setFont(montserratFont);
            this.jLabel1.setForeground(Color.WHITE);
            this.jLabel2.setForeground(Color.WHITE);
            this.mColectivos.setFont(montserratFont1);
            this.MRutas.setFont(montserratFont1);
            this.mPasajeros.setFont(montserratFont1);
            this.mPasajes.setFont(montserratFont1);
            this.miColectivos.setFont(montserratFont1);
            this.miGestRutHor.setFont(montserratFont1);
            this.miGestionPasajes.setFont(montserratFont1);
            this.miHistorialAnular.setFont(montserratFont1);
            this.miListarPasajeros.setFont(montserratFont1);
            
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        mbEscritorio = new javax.swing.JMenuBar();
        mPasajes = new javax.swing.JMenu();
        miGestionPasajes = new javax.swing.JMenuItem();
        miHistorialAnular = new javax.swing.JMenuItem();
        mPasajeros = new javax.swing.JMenu();
        miListarPasajeros = new javax.swing.JMenuItem();
        mColectivos = new javax.swing.JMenu();
        miColectivos = new javax.swing.JMenuItem();
        MRutas = new javax.swing.JMenu();
        miGestRutHor = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        dpEscritorio.setPreferredSize(new java.awt.Dimension(1040, 640));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setText("El placer de viajar con nosotros");

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setText("Sol Bus");

        dpEscritorio.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dpEscritorio.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout dpEscritorioLayout = new javax.swing.GroupLayout(dpEscritorio);
        dpEscritorio.setLayout(dpEscritorioLayout);
        dpEscritorioLayout.setHorizontalGroup(
            dpEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dpEscritorioLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(dpEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(731, Short.MAX_VALUE))
        );
        dpEscritorioLayout.setVerticalGroup(
            dpEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dpEscritorioLayout.createSequentialGroup()
                .addGap(393, 393, 393)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(174, 174, 174))
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

        miListarPasajeros.setText("Listar Pasajeros");
        miListarPasajeros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miListarPasajerosActionPerformed(evt);
            }
        });
        mPasajeros.add(miListarPasajeros);

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
        int x = (dpEscritorio.getWidth() - ip.getWidth()) / 2;
        int y = (dpEscritorio.getHeight() - ip.getHeight()) / 2;

        ip.setLocation(x, y);
        
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
        int x = (dpEscritorio.getWidth() - igrh.getWidth()) / 2;
        int y = (dpEscritorio.getHeight() - igrh.getHeight()) / 2;

        igrh.setLocation(x, y);
        dpEscritorio.add(igrh);
        dpEscritorio.moveToFront(igrh);
    }//GEN-LAST:event_miGestRutHorActionPerformed

    private void miListarPasajerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miListarPasajerosActionPerformed
        Pasajero pasajero = new Pasajero();
        PasajeroData pasajeroData = new PasajeroData();
        vistas.InfListaBajaPasajeros ibp = new vistas.InfListaBajaPasajeros();
        ctrlListarPasajeros clp = new ctrlListarPasajeros(pasajero, pasajeroData, ibp);
        dpEscritorio.removeAll();
        dpEscritorio.repaint();
        dpEscritorio.add(backgroundLabel, new Integer(Integer.MIN_VALUE));
        ibp.setVisible(true);
        int x = (dpEscritorio.getWidth() - ibp.getWidth()) / 2;
        int y = (dpEscritorio.getHeight() - ibp.getHeight()) / 2;

        ibp.setLocation(x, y);
        dpEscritorio.add(ibp);
        dpEscritorio.moveToFront(ibp);
    }//GEN-LAST:event_miListarPasajerosActionPerformed

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
       int x = (dpEscritorio.getWidth() - ihp.getWidth()) / 2;
        int y = (dpEscritorio.getHeight() - ihp.getHeight()) / 2;

        ihp.setLocation(x, y);
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
        dpEscritorio.add(backgroundLabel, new Integer(Integer.MIN_VALUE));
        cV.setVisible(true);
        int x = (dpEscritorio.getWidth() - cV.getWidth()) / 2;
        int y = (dpEscritorio.getHeight() - cV.getHeight()) / 2;

        cV.setLocation(x, y);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu mColectivos;
    private javax.swing.JMenu mPasajeros;
    private javax.swing.JMenu mPasajes;
    private javax.swing.JMenuBar mbEscritorio;
    private javax.swing.JMenuItem miColectivos;
    private javax.swing.JMenuItem miGestRutHor;
    private javax.swing.JMenuItem miGestionPasajes;
    private javax.swing.JMenuItem miHistorialAnular;
    private javax.swing.JMenuItem miListarPasajeros;
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

