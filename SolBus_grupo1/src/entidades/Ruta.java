package entidades;

import java.time.LocalTime;


public class Ruta {
    
    private int idRuta=-1;
    private String origen, destino;
    private LocalTime duracion;

    public Ruta() {
    }

    public Ruta(String origen, String destino, LocalTime duracion) {
        this.origen = origen;
        this.destino = destino;
        this.duracion = duracion;
    }

    public Ruta(int idRuta, String origen, String destino, LocalTime duracion) {
        this.idRuta = idRuta;
        this.origen = origen;
        this.destino = destino;
        this.duracion = duracion;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Ruta nº " + idRuta + ". Origen: " + origen + ". Destino: " + destino;

    }
    
    
    
    
}
