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
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;

public class ctrlHistorialPasajes implements ActionListener {

    private Pasaje pasaje;
    private PasajeData pasajeData;
    private RutaData rutaData;
    private HorarioData horarioData;
    
    private ColectivoData colectivoData;
    private InfHistorialPasajes pasajeVista;
    private PasajeroData pasajeroData;
    private DefaultTableModel model = new DefaultTableModel();

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
        
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == pasajeVista.btnVerHistorial) {
            LocalDate hoy = LocalDate.now();
            Pasajero pasajero;
            ArrayList<Pasaje> listaPasajes = (ArrayList<Pasaje>) pasajeData.listarPasajesVendidosPorFecha(hoy);

            Document documento = new Document();

            try {

                // Convertir la fecha a formato String con guiones
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String fechaHoy = formatter.format(hoy);

                // Ruta del directorio de usuario
                String ruta = System.getProperty("user.home");

                // Crear el documento PDF
                PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Historial-" + fechaHoy + ".pdf"));
                documento.open();

                // Agregar encabezado con el nombre de la empresa
                Paragraph encabezado = new Paragraph("Sol Bus", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, new BaseColor(203, 43, 50))); // Color #CB2B32
                encabezado.setAlignment(Element.ALIGN_CENTER);
                documento.add(encabezado);

                // Agregar título del historial
                Paragraph titulo = new Paragraph("Historial de Pasajes", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.DARK_GRAY));
                titulo.setAlignment(Element.ALIGN_CENTER);
                documento.add(titulo);

                // Agregar fecha de hoy
                Paragraph fechaActual = new Paragraph("Fecha: " + fechaHoy, FontFactory.getFont(FontFactory.HELVETICA, 12, new BaseColor(203, 43, 50))); // Color #CB2B32
                fechaActual.setAlignment(Element.ALIGN_CENTER);
                documento.add(fechaActual);

                // Agregar espacio en blanco
                documento.add(new Paragraph("\n"));

                // Crear tabla para el historial de pasajes
                PdfPTable tablita = new PdfPTable(3);
                tablita.setWidthPercentage(100);

                // Encabezados de la tabla
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
                    documento.close();
                }
            }

        }

    }

    private void armarCabecera() {
        ArrayList<Object> filaCabecera = new ArrayList<>();
        filaCabecera.add("Id pasaje");
        filaCabecera.add("Id pasajero");
        filaCabecera.add("id colectivo");
        filaCabecera.add("id ruta");
        filaCabecera.add("Fecha viaje");
        filaCabecera.add("Hora viaje");
        filaCabecera.add("Asiento");
        filaCabecera.add("Precio");

        for (Object i : filaCabecera) {
            model.addColumn(i);
        }
        pasajeVista.tblHistPasajes.setModel(model);
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
        LocalDate hoy = LocalDate.now();
        ArrayList<Pasaje> listaPasajes = (ArrayList<Pasaje>) pasajeData.listarPasajesVendidosPorFecha(hoy);
        for (Pasaje p : listaPasajes) {
            model.addRow(new Object[]{
                p.getIdPasaje(),
                p.getPasajero().getIdPasajero(),
                p.getColectivo().getIdColectivo(),
                p.getRuta().getIdRuta(),
                p.getFechaViaje(),
                p.getHoraViaje(),
                p.getAsiento(),
                p.getPrecio()
            });
        }
    }

  private void cargarHorario(){
         ArrayList<Horario> listaHorarios = (ArrayList<Horario>) horarioData.listarHorarios();
        for (Horario item : listaHorarios) {
            pasajeVista.cbHorarios.addItem(item);
        }
    }

    private void cargarRutas(){
         ArrayList<Ruta> listaRutas = (ArrayList<Ruta>) rutaData.listarRutasDisponibles();
        for (Ruta item : listaRutas) {
            pasajeVista.cbRutas.addItem(item);
        }
    }
    


public final void poneteBonito() throws IOException {

    pasajeVista.setSize(new Dimension(770, 620));
    pasajeVista.setBorder(BorderFactory.createLineBorder(new Color(202, 40, 43), 3));
    pasajeVista.getContentPane().setBackground(new Color(240, 240, 240)); // Gris claro

    // Combobox
    pasajeVista.cbHorarios.setBackground(new Color(240, 240, 240)); // Gris claro
    pasajeVista.cbRutas.setBackground(new Color(240, 240, 240)); // Gris claro

    // Labels
    pasajeVista.lblTituloHistPasajes.setForeground(new Color(41, 37, 28));
    pasajeVista.jlPasajesT.setForeground(new Color(41, 37, 28));

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
    try {
        Font montserratFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/Montserrat-Regular.ttf")).deriveFont(Font.PLAIN, 14);
        Font montserratFontTitulo = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/Montserrat-Regular.ttf")).deriveFont(Font.BOLD, 18);

        pasajeVista.lblTituloHistPasajes.setFont(montserratFontTitulo);
        pasajeVista.jlPasajesT.setFont(montserratFontTitulo);
        pasajeVista.cbHorarios.setFont(montserratFont);
        pasajeVista.cbRutas.setFont(montserratFont);
        pasajeVista.rbHorario.setFont(montserratFont);
        pasajeVista.rbPasajero.setFont(montserratFont);
        pasajeVista.rbRuta.setFont(montserratFont);
        pasajeVista.txtApellido.setFont(montserratFont);
        pasajeVista.txtDNI.setFont(montserratFont);

    } catch (FontFormatException | IOException e) {
        e.printStackTrace();
    }
}


}
