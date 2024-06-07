
package controladores;

import accesoDatos.RutaData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import vistas.InfListarRutas;

/**
 *
 * @author amiev
 */
public class ctrlListarRutas implements ActionListener {
    
    private RutaData rutaData;
    private InfListarRutas listarRutasVista;

    public ctrlListarRutas(RutaData rutaData, InfListarRutas listarRutasVista) {
        
        this.rutaData = rutaData;
        this.listarRutasVista = listarRutasVista;
        listarRutasVista.rbVerTodasRutas.addActionListener(this);
        listarRutasVista.rbListarOrigenDestino.addActionListener(this);
        listarRutasVista.txtOrigen.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                //accion
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                //acción
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
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

   
}
    
    
    
    
    
    

