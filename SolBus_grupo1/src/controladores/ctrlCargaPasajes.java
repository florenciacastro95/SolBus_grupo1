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


//HAY QUE AGREGAR VALIDACIÓN DE PASAJES SEGUN CAPCIDAD DE COLECTIVO Y ASIENTOS DISPONIBLES
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
            LocalTime horita = null;
            boolean bandera = true;

            Colectivo colectivo = (Colectivo) pasajeVista.cbColectivos.getSelectedItem();
            String nombre = "", apellido = "", dni = "";

            if (validarString(pasajeVista.txtApellido.getText())) {
                apellido = pasajeVista.txtApellido.getText();
            } else {
                bandera = false;
                JOptionPane.showMessageDialog(null, "Apellido inválido");
            }
            if (validarString(pasajeVista.txtNombre.getText())) {
                nombre = pasajeVista.txtNombre.getText();
            } else {
                bandera = false;
                JOptionPane.showMessageDialog(null, "Nombre inválido");
            }
            if (validarEnteros(pasajeVista.txtDni.getText()) && validarDniTam(pasajeVista.txtDni.getText().length())) {
                dni = pasajeVista.txtDni.getText();
            } else if (validarEnteros(pasajeVista.txtDni.getText()) && !validarDniTam(pasajeVista.txtDni.getText().length())) {
                bandera = false;
                JOptionPane.showMessageDialog(null, "El DNI debe contener 7 u 8 dígitos");
            } else if (!validarEnteros(pasajeVista.txtDni.getText())) {
                bandera = false;
                JOptionPane.showMessageDialog(null, "El DNI solo debe contener números");
            } else {
                bandera = false;
                JOptionPane.showMessageDialog(null, "El DNI solo debe contener 7 u 8 dígitos. No se admiten letras");
            }
            if (pasajeVista.cbHorario.getSelectedItem() != null) {
                horita = ((Horario) pasajeVista.cbHorario.getSelectedItem()).getHoraSalida();
            } else {
                bandera = false;
                JOptionPane.showMessageDialog(null, "No se puede vender un pasaje sin horario");
            }
            if (bandera && horita != null) {
                pasajero = new Pasajero(nombre, apellido, dni, null, null);
                pasajeroData.guardarPasajero(pasajero);
                pasaje = new Pasaje(pasajero, colectivo, (Ruta) pasajeVista.cbRuta.getSelectedItem(), LocalDate.now(), horita, 14, 0);
                pasajeData.venderPasaje(pasaje);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo vender pasaje");
            }
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

    //cambié expresión regular para que acepte todos las tildes por si metemos
    //nombres en otros idiomas latinos como apellido Müller o nombre François
    public boolean validarString(String s) {
        String regExp = "^[\\p{L}\\p{M} .'-]+$";

        return s.matches(regExp);
        //estoy perdiendo salud mental con este paquete de control
    }

    public boolean validarDniTam(int tam) {
        return tam == 8 || tam == 7;
    }

}
