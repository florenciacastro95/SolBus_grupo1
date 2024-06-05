
package vistas;
import entidades.*;
import vistas.*;
import accesoDatos.*;
import java.util.ArrayList;

public class InfGestionPasajes extends javax.swing.JInternalFrame {

    ArrayList<Ruta> rutas = new ArrayList<>();
    RutaData rd=new RutaData();
    HorarioData hd=new HorarioData();
    public InfGestionPasajes() {
        
        initComponents();
        rutas=(ArrayList<Ruta>) rd.listarRutasDisponibles();
        cargarCbRuta(rutas);
        cargarCbHorario();
        
        
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
        pnlNoRegistrado = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        lblNombreNoR = new javax.swing.JLabel();
        lblApellidoNoR = new javax.swing.JLabel();
        lblDniNoR = new javax.swing.JLabel();
        rbRegistrado = new javax.swing.JRadioButton();
        pnlRegistrado = new javax.swing.JPanel();
        lblDNIRegistrado = new javax.swing.JLabel();
        txtDniRegistrado = new javax.swing.JTextField();
        rbNoRegistrado = new javax.swing.JRadioButton();

        lblTitulo.setText("GESTION DE VENTA DE PASAJES");

        lblRuta.setText("RUTA");

        lblHorario.setText("HORARIO");

        btnVenderPasaje.setText("VENDER PASAJE");

        btnEmitirRecibo.setText("EMITIR RECIBO DE VENTA");

        lblNombreNoR.setText("Nombre");

        lblApellidoNoR.setText("Apellido");

        lblDniNoR.setText("DNI");

        buttonGroup1.add(rbRegistrado);
        rbRegistrado.setText("PASAJERO NO REGISTRADO");

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
                    .addComponent(txtApellido)
                    .addComponent(txtDni)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(99, Short.MAX_VALUE))
            .addGroup(pnlNoRegistradoLayout.createSequentialGroup()
                .addComponent(rbRegistrado)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlNoRegistradoLayout.setVerticalGroup(
            pnlNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoRegistradoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(rbRegistrado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreNoR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellidoNoR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDniNoR))
                .addContainerGap())
        );

        lblDNIRegistrado.setText("DNI");

        buttonGroup1.add(rbNoRegistrado);
        rbNoRegistrado.setText("PASAJERO NO REGISTRADO");

        javax.swing.GroupLayout pnlRegistradoLayout = new javax.swing.GroupLayout(pnlRegistrado);
        pnlRegistrado.setLayout(pnlRegistradoLayout);
        pnlRegistradoLayout.setHorizontalGroup(
            pnlRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistradoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDNIRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDniRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRegistradoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(rbNoRegistrado))
        );
        pnlRegistradoLayout.setVerticalGroup(
            pnlRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistradoLayout.createSequentialGroup()
                .addComponent(rbNoRegistrado)
                .addGap(18, 18, 18)
                .addGroup(pnlRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDNIRegistrado)
                    .addComponent(txtDniRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(btnVenderPasaje)
                        .addGap(52, 52, 52)
                        .addComponent(btnEmitirRecibo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHorario)
                            .addComponent(lblRuta))
                        .addGap(25, 25, 25)
                        .addComponent(lblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlNoRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbRuta, 0, 253, Short.MAX_VALUE)
                            .addComponent(cbHorario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(202, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(pnlRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(pnlNoRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVenderPasaje)
                    .addComponent(btnEmitirRecibo))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnEmitirRecibo;
    public javax.swing.JButton btnVenderPasaje;
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JComboBox<Horario> cbHorario;
    public javax.swing.JComboBox<Ruta> cbRuta;
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
