package accesoDatos;

import java.sql.Connection;
import entidades.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ColectivoData {
    private Connection c = null;

    public ColectivoData() {
        c= Conexion.getConexion();
    }
    
    public void guardarColectivo(Colectivo colectivo){
        
        String sql =("INSERT INTO `colectivo`(`matricula`, `estado`, `marca`, `modelo`, `capacidad`)"
                + " VALUES (?,?,?,?,?)");
        
        try{
            PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, colectivo.getMatricula());
            ps.setBoolean(2, colectivo.isEstado());
            ps.setString(3, colectivo.getMarca());
            ps.setString(4, colectivo.getModelo());
            ps.setInt(5, colectivo.getCapacidad());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
            
                colectivo.setIdColectivo(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Colectivo guardado :)");

            }
            ps.close();
            
        }
        
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR EN COLECTIVO DATA, EN METODO GUARDAR COLECTIVO. ");
        }
        
    }
    
    public void borrarColectivo(int id){
        String sql = "UPDATE `colectivo` "
                + "SET `estado`=0 WHERE id_Colectivo=?";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int validation = ps.executeUpdate();

            if (validation == 1) {
                JOptionPane.showMessageDialog(null, "Se elimino ese colectivo!");
            } else {
                JOptionPane.showMessageDialog(null, "Ese colectivo no existe pa");
            }

            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL en colectivo data (metodo borrar colectivo)." + e);
        }
    }
    
    public Colectivo buscarColectivoPorId(int id) {

        Colectivo colectivo = null;

        String sql = "SELECT * FROM colectivo WHERE id_Colectivo = ?";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
//`matricula`, `estado`, `marca`, `modelo`, `capacidad`)
            if (rs.next()) {
                colectivo = new Colectivo();
                colectivo.setIdColectivo(id);
                colectivo.setMatricula(rs.getString("matricula"));
                colectivo.setEstado(rs.getBoolean("estado"));
                colectivo.setMarca(rs.getString("marca"));
                colectivo.setModelo(rs.getString("modelo"));
                colectivo.setCapacidad(rs.getInt("capacidad"));
                
            } else {
                JOptionPane.showMessageDialog(null, "No existe ese colectivo :(");
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en colectivo data, metodo buscarColectivo - error SQL." + e);
        }

        return colectivo;
        //un saludo para el profe que mira los comentarios ;)
    }
    
    public void actualizarColectivo(Colectivo colectivo) {

        String sql = "UPDATE colectivo SET matricula = ?, estado = ?, marca = ?, modelo = ?, capacidad = ?"
                + " WHERE id_Colectivo= ?";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            
            ps.setString(1, colectivo.getMatricula());
            ps.setBoolean(2, colectivo.isEstado());
            ps.setString(3, colectivo.getMarca());
            ps.setString(4, colectivo.getModelo());
            ps.setInt(5, colectivo.getCapacidad());
            ps.setInt(6, colectivo.getIdColectivo());
            
            int validation = ps.executeUpdate();
            if(validation == 1){
                JOptionPane.showMessageDialog(null, "La información del colectivo ha sido actualizada");
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL en COLECTIVO DATA METODO ACTUALIZAR COLECTIVO" + e);
        }

    }
//ASIENTO PUEDE SER ENTERO POSITIVO SI SE ANULA VENTA O ENTERO NEGATIVO SI SE VENDE
    public void actualizarAsientos(Colectivo colectivo, int asiento) {

        String sql = "UPDATE colectivo SET capacidad = ?"
                + " WHERE id_Colectivo= ?";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            
            ps.setInt(1, colectivo.getCapacidad()+asiento);
            ps.setInt(2, colectivo.getIdColectivo());
            
            int validation = ps.executeUpdate();
            if(validation == 1){
                JOptionPane.showMessageDialog(null, "Los asientos han sido actualizados");
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL en COLECTIVO DATA METODO actualizarAsientos" + e);
        }

    }
    
    public List<Colectivo> listarColectivos() {

        String sql = "SELECT id_Colectivo, matricula, estado, marca, modelo, capacidad FROM colectivo WHERE estado = 1";
        ArrayList<Colectivo> colectivos = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Colectivo colectivo = new Colectivo();
                colectivo = new Colectivo();
                colectivo.setIdColectivo(rs.getInt("id_Colectivo"));
                colectivo.setMatricula(rs.getString("matricula"));
                colectivo.setEstado(rs.getBoolean("estado"));
                colectivo.setMarca(rs.getString("marca"));
                colectivo.setModelo(rs.getString("modelo"));
                colectivo.setCapacidad(rs.getInt("capacidad"));

                colectivos.add(colectivo);
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL en COLECTIVO DATA metodo listar coles" + e);
        }

        return colectivos;
    }
    
    //elimina colectivos y verifica que este no esté actualmente inactivo
    public void eliminarColectivo(Colectivo colectivo){
        String sql = "UPDATE colectivo SET estado = false WHERE id_Colectivo=? AND estado=true";
        
        try{
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, colectivo.getIdColectivo());
            int validation = ps.executeUpdate();
            
            if(validation==1){
                JOptionPane.showMessageDialog(null, "El colectivo ha sido eliminado");
            }else{
                JOptionPane.showMessageDialog(null, "No encontramos colectivo con ese id que esté actualmente activo");
            }
        }
        
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error SQL en COLECTIVO DATA metodo eliminar coles" + e);
        }
        
    }
}
