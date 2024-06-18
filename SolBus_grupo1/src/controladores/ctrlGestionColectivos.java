package controladores;

import accesoDatos.*;
import entidades.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import vistas.*;

public class ctrlGestionColectivos implements ActionListener {

    private Colectivo colectivo;
    private ColectivoData colectivoData;
    private PasajeData pD = new PasajeData();
    private infGestionColectivo colectivoVista;
    private TableModelIdBloqueado model = new TableModelIdBloqueado();

    public ctrlGestionColectivos(Colectivo c, ColectivoData cD, infGestionColectivo cV) {

        this.colectivo = c;
        this.colectivoData = cD;
        this.colectivoVista = cV;

        poneteBonito();
        armarCabeceraColes();
        cargarTablaColes();

        colectivoVista.btnAgregarFila.addActionListener(this);
        colectivoVista.btnAgregarCole.addActionListener(this);
        colectivoVista.btnActualizarColes.addActionListener(this);
        colectivoVista.btnEliminarColes.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //Agregar Fila
        if (ae.getSource() == colectivoVista.btnAgregarFila) {
            model.addRow(new Object[]{"", "matricula", "marca", "modelo", "capacidad", "estado"});

        }
        //Agregar Colectivo
        if (ae.getSource() == colectivoVista.btnAgregarCole) {

            ArrayList<Colectivo> cols = new ArrayList<>();

            if (colectivoVista.tblColes.getRowCount() > 0) {
                for (int i = 0; i < colectivoVista.tblColes.getRowCount(); i++) {
                    Boolean bandera = true;
                    if (colectivoVista.tblColes.getValueAt(i, 0).equals("")) {
                        Colectivo c = new Colectivo();
                        if (validarMatricula((String) colectivoVista.tblColes.getValueAt(i, 1))) {
                            c.setMatricula((String) colectivoVista.tblColes.getValueAt(i, 1));
                        } else {
                            bandera = false;
                            JOptionPane.showMessageDialog(null, "Matrícula inválida");
                        }

                        if (validarString((String) colectivoVista.tblColes.getValueAt(i, 2))) {
                            c.setMarca((String) colectivoVista.tblColes.getValueAt(i, 2));
                        } else {
                            bandera = false;
                            JOptionPane.showMessageDialog(null, "Marca inválida");
                        }

                        if (validarString((String) colectivoVista.tblColes.getValueAt(i, 3))) {
                            c.setModelo((String) colectivoVista.tblColes.getValueAt(i, 3));
                        } else {
                            bandera = false;
                            JOptionPane.showMessageDialog(null, "Modelo inválido");
                        }

                        if (validarInt(colectivoVista.tblColes.getValueAt(i, 4).toString())) {
                            String capacidadStr = colectivoVista.tblColes.getValueAt(i, 4).toString();
                            int capacidad = Integer.parseInt(capacidadStr);
                            c.setCapacidad(capacidad);
                        } else {
                            bandera = false;
                            JOptionPane.showMessageDialog(null, "Capacidad invalida, ingrese un numero entero");
                        }

                        if (colectivoVista.tblColes.getValueAt(i, 5).equals("Disponible")) {
                            c.setEstado(true);
                        } else if (colectivoVista.tblColes.getValueAt(i, 5).equals("No disponible")) {
                            c.setEstado(false);
                        } else {
                            bandera = false;
                            JOptionPane.showMessageDialog(null, "Estado incorrecto, ingrese Disponible o No disponible");
                        }

                        if (bandera) {
                            cols.add(c);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo agregar el colectivo. Datos inválidos");
                        }

                    }

                }
            }
            if (!cols.isEmpty()) {
                for (Colectivo cole : cols) {
                    colectivoData.guardarColectivo(cole);
                }

            }
            limpiarTablaColes();
            cargarTablaColes();

        }
        //Actualizar Colectivo
        if (ae.getSource() == colectivoVista.btnActualizarColes) {
            if (colectivoVista.tblColes.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(colectivoVista, "Debe seleccionar una fila");
            } else {
                int filaSelect = colectivoVista.tblColes.getSelectedRow();

                Boolean bandera = true;
                if (filaSelect != -1) {

                    int idCol = (int) colectivoVista.tblColes.getValueAt(filaSelect, 0);
                    colectivo = colectivoData.buscarColectivoPorId(idCol);

                    if (validarMatricula((String) colectivoVista.tblColes.getValueAt(filaSelect, 1))) {
                        colectivo.setMatricula((String) colectivoVista.tblColes.getValueAt(filaSelect, 1));
                    } else {
                        bandera = false;
                        JOptionPane.showMessageDialog(null, "Matricula invalida");
                    }

                    if (validarString((String) colectivoVista.tblColes.getValueAt(filaSelect, 2))) {
                        colectivo.setMarca((String) colectivoVista.tblColes.getValueAt(filaSelect, 2));
                    } else {
                        bandera = false;
                        JOptionPane.showMessageDialog(null, "Marca invalido");
                    }

                    if (validarString((String) colectivoVista.tblColes.getValueAt(filaSelect, 3))) {
                        colectivo.setModelo((String) colectivoVista.tblColes.getValueAt(filaSelect, 3));
                    } else {
                        bandera = false;
                        JOptionPane.showMessageDialog(null, "Modelo invalido");
                    }
                    if (validarInt(colectivoVista.tblColes.getValueAt(filaSelect, 4).toString())) {
                        colectivo.setCapacidad((int) colectivoVista.tblColes.getValueAt(filaSelect, 4));
                    } else {
                        bandera = false;
                        JOptionPane.showMessageDialog(null, "Capacidad invalida, ingrese un numero entero");
                    }
                }

                if (colectivoVista.tblColes.getValueAt(filaSelect, 5).equals("Disponible")) {
                    colectivo.setEstado(true);
                } else if (colectivoVista.tblColes.getValueAt(filaSelect, 5).equals("No disponible")) {
                    colectivo.setEstado(false);
                } else {
                    bandera = false;
                    JOptionPane.showMessageDialog(null, "Estado incorrecto, ingrese Disponible o No disponible");
                }
                String mensaje = "¿Está seguro que desea actualizar el colectivo?";
                int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
                if (bandera && respuesta == JOptionPane.YES_OPTION) {
                    colectivoData.actualizarColectivo(colectivo);

                    limpiarTablaColes();
                    cargarTablaColes();
                }

            }

        }

        //Borrar Colectivo
        if (colectivoVista.tblColes.getSelectedRow() != -1) {
            if (ae.getSource() == colectivoVista.btnEliminarColes) {
                int filaSelect = colectivoVista.tblColes.getSelectedRow();
                if (filaSelect != -1) {
                    int idHorario = (int) colectivoVista.tblColes.getValueAt(filaSelect, 0);
                    colectivo = colectivoData.buscarColectivoPorId(idHorario);
                    if (!pD.pasajeColectivo(colectivo)) {
                        String mensaje = "¿Está seguro que desea eliminar el colectivo?";
                        int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
                        if (respuesta == JOptionPane.YES_OPTION) {

                            colectivoData.borrarColectivo(idHorario);
                            limpiarTablaColes();
                            cargarTablaColes();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se puede eliminar el colectivo porque tiene pasajes asociados al día de hoy");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "seleccione una fila ");
        }

    }

   
    private void armarCabeceraColes() {
        ArrayList<Object> filaCabecera = new ArrayList<>();
        filaCabecera.add("N° de Colectivo");
        filaCabecera.add("Matricula");
        filaCabecera.add("Marca");
        filaCabecera.add("Modelo");
        filaCabecera.add("Capacidad");
        filaCabecera.add("Estado");

        for (Object i : filaCabecera) {
            model.addColumn(i);
        }
        colectivoVista.tblColes.setModel(model);
    }

    private void cargarTablaColes() {
        ArrayList<Colectivo> comodin = (ArrayList<Colectivo>) colectivoData.listarColectivosInc();

        for (Colectivo col : comodin) {

            String estadoCol = col.isEstado() ? "Disponible" : "No disponible";
            model.addRow(new Object[]{col.getIdColectivo(), col.getMatricula(), col.getMarca(), col.getModelo(), col.getCapacidad(), estadoCol});
        }

    }

    private void limpiarTablaColes() {
        model.setRowCount(0);
    }

    private boolean validarString(String s) {
        String regExp = "^[a-zA-Z0-9 ]+$";

        return s.matches(regExp);
        //reciclar código está buenísmo =)
    }

    private boolean validarInt(String s) {
        String regExp = "^\\d+$";
        return s.matches(regExp);
    }

    private boolean validarMatricula(String s) {
        String regExp = "^[a-zA-Z0-9]{6,}$";
        return s.matches(regExp);
    }

    class TableModelIdBloqueado extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            // Bloquear la primera columna (columna 0)
            return column != 0;
        }
    }

    private final void poneteBonito() {
        colectivoVista.setSize(new Dimension(450, 520));
        colectivoVista.setBorder(BorderFactory.createLineBorder(new Color(202, 40, 43), 3));
        colectivoVista.getContentPane().setBackground(new Color(240, 240, 240)); // Gris claro

        // Botones
        colectivoVista.btnActualizarColes.setBackground(new Color(202, 40, 43)); // Color acento
        colectivoVista.btnAgregarCole.setBackground(new Color(202, 40, 43)); // Color acento
        colectivoVista.btnEliminarColes.setBackground(new Color(202, 40, 43)); // Color acento
        colectivoVista.btnAgregarFila.setBackground(new Color(202, 40, 43)); // Color acento

        colectivoVista.btnActualizarColes.setForeground(Color.white);
        colectivoVista.btnAgregarCole.setForeground(Color.white);
        colectivoVista.btnEliminarColes.setForeground(Color.white);
        colectivoVista.btnAgregarFila.setForeground(Color.white);

        // Label
        colectivoVista.lblTitulo.setForeground(new Color(41, 37, 28));
        colectivoVista.lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        // JScrollPane
        colectivoVista.jScrollPane2.setBackground(new Color(240, 240, 240)); // Gris claro

        // Table
        colectivoVista.tblColes.setBackground(new Color(220, 220, 220)); // Gris medio

        // Aplicamos la fuente personalizada
        try {
            Font montserratFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/Montserrat-Regular.ttf")).deriveFont(Font.PLAIN, 14);
            Font montserratFontTitulo = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/Montserrat-Regular.ttf")).deriveFont(Font.BOLD, 18);

            colectivoVista.lblTitulo.setFont(montserratFontTitulo);

            colectivoVista.btnActualizarColes.setFont(montserratFont);
            colectivoVista.btnAgregarCole.setFont(montserratFont);
            colectivoVista.btnEliminarColes.setFont(montserratFont);
            colectivoVista.btnAgregarFila.setFont(montserratFont);

            colectivoVista.tblColes.setFont(montserratFont);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

}
