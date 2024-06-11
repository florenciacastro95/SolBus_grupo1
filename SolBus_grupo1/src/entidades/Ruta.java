package entidades;

import java.time.LocalTime;


public class Ruta {
    
    private int idRuta=-1;
    private String origen, destino;
    private LocalTime duracion;
    private boolean estado;
    
    public Ruta() {
    }

    public Ruta(String origen, String destino, LocalTime duracion) {
        this.origen = origen;
        this.destino = destino;
        this.duracion = duracion;
        this.estado= true;
    }

    public Ruta(int idRuta, String origen, String destino, LocalTime duracion, boolean estado) {
        this.idRuta = idRuta;
        this.origen = origen;
        this.destino = destino;
        this.duracion = duracion;
        this.estado=estado;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    

    @Override
    public String toString() {
        return origen + " a " + destino;

    }
    
    
    
    
}
