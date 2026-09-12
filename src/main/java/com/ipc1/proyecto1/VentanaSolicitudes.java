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

public class VentanaSolicitudes extends JFrame {

    // Declaro estos arriba pa que toda la clase pueda usarlos
    private GestionSolicitudes gestion;
    private GestionAdoptantes gestionAdoptantes;
    private GestionAnimales gestionAnimales;
    private VentanaPrincipal ventanaPrincipal;
    private GestionUbicaciones gestionUbicaciones;
    
    // Bitacora
    private GestionBitacora gestionBitacora;
    private Usuario usuarioActual;

    private JTextField txtCodigo;
    private JTextField txtCodigoAdoptante;
    private JTextField txtCodigoAnimal;

    private JComboBox<String> cmbEstado;

    private JTable tabla; //Crea el componente visual
    private DefaultTableModel modeloTabla;


    // =========================
    // CONSTRUCTOR
    // =========================

    public VentanaSolicitudes(
            GestionSolicitudes gestion,
            GestionAdoptantes gestionAdoptantes,
            GestionAnimales gestionAnimales,
            GestionUbicaciones gestionUbicaciones,
            GestionBitacora gestionBitacora,
            Usuario usuarioActual,
            VentanaPrincipal ventanaPrincipal) {

        // Usa los mismos gestores que ya contienen los datos cargados
        this.gestion = gestion;
        this.gestionAdoptantes = gestionAdoptantes;
        this.gestionAnimales = gestionAnimales;
        this.gestionUbicaciones = gestionUbicaciones;

        // Guarda la bitacora para registrar las acciones
        this.gestionBitacora = gestionBitacora;

        // Guarda quien inicio sesion
        this.usuarioActual = usuarioActual;

        // Guarda la ventana principal para poder regresar despues
        this.ventanaPrincipal = ventanaPrincipal;

        setTitle("Gestion de Solicitudes");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        iniciarComponentes();

        // Muestra las solicitudes que ya estaban cargadas desde el archivo
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
                new JLabel("GESTION DE SOLICITUDES");

        lblTitulo.setBounds(330, 15, 200, 30);
        panel.add(lblTitulo);


        // =========================
        // CODIGO DE SOLICITUD
        // =========================

        JLabel lblCodigo =
                new JLabel("Codigo:");

        lblCodigo.setBounds(40, 65, 120, 25);
        panel.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(170, 65, 170, 25);
        panel.add(txtCodigo);


        // =========================
        // CODIGO ADOPTANTE
        // =========================

        JLabel lblAdoptante =
                new JLabel("Cod. Adoptante:");

        lblAdoptante.setBounds(40, 105, 120, 25);
        panel.add(lblAdoptante);

        txtCodigoAdoptante = new JTextField();
        txtCodigoAdoptante.setBounds(170, 105, 170, 25);
        panel.add(txtCodigoAdoptante);


        // =========================
        // CODIGO ANIMAL
        // =========================

        JLabel lblAnimal =
                new JLabel("Cod. Animal:");

        lblAnimal.setBounds(380, 65, 100, 25);
        panel.add(lblAnimal);

        txtCodigoAnimal = new JTextField();
        txtCodigoAnimal.setBounds(480, 65, 150, 25);
        panel.add(txtCodigoAnimal);


        // =========================
        // ESTADO
        // =========================

        JLabel lblEstado =
                new JLabel("Estado:");

        lblEstado.setBounds(380, 105, 80, 25);
        panel.add(lblEstado);

        cmbEstado =
                new JComboBox<>(
                        new String[]{
                            "Aprobada",
                            "Rechazada"
                        }
                );

        cmbEstado.setBounds(480, 105, 150, 25);
        panel.add(cmbEstado);


        // =========================
        // BOTONES
        // =========================

        JButton btnRegistrar =
                new JButton("Registrar");

        btnRegistrar.setBounds(660, 60, 130, 30);
        panel.add(btnRegistrar);


        JButton btnCambiarEstado =
                new JButton("Cambiar estado");

        btnCambiarEstado.setBounds(660, 100, 130, 30);
        panel.add(btnCambiarEstado);


        JButton btnPendientes =
                new JButton("Pendientes");

        btnPendientes.setBounds(660, 140, 130, 30);
        panel.add(btnPendientes);


        JButton btnHistorial =
                new JButton("Ver historial");

        btnHistorial.setBounds(660, 180, 130, 30);
        panel.add(btnHistorial);


        JButton btnLimpiar =
                new JButton("Limpiar");

        btnLimpiar.setBounds(660, 220, 130, 30);
        panel.add(btnLimpiar);


        JButton btnRegresar =
                new JButton("Regresar");

        btnRegresar.setBounds(660, 480, 130, 35);
        panel.add(btnRegresar);


        // =========================
        // TABLA
        // =========================

        // Define los nombres de las columnas
        String[] columnas = {
            "Codigo",
            "Adoptante",
            "Animal",
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
        scroll.setBounds(40, 290, 590, 225);


        // Agrega la tabla visible a la ventana
        panel.add(scroll);


        // =========================
        // ACCIONES
        // =========================

        btnRegistrar.addActionListener(e -> registrarSolicitud());

        btnCambiarEstado.addActionListener(e -> cambiarEstado());

        btnPendientes.addActionListener(e -> mostrarPendientes());

        btnHistorial.addActionListener(e -> actualizarTabla());

        btnLimpiar.addActionListener(e -> limpiarCampos());

        btnRegresar.addActionListener(e -> regresar());


        add(panel);
    }


    // =========================
    // REGISTRAR SOLICITUD
    // =========================

    private void registrarSolicitud() {

        String codigo =
                txtCodigo.getText().trim();

        String codigoAdoptante =
                txtCodigoAdoptante.getText().trim();

        String codigoAnimal =
                txtCodigoAnimal.getText().trim();


        if (codigo.isEmpty()
                || codigoAdoptante.isEmpty()
                || codigoAnimal.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe llenar todos los campos"
            );

            return;
        }


        // Busca al adoptante usando el codigo escrito
        Adoptante adoptante =
                gestionAdoptantes.buscarPorCodigo(
                        codigoAdoptante
                );


        if (adoptante == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "El adoptante no existe"
            );

            return;
        }


