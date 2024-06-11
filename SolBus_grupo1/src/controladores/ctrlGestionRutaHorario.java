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
    
    private Ruta rutaR;
    private Ruta rutaH;
    private Horario horario;
    private RutaData rD;
    private HorarioData hD;
    private infGestionRutaHorario gRutHorVista;
    private TableModelIdBloqueado model1 =  new TableModelIdBloqueado();
    private TableModelIdBloqueado model2 =  new TableModelIdBloqueado();
    
    public ctrlGestionRutaHorario(Ruta rutaR, Ruta rutaH, Horario horario, RutaData rD, HorarioData hD, infGestionRutaHorario gRutHorVista) {
        this.rutaR = rutaR;
        this.rutaH = rutaH;
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
        gRutHorVista.btnActHor.addActionListener(this);
        gRutHorVista.btnAgregarHor.addActionListener(this);
        gRutHorVista.btnBajaHor.addActionListener(this);
                
        gRutHorVista.cbRuta.addItemListener(this);
    }

    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        //PANEL DE RUTA
        if (ae.getSource() == gRutHorVista.btnAgregar) {
            
            if (validarString(gRutHorVista.jtfOrigen.getText())) {
                rutaR.setOrigen(gRutHorVista.jtfOrigen.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Campo inválido de Origen");
            }

            if (validarString(gRutHorVista.jtfDestino.getText())) {
                rutaR.setDestino(gRutHorVista.jtfDestino.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Campo inválido de Destino");
            }

            if (validarTiempo(gRutHorVista.jtfDuracion.getText())) {
                rutaR.setDuracion(LocalTime.parse(gRutHorVista.jtfDuracion.getText()));
            } else {
                JOptionPane.showMessageDialog(null, "Campo inválido de Duración");
            }
            rutaR.setEstado(true);
            rD.guardarRuta(rutaR);
            limpiarTFRuta();
            limpiarTablaRuta();
            cargarTablaRuta((ArrayList) rD.listarRutasDisponibles());
        }
        if (ae.getSource() == gRutHorVista.btnFiltrar) {
            
            limpiarTablaRuta();
            if (validarString(gRutHorVista.jtfOrigen.getText())) {
                cargarTablaRuta((ArrayList) rD.listarRutasPorOrigen(gRutHorVista.jtfOrigen.getText()));

            } else if (validarString(gRutHorVista.jtfDestino.getText())) {
                cargarTablaRuta((ArrayList) rD.listarRutasPorDestino(gRutHorVista.jtfDestino.getText()));
            } else {
                JOptionPane.showMessageDialog(null, "Busqueda inválida. Para poder efectuar esta búsqueda debe ingresar un "
                        + "origen o un destino válidos");
                cargarTablaRuta((ArrayList) rD.listarRutasDisponibles());
            }
            if(tablaRutaVacia()){
                JOptionPane.showMessageDialog(null, "Ese origen o destino no se encuentra entre nuestras rutas aún");
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
                rutaR = rD.buscarRutaPorID(idRuta);
                if(validarString((String) gRutHorVista.tblListarRutas.getValueAt(filaSelect, 1))){
                    rutaR.setOrigen((String) gRutHorVista.tblListarRutas.getValueAt(filaSelect, 1));
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese un origen valido");
                }
                if(validarString((String) gRutHorVista.tblListarRutas.getValueAt(filaSelect, 2))){
                    rutaR.setDestino((String) gRutHorVista.tblListarRutas.getValueAt(filaSelect, 2));
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese un destino valido");
                }
                if(validarTiempo(gRutHorVista.tblListarRutas.getValueAt(filaSelect, 3).toString())){
                    rutaR.setDuracion(LocalTime.parse(gRutHorVista.tblListarRutas.getValueAt(filaSelect, 3).toString()));
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese una duración válida");
                }
                
                String mensaje = "¿Está seguro que desea actualizar la ruta?";
                int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION){
                    rD.actualizarRuta(rutaR);
                    limpiarTablaRuta();
                    cargarTablaRuta((ArrayList) rD.listarRutasDisponibles());
                }
                
            }
            
        }
        
        //PANEL DE HORARIOS
        if(ae.getSource() == gRutHorVista.btnAgregarF){
 
            model2.addRow(new Object[] {"", "Salida", "Llegada"});
        }
        
        if(ae.getSource() == gRutHorVista.btnAgregarHor){
            ArrayList<Horario> hor = new ArrayList<Horario>();
            for (int i = 0; i < gRutHorVista.tblHorarios.getRowCount(); i++) {
                if(gRutHorVista.tblHorarios.getValueAt(i, 0).equals("")){
                    if(validarTiempo(gRutHorVista.tblHorarios.getValueAt(i, 1).toString()) &&  validarTiempo(gRutHorVista.tblHorarios.getValueAt(i, 2).toString())){
                        Horario h = new Horario();
                        h.setHoraSalida(LocalTime.parse(gRutHorVista.tblHorarios.getValueAt(i, 1).toString()));
                        h.setHoraLlegada(LocalTime.parse(gRutHorVista.tblHorarios.getValueAt(i, 2).toString()));
                        h.setRuta(rutaH);
                        h.setEstado(true);
                        //voy a moriiir
                        hor.add(h);
                    } else {
                        JOptionPane.showMessageDialog(null, "Hora de salida y/o de llegada inválida/s");
                        //Estoy cansada, Jefe
                    }
                    
                }
            }
            
            for(Horario h : hor){
                hD.guardarHorario(h);
            }
            
        }
        
        if(ae.getSource() == gRutHorVista.btnBajaHor){
            
            int filaSelect = gRutHorVista.tblHorarios.getSelectedRow();
            if (filaSelect != -1) {
                String mensaje = "¿Está seguro que desea eliminar el horario?";
                int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    int idHorario = (int) gRutHorVista.tblHorarios.getValueAt(filaSelect, 0);
                    
                    hD.bajaHorario(idHorario);
                    limpiarTablaHor();
                    cargarTablaHor((ArrayList) hD.listarHorariosPorRuta(rutaH));
                }
            }
        }
        
        if(ae.getSource() == gRutHorVista.btnActHor){
            int filaSelect = gRutHorVista.tblHorarios.getSelectedRow();
            if (filaSelect != -1) {
                
                if(validarInt(gRutHorVista.tblHorarios.getValueAt(filaSelect, 0).toString())){
                    int idHor= (int) gRutHorVista.tblHorarios.getValueAt(filaSelect, 0);
                    horario = hD.buscarHorarioPorId(idHor);
                } else {
                    JOptionPane.showMessageDialog(null, "Ese horario no es parte de nuestro servicio y no puede ser modificado");
                }
                
                if (validarTiempo(gRutHorVista.tblHorarios.getValueAt(filaSelect, 1).toString()) &&  validarTiempo(gRutHorVista.tblHorarios.getValueAt(filaSelect, 2).toString())){
                    horario.setHoraSalida(LocalTime.parse(gRutHorVista.tblHorarios.getValueAt(filaSelect, 1).toString()));
                    horario.setHoraLlegada(LocalTime.parse(gRutHorVista.tblHorarios.getValueAt(filaSelect, 2).toString()));
                } else {
                    JOptionPane.showMessageDialog(null, "Hora de salida y/o de llegada incorrectas, revise e intente nuevamente");
                }
                
                
                String mensaje = "¿Está seguro que desea actualizar el horario?";
                int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION){
                    hD.actualizarHorario(horario);
                    limpiarTablaHor();
                    cargarTablaHor((ArrayList) hD.listarHorariosPorRuta(rutaH));
                }
                
            }
            
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent ie) {
        if(ie.getStateChange() == ItemEvent.SELECTED){
            if(ie.getSource() == gRutHorVista.cbRuta){
                limpiarTablaHor();
                ArrayList<Horario> hor = new ArrayList<Horario>();
                rutaH = (Ruta) gRutHorVista.cbRuta.getSelectedItem();
                hor = (ArrayList<Horario>) hD.listarHorariosPorRuta(rutaH);
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
    
    public boolean validarInt(String s) {
        String regExp = "^\\d+$"; 
        return s.matches(regExp);
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
    
    class TableModelIdBloqueado extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            // Bloquear la primera columna (columna 0)
            return column != 0;
        }
    }
    public final void poneteBonito() {

    gRutHorVista.setSize(new Dimension(1000, 620));
    gRutHorVista.setBorder(BorderFactory.createLineBorder(new Color(202, 40, 43), 3));
    gRutHorVista.getContentPane().setBackground(new Color(240, 240, 240)); // Gris claro

    // Botones
    gRutHorVista.btnActRuta.setBackground(new Color(202, 40, 43)); // Color acento
    gRutHorVista.btnAgregar.setBackground(new Color(202, 40, 43)); // Color acento
    gRutHorVista.btnAgregarF.setBackground(new Color(202, 40, 43)); // Color acento
    gRutHorVista.btnBajaRuta.setBackground(new Color(202, 40, 43)); // Color acento
    gRutHorVista.btnFiltrar.setBackground(new Color(202, 40, 43)); // Color acento
    gRutHorVista.btnAgregarHor.setBackground(new Color(202, 40, 43)); // Color acento
    gRutHorVista.btnBajaHor.setBackground(new Color(202, 40, 43)); // Color acento
    gRutHorVista.btnActHor.setBackground(new Color(202, 40, 43)); // Color acento
    gRutHorVista.btnVerTodas.setBackground(new Color(202, 40, 43)); // Color acento
    gRutHorVista.btnActRuta.setForeground(Color.white);
    gRutHorVista.btnAgregar.setForeground(Color.white);
    gRutHorVista.btnAgregarF.setForeground(Color.white);
    gRutHorVista.btnBajaRuta.setForeground(Color.white);
    gRutHorVista.btnFiltrar.setForeground(Color.white);
    gRutHorVista.btnAgregarHor.setForeground(Color.white);
    gRutHorVista.btnBajaHor.setForeground(Color.white);
    gRutHorVista.btnActHor.setForeground(Color.white);
    gRutHorVista.btnVerTodas.setForeground(Color.white);

    // Labels
    gRutHorVista.jLabel1.setForeground(new Color(41, 37, 28));
    gRutHorVista.jLabel2.setForeground(new Color(41, 37, 28));
    gRutHorVista.jLabel3.setForeground(new Color(41, 37, 28));
    gRutHorVista.jLabel4.setForeground(new Color(41, 37, 28));
    gRutHorVista.jLabel5.setForeground(new Color(41, 37, 28));
    gRutHorVista.jLabel6.setForeground(new Color(41, 37, 28));

    // Panels
    gRutHorVista.pnlHorario.setBackground(new Color(240, 240, 240)); // Gris claro
    gRutHorVista.pnlRuta.setBackground(new Color(240, 240, 240)); // Gris claro

    // TextFields
    gRutHorVista.jtfDestino.setBackground(new Color(220, 220, 220)); // Gris medio
    gRutHorVista.jtfDuracion.setBackground(new Color(220, 220, 220)); // Gris medio
    gRutHorVista.jtfOrigen.setBackground(new Color(220, 220, 220)); // Gris medio

    // ComboBox
    gRutHorVista.cbRuta.setBackground(new Color(240, 240, 240)); // Gris claro

    // Tablas
    gRutHorVista.tblHorarios.setBackground(new Color(220, 220, 220)); // Gris medio
    gRutHorVista.tblListarRutas.setBackground(new Color(220, 220, 220)); // Gris medio

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
            gRutHorVista.btnBajaHor.setFont(montserratFont);
            gRutHorVista.btnActHor.setFont(montserratFont);
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
