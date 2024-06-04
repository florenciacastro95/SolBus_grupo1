

package accesoDatos;
import java.sql.Connection;
import entidades.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
//INSERT INTO `pasajero`(`id_Pasajero`, `nombre`, `apellido`, `dni`, `estado`, `correo`, `telefono`)
public class PasajeroData {
    private Connection c = null;

    public PasajeroData() {
        c= Conexion.getConexion();
    }
    
    public void guardarPasajero(Pasajero pasajero){
        
        String sql =("INSERT INTO `pasajero`(`nombre`, `apellido`, `dni`, `estado`, `correo`, `telefono`)"
                + " VALUES (?,?,?,?,?)");
        
        try{
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, pasajero.getNombre());
            ps.setString(2, pasajero.getApellido());
            ps.setString(3, pasajero.getDni());
            ps.setBoolean(4, pasajero.isEstado());
            ps.setString(5, pasajero.getCorreo());
            ps.setString(6, pasajero.getTelefono());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
            
                pasajero.setIdPasajero(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Pasajero guardado :)");

            }
            ps.close();
            
        }
        
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR EN PASAJERO DATA, EN METODO GUARDAR PASAJERO. ");
        }
        
    }
    
    public void borrarPasajero(int id){
        String sql = "UPDATE `pasajero` "
                + "SET `estado`=0 WHERE id_Pasajero=?";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int validation = ps.executeUpdate();

            if (validation == 1) {
                JOptionPane.showMessageDialog(null, "Se elimino ese pasajero!");
            } else {
                JOptionPane.showMessageDialog(null, "Ese pasajero no existe pa");
            }

            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL en pasajero data (metodo borrar pasajero)." + e);
        }
    }
    public Pasajero buscarPasajeroPorApellido(String apellido) {

        Pasajero pasajero = null;

        String sql = "SELECT * FROM pasajero WHERE nombre LIKE ? OR apellido LIKE ?";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, apellido);
            ps.setString(2, apellido);//primero va a probar buscarlo por el nombre y despues por el apellido :)
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pasajero = new Pasajero();
                pasajero.setNombre(rs.getString("nombre"));
                pasajero.setApellido(rs.getString("apellido"));
                pasajero.setDni(rs.getString("dni"));
                pasajero.setEstado(rs.getBoolean("estado"));
                pasajero.setCorreo(rs.getString("correo"));
                pasajero.setTelefono(rs.getString("telefono"));

            } else {
                JOptionPane.showMessageDialog(null, "No existe ese pasajero :(");
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en pasajero data, metodo buscarPasajero - error SQL." + e);
        }

        return pasajero;
        //un saludo para el profe que mira los comentarios ;)
    }
    
        public Pasajero buscarPasajeroPorDni(int dni) {

        Pasajero pasajero = null;

        String sql = "SELECT * FROM pasajero WHERE nombre LIKE ? OR apellido LIKE ?";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pasajero = new Pasajero();
                pasajero.setNombre(rs.getString("nombre"));
                pasajero.setApellido(rs.getString("apellido"));
                pasajero.setDni(rs.getString("dni"));
                pasajero.setEstado(rs.getBoolean("estado"));
                pasajero.setCorreo(rs.getString("correo"));
                pasajero.setTelefono(rs.getString("telefono"));

            } else {
                JOptionPane.showMessageDialog(null, "No existe ese pasajero :(");
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en pasajero data, metodo buscarPasajeroPorDni - error SQL." + e);
        }

        return pasajero;
        //un saludo para el profe que mira los comentarios ;)
    }
    
    public Pasajero buscarPasajeroPorId(int id) {

        Pasajero pasajero = null;

        String sql = "SELECT * FROM pasajero WHERE id_Pasajero = ?";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pasajero = new Pasajero();
                pasajero.setIdPasajero(id);
                pasajero.setNombre(rs.getString("nombre"));
                pasajero.setApellido(rs.getString("apellido"));
                pasajero.setDni(rs.getString("dni"));
                pasajero.setEstado(rs.getBoolean("estado"));
                pasajero.setCorreo(rs.getString("correo"));
                pasajero.setTelefono(rs.getString("telefono"));
                
            } else {
                JOptionPane.showMessageDialog(null, "No existe ese pasajero :(");
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en pasajero data, metodo buscarPasajero - error SQL." + e);
        }

        return pasajero;
        //un saludo para el profe que mira los comentarios ;)
    }
    
    public void actualizarPasajero(Pasajero pasajero) {

        String sql = "UPDATE pasajero SET nombre=?, apellido=?, `dni`=?, `estado`=?, `correo`=?, `telefono`=?,"
                + " WHERE id_Pasajero= ?";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            
            ps.setString(1, pasajero.getNombre());
            ps.setString(2, pasajero.getApellido());
            ps.setString(3, pasajero.getDni());
            ps.setBoolean(4, pasajero.isEstado());
            ps.setString(5, pasajero.getCorreo());
            ps.setString(6, pasajero.getTelefono());
            ps.setInt(7, pasajero.getIdPasajero());
           
            
            int validation = ps.executeUpdate();
            if(validation == 1){
                JOptionPane.showMessageDialog(null, "La información del pasajero ha sido actualizada");
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL en PASAJERO DATA METODO ACTUALIZAR PASAJERO" + e);
        }

    }
    
    public List<Pasajero> listarPasajeros() {

        String sql = "SELECT `id_Pasajero` `nombre`, `apellido`, `dni`, `estado`, `correo`, `telefono`"
                + "  FROM pasajero WHERE estado = 1";
        ArrayList<Pasajero> pasajeros = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pasajero pasajero = new Pasajero();
                pasajero = new Pasajero();
                pasajero.setIdPasajero(rs.getInt("id_Pasajero"));
                pasajero.setNombre(rs.getString("nombre"));
                pasajero.setApellido(rs.getString("apellido"));
                pasajero.setDni(rs.getString("dni"));
                pasajero.setEstado(rs.getBoolean("estado"));
                pasajero.setCorreo(rs.getString("correo"));
                pasajero.setTelefono(rs.getString("telefono"));

                pasajeros.add(pasajero);
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL en PASAJERO DATA metodo listar coles" + e);
        }

        return pasajeros;
    }
    
    public void eliminarPasajero(Pasajero pasajero){
        String sql = "UPDATE pasajero SET estado = false WHERE id_Pasajero=?";
        
        try{
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, pasajero.getIdPasajero());
            int validation = ps.executeUpdate();
            
            if(validation==1){
                JOptionPane.showMessageDialog(null, "El pasajero ha sido eliminado");
            }else{
                JOptionPane.showMessageDialog(null, "No encontramos pasajero con ese id que esté actualmente activo");
            }
        }
        
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error SQL en PASAJERO DATA metodo eliminar pasajero" + e);
        }
        
    }
}