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
import javax.swing.table.DefaultTableModel; //Guarda las filas de la tabla (define la estructura)

public class VentanaAnimales extends JFrame {

    // Decalramos todos estos arriba pa que toda la clase pueda usarla
    private GestionAnimales gestion;
    private VentanaPrincipal ventanaPrincipal;
    private GestionSolicitudes gestionSolicitudes;
    private GestionUbicaciones gestionUbicaciones;
    
    //Bitacora
    private GestionBitacora gestionBitacora;
    private Usuario usuarioActual;

    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtEdad;

    private JComboBox<String> cmbEspecie;
    private JComboBox<String> cmbEstado;

    private JTable tabla; //Crea el componente visual
    private DefaultTableModel modeloTabla;


    // =========================
    // CONSTRUCTOR
    // =========================

    public VentanaAnimales(
            GestionAnimales gestion,
            GestionSolicitudes gestionSolicitudes,
            GestionUbicaciones gestionUbicaciones,
            GestionBitacora gestionBitacora,
            Usuario usuarioActual,
            VentanaPrincipal ventanaPrincipal) {

        // Usa el mismo gestor que ya contiene los animales cargados
        this.gestion = gestion;

        // Guarda el gestor de bitacora para registrar las acciones importantes
        this.gestionBitacora = gestionBitacora;

        // Guarda quien inicio sesion para saber quien hizo cada accion
        this.usuarioActual = usuarioActual;

        // Guarda la ventana principal para poder regresar despues
        this.ventanaPrincipal = ventanaPrincipal;
        
        this.gestionSolicitudes = gestionSolicitudes;
        
        this.gestionUbicaciones = gestionUbicaciones;

        setTitle("Gestion de Animales");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        iniciarComponentes();

        // Muestra los animales que ya estaban cargados desde el archivo
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
                new JLabel("GESTION DE ANIMALES");

        lblTitulo.setBounds(340, 15, 180, 30);
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
        // ESPECIE
        // =========================

        JLabel lblEspecie = new JLabel("Especie:");
        lblEspecie.setBounds(40, 145, 80, 25);
        panel.add(lblEspecie);

        cmbEspecie =
                new JComboBox<>(
                        new String[]{"Perro", "Gato"}
                );

        cmbEspecie.setBounds(130, 145, 170, 25);
        panel.add(cmbEspecie);


        // =========================
        // EDAD
        // =========================

        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setBounds(340, 65, 80, 25);
        panel.add(lblEdad);

        txtEdad = new JTextField();
        txtEdad.setBounds(430, 65, 170, 25);
        panel.add(txtEdad);


        // =========================
        // ESTADO
        // =========================

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(340, 105, 80, 25);
        panel.add(lblEstado);

        cmbEstado =
                new JComboBox<>(
                        new String[]{
                            "Ingresado",
                            "En tratamiento",
                            "Disponible"
                        }
                );

        cmbEstado.setBounds(430, 105, 170, 25);
        panel.add(cmbEstado);


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
                new JButton("Editar estado");

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
            "Especie",
            "Edad",
            "Estado"
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

        //Tabla visual
        tabla = new JTable(modeloTabla);
        
        //Mete la tabla dentro de un scroll pa mostrarla con desplazamiento
        JScrollPane scroll =
                new JScrollPane(tabla);
        
        //Decide donde aparece y cuanto mide
        scroll.setBounds(40, 300, 560, 215);
        
        //Agrega la tabla visible a la ventana
        panel.add(scroll);


        // =========================
        // ACCIONES
        // =========================

        btnRegistrar.addActionListener(e -> registrarAnimal());

        btnBuscar.addActionListener(e -> buscarAnimal());

        btnEditar.addActionListener(e -> editarEstado());

        btnEliminar.addActionListener(e -> eliminarAnimal());

        btnListar.addActionListener(e -> actualizarTabla());

        btnLimpiar.addActionListener(e -> limpiarCampos());

        btnRegresar.addActionListener(e -> regresar());


        add(panel);
    }


    // =========================
    // REGISTRAR ANIMAL
    // =========================

    private void registrarAnimal() {

        String codigo =
                txtCodigo.getText().trim();

        String nombre =
                txtNombre.getText().trim();

        String especie =
                cmbEspecie.getSelectedItem().toString();

        String textoEdad =
                txtEdad.getText().trim();


        // Evita registrar datos vacios
        if (codigo.isEmpty()
                || nombre.isEmpty()
                || textoEdad.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe llenar todos los campos"
            );

            return;
        }


        try {

            int edad =
                    Integer.parseInt(textoEdad);


            if (edad < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "La edad no puede ser negativa"
                );

                return;
            }


            Animal nuevoAnimal =
                    new Animal(
                            codigo,
                            nombre,
                            especie,
                            edad
                    );


            boolean registrado =
                    gestion.registrarAnimal(nuevoAnimal);


