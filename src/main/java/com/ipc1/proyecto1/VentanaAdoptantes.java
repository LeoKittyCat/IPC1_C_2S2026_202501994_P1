package com.ipc1.proyecto1;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel; //Guarda las filas de la tabla y define la estructura

public class VentanaAdoptantes extends JFrame {

    // Declaro estos arriba pa que toda la clase pueda usarlos
    private GestionAdoptantes gestion;
    private VentanaPrincipal ventanaPrincipal;

    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtCorreo;

    private JTable tabla; //Crea el componente visual
    private DefaultTableModel modeloTabla;


    // =========================
    // CONSTRUCTOR
    // =========================

    public VentanaAdoptantes(
            GestionAdoptantes gestion,
            VentanaPrincipal ventanaPrincipal) {

        // Usa el mismo gestor que ya contiene los adoptantes cargados
        this.gestion = gestion;

        // Guarda la ventana principal para poder regresar despues
        this.ventanaPrincipal = ventanaPrincipal;

        setTitle("Gestion de Adoptantes");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        iniciarComponentes();

        // Muestra los adoptantes que ya estaban cargados desde el archivo
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
                new JLabel("GESTION DE ADOPTANTES");

        lblTitulo.setBounds(330, 15, 200, 30);
        panel.add(lblTitulo);


        // =========================
        // CODIGO
        // =========================

        JLabel lblCodigo = new JLabel("Codigo:");
        lblCodigo.setBounds(40, 65, 80, 25);
        panel.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(130, 65, 170, 25);
        panel.add(txtCodigo);


        // =========================
        // NOMBRE
        // =========================

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(40, 105, 80, 25);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(130, 105, 170, 25);
        panel.add(txtNombre);


        // =========================
        // TELEFONO
        // =========================

        JLabel lblTelefono = new JLabel("Telefono:");
        lblTelefono.setBounds(340, 65, 80, 25);
        panel.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(430, 65, 170, 25);
        panel.add(txtTelefono);


        // =========================
        // CORREO
        // =========================

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(340, 105, 80, 25);
        panel.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(430, 105, 170, 25);
        panel.add(txtCorreo);


        // =========================
        // BOTONES
        // =========================

        JButton btnRegistrar =
                new JButton("Registrar");

        btnRegistrar.setBounds(650, 60, 130, 30);
        panel.add(btnRegistrar);


        JButton btnBuscar =
                new JButton("Buscar");

        btnBuscar.setBounds(650, 100, 130, 30);
        panel.add(btnBuscar);


        JButton btnEditar =
                new JButton("Editar");

        btnEditar.setBounds(650, 140, 130, 30);
        panel.add(btnEditar);


        JButton btnEliminar =
                new JButton("Eliminar");

        btnEliminar.setBounds(650, 180, 130, 30);
        panel.add(btnEliminar);


        JButton btnListar =
                new JButton("Listar todos");

        btnListar.setBounds(650, 220, 130, 30);
        panel.add(btnListar);


        JButton btnLimpiar =
                new JButton("Limpiar");

        btnLimpiar.setBounds(650, 260, 130, 30);
        panel.add(btnLimpiar);


        JButton btnRegresar =
                new JButton("Regresar");

        btnRegresar.setBounds(650, 480, 130, 35);
        panel.add(btnRegresar);


        // =========================
        // TABLA
        // =========================

        // Define los nombres de las columnas
        String[] columnas = {
            "Codigo",
            "Nombre",
            "Telefono",
            "Correo"
        };


        // Crea el modelo que va a manejar las filas y columnas
        modeloTabla =
                new DefaultTableModel(columnas, 0) {

            // Evita que los datos puedan editarse directamente desde la tabla
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
        scroll.setBounds(40, 300, 560, 215);


        // Agrega la tabla visible a la ventana
        panel.add(scroll);


        // =========================
        // ACCIONES
        // =========================

        btnRegistrar.addActionListener(e -> registrarAdoptante());

        btnBuscar.addActionListener(e -> buscarAdoptante());

        btnEditar.addActionListener(e -> editarAdoptante());

        btnEliminar.addActionListener(e -> eliminarAdoptante());

        btnListar.addActionListener(e -> actualizarTabla());

        btnLimpiar.addActionListener(e -> limpiarCampos());

        btnRegresar.addActionListener(e -> regresar());


        add(panel);
    }


    // =========================
    // REGISTRAR ADOPTANTE
    // =========================

    private void registrarAdoptante() {

        String codigo =
                txtCodigo.getText().trim();

        String nombre =
                txtNombre.getText().trim();

        String telefono =
                txtTelefono.getText().trim();

        String correo =
                txtCorreo.getText().trim();


        // Evita registrar campos vacios
        if (codigo.isEmpty()
                || nombre.isEmpty()
                || telefono.isEmpty()
                || correo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe llenar todos los campos"
            );

            return;
        }


        Adoptante nuevoAdoptante =
                new Adoptante(
                        codigo,
                        nombre,
                        telefono,
                        correo
                );


        boolean registrado =
                gestion.registrarAdoptante(nuevoAdoptante);


        if (registrado) {

            // Guarda inmediatamente el cambio en adoptantes.txt
            PersistenciaAdoptantes.guardarAdoptantes(gestion);

            JOptionPane.showMessageDialog(
                    this,
                    "Adoptante registrado correctamente"
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
    // BUSCAR ADOPTANTE
    // =========================

    private void buscarAdoptante() {

        String codigo =
                txtCodigo.getText().trim();


        if (codigo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el codigo del adoptante"
            );

            return;
        }


        Adoptante adoptante =
                gestion.buscarPorCodigo(codigo);


        if (adoptante == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Adoptante no encontrado"
            );

            return;
        }


        // Llena los campos con la informacion del adoptante encontrado
        txtNombre.setText(
                adoptante.getNombre()
        );

        txtTelefono.setText(
                adoptante.getTelefono()
        );

        txtCorreo.setText(
                adoptante.getCorreo()
        );


        // Limpia la tabla y muestra solamente el adoptante encontrado
        modeloTabla.setRowCount(0);

        agregarAdoptanteTabla(adoptante);
    }


    // =========================
    // EDITAR ADOPTANTE
    // =========================

    private void editarAdoptante() {

        String codigo =
                txtCodigo.getText().trim();

        String nombre =
                txtNombre.getText().trim();

        String telefono =
                txtTelefono.getText().trim();

        String correo =
                txtCorreo.getText().trim();


        if (codigo.isEmpty()
                || nombre.isEmpty()
                || telefono.isEmpty()
                || correo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe llenar todos los campos"
            );

            return;
        }


        boolean editado =
                gestion.editarAdoptante(
                        codigo,
                        nombre,
                        telefono,
                        correo
                );


        if (editado) {

            // Guarda los nuevos datos en adoptantes.txt
            PersistenciaAdoptantes.guardarAdoptantes(gestion);

            JOptionPane.showMessageDialog(
                    this,
                    "Adoptante editado correctamente"
            );

            actualizarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Adoptante no encontrado"
            );
        }
    }


    // =========================
    // ELIMINAR ADOPTANTE
    // =========================

    private void eliminarAdoptante() {

        String codigo =
                txtCodigo.getText().trim();


        if (codigo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el codigo del adoptante"
            );

            return;
        }


        int respuesta =
                JOptionPane.showConfirmDialog(
                        this,
                        "Desea eliminar este adoptante?",
                        "Confirmar",
                        JOptionPane.YES_NO_OPTION
                );


        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }


        if (gestion.eliminarAdoptante(codigo)) {

            // Guarda activo=false dentro de adoptantes.txt
            PersistenciaAdoptantes.guardarAdoptantes(gestion);

            JOptionPane.showMessageDialog(
                    this,
                    "Adoptante eliminado correctamente"
            );

            actualizarTabla();
            limpiarCampos();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Adoptante no encontrado"
            );
        }
    }


    // =========================
    // ACTUALIZAR TABLA
    // =========================

    private void actualizarTabla() {

        // Borra las filas anteriores para no duplicar datos
        modeloTabla.setRowCount(0);

        Adoptante[] adoptantes =
                gestion.getAdoptantes();

        int cantidad =
                gestion.getCantidadAdoptantes();


        for (int i = 0; i < cantidad; i++) {

            // Solo muestra adoptantes activos
            if (adoptantes[i].isActivo()) {

                agregarAdoptanteTabla(adoptantes[i]);
            }
        }
    }


    // =========================
    // AGREGAR FILA A LA TABLA
    // =========================

    private void agregarAdoptanteTabla(Adoptante adoptante) {

        // Convierte al adoptante registrado en una fila
        Object[] fila = {
            adoptante.getCodigo(),
            adoptante.getNombre(),
            adoptante.getTelefono(),
            adoptante.getCorreo()
        };


        // Esto agrega cada adoptante a la tabla
        modeloTabla.addRow(fila);
    }


    // =========================
    // LIMPIAR CAMPOS
    // =========================

    private void limpiarCampos() {

        txtCodigo.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
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