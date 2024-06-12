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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class ctrlHistorialPasajes implements ActionListener, ItemListener {

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

    //mails
    private static String emailFrom = "grupo1solbus@gmail.com";
    private static String passwordFrom = "bcmjtbkmgtefvbzi";
    private String emailTo;
    private String subject;
    private String content;

    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;

    public ctrlHistorialPasajes(Pasaje pasaje, PasajeData pasajeData, RutaData rutaData, HorarioData horarioData, ColectivoData colectivoData, InfHistorialPasajes pasajeVista, PasajeroData pasajeroData) throws IOException {
        this.pasaje = pasaje;
        this.pasajeData = pasajeData;
        this.rutaData = rutaData;
        this.horarioData = horarioData;
        this.colectivoData = colectivoData;
        this.pasajeVista = pasajeVista;
        this.pasajeroData = pasajeroData;

        mProperties = new Properties();

        armarCabecera();
        cargarPasajesDeHoy();
        cargarRutas();
        cargarHorario();
        poneteBonito();
        pasajeVista.rbPasajero.addActionListener(this);
        pasajeVista.rbRuta.addActionListener(this);
        pasajeVista.btnVerHistorial.addActionListener(this);
        pasajeVista.rbFecha.addActionListener(this);
        pasajeVista.rbVerTodo.addActionListener(this);
        pasajeVista.btnEnviarMail.addActionListener(this);
        desactivarComponentesPorTipo();
        sugerirEnviarMail();
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

        DocumentListener documentListener = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                filtrarPorPasajero();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                filtrarPorPasajero();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                filtrarPorPasajero();
            }
        };

        pasajeVista.txtApellido.getDocument().addDocumentListener(documentListener);
        pasajeVista.txtDNI.getDocument().addDocumentListener(documentListener);

        pasajeVista.cbRutas2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pasajeVista.rbRuta.isSelected()) {
                    filtrarPorRuta();
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == pasajeVista.btnVerHistorial) {
            verHistorial();
        }
        if (e.getSource() == pasajeVista.rbPasajero) {
            desactivarComponentesPorTipo();
        } else if (e.getSource() == pasajeVista.rbRuta) {
            desactivarComponentesPorTipo();

        } else if (e.getSource() == pasajeVista.rbFecha) {
            desactivarComponentesPorTipo();
        } else if (e.getSource() == pasajeVista.rbVerTodo) {
            desactivarComponentesPorTipo();
            cargarTodosPasajes();
        }

        if (e.getSource() == pasajeVista.btnEnviarMail) {

            List<Pasajero> todos = pasajeroData.listarPasajerosRegistrados();
            Pasajero elegido = null;
            for (Pasajero p : todos) {
                if (pasajeData.pasajeroHaCompradoNueveOMasPasajes(p.getIdPasajero())) {

                    elegido = p;
                    break;

                }

            }
            if (elegido != null) {
                int option = JOptionPane.showConfirmDialog(null, "¿Desea enviar un correo electrónico a " + elegido.toString() + " ?", "Confirmación", JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    prepararMail(elegido);
                    enviarMail();
                }
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {

    }

    private void sugerirEnviarMail() {

        List<Pasajero> todos = pasajeroData.listarPasajerosRegistrados();
        Pasajero elegido = null;
        for (Pasajero p : todos) {
            if (pasajeData.pasajeroHaCompradoNueveOMasPasajes(p.getIdPasajero())) {

                elegido = p;
                break;

            }

        }
        if (elegido != null) {
            pasajeVista.btnEnviarMail.setVisible(true);
            pasajeVista.lblGanador1.setText(elegido.toString() + "\n ha ganado un pasaje gratis");

            pasajeVista.lblGanador1.setVisible(true);

        }

    }

    private void prepararMail(Pasajero p) {
    emailTo = p.getCorreo();
    subject = "FELICIDADES " + p.toString().toUpperCase() + " HAS GANADO UN PASAJE GRATIS";

    // Contenido del correo en HTML
    content = "<html><body>"
            + "<p>Querido/a " + p.toString() + ":<br>"
            + "Gracias por elegirnos, tu <b>décimo pasaje es gratis</b>.<br>"
            + "Atte. <span style='color:#CB2B32;'>SolBus</span></p>"
            + "</body></html>";

    mProperties.put("mail.smtp.host", "smtp.gmail.com"); // Cambia esto al servidor SMTP que uses
    mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

    mProperties.setProperty("mail.smtp.starttls.enable", "true");
    mProperties.setProperty("mail.smtp.port", "587"); // Puerto para TLS
    mProperties.setProperty("mail.smtp.user", emailFrom);
    mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
    mProperties.setProperty("mail.smtp.auth", "true");

    mSession = Session.getInstance(mProperties);
    mCorreo = new MimeMessage(mSession);
    try {
        mCorreo.setFrom(new InternetAddress(emailFrom)); // Email del remitente
        mCorreo.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo)); // Email del destinatario
        mCorreo.setSubject(subject);

        // Crear el cuerpo del mensaje
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(content, "text/html; charset=UTF-8");

        // Crear el contenido multipart
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // Adjuntar el PDF
        String rutaPdf = generarComprobante(p);
        if (!rutaPdf.isEmpty()) {
            MimeBodyPart attachPart = new MimeBodyPart();
            attachPart.attachFile(rutaPdf);
            multipart.addBodyPart(attachPart);
        }

        // Configurar el contenido del mensaje
        mCorreo.setContent(multipart);

    } catch (AddressException e) {
        e.printStackTrace();
    } catch (MessagingException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    private void enviarMail() {

        try {
            // Enviar el correo
            Transport transport = mSession.getTransport("smtp");
            transport.connect(emailFrom, passwordFrom);
            transport.sendMessage(mCorreo, mCorreo.getAllRecipients());
            transport.close();

            System.out.println("Correo enviado exitosamente!");
        } catch (NoSuchProviderException ex) {
            System.out.println("No existe el servidor " + ex);
        } catch (MessagingException ex) {
            System.out.println("Mensaje no enviado " + ex);
        }

    }
    public static String generarComprobante(Pasajero p) {
        Document documento = new Document();
        String ruta = "";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String fechaHoy = formatter.format(LocalDate.now());

            ruta = System.getProperty("user.home") + "/Desktop/DocumentosSolBus/Comprobantes/" + p.getIdPasajero() + "_Comprobante.pdf";
            Path path = Paths.get(ruta);

            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }

            PdfWriter.getInstance(documento, new FileOutputStream(ruta));
            documento.open();

            Paragraph encabezado = new Paragraph("Sol Bus", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, new BaseColor(203, 43, 50))); // Color #CB2B32
            encabezado.setAlignment(Element.ALIGN_CENTER);
            documento.add(encabezado);

            Paragraph titulo = new Paragraph("Comprobante de Pasaje Gratis", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.DARK_GRAY));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            Paragraph fechaActual = new Paragraph("Fecha: " + fechaHoy, FontFactory.getFont(FontFactory.HELVETICA, 12, new BaseColor(203, 43, 50))); // Color #CB2B32
            fechaActual.setAlignment(Element.ALIGN_CENTER);
            documento.add(fechaActual);

            documento.add(new Paragraph("\n"));

            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(100);

            PdfPCell cellTitulo = new PdfPCell(new Phrase("Detalle", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE)));
            cellTitulo.setBackgroundColor(new BaseColor(203, 43, 50)); // Color #CB2B32
            cellTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(cellTitulo);

            PdfPCell cellContenido = new PdfPCell(new Phrase("Información", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE)));
            cellContenido.setBackgroundColor(new BaseColor(203, 43, 50)); // Color #CB2B32
            cellContenido.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(cellContenido);

            tabla.addCell("Nombre y Apellido:");
            tabla.addCell(p.getNombre() + " " + p.getApellido());

            tabla.addCell("DNI:");
            tabla.addCell(p.getDni());

            documento.add(tabla);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (documento != null && documento.isOpen()) {
                documento.close();
            }
        }

        return ruta;
    }

    private void verHistorial() {
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

    //ALGUN DIA SERAS IMPLMENTADO PERO ESTE NO ES EL DIA
    /*
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

                pasaje.setAsiento(butaca);
                pasaje.setPrecio(precio);
                pasaje.setRuta(ruta);

                model.setValueAt(pasaje.getPasajero().getNombre(), selectedRow, 1);
                model.setValueAt(pasaje.getPasajero().getApellido(), selectedRow, 2);

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
     */
    private void cargarDetallesPasajeSeleccionado() {
        int selectedRow = pasajeVista.tblHistPasajes.getSelectedRow();
        if (selectedRow != -1) {
            int idPasaje = (int) model.getValueAt(selectedRow, 0);
            Pasaje pasaje = pasajeData.buscarPasajePorId(idPasaje);
            pasajeVista.txtDNI.setText(pasaje.getPasajero().getDni());
            // Seleccionar la Ruta y el Horario en los ComboBoxes
            pasajeVista.cbRutas2.setSelectedItem(pasaje.getRuta());
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
        LocalDate fecha;
        if (pasajeVista.dateChooser.getDate() != null) {
            fecha = pasajeVista.dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } else {
            fecha = LocalDate.now();
        }

        ArrayList<Pasaje> listaPasajes = (ArrayList<Pasaje>) pasajeData.listarPasajesVendidosPorFecha(fecha);
        actualizarTablaConPasajes(listaPasajes);
    }

    private void cargarTodosPasajes() {
        ArrayList<Pasaje> listaPasajes = (ArrayList<Pasaje>) pasajeData.listarPasajesVendidos();
        actualizarTablaConPasajes(listaPasajes);
    }

    private void cargarRutas() {
        ArrayList<Ruta> rutas = (ArrayList<Ruta>) rutaData.listarRutasDisponibles();
        DefaultComboBoxModel<Ruta> model = new DefaultComboBoxModel<>(rutas.toArray(new Ruta[0]));
        pasajeVista.cbRutas2.setModel(model);
    }

    private void cargarHorario() {
        ArrayList<Horario> horarios = (ArrayList<Horario>) horarioData.listarHorarios();
        DefaultComboBoxModel<Horario> model = new DefaultComboBoxModel<>(horarios.toArray(new Horario[0]));
    }

    private void actualizarTablaConPasajes(ArrayList<Pasaje> listaPasajes) {
        model.setRowCount(0);
        for (Pasaje p : listaPasajes) {
            model.addRow(new Object[]{p.getIdPasaje(), p.getPasajero().toString(), p.getColectivo().getMatricula(),
                p.getRuta().toString(), p.getHoraViaje().toString(), p.getFechaViaje().toString(),
                p.getAsiento(), p.getPrecio()});
        }
    }

    private void filtrarPorPasajero() {
        if (pasajeVista.rbPasajero.isSelected()) {
            String ape = pasajeVista.txtApellido.getText();
            String dni = pasajeVista.txtDNI.getText();

            ArrayList<Pasaje> pasajesFiltrados = new ArrayList<>();
            if (!ape.isEmpty() || !dni.isEmpty()) {
                if (!ape.isEmpty() && !dni.isEmpty()) {
                    pasajesFiltrados = (ArrayList<Pasaje>) pasajeData.listarPasajesPorPasajeroYDNI(ape, dni);
                } else if (!ape.isEmpty()) {
                    pasajesFiltrados = (ArrayList<Pasaje>) pasajeData.listarPasajesPorNombrePasajero(ape);
                } else if (!dni.isEmpty()) {
                    pasajesFiltrados = (ArrayList<Pasaje>) pasajeData.listarPasajesPorDNI(dni);
                }
            } else {
                pasajesFiltrados = (ArrayList<Pasaje>) pasajeData.listarPasajesVendidosPorFecha(LocalDate.now());
            }
            actualizarTablaConPasajes(pasajesFiltrados);
        }
    }

    private void filtrarPorRuta() {
        if (pasajeVista.rbRuta.isSelected()) {
            Ruta ruta = (Ruta) pasajeVista.cbRutas2.getSelectedItem();
            ArrayList<Pasaje> pasajesFiltrados = (ArrayList<Pasaje>) pasajeData.listarPasajesVendidosPorRuta(ruta);
            actualizarTablaConPasajes(pasajesFiltrados);
        }
    }

    private void desactivarComponentesPorTipo() {
        if (pasajeVista.rbPasajero.isSelected()) {
            pasajeVista.cbRutas2.setEnabled(false);
            pasajeVista.txtApellido.setEnabled(true);
            pasajeVista.txtDNI.setEnabled(true);
            pasajeVista.dateChooser.setEnabled(false);
        } else if (pasajeVista.rbRuta.isSelected()) {
            pasajeVista.cbRutas2.setEnabled(true);
            pasajeVista.txtApellido.setEnabled(false);
            pasajeVista.txtDNI.setEnabled(false);
            pasajeVista.dateChooser.setEnabled(false);
        } else if (pasajeVista.rbFecha.isSelected()) {
            pasajeVista.dateChooser.setEnabled(true);
            pasajeVista.cbRutas2.setEnabled(false);
            pasajeVista.txtApellido.setEnabled(false);
            pasajeVista.txtDNI.setEnabled(false);

        } else if (pasajeVista.rbVerTodo.isSelected()) {

            pasajeVista.cbRutas2.setEnabled(false);
            pasajeVista.txtApellido.setEnabled(false);
            pasajeVista.txtDNI.setEnabled(false);
            pasajeVista.dateChooser.setEnabled(false);
        }
    }

    public final void poneteBonito() throws IOException {

        pasajeVista.setSize(new Dimension(850, 620));
        pasajeVista.setBorder(BorderFactory.createLineBorder(new Color(202, 40, 43), 3));
        pasajeVista.getContentPane().setBackground(new Color(240, 240, 240));

        // MIRA ESOS BUTTONS PAPA
        pasajeVista.btnVerHistorial.setBackground(new Color(202, 40, 43));
        pasajeVista.btnVerHistorial.setForeground(Color.white);
        pasajeVista.btnEnviarMail.setBackground(new Color(202, 40, 43));
        pasajeVista.btnEnviarMail.setForeground(Color.white);
        // Combobox
        pasajeVista.cbRutas2.setBackground(new Color(240, 240, 240));

        // Labels
        pasajeVista.lblTituloGestion.setForeground(new Color(41, 37, 28));
        pasajeVista.lblTituloFiltrar.setForeground(new Color(41, 37, 28));
        pasajeVista.lblSugerencia.setForeground(new Color(41, 37, 28));
        pasajeVista.lblGanador1.setForeground(new Color(41, 37, 28));
        // Panels
        pasajeVista.pnlPasajero.setBackground(new Color(240, 240, 240));
        pasajeVista.pnlRuta.setBackground(new Color(240, 240, 240));

        // RadioButtons
        pasajeVista.rbPasajero.setForeground(new Color(41, 37, 28));
        pasajeVista.rbRuta.setForeground(new Color(41, 37, 28));
        pasajeVista.rbFecha.setForeground(new Color(41, 37, 28));
        pasajeVista.rbVerTodo.setForeground(new Color(41, 37, 28));

        // TextFields
        pasajeVista.txtApellido.setBackground(new Color(220, 220, 220));
        pasajeVista.txtDNI.setBackground(new Color(220, 220, 220));

        // Table
        pasajeVista.spnHistPasajes.setBackground(new Color(240, 240, 240));
        pasajeVista.tblHistPasajes.setBackground(new Color(220, 220, 220));

        // Centramos los títulos
        pasajeVista.lblTituloGestion.setHorizontalAlignment(SwingConstants.CENTER);

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

            pasajeVista.lblTituloGestion.setFont(montserratFontTitulo);
            pasajeVista.lblTituloFiltrar.setFont(montserratFont);
            pasajeVista.lblGanador1.setFont(montserratFont);
            pasajeVista.lblSugerencia.setFont(montserratFont);
            pasajeVista.cbRutas2.setFont(montserratFont);
            pasajeVista.rbPasajero.setFont(montserratFont);
            pasajeVista.rbRuta.setFont(montserratFont);
            pasajeVista.rbFecha.setFont(montserratFont);
            pasajeVista.rbVerTodo.setFont(montserratFont);
            pasajeVista.txtApellido.setFont(montserratFont);
            pasajeVista.txtDNI.setFont(montserratFont);
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

