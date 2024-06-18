package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entidades.*;
import vistas.*;
import accesoDatos.*;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
//todo para agregar boludeces de estilos :B
import java.time.ZoneId;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
//Mira como usamos esos recibos :b
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
//pa crear carpetas para los recibos :B
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//HAY QUE AGREGAR VALIDACIÓN DE PASAJES SEGUN CAPCIDAD DE COLECTIVO Y ASIENTOS DISPONIBLES
public class ctrlCargaPasajes implements ActionListener, ItemListener {

    private Pasaje pasaje;
    private PasajeData pasajeData;
    private ColectivoData colectivoData;
    private InfGestionPasajes pasajeVista;
    private PasajeroData pasajeroData;
    private Ruta r;
    private double precio;
    private int multiplicador = 40;

    /*
     *********************
     *****CONSTRUCTOR*****
     *********************
     */
    public ctrlCargaPasajes(Pasaje pasaje, PasajeData pasajeData, InfGestionPasajes pasajeVista) {
        this.pasaje = pasaje;
        this.pasajeData = pasajeData;
        this.pasajeVista = pasajeVista;
        colectivoData = new ColectivoData();
        pasajeData = new PasajeData();
        pasajeroData = new PasajeroData();
        pasajeVista.btnVenderPasaje.addActionListener(this);
        pasajeVista.cbRuta.addItemListener(this);
        pasajeVista.cbColectivos.addItemListener(this);
        pasajeVista.cbHorario.addItemListener(this);
        pasajeVista.rbRegistrado.addActionListener(this);
        pasajeVista.rbNoRegistrado.addActionListener(this);
        pasajeVista.btnAnularPasaje.addActionListener(this);
        armarCabeceraTblAsientos();
        cargarTblAsientos();
        poneteBonito();
        pasajeVista.dateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    cargarTblAsientos();
                }
            }
        });

        if (pasajeVista.cbRuta != null && (Ruta) pasajeVista.cbRuta.getSelectedItem() != null) {
            r = (Ruta) pasajeVista.cbRuta.getSelectedItem();
            precio = r.getDuracion().getMinute();
            calcularPrecio();
        }

    }

    @Override

    /*
     **************************
     *****ACTION PERFORMED*****
     **************************
     */
    public void actionPerformed(ActionEvent e) {
        //mirar la capacidad de el colectivo

        if (e.getSource() == pasajeVista.btnVenderPasaje) {

            venderPasaje();
        }

        if (e.getSource()
                == pasajeVista.btnAnularPasaje) {

            anularPasaje();

            cargarTblAsientos();
        }

        /**
         *******************************
         *****PASAJERO REG/O NO REG
         */
        if (e.getSource()
                == pasajeVista.rbNoRegistrado) {

            if (pasajeVista.cbRuta != null && (Ruta) pasajeVista.cbRuta.getSelectedItem() != null) {

                calcularPrecio();
            }

            pasajeVista.txtDni.setEnabled(true);
            pasajeVista.txtApellido.setEnabled(true);
            pasajeVista.txtNombre.setEnabled(true);
            pasajeVista.txtDniRegistrado.setEnabled(false);

        } else if (e.getSource()
                == pasajeVista.rbRegistrado) {
            if (pasajeVista.cbRuta != null && (Ruta) pasajeVista.cbRuta.getSelectedItem() != null) {

                calcularPrecio();
            }
            pasajeVista.txtDni.setEnabled(false);
            pasajeVista.txtApellido.setEnabled(false);
            pasajeVista.txtNombre.setEnabled(false);
            pasajeVista.txtDniRegistrado.setEnabled(true);
        }

    }

    /*
     **************************
     ***ITEM CHANGE PARA EL ***
     *** COMBO DE HORARIO******
     **************************
     */
    @Override
    public void itemStateChanged(ItemEvent ie) {

        if (ie.getStateChange() == ItemEvent.SELECTED) {
            if (ie.getSource() == pasajeVista.cbRuta) {
                calcularPrecio();
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
            if (ie.getSource() == pasajeVista.cbHorario
                    || ie.getSource() == pasajeVista.cbColectivos) {

                cargarTblAsientos();

            }

        }

    }

    /*
         ***********************
         *****VENDER PASAJE*****
         ***********************
     */
    private void venderPasaje() {

        Pasaje pasaje;
        Pasajero pasajero;
        LocalTime horita = null;
        boolean bandera = true;
        LocalDate fechaDtch;
        int asiento = 0;
        String dni = "";
        if (pasajeVista.cbHorario.getSelectedItem() != null) {
            horita = ((Horario) pasajeVista.cbHorario.getSelectedItem()).getHoraSalida();
        } else {
            bandera = false;
            JOptionPane.showMessageDialog(null, "No se puede vender un pasaje sin horario");
        }
        if (pasajeVista.dateChooser.getDate() != null) {
            fechaDtch = pasajeVista.dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } else {
            fechaDtch = LocalDate.now();
        }
        int selectedRow = pasajeVista.tblAsientos.getSelectedRow();
        int selectedColumn = pasajeVista.tblAsientos.getSelectedColumn();

        if (selectedRow != -1 && selectedColumn != -1) {
            Object value = pasajeVista.tblAsientos.getValueAt(selectedRow, selectedColumn);
            if (value instanceof Integer) {
                asiento = (Integer) value;
                System.out.println("Asiento seleccionado: " + asiento);
            } else {
                bandera = false;
                JOptionPane.showMessageDialog(null, "Seleccione un asiento válido.");
            }
        } else {
            bandera = false;
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún asiento.");
        }
        Colectivo colectivo = (Colectivo) pasajeVista.cbColectivos.getSelectedItem();

        if (pasajeVista.rbNoRegistrado.isSelected()) {

            String nombre = "", apellido = "";

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
            if (pasajeroData.buscarPasajeroPorDni(pasajeVista.txtDni.getText()) != null) {
                bandera = false;
                JOptionPane.showMessageDialog(null, "Ese pasajero ya existe, no se puede agregar como usuario no registrado");
            }
            if(sePuedeVenderEnEsteCole()){
                JOptionPane.showMessageDialog(null, "Primero debe vender todos los pasajes de colectivos anteriores a este");
            }
            if (bandera && horita != null && asiento != 0 && !pasajeData.estaElPasaje(asiento, r, colectivo, fechaDtch, horita)) {
                pasajero = new Pasajero(nombre, apellido, dni, null, null);
                pasajeroData.guardarPasajero(pasajero);
                String precioSinSimbolo = pasajeVista.lblPrecioCalculado.getText().replaceAll("[^\\d.]", "");

                Double precioLimpio = Double.parseDouble(precioSinSimbolo);

                pasaje = new Pasaje(pasajero, colectivo, (Ruta) pasajeVista.cbRuta.getSelectedItem(),
                        fechaDtch, horita, asiento, precioLimpio);
                //colectivoData.actualizarAsientos(colectivo, -1);
                pasajeData.venderPasaje(pasaje);
                //USUARIO NO REGISTRADO PDF

                Document documento = null;

                try {
                    documento = new Document();
                    String ruta = System.getProperty("user.home") + "/Desktop/DocumentosSolBus/Recibos/Fecha_" + pasaje.getFechaViaje().toString();
                    Path path = Paths.get(ruta);

                    if (!Files.exists(path)) {
                        Files.createDirectories(path);
                    }

                    PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Recibo-" + apellido + ".pdf"));
                    documento.open();

                    Paragraph encabezado1 = new Paragraph("Sol Bus", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, new BaseColor(203, 43, 50))); // Color #CB2B32
                    encabezado1.setAlignment(Element.ALIGN_CENTER);
                    documento.add(encabezado1);
                    Paragraph encabezado = new Paragraph("Recibo de Pasaje", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK));
                    encabezado.setAlignment(Element.ALIGN_CENTER);
                    documento.add(encabezado);

                    documento.add(Chunk.NEWLINE);

                    PdfPTable tablita = new PdfPTable(2);
                    tablita.setWidthPercentage(70);

                    PdfPCell cellTitulo = new PdfPCell(new Phrase("Detalles del Pasaje", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.WHITE)));
                    cellTitulo.setBackgroundColor(new BaseColor(203, 43, 50)); // Color de fondo #CB2B32
                    cellTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellTitulo.setColspan(2);
                    tablita.addCell(cellTitulo);

                    com.itextpdf.text.Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
                    com.itextpdf.text.Font fontContenido = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

                    tablita.addCell(new Phrase("Id Pasaje:", fontTitulo));
                    tablita.addCell(new Phrase(String.valueOf(pasaje.getIdPasaje()), fontContenido));

                    tablita.addCell(new Phrase("Nombre y Apellido Pasajero:", fontTitulo));
                    tablita.addCell(new Phrase(pasajero.getNombre() + " " + pasajero.getApellido(), fontContenido));

                    tablita.addCell(new Phrase("DNI Pasajero:", fontTitulo));
                    tablita.addCell(new Phrase(pasajero.getDni(), fontContenido));

                    tablita.addCell(new Phrase("N° de Butaca:", fontTitulo));
                    tablita.addCell(new Phrase(String.valueOf(pasaje.getAsiento()), fontContenido));

                    tablita.addCell(new Phrase("Origen y Destino:", fontTitulo));
                    tablita.addCell(new Phrase(pasaje.getRuta().getOrigen() + " - " + pasaje.getRuta().getDestino(), fontContenido));

                    tablita.addCell(new Phrase("Precio:", fontTitulo));
                    tablita.addCell(new Phrase(String.valueOf(pasaje.getPrecio()), fontContenido));

                    tablita.addCell(new Phrase("Fecha y Hora:", fontTitulo));
                    tablita.addCell(new Phrase(pasaje.getFechaViaje().toString() + " " + pasaje.getHoraViaje().toString(), fontContenido));

                    documento.add(tablita);

                    documento.add(Chunk.NEWLINE);

                    Paragraph piePagina = new Paragraph("¡Gracias por viajar con nosotros!", FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK));
                    piePagina.setAlignment(Element.ALIGN_CENTER);
                    documento.add(piePagina);

                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    if (documento != null && documento.isOpen()) {
                        JOptionPane.showMessageDialog(null, "Imprimiento recibo en el escritorio...");
                        documento.close();
                    }
                }

                JOptionPane.showMessageDialog(null, "Pasaje vendido a " + pasajero.toString());
                cargarTblAsientos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo vender pasaje");
            }

        } else if (pasajeVista.rbRegistrado.isSelected()) {

            if (validarEnteros(pasajeVista.txtDniRegistrado.getText()) && validarDniTam(pasajeVista.txtDniRegistrado.getText().length())) {
                dni = pasajeVista.txtDniRegistrado.getText();
            } else if (validarEnteros(pasajeVista.txtDni.getText()) && !validarDniTam(pasajeVista.txtDniRegistrado.getText().length())) {
                bandera = false;
                JOptionPane.showMessageDialog(null, "El DNI debe contener 7 u 8 dígitos");
            } else if (!validarEnteros(pasajeVista.txtDniRegistrado.getText())) {
                bandera = false;
                JOptionPane.showMessageDialog(null, "El DNI solo debe contener números");
            } else {
                bandera = false;
                JOptionPane.showMessageDialog(null, "El DNI solo debe contener 7 u 8 dígitos. No se admiten letras");
            }
            if(!sePuedeVenderEnEsteCole()){
                JOptionPane.showMessageDialog(null, "Primero debe vender todos los pasajes de colectivos anteriores a este");
                 bandera = false;
            }
            pasajero = pasajeroData.buscarPasajeroPorDni(dni);
            if (bandera && horita != null && asiento != 0 && pasajero != null && !pasajeData.estaElPasaje(asiento, r, colectivo, fechaDtch, horita)) {
                String precioSinSimbolo = pasajeVista.lblPrecioCalculado.getText().replaceAll("[^\\d.]", "");

                Double precioLimpio = Double.parseDouble(precioSinSimbolo);

                pasaje = new Pasaje(pasajero, colectivo, (Ruta) pasajeVista.cbRuta.getSelectedItem(),
                        fechaDtch, horita, asiento, precioLimpio);

                //colectivoData.actualizarAsientos(colectivo, -1);
                pasajeData.venderPasaje(pasaje);
                //USUARIO REGISTRADO PDF

                Document documento = new Document();

                try {
                    String ruta = System.getProperty("user.home") + "/Desktop/DocumentosSolBus/Recibos/Fecha_" + pasaje.getFechaViaje().toString();
                    Path path = Paths.get(ruta);

                    if (!Files.exists(path)) {
                        Files.createDirectories(path);
                    }

                    PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Recibo-" + pasajero.getApellido() + pasaje.getIdPasaje() + ".pdf"));
                    documento.open();

                    Paragraph encabezado1 = new Paragraph("Sol Bus", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, new BaseColor(203, 43, 50))); // Color #CB2B32
                    encabezado1.setAlignment(Element.ALIGN_CENTER);
                    documento.add(encabezado1);
                    Paragraph encabezado = new Paragraph("Recibo de Pasaje", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK));
                    encabezado.setAlignment(Element.ALIGN_CENTER);
                    documento.add(encabezado);

                    documento.add(Chunk.NEWLINE);

                    PdfPTable tablita = new PdfPTable(2);
                    tablita.setWidthPercentage(70);

                    PdfPCell cellTitulo = new PdfPCell(new Phrase("Detalles del Pasaje", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.WHITE)));
                    cellTitulo.setBackgroundColor(new BaseColor(203, 43, 50)); // Color de fondo #CB2B32
                    cellTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellTitulo.setColspan(2);
                    tablita.addCell(cellTitulo);

                    com.itextpdf.text.Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
                    com.itextpdf.text.Font fontContenido = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

                    tablita.addCell(new Phrase("Id Pasaje:", fontTitulo));
                    tablita.addCell(new Phrase(String.valueOf(pasaje.getIdPasaje()), fontContenido));

                    tablita.addCell(new Phrase("Nombre y Apellido Pasajero:", fontTitulo));
                    tablita.addCell(new Phrase(pasajero.getNombre() + " " + pasajero.getApellido(), fontContenido));

                    tablita.addCell(new Phrase("DNI Pasajero:", fontTitulo));
                    tablita.addCell(new Phrase(pasajero.getDni(), fontContenido));

                    tablita.addCell(new Phrase("N° de Butaca:", fontTitulo));
                    tablita.addCell(new Phrase(String.valueOf(pasaje.getAsiento()), fontContenido));

                    tablita.addCell(new Phrase("Origen y Destino:", fontTitulo));
                    tablita.addCell(new Phrase(pasaje.getRuta().getOrigen() + " - " + pasaje.getRuta().getDestino(), fontContenido));

                    tablita.addCell(new Phrase("Precio:", fontTitulo));
                    tablita.addCell(new Phrase(String.valueOf(pasaje.getPrecio()), fontContenido));

                    tablita.addCell(new Phrase("Fecha y Hora:", fontTitulo));
                    tablita.addCell(new Phrase(pasaje.getFechaViaje().toString() + " " + pasaje.getHoraViaje().toString(), fontContenido));

                    documento.add(tablita);

                    documento.add(Chunk.NEWLINE);

                    Paragraph piePagina = new Paragraph("¡Gracias por viajar con nosotros!", FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK));
                    piePagina.setAlignment(Element.ALIGN_CENTER);
                    documento.add(piePagina);

                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    if (documento != null && documento.isOpen()) {
                        JOptionPane.showMessageDialog(null, "Imprimiendo recibo en el escritorio...");
                        documento.close();
                    }
                }

                JOptionPane.showMessageDialog(null, "Pasaje vendido a " + pasajero.toString());
                cargarTblAsientos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo vender pasaje");
            }
        }

    }

    /*
         ***********************
         *****ANULAR PASAJE*****
         ***********************
     */
    public void anularPasaje() {
        int asiento = 0;
        LocalDate fechita = null;
        LocalTime horita = null;
        int selectedRow = pasajeVista.tblAsientos.getSelectedRow();
        int selectedColumn = pasajeVista.tblAsientos.getSelectedColumn();
        Colectivo colectivo = (Colectivo) pasajeVista.cbColectivos.getSelectedItem();
        Ruta ruta = (Ruta) pasajeVista.cbRuta.getSelectedItem();

        if (pasajeVista.cbHorario.getSelectedItem() != null) {
            horita = ((Horario) pasajeVista.cbHorario.getSelectedItem()).getHoraSalida();
        }

        if (pasajeVista.dateChooser.getDate() != null) {
            fechita = pasajeVista.dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } else {
            fechita = LocalDate.now();
        }

        if (selectedRow != -1 && selectedColumn != -1) {
            Object value = pasajeVista.tblAsientos.getValueAt(selectedRow, selectedColumn);
            if (pasajeData.estaElPasaje((Integer) value, ruta, colectivo, fechita, horita)) {
                if (value instanceof Integer) {

                    asiento = (Integer) value;
                    System.out.println("Asiento seleccionado: " + asiento);

                    Pasaje pasaje = pasajeData.buscarPasajePorViaje(ruta, colectivo, fechita, horita, asiento);
                    Pasajero pasajero = pasajeroData.buscarPasajeroPorId(pasaje.getPasajero().getIdPasajero());

                    System.out.println(pasajero.getIdPasajero());
                    ///*
                    if (pasaje != null) {

                        int response = JOptionPane.showConfirmDialog(
                                null,
                                "¿Está seguro que desea eliminar el pasaje del pasajero " + pasajero.toString() + "?",
                                "Confirmar eliminación",
                                JOptionPane.YES_NO_OPTION
                        );

                        if (response == JOptionPane.YES_OPTION) {

                            pasajeData.eliminarPasajePorViaje(asiento, ruta, colectivo, fechita, horita);
                            //colectivoData.actualizarAsientos(colectivo, 1);
                            cargarTblAsientos();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo encontrar el pasajero para el asiento seleccionado.");
                    }//*/
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede anular un pasaje con asiento libre");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un asiento válido.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún asiento.");
        }
    }

    private boolean sePuedeVenderEnEsteCole() {

        //Colectivo)pasajeVista.cbColectivos.getItemAt(0)) {
        boolean bandera = true;
        LocalDate fechita;
        if(pasajeVista.dateChooser.getDate()!=null){
        fechita=pasajeVista.dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        else{
            fechita=LocalDate.now();
        }
        if (pasajeVista.cbColectivos.getSelectedIndex() > 0) {
            
            for (int i = pasajeVista.cbColectivos.getSelectedIndex()-1; i >= 0; i--) {
                
                Colectivo c = pasajeVista.cbColectivos.getItemAt(i);
                int obtenerPasajes=pasajeData.pasajeColectivo(c,
                    fechita,((Horario)pasajeVista.cbHorario.getSelectedItem()).getHoraSalida(), (Ruta)pasajeVista.cbRuta.getSelectedItem());
                
                if(pasajeVista.cbColectivos.getItemAt(i).getCapacidad()>obtenerPasajes){
                    return false;
                }
            }
        }
        return bandera;
    }

    /*
     **************************
     *****CALCULARPRECIO()*****
     **************************
     */
    private void calcularPrecio() {

        precio = 0;
        if (pasajeVista.cbRuta != null) {
            r = (Ruta) pasajeVista.cbRuta.getSelectedItem();
            precio = (r.getDuracion().getHour() * 60) + (r.getDuracion().getMinute());

        }
        precio = precio * multiplicador;
        if (pasajeVista.rbRegistrado.isSelected()) {
            precio = precio - (precio * 0.15);
            pasajeVista.lblPromo.setText("Tiene un 15% de descuento");
        } else {
            pasajeVista.lblPromo.setText("");
        }

        pasajeVista.lblPrecioCalculado.setText("$" + precio + "");

    }

    /*
     **********************
     *****VALIDACIONES*****
     **********************
     */
    private boolean validarEnteros(String s) {

        String regExp = "^-?\\d+$";

        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(s);

        return m.matches();
    }

    //cambié expresión regular para que acepte todos las tildes por si metemos
    //nombres en otros idiomas latinos como apellido Müller o nombre François
    private boolean validarString(String s) {
        String regExp = "^[\\p{L}\\p{M} .'-]+$";

        return s.matches(regExp);
        //estoy perdiendo salud mental con este paquete de control
    }

    private boolean validarDniTam(int tam) {
        return tam == 8 || tam == 7;
    }

    private void limpiarCampos() {
        pasajeVista.txtApellido.setText("");
        pasajeVista.txtDni.setText("");
        pasajeVista.txtNombre.setText("");
        pasajeVista.txtDniRegistrado.setText("");

    }

    /*
     ************************
     ***CABECERA DEL TABLE***
     ************************
     */
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

    /*
     ***********************
     ***TABLE DE ASIENTOS*** 
     ***********************
     */
    private void cargarTblAsientos() {
        DefaultTableModel model = (DefaultTableModel) pasajeVista.tblAsientos.getModel();
        model.setRowCount(0);
        int numAsiento = 1;
        int colectivoSeleccionado = ((Colectivo) pasajeVista.cbColectivos.getSelectedItem()).getCapacidad();
        int numFila = colectivoSeleccionado / 4;
        for (int fila = 0; fila < numFila; fila++) {

            model.addRow(new Object[]{
                numAsiento++, numAsiento++, numAsiento++, numAsiento++
            });
        }
        switch (colectivoSeleccionado % 4) {
            case 1:
                model.addRow(new Object[]{numAsiento++});
                break;
            case 2:
                model.addRow(new Object[]{numAsiento++, numAsiento++});
                break;
            case 3:
                model.addRow(new Object[]{numAsiento++, numAsiento++, numAsiento++});
                break;
            default:
                break;
        }

        try {
            LocalDate fechaDtch;
            if (pasajeVista.dateChooser.getDate() != null) {
                fechaDtch = pasajeVista.dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            } else {
                fechaDtch = LocalDate.now();
            }

            ArrayList<Integer> asientosOcupados = (ArrayList<Integer>) pasajeData.listarAsientosOcupadosPorViaje((Ruta) pasajeVista.cbRuta.getSelectedItem(), (Colectivo) pasajeVista.cbColectivos.getSelectedItem(),
                    fechaDtch, ((Horario) pasajeVista.cbHorario.getSelectedItem()).getHoraSalida());

            ArrayList<Integer> asientosOcupadosSet = new ArrayList<>(asientosOcupados);

            // aca llamamos al renderer personalizado y se lo asignamos a cada columna
            PoneteBonitaTablita renderer = new PoneteBonitaTablita(asientosOcupadosSet);
            for (int i = 0; i < pasajeVista.tblAsientos.getColumnCount(); i++) {
                pasajeVista.tblAsientos.getColumnModel().getColumn(i).setCellRenderer(renderer);
            }
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    /*
     *********************
     ***PARTE DE DISEÑO***
     *********************
     */
    //PALETA DE COLORES EN:https://paletadecolores.com.ar/paleta/e7ddd3/c0c2bd/9c9994/29251c/e6aa9f/
    //    #174D51
    //    #0c2521
    //    #D48931
    //    #6F1C00
    private final void poneteBonito() {

        pasajeVista.setSize(new Dimension(570, 620));

        pasajeVista.setBorder(BorderFactory.createLineBorder(new Color(202, 40, 43), 3));

        // FONDITO INTERNAAAAL
        pasajeVista.getContentPane().setBackground(new Color(240, 240, 240));

        // MIRA ESOS BUTTONS PAPA
        pasajeVista.btnVenderPasaje.setBackground(new Color(202, 40, 43));
        pasajeVista.btnAnularPasaje.setBackground(new Color(202, 40, 43));
        pasajeVista.btnVenderPasaje.setForeground(Color.white);
        pasajeVista.btnAnularPasaje.setForeground(Color.white);

        // TITULO
        pasajeVista.lblTitulo.setForeground(new Color(41, 37, 28));

        // TABLITA BACK
        pasajeVista.spTabla.setBackground(new Color(240, 240, 240));
        pasajeVista.tblAsientos.setBackground(new Color(220, 220, 220));

        // LABELS
        pasajeVista.lblRuta.setForeground(new Color(41, 37, 28));
        pasajeVista.lblHorario.setForeground(new Color(41, 37, 28));
        pasajeVista.lblNombreNoR.setForeground(new Color(41, 37, 28));
        pasajeVista.lblApellidoNoR.setForeground(new Color(41, 37, 28));
        pasajeVista.lblDniNoR.setForeground(new Color(41, 37, 28));
        pasajeVista.lblDNIRegistrado.setForeground(new Color(41, 37, 28));
        pasajeVista.lblPrecio.setForeground(new Color(41, 37, 28));
        pasajeVista.lblFecha.setForeground(new Color(41, 37, 28));
        pasajeVista.lblColectivo.setForeground(new Color(41, 37, 28));
        pasajeVista.lblPrecioCalculado.setForeground(new Color(41, 37, 28));
        pasajeVista.lblPromo.setForeground(new Color(41, 37, 28));
        // PANELS
        pasajeVista.pnlNoRegistrado.setBackground(new Color(240, 240, 240));
        pasajeVista.pnlRegistrado.setBackground(new Color(240, 240, 240));

        // TEXTFIELDS
        pasajeVista.txtNombre.setBackground(new Color(220, 220, 220));
        pasajeVista.txtApellido.setBackground(new Color(220, 220, 220));
        pasajeVista.txtDni.setBackground(new Color(220, 220, 220));
        pasajeVista.txtDniRegistrado.setBackground(new Color(220, 220, 220));

        // COMBOBOXES
        pasajeVista.cbRuta.setBackground(new Color(240, 240, 240));
        pasajeVista.cbHorario.setBackground(new Color(240, 240, 240));
        pasajeVista.cbColectivos.setBackground(new Color(240, 240, 240));

        // RADIOBUTTONS
        pasajeVista.rbRegistrado.setForeground(new Color(41, 37, 28));
        pasajeVista.rbNoRegistrado.setForeground(new Color(41, 37, 28));

        // text field del JDATE
        JTextField dateTextField = ((JTextField) pasajeVista.dateChooser.getDateEditor().getUiComponent());
        dateTextField.setForeground(new Color(41, 37, 28));
        dateTextField.setBackground(new Color(220, 220, 220));

        // el render de la tabla
        ArrayList<Integer> asientosOcupados = null;
        pasajeVista.tblAsientos.setDefaultRenderer(Object.class,
                new PoneteBonitaTablita(asientosOcupados));

        // CENTREMOS EL TITULO
        pasajeVista.lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        // LA FONT MAS LINDA
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
            pasajeVista.lblPromo.setFont(montserratFont);
            pasajeVista.lblColectivo.setFont(montserratFont);
            pasajeVista.btnVenderPasaje.setFont(montserratFont);
            pasajeVista.btnAnularPasaje.setFont(montserratFont);
            pasajeVista.txtNombre.setFont(montserratFont);
            pasajeVista.txtApellido.setFont(montserratFont);
            pasajeVista.txtDni.setFont(montserratFont);
            pasajeVista.txtDniRegistrado.setFont(montserratFont);
            pasajeVista.cbRuta.setFont(montserratFont);
            pasajeVista.cbHorario.setFont(montserratFont);
            pasajeVista.cbColectivos.setFont(montserratFont);
            pasajeVista.lblPrecioCalculado.setFont(montserratFont);
            pasajeVista.rbRegistrado.setFont(montserratFont);
            pasajeVista.rbNoRegistrado.setFont(montserratFont);

            dateTextField.setFont(montserratFont);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();

        }
    }

    //APLICAR PARA CUANDO SE HAGA EL COMBOBOX DE COLES DISPOS
    class modeloComboColes extends DefaultComboBoxModel {

    }

    /*
     *************************************
     ********CLASE INTERNA PARA **********
     ***DARLE FORMATO A LAS CELDAS *******
     * USAMOS LA CLASE A IMPLEMENTAR DEL * 
     *****DefaultTableCellRenderer *******
     *************************************
     */
    class PoneteBonitaTablita extends DefaultTableCellRenderer {

        private ArrayList<Integer> asientosOcupados;

        public PoneteBonitaTablita(ArrayList<Integer> asientosOcupados) {
            this.asientosOcupados = asientosOcupados;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBorder(noFocusBorder);
            setFont(new java.awt.Font("Montserrat", 0, 18));  //MONSEEE
            setHorizontalAlignment(SwingConstants.CENTER);
            table.setRowHeight(28);

            if (isSelected) {
                setBackground(new Color(202, 40, 43));
                setForeground(Color.white);
            } else if (asientosOcupados != null && asientosOcupados.contains(value)) {
                setBackground(Color.RED);
                setForeground(Color.WHITE);
            } else {
                setBackground(new Color(240, 240, 240));
                setForeground(new Color(41, 37, 28));
            }

            return this;
        }
    }
}
