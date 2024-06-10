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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import vistas.infGestionRutaHorario;

public class ctrlGestionRutaHorario implements ActionListener, ItemListener {
    
    private Ruta ruta;
    private Horario horario;
    private RutaData rD;
    private HorarioData hD;
    private infGestionRutaHorario gRutHorVista;
    private DefaultTableModel model1 = new DefaultTableModel();
    private DefaultTableModel model2 = new DefaultTableModel();
    
    public ctrlGestionRutaHorario(Ruta ruta, Horario horario, RutaData rD, HorarioData hD, infGestionRutaHorario gRutHorVista) {
        this.ruta = ruta;
        this.horario = horario;
        this.rD = rD;
        this.hD = hD;
        this.gRutHorVista = gRutHorVista;
        armarCabeceraRuta();
        armarCabeceraHor();
        poneteBonito();
        cargarCbRuta((ArrayList) rD.listarRutasDisponibles());
        cargarTablaRuta((ArrayList) rD.listarRutasDisponibles());
        
        gRutHorVista.btnAgregar.addActionListener(this);
        gRutHorVista.btnFiltrar.addActionListener(this);
        gRutHorVista.btnVerTodas.addActionListener(this);
        gRutHorVista.btnBajaRuta.addActionListener(this);
        gRutHorVista.btnActRuta.addActionListener(this);
        gRutHorVista.btnAgregarF.addActionListener(this);
        
        gRutHorVista.cbRuta.addItemListener(this);
    }

    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        //PANEL DE RUTA
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

