package controladores;

import accesoDatos.*;
import entidades.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import vistas.*;

public class ctrlHistorialPasajes implements ActionListener {

    private Pasaje pasaje;
    private PasajeData pasajeData;
    private ColectivoData colectivoData;
    private InfHistorialPasajes pasajeVista;
    private PasajeroData pasajeroData;
    private DefaultTableModel model = new DefaultTableModel();
    
    
    
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
        poneteBonito();
    }

    public boolean validarDniTam(int tam) {
        return tam == 8 || tam == 7;
    }

    public boolean validarString(String s) {
        String regExp = "^[\\p{L}\\p{M} .'-]+$";

        return s.matches(regExp);
        //estoy perdiendo salud mental con este paquete de control
    }
    
    public final void poneteBonito() {

        pasajeVista.setSize(new Dimension(770, 620));
        pasajeVista.setBorder(BorderFactory.createLineBorder(new Color(41, 37, 28), 3));
        pasajeVista.getContentPane().setBackground(new Color(231, 221, 211));

        // Combobox
        pasajeVista.cbHorarios.setBackground(new Color(231, 221, 211));
        pasajeVista.cbRutas.setBackground(new Color(231, 221, 211));

        // Labels
        pasajeVista.lblTituloHistPasajes.setForeground(new Color(41, 37, 28));

        // Panels
        pasajeVista.pnlHorario.setBackground(new Color(231, 221, 211));
        pasajeVista.pnlPasajero.setBackground(new Color(231, 221, 211));
        pasajeVista.pnlRuta.setBackground(new Color(231, 221, 211));

        // RadioButtons
        pasajeVista.rbHorario.setForeground(new Color(41, 37, 28));
        pasajeVista.rbPasajero.setForeground(new Color(41, 37, 28));
        pasajeVista.rbRuta.setForeground(new Color(41, 37, 28));

        // TextFields
        pasajeVista.txtApellido.setBackground(new Color(192, 153, 139));
        pasajeVista.txtDNI.setBackground(new Color(192, 153, 139));

        // Table
        pasajeVista.spnHistPasajes.setBackground(new Color(231, 221, 211));
        pasajeVista.tblHistPasajes.setBackground(new Color(192, 153, 139));

        // Centramos los títulos
        pasajeVista.lblTituloHistPasajes.setHorizontalAlignment(SwingConstants.CENTER);

        // Aplicamos la fuente personalizada
        try {
            Font montserratFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/Montserrat-Regular.ttf")).deriveFont(Font.PLAIN, 14);
            Font montserratFontTitulo = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/Montserrat-Regular.ttf")).deriveFont(Font.BOLD, 18);

            pasajeVista.lblTituloHistPasajes.setFont(montserratFontTitulo);

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
