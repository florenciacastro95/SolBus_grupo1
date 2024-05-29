/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author tadeowoto
 */
public class Pasaje {
    
    private int idPasaje;
    private Pasajero pasajero;
    private Colectivo colectivo;
    private Ruta ruta;
    private LocalDate fechaViaje;
    private LocalTime horaViaje;
    private int asiento;
    private double precio;

    public Pasaje(int idPasaje, Pasajero pasajero, Colectivo colectivo, Ruta ruta, LocalDate fechaViaje, LocalTime horaViaje, int asiento, double precio) {
        this.idPasaje = idPasaje;
        this.pasajero = pasajero;
        this.colectivo = colectivo;
        this.ruta = ruta;
        this.fechaViaje = fechaViaje;
        this.horaViaje = horaViaje;
        this.asiento = asiento;
        this.precio = precio;
    }

    public Pasaje() {
    }

    public Pasaje(Pasajero pasajero, Colectivo colectivo, Ruta ruta, LocalDate fechaViaje, LocalTime horaViaje, int asiento, double precio) {
        this.pasajero = pasajero;
        this.colectivo = colectivo;
        this.ruta = ruta;
        this.fechaViaje = fechaViaje;
        this.horaViaje = horaViaje;
        this.asiento = asiento;
        this.precio = precio;
    }

    public int getIdPasaje() {
        return idPasaje;
    }
    
    

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Colectivo getColectivo() {
        return colectivo;
    }

    public void setColectivo(Colectivo colectivo) {
        this.colectivo = colectivo;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public LocalDate getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(LocalDate fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public LocalTime getHoraViaje() {
        return horaViaje;
    }

    public void setHoraViaje(LocalTime horaViaje) {
        this.horaViaje = horaViaje;
    }

    public int getAsiento() {
        return asiento;
    }

    public void setAsiento(int asiento) {
        this.asiento = asiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
    @Override
    public String toString() {
        return "Pasaje nº " + idPasaje + " Pasajero nº "+pasajero.getIdPasajero();
    }
        
    
    
    
}
