package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entidades.*;
import vistas.*;
import accesoDatos.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
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
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

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
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Ventana", "Pasillo", "Pasillo", "Ventana"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que las celdas no sean editables
            }
        };

        pasajeVista.tblAsientos.setModel(model);
        pasajeVista.tblAsientos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pasajeVista.tblAsientos.setCellSelectionEnabled(true);

        try {
            JTableHeader header = pasajeVista.tblAsientos.getTableHeader();
            Font montserratFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/Montserrat-Regular.ttf")).deriveFont(Font.BOLD, 14);

            header.setFont(montserratFont);
            header.setForeground(new Color(41, 37, 28));
            header.setBackground(new Color(231, 221, 211));

            header.setOpaque(true);
            header.setBackground(new Color(192, 153, 139));

            Dimension headerSize = header.getPreferredSize();
            headerSize.height = 36;
            header.setPreferredSize(headerSize);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        //NO TE MOVAS CARAJO
        pasajeVista.tblAsientos.getTableHeader().setReorderingAllowed(false);
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
                        model.setValueAt("Ocup.", fila, columna);
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e);
        }

    }

    //PALETA DE COLORES EN:https://paletadecolores.com.ar/paleta/e7ddd3/c0c2bd/9c9994/29251c/e6aa9f/
    //    #174D51
    //    #0c2521
    //    #D48931
    //    #6F1C00
    public void poneteBonito() {

        pasajeVista.setSize(new Dimension(570, 620));

        pasajeVista.setBorder(BorderFactory.createLineBorder(new Color(41, 37, 28), 3));

        //FONDITO INTERNAAAAL
        pasajeVista.getContentPane().setBackground(new Color(231, 221, 211));

        //MIRA ESOS BUTTONS PAPA
        pasajeVista.btnVenderPasaje.setBackground(new Color(41, 37, 28));
        pasajeVista.btnEmitirRecibo.setBackground(new Color(41, 37, 28));
        pasajeVista.btnVenderPasaje.setForeground(Color.white);
        pasajeVista.btnEmitirRecibo.setForeground(Color.white);
        //TITULO
        pasajeVista.lblTitulo.setForeground(new Color(41, 37, 28));

        //TABLITA BACK
        pasajeVista.spTabla.setBackground(new Color(231, 221, 211));
        pasajeVista.tblAsientos.setBackground(new Color(192, 153, 139)); 
        
        //LABELS
        pasajeVista.lblRuta.setForeground(new Color(41, 37, 28)); 
        pasajeVista.lblHorario.setForeground(new Color(41, 37, 28));
        pasajeVista.lblNombreNoR.setForeground(new Color(41, 37, 28));
        pasajeVista.lblApellidoNoR.setForeground(new Color(41, 37, 28));
        pasajeVista.lblDniNoR.setForeground(new Color(41, 37, 28));
        pasajeVista.lblDNIRegistrado.setForeground(new Color(41, 37, 28));
        pasajeVista.lblPrecio.setForeground(new Color(41, 37, 28));
        pasajeVista.lblFecha.setForeground(new Color(41, 37, 28));
        pasajeVista.lblColectivo.setForeground(new Color(41, 37, 28)); 

        //PANELS
        pasajeVista.pnlNoRegistrado.setBackground(new Color(231, 221, 211));
        pasajeVista.pnlRegistrado.setBackground(new Color(231, 221, 211));

        //TEXTFILEDS
        pasajeVista.txtNombre.setBackground(new Color(192, 153, 139));
        pasajeVista.txtApellido.setBackground(new Color(192, 153, 139));
        pasajeVista.txtDni.setBackground(new Color(192, 153, 139)); 
        pasajeVista.txtDniRegistrado.setBackground(new Color(192, 153, 139));

        //COMBOBITCHES
        pasajeVista.cbRuta.setBackground(new Color(231, 221, 211)); 
        pasajeVista.cbHorario.setBackground(new Color(231, 221, 211)); 
        pasajeVista.cbColectivos.setBackground(new Color(231, 221, 211));
        pasajeVista.cbPrecios.setBackground(new Color(231, 221, 211));

        //RADIOB
        pasajeVista.rbRegistrado.setForeground(new Color(41, 37, 28)); 
        pasajeVista.rbNoRegistrado.setForeground(new Color(41, 37, 28));
        //text field del JDATE
        JTextField dateTextField = ((JTextField) pasajeVista.dateChooser.getDateEditor().getUiComponent());
        dateTextField.setForeground(new Color(231, 221, 211));
        dateTextField.setBackground(new Color(192, 153, 139));
        //el render de la tabla
        pasajeVista.tblAsientos.setDefaultRenderer(Object.class, new PoneteBonitaTablita());

        //CENTREMOS EL TITULO
        pasajeVista.lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            Font montserratFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/Montserrat-Regular.ttf")).deriveFont(Font.PLAIN, 14);
            Font montserratFontTitulo = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/Montserrat-Regular.ttf")).deriveFont(Font.BOLD, 18);

            pasajeVista.lblTitulo.setFont(montserratFontTitulo);
            pasajeVista.lblRuta.setFont(montserratFont);
            pasajeVista.lblHorario.setFont(montserratFont);
            pasajeVista.lblNombreNoR.setFont(montserratFont);
            pasajeVista.lblApellidoNoR.setFont(montserratFont);
            pasajeVista.lblDniNoR.setFont(montserratFont);
            pasajeVista.lblDNIRegistrado.setFont(montserratFont);
            pasajeVista.lblPrecio.setFont(montserratFont);
            pasajeVista.lblFecha.setFont(montserratFont);
            pasajeVista.lblColectivo.setFont(montserratFont);
            pasajeVista.btnVenderPasaje.setFont(montserratFont);
            pasajeVista.btnEmitirRecibo.setFont(montserratFont);
            pasajeVista.txtNombre.setFont(montserratFont);
            pasajeVista.txtApellido.setFont(montserratFont);
            pasajeVista.txtDni.setFont(montserratFont);
            pasajeVista.txtDniRegistrado.setFont(montserratFont);
            pasajeVista.cbRuta.setFont(montserratFont);
            pasajeVista.cbHorario.setFont(montserratFont);
            pasajeVista.cbColectivos.setFont(montserratFont);
            pasajeVista.cbPrecios.setFont(montserratFont);
            pasajeVista.rbRegistrado.setFont(montserratFont);
            pasajeVista.rbNoRegistrado.setFont(montserratFont);
            dateTextField.setFont(montserratFont);
            
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    class PoneteBonitaTablita extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBorder(noFocusBorder);
            setFont(new java.awt.Font("Arial", 0, 18));
            setHorizontalAlignment(SwingConstants.CENTER);
            table.setRowHeight(28);
            if (isSelected) {
                setBackground(new Color(41, 37, 28));
                setForeground(new Color(231, 221, 211));
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }

            return this;
        }

    }
}
