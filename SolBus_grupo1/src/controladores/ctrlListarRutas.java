/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import accesoDatos.RutaData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import vistas.InfListarRutas;

/**
 *
 * @author amiev
 */
public class ctrlListarRutas implements ActionListener, ItemListener {
    
    private RutaData rutaData;
    private InfListarRutas listarRutasVista;

    public ctrlListarRutas(RutaData rutaData, InfListarRutas listarRutasVista) {
        
        this.rutaData = rutaData;
        this.listarRutasVista = listarRutasVista;
        listarRutasVista.rbVerTodasRutas.addActionListener(this);
        listarRutasVista.rbListarOrigenDestino.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource()==listarRutasVista.rbVerTodasRutas){
            listarRutasVista.txtOrigen.setEnabled(false);
            listarRutasVista.txtDestino.setEnabled(false);
            //llamar a listarRutas método para cargar la tabla con todas las rutas;
        } else if(ae.getSource()==listarRutasVista.rbListarOrigenDestino){
            listarRutasVista.txtOrigen.setEnabled(true);
            listarRutasVista.txtDestino.setEnabled(true);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        
        if(ie.getSource()==listarRutasVista.rbListarOrigenDestino){
            listarRutasVista.txtOrigen.setEnabled(true);
            listarRutasVista.txtDestino.setEnabled(true);
        }
    }
    
    
    
    
    
    
    
}
