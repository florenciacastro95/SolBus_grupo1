
package controladores;

import accesoDatos.*;
import entidades.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import vistas.*;

public class ctrlGestionColectivos {
    private Colectivo colectivo;
    private ColectivoData colectivoData;
    private infGestionColectivo colectivoVista;

    public ctrlGestionColectivos() {
        poneteBonito();
    }
    
    public final void poneteBonito() {

        colectivoVista.setSize(new Dimension(570, 620));
        colectivoVista.setBorder(BorderFactory.createLineBorder(new Color(41, 37, 28), 3));
        colectivoVista.getContentPane().setBackground(new Color(231, 221, 211));

        // Botones
        colectivoVista.btnActualizarColes.setBackground(new Color(41, 37, 28));
        colectivoVista.btnAgregarCole.setBackground(new Color(41, 37, 28));
        colectivoVista.btnEliminarColes.setBackground(new Color(41, 37, 28));
        colectivoVista.btnFiltrarCole.setBackground(new Color(41, 37, 28));

        colectivoVista.btnActualizarColes.setForeground(Color.white);
        colectivoVista.btnAgregarCole.setForeground(Color.white);
        colectivoVista.btnEliminarColes.setForeground(Color.white);
        colectivoVista.btnFiltrarCole.setForeground(Color.white);

        // ComboBox
        colectivoVista.cbHorario.setBackground(new Color(231, 221, 211));

        // Label
        colectivoVista.lblTitulo.setForeground(new Color(41, 37, 28));
        colectivoVista.lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        // JScrollPane
        colectivoVista.jScrollPane2.setBackground(new Color(231, 221, 211));

        // Table
        colectivoVista.tblColes.setBackground(new Color(192, 153, 139));

        // Aplicamos la fuente personalizada
        try {
            Font montserratFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/Montserrat-Regular.ttf")).deriveFont(Font.PLAIN, 14);
            Font montserratFontTitulo = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/Montserrat-Regular.ttf")).deriveFont(Font.BOLD, 18);

            colectivoVista.lblTitulo.setFont(montserratFontTitulo);

            colectivoVista.btnActualizarColes.setFont(montserratFont);
            colectivoVista.btnAgregarCole.setFont(montserratFont);
            colectivoVista.btnEliminarColes.setFont(montserratFont);
            colectivoVista.btnFiltrarCole.setFont(montserratFont);
            colectivoVista.cbHorario.setFont(montserratFont);
            colectivoVista.tblColes.setFont(montserratFont);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
    
}
