package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entidades.*;
import vistas.*;
import accesoDatos.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;

//HAY QUE AGREGAR VALIDACIÓN DE PASAJES SEGUN CAPCIDAD DE COLECTIVO Y ASIENTOS DISPONIBLES
public class ctrlCargaPasajes implements ActionListener, ItemListener {

    private Pasaje pasaje;
    private PasajeData pasajeData;
    private ColectivoData colectivoData;
    private InfGestionPasajes pasajeVista;
    private PasajeroData pasajeroData;

    public ctrlCargaPasajes(Pasaje pasaje, PasajeData pasajeData, InfGestionPasajes pasajeVista) {
        this.pasaje = pasaje;
        this.pasajeData = pasajeData;
        this.pasajeVista = pasajeVista;
        colectivoData = new ColectivoData();
        pasajeData = new PasajeData();
        pasajeroData = new PasajeroData();
        pasajeVista.btnVenderPasaje.addActionListener(this);
        pasajeVista.btnEmitirRecibo.addActionListener(this);
        pasajeVista.cbRuta.addItemListener(this);
        pasajeVista.rbRegistrado.addActionListener(this);
        pasajeVista.rbNoRegistrado.addActionListener(this);
        armarCabeceraTblAsientos();
        cargarTblAsientos();
        poneteBonito();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //mirar la capacidad de el colectivo
        if (e.getSource() == pasajeVista.btnVenderPasaje) {
            Pasaje pasaje;
            Pasajero pasajero;
            LocalTime horita = null;
            boolean bandera = true;

            Colectivo colectivo = (Colectivo) pasajeVista.cbColectivos.getSelectedItem();
            String nombre = "", apellido = "", dni = "";

            if (validarString(pasajeVista.txtApellido.getText())) {
                apellido = pasajeVista.txtApellido.getText();
            } else {
                bandera = false;
                JOptionPane.showMessageDialog(null, "Apellido inválido");
            }
            if (validarString(pasajeVista.txtNombre.getText())) {
                nombre = pasajeVista.txtNombre.getText();
            } else {
                bandera = false;
                JOptionPane.showMessageDialog(null, "Nombre inválido");
            }
            if (validarEnteros(pasajeVista.txtDni.getText()) && validarDniTam(pasajeVista.txtDni.getText().length())) {
                dni = pasajeVista.txtDni.getText();
            } else if (validarEnteros(pasajeVista.txtDni.getText()) && !validarDniTam(pasajeVista.txtDni.getText().length())) {
                bandera = false;
                JOptionPane.showMessageDialog(null, "El DNI debe contener 7 u 8 dígitos");
            } else if (!validarEnteros(pasajeVista.txtDni.getText())) {
                bandera = false;
                JOptionPane.showMessageDialog(null, "El DNI solo debe contener números");
            } else {
                bandera = false;
                JOptionPane.showMessageDialog(null, "El DNI solo debe contener 7 u 8 dígitos. No se admiten letras");
            }
            if (pasajeVista.cbHorario.getSelectedItem() != null) {
                horita = ((Horario) pasajeVista.cbHorario.getSelectedItem()).getHoraSalida();
            } else {
                bandera = false;
                JOptionPane.showMessageDialog(null, "No se puede vender un pasaje sin horario");
            }
            if (bandera && horita != null) {
                pasajero = new Pasajero(nombre, apellido, dni, null, null);
                pasajeroData.guardarPasajero(pasajero);
                pasaje = new Pasaje(pasajero, colectivo, (Ruta) pasajeVista.cbRuta.getSelectedItem(), LocalDate.now(), horita, 14, 0);
                pasajeData.venderPasaje(pasaje);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo vender pasaje");
            }
        }

        if (e.getSource() == pasajeVista.rbNoRegistrado) {

            pasajeVista.txtDni.setEnabled(true);
            pasajeVista.txtApellido.setEnabled(true);
            pasajeVista.txtNombre.setEnabled(true);
            pasajeVista.txtDniRegistrado.setEnabled(false);

        } else if (e.getSource() == pasajeVista.rbRegistrado) {
            pasajeVista.txtDni.setEnabled(false);
            pasajeVista.txtApellido.setEnabled(false);
            pasajeVista.txtNombre.setEnabled(false);
            pasajeVista.txtDniRegistrado.setEnabled(true);
        }

    }

