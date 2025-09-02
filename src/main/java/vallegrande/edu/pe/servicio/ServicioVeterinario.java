package vallegrande.edu.pe.servicio;

import vallegrande.edu.pe.model.Mascota;

public abstract class ServicioVeterinario {
    public abstract void aplicarVacuna(Mascota mascota);
    public abstract void darTratamiento(Mascota mascota, String tratamiento);
    public abstract void realizarChequeo(Mascota mascota);
}
