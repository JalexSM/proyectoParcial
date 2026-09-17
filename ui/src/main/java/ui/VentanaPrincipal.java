package ui;

import java.awt.Color;
import java.awt.Font;
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
    	
    	Color azul = new Color(52, 152, 219);
    	Color azulOscuro = new Color(41, 128, 185);
    	Color rojo = new Color(231, 76, 60);

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBackground(new Color(236, 240, 241));
        
        

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
       
        
        botonAgregar.setForeground(Color.WHITE);
        botonListar.setForeground(Color.WHITE);
        botonActualizar.setForeground(Color.WHITE);
        botonEliminar.setForeground(Color.WHITE);
        botonSalir.setForeground(Color.WHITE);

        botonAgregar.setBackground(azul);
        botonListar.setBackground(azul);
        botonActualizar.setBackground(azulOscuro);
        botonEliminar.setBackground(rojo);
        botonSalir.setBackground(Color.DARK_GRAY);
        
        Font fuente = new Font("Arial", Font.BOLD, 15);

        botonAgregar.setFont(fuente);
        botonListar.setFont(fuente);
        botonActualizar.setFont(fuente);
        botonEliminar.setFont(fuente);
        botonSalir.setFont(fuente);
        
        botonAgregar.setFocusPainted(false);
        botonListar.setFocusPainted(false);
        botonActualizar.setFocusPainted(false);
        botonEliminar.setFocusPainted(false);
        botonSalir.setFocusPainted(false);
        
        botonAgregar.addActionListener(e -> {

            VentanaAgregar ventanaAgregar = new VentanaAgregar();

            ventanaAgregar.setVisible(true);
        });
        
        botonListar.addActionListener(e -> {

            VentanaListar ventanaListar = new VentanaListar();

            ventanaListar.setVisible(true);
        });
        
        botonActualizar.addActionListener(e -> {

            VentanaActualizar ventanaActualizar = new VentanaActualizar();

            ventanaActualizar.setVisible(true);
        });
        
        
        
        
        botonEliminar.addActionListener(e -> {
        	
        	VentanaEliminar ventanaEliminar = new VentanaEliminar();
        	
        	ventanaEliminar.setVisible(true);
        	
        });
        
        

        botonSalir.addActionListener(e -> {
            System.exit(0);
        });
    }
}