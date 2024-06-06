package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entidades.*;
import vistas.*;
import accesoDatos.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class ctrlCargaPasajes implements ActionListener, ItemListener {

    private Pasaje pasaje;
    private PasajeData pasajeData;
    private ColectivoData colectivoData;
    private InfGestionPasajes pasajeVista;
    private PasajeroData pasajeroData;

    public ctrlCargaPasajes(Pasaje pasaje, PasajeData pasajeData, InfGestionPasajes pasajeVista) {
        this.pasaje = pasaje;
        this.pasajeData = pasajeData;
        this.pasajeVista = pasajeVista;
        colectivoData = new ColectivoData();
        pasajeData = new PasajeData();
        pasajeroData = new PasajeroData();
        pasajeVista.btnVenderPasaje.addActionListener(this);
        pasajeVista.btnEmitirRecibo.addActionListener(this);
        pasajeVista.cbRuta.addItemListener(this);
        pasajeVista.rbRegistrado.addActionListener(this);
        pasajeVista.rbNoRegistrado.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //mirar la capacidad de el colectivo
        if (e.getSource() == pasajeVista.btnVenderPasaje) {
            Pasaje pasaje;
            Pasajero pasajero;
            LocalTime horita = ((Horario) pasajeVista.cbHorario.getSelectedItem()).getHoraSalida();
            Colectivo colectivo = (Colectivo) pasajeVista.cbColectivos.getSelectedItem();
            String nombre, apellido, dni;
            apellido = pasajeVista.txtApellido.getText();
            nombre = pasajeVista.txtNombre.getText();
            dni = pasajeVista.txtDni.getText();
            pasajero = new Pasajero(nombre, apellido, dni, null, null);
            pasajeroData.guardarPasajero(pasajero);
            pasaje = new Pasaje(pasajero, colectivo, (Ruta) pasajeVista.cbRuta.getSelectedItem(), LocalDate.now(), horita, 0, 0);

            pasajeData.venderPasaje(pasaje);

        }

        if (e.getSource() == pasajeVista.rbNoRegistrado) {

            pasajeVista.txtDni.setEnabled(true);
            pasajeVista.txtApellido.setEnabled(true);
            pasajeVista.txtNombre.setEnabled(true);
            pasajeVista.txtDniRegistrado.setEnabled(false);

        } else if (e.getSource() == pasajeVista.rbRegistrado) {
            pasajeVista.txtDni.setEnabled(false);
            pasajeVista.txtApellido.setEnabled(false);
            pasajeVista.txtNombre.setEnabled(false);
            pasajeVista.txtDniRegistrado.setEnabled(true);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        if (ie.getStateChange() == ItemEvent.SELECTED) {
            if (ie.getSource() == pasajeVista.cbRuta) {
                pasajeVista.cbHorario.removeAllItems();
                HorarioData hd = new HorarioData();
                Ruta itemSeleccionado = (Ruta) pasajeVista.cbRuta.getSelectedItem();
                ArrayList<Horario> horarios = new ArrayList<>();

                horarios = (ArrayList<Horario>) hd.listarHorariosPorRuta(itemSeleccionado);
                for (Horario horario : horarios) {
                    System.out.println(horario.toString());
                    pasajeVista.cbHorario.addItem(horario);
                }
                if (horarios.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay horarios activos para esta ruta. Agregue horarios");
                }
            }
            if (ie.getSource() == pasajeVista.rbNoRegistrado) {
                pasajeVista.txtDni.setEnabled(true);
                pasajeVista.txtApellido.setEnabled(true);
                pasajeVista.txtNombre.setEnabled(true);
            }
        }
    }

    public boolean validarEnteros(String s) {

        String regExp = "^-?\\d+$";

        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(s);

        return m.matches();
    }

    public boolean validarString(String s) {
        String regExp = "^[a-zA-Z ]+$";

        return s.matches(regExp);
        //estoy perdiendo salud mental con este paquete de control
    }

}
