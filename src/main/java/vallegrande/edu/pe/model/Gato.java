package vallegrande.edu.pe.model;

public class Gato extends Mascota {
    private String color;
    private String tipoPelo;
    private String temperamento;

    public Gato(String nombre, int edad, String color, String tipoPelo, String temperamento) {
        super(nombre, edad, "Gato");
        this.color = color;
        this.tipoPelo = tipoPelo;
        this.temperamento = temperamento;
    }

    @Override
    public String mostrarInfo() {
        return getNombre() + " - Color " + color + " - Edad " + getEdad() +
                " años - Temperamento: " + temperamento;
    }
}
