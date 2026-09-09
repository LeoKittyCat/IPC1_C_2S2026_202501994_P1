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

public class VentanaUbicaciones extends JFrame {

    // Declaro estos arriba pa que toda la clase pueda usarlos
    private GestionUbicaciones gestion;
    private GestionAnimales gestionAnimales;
    private VentanaPrincipal ventanaPrincipal;

    private JTextField txtCodigoAnimal;

    private JComboBox<String> cmbArea;
    private JComboBox<String> cmbEspacio;

    private JTable tabla; //Crea el componente visual
    private DefaultTableModel modeloTabla;


    // =========================
    // CONSTRUCTOR
    // =========================

    public VentanaUbicaciones(
            GestionUbicaciones gestion,
            GestionAnimales gestionAnimales,
            VentanaPrincipal ventanaPrincipal) {

        // Usa la misma matriz que ya tiene GestionUbicaciones
        this.gestion = gestion;

        // Sirve para buscar el animal antes de asignarlo
        this.gestionAnimales = gestionAnimales;

        // Guarda la ventana principal para poder regresar despues
        this.ventanaPrincipal = ventanaPrincipal;

        setTitle("Ubicaciones del Refugio");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        iniciarComponentes();

        // Muestra el estado actual de la matriz
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
                new JLabel("UBICACIONES DEL REFUGIO");

        lblTitulo.setBounds(330, 20, 220, 30);
        panel.add(lblTitulo);


        // =========================
        // CODIGO DEL ANIMAL
        // =========================

        JLabel lblCodigo =
                new JLabel("Codigo animal:");

        lblCodigo.setBounds(40, 80, 110, 25);
        panel.add(lblCodigo);


        txtCodigoAnimal =
                new JTextField();

        txtCodigoAnimal.setBounds(155, 80, 160, 25);
        panel.add(txtCodigoAnimal);


        // =========================
        // AREA
        // =========================

        JLabel lblArea =
                new JLabel("Area:");

        lblArea.setBounds(350, 80, 60, 25);
        panel.add(lblArea);


        cmbArea =
                new JComboBox<>(
                        new String[]{
                            "Perros",
                            "Gatos",
                            "Veterinaria"
                        }
                );

        cmbArea.setBounds(410, 80, 150, 25);
        panel.add(cmbArea);


        // =========================
        // ESPACIO
        // =========================

        JLabel lblEspacio =
                new JLabel("Espacio:");

        lblEspacio.setBounds(350, 120, 60, 25);
        panel.add(lblEspacio);


        cmbEspacio =
                new JComboBox<>(
                        new String[]{
                            "0",
                            "1",
                            "2",
                            "3",
                            "4"
                        }
                );

        cmbEspacio.setBounds(410, 120, 150, 25);
        panel.add(cmbEspacio);


        // =========================
        // BOTONES
        // =========================

        JButton btnAsignar =
                new JButton("Asignar");

        btnAsignar.setBounds(620, 70, 140, 30);
        panel.add(btnAsignar);


        JButton btnLiberar =
                new JButton("Liberar");

        btnLiberar.setBounds(620, 110, 140, 30);
        panel.add(btnLiberar);


        JButton btnConsultar =
                new JButton("Consultar");

        btnConsultar.setBounds(620, 150, 140, 30);
        panel.add(btnConsultar);


        JButton btnActualizar =
                new JButton("Actualizar");

        btnActualizar.setBounds(620, 190, 140, 30);
        panel.add(btnActualizar);


        JButton btnRegresar =
                new JButton("Regresar");

        btnRegresar.setBounds(620, 430, 140, 35);
        panel.add(btnRegresar);


        // =========================
        // TABLA DE LA MATRIZ
        // =========================

        // La primera columna muestra el area
        // Las otras cinco representan los espacios de esa fila
        String[] columnas = {
            "Area",
            "Espacio 0",
            "Espacio 1",
            "Espacio 2",
            "Espacio 3",
            "Espacio 4"
        };


        // Crea el modelo que va a manejar las filas y columnas
        modeloTabla =
                new DefaultTableModel(columnas, 0) {

            // Evita editar la matriz directamente desde la tabla
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
        scroll.setBounds(40, 260, 540, 205);


        // Agrega la tabla visible a la ventana
        panel.add(scroll);


        // =========================
        // ACCIONES
        // =========================

        btnAsignar.addActionListener(e -> asignarAnimal());

        btnLiberar.addActionListener(e -> liberarEspacio());

        btnConsultar.addActionListener(e -> consultarEspacio());

        btnActualizar.addActionListener(e -> actualizarTabla());

        btnRegresar.addActionListener(e -> regresar());


        add(panel);
    }


    // =========================
    // OBTENER FILA
    // =========================

    private int obtenerFilaSeleccionada() {

        // El indice del ComboBox coincide con la fila de la matriz
        // Perros = 0, Gatos = 1, Veterinaria = 2
        return cmbArea.getSelectedIndex();
    }


    // =========================
    // OBTENER COLUMNA
    // =========================

    private int obtenerColumnaSeleccionada() {

        // El indice tambien coincide con la columna elegida
        return cmbEspacio.getSelectedIndex();
    }


    // =========================
    // ASIGNAR ANIMAL
    // =========================

    private void asignarAnimal() {

        String codigo =
                txtCodigoAnimal.getText().trim();


        if (codigo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el codigo del animal"
            );

            return;
        }


        // Busca al animal dentro del arreglo de GestionAnimales
        Animal animal =
                gestionAnimales.buscarPorCodigo(codigo);


        if (animal == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Animal no encontrado"
            );

            return;
        }


