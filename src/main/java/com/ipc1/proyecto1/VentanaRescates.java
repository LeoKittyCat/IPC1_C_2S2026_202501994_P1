package com.ipc1.proyecto1;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel; //Guarda las filas de la tabla y define la estructura

public class VentanaRescates extends JFrame {

    // Declaro estos arriba pa que toda la clase pueda usarlos
    private GestionRescate gestion;
    private VentanaPrincipal ventanaPrincipal;
    
    // Bitacora
    private GestionBitacora gestionBitacora;
    private Usuario usuarioActual;

    private JTextField txtCodigo;
    private JTextField txtDescripcion;
    private JTextField txtUbicacion;

    private JComboBox<String> cmbPrioridad;

    private JTable tabla; //Crea el componente visual
    private DefaultTableModel modeloTabla;


    // =========================
    // CONSTRUCTOR
    // =========================

    public VentanaRescates(
            GestionRescate gestion,
            GestionBitacora gestionBitacora,
            Usuario usuarioActual,
            VentanaPrincipal ventanaPrincipal) {

        // Usa el mismo gestor que ya contiene los rescates cargados
        this.gestion = gestion;

        // Guarda la bitacora para registrar las acciones
        this.gestionBitacora = gestionBitacora;

        // Guarda quien inicio sesion
        this.usuarioActual = usuarioActual;

        // Guarda la ventana principal para poder regresar despues
        this.ventanaPrincipal = ventanaPrincipal;

        setTitle("Gestion de Rescates");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        iniciarComponentes();

        // Muestra los rescates que ya estaban cargados desde el archivo
        actualizarTabla();
    }


    // =========================
    // COMPONENTES
    // =========================

    private void iniciarComponentes() {

        JPanel panel = new JPanel();

        // Permite colocar los componentes usando coordenadas
        panel.setLayout(null);


        JLabel lblTitulo =
                new JLabel("GESTION DE RESCATES");

        lblTitulo.setBounds(350, 15, 200, 30);
        panel.add(lblTitulo);


        // =========================
        // CODIGO
        // =========================

        JLabel lblCodigo = new JLabel("Codigo:");
        lblCodigo.setBounds(40, 65, 80, 25);
        panel.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(140, 65, 180, 25);
        panel.add(txtCodigo);


        // =========================
        // DESCRIPCION
        // =========================

        JLabel lblDescripcion = new JLabel("Descripcion:");
        lblDescripcion.setBounds(40, 105, 100, 25);
        panel.add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(140, 105, 180, 25);
        panel.add(txtDescripcion);


        // =========================
        // UBICACION
        // =========================

        JLabel lblUbicacion = new JLabel("Ubicacion:");
        lblUbicacion.setBounds(360, 65, 90, 25);
        panel.add(lblUbicacion);

        txtUbicacion = new JTextField();
        txtUbicacion.setBounds(450, 65, 180, 25);
        panel.add(txtUbicacion);


        // =========================
        // PRIORIDAD
        // =========================

        JLabel lblPrioridad = new JLabel("Prioridad:");
        lblPrioridad.setBounds(360, 105, 90, 25);
        panel.add(lblPrioridad);

        cmbPrioridad =
                new JComboBox<>(
                        new String[]{
                            "Baja",
                            "Media",
                            "Alta"
                        }
                );

        cmbPrioridad.setBounds(450, 105, 180, 25);
        panel.add(cmbPrioridad);


        // =========================
        // BOTONES
        // =========================

        JButton btnRegistrar =
                new JButton("Registrar");

        btnRegistrar.setBounds(680, 60, 140, 30);
        panel.add(btnRegistrar);


        JButton btnBuscar =
                new JButton("Buscar");

        btnBuscar.setBounds(680, 100, 140, 30);
        panel.add(btnBuscar);


        JButton btnCambiarPrioridad =
                new JButton("Cambiar prioridad");

        btnCambiarPrioridad.setBounds(680, 140, 140, 30);
        panel.add(btnCambiarPrioridad);


        JButton btnAtender =
                new JButton("Atender");

        btnAtender.setBounds(680, 180, 140, 30);
        panel.add(btnAtender);


        JButton btnActivos =
                new JButton("Ver activos");

        btnActivos.setBounds(680, 220, 140, 30);
        panel.add(btnActivos);


        JButton btnHistorial =
                new JButton("Ver historial");

        btnHistorial.setBounds(680, 260, 140, 30);
        panel.add(btnHistorial);


        JButton btnLimpiar =
                new JButton("Limpiar");

        btnLimpiar.setBounds(680, 300, 140, 30);
        panel.add(btnLimpiar);


        JButton btnRegresar =
                new JButton("Regresar");

        btnRegresar.setBounds(680, 480, 140, 35);
        panel.add(btnRegresar);


        // =========================
        // TABLA
        // =========================

        // Define los nombres de las columnas
        String[] columnas = {
            "Codigo",
            "Descripcion",
            "Ubicacion",
            "Prioridad",
            "Estado"
        };


        // Crea el modelo que va a manejar las filas y columnas
        modeloTabla =
                new DefaultTableModel(columnas, 0) {

            // Evita editar los datos directamente desde la tabla
            @Override
            public boolean isCellEditable(
                    int fila,
                    int columna) {

                return false;
            }
        };


        // Tabla visual
        tabla = new JTable(modeloTabla);


        // Mete la tabla dentro de un scroll pa mostrarla con desplazamiento
        JScrollPane scroll =
                new JScrollPane(tabla);


        // Decide donde aparece y cuanto mide
        scroll.setBounds(40, 350, 590, 165);


        // Agrega la tabla visible a la ventana
        panel.add(scroll);


        // =========================
        // ACCIONES
        // =========================

        btnRegistrar.addActionListener(e -> registrarRescate());

        btnBuscar.addActionListener(e -> buscarRescate());

        btnCambiarPrioridad.addActionListener(e -> cambiarPrioridad());

        btnAtender.addActionListener(e -> atenderRescate());

        btnActivos.addActionListener(e -> mostrarActivos());

        btnHistorial.addActionListener(e -> actualizarTabla());

        btnLimpiar.addActionListener(e -> limpiarCampos());

        btnRegresar.addActionListener(e -> regresar());


        add(panel);
    }


