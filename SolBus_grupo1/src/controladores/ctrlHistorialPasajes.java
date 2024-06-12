package controladores;

import accesoDatos.*;
import com.itextpdf.text.BaseColor;
import entidades.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import vistas.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class ctrlHistorialPasajes implements ActionListener {

    private Pasaje pasaje;
    private PasajeData pasajeData;
    private RutaData rutaData;
    private HorarioData horarioData;

    private ColectivoData colectivoData;
    private InfHistorialPasajes pasajeVista;
    private PasajeroData pasajeroData;
    //clase para que no se editen las celdas de las columnas de los id
    //está abajo :B pero es un defaulttablemodel, no se asusten
    private NoMeLaEdite model = new NoMeLaEdite();

    public ctrlHistorialPasajes(Pasaje pasaje, PasajeData pasajeData, RutaData rutaData, HorarioData horarioData, ColectivoData colectivoData, InfHistorialPasajes pasajeVista, PasajeroData pasajeroData) throws IOException {
        this.pasaje = pasaje;
        this.pasajeData = pasajeData;
        this.rutaData = rutaData;
        this.horarioData = horarioData;
        this.colectivoData = colectivoData;
        this.pasajeVista = pasajeVista;
        this.pasajeroData = pasajeroData;

        armarCabecera();
        cargarPasajesDeHoy();
        cargarRutas();
        cargarHorario();
        poneteBonito();
        pasajeVista.rbHorario.addActionListener(this);
        pasajeVista.rbPasajero.addActionListener(this);
        pasajeVista.rbRuta.addActionListener(this);
        pasajeVista.btnVerHistorial.addActionListener(this);
        pasajeVista.dateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    cargarPasajesDeHoy();
                }
            }
        });

        pasajeVista.tblHistPasajes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && pasajeVista.tblHistPasajes.getSelectedRow() != -1) {
                cargarDetallesPasajeSeleccionado();
            }
        });

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == pasajeVista.btnVerHistorial) {
            LocalDate fechaDtch;
            if (pasajeVista.dateChooser.getDate() != null) {
                fechaDtch = pasajeVista.dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            } else {
                fechaDtch = LocalDate.now();
            }
            Pasajero pasajero;
            ArrayList<Pasaje> listaPasajes = (ArrayList<Pasaje>) pasajeData.listarPasajesVendidosPorFecha(fechaDtch);

            if (!listaPasajes.isEmpty()) {
                Document documento = new Document();

                try {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String fechaHoy = formatter.format(fechaDtch);

                    String ruta = System.getProperty("user.home") + "/Desktop/DocumentosSolBus/Historial/Fecha_" + fechaHoy;
                    Path path = Paths.get(ruta);

                    if (!Files.exists(path)) {
                        Files.createDirectories(path);
                    }

                    PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Reporte_" + fechaHoy + ".pdf"));
                    documento.open();

                    Paragraph encabezado = new Paragraph("Sol Bus", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, new BaseColor(203, 43, 50))); // Color #CB2B32
                    encabezado.setAlignment(Element.ALIGN_CENTER);
                    documento.add(encabezado);

                    Paragraph titulo = new Paragraph("Historial de Pasajes", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.DARK_GRAY));
                    titulo.setAlignment(Element.ALIGN_CENTER);
                    documento.add(titulo);

                    Paragraph fechaActual = new Paragraph("Fecha: " + fechaHoy, FontFactory.getFont(FontFactory.HELVETICA, 12, new BaseColor(203, 43, 50))); // Color #CB2B32
                    fechaActual.setAlignment(Element.ALIGN_CENTER);
                    documento.add(fechaActual);

                    documento.add(new Paragraph("\n"));

                    PdfPTable tablita = new PdfPTable(3);
                    tablita.setWidthPercentage(100);

                    PdfPCell cellIdPasaje = new PdfPCell(new Phrase("Id Pasaje", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE)));
                    cellIdPasaje.setBackgroundColor(new BaseColor(203, 43, 50)); // Color #CB2B32
                    cellIdPasaje.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tablita.addCell(cellIdPasaje);

                    PdfPCell cellNombreApellido = new PdfPCell(new Phrase("Nombre y Apellido Pasajero", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE)));
                    cellNombreApellido.setBackgroundColor(new BaseColor(203, 43, 50)); // Color #CB2B32
                    cellNombreApellido.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tablita.addCell(cellNombreApellido);

                    PdfPCell cellDni = new PdfPCell(new Phrase("DNI Pasajero", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE)));
                    cellDni.setBackgroundColor(new BaseColor(203, 43, 50)); // Color #CB2B32
                    cellDni.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tablita.addCell(cellDni);
                    for (Pasaje listaPasaje : listaPasajes) {
                        pasajero = pasajeroData.buscarPasajeroPorId(listaPasaje.getPasajero().getIdPasajero());
                        tablita.addCell(listaPasaje.getIdPasaje() + "");
                        tablita.addCell(pasajero.getNombre() + " " + pasajero.getApellido());
                        tablita.addCell(pasajero.getDni());
                    }
                    documento.add(tablita);

                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    if (documento != null && documento.isOpen()) {
                        JOptionPane.showMessageDialog(null, "Imprimiento en el escritorio...");
                        documento.close();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No hay pasajes para imprimir");
            }

        }
        if (e.getSource() == pasajeVista.btnActualizar) {
            actualizarPasaje();
        }

    }
