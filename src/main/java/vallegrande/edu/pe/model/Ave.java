package vallegrande.edu.pe.model;

public class Ave extends Mascota {
    private double envergadura;
    private String tipoCanto;
    private String dieta;

    public Ave(String nombre, int edad, double envergadura, String tipoCanto, String dieta) {
        super(nombre, edad, "Ave");
        this.envergadura = envergadura;
        this.tipoCanto = tipoCanto;
        this.dieta = dieta;
    }

    @Override
    public String mostrarInfo() {
        return getNombre() + " - Edad " + getEdad() + " años - Envergadura: " + envergadura +
                "cm - Canto: " + tipoCanto + " - Dieta: " + dieta;
    }
}
