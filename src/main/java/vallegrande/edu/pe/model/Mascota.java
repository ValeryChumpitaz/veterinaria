package vallegrande.edu.pe.model;

import java.time.LocalDate;

public class Mascota {
    private String nombre;
    private int edad;
    private String especie;
    private LocalDate ultimaVisita;

    public Mascota(String nombre, int edad, String especie) {
        this.nombre = nombre;
        this.edad = edad;
        this.especie = especie;
        this.ultimaVisita = LocalDate.now();
    }

    // Getters y Setters (encapsulamiento)
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public LocalDate getUltimaVisita() { return ultimaVisita; }

    // Métodos comunes
    public void registrarVisita() {
        this.ultimaVisita = LocalDate.now();
    }

    public int calcularEdad() {
        return edad;
    }

    public String mostrarInfo() {
        return nombre + " - Especie: " + especie + " - Edad: " + edad + " años";
    }
}
