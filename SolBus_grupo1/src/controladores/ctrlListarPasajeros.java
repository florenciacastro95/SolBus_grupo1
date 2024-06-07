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

/**
 *
 * @author tDev
 */
public class ctrlListarPasajeros implements ActionListener{

    private Pasajero pasajero;
    private PasajeroData pasajeroData;
    private InfListaBajaPasajeros pasajeroVista;
    private DefaultTableModel model = new DefaultTableModel();
    
    public ctrlListarPasajeros(Pasajero pasajero, PasajeroData pasajeroData, InfListaBajaPasajeros pasajeroVista) {
        this.pasajero = pasajero;
        this.pasajeroData = pasajeroData;
        this.pasajeroVista = pasajeroVista;
        pasajeroVista.rbDni.addActionListener(this);
        pasajeroVista.rbNombreApellido.addActionListener(this);
        pasajeroVista.rbVerTodo.addActionListener(this);
        pasajeroVista.rbDni.addActionListener(this);
        armarCabecera();
        
        
    }
    
    private void armarCabecera() {
        ArrayList<Object> filaCabecera = new ArrayList<>();
        filaCabecera.add("Nombre");
        filaCabecera.add("Apellido");
        filaCabecera.add("Dni");
        filaCabecera.add("Correo");
        filaCabecera.add("Telefono");
        

        for (Object i : filaCabecera) {
            model.addColumn(i);
        }
        pasajeroVista.jtListarPasajeros.setModel(model);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == pasajeroVista.rbDni) {
            pasajeroVista.jtNombre.setEnabled(false);
            pasajeroVista.jtDni.setEnabled(true);
        }
        if (e.getSource() == pasajeroVista.rbNombreApellido) {
            pasajeroVista.jtNombre.setEnabled(true);
            pasajeroVista.jtDni.setEnabled(false);
        }
        if (e.getSource() == pasajeroVista.rbVerTodo) {
            //llamar al metodo
        }
        
        if (e.getSource() == pasajeroVista.btnBuscar) {
            if (pasajeroVista.rbDni.isSelected()) {
                int dni = Integer.parseInt(pasajeroVista.jtDni.getText());
                Pasajero pasajero = new Pasajero();
                pasajero = pasajeroData.buscarPasajeroPorDni(dni+"");
                cargarTabla(pasajero);
            }
        }
        
        
    }
    
    public void cargarTabla(Pasajero p){
    
       model.setRowCount(0);
       
       model.addRow(new Object[] {p.getNombre(),p.getApellido(),p.getDni(),p.getCorreo(),p.getTelefono()});
    
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
