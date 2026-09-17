package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.umg.programacion2.empleados.dao.EmpleadoDAO;


public class VentanaEliminar extends JFrame {

    private JTextField campoId;

    private EmpleadoDAO dao = new EmpleadoDAO();

    public VentanaEliminar() {

        setTitle("Eliminar empleado");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        crearFormulario();
    }

    private void crearFormulario() {

    	JPanel panel = new JPanel(new FlowLayout());

        JLabel etiquetaId = new JLabel("ID del empleado:");

        campoId = new JTextField(10);

        JButton botonEliminar = new JButton("Eliminar");
        botonEliminar.setPreferredSize(new Dimension(100, 35));

        botonEliminar.addActionListener(e -> eliminarEmpleado());

        panel.add(etiquetaId);
        panel.add(campoId);
        panel.add(botonEliminar);

        add(panel, BorderLayout.CENTER);
    }

    private void eliminarEmpleado() {

        String textoId = campoId.getText().trim();

        if (textoId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese el ID del empleado.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            int id = Integer.parseInt(textoId);

            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de eliminar al empleado con ID " + id + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirmacion != JOptionPane.YES_OPTION) {
                return;
            }

            boolean eliminado = dao.eliminar(id);

            if (eliminado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Empleado eliminado correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE
                );

                campoId.setText("");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se encontró un empleado con ese ID.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "El ID debe ser un número entero.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al eliminar empleado: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}