            if (validarTiempo(gRutHorVista.jtfDuracion.getText())) {
                ruta.setDuracion(LocalTime.parse(gRutHorVista.jtfDuracion.getText()));
            } else {
                JOptionPane.showMessageDialog(null, "Campo inválido de Duración");
            }
            ruta.setEstado(true);
            rD.guardarRuta(ruta);
            limpiarTFRuta();
            limpiarTablaRuta();
            cargarTablaRuta((ArrayList) rD.listarRutasDisponibles());
        }
        if (ae.getSource() == gRutHorVista.btnFiltrar) {
            
            limpiarTablaRuta();
            if (validarString(gRutHorVista.jtfOrigen.getText())) {
                cargarTablaRuta((ArrayList) rD.listarRutasPorOrigen(gRutHorVista.jtfOrigen.getText()));

            } else if (validarString(gRutHorVista.jtfDestino.getText())) {
                //Acá listar por destino
                cargarTablaRuta((ArrayList) rD.listarRutasPorDestino(gRutHorVista.jtfDestino.getText()));
            } else {
                JOptionPane.showMessageDialog(null, "Busqueda inválida. Para poder efectuar esta búsqueda debe ingresar un "
                        + "origen o un destino válidos");
                cargarTablaRuta((ArrayList) rD.listarRutasDisponibles());
            }
            if(tablaRutaVacia()){
                JOptionPane.showMessageDialog(null, "Ese origen o destino no se encuentra entre nuestras rutas aún :'(");
            }
            limpiarTFRuta();
        }
        
        if (ae.getSource() == gRutHorVista.btnVerTodas) {
            
            limpiarTablaRuta();
            cargarTablaRuta((ArrayList) rD.listarRutasDisponibles());
        }
        
        if (ae.getSource() == gRutHorVista.btnBajaRuta) {
            
            int filaSelect = gRutHorVista.tblListarRutas.getSelectedRow();
            if (filaSelect != -1) {
                String mensaje = "¿Está seguro que desea eliminar la ruta?";
                int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    int idRuta = (int) gRutHorVista.tblListarRutas.getValueAt(filaSelect, 0);
                    
                    rD.bajaRuta(idRuta);
                    limpiarTablaRuta();
                    cargarTablaRuta((ArrayList) rD.listarRutasDisponibles());
                }
            }
        }
        
        if (ae.getSource() == gRutHorVista.btnActRuta) {
            int filaSelect = gRutHorVista.tblListarRutas.getSelectedRow();
            if (filaSelect != -1) {
                int idRuta = (int) gRutHorVista.tblListarRutas.getValueAt(filaSelect, 0);
                ruta = rD.buscarRutaPorID(idRuta);
                if(validarString((String) gRutHorVista.tblListarRutas.getValueAt(filaSelect, 1))){
                    ruta.setOrigen((String) gRutHorVista.tblListarRutas.getValueAt(filaSelect, 1));
                }
                if(validarString((String) gRutHorVista.tblListarRutas.getValueAt(filaSelect, 2))){
                    ruta.setDestino((String) gRutHorVista.tblListarRutas.getValueAt(filaSelect, 2));
                }
                if(validarTiempo((String) gRutHorVista.tblListarRutas.getValueAt(filaSelect, 3))){
                    ruta.setDuracion(LocalTime.parse((String) gRutHorVista.tblListarRutas.getValueAt(filaSelect, 3)));
                }
                
                String mensaje = "¿Está seguro que desea actualizar la ruta?";
                int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION){
                    rD.actualizarRuta(ruta);
                    limpiarTablaRuta();
                    cargarTablaRuta((ArrayList) rD.listarRutasDisponibles());
                }
                
            }
            
        }
        
        //PANEL DE HORARIOS
        if(ae.getSource() == gRutHorVista.btnAgregarF){
 
            model2.addRow(new Object[] {"", "Salida", "Llegada"});
        }
        //Falta agregar funcionalidad 
        if(ae.getSource() == gRutHorVista.btnAgregarHor){
            ArrayList<Horario> hor = new ArrayList<Horario>();
            
        }
        /*
        Eliminar y actualizar
        */
    }
    
    @Override
    public void itemStateChanged(ItemEvent ie) {
        if(ie.getStateChange() == ItemEvent.SELECTED){
            if(ie.getSource() == gRutHorVista.cbRuta){
                limpiarTablaHor();
                ArrayList<Horario> hor = new ArrayList<Horario>();
                ruta = (Ruta) gRutHorVista.cbRuta.getSelectedItem();
                hor = (ArrayList<Horario>) hD.listarHorariosPorRuta(ruta);
                cargarTablaHor(hor);
            }         
        }
    }
    
    
    private void armarCabeceraRuta() {
        ArrayList<Object> filaCabecera = new ArrayList<>();
        filaCabecera.add("N° de Ruta");
        filaCabecera.add("Origen");
        filaCabecera.add("Destino");
        filaCabecera.add("Duracion");
        

        for (Object i : filaCabecera) {
            model1.addColumn(i);
        }
        gRutHorVista.tblListarRutas.setModel(model1);
    }
    
    private void armarCabeceraHor() {
        ArrayList<Object> cabHorario = new ArrayList<>();
        cabHorario.add("N° de Horario");
        cabHorario.add("Hora de salida");
        cabHorario.add("Hora de llegada");
        

        for (Object i : cabHorario) {
            model2.addColumn(i);
        }
        gRutHorVista.tblHorarios.setModel(model2);
    }
    
    public void cargarTablaRuta(ArrayList arrayList){
        ArrayList<Ruta> comodin = arrayList;
        
        for(Ruta rut : comodin) {
            model1.addRow(new Object[] {rut.getIdRuta(), rut.getOrigen(), rut.getDestino(), rut.getDuracion()});                    
        }  
    }
    
    public void cargarTablaHor(ArrayList arrayList){
        ArrayList<Horario> comodin = arrayList;
        
        for(Horario hor : comodin) {
            model2.addRow(new Object[] {hor.getIdHorario(), hor.getHoraSalida(), hor.getHoraLlegada()});                    
        }  
    }
    
    public void limpiarTablaRuta(){
        model1.setRowCount(0);
    }
    
    public void limpiarTablaHor(){
        model2.setRowCount(0);
    }
    
    public boolean tablaRutaVacia(){
        return model1.getRowCount() == 0;
    }
    
    public void cargarCbRuta(ArrayList<Ruta> rutas) {
        for (Ruta ruta : rutas) {
            gRutHorVista.cbRuta.addItem(ruta);
        }
    }
    
    public boolean validarString(String s) {
        String regExp = "^[a-zA-Z ]+$";

        return s.matches(regExp);
        //reciclar código está buenísmo =)
    }
    
    public boolean validarTiempo(String s) {
        String regExp = "^([0-1][0-9]|2[0-3]):[0-5][0-9]$";

        return s.matches(regExp);
    }
    
    public void limpiarTFRuta() {
        gRutHorVista.jtfOrigen.setText("");
        gRutHorVista.jtfDestino.setText("");
        gRutHorVista.jtfDuracion.setText("");
    }
    public final void poneteBonito() {

        gRutHorVista.setSize(new Dimension(1000, 620));
        gRutHorVista.setBorder(BorderFactory.createLineBorder(new Color(41, 37, 28), 3));
        gRutHorVista.getContentPane().setBackground(new Color(231, 221, 211));

        // Botones
        gRutHorVista.btnActRuta.setBackground(new Color(41, 37, 28));
        gRutHorVista.btnAgregar.setBackground(new Color(41, 37, 28));
        gRutHorVista.btnAgregarF.setBackground(new Color(41, 37, 28));
        gRutHorVista.btnBajaRuta.setBackground(new Color(41, 37, 28));
        gRutHorVista.btnFiltrar.setBackground(new Color(41, 37, 28));
        gRutHorVista.btnAgregarHor.setBackground(new Color(41, 37, 28));
        gRutHorVista.jButton3.setBackground(new Color(41, 37, 28));
        gRutHorVista.jButton4.setBackground(new Color(41, 37, 28));
        gRutHorVista.btnVerTodas.setBackground(new Color(41, 37, 28));

        gRutHorVista.btnActRuta.setForeground(Color.white);
        gRutHorVista.btnAgregar.setForeground(Color.white);
        gRutHorVista.btnAgregarF.setForeground(Color.white);
        gRutHorVista.btnBajaRuta.setForeground(Color.white);
        gRutHorVista.btnFiltrar.setForeground(Color.white);
        gRutHorVista.btnAgregarHor.setForeground(Color.white);
        gRutHorVista.jButton3.setForeground(Color.white);
        gRutHorVista.jButton4.setForeground(Color.white);
        gRutHorVista.btnVerTodas.setForeground(Color.white);

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
        gRutHorVista.cbRuta.setBackground(new Color(231, 221, 211));

        // Tablas
      
        gRutHorVista.tblHorarios.setBackground(new Color(192, 153, 139));
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
            gRutHorVista.btnAgregarHor.setFont(montserratFont);
            gRutHorVista.jButton3.setFont(montserratFont);
            gRutHorVista.jButton4.setFont(montserratFont);
            gRutHorVista.btnVerTodas.setFont(montserratFont);
            gRutHorVista.jtfDestino.setFont(montserratFont);
            gRutHorVista.jtfDuracion.setFont(montserratFont);
            gRutHorVista.jtfOrigen.setFont(montserratFont);
            gRutHorVista.cbRuta.setFont(montserratFont);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    
    
}
