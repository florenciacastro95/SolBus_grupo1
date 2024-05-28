package entidades;

public class Colectivo {
    
    private int idColectivo=-1;
    private String matricula, marca, modelo;
    private int capacidad;

    //3 CONSTRUCTORES. Uno vacío, otro que no necesite recibir ID y otro que sí
    public Colectivo() {
    }

    public Colectivo(String matricula, String marca, String modelo, int capacidad) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.capacidad = capacidad;
    }
    
    public Colectivo(int idColectivo, String matricula, String marca, String modelo, int capacidad) {
        this.idColectivo=idColectivo;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.capacidad = capacidad;
    }

    public int getIdColectivo() {
        return idColectivo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Colectivo nº " + idColectivo + ". Matricula: " + matricula;
    }
    
    
}