    @Override
    public void itemStateChanged(ItemEvent ie) {

        if (ie.getStateChange() == ItemEvent.SELECTED) {
            if (ie.getSource() == pasajeVista.cbRuta) {
                pasajeVista.cbHorario.removeAllItems();
                HorarioData hd = new HorarioData();
                Ruta itemSeleccionado = (Ruta) pasajeVista.cbRuta.getSelectedItem();
                ArrayList<Horario> horarios = new ArrayList<>();

                horarios = (ArrayList<Horario>) hd.listarHorariosPorRuta(itemSeleccionado);
                for (Horario horario : horarios) {
                    System.out.println(horario.toString());
                    pasajeVista.cbHorario.addItem(horario);

                }
                cargarTblAsientos();
                if (horarios.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay horarios activos para esta ruta. Agregue horarios");
                }
            }
            if (ie.getSource() == pasajeVista.rbNoRegistrado) {
                pasajeVista.txtDni.setEnabled(true);
                pasajeVista.txtApellido.setEnabled(true);
                pasajeVista.txtNombre.setEnabled(true);
            }
        }
    }

    public boolean validarEnteros(String s) {

        String regExp = "^-?\\d+$";

        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(s);

        return m.matches();
    }

    //cambié expresión regular para que acepte todos las tildes por si metemos
    //nombres en otros idiomas latinos como apellido Müller o nombre François
    public boolean validarString(String s) {
        String regExp = "^[\\p{L}\\p{M} .'-]+$";

        return s.matches(regExp);
        //estoy perdiendo salud mental con este paquete de control
    }

    public boolean validarDniTam(int tam) {
        return tam == 8 || tam == 7;
    }

