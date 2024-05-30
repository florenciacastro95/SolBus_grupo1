package accesoDatos;

import java.sql.Connection;
import entidades.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PasajeData {
     private Connection c = null;

    public PasajeData() {
        c= Conexion.getConexion();
    }
    
    public void guardarPasaje(Pasaje pasaje){
        //id_Pasaje, id_Pasajero, id_Colectivo, id_Ruta, fechaViaje, horaViaje, asiento, precio
        String sql =("INSERT INTO pasaje(id_Pasajero, id_Colectivo, id_Ruta,fechaViaje, horaViaje, asiento, precio)"
                + " VALUES ('?','?','?','?','?','?','?')");
        
        try{
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, pasaje.getPasajero().getIdPasajero());  
            ps.setInt(2, pasaje.getColectivo().getIdColectivo());
            ps.setInt(3, pasaje.getRuta().getIdRuta());
            ps.setDate(4, Date.valueOf(pasaje.getFechaViaje()) );
            ps.setTime(5, Time.valueOf(pasaje.getHoraViaje()));
            ps.setInt(6, pasaje.getAsiento());
            ps.setDouble(7, pasaje.getPrecio());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
            
                pasaje.setIdPasaje(1);
                JOptionPane.showMessageDialog(null, "Pasaje guardado :)");

            }
            ps.close();
            
        }
        
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR EN PASAJE DATA, EN METODO GUARDAR PASAJE. ");
        }
        
    }
    // ver de implementar borrado logico ahora borramos posta
    public void borrarPasaje(int id){
        String sql = "DELETE FROM pasaje "
                + " WHERE id_Pasaje=?";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int validation = ps.executeUpdate();

            if (validation == 1) {
                JOptionPane.showMessageDialog(null, "Se elimino ese pasaje!");
            } else {
                JOptionPane.showMessageDialog(null, "Ese pasaje no existe pa");
            }

            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL en pasaje data (metodo borrar pasaje)." + e);
        }
    }
    
    public Pasaje buscarPasajePorId(int id) {

        Pasaje pasaje = null;

        String sql = "SELECT * FROM pasaje WHERE id_Pasaje = ?";
   //id_Pasaje, id_Pasajero, id_Colectivo, id_Ruta, fechaViaje, horaViaje, asiento, precio
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pasaje = new Pasaje();
                pasaje.setIdPasaje(id);
                PasajeroData pd = new PasajeroData() ;
                pasaje.setPasajero(pd.buscarPasajeroPorId(rs.getInt("id_Pasajero")));
                ColectivoData cd = new ColectivoData();
                pasaje.setColectivo(cd.buscarColectivoPorId(rs.getInt("id_Colectivo")));
                RutaData rd = new RutaData ();
                pasaje.setRuta(rd.buscarRutaPorID(rs.getInt("id_Ruta")));
                pasaje.setFechaViaje(rs.getDate("fechaViaje").toLocalDate());
                pasaje.setHoraViaje(rs.getTime("horaViaje").toLocalTime());
                pasaje.setAsiento(rs.getInt("asiento"));
                pasaje.setPrecio(rs.getDouble("precio"));
                
                
                
            } else {
                JOptionPane.showMessageDialog(null, "No existe ese pasaje :(");
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en pasaje data, metodo buscarPasaje - error SQL." + e);
        }

        return pasaje;
        //matenme
    }
   
    //ACA VER SI SE PUEDE HACER UN "ACTUALIZAR PASAJE" no tendria mucho sentido pero no sé
    

    
    //Listar pasajes por pasajero
    
    //Listar pasajes por colectivo
    
    //Listar pasajes por fecha
}