package com.ipc1.proyecto1;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class VentanaReportes extends JFrame {

    // Declaro estos arriba pa que toda la clase pueda usarlos
    private GestionAnimales gestionAnimales;
    private GestionAdoptantes gestionAdoptantes;
    private GestionSolicitudes gestionSolicitudes;
    private GestionRescate gestionRescate;
    private GestionUbicaciones gestionUbicaciones;

    private VentanaPrincipal ventanaPrincipal;


    // =========================
    // CONSTRUCTOR
    // =========================

    public VentanaReportes(
            GestionAnimales gestionAnimales,
            GestionAdoptantes gestionAdoptantes,
            GestionSolicitudes gestionSolicitudes,
            GestionRescate gestionRescate,
            GestionUbicaciones gestionUbicaciones,
            VentanaPrincipal ventanaPrincipal) {

        // Guarda los mismos gestores que ya tienen todos los datos cargados
        this.gestionAnimales = gestionAnimales;
        this.gestionAdoptantes = gestionAdoptantes;
        this.gestionSolicitudes = gestionSolicitudes;
        this.gestionRescate = gestionRescate;
        this.gestionUbicaciones = gestionUbicaciones;

        // Guarda la ventana principal para poder regresar despues
        this.ventanaPrincipal = ventanaPrincipal;

        setTitle("Reportes");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
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


        JLabel lblTitulo =
                new JLabel("GENERACION DE REPORTES");

        lblTitulo.setBounds(205, 25, 220, 30);
        panel.add(lblTitulo);


        // =========================
        // BOTONES
        // =========================

        JButton btnAnimales =
                new JButton("Reporte de animales");

        btnAnimales.setBounds(80, 90, 190, 40);
        panel.add(btnAnimales);


        JButton btnAdoptantes =
                new JButton("Reporte de adoptantes");

        btnAdoptantes.setBounds(320, 90, 190, 40);
        panel.add(btnAdoptantes);
        
        JButton btnAdopciones =
                new JButton("Reporte de adopciones");

        btnAdopciones.setBounds(80, 270, 190, 40);
        panel.add(btnAdopciones);


        JButton btnSolicitudes =
                new JButton("Reporte de solicitudes");

        btnSolicitudes.setBounds(80, 150, 190, 40);
        panel.add(btnSolicitudes);


        JButton btnRescates =
                new JButton("Reporte de rescates");

        btnRescates.setBounds(320, 150, 190, 40);
        panel.add(btnRescates);


        JButton btnUbicaciones =
                new JButton("Reporte de ubicaciones");

        btnUbicaciones.setBounds(80, 210, 190, 40);
        panel.add(btnUbicaciones);


        JButton btnTodos =
                new JButton("Generar todos");

        btnTodos.setBounds(320, 270, 190, 40);
        panel.add(btnTodos);


        JButton btnRegresar =
                new JButton("Regresar");

        btnRegresar.setBounds(200, 350, 190, 40);
        panel.add(btnRegresar);


        // =========================
        // ACCIONES
        // =========================

        btnAnimales.addActionListener(e -> generarAnimales());

        btnAdoptantes.addActionListener(e -> generarAdoptantes());
        
        btnAdopciones.addActionListener(e -> generarAdopciones());

        btnSolicitudes.addActionListener(e -> generarSolicitudes());

        btnRescates.addActionListener(e -> generarRescates());

        btnUbicaciones.addActionListener(e -> generarUbicaciones());

        btnTodos.addActionListener(e -> generarTodos());

        btnRegresar.addActionListener(e -> regresar());


        add(panel);
    }


    // =========================
    // REPORTE DE ANIMALES
    // =========================

    private void generarAnimales() {
        
        // Esto devuelve true si pudo generar el html
        if (GeneradorReportes.generarReporteAnimales(
                gestionAnimales)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Reporte de animales generado correctamente"
            );
        
            //No se pude generar
        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo generar el reporte"
            );
        }
    }


    // =========================
    // REPORTE DE ADOPTANTES
    // =========================

    private void generarAdoptantes() {

        if (GeneradorReportes.generarReporteAdoptantes(
                gestionAdoptantes)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Reporte de adoptantes generado correctamente"
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo generar el reporte"
            );
        }
    }


    // =========================
    // REPORTE DE SOLICITUDES
    // =========================

    private void generarSolicitudes() {

        if (GeneradorReportes.generarReporteSolicitudes(
                gestionSolicitudes)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Reporte de solicitudes generado correctamente"
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo generar el reporte"
            );
        }
    }


    // =========================
    // REPORTE DE RESCATES
    // =========================

    private void generarRescates() {

        if (GeneradorReportes.generarReporteRescates(
                gestionRescate)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Reporte de rescates generado correctamente"
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo generar el reporte"
            );
        }
    }


    // =========================
    // REPORTE DE UBICACIONES
    // =========================

    private void generarUbicaciones() {

        if (GeneradorReportes.generarReporteUbicaciones(
                gestionUbicaciones)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Reporte de ubicaciones generado correctamente"
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo generar el reporte"
            );
        }
    }


    // =========================
    // GENERAR TODOS
    // =========================

    private void generarTodos() {

        boolean animales =
                GeneradorReportes.generarReporteAnimales(
                        gestionAnimales
                );

        boolean adoptantes =
                GeneradorReportes.generarReporteAdoptantes(
                        gestionAdoptantes
                );

        boolean solicitudes =
                GeneradorReportes.generarReporteSolicitudes(
                        gestionSolicitudes
                );

        boolean rescates =
                GeneradorReportes.generarReporteRescates(
                        gestionRescate
                );

        boolean ubicaciones =
                GeneradorReportes.generarReporteUbicaciones(
                        gestionUbicaciones
                );
        
        boolean adopciones =
                GeneradorReportes.generarReporteAdopciones(
                        gestionSolicitudes
                );


        // Solo muestra correcto si todos los reportes pudieron generarse
        if (animales
            && adoptantes
            && solicitudes
            && rescates
            && ubicaciones
            && adopciones) {

            JOptionPane.showMessageDialog(
                    this,
                    "Todos los reportes fueron generados correctamente"
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Uno o mas reportes no pudieron generarse"
            );
        }
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
    
    // =========================
    // REPORTE DE ADOPCIONES
    // =========================

    private void generarAdopciones() {

        if (GeneradorReportes.generarReporteAdopciones(
                gestionSolicitudes)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Reporte de adopciones generado correctamente"
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo generar el reporte"
            );
        }
    }
}