    private void armarCabeceraTblAsientos() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"V", "P", "P", "V"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que las celdas no sean editables
            }
        };
        pasajeVista.tblAsientos.setModel(model);
        pasajeVista.tblAsientos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Selección de una celda a la vez
        pasajeVista.tblAsientos.setCellSelectionEnabled(true);
    }

    private void cargarTblAsientos() {
        DefaultTableModel model = (DefaultTableModel) pasajeVista.tblAsientos.getModel();
        model.setRowCount(0);

        int numAsiento = 1;
        for (int fila = 0; fila < 8; fila++) {
            model.addRow(new Object[]{
                numAsiento++, numAsiento++, numAsiento++, numAsiento++
            });
        }
        try {
            ArrayList<Integer> asientosOcupados = (ArrayList<Integer>) pasajeData.listarAsientosOcupadosPorViaje((Ruta) pasajeVista.cbRuta.getSelectedItem(), (Colectivo) pasajeVista.cbColectivos.getSelectedItem(),
                    LocalDate.of(2024, 6, 6), ((Horario) pasajeVista.cbHorario.getSelectedItem()).getHoraSalida());
            for (int fila = 0; fila < model.getRowCount(); fila++) {
                for (int columna = 0; columna < model.getColumnCount(); columna++) {
                    Integer asientoActual = (Integer) model.getValueAt(fila, columna);
                    System.out.println(asientoActual);
                    if (asientosOcupados.contains(asientoActual)) {
                        model.setValueAt("ocup", fila, columna);
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e);
        }

    }

    public void poneteBonito() {
            // Borde del JInternalFrame
    pasajeVista.setBorder(BorderFactory.createLineBorder(new Color(41, 37, 28), 3)); // Marrón oscuro

    // Cambiar el color de fondo del JInternalFrame
    pasajeVista.getContentPane().setBackground(new Color(231, 221, 211)); // Beige claro

    // Cambiar el color de los botones
    pasajeVista.btnVenderPasaje.setBackground(new Color(41, 37, 28)); // Rosa claro
    pasajeVista.btnEmitirRecibo.setBackground(new Color(41, 37, 28)); // Marrón oscuro
    pasajeVista.btnVenderPasaje.setForeground(Color.white); // Texto blanco
    pasajeVista.btnEmitirRecibo.setForeground(Color.white); // Texto blanco

    // Cambiar el color del título
    pasajeVista.lblTitulo.setForeground(new Color(41, 37, 28)); // Marrón oscuro

    // Cambiar el color de la tabla y del scroll pane
    pasajeVista.spTabla.setBackground(new Color(231, 221, 211)); // Beige claro
    pasajeVista.tblAsientos.setBackground(new Color(192, 153, 139)); // Rosa claro

    // Cambiar el color del texto
    pasajeVista.lblRuta.setForeground(new Color(41, 37, 28)); // Marrón oscuro
    pasajeVista.lblHorario.setForeground(new Color(41, 37, 28)); // Marrón oscuro
    pasajeVista.lblNombreNoR.setForeground(new Color(41, 37, 28)); // Marrón oscuro
    pasajeVista.lblApellidoNoR.setForeground(new Color(41, 37, 28)); // Marrón oscuro
    pasajeVista.lblDniNoR.setForeground(new Color(41, 37, 28)); // Marrón oscuro
    pasajeVista.lblDNIRegistrado.setForeground(new Color(41, 37, 28)); // Marrón oscuro
    pasajeVista.lblPrecio.setForeground(new Color(41, 37, 28)); // Marrón oscuro
    pasajeVista.lblFecha.setForeground(new Color(41, 37, 28)); // Marrón oscuro
    pasajeVista.lblColectivo.setForeground(new Color(41, 37, 28)); // Marrón oscuro

    // Cambiar el color de fondo de los paneles
    pasajeVista.pnlNoRegistrado.setBackground(new Color(231, 221, 211)); // Beige claro
    pasajeVista.pnlRegistrado.setBackground(new Color(231, 221, 211)); // Beige claro

    // Cambiar el color de fondo de los text fields
    pasajeVista.txtNombre.setBackground(new Color(192, 153, 139)); // Rosa claro
    pasajeVista.txtApellido.setBackground(new Color(192, 153, 139)); // Rosa claro
    pasajeVista.txtDni.setBackground(new Color(192, 153, 139)); // Rosa claro
    pasajeVista.txtDniRegistrado.setBackground(new Color(192, 153, 139)); // Rosa claro

    // Cambiar el color de fondo de los combos
    pasajeVista.cbRuta.setBackground(new Color(231, 221, 211)); // Beige claro
    pasajeVista.cbHorario.setBackground(new Color(231, 221, 211)); // Beige claro
    pasajeVista.cbColectivos.setBackground(new Color(231, 221, 211)); // Beige claro
    pasajeVista.cbPrecios.setBackground(new Color(231, 221, 211)); // Beige claro

    // Cambiar el color de los botones de radio
    pasajeVista.rbRegistrado.setForeground(new Color(41, 37, 28)); // Marrón oscuro
    pasajeVista.rbNoRegistrado.setForeground(new Color(41, 37, 28)); // Marrón oscuro


        // Establecer un renderizador personalizado para la tabla
        pasajeVista.tblAsientos.setDefaultRenderer(Object.class, new PoneteBonitaTablita());
    }

    class PoneteBonitaTablita extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBorder(noFocusBorder);
            setFont(new java.awt.Font("Arial", 0, 14)); // Cambiar el font de las celdas de la tabla
            setHorizontalAlignment(SwingConstants.CENTER); // Alinear el contenido al centro
            return this;
        }

    }
}
