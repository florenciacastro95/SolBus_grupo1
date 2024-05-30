
package entidades;

import java.time.LocalTime;


public class Horario {
    
    private int idHorario=-1;
    private Ruta ruta;
    private LocalTime horaSalida,horaLlegada;

    public Horario() {
    }

    public Horario(int idHorario, Ruta ruta, LocalTime horaSalida, LocalTime horaLlegada) {
        this.idHorario = idHorario;
        this.ruta = ruta;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
    }

    public Horario(Ruta ruta, LocalTime horaSalida, LocalTime horaLlegada) {
        this.ruta = ruta;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }
       
    
    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(LocalTime horaLlegada) {
        this.horaLlegada = horaLlegada;
    }
    
  
    @Override
    public String toString() {
        return "Horario nº " + idHorario;
    }
            
}
