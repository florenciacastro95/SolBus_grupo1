/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import accesoDatos.*;
import entidades.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    }

    public boolean validarDniTam(int tam) {
        return tam == 8 || tam == 7;
    }

    public boolean validarString(String s) {
        String regExp = "^[\\p{L}\\p{M} .'-]+$";

        return s.matches(regExp);
        //estoy perdiendo salud mental con este paquete de control
    }
}
