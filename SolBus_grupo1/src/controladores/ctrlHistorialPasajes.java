package controladores;

import accesoDatos.*;
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
        
    }



    public void actionPerformed(ActionEvent e) {

        
        
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
