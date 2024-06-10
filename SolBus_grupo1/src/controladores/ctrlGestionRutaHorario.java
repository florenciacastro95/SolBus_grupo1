package controladores;

import entidades.Ruta;
import entidades.Horario;
import accesoDatos.RutaData;
import accesoDatos.HorarioData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
    
    
    
}
