package vallegrande.edu.pe.servicio;

import vallegrande.edu.pe.model.Mascota;

public class ServicioVeterinarioImpl extends ServicioVeterinario {

    @Override
    public void aplicarVacuna(Mascota mascota) {
        System.out.println("Aplicando vacuna a " + mascota.getNombre());
        mascota.registrarVisita();
    }

    @Override
    public void darTratamiento(Mascota mascota, String tratamiento) {
        System.out.println("Tratamiento '" + tratamiento + "' aplicado a " + mascota.getNombre());
        mascota.registrarVisita();
    }

    @Override
    public void realizarChequeo(Mascota mascota) {
        System.out.println("Chequeo realizado a " + mascota.mostrarInfo());
        mascota.registrarVisita();
    }
}
