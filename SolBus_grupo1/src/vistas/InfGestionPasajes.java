
package vistas;
import entidades.*;
import vistas.*;
import accesoDatos.*;
import java.util.ArrayList;

public class InfGestionPasajes extends javax.swing.JInternalFrame {

    ArrayList<Ruta> rutas = new ArrayList<>();
    ArrayList<Colectivo> colectivos = new ArrayList<>();
    RutaData rd=new RutaData();
    HorarioData hd=new HorarioData();
    ColectivoData cd = new ColectivoData();
    public InfGestionPasajes() {
        
        initComponents();
        rutas=(ArrayList<Ruta>) rd.listarRutasDisponibles();
        colectivos = (ArrayList<Colectivo>) cd.listarColectivos();
        cargarCbRuta(rutas);
        cargarCbHorario();
        cargarCbColectivo(colectivos);
        
    }

   private void cargarCbColectivo(ArrayList<Colectivo>colectivos){
    
        for (Colectivo cole : colectivos) {
            cbColectivos.addItem(cole);
        }
    }
    private void cargarCbRuta(ArrayList<Ruta>rutas){
    
        for (Ruta ruta : rutas) {
            cbRuta.addItem(ruta);
        }
    }
    private void cargarCbHorario(){
        System.out.println(cbRuta.getSelectedItem());
        cargarCbHorario((Ruta) cbRuta.getSelectedItem());
    
    }
    private void cargarCbHorario(Ruta ruta){
        ArrayList<Horario> horarios = new ArrayList<>();
       
        horarios=(ArrayList<Horario>) hd.listarHorariosPorRuta(ruta);
        for (Horario horario : horarios) {
            System.out.println(horario.toString());
            cbHorario.addItem(horario);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lblTitulo = new javax.swing.JLabel();
        lblRuta = new javax.swing.JLabel();
        lblHorario = new javax.swing.JLabel();
        cbRuta = new javax.swing.JComboBox<>();
        cbHorario = new javax.swing.JComboBox<>();
        btnVenderPasaje = new javax.swing.JButton();
        btnEmitirRecibo = new javax.swing.JButton();
        pnlRegistrado = new javax.swing.JPanel();
        lblDNIRegistrado = new javax.swing.JLabel();
        txtDniRegistrado = new javax.swing.JTextField();
        pnlNoRegistrado = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        lblNombreNoR = new javax.swing.JLabel();
        lblApellidoNoR = new javax.swing.JLabel();
        lblDniNoR = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        rbRegistrado = new javax.swing.JRadioButton();
        rbNoRegistrado = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        cbColectivos = new javax.swing.JComboBox<>();

        lblTitulo.setText("GESTION DE VENTA DE PASAJES");

        lblRuta.setText("RUTA");

        lblHorario.setText("HORARIO");

        btnVenderPasaje.setText("VENDER PASAJE");

        btnEmitirRecibo.setText("EMITIR RECIBO DE VENTA");

        lblDNIRegistrado.setText("DNI");

        txtDniRegistrado.setEnabled(false);

        javax.swing.GroupLayout pnlRegistradoLayout = new javax.swing.GroupLayout(pnlRegistrado);
        pnlRegistrado.setLayout(pnlRegistradoLayout);
        pnlRegistradoLayout.setHorizontalGroup(
            pnlRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistradoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDNIRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDniRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        pnlRegistradoLayout.setVerticalGroup(
            pnlRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistradoLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(pnlRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDNIRegistrado)
                    .addComponent(txtDniRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72))
        );

        txtNombre.setEnabled(false);

        txtDni.setEnabled(false);

        lblNombreNoR.setText("Nombre");

        lblApellidoNoR.setText("Apellido");

        lblDniNoR.setText("DNI");

        txtApellido.setEnabled(false);

        javax.swing.GroupLayout pnlNoRegistradoLayout = new javax.swing.GroupLayout(pnlNoRegistrado);
        pnlNoRegistrado.setLayout(pnlNoRegistradoLayout);
        pnlNoRegistradoLayout.setHorizontalGroup(
            pnlNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoRegistradoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblApellidoNoR)
                    .addComponent(lblNombreNoR)
                    .addComponent(lblDniNoR, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(txtNombre)
                    .addComponent(txtDni))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlNoRegistradoLayout.setVerticalGroup(
            pnlNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoRegistradoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreNoR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellidoNoR)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDniNoR)))
        );

        buttonGroup1.add(rbRegistrado);
        rbRegistrado.setText("PASAJERO REGISTRADO");

        buttonGroup1.add(rbNoRegistrado);
        rbNoRegistrado.setText("PASAJERO NO REGISTRADO");
        rbNoRegistrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNoRegistradoActionPerformed(evt);
            }
        });

        jLabel1.setText("COLECTIVO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlNoRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbNoRegistrado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbRegistrado)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(pnlRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbRuta, 0, 253, Short.MAX_VALUE)
                            .addComponent(cbHorario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHorario)
                            .addComponent(lblRuta)
                            .addComponent(jLabel1))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(lblTitulo))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbColectivos, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVenderPasaje)
                .addGap(66, 66, 66)
                .addComponent(btnEmitirRecibo)
                .addGap(78, 78, 78))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblRuta)
                        .addGap(26, 26, 26)
                        .addComponent(lblHorario))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbColectivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbNoRegistrado)
                    .addComponent(rbRegistrado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlNoRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEmitirRecibo)
                    .addComponent(btnVenderPasaje))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbNoRegistradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoRegistradoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbNoRegistradoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnEmitirRecibo;
    public javax.swing.JButton btnVenderPasaje;
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JComboBox<Colectivo> cbColectivos;
    public javax.swing.JComboBox<Horario> cbHorario;
    public javax.swing.JComboBox<Ruta> cbRuta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblApellidoNoR;
    private javax.swing.JLabel lblDNIRegistrado;
    private javax.swing.JLabel lblDniNoR;
    private javax.swing.JLabel lblHorario;
    private javax.swing.JLabel lblNombreNoR;
    private javax.swing.JLabel lblRuta;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlNoRegistrado;
    private javax.swing.JPanel pnlRegistrado;
    public javax.swing.JRadioButton rbNoRegistrado;
    public javax.swing.JRadioButton rbRegistrado;
    public javax.swing.JTextField txtApellido;
    public javax.swing.JTextField txtDni;
    public javax.swing.JTextField txtDniRegistrado;
    public javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
