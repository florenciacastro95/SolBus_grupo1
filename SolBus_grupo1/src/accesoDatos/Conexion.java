package accesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    private static final String URL = "jdbc:mariadb://localhost/";
    private static final String DB = "solbusdbcargada";
    private static final String USER ="root";
    private static final String PASSWORD = "";
    
    
    private static Connection con;

    private Conexion() {
    }
    
    public static Connection getConexion(){
        
        
        if(con==null){ //significa que es la primera vez que se invoca este metodo
            try{
                Class.forName("org.mariadb.jdbc.Driver");
                con= DriverManager.getConnection(URL+DB, USER, PASSWORD);
                JOptionPane.showMessageDialog(null,"Conexion exitosa");
            }
            catch(ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, "Error en CONEXION DATA. No se pudieron cargar los drivers");
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error en CONEXION DATA. Error SQL");
            }
        }
        return con;
    }
}
