package vallegrande.edu.pe.model;

public class Perro extends Mascota {
    private String raza;
    private String tamano;
    private String nivelActividad;

    public Perro(String nombre, int edad, String raza, String tamano, String nivelActividad) {
        super(nombre, edad, "Perro");
        this.raza = raza;
        this.tamano = tamano;
        this.nivelActividad = nivelActividad;
    }

    @Override
    public String mostrarInfo() {
        return getNombre() + " - Raza " + raza + " - Edad " + getEdad() +
                " años - Actividad: " + nivelActividad;
    }
}