            if (registrado) {

                // Guarda inmediatamente el cambio en animales.txt
                PersistenciaAnimales.guardarAnimales(gestion);
                
                // Registra quien ingreso al animal (Bitacora)
                gestionBitacora.registrarAccion(
                        new Bitacora(
                                "Registrar animal",
                                "Se registro el animal " + codigo,
                                usuarioActual.getUsuario()
                        )
                );

                // Guarda inmediatamente la bitacora
                PersistenciaBitacora.guardarBitacora(
                        gestionBitacora
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Animal registrado correctamente"
                );

                actualizarTabla();
                limpiarCampos();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "El codigo ya existe o no hay espacio disponible"
                );
            }


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "La edad debe ser un numero entero"
            );
        }
    }


    // =========================
    // BUSCAR ANIMAL
    // =========================

    private void buscarAnimal() {

        String codigo =
                txtCodigo.getText().trim();


        if (codigo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el codigo del animal"
            );

            return;
        }


        Animal animal =
                gestion.buscarPorCodigo(codigo);


        if (animal == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Animal no encontrado"
            );

            return;
        }


        // Llena los campos con la informacion del animal encontrado
        txtNombre.setText(animal.getNombre());

        cmbEspecie.setSelectedItem(
                animal.getEspecie()
        );

        txtEdad.setText(
                String.valueOf(animal.getEdad())
        );

        cmbEstado.setSelectedItem(
                animal.getEstado()
        );


        // Limpia la tabla y muestra solamente el animal encontrado
        modeloTabla.setRowCount(0);

        agregarAnimalTabla(animal);
    }


    // =========================
    // EDITAR ESTADO
    // =========================

    private void editarEstado() {

        String codigo =
                txtCodigo.getText().trim();

        String estado =
                cmbEstado.getSelectedItem().toString();


        if (codigo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el codigo del animal"
            );

            return;
        }


        if (gestion.editarEstado(codigo, estado)) {

            // Guarda el nuevo estado en el archivo
            PersistenciaAnimales.guardarAnimales(gestion);
            
            // Registra el cambio de estado del animal (bitacora)
            gestionBitacora.registrarAccion(
                    new Bitacora(
                            "Editar estado animal",
                            "Se cambio el estado del animal "
                            + codigo
                            + " a "
                            + estado,
                            usuarioActual.getUsuario()
                    )
            );

            PersistenciaBitacora.guardarBitacora(
                    gestionBitacora
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Estado actualizado correctamente"
            );

            actualizarTabla();
            
            // Limpia los campos para no dejar datos de otro animal en pantalla
            limpiarCampos();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Animal no encontrado"
            );
        }
    }


    // =========================
    // ELIMINAR ANIMAL
    // =========================

    private void eliminarAnimal() {

        String codigo =
                txtCodigo.getText().trim();


        if (codigo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el codigo del animal"
            );

            return;
        }


        // Primero comprueba que el animal exista
        Animal animal =
                gestion.buscarPorCodigo(codigo);


        if (animal == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Animal no encontrado"
            );

            return;
        }


        // No permite eliminar un animal que todavia ocupa un espacio
        if (gestionUbicaciones.animalYaAsignado(animal)) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se puede eliminar el animal porque todavia ocupa un espacio del refugio"
            );

            return;
        }


        // No permite eliminar un animal con solicitudes pendientes
        int pendientes =
                gestionSolicitudes.contarPendientesPorAnimal(codigo);


        if (pendientes > 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se puede eliminar el animal porque tiene "
                    + pendientes
                    + " solicitud(es) pendiente(s)"
            );

            return;
        }


        // Confirmacion normal
        int respuesta =
                JOptionPane.showConfirmDialog(
                        this,
                        "Desea eliminar este animal?",
                        "Confirmar",
                        JOptionPane.YES_NO_OPTION
                );


        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }


        // Eliminacion logica
        if (gestion.eliminarAnimal(codigo)) {

            PersistenciaAnimales.guardarAnimales(
                    gestion
            );


            gestionBitacora.registrarAccion(
                    new Bitacora(
                            "Eliminar animal",
                            "Se elimino logicamente el animal "
                            + codigo,
                            usuarioActual.getUsuario()
                    )
            );


            PersistenciaBitacora.guardarBitacora(
                    gestionBitacora
            );


            JOptionPane.showMessageDialog(
                    this,
                    "Animal eliminado correctamente"
            );


            actualizarTabla();
            limpiarCampos();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Animal no encontrado"
            );
        }
    }


    // =========================
    // ACTUALIZAR TABLA
    // =========================

    private void actualizarTabla() {

        // Borra las filas anteriores para no duplicar datos
        modeloTabla.setRowCount(0);

        Animal[] animales =
                gestion.getAnimales();

        int cantidad =
                gestion.getCantidadAnimales();


        for (int i = 0; i < cantidad; i++) {

            // La eliminacion es logica por eso solo muestra los activos
            if (animales[i].isActivo()) {

                agregarAnimalTabla(animales[i]);
            }
        }
    }


    // =========================
    // AGREGAR FILA A LA TABLA
    // =========================

    private void agregarAnimalTabla(Animal animal) {
        //Convierte al animal registrado en una fila
        Object[] fila = {
            animal.getCodigo(),
            animal.getNombre(),
            animal.getEspecie(),
            animal.getEdad(),
            animal.getEstado()
        };
        
        //Esto agrega cada animal
        modeloTabla.addRow(fila);
    }


    // =========================
    // LIMPIAR CAMPOS
    // =========================

    private void limpiarCampos() {

        txtCodigo.setText("");
        txtNombre.setText("");
        txtEdad.setText("");

        cmbEspecie.setSelectedIndex(0);
        cmbEstado.setSelectedIndex(0);
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