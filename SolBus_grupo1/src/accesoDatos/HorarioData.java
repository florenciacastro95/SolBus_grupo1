
//METODOS QUE FALTAN
//AGREGAR BAJA LOGICA

//LISTAR HORARIO POR RUTA
//LISTAR HORARIO POR FECHA
package accesoDatos;

import java.sql.Connection;
import entidades.*;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class HorarioData {
    
    private Connection c = null;
    private RutaData rD = new RutaData();

    public HorarioData() {
        c= Conexion.getConexion();
    }
    
    public void guardarHorario(Horario horario){
        
        String sql =("INSERT INTO `horario`(`id_Ruta`, `horaSalida`, `horaLlegada`, `estado`)"
                + "VALUES ('?','?','?','?');");
        
        try{
            PreparedStatement ps = c.prepareStatement(sql);
            Ruta ruta = horario.getRuta();
            ps.setInt(1, ruta.getIdRuta());
            ps.setTime(2, Time.valueOf(horario.getHoraSalida()));
            ps.setTime(3, Time.valueOf(horario.getHoraSalida()));
            ps.setBoolean(4, ruta.isEstado());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                
                horario.setIdHorario(1);
                JOptionPane.showMessageDialog(null, "Nuevo horario guardado :)");

            }
            ps.close();
            
        }
        
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR EN HORARIO DATA, EN METODO GUARDAR HORARIO.");
        }    
    }
    
    public void borrarHorarioFisico(int id){
        
        String sql = "DELETE FROM `horario` WHERE id_Horario = ?;";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int validation = ps.executeUpdate();

            if (validation == 1) {
                JOptionPane.showMessageDialog(null, "Se elimino ese horario!");
            } else {
                JOptionPane.showMessageDialog(null, "Ese horario no existe, pa");
            }

            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL en HORARIO DATA (metodo borrar horario)." + e);
        }
    }
    
    public Horario buscarHorarioPorId(int id) {

        Horario horario = null;

        String sql = "SELECT * FROM `horario` WHERE id_Horario = ? ;";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                horario = new Horario();
                horario.setIdHorario(id);
                horario.setRuta(rD.buscarRutaPorID(rs.getInt("id_Ruta")));
                horario.setHoraSalida(rs.getTime("horaSalida").toLocalTime());
                horario.setHoraLlegada(rs.getTime("horaLlegada").toLocalTime());
                
            } else {
                JOptionPane.showMessageDialog(null, "No existe ese horario :(");
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en horario data, metodo buscarHorario - error SQL." + e);
        }

        return horario;
        //un saludo para el profe que mira los comentarios ;)
    }
    
    public void actualizarHorario(Horario horario) {

        
        String sql = "UPDATE `horario` SET `id_Ruta`= '?',"
                + "`horaSalida`='?',`horaLlegada`='?', `estado`= '?' WHERE `id_Horario`= '?';";
        
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            Ruta ruta = horario.getRuta();
            ps.setInt(1, ruta.getIdRuta());
            ps.setTime(2, Time.valueOf(horario.getHoraSalida()));
            ps.setTime(3, Time.valueOf(horario.getHoraLlegada()));
            ps.setInt(4, horario.getIdHorario());
            ps.setBoolean(5, ruta.isEstado());
            int validation = ps.executeUpdate();
            if(validation == 1){
                JOptionPane.showMessageDialog(null, "La información del horario ha sido actualizada");
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL en HORARIO DATA METODO ACTUALIZAR HORARIO" + e);
        }

    }
    
    public List<Horario> listarHorarios() {

        String sql = "SELECT * FROM `horario` WHERE estado=true";
        ArrayList<Horario> horarios = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Horario horario = new Horario();
                horario.setIdHorario(rs.getInt("id_Horario"));
                horario.setRuta(rD.buscarRutaPorID(rs.getInt("id_Ruta")));
                horario.setHoraSalida(rs.getTime("horaSalida").toLocalTime());
                horario.setHoraLlegada(rs.getTime("horaLlegada").toLocalTime());
                
                horarios.add(horario); 
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL en HORARIO DATA metodo listar horarios" + e);
        }

        return horarios;
    }
}
