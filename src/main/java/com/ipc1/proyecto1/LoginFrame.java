package com.ipc1.proyecto1;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class LoginFrame extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;

    private GestionUsuarios gestionUsuarios;
    private GestionAnimales gestionAnimales;
    private GestionAdoptantes gestionAdoptantes;

    // =========================
    // CONSTRUCTOR
    // =========================

    public LoginFrame(GestionUsuarios gestionUsuarios, 
            GestionAnimales gestionAnimales,
            GestionAdoptantes gestionAdoptantes) {

        // Guarda el gestor que ya tiene los usuarios (y animales) cargados desde el archivo
        this.gestionUsuarios = gestionUsuarios;
        this.gestionAnimales = gestionAnimales;
        this.gestionAdoptantes = gestionAdoptantes;
        
        // Configuracion general de la ventana
        setTitle("Centro de Rescate Animal - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        iniciarComponentes();
    }


    // =========================
    // COMPONENTES DEL LOGIN
    // =========================

    private void iniciarComponentes() {

        JPanel panel = new JPanel();

        // null permite colocar cada componente usando coordenadas
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("INICIO DE SESION");
        lblTitulo.setBounds(135, 20, 150, 30);
        panel.add(lblTitulo);


        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(60, 70, 80, 25);
        panel.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 70, 170, 25);
        panel.add(txtUsuario);


        JLabel lblContrasena = new JLabel("Contrasena:");
        lblContrasena.setBounds(60, 110, 90, 25);
        panel.add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(150, 110, 170, 25);
        panel.add(txtContrasena);


        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(145, 155, 110, 30);
        panel.add(btnIngresar);


        // Cuando se presiona el boton ejecuta el metodo iniciarSesion
        btnIngresar.addActionListener(e -> iniciarSesion());


        add(panel);
    }


    // =========================
    // INICIAR SESION
    // =========================

    private void iniciarSesion() {

        String usuario = txtUsuario.getText().trim();

        // getPassword devuelve char[] y aqui lo convierte a String
        String contrasena =
                new String(txtContrasena.getPassword());


        if (usuario.isEmpty() || contrasena.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe llenar todos los campos"
            );

            return;
        }


        // Usa exactamente la misma logica que ya funcionaba en terminal
        Usuario encontrado =
                gestionUsuarios.iniciarSesion(
                        usuario,
                        contrasena
                );


        if (encontrado != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Bienvenido "
                    + encontrado.getUsuario()
                    + "\nRol: "
                    + encontrado.getRol()
            );


            VentanaPrincipal ventana =
        new VentanaPrincipal(
                encontrado,
                gestionAnimales,
                gestionAdoptantes
        );

            ventana.setVisible(true);
            // Abre la ventana principal usando el usuario que inicio sesion


            dispose();
            // Cierra el login de ususaros 
        }else {

            JOptionPane.showMessageDialog(
                    this,
                    "Usuario o contrasena incorrectos"
            );

            // Borra solamente la contraseña para volver a intentar
            txtContrasena.setText("");
        }
    }
    
    public static void main(String[] args) {

        GestionUsuarios gestionUsuarios = new GestionUsuarios();
        PersistenciaUsuarios.cargarUsuarios(gestionUsuarios);

        GestionAnimales gestionAnimales = new GestionAnimales();
        PersistenciaAnimales.cargarAnimales(gestionAnimales);
        
        GestionAdoptantes gestionAdoptantes = new GestionAdoptantes();
        PersistenciaAdoptantes.cargarAdoptantes(gestionAdoptantes);

        LoginFrame login =
        new LoginFrame(
                gestionUsuarios,
                gestionAnimales,
                gestionAdoptantes
        );

        login.setVisible(true);
    }
}