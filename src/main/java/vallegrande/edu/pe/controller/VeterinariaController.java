package vallegrande.edu.pe.controller;

import vallegrande.edu.pe.model.Mascota;
import vallegrande.edu.pe.model.Perro;
import vallegrande.edu.pe.model.Gato;
import vallegrande.edu.pe.model.Ave;
import vallegrande.edu.pe.view.VeterinariaView;

import javax.swing.*;
import java.util.ArrayList;

public class VeterinariaController {

    private final VeterinariaView view;
    private final ArrayList<Mascota> mascotas = new ArrayList<>();

    public VeterinariaController(VeterinariaView view) {
        this.view = view;

        // Listeners
        view.getBtnRegistrar().addActionListener(e -> registrarMascota());
        view.getBtnVacunar().addActionListener(e -> aplicarVacuna());
        view.getBtnHistorial().addActionListener(e -> verHistorial());
        view.getBtnBuscar().addActionListener(e -> buscar());
        view.getBtnLimpiarBusqueda().addActionListener(e -> limpiarBusqueda());
    }

    // --- Acciones ---

    private void registrarMascota() {
        String nombre = view.getTxtNombre().getText().trim();
        String edadStr = view.getTxtEdad().getText().trim();
        String especie = (String) view.getCbEspecie().getSelectedItem();

        // Validaciones básicas
        if (nombre.isEmpty() || edadStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Complete Nombre y Edad.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int edad;
        try {
            edad = Integer.parseInt(edadStr);
            if (edad < 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Edad debe ser un entero ≥ 0.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Mascota m = null;
        String detalle = "";

        switch (especie) {
            case "Perro" -> {
                String raza = view.getTxtRazaPerro().getText().trim();
                String tam = (String) view.getCbTamanoPerro().getSelectedItem();
                String act = (String) view.getCbActividadPerro().getSelectedItem();
                if (raza.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Ingrese la raza del perro.", "Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                m = new Perro(nombre, edad, raza, tam, act);
                detalle = "Raza: " + raza + ", Tamaño: " + tam + ", Actividad: " + act;
            }
            case "Gato" -> {
                String color = view.getTxtColorGato().getText().trim();
                String pelo = (String) view.getCbPeloGato().getSelectedItem();
                String temp = (String) view.getCbTemperamentoGato().getSelectedItem();
                if (color.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Ingrese el color del gato.", "Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                m = new Gato(nombre, edad, color, pelo, temp);
                detalle = "Color: " + color + ", Pelo: " + pelo + ", Temperamento: " + temp;
            }
            case "Ave" -> {
                String envStr = view.getTxtEnvergaduraAve().getText().trim();
                String canto = (String) view.getCbCantoAve().getSelectedItem();
                String dieta = (String) view.getCbDietaAve().getSelectedItem();
                double envergadura;
                try {
                    envergadura = Double.parseDouble(envStr);
                    if (envergadura <= 0) throw new NumberFormatException();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view, "Envergadura debe ser numérica > 0.", "Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                m = new Ave(nombre, edad, envergadura, canto, dieta);
                detalle = "Enverg: " + envergadura + " cm, Canto: " + canto + ", Dieta: " + dieta;
            }
        }

        if (m != null) {
            mascotas.add(m);
            view.getModeloTabla().addRow(new Object[]{
                    m.getNombre(),
                    m.getEdad(),
                    m.getEspecie(),
                    "No",           // estado de vacunación en la tabla
                    detalle
            });
            limpiarFormulario();
        }
    }

    private void aplicarVacuna() {
        int filaVista = view.getTablaMascotas().getSelectedRow();
        if (filaVista == -1) {
            JOptionPane.showMessageDialog(view, "Seleccione una mascota en la tabla.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        // Como no usamos sorter, el índice de vista coincide con el de modelo
        view.getModeloTabla().setValueAt("Sí", filaVista, 3);
        String nombre = view.getModeloTabla().getValueAt(filaVista, 0).toString();
        JOptionPane.showMessageDialog(view, "Vacuna aplicada a " + nombre + ".", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void verHistorial() {
        JOptionPane.showMessageDialog(view, "Historial en construcción 🛠️", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void buscar() {
        String q = view.getTxtBuscar().getText().trim().toLowerCase();
        if (q.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Ingrese texto para buscar (nombre o especie).", "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int rows = view.getModeloTabla().getRowCount();
        for (int i = 0; i < rows; i++) {
            String nombre = view.getModeloTabla().getValueAt(i, 0).toString().toLowerCase();
            String especie = view.getModeloTabla().getValueAt(i, 2).toString().toLowerCase();
            if (nombre.contains(q) || especie.contains(q)) {
                view.getTablaMascotas().setRowSelectionInterval(i, i);
                view.getTablaMascotas().scrollRectToVisible(view.getTablaMascotas().getCellRect(i, 0, true));
                return;
            }
        }
        JOptionPane.showMessageDialog(view, "Sin resultados.", "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
    }

    private void limpiarBusqueda() {
        view.getTxtBuscar().setText("");
        view.getTablaMascotas().clearSelection();
    }

    // --- Utilidades ---

    private void limpiarFormulario() {
        view.getTxtNombre().setText("");
        view.getTxtEdad().setText("");
        view.getCbEspecie().setSelectedIndex(0);

        view.getTxtRazaPerro().setText("");
        view.getCbTamanoPerro().setSelectedIndex(1);   // Mediano
        view.getCbActividadPerro().setSelectedIndex(1);// Medio

        view.getTxtColorGato().setText("");
        view.getCbPeloGato().setSelectedIndex(0);
        view.getCbTemperamentoGato().setSelectedIndex(0);

        view.getTxtEnvergaduraAve().setText("");
        view.getCbCantoAve().setSelectedIndex(0);
        view.getCbDietaAve().setSelectedIndex(0);
    }
}