        int fila =
                obtenerFilaSeleccionada();

        int columna =
                obtenerColumnaSeleccionada();


        // Revisa que el perro o gato pueda estar en el area elegida
        if (!gestion.areaValidaParaAnimal(
                fila,
                animal)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ese animal no puede ser asignado a esa area"
            );

            return;
        }


        // Evita que el mismo animal ocupe dos espacios
        if (gestion.animalYaAsignado(animal)) {

            JOptionPane.showMessageDialog(
                    this,
                    "El animal ya tiene un espacio asignado"
            );

            return;
        }


        // Evita ocupar una casilla que ya tiene otro animal
        if (!gestion.estaDisponible(
                fila,
                columna)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ese espacio ya esta ocupado"
            );

            return;
        }


        if (gestion.asignarAnimal(
                fila,
                columna,
                animal)) {
            
            PersistenciaUbicaciones.guardarUbicaciones(gestion);
            // Guarda la matriz despues de asignar el animal

            JOptionPane.showMessageDialog(
                    this,
                    "Animal asignado correctamente"
            );

            actualizarTabla();
            txtCodigoAnimal.setText("");
        }
    }


    // =========================
    // LIBERAR ESPACIO
    // =========================

    private void liberarEspacio() {
        
        

        int fila =
                obtenerFilaSeleccionada();

        int columna =
                obtenerColumnaSeleccionada();


        if (gestion.liberarEspacio(
                fila,
                columna)) {
            PersistenciaUbicaciones.guardarUbicaciones(gestion);
            // Actualiza ubicaciones.txt despues de liberar el espacio
    
            // liberarEspacio pone animal = null en esta posicion
            JOptionPane.showMessageDialog(
                    this,
                    "Espacio liberado correctamente"
            );

            actualizarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "El espacio ya estaba libre"
            );
        }
    }


    // =========================
    // CONSULTAR ESPACIO
    // =========================

    private void consultarEspacio() {

        int fila =
                obtenerFilaSeleccionada();

        int columna =
                obtenerColumnaSeleccionada();


        if (gestion.estaDisponible(
                fila,
                columna)) {

            JOptionPane.showMessageDialog(
                    this,
                    "El espacio esta disponible"
            );

        } else {

            // Obtiene directamente la casilla seleccionada
            EspacioRefugio espacio =
                    gestion.getEspacios()[fila][columna];


            JOptionPane.showMessageDialog(
                    this,
                    "Espacio ocupado por: "
                    + espacio.getAnimal().getCodigo()
                    + " - "
                    + espacio.getAnimal().getNombre()
            );
        }
    }


    // =========================
    // ACTUALIZAR TABLA
    // =========================

    private void actualizarTabla() {

        // Limpia la tabla antes de volver a dibujar la matriz
        modeloTabla.setRowCount(0);

        EspacioRefugio[][] espacios =
                gestion.getEspacios();


        // Recorre cada fila de la matriz
        for (int fila = 0;
                fila < espacios.length;
                fila++) {

            // De esta forma creamos la fila con 6 espacios
            Object[] datosFila =
                    new Object[6];


            // La primera columna contiene el nombre del area
            datosFila[0] =
                    gestion.obtenerNombreArea(fila);


            // Recorre los 5 espacios de esta area
            for (int columna = 0;
                    columna < espacios[fila].length;
                    columna++) {


                EspacioRefugio espacio =
                        espacios[fila][columna];


                if (espacio.estaDisponible()) {
                    //Esto llena las otras 5 columnas
                    datosFila[columna + 1] =
                            "Libre";

                } else {

                    // Si esta ocupado muestra el codigo del animal
                    // El +1 existe por que la posicion 0 ya esta ocupada
                    datosFila[columna + 1] =
                            espacio.getAnimal().getCodigo();
                }
            }


            // Convierte cada fila real de la matriz en una fila visual
            modeloTabla.addRow(datosFila);
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
}