//METODOS QUE FALTAN
//AGREGAR LISTAR PASAJE POR RUTA
//SACAR CONSULTAS POR ID EN VISTAS
//ver que onda con el estado
//DAR OPCION DE VER PASAJES DISPONIBLES ANTES DE VENDER
package accesoDatos;

import java.sql.Connection;
import entidades.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PasajeData {

    private Connection c = null;
    private PasajeroData pd;
    private ColectivoData cd;
    private RutaData rd;

    public PasajeData() {
        c = Conexion.getConexion();
        pd = new PasajeroData();
        cd = new ColectivoData();
        rd = new RutaData();
    }

    //EMITIR RECIBO A PASAJERO:
    //IReport - Armar formato de pasaje para vincular a la db - 
    //está agregado el actualizarAsientos de cole data
    public void venderPasaje(Pasaje pasaje) {
        //id_Pasaje, id_Pasajero, id_Colectivo, id_Ruta, fechaViaje, horaViaje, asiento, precio
        String sql = ("INSERT INTO pasaje(id_Pasajero, id_Colectivo, id_Ruta,fechaViaje, horaViaje, asiento, precio)"
                + " VALUES (?,?,?,?,?,?,?)");

        try {
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, pasaje.getPasajero().getIdPasajero());
            ps.setInt(2, pasaje.getColectivo().getIdColectivo());
            ps.setInt(3, pasaje.getRuta().getIdRuta());
            ps.setDate(4, Date.valueOf(pasaje.getFechaViaje()));
            ps.setTime(5, Time.valueOf(pasaje.getHoraViaje()));
            ps.setInt(6, pasaje.getAsiento());
            ps.setDouble(7, pasaje.getPrecio());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {

                pasaje.setIdPasaje(rs.getInt(1));
                cd.actualizarAsientos(cd.buscarColectivoPorId(pasaje.getColectivo().getIdColectivo()), -1);
                JOptionPane.showMessageDialog(null, "Pasaje guardado :)");

            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR EN PASAJE DATA, EN METODO GUARDAR PASAJE. ");
            System.out.println(e);
        }

    }

    // ver de implementar borrado logico ahora borramos posta
    //está agregado el actualizarAsientos de cole data
    public void borrarPasaje(int id) {
        String sql = "DELETE FROM pasaje "
                + " WHERE id_Pasaje=?";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int validation = ps.executeUpdate();

            if (validation == 1) {
                JOptionPane.showMessageDialog(null, "Se elimino ese pasaje!");
                cd.actualizarAsientos(cd.buscarColectivoPorId(buscarPasajePorId(id).getColectivo().getIdColectivo()), 1);
            } else {
                JOptionPane.showMessageDialog(null, "Ese pasaje no existe pa");
            }

            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL en pasaje data (metodo borrar pasaje)." + e);
        }
    }
    
    public boolean estaElPasaje(int asiento, Ruta rutaBuscada,
            Colectivo colectivo, LocalDate fecha, LocalTime hora){
    String sql = "SELECT * FROM pasaje WHERE id_Ruta = ? AND id_Colectivo = ? AND "
                + "fechaViaje = ? AND horaViaje = ? AND asiento = ?";
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, rutaBuscada.getIdRuta());
            ps.setInt(2, colectivo.getIdColectivo());
            ps.setDate(3, Date.valueOf(fecha));
            ps.setTime(4, Time.valueOf(hora));
            ps.setInt(5, asiento);
            ResultSet rs = ps.executeQuery();
            ps.close();
            if (rs.next()) {
                return true;

            } else {
                return false;
            }
            

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en el metodo estaElPasaje, no se pudo acceder a los pasajes ." + e);
            System.out.println(e);
        }    
        return false;

    }
    

    //flor ta out chicos
    public boolean eliminarPasajePorViaje(int asiento, Ruta rutaBuscada,
            Colectivo colectivo, LocalDate fecha, LocalTime hora) {
        boolean band = true;
        String sql = "DELETE from pasaje WHERE id_Ruta = ? AND id_Colectivo = ? AND "
                + "fechaViaje = ? AND horaViaje = ? AND asiento = ?";

        Colectivo idC=cd.buscarColectivoPorId(buscarPasajePorViaje(rutaBuscada, colectivo, fecha, hora, asiento).getColectivo().getIdColectivo());
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, rutaBuscada.getIdRuta());
            ps.setInt(2, colectivo.getIdColectivo());
            ps.setDate(3, Date.valueOf(fecha));
            ps.setTime(4, Time.valueOf(hora));
            ps.setInt(5, asiento);
            int validation = ps.executeUpdate();
            if (validation == 1) {
                cd.actualizarAsientos(idC, 1);
                JOptionPane.showMessageDialog(null, "Se elimino ese pasaje!");

            } else {
                JOptionPane.showMessageDialog(null, "Ese pasaje no existe pa");
                band = false;
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en el metodo eliminarPasajePorViaje, no se pudo acceder a los pasajes ." + e);
            band = false;
        }

        return band;
    }

    public Pasaje buscarPasajePorViaje(Ruta rutaBuscada,
            Colectivo colectivo, LocalDate fecha, LocalTime hora, int asiento) {

        Pasaje pasaje = null;
        String sql = "SELECT * FROM pasaje WHERE id_Ruta = ? AND id_Colectivo = ? AND "
                + "fechaViaje = ? AND horaViaje = ? AND asiento = ?";

        try {
            PreparedStatement ps =c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, rutaBuscada.getIdRuta());
            ps.setInt(2, colectivo.getIdColectivo());
            ps.setDate(3, Date.valueOf(fecha));
            ps.setTime(4, Time.valueOf(hora));
            ps.setInt(5, asiento);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                pasaje = new Pasaje();
                pasaje.setIdPasaje(rs.getInt("id_Pasaje"));
                pasaje.setPasajero(pd.buscarPasajeroPorId(rs.getInt("id_Pasajero")));
                pasaje.setColectivo(cd.buscarColectivoPorId(rs.getInt("id_Colectivo")));
                pasaje.setRuta(rd.buscarRutaPorID(rs.getInt("id_Ruta")));
                pasaje.setFechaViaje(rs.getDate("fechaViaje").toLocalDate());
                pasaje.setHoraViaje(rs.getTime("horaViaje").toLocalTime());
                pasaje.setAsiento(rs.getInt("asiento"));
                pasaje.setPrecio(rs.getDouble("precio"));

            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en el metodo buscarPasajePorViaje, no se pudo acceder a los pasajes ." + e);
        }

        return pasaje;
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
                pasaje.setPasajero(pd.buscarPasajeroPorId(rs.getInt("id_Pasajero")));
                pasaje.setColectivo(cd.buscarColectivoPorId(rs.getInt("id_Colectivo")));
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
    public List<Pasaje> listarPasajesPorPasajero(int idPasajero) {

        String sql = "SELECT * FROM pasaje WHERE id_Pasajero =?";
        ArrayList<Pasaje> pasajes = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, idPasajero);
            ResultSet rs = ps.executeQuery();
            //id_Pasaje, id_Pasajero, id_Colectivo, id_Ruta, fechaViaje, horaViaje, asiento, precio
            while (rs.next()) {
                Pasaje pasaje = new Pasaje();
                pasaje.setIdPasaje(rs.getInt("id_Pasaje"));
                pasaje.setPasajero(pd.buscarPasajeroPorId(rs.getInt("id_Pasajero")));
                pasaje.setColectivo(cd.buscarColectivoPorId(rs.getInt("id_Colectivo")));
                pasaje.setRuta(rd.buscarRutaPorID(rs.getInt("id_Ruta")));
                pasaje.setFechaViaje(rs.getDate("fechaViaje").toLocalDate());
                pasaje.setHoraViaje(rs.getTime("horaViaje").toLocalTime());
                pasaje.setAsiento(rs.getInt("asiento"));
                pasaje.setPrecio(rs.getDouble("precio"));
                pasajes.add(pasaje);

            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en el metodo obtenerPasajesPorPasajero, no se pudo acceder a los pasajes ." + e);
        }

        return pasajes;
    }

    //Listar pasajes por colectivo
    public List<Pasaje> listarPasajesVendidosPorColectivo(int idColectivo) {

        String sql = "SELECT * FROM pasaje WHERE id_Colectivo =?";
        ArrayList<Pasaje> pasajes = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, idColectivo);
            ResultSet rs = ps.executeQuery();
            //id_Pasaje, id_Pasajero, id_Colectivo, id_Ruta, fechaViaje, horaViaje, asiento, precio
            while (rs.next()) {
                Pasaje pasaje = new Pasaje();
                pasaje.setIdPasaje(rs.getInt("id_Pasaje"));
                pasaje.setPasajero(pd.buscarPasajeroPorId(rs.getInt("id_Pasajero")));
                pasaje.setColectivo(cd.buscarColectivoPorId(rs.getInt("id_Colectivo")));
                pasaje.setRuta(rd.buscarRutaPorID(rs.getInt("id_Ruta")));
                pasaje.setFechaViaje(rs.getDate("fechaViaje").toLocalDate());
                pasaje.setHoraViaje(rs.getTime("horaViaje").toLocalTime());
                pasaje.setAsiento(rs.getInt("asiento"));
                pasaje.setPrecio(rs.getDouble("precio"));
                pasajes.add(pasaje);

            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en el metodo pasajesVendidosPorColectivo, no se pudo acceder a los pasajes ." + e);
        }

        return pasajes;
    }

    //Listar pasajes por fecha
    public List<Pasaje> listarPasajesVendidosPorFecha(LocalDate fechaBuscada) {

        String sql = "SELECT * FROM pasaje WHERE fechaViaje =?";
        ArrayList<Pasaje> pasajes = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(fechaBuscada));
            ResultSet rs = ps.executeQuery();
            //id_Pasaje, id_Pasajero, id_Colectivo, id_Ruta, fechaViaje, horaViaje, asiento, precio
            while (rs.next()) {
                Pasaje pasaje = new Pasaje();
                pasaje.setIdPasaje(rs.getInt("id_Pasaje"));
                pasaje.setPasajero(pd.buscarPasajeroPorId(rs.getInt("id_Pasajero")));
                pasaje.setColectivo(cd.buscarColectivoPorId(rs.getInt("id_Colectivo")));
                pasaje.setRuta(rd.buscarRutaPorID(rs.getInt("id_Ruta")));
                pasaje.setFechaViaje(rs.getDate("fechaViaje").toLocalDate());
                pasaje.setHoraViaje(rs.getTime("horaViaje").toLocalTime());
                pasaje.setAsiento(rs.getInt("asiento"));
                pasaje.setPrecio(rs.getDouble("precio"));
                pasajes.add(pasaje);

            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en el metodo pasajesVendidosPorFecha, no se pudo acceder a los pasajes ." + e);
        }

        return pasajes;
    }

    public List<Pasaje> listarPasajePorHorario(LocalTime horaBuscada) {

        String sql = "SELECT * FROM pasaje WHERE horaViaje =?";
        ArrayList<Pasaje> pasajes = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setTime(1, Time.valueOf(horaBuscada));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pasaje pasaje = new Pasaje();
                pasaje.setIdPasaje(rs.getInt("id_Pasaje"));
                pasaje.setPasajero(pd.buscarPasajeroPorId(rs.getInt("id_Pasajero")));
                pasaje.setColectivo(cd.buscarColectivoPorId(rs.getInt("id_Colectivo")));
                pasaje.setRuta(rd.buscarRutaPorID(rs.getInt("id_Ruta")));
                pasaje.setFechaViaje(rs.getDate("fechaViaje").toLocalDate());
                pasaje.setHoraViaje(rs.getTime("horaViaje").toLocalTime());
                pasaje.setAsiento(rs.getInt("asiento"));
                pasaje.setPrecio(rs.getDouble("precio"));
                pasajes.add(pasaje);

            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en el metodo  pasajesPorHorario, no se pudo acceder a los pasajes ." + e);
        }

        return pasajes;
    }

    public List<Pasaje> listarPasajesVendidosPorRuta(Ruta rutaBuscada) {

        String sql = "SELECT * FROM pasaje WHERE id_ruta = ?";
        ArrayList<Pasaje> pasajes = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, rutaBuscada.getIdRuta());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pasaje pasaje = new Pasaje();
                pasaje.setIdPasaje(rs.getInt("id_Pasaje"));
                pasaje.setPasajero(pd.buscarPasajeroPorId(rs.getInt("id_Pasajero")));
                pasaje.setColectivo(cd.buscarColectivoPorId(rs.getInt("id_Colectivo")));
                pasaje.setRuta(rd.buscarRutaPorID(rs.getInt("id_Ruta")));
                pasaje.setFechaViaje(rs.getDate("fechaViaje").toLocalDate());
                pasaje.setHoraViaje(rs.getTime("horaViaje").toLocalTime());
                pasaje.setAsiento(rs.getInt("asiento"));
                pasaje.setPrecio(rs.getDouble("precio"));
                pasajes.add(pasaje);

            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en el metodo pasajesPorRuta, no se pudo acceder a los pasajes ." + e);
        }

        return pasajes;
    }

    public List<Integer> listarAsientosOcupadosPorViaje(Ruta rutaBuscada,
            Colectivo colectivo, LocalDate fecha, LocalTime hora) {

        //flor quiere morir D: esto es demasiado jefe
        //Ejemplo para listar pasajes vendidos en el mismo viaje
        //SELECT asiento FROM pasaje WHERE id_Ruta = 1 AND id_Colectivo = 1 
        //AND fechaViaje = '2024-06-06' AND horaViaje = '11:00:00';
        String sql = "SELECT asiento FROM pasaje WHERE id_Ruta = ? AND id_Colectivo = ? AND "
                + "fechaViaje = ? AND horaViaje = ?";
        ArrayList<Integer> asientosOcupados = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, rutaBuscada.getIdRuta());
            ps.setInt(2, colectivo.getIdColectivo());
            ps.setDate(3, Date.valueOf(fecha));
            ps.setTime(4, Time.valueOf(hora));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                asientosOcupados.add(rs.getInt("asiento"));

            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en el metodo listarAsientosOcupadosPorViaje, no se pudo acceder a los pasajes ." + e);
        }

        return asientosOcupados;
    }

    
}
