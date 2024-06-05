package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entidades.*;
import vistas.*;
import accesoDatos.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ctrlCargaPasajes implements ActionListener, ItemListener {

    private Pasaje pasaje;
    private PasajeData pasajeData;
    private InfGestionPasajes pasajeVista;

    public ctrlCargaPasajes(Pasaje pasaje, PasajeData pasajeData, InfGestionPasajes pasajeVista) {
        this.pasaje = pasaje;
        this.pasajeData = pasajeData;
        this.pasajeVista = pasajeVista;

        pasajeVista.btnVenderPasaje.addActionListener(this);
        pasajeVista.btnEmitirRecibo.addActionListener(this);
        pasajeVista.cbRuta.addItemListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == pasajeVista.btnVenderPasaje) {

            if (pasajeVista.rbNoRegistrado.isSelected()) {
                // El radio button rbNoRegistrado está seleccionado
                // Realiza las acciones necesarias
            } else if (pasajeVista.rbRegistrado.isSelected()) {
                // El radio button rbRegistrado está seleccionado
                // Realiza las acciones necesarias
            } else {
                // Ningún radio button está seleccionado
                // Realiza las acciones necesarias
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

    @Override
    public void itemStateChanged(ItemEvent ie) {
        pasajeVista.cbHorario.removeAllItems();
        HorarioData hd = new HorarioData();
        Ruta itemSeleccionado = (Ruta) pasajeVista.cbRuta.getSelectedItem();
        ArrayList<Horario> horarios = new ArrayList<>();

        horarios = (ArrayList<Horario>) hd.listarHorariosPorRuta(itemSeleccionado);
        for (Horario horario : horarios) {
            System.out.println(horario.toString());
            pasajeVista.cbHorario.addItem(horario);
        }
    }
}