    // =========================
    // REGISTRAR RESCATE
    // =========================

    private void registrarRescate() {

        String codigo =
                txtCodigo.getText().trim();

        String descripcion =
                txtDescripcion.getText().trim();

        String ubicacion =
                txtUbicacion.getText().trim();

        String prioridad =
                cmbPrioridad.getSelectedItem().toString();


        // Evita registrar campos vacios
        if (codigo.isEmpty()
                || descripcion.isEmpty()
                || ubicacion.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe llenar todos los campos"
            );

            return;
        }


        Rescate nuevoRescate =
                new Rescate(
                        codigo,
                        descripcion,
                        ubicacion,
                        prioridad
                );


        boolean registrado =
                gestion.registrarRescate(nuevoRescate);


        if (registrado) {

            // Guarda inmediatamente el rescate en rescates.txt
            PersistenciaRescate.guardarRescates(gestion);
            
            // Registra el nuevo rescate en la bitacora
            gestionBitacora.registrarAccion(
                    new Bitacora(
                            "Registrar rescate",
                            "Se registro el rescate " + codigo,
                            usuarioActual.getUsuario()
                    )
            );

            PersistenciaBitacora.guardarBitacora(
                    gestionBitacora
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Rescate registrado correctamente"
            );

            actualizarTabla();
            limpiarCampos();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "El codigo ya existe o no hay espacio disponible"
            );
        }
    }


    // =========================
    // BUSCAR RESCATE
    // =========================

    private void buscarRescate() {

        String codigo =
                txtCodigo.getText().trim();


        if (codigo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el codigo del rescate"
            );

            return;
        }


        Rescate rescate =
                gestion.buscarPorCodigo(codigo);


        if (rescate == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Rescate no encontrado"
            );

            return;
        }


        // Llena los campos con los datos del rescate encontrado
        txtDescripcion.setText(
                rescate.getDescripcion()
        );

        txtUbicacion.setText(
                rescate.getUbicacion()
        );

        cmbPrioridad.setSelectedItem(
                rescate.getPrioridad()
        );


        // Limpia la tabla y muestra solamente el rescate encontrado
        modeloTabla.setRowCount(0);

        agregarRescateTabla(rescate);
    }


    // =========================
    // CAMBIAR PRIORIDAD
    // =========================

    private void cambiarPrioridad() {

        String codigo =
                txtCodigo.getText().trim();

        String prioridad =
                cmbPrioridad.getSelectedItem().toString();


        if (codigo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el codigo del rescate"
            );

            return;
        }


        if (gestion.cambiarPrioridad(codigo, prioridad)) {

            // Guarda la nueva prioridad en el archivo
            PersistenciaRescate.guardarRescates(gestion);

            JOptionPane.showMessageDialog(
                    this,
                    "Prioridad actualizada correctamente"
            );

            actualizarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Rescate no encontrado"
            );
        }
    }


    // =========================
    // ATENDER RESCATE
    // =========================

    private void atenderRescate() {

        String codigo =
                txtCodigo.getText().trim();


        if (codigo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el codigo del rescate"
            );

            return;
        }


        Rescate rescate =
                gestion.buscarPorCodigo(codigo);


        if (rescate == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Rescate no encontrado"
            );

            return;
        }


        // Evita atender dos veces el mismo rescate
        if (rescate.getEstado().equalsIgnoreCase("Atendido")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Este rescate ya fue atendido"
            );

            return;
        }


        if (gestion.atenderRescate(codigo)) {

            // Guarda el estado Atendido en el archivo
            PersistenciaRescate.guardarRescates(gestion);

            // Registra que el rescate fue atendido
            gestionBitacora.registrarAccion(
                    new Bitacora(
                            "Atender rescate",
                            "Se atendio el rescate " + codigo,
                            usuarioActual.getUsuario()
                    )
            );

            PersistenciaBitacora.guardarBitacora(
                    gestionBitacora
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Rescate atendido correctamente"
            );

            actualizarTabla();
        }
    }


    // =========================
    // MOSTRAR ACTIVOS
    // =========================

    private void mostrarActivos() {

        // Borra las filas anteriores
        modeloTabla.setRowCount(0);

        Rescate[] rescates =
                gestion.getRescates();

        int cantidad =
                gestion.getCantidadRescates();


        for (int i = 0; i < cantidad; i++) {

            if (rescates[i].getEstado()
                    .equalsIgnoreCase("Activo")) {

                agregarRescateTabla(rescates[i]);
            }
        }
    }


    // =========================
    // ACTUALIZAR TABLA
    // =========================

    private void actualizarTabla() {

        // Borra las filas anteriores para no duplicar datos
        modeloTabla.setRowCount(0);

        Rescate[] rescates =
                gestion.getRescates();

        int cantidad =
                gestion.getCantidadRescates();


        for (int i = 0; i < cantidad; i++) {

            agregarRescateTabla(
                    rescates[i]
            );
        }
    }


    // =========================
    // AGREGAR FILA A LA TABLA
    // =========================

    private void agregarRescateTabla(
            Rescate rescate) {

        // Convierte el rescate registrado en una fila
        Object[] fila = {
            rescate.getCodigo(),
            rescate.getDescripcion(),
            rescate.getUbicacion(),
            rescate.getPrioridad(),
            rescate.getEstado()
        };


        // Agrega cada rescate a la tabla
        modeloTabla.addRow(fila);
    }


    // =========================
    // LIMPIAR CAMPOS
    // =========================

    private void limpiarCampos() {

        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtUbicacion.setText("");

        cmbPrioridad.setSelectedIndex(0);
    }


    // =========================
    // REGRESAR
    // =========================

    private void regresar() {

        // Vuelve a mostrar el menu principal
        ventanaPrincipal.setVisible(true);

        // Cierra esta ventana
        dispose();
    }
}