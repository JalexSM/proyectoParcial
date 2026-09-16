package ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {

        setTitle("Gestión de Empleados");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        crearMenu();
    }

    private void crearMenu() {

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton botonAgregar = new JButton("Agregar empleado");
        JButton botonListar = new JButton("Listar empleados");
        JButton botonActualizar = new JButton("Actualizar empleado");
        JButton botonEliminar = new JButton("Eliminar empleado");
        JButton botonSalir = new JButton("Salir");

        panel.add(botonAgregar);
        
        panel.add(botonListar);
        panel.add(botonActualizar);
        panel.add(botonEliminar);
        panel.add(botonSalir);

        add(panel);
        
        botonAgregar.addActionListener(e -> {

            VentanaAgregar ventanaAgregar = new VentanaAgregar();

            ventanaAgregar.setVisible(true);
        });
        
        botonListar.addActionListener(e -> {

            VentanaListar ventanaListar = new VentanaListar();

            ventanaListar.setVisible(true);
        });
        
        

        botonSalir.addActionListener(e -> {
            System.exit(0);
        });
    }
}