package vistas;

import entidades.*;
import vistas.*;
import accesoDatos.*;
import java.awt.Color;
import java.awt.Component;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class InfGestionPasajes extends javax.swing.JInternalFrame {

    ArrayList<Ruta> rutas = new ArrayList<>();
    ArrayList<Colectivo> colectivos = new ArrayList<>();
    RutaData rd = new RutaData();
    HorarioData hd = new HorarioData();
    ColectivoData cd = new ColectivoData();
    PasajeData pd = new PasajeData();
    private DefaultTableModel model;

    public InfGestionPasajes() throws UnsupportedLookAndFeelException {

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
        lblColectivo = new javax.swing.JLabel();
        cbColectivos = new javax.swing.JComboBox<>();
        cbPrecios = new javax.swing.JComboBox<>();
        spTabla = new javax.swing.JScrollPane();
        tblAsientos = new javax.swing.JTable();
        dateChooser = new com.toedter.calendar.JDateChooser();
        lblPrecio = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Gill Sans MT", 1, 24)); // NOI18N
        lblTitulo.setText("GESTION DE VENTA DE PASAJES");
        getContentPane().add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 410, 50));

        lblRuta.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        lblRuta.setText("Ruta");
        getContentPane().add(lblRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 50, 40));

        lblHorario.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        lblHorario.setText("Horario");
        getContentPane().add(lblHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 60, 40));

        cbRuta.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(cbRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 200, -1));

        getContentPane().add(cbHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 200, -1));

        btnVenderPasaje.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        btnVenderPasaje.setText("VENDER PASAJE");
        getContentPane().add(btnVenderPasaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 330, 150, 60));

        btnEmitirRecibo.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        btnEmitirRecibo.setText("EMITIR RECIBO");
        getContentPane().add(btnEmitirRecibo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 400, 150, 60));

        lblDNIRegistrado.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
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

        getContentPane().add(pnlRegistrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 210, 60));

        txtNombre.setEnabled(false);

        txtDni.setEnabled(false);

        lblNombreNoR.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        lblNombreNoR.setText("Nombre");

        lblApellidoNoR.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        lblApellidoNoR.setText("Apellido");

        lblDniNoR.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
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

        getContentPane().add(pnlNoRegistrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 210, -1));

        buttonGroup1.add(rbRegistrado);
        rbRegistrado.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        rbRegistrado.setText("PASAJERO REGISTRADO");
        getContentPane().add(rbRegistrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, -1, -1));

        buttonGroup1.add(rbNoRegistrado);
        rbNoRegistrado.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        rbNoRegistrado.setText("PASAJERO NO REGISTRADO");
        getContentPane().add(rbNoRegistrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, -1, -1));

        lblColectivo.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        lblColectivo.setText("Colectivo");
        getContentPane().add(lblColectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 80, 40));

        getContentPane().add(cbColectivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 200, -1));

        cbPrecios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(cbPrecios, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 200, -1));

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

        getContentPane().add(spTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 190, 160));
        getContentPane().add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 259, 210, 30));

        lblPrecio.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        lblPrecio.setText("Precio");
        getContentPane().add(lblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 80, 40));

        lblFecha.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        lblFecha.setText("Fecha");
        getContentPane().add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 60, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnEmitirRecibo;
    public javax.swing.JButton btnVenderPasaje;
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JComboBox<Colectivo> cbColectivos;
    public javax.swing.JComboBox<Horario> cbHorario;
    public javax.swing.JComboBox<String> cbPrecios;
    public javax.swing.JComboBox<Ruta> cbRuta;
    private com.toedter.calendar.JDateChooser dateChooser;
    public javax.swing.JLabel lblApellidoNoR;
    public javax.swing.JLabel lblColectivo;
    public javax.swing.JLabel lblDNIRegistrado;
    public javax.swing.JLabel lblDniNoR;
    public javax.swing.JLabel lblFecha;
    public javax.swing.JLabel lblHorario;
    public javax.swing.JLabel lblNombreNoR;
    public javax.swing.JLabel lblPrecio;
    public javax.swing.JLabel lblRuta;
    public javax.swing.JLabel lblTitulo;
    public javax.swing.JPanel pnlNoRegistrado;
    public javax.swing.JPanel pnlRegistrado;
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