private void actualizarPasaje() {
    int selectedRow = pasajeVista.tblHistPasajes.getSelectedRow();
    if (selectedRow != -1) {
        try {
            int idPasaje = (int) model.getValueAt(selectedRow, 0);
            Pasaje pasaje = pasajeData.buscarPasajePorId(idPasaje);

            //NOMBRE Y APELLIDO DE PASAJERO
            String[] nombreApellido = pasajeVista.txtNombreApellido.getText().split(" ");
            Pasajero pasajero = pasaje.getPasajero();
            pasajero.setNombre(nombreApellido[0]);
            pasajero.setApellido(nombreApellido[1]);

            //BUTACA Y PRECIO
            int butaca = Integer.parseInt(pasajeVista.txtButaca.getText());
            double precio = Double.parseDouble(pasajeVista.txtPrecio.getText());
            
            //RUTA Y HORARIO
            Ruta ruta = (Ruta) pasajeVista.cbRutas2.getSelectedItem();
            Horario horario = (Horario) pasajeVista.cbHorarios2.getSelectedItem();

            pasaje.setAsiento(butaca);
            pasaje.setPrecio(precio);
            pasaje.setRuta(ruta);

            pasajeroData.actualizarPasajero(pasajero);
            pasajeData.actualizarPasaje(pasaje);

            JOptionPane.showMessageDialog(pasajeVista, "Pasaje actualizado correctamente.");
            cargarPasajesDeHoy();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(pasajeVista, "Error al actualizar el pasaje: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(pasajeVista, "Por favor, seleccione un pasaje de la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
}
    private void cargarDetallesPasajeSeleccionado() {
        int selectedRow = pasajeVista.tblHistPasajes.getSelectedRow();
        if (selectedRow != -1) {
            int idPasaje = (int) model.getValueAt(selectedRow, 0);
            Pasaje pasaje = pasajeData.buscarPasajePorId(idPasaje);
            Pasajero pasajero = pasajeroData.buscarPasajeroPorId(pasaje.getPasajero().getIdPasajero());

            pasajeVista.txtNombreApellido.setText(pasajero.getNombre() + " " + pasajero.getApellido());
 pasajeVista.txtButaca.setText(String.valueOf(pasaje.getAsiento()));
            pasajeVista.txtPrecio.setText(String.valueOf(pasaje.getPrecio()));

            pasajeVista.cbRutas2.setSelectedItem(rutaData.buscarRutaPorID(pasaje.getRuta().getIdRuta()));
            pasajeVista.cbHorarios2.setSelectedItem(horarioData.buscarHorarioPorId(idPasaje);
        }
    }

    private void armarCabecera() {
        ArrayList<Object> filaCabecera = new ArrayList<>();
        filaCabecera.add("Pasaje");
        filaCabecera.add("Pasajero");
        filaCabecera.add("Colectivo");
        filaCabecera.add("Ruta");
        filaCabecera.add("Fecha");
        filaCabecera.add("Hora");
        filaCabecera.add("Asiento");
        filaCabecera.add("Precio");

        for (Object i : filaCabecera) {
            model.addColumn(i);
        }
        pasajeVista.tblHistPasajes.setModel(model);
        //vamo a darle estilo a esa cabecera ewe

        try {
            JTableHeader header = pasajeVista.tblHistPasajes.getTableHeader();
            Font montserratFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/Montserrat-Regular.ttf")).deriveFont(Font.BOLD, 12);

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
    }

    public boolean validarDniTam(int tam) {
        return tam == 8 || tam == 7;
    }

    public boolean validarString(String s) {
        String regExp = "^[\\p{L}\\p{M} .'-]+$";

        return s.matches(regExp);
        //estoy perdiendo salud mental con este paquete de control
    }

    private void cargarPasajesDeHoy() {
        model.setRowCount(0);
        LocalDate fechaDtch;
        if (pasajeVista.dateChooser.getDate() != null) {
            fechaDtch = pasajeVista.dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } else {
            fechaDtch = LocalDate.now();
        }
        ArrayList<Pasaje> listaPasajes = (ArrayList<Pasaje>) pasajeData.listarPasajesVendidosPorFecha(fechaDtch);
        for (Pasaje p : listaPasajes) {
            model.addRow(new Object[]{
                p.getIdPasaje(),
                pasajeroData.buscarPasajeroPorId(p.getPasajero().getIdPasajero()),
                colectivoData.buscarColectivoPorId(p.getColectivo().getIdColectivo()),
                rutaData.buscarRutaPorID(p.getRuta().getIdRuta()),
                p.getFechaViaje(),
                p.getHoraViaje(),
                p.getAsiento(),
                p.getPrecio()
            });
        }
    }

    private void cargarHorario() {
        ArrayList<Horario> listaHorarios = (ArrayList<Horario>) horarioData.listarHorarios();
        for (Horario item : listaHorarios) {
            pasajeVista.cbHorarios.addItem(item);
            pasajeVista.cbHorarios2.addItem(item);
        }
    }

    private void cargarRutas() {
        ArrayList<Ruta> listaRutas = (ArrayList<Ruta>) rutaData.listarRutasDisponibles();
        for (Ruta item : listaRutas) {
            pasajeVista.cbRutas.addItem(item);
            pasajeVista.cbRutas2.addItem(item);
        }
    }

    public final void poneteBonito() throws IOException {

        pasajeVista.setSize(new Dimension(770, 620));
        pasajeVista.setBorder(BorderFactory.createLineBorder(new Color(202, 40, 43), 3));
        pasajeVista.getContentPane().setBackground(new Color(240, 240, 240)); // Gris claro

        // MIRA ESOS BUTTONS PAPA
        pasajeVista.btnVerHistorial.setBackground(new Color(202, 40, 43));
        pasajeVista.btnVerHistorial.setForeground(Color.white);
        pasajeVista.btnActualizar.setBackground(new Color(202, 40, 43));
        pasajeVista.btnActualizar.setForeground(Color.white);

        // Combobox
        pasajeVista.cbHorarios.setBackground(new Color(240, 240, 240)); // Gris claro
        pasajeVista.cbRutas2.setBackground(new Color(240, 240, 240)); // Gris claro

        // Labels
        pasajeVista.lblTituloHistPasajes.setForeground(new Color(41, 37, 28));
        pasajeVista.lblPrecio.setForeground(new Color(41, 37, 28));

        // Panels
        pasajeVista.pnlHorario.setBackground(new Color(240, 240, 240)); // Gris claro
        pasajeVista.pnlPasajero.setBackground(new Color(240, 240, 240)); // Gris claro
        pasajeVista.pnlRuta.setBackground(new Color(240, 240, 240)); // Gris claro

        // RadioButtons
        pasajeVista.rbHorario.setForeground(new Color(41, 37, 28));
        pasajeVista.rbPasajero.setForeground(new Color(41, 37, 28));
        pasajeVista.rbRuta.setForeground(new Color(41, 37, 28));

        // TextFields
        pasajeVista.txtApellido.setBackground(new Color(220, 220, 220)); // Gris medio
        pasajeVista.txtDNI.setBackground(new Color(220, 220, 220)); // Gris medio

        // Table
        pasajeVista.spnHistPasajes.setBackground(new Color(240, 240, 240)); // Gris claro
        pasajeVista.tblHistPasajes.setBackground(new Color(220, 220, 220)); // Gris medio

        // Centramos los títulos
        pasajeVista.lblTituloHistPasajes.setHorizontalAlignment(SwingConstants.CENTER);

        // Aplicamos la fuente personalizada
        //vamo a darle estilo a esa tabla
        pasajeVista.tblHistPasajes.setDefaultRenderer(Object.class, new PoneteBonitaTablita());

        //la wasada de darle formato a un dchooser jaasdasdj estoy esquizofrenicaaaa
        // text field del JDATE
        JTextField dateTextField = ((JTextField) pasajeVista.dateChooser.getDateEditor().getUiComponent());
        dateTextField.setForeground(new Color(41, 37, 28));
        dateTextField.setBackground(new Color(220, 220, 220));

        try {
            Font montserratFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/Montserrat-Regular.ttf")).deriveFont(Font.PLAIN, 14);
            Font montserratFontTitulo = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/Montserrat-Regular.ttf")).deriveFont(Font.BOLD, 18);

            pasajeVista.lblTituloHistPasajes.setFont(montserratFontTitulo);
            pasajeVista.lblPrecio.setFont(montserratFontTitulo);
            pasajeVista.cbHorarios.setFont(montserratFont);
            pasajeVista.cbRutas2.setFont(montserratFont);
            pasajeVista.rbHorario.setFont(montserratFont);
            pasajeVista.rbPasajero.setFont(montserratFont);
            pasajeVista.rbRuta.setFont(montserratFont);
            pasajeVista.txtApellido.setFont(montserratFont);
            pasajeVista.txtDNI.setFont(montserratFont);
            pasajeVista.btnActualizar.setFont(montserratFont);
            pasajeVista.btnVerHistorial.setFont(montserratFont);
            dateTextField.setFont(montserratFont);//FUNCIONA DALE LPM

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    class PoneteBonitaTablita extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBorder(noFocusBorder);
            setFont(new java.awt.Font("Montserrat", 0, 12));  //MONSEEE
            setHorizontalAlignment(SwingConstants.CENTER);
            table.setRowHeight(28);

            if (isSelected) {
                setBackground(new Color(202, 40, 43));
                setForeground(Color.white);
            } else {
                setBackground(new Color(240, 240, 240));
                setForeground(new Color(41, 37, 28));
            }

            return this;
        }
    }

    class NoMeLaEdite extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            int[] nonEditableColumns = {0, 1, 2, 3};
            for (int col : nonEditableColumns) {
                if (column == col) {
                    return false;
                }
            }
            return true;
        }
    }
}
