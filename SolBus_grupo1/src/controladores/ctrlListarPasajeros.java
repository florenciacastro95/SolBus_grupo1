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
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import vistas.*;

/**
 *
 * @author tDev
 */
public class ctrlListarPasajeros implements ActionListener {

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
        pasajeroVista.btnBorrar.addActionListener(this);
        pasajeroVista.btnRegistrar.addActionListener(this);
        pasajeroVista.btnActualizar1.addActionListener(this);
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
        if (e.getSource() == pasajeroVista.btnBorrar) {
            int selectedRow = pasajeroVista.jtListarPasajeros.getSelectedRow();
            if (selectedRow != -1) {
                String nombre = (String) pasajeroVista.jtListarPasajeros.getValueAt(selectedRow, 0);
                String apellido = (String) pasajeroVista.jtListarPasajeros.getValueAt(selectedRow, 1);

                Pasajero pasajeroAEliminar = pasajeroData.buscarPasajeroPorApellido(apellido);
                if (pasajeroAEliminar != null) {
                    int confirm = JOptionPane.showConfirmDialog(pasajeroVista,
                            "¿Está seguro que desea borrar al pasajero " + nombre + " " + apellido + "?",
                            "Confirmar Borrado",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        pasajeroData.borrarPasajero(pasajeroAEliminar.getIdPasajero());
                        model.removeRow(selectedRow);
                    }
                } else {
                    JOptionPane.showMessageDialog(pasajeroVista, "No se encontró el pasajero.");
                }
            } else {
                JOptionPane.showMessageDialog(pasajeroVista, "Seleccione un pasajero para borrar.");
            }
        }

        if (e.getSource() == pasajeroVista.btnRegistrar) {
            String nombre = pasajeroVista.txtNombre.getText();
            String apellido = pasajeroVista.txtApellido.getText();
            String dniText = pasajeroVista.txtDni.getText();
            String correo = pasajeroVista.txtCorreo.getText();
            String telefono = pasajeroVista.jTextField5.getText();

            if (!validarString(nombre)) {
                JOptionPane.showMessageDialog(pasajeroVista, "Nombre inválido.");

            }

            if (!validarString(apellido)) {
                JOptionPane.showMessageDialog(pasajeroVista, "Apellido inválido.");

            }

            if (!dniText.matches("\\d+") || !validarDniTam(dniText.length())) {
                JOptionPane.showMessageDialog(pasajeroVista, "DNI inválido.");

            }

            Pasajero nuevoPasajero = new Pasajero();
            nuevoPasajero.setNombre(nombre);
            nuevoPasajero.setApellido(apellido);
            nuevoPasajero.setDni(dniText);
            if (!correo.isEmpty()) {
                nuevoPasajero.setCorreo(correo);
            }
            if (!telefono.isEmpty()) {
                nuevoPasajero.setTelefono(telefono);
            }

            pasajeroData.guardarPasajero(nuevoPasajero);
            actualizarTablaConPasajeros((ArrayList<Pasajero>) pasajeroData.listarPasajeros());

            pasajeroVista.txtNombre.setText("");
            pasajeroVista.txtApellido.setText("");
            pasajeroVista.txtDni.setText("");
            pasajeroVista.txtCorreo.setText("");
            pasajeroVista.jTextField5.setText("");
        }

        if (e.getSource() == pasajeroVista.btnActualizar1) {
            int filaSeleccionada = pasajeroVista.jtListarPasajeros.getSelectedRow();
            if (filaSeleccionada != -1) {
                String dni = (String) pasajeroVista.jtListarPasajeros.getValueAt(filaSeleccionada, 2);
                Pasajero pasajero = pasajeroData.buscarPasajeroPorDni(dni);

                if (pasajero != null) {
                    int idPasajero = pasajero.getIdPasajero();

                    String nuevoNombre = (String) pasajeroVista.jtListarPasajeros.getValueAt(filaSeleccionada, 0);
                    String nuevoApellido = (String) pasajeroVista.jtListarPasajeros.getValueAt(filaSeleccionada, 1);
                    String nuevoDni = (String) pasajeroVista.jtListarPasajeros.getValueAt(filaSeleccionada, 2);
                    String nuevoCorreo = (String) pasajeroVista.jtListarPasajeros.getValueAt(filaSeleccionada, 3);
                    String nuevoTelefono = (String) pasajeroVista.jtListarPasajeros.getValueAt(filaSeleccionada, 4);
                    Pasajero pasajeroActualizado = new Pasajero(idPasajero, nuevoNombre, nuevoApellido, nuevoDni, nuevoCorreo, nuevoTelefono);
                    pasajeroData.actualizarPasajero(pasajeroActualizado);
                }

            } else {
                JOptionPane.showMessageDialog(pasajeroVista, "Seleccione una fila para actualizar.");
            }
        }

    }

    public void cargarTabla(Pasajero p) {

        model.setRowCount(0);

        model.addRow(new Object[]{p.getNombre(), p.getApellido(), p.getDni(), p.getCorreo(), p.getTelefono()});

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