        // Busca al animal usando el codigo escrito
        Animal animal =
                gestionAnimales.buscarPorCodigo(
                        codigoAnimal
                );


        if (animal == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "El animal no existe"
            );

            return;
        }
        
        // El animal puede recibir solicitudes si esta disponible
        // o si ya tiene otras solicitudes en proceso
        if (!animal.getEstado().equalsIgnoreCase("Disponible")
                && !animal.getEstado().equalsIgnoreCase("En proceso")) {

            JOptionPane.showMessageDialog(
                    this,
                    "El animal no esta disponible para adopcion"
            );

            return;
        }


        // Evita solicitudes pendientes duplicadas
        if (gestion.existePendiente(codigoAdoptante, codigoAnimal)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ya existe una solicitud pendiente de este adoptante para este animal"
            );

            return;
        }


        Solicitud nuevaSolicitud =
                new Solicitud(
                        codigo,
                        adoptante,
                        animal
                );


        boolean registrada =
                gestion.registrarSolicitud(
                        nuevaSolicitud
                );


        if (registrada) {
            
            // Al registrar una solicitud el animal pasa a En proceso
            gestionAnimales.editarEstado(
                    animal.getCodigo(),
                    "En proceso"
            );

            // Guarda el nuevo estado del animal
            PersistenciaAnimales.guardarAnimales(
                    gestionAnimales
            );
            
            

            // La solicitud comienza como Pendiente desde su constructor
            PersistenciaSolicitudes.guardarSolicitudes(gestion);
            
            // Registra la nueva solicitud en la bitacora
            gestionBitacora.registrarAccion(
                    new Bitacora(
                            "Registrar solicitud",
                            "Se registro la solicitud "
                            + codigo
                            + " para el animal "
                            + codigoAnimal,
                            usuarioActual.getUsuario()
                    )
            );

            PersistenciaBitacora.guardarBitacora(
                    gestionBitacora
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Solicitud registrada correctamente"
            );

            actualizarTabla();
            limpiarCampos();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo registrar la solicitud"
            );
        }
    }


    // =========================
    // CAMBIAR ESTADO
    // =========================

    private void cambiarEstado() {

        String codigo =
                txtCodigo.getText().trim();

        String nuevoEstado =
                cmbEstado.getSelectedItem().toString();


        if (codigo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el codigo de la solicitud"
            );

            return;
        }


        // Busca la solicitud completa antes de modificarla
        Solicitud solicitud =
                gestion.buscarPorCodigo(codigo);


        if (solicitud == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Solicitud no encontrada"
            );

            return;
        }
        
        // Solo una solicitud pendiente puede aprobarse o rechazarse
        if (!solicitud.getEstado().equalsIgnoreCase("Pendiente")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Solo se pueden modificar solicitudes pendientes"
            );

            return;
        }


        // Si quiere aprobarla revisa que el animal no haya sido adoptado antes
        if (nuevoEstado.equalsIgnoreCase("Aprobada")) {

            Animal animal =
                    solicitud.getAnimal();

            // Rechaza las otras solicitudes del mismo animal
            gestion.rechazarOtrasPendientes(
                    animal.getCodigo(),
                    solicitud.getCodigo()
            );

            // Animal pasa a Adoptado
            gestionAnimales.editarEstado(
                    animal.getCodigo(),
                    "Adoptado"
            );

            // Libera su espacio
            gestionUbicaciones.liberarAnimal(animal);

            // Guarda animales
            PersistenciaAnimales.guardarAnimales(
                    gestionAnimales
            );

            // Guarda ubicaciones
            PersistenciaUbicaciones.guardarUbicaciones(
                    gestionUbicaciones
            );
        }


        boolean cambiado =
                gestion.cambiarEstado(
                        codigo,
                        nuevoEstado
                );


        if (cambiado) {

            // Si se aprueba la solicitud el animal pasa oficialmente a Adoptado
            if (nuevoEstado.equalsIgnoreCase("Aprobada")) {

                Animal animal =
                        solicitud.getAnimal();


                // Usa el mismo metodo que ya existe en GestionAnimales
                gestionAnimales.editarEstado(
                        animal.getCodigo(),
                        "Adoptado"
                );


                // Si estaba ubicado en el refugio libera su espacio
                gestionUbicaciones.liberarAnimal(animal);


                // Guarda todos los cambios relacionados con la adopcion
                PersistenciaAnimales.guardarAnimales(
                        gestionAnimales
                );

                PersistenciaUbicaciones.guardarUbicaciones(
                        gestionUbicaciones
                );
            }
            
            else if (nuevoEstado.equalsIgnoreCase("Rechazada")) {

            Animal animal =
                    solicitud.getAnimal();

            // Si ya no quedan solicitudes pendientes
            // el animal vuelve a estar Disponible
            if (gestion.contarPendientesPorAnimal(
                    animal.getCodigo()) == 0) {

                gestionAnimales.editarEstado(
                        animal.getCodigo(),
                        "Disponible"
                );

                PersistenciaAnimales.guardarAnimales(
                        gestionAnimales
                );
            }
        }


        // Guarda el nuevo estado de la solicitud
        PersistenciaSolicitudes.guardarSolicitudes(
                gestion
        );
            


            // Guarda el nuevo estado de la solicitud
            PersistenciaSolicitudes.guardarSolicitudes(
                    gestion
            );
            
            // Registra el nuevo estado de la solicitud
            gestionBitacora.registrarAccion(
                    new Bitacora(
                            "Cambiar estado solicitud",
                            "La solicitud "
                            + codigo
                            + " cambio a "
                            + nuevoEstado,
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

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo cambiar el estado"
            );
        }
    }


    // =========================
    // MOSTRAR PENDIENTES
    // =========================

    private void mostrarPendientes() {

        // Limpia primero la tabla
        modeloTabla.setRowCount(0);
        
        // Obtiene la solicitud 
        Solicitud[] solicitudes =
                gestion.getSolicitudes();

        int cantidad =
                gestion.getCantidadSolicitudes();


        for (int i = 0; i < cantidad; i++) {

            if (solicitudes[i].getEstado()
                    .equalsIgnoreCase("Pendiente")) {

                agregarSolicitudTabla(
                        solicitudes[i]
                );
            }
        }
    }


    // =========================
    // ACTUALIZAR TABLA
    // =========================

    private void actualizarTabla() {

        // Borra las filas anteriores para no duplicar datos
        modeloTabla.setRowCount(0);

        Solicitud[] solicitudes =
                gestion.getSolicitudes();

        int cantidad =
                gestion.getCantidadSolicitudes();


        for (int i = 0; i < cantidad; i++) {

            agregarSolicitudTabla(
                    solicitudes[i]
            );
        }
    }


    // =========================
    // AGREGAR FILA A LA TABLA
    // =========================

    private void agregarSolicitudTabla(
            Solicitud solicitud) {

        // Convierte la solicitud registrada en una fila
        Object[] fila = {
            solicitud.getCodigo(),
            solicitud.getAdoptante().getCodigo(),
            solicitud.getAnimal().getCodigo(),
            solicitud.getEstado()
        };


        // Agrega cada solicitud a la tabla
        modeloTabla.addRow(fila);
    }


    // =========================
    // LIMPIAR CAMPOS
    // =========================

    private void limpiarCampos() {

        txtCodigo.setText("");
        txtCodigoAdoptante.setText("");
        txtCodigoAnimal.setText("");

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