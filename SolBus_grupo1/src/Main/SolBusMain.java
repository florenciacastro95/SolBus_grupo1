package Main;

import entidades.*;
import accesoDatos.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SolBusMain {

    public static void main(String[] args) {

       
        //        ColectivoData cd = new ColectivoData();
//        HorarioData hd =  new HorarioData();
//        PasajeData pd = new PasajeData();
//        PasajeroData pjd = new PasajeroData();
//        RutaData rd = new RutaData();
//        ArrayList<Colectivo> colectivos = new ArrayList<>();
//        ArrayList<Ruta> rutas = new ArrayList<>();
//        ArrayList<Horario> horarios = new ArrayList<>();
        //PRUEBAS COLECTIVODATA
        //String matricula, String marca, String modelo, int capacidad
//      Colectivo colectivo = new Colectivo(14,"PEDRO", "Marco Polo","Mercedes 1", 10);
//      Colectivo reemplazo = new Colectivo("AGUANTE SAN LORENZO","Marco Polo","Mercedes 2",5);
//      cd.guardarColectivo(colectivo);  
//      cd.borrarColectivo(14);
//      System.out.println(cd.buscarColectivoPorId(13).toString());
//        cd.actualizarColectivo(colectivo);
//        colectivos = (ArrayList<Colectivo>) cd.listarColectivos();
//        for (Colectivo colectivo1 : colectivos) {
//            System.out.println(colectivo1.toString());
//        }
//       cd.eliminarColectivo(colectivo);
//        rd.guardarRuta(r);
//Ruta r2 = new Ruta( 1, "guanacoBoleado", "mercedes", LocalTime.of(12, 00), true);
        //rd.guardarRuta(r2);
//        rd.bajaRuta(2);
//        System.out.println(rd.buscarRutaPorID(2).toString());
//        rd.actualizarRuta(r2);
        //PRUEBAS HORARIODATA
        
//        Horario h = new Horario(8,r2, LocalTime.of(11, 00), LocalTime.of(17, 40), true);
//        hd.guardarHorario(h);
//        hd.borrarHorarioFisico(6);
//        hd.bajaHorario(7);
//        System.out.println(hd.buscarHorarioPorId(8).toString());
//          hd.actualizarHorario(h);
//           horarios = (ArrayList<Horario>) hd.listarHorarios();
//        for (Horario aux : horarios) {
//               System.out.println(aux.toString());
//        }
//        horarios = (ArrayList<Horario>) hd.listarHorariosPorHoraSalida(LocalTime.of(11, 00));
//        for (Horario aux : horarios) {
//               System.out.println(aux.toString());
////        }
//        horarios = (ArrayList<Horario>) hd.listarHorariosPorRuta(r2);
//        for (Horario a : horarios){
//               System.out.println(aux.toString());
//        }
//        PRUEBAS RUTADATA
//        Ruta r = new Ruta("Merlo", "San Luis", LocalTime.of(3, 40), true);
//        Ruta r2 = new Ruta( 1, "guanacoBoleado", "mercedes", LocalTime.of(12, 00), true);
//        rutas = (ArrayList<Ruta>) rd.listarRutasDisponibles();
//        for (Ruta aux : rutas) {
//               System.out.println(aux.toString());
//        }
//        
//        rutas = (ArrayList<Ruta>) rd.listarRutasPorDestino("DEPRESION");
//        for (Ruta aux : rutas) {
//               System.out.println(aux.toString());
//        }
//        
//        rutas = (ArrayList<Ruta>) rd.listarRutasPorOrigen("PROGRAMACION 2");
//        for (Ruta aux : rutas) {
//               System.out.println(aux.toString());
//        }
//PRUEBAS PASAJERO DATA
//     PasajeroData pasajeroData = new PasajeroData();
//        
//        Pasajero pasajero1 = new Pasajero(1, "Flor", "Castro", "12345678", "fmcastro95@gmail.com", "123456789");
//        pasajeroData.guardarPasajero(pasajero1);
//        
//        // Buscar pasajero por apellido
//        Pasajero pasajeroBuscadoPorApellido = pasajeroData.buscarPasajeroPorApellido("Castro");
//        if (pasajeroBuscadoPorApellido != null) {
//            System.out.println("Pasajero encontrado por apellido: " + pasajeroBuscadoPorApellido);
//        } else {
//            System.out.println("No se encontró el pasajero con el apellido dado.");
//        }
//        
//        // Buscar pasajero por DNI
//        Pasajero pasajeroBuscadoPorDni = pasajeroData.buscarPasajeroPorDni("12345678");
//        if (pasajeroBuscadoPorDni != null) {
//            System.out.println("Pasajero encontrado por DNI: " + pasajeroBuscadoPorDni);
//        } else {
//            System.out.println("No se encontró el pasajero con el DNI dado.");
//        }
//        
//        // Obtener todos los pasajeros activos
//        List<Pasajero> pasajerosActivos = pasajeroData.obtenerPasajerosActivos();
//        System.out.println("Lista de pasajeros activos:");
//        for (Pasajero p : pasajerosActivos) {
//            System.out.println(p);
//        }
//        
//        // Borrar pasajero
//        pasajeroData.borrarPasajero(pasajero1.getIdPasajero());
//        
//        // Intentar buscar de nuevo el pasajero borrado
//        Pasajero pasajeroBorrado = pasajeroData.buscarPasajeroPorDni("12345678");
//        if (pasajeroBorrado != null) {
//            System.out.println("Pasajero encontrado después de borrar: " + pasajeroBorrado);
//        } else {
//            System.out.println("El pasajero ha sido correctamente borrado.");
//        }
//    
//        PasajeData pasajeData = new PasajeData();
//        PasajeroData pasajeroData = new PasajeroData();
//        ColectivoData colectivoData = new ColectivoData();
//        RutaData rutaData = new RutaData();
//
//        // Crear y guardar 10 pasajeros
//        Pasajero[] pasajeros = {
//            new Pasajero("Tadeo", "Gomez", "12345601", "tadeo.gomez@example.com", "1234567890"),
//            new Pasajero("Ailen", "Rodriguez", "12345602", "ailen.rodriguez@example.com", "1234567891"),
//            new Pasajero("Florencia", "Martinez", "12345603", "florencia.martinez@example.com", "1234567892"),
//            new Pasajero("Adan", "Perez", "12345604", "adan.perez@example.com", "1234567893"),
//            new Pasajero("Sofia", "Lopez", "12345605", "sofia.lopez@example.com", "1234567894"),
//            new Pasajero("Mateo", "Sanchez", "12345606", "mateo.sanchez@example.com", "1234567895"),
//            new Pasajero("Camila", "Diaz", "12345607", "camila.diaz@example.com", "1234567896"),
//            new Pasajero("Lautaro", "Fernandez", "12345608", "lautaro.fernandez@example.com", "1234567897"),
//            new Pasajero("Valentina", "Gonzalez", "12345609", "valentina.gonzalez@example.com", "1234567898"),
//            new Pasajero("Joaquin", "Garcia", "12345610", "joaquin.garcia@example.com", "1234567899")
//        };
//
//        for (Pasajero pasajero : pasajeros) {
//            pasajeroData.guardarPasajero(pasajero);
//        }
//
//        // Crear y guardar 2 colectivos
//        Colectivo colectivo1 = new Colectivo("ACA420", "Carmelita descalza", "Acme", 6);
//        Colectivo colectivo2 = new Colectivo("ICKKCK", "Poneme un modelo", "manteca Z", 4);
//
//        colectivoData.guardarColectivo(colectivo1);
//        colectivoData.guardarColectivo(colectivo2);
//
//        // Crear y guardar 4 rutas
//        Ruta ruta1 = new Ruta("Merlo", "San Luis", LocalTime.of(3, 40), true);
//        Ruta ruta2 = new Ruta("San Luis", "La Punta", LocalTime.of(1, 10), true);
//        Ruta ruta3 = new Ruta("Villa Mercedes", "San Luis", LocalTime.of(1, 50), true);
//        Ruta ruta4 = new Ruta("Merlo", "Villa Mercedes", LocalTime.of(3, 30), true);
//
//        rutaData.guardarRuta(ruta1);
//        rutaData.guardarRuta(ruta2);
//        rutaData.guardarRuta(ruta3);
//        rutaData.guardarRuta(ruta4);
//
//        // Crear pasajes aleatorios para los pasajeros
//        for (int i = 0; i < pasajeros.length; i++) {
//            Pasajero pasajero = pasajeros[i];
//            Colectivo colectivo = (i % 2 == 0) ? colectivo1 : colectivo2;
//            Ruta ruta;
//            if (i % 4 == 0) {
//                ruta = ruta1;
//            } else if (i % 4 == 1) {
//                ruta = ruta2;
//            } else if (i % 4 == 2) {
//                ruta = ruta3;
//            } else {
//                ruta = ruta4;
//            }
//            LocalDate fechaViaje = LocalDate.now().plusDays(i % 5);
//            LocalTime horaViaje = LocalTime.of((i % 24), (i * 5) % 60);
//            Pasaje pasaje = new Pasaje(pasajero, colectivo, ruta, fechaViaje, horaViaje, i + 1, 1500.0 + (i * 50));
//            pasajeData.venderPasaje(pasaje);
//        }
//
//        // Probar obtener pasajes por pasajero
//        for (Pasajero pasajero : pasajeros) {
//            List<Pasaje> pasajesPorPasajero = pasajeData.obtenerPasajesPorPasajero(pasajero.getIdPasajero());
//            System.out.println("Lista de pasajes para el pasajero " + pasajero.getNombre() + " " + pasajero.getApellido() + ":");
//            for (Pasaje p : pasajesPorPasajero) {
//                System.out.println(p);
//            }
//        }
//
//        // Probar obtener pasajes por colectivo
//        List<Pasaje> pasajesPorColectivo1 = pasajeData.pasajesVendidosPorColectivo(colectivo1.getIdColectivo());
//        List<Pasaje> pasajesPorColectivo2 = pasajeData.pasajesVendidosPorColectivo(colectivo2.getIdColectivo());
//
//        System.out.println("Lista de pasajes para el colectivo " + colectivo1.getModelo() + ":");
//        for (Pasaje p : pasajesPorColectivo1) {
//            System.out.println(p);
//        }
//
//        System.out.println("Lista de pasajes para el colectivo " + colectivo2.getModelo() + ":");
//        for (Pasaje p : pasajesPorColectivo2) {
//            System.out.println(p);
//        }
//
//        // Probar obtener pasajes por fecha
//        LocalDate fechaPrueba = LocalDate.now();
//        List<Pasaje> pasajesPorFecha = pasajeData.pasajesVendidosPorFecha(fechaPrueba);
//        System.out.println("Lista de pasajes para la fecha " + fechaPrueba + ":");
//        for (Pasaje p : pasajesPorFecha) {
//            System.out.println(p);
//        }
//
//        // Probar obtener pasajes por hora
//        LocalTime horaPrueba = LocalTime.now();
//        List<Pasaje> pasajesPorHora = pasajeData.listarPasajePorHorario(horaPrueba);
//        System.out.println("Lista de pasajes para la hora " + horaPrueba + ":");
//        for (Pasaje p : pasajesPorHora) {
//            System.out.println(p);
//        }
//
//        // Probar obtener pasajes por ruta
//        List<Pasaje> pasajesPorRuta1 = pasajeData.pasajesVendidosPorRuta(ruta1);
//        System.out.println("Lista de pasajes para la ruta " + ruta1.getOrigen() + " - " + ruta1.getDestino() + ":");
//        for (Pasaje p : pasajesPorRuta1) {
//            System.out.println(p);
//        }
//
//        // Borrar un pasaje y comprobar que se ha borrado
//        Pasaje pasajeABorrar = pasajeData.buscarPasajePorId(1);
//        if (pasajeABorrar != null) {
//            pasajeData.borrarPasaje(pasajeABorrar.getIdPasaje());
//            Pasaje pasajeBorrado = pasajeData.buscarPasajePorId(pasajeABorrar.getIdPasaje());
//            if (pasajeBorrado != null) {
//                System.out.println("Pasaje encontrado después de borrar: " + pasajeBorrado);
//            } else {
//                System.out.println("El pasaje ha sido correctamente borrado.");
//            }
//        }

    }
}
