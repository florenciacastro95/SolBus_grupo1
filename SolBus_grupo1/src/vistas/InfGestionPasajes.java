package vistas;

import entidades.*;
import vistas.*;
import accesoDatos.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class InfGestionPasajes extends javax.swing.JInternalFrame {

    ArrayList<Ruta> rutas = new ArrayList<>();
    ArrayList<Colectivo> colectivos = new ArrayList<>();
    RutaData rd = new RutaData();
    HorarioData hd = new HorarioData();
    ColectivoData cd = new ColectivoData();
    PasajeData pd = new PasajeData();
    private DefaultTableModel model;

    public InfGestionPasajes() {

        initComponents();
        model = new DefaultTableModel();
        rutas = (ArrayList<Ruta>) rd.listarRutasDisponibles();
        colectivos = (ArrayList<Colectivo>) cd.listarColectivos();
        cargarCbRuta(rutas);
        cargarCbHorario();
        cargarCbColectivo(colectivos);

    }

    private void cargarCbColectivo(ArrayList<Colectivo> colectivos) {

        for (Colectivo cole : colectivos) {
            cbColectivos.addItem(cole);
        }
    }

    private void cargarCbRuta(ArrayList<Ruta> rutas) {

        for (Ruta ruta : rutas) {
            cbRuta.addItem(ruta);
        }
    }

    private void cargarCbHorario() {
        System.out.println(cbRuta.getSelectedItem());
        cargarCbHorario((Ruta) cbRuta.getSelectedItem());

    }

    private void cargarCbHorario(Ruta ruta) {
        ArrayList<Horario> horarios = new ArrayList<>();

        horarios = (ArrayList<Horario>) hd.listarHorariosPorRuta(ruta);
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
        jComboBox1 = new javax.swing.JComboBox<>();
        spTabla = new javax.swing.JScrollPane();
        tblAsientos = new javax.swing.JTable();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Gill Sans MT", 0, 24)); // NOI18N
        lblTitulo.setText("GESTION DE VENTA DE PASAJES");
        getContentPane().add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 430, 50));

        lblRuta.setText("Ruta");
        getContentPane().add(lblRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        lblHorario.setText("Horario");
        getContentPane().add(lblHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        cbRuta.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(cbRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 170, -1));

        getContentPane().add(cbHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 170, -1));

        btnVenderPasaje.setText("VENDER PASAJE");
        getContentPane().add(btnVenderPasaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 330, -1, 60));

        btnEmitirRecibo.setText("EMITIR RECIBO DE VENTA");
        getContentPane().add(btnEmitirRecibo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 410, 130, 70));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlRegistradoLayout.setVerticalGroup(
            pnlRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistradoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDNIRegistrado)
                    .addComponent(txtDniRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getContentPane().add(pnlRegistrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 250, -1, 60));

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

        getContentPane().add(pnlNoRegistrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, -1, -1));

        buttonGroup1.add(rbRegistrado);
        rbRegistrado.setText("PASAJERO REGISTRADO");
        getContentPane().add(rbRegistrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, -1, -1));

        buttonGroup1.add(rbNoRegistrado);
        rbNoRegistrado.setText("PASAJERO NO REGISTRADO");
        getContentPane().add(rbNoRegistrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, -1, -1));

        jLabel1.setText("Colectivo");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        getContentPane().add(cbColectivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 170, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, -1, -1));

        tblAsientos.setModel(new javax.swing.table.DefaultTableModel(
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
        spTabla.setViewportView(tblAsientos);

        getContentPane().add(spTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 190, 193));
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, -1, -1));

        jLabel2.setText("Precio");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 40, 20));

        jLabel3.setText("Fecha");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnEmitirRecibo;
    public javax.swing.JButton btnVenderPasaje;
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JComboBox<Colectivo> cbColectivos;
    public javax.swing.JComboBox<Horario> cbHorario;
    public javax.swing.JComboBox<Ruta> cbRuta;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    public javax.swing.JScrollPane spTabla;
    public javax.swing.JTable tblAsientos;
    public javax.swing.JTextField txtApellido;
    public javax.swing.JTextField txtDni;
    public javax.swing.JTextField txtDniRegistrado;
    public javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
