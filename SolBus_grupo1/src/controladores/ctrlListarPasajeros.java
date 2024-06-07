/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import accesoDatos.*;
import entidades.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
        addDocumentListeners();
        
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
            pasajeroVista.jtNombre.setEnabled(false);
            pasajeroVista.jtDni.setEnabled(false);
            actualizarTablaConPasajeros((ArrayList<Pasajero>) pasajeroData.listarPasajeros());
            
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


        private void addDocumentListeners() {
        pasajeroVista.jtDni.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarPorDni();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarPorDni();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarPorDni();
            }
        });

        pasajeroVista.jtNombre.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarPorNombreApellido();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarPorNombreApellido();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarPorNombreApellido();
            }
        });
    }
    
        private void buscarPorDni() {
            System.out.println("viva la pepadni");
        if (pasajeroVista.rbDni.isSelected()) {
            String dniText = pasajeroVista.jtDni.getText();
            if (!dniText.isEmpty()) {
                ArrayList<Pasajero> pasajeros = (ArrayList<Pasajero>) pasajeroData.listarPasajerosPorPrefijoDni(dniText);
                actualizarTablaConPasajeros(pasajeros);
            } else {
                model.setRowCount(0); 
            }
        }
    }

    private void buscarPorNombreApellido() {
        System.out.println("viva la pepa");
        if (pasajeroVista.rbNombreApellido.isSelected()) {
            String nombreApellido = pasajeroVista.jtNombre.getText();
            if (!nombreApellido.isEmpty()) {
                ArrayList<Pasajero> pasajeros = (ArrayList<Pasajero>) pasajeroData.listarPasajerosPorPrefijoApellido(nombreApellido);
                actualizarTablaConPasajeros(pasajeros);
            } else {
                model.setRowCount(0); 
            }
        }
    }
    
        private void actualizarTablaConPasajeros(ArrayList<Pasajero> pasajeros) {
        // Limpia la tabla
        model.setRowCount(0);

        // Añade los pasajeros al modelo de la tabla
        for (Pasajero pasajero : pasajeros) {
            model.addRow(new Object[]{
                pasajero.getNombre(),
                pasajero.getApellido(),
                pasajero.getDni(),
                pasajero.getCorreo(),
                pasajero.getTelefono()
            });
        }
    }
}
