package vistas;

import entidades.*;
import vistas.*;
import accesoDatos.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class InfGestionPasajes extends javax.swing.JInternalFrame {

    ArrayList<Ruta> rutas = new ArrayList<>();
    ArrayList<Colectivo> colectivos = new ArrayList<>();
    RutaData rd = new RutaData();
    HorarioData hd = new HorarioData();
    ColectivoData cd = new ColectivoData();
    private DefaultTableModel model;

    public InfGestionPasajes() {

        initComponents();
        model = new DefaultTableModel();
        rutas = (ArrayList<Ruta>) rd.listarRutasDisponibles();
        colectivos = (ArrayList<Colectivo>) cd.listarColectivos();
        cargarCbRuta(rutas);
        cargarCbHorario();
        cargarCbColectivo(colectivos);
        armarCabeceraTblAsientos();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAsientos = new javax.swing.JTable();

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
                .addContainerGap(465, Short.MAX_VALUE))
        );
        pnlRegistradoLayout.setVerticalGroup(
            pnlRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistradoLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(pnlRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDNIRegistrado)
                    .addComponent(txtDniRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(150, Short.MAX_VALUE))
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

        jLabel1.setText("COLECTIVO");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
        jScrollPane1.setViewportView(tblAsientos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbRuta, 0, 155, Short.MAX_VALUE)
                            .addComponent(cbHorario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHorario)
                            .addComponent(lblRuta)
                            .addComponent(jLabel1))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(lblTitulo))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbColectivos, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pnlNoRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rbNoRegistrado)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(btnVenderPasaje)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(btnEmitirRecibo))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(pnlRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rbRegistrado)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void armarCabeceraTblAsientos() {
        ArrayList<Object> filaCabecera = new ArrayList<>();
        filaCabecera.add("Ventana");
        filaCabecera.add("Pasillo");
        filaCabecera.add("Pasillo");
        filaCabecera.add("Ventana");

        for (Object i : filaCabecera) {
            model.addColumn(i);
        }
        tblAsientos.setModel(model);
    }
    
    private void cargarTblAsientos(){
        //tengo que recuperar el colectivo
        //t
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnEmitirRecibo;
    public javax.swing.JButton btnVenderPasaje;
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JComboBox<Colectivo> cbColectivos;
    public javax.swing.JComboBox<Horario> cbHorario;
    public javax.swing.JComboBox<Ruta> cbRuta;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
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
    private javax.swing.JTable tblAsientos;
    public javax.swing.JTextField txtApellido;
    public javax.swing.JTextField txtDni;
    public javax.swing.JTextField txtDniRegistrado;
    public javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
