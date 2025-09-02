package vallegrande.edu.pe.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VeterinariaView extends JFrame {

    // Campos comunes
    private JTextField txtNombre, txtEdad;

    // Especie
    private JComboBox<String> cbEspecie;

    // Paneles específicos (CardLayout)
    private JPanel panelEspecificos; // contendrá cards: Perro, Gato, Ave
    // Perro
    private JTextField txtRazaPerro;
    private JComboBox<String> cbTamanoPerro, cbActividadPerro;
    // Gato
    private JTextField txtColorGato;
    private JComboBox<String> cbPeloGato, cbTemperamentoGato;
    // Ave
    private JTextField txtEnvergaduraAve;
    private JComboBox<String> cbCantoAve, cbDietaAve;

    // Acciones
    private JButton btnRegistrar, btnVacunar, btnHistorial, btnBuscar, btnLimpiarBusqueda;
    private JTextField txtBuscar;

    // Tabla
    private JTable tablaMascotas;
    private DefaultTableModel modeloTabla;

    public VeterinariaView() {
        setTitle("🐾 Sistema Veterinaria - Vallegrande");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(980, 640);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(12, 12));

        add(construirFormulario(), BorderLayout.NORTH);
        add(construirTabla(), BorderLayout.CENTER);
        add(construirAcciones(), BorderLayout.SOUTH);

        // Estilos de tabla
        tablaMascotas.setRowHeight(28);
        tablaMascotas.setShowGrid(true);
        tablaMascotas.setIntercellSpacing(new Dimension(10, 5));
        tablaMascotas.setFillsViewportHeight(true);
        tablaMascotas.getTableHeader().setReorderingAllowed(false);

        // Renderer para colorear por vacunación
        tablaMascotas.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Object estado = table.getValueAt(row, 3); // columna "Vacunado"
                if (!isSelected) {
                    if (estado != null && estado.toString().startsWith("Sí")) {
                        c.setBackground(new Color(210, 245, 210)); // verde claro
                    } else {
                        c.setBackground(Color.WHITE);
                    }
                } else {
                    c.setBackground(new Color(180, 205, 255)); // selección azul
                }
                return c;
            }
        });
    }

    // --- Construcción de UI ---

    private JPanel construirFormulario() {
        JPanel wrapper = new JPanel(new BorderLayout(10, 10));
        wrapper.setBorder(BorderFactory.createTitledBorder("Registro de Mascota"));

        // Fila 1 (comunes)
        JPanel fila1 = new JPanel(new GridLayout(2, 6, 8, 8));
        fila1.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        fila1.add(txtNombre);

        fila1.add(new JLabel("Edad (años):"));
        txtEdad = new JTextField();
        fila1.add(txtEdad);

        fila1.add(new JLabel("Especie:"));
        cbEspecie = new JComboBox<>(new String[]{"Perro", "Gato", "Ave"});
        fila1.add(cbEspecie);

        // Panel específico por especie (CardLayout)
        panelEspecificos = new JPanel(new CardLayout());
        panelEspecificos.setBorder(BorderFactory.createTitledBorder("Atributos específicos"));

        panelEspecificos.add(panelPerro(), "Perro");
        panelEspecificos.add(panelGato(), "Gato");
        panelEspecificos.add(panelAve(), "Ave");

        // Cambio de card al cambiar especie
        cbEspecie.addActionListener(e -> {
            CardLayout cl = (CardLayout) panelEspecificos.getLayout();
            cl.show(panelEspecificos, (String) cbEspecie.getSelectedItem());
        });

        wrapper.add(fila1, BorderLayout.NORTH);
        wrapper.add(panelEspecificos, BorderLayout.CENTER);
        return wrapper;
    }

    private JPanel panelPerro() {
        JPanel p = new JPanel(new GridLayout(1, 6, 8, 8));
        p.add(new JLabel("Raza:"));
        txtRazaPerro = new JTextField();
        p.add(txtRazaPerro);

        p.add(new JLabel("Tamaño:"));
        cbTamanoPerro = new JComboBox<>(new String[]{"Pequeño", "Mediano", "Grande"});
        p.add(cbTamanoPerro);

        p.add(new JLabel("Actividad:"));
        cbActividadPerro = new JComboBox<>(new String[]{"Bajo", "Medio", "Alto"});
        p.add(cbActividadPerro);
        return p;
    }

    private JPanel panelGato() {
        JPanel p = new JPanel(new GridLayout(1, 6, 8, 8));
        p.add(new JLabel("Color:"));
        txtColorGato = new JTextField();
        p.add(txtColorGato);

        p.add(new JLabel("Tipo de pelo:"));
        cbPeloGato = new JComboBox<>(new String[]{"Corto", "Medio", "Largo"});
        p.add(cbPeloGato);

        p.add(new JLabel("Temperamento:"));
        cbTemperamentoGato = new JComboBox<>(new String[]{"Tranquilo", "Independiente", "Juguetón"});
        p.add(cbTemperamentoGato);
        return p;
    }

    private JPanel panelAve() {
        JPanel p = new JPanel(new GridLayout(1, 6, 8, 8));
        p.add(new JLabel("Envergadura (cm):"));
        txtEnvergaduraAve = new JTextField();
        p.add(txtEnvergaduraAve);

        p.add(new JLabel("Tipo de canto:"));
        cbCantoAve = new JComboBox<>(new String[]{"Melódico", "Agudo", "Grave"});
        p.add(cbCantoAve);

        p.add(new JLabel("Dieta:"));
        cbDietaAve = new JComboBox<>(new String[]{"Semillas", "Insectos", "Mixta"});
        p.add(cbDietaAve);
        return p;
    }

    private JScrollPane construirTabla() {
        modeloTabla = new DefaultTableModel(
                new Object[]{"Nombre", "Edad", "Especie", "Vacunado", "Detalle"}, 0
        ) {
            // Todas las celdas no editables desde la UI
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaMascotas = new JTable(modeloTabla);
        JScrollPane sp = new JScrollPane(tablaMascotas);
        sp.setBorder(BorderFactory.createTitledBorder("Mascotas registradas"));
        return sp;
    }

    private JPanel construirAcciones() {
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 10));
        actions.setBorder(BorderFactory.createTitledBorder("Acciones"));

        btnRegistrar = new JButton("Registrar");
        btnVacunar = new JButton("Aplicar Vacuna");
        btnHistorial = new JButton("Ver Historial");

        txtBuscar = new JTextField(18);
        btnBuscar = new JButton("Buscar");
        btnLimpiarBusqueda = new JButton("Limpiar");

        actions.add(btnRegistrar);
        actions.add(btnVacunar);
        actions.add(btnHistorial);
        actions.add(new JLabel("Buscar (nombre/especie):"));
        actions.add(txtBuscar);
        actions.add(btnBuscar);
        actions.add(btnLimpiarBusqueda);
        return actions;
    }

    // --- Getters para Controller ---

    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtEdad() { return txtEdad; }
    public JComboBox<String> getCbEspecie() { return cbEspecie; }

    public JTextField getTxtRazaPerro() { return txtRazaPerro; }
    public JComboBox<String> getCbTamanoPerro() { return cbTamanoPerro; }
    public JComboBox<String> getCbActividadPerro() { return cbActividadPerro; }

    public JTextField getTxtColorGato() { return txtColorGato; }
    public JComboBox<String> getCbPeloGato() { return cbPeloGato; }
    public JComboBox<String> getCbTemperamentoGato() { return cbTemperamentoGato; }

    public JTextField getTxtEnvergaduraAve() { return txtEnvergaduraAve; }
    public JComboBox<String> getCbCantoAve() { return cbCantoAve; }
    public JComboBox<String> getCbDietaAve() { return cbDietaAve; }

    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JButton getBtnVacunar() { return btnVacunar; }
    public JButton getBtnHistorial() { return btnHistorial; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnLimpiarBusqueda() { return btnLimpiarBusqueda; }
    public JTextField getTxtBuscar() { return txtBuscar; }

    public JTable getTablaMascotas() { return tablaMascotas; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }
}
