package accesoDatos;

import entidades.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
//
//CREATE TABLE `ruta` (
//  `id_Ruta` int(11) NOT NULL,
//  `origen` varchar(30) NOT NULL,
//  `destino` varchar(30) NOT NULL,
//  `duracionEstimada` time NOT NULL
//)

public class RutaData {

    private Connection c = null;

    public RutaData() {
        c = Conexion.getConexion();
    }

    public void guardarRuta(Ruta ruta) {

        String sql = ("INSERT INTO `ruta`(`origen`, `destino`, `duracionEstimada`)"
                + " VALUES ('?','?','?')");

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, ruta.getOrigen());
            ps.setString(2, ruta.getDestino());
            ps.setTime(3, Time.valueOf(ruta.getDuracion()));

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {

                ruta.setIdRuta(1);
                JOptionPane.showMessageDialog(null, "Ruta guardada :)");

            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR EN RUTA DATA, EN METODO GUARDAR RUTA. ");
        }
    }

    //tendriamos que dar de baja una ruta (agregar un estado)
    
        public Ruta buscarRutaPorID(int id) {

        Ruta ruta = null;

        String sql = "SELECT * FROM ruta WHERE id_Ruta = ?";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ruta = new Ruta();
                ruta.setIdRuta(id);
                ruta.setOrigen(rs.getString("origen"));
                ruta.setDestino(rs.getString("destino"));
                ruta.setDuracion((rs.getTime("duracionEstimada").toLocalTime()));
                
            } else {
                JOptionPane.showMessageDialog(null, "No existe esa ruta :(");
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en ruta data, metodo buscarRuta - error SQL." + e);
        }

        return ruta;
        //un saludo para el profe que mira los comentarios ;)
    }
    
        public void actualizarRuta(Ruta ruta) {

        String sql = "UPDATE ruta SET origen = ?, destino = ?, duracionEstimada = ? WHERE id_Ruta= ?";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            
            ps.setString(1, ruta.getOrigen());
            ps.setString(3, ruta.getDestino());
            ps.setTime(3, Time.valueOf(ruta.getDuracion()));
            int validation = ps.executeUpdate();
            if(validation == 1){
                JOptionPane.showMessageDialog(null, "La información del ruta ha sido actualizada");
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL en RUTA DATA METODO ACTUALIZAR RUTA" + e);
        }

    }
        
        
         public List<Ruta> listarRutas() {

        String sql = "SELECT idRuta, origen, destino, duracionEstimada  FROM ruta";
        ArrayList<Ruta> rutas = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ruta ruta = new Ruta();
                ruta = new Ruta();
                ruta.setIdRuta(rs.getInt("id_Ruta"));
                ruta.setOrigen(rs.getString("origen"));
                ruta.setDestino(rs.getString("destino"));
                ruta.setDuracion((rs.getTime("duracionEstimada").toLocalTime()));
                rutas.add(ruta);
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL en Ruta DATA metodo listar rutas" + e);
        }

        return rutas;
    }   
        
    
}
