package controladores;

import entidades.Ruta;
import entidades.Horario;
import accesoDatos.RutaData;
import accesoDatos.HorarioData;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import vistas.infGestionRutaHorario;

public class ctrlGestionRutaHorario implements ActionListener {
    
    private Ruta ruta;
    private Horario horario;
    private RutaData rD;
    private HorarioData hD;
    private infGestionRutaHorario gRutHorVista;

    public ctrlGestionRutaHorario(Ruta ruta, Horario horario, RutaData rD, HorarioData hD, infGestionRutaHorario gRutHorVista) {
        this.ruta = ruta;
        this.horario = horario;
        this.rD = rD;
        this.hD = hD;
        this.gRutHorVista = gRutHorVista;
        gRutHorVista.cargarTablaRuta((ArrayList) rD.listarRutasDisponibles());
        gRutHorVista.btnAgregar.addActionListener(this);
        gRutHorVista.btnFiltrar.addActionListener(this);
        gRutHorVista.btnBajaRuta.addActionListener(this);
        
        poneteBonito();
    }

    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == gRutHorVista.btnAgregar) {
            
            if (validarString(gRutHorVista.jtfOrigen.getText())) {
                ruta.setOrigen(gRutHorVista.jtfOrigen.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Campo inválido de Origen");
            }

            if (validarString(gRutHorVista.jtfDestino.getText())) {
                ruta.setDestino(gRutHorVista.jtfDestino.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Campo inválido de Destino");
            }

            if (validarDuracion(gRutHorVista.jtfDuracion.getText())) {
                ruta.setDuracion(LocalTime.parse(gRutHorVista.jtfDuracion.getText()));
            } else {
                JOptionPane.showMessageDialog(null, "Campo inválido de Duración");
            }
            ruta.setEstado(true);
            rD.guardarRuta(ruta);
            limpiarTFRuta();
            gRutHorVista.cargarTablaRuta((ArrayList) rD.listarRutasDisponibles());
        }
        if (ae.getSource() == gRutHorVista.btnFiltrar) {
            /*
            A MEJORAR:
                *   Que envíe un mensaje si el origen o el destino no existe en SolBus
                *   Que el Internal Frame inicie con todas las rutas listadas
                *   Actualizar las rutas listadas luego de guardar, eliminar y actualizar.
                *   Btn ver todas. O volver a radioButton.
            */
            gRutHorVista.limpiarTablaRuta();
            if (validarString(gRutHorVista.jtfOrigen.getText())) {
                gRutHorVista.cargarTablaRuta((ArrayList) rD.listarRutasPorOrigen(gRutHorVista.jtfOrigen.getText()));

            } else if (validarString(gRutHorVista.jtfDestino.getText())) {
                //Acá listar por destino
                gRutHorVista.cargarTablaRuta((ArrayList) rD.listarRutasPorDestino(gRutHorVista.jtfDestino.getText()));
            } else {
                JOptionPane.showMessageDialog(null, "Busqueda inválida. Para poder efectuar esta búsqueda debe ingresar un "
                        + "origen o un destino válidos");
                gRutHorVista.cargarTablaRuta((ArrayList) rD.listarRutasDisponibles());
            }
            limpiarTFRuta();
        }
        if (ae.getSource() == gRutHorVista.btnBajaRuta) {
            int filaSelect = gRutHorVista.tblListarRutas.getSelectedRow();
            if (filaSelect != -1) {
                String mensaje = "¿Está seguro que desea eliminar la ruta?";
                int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    int idRuta = (int) gRutHorVista.tblListarRutas.getValueAt(filaSelect, 0);
                    
                    rD.bajaRuta(idRuta);
                    gRutHorVista.limpiarTablaRuta();
                    gRutHorVista.cargarTablaRuta((ArrayList) rD.listarRutasDisponibles());
  
                }
            }
        }
    }
    
    public boolean validarString(String s) {
        String regExp = "^[a-zA-Z ]+$";

        return s.matches(regExp);
        //reciclar código está buenísmo =)
    }
    
    public boolean validarDuracion(String s) {
        String regExp = "^([0-1][0-9]|2[0-3]):[0-5][0-9]$";

        return s.matches(regExp);
    }
    
    public void limpiarTFRuta() {
        gRutHorVista.jtfOrigen.setText("");
        gRutHorVista.jtfDestino.setText("");
        gRutHorVista.jtfDuracion.setText("");
    }
    public final void poneteBonito() {

        gRutHorVista.setSize(new Dimension(570, 620));
        gRutHorVista.setBorder(BorderFactory.createLineBorder(new Color(41, 37, 28), 3));
        gRutHorVista.getContentPane().setBackground(new Color(231, 221, 211));

        // Botones
        gRutHorVista.btnActRuta.setBackground(new Color(41, 37, 28));
        gRutHorVista.btnAgregar.setBackground(new Color(41, 37, 28));
        gRutHorVista.btnAgregarF.setBackground(new Color(41, 37, 28));
        gRutHorVista.btnBajaRuta.setBackground(new Color(41, 37, 28));
        gRutHorVista.btnFiltrar.setBackground(new Color(41, 37, 28));
        gRutHorVista.jButton2.setBackground(new Color(41, 37, 28));
        gRutHorVista.jButton3.setBackground(new Color(41, 37, 28));
        gRutHorVista.jButton4.setBackground(new Color(41, 37, 28));
        gRutHorVista.jButton5.setBackground(new Color(41, 37, 28));

        gRutHorVista.btnActRuta.setForeground(Color.white);
        gRutHorVista.btnAgregar.setForeground(Color.white);
        gRutHorVista.btnAgregarF.setForeground(Color.white);
        gRutHorVista.btnBajaRuta.setForeground(Color.white);
        gRutHorVista.btnFiltrar.setForeground(Color.white);
        gRutHorVista.jButton2.setForeground(Color.white);
        gRutHorVista.jButton3.setForeground(Color.white);
        gRutHorVista.jButton4.setForeground(Color.white);
        gRutHorVista.jButton5.setForeground(Color.white);

        // Labels
        gRutHorVista.jLabel1.setForeground(new Color(41, 37, 28));
        gRutHorVista.jLabel2.setForeground(new Color(41, 37, 28));
        gRutHorVista.jLabel3.setForeground(new Color(41, 37, 28));
        gRutHorVista.jLabel4.setForeground(new Color(41, 37, 28));
        gRutHorVista.jLabel5.setForeground(new Color(41, 37, 28));
        gRutHorVista.jLabel6.setForeground(new Color(41, 37, 28));

        // Panels
        gRutHorVista.pnlHorario.setBackground(new Color(231, 221, 211));
        gRutHorVista.pnlRuta.setBackground(new Color(231, 221, 211));

        // TextFields
        gRutHorVista.jtfDestino.setBackground(new Color(192, 153, 139));
        gRutHorVista.jtfDuracion.setBackground(new Color(192, 153, 139));
        gRutHorVista.jtfOrigen.setBackground(new Color(192, 153, 139));

        // ComboBox
        gRutHorVista.cbHorario.setBackground(new Color(231, 221, 211));

        // Tablas
      
        gRutHorVista.jTable2.setBackground(new Color(192, 153, 139));
        gRutHorVista.tblListarRutas.setBackground(new Color(192, 153, 139));

        // Centramos los títulos
        gRutHorVista.jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        gRutHorVista.jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        gRutHorVista.jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        gRutHorVista.jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        gRutHorVista.jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        gRutHorVista.jLabel6.setHorizontalAlignment(SwingConstants.CENTER);

        // Aplicamos la fuente personalizada
        try {
            Font montserratFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/Montserrat-Regular.ttf")).deriveFont(Font.PLAIN, 14);
            Font montserratFontTitulo = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/Montserrat-Regular.ttf")).deriveFont(Font.BOLD, 18);

            gRutHorVista.jLabel1.setFont(montserratFontTitulo);
            gRutHorVista.jLabel2.setFont(montserratFontTitulo);
            gRutHorVista.jLabel3.setFont(montserratFontTitulo);
            gRutHorVista.jLabel4.setFont(montserratFontTitulo);
            gRutHorVista.jLabel5.setFont(montserratFontTitulo);
            gRutHorVista.jLabel6.setFont(montserratFontTitulo);

            gRutHorVista.btnActRuta.setFont(montserratFont);
            gRutHorVista.btnAgregar.setFont(montserratFont);
            gRutHorVista.btnAgregarF.setFont(montserratFont);
            gRutHorVista.btnBajaRuta.setFont(montserratFont);
            gRutHorVista.btnFiltrar.setFont(montserratFont);
            gRutHorVista.jButton2.setFont(montserratFont);
            gRutHorVista.jButton3.setFont(montserratFont);
            gRutHorVista.jButton4.setFont(montserratFont);
            gRutHorVista.jButton5.setFont(montserratFont);
            gRutHorVista.jtfDestino.setFont(montserratFont);
            gRutHorVista.jtfDuracion.setFont(montserratFont);
            gRutHorVista.jtfOrigen.setFont(montserratFont);
            gRutHorVista.cbHorario.setFont(montserratFont);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
