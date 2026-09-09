package com.ipc1.proyecto1;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaPrincipal extends JFrame {

    private Usuario usuarioActual;
    private GestionAnimales gestionAnimales;
    private GestionAdoptantes gestionAdoptantes;
    private GestionSolicitudes gestionSolicitudes;
    private GestionRescate gestionRescate;
    private GestionUbicaciones gestionUbicaciones;
    
    private JButton btnAnimales;
    private JButton btnAdoptantes;
    private JButton btnSolicitudes;
    private JButton btnRescates;
    private JButton btnUbicaciones;
    private JButton btnReportes;
    private JButton btnSalir;


    // =========================
    // CONSTRUCTOR
    // =========================

    
public VentanaPrincipal(
        Usuario usuarioActual, //Recibe el usuario encontrado
        GestionAnimales gestionAnimales,
        GestionAdoptantes gestionAdoptantes,
        GestionSolicitudes gestionSolicitudes,
        GestionRescate gestionRescate,
        GestionUbicaciones gestionUbicaciones) {

        this.usuarioActual = usuarioActual;
        this.gestionAnimales = gestionAnimales;
        this.gestionAdoptantes = gestionAdoptantes;
        this.gestionSolicitudes = gestionSolicitudes;
        this.gestionRescate = gestionRescate;
        this.gestionUbicaciones = gestionUbicaciones;
        // Guarda el usuario (y animal...y adoptantes y aja) que inicio sesion para poder mostrar su nombre y rol

        setTitle("Centro de Rescate Animal");

        setSize(650, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        // Hace que la ventana aparezca centrada

        setResizable(false);

        iniciarComponentes();
    }


    // =========================
    // COMPONENTES
    // =========================

    private void iniciarComponentes() {

        JPanel panel = new JPanel();

        // Permite colocar los componentes usando coordenadas
        panel.setLayout(null);


        JLabel lblTitulo = new JLabel("CENTRO DE RESCATE ANIMAL");

        lblTitulo.setBounds(225, 25, 220, 30);

        panel.add(lblTitulo);

        // Ya teniendo el usuario encontrado
        JLabel lblUsuario = new JLabel(
                "Usuario: "
                + usuarioActual.getUsuario()
                + " | Rol: "
                + usuarioActual.getRol()
        );

        lblUsuario.setBounds(30, 65, 350, 25);

        panel.add(lblUsuario);


        // =========================
        // BOTONES
        // =========================

        btnAnimales = new JButton("Animales");
        btnAnimales.setBounds(75, 120, 200, 45);
        panel.add(btnAnimales);


        btnAdoptantes = new JButton("Adoptantes");
        btnAdoptantes.setBounds(355, 120, 200, 45);
        panel.add(btnAdoptantes);


        btnSolicitudes = new JButton("Solicitudes");
        btnSolicitudes.setBounds(75, 190, 200, 45);
        panel.add(btnSolicitudes);


        btnRescates = new JButton("Rescates");
        btnRescates.setBounds(355, 190, 200, 45);
        panel.add(btnRescates);


        btnUbicaciones = new JButton("Ubicaciones");
        btnUbicaciones.setBounds(75, 260, 200, 45);
        panel.add(btnUbicaciones);


        btnReportes = new JButton("Reportes");
        btnReportes.setBounds(355, 260, 200, 45);
        panel.add(btnReportes);


        btnSalir = new JButton("Cerrar sesion");
        btnSalir.setBounds(215, 350, 200, 45);
        panel.add(btnSalir);


        // Por ahora solamente prueba que los botones reciben clic
        btnAnimales.addActionListener(e -> {

        // Oculta el menu principal mientras esta abierto el modulo de animales
        setVisible(false);

        VentanaAnimales ventanaAnimales =
                new VentanaAnimales(
                        gestionAnimales,
                        this
                );

        ventanaAnimales.setVisible(true);
    });


        btnAdoptantes.addActionListener(e -> {

            // Oculta el menu principal mientras esta abierto el modulo
            setVisible(false);

            VentanaAdoptantes ventanaAdoptantes =
                    new VentanaAdoptantes(
                            gestionAdoptantes,
                            this
                    );

            ventanaAdoptantes.setVisible(true);
        });


        btnSolicitudes.addActionListener(e -> {

            // Oculta el menu principal mientras esta abierto el modulo
            setVisible(false);

            VentanaSolicitudes ventanaSolicitudes =
                new VentanaSolicitudes(
                        gestionSolicitudes,
                        gestionAdoptantes,
                        gestionAnimales,
                        gestionUbicaciones,
                        this
                );

            ventanaSolicitudes.setVisible(true);
        });


        btnRescates.addActionListener(e -> {

            // Oculta el menu principal mientras esta abierto el modulo
            setVisible(false);

            VentanaRescates ventanaRescates =
                    new VentanaRescates(
                            gestionRescate,
                            this
                    );

            ventanaRescates.setVisible(true);
        });


        btnUbicaciones.addActionListener(e -> {

            // Oculta el menu principal mientras esta abierto el modulo
            setVisible(false);

            VentanaUbicaciones ventanaUbicaciones =
                    new VentanaUbicaciones(
                            gestionUbicaciones,
                            gestionAnimales,
                            this
                    );

            ventanaUbicaciones.setVisible(true);
        });


        btnReportes.addActionListener(e -> {

            // Oculta el menu principal mientras esta abierta la ventana de reportes
            setVisible(false);

            VentanaReportes ventanaReportes =
                    new VentanaReportes(
                            gestionAnimales,
                            gestionAdoptantes,
                            gestionSolicitudes,
                            gestionRescate,
                            gestionUbicaciones,
                            this
                    );

            ventanaReportes.setVisible(true);
        });


        btnSalir.addActionListener(e -> {

            // Cierra la ventana principal
            dispose();
        });


        add(panel);
    }
}