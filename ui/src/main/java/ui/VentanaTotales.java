package ui;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.umg.programacion2.empleados.dao.EmpleadoDAO;
import edu.umg.programacion2.empleados.modelo.Empleado;
public class VentanaTotales extends JFrame {

    private EmpleadoDAO empleadoDAO;

    public VentanaTotales() {

        empleadoDAO = new EmpleadoDAO();

        setTitle("Totales de empleados");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 1));

        JLabel etiquetaTotal = new JLabel("Total de salarios: ");
        JLabel etiquetaPromedio = new JLabel("Promedio de salarios: ");
        JButton botonCalcular = new JButton("Calcular");

        panel.add(etiquetaTotal);
        panel.add(etiquetaPromedio);
        panel.add(botonCalcular);

        add(panel);

        botonCalcular.addActionListener(e -> {

            try {

                List<Empleado> empleados = empleadoDAO.listarTodos();

                if (empleados.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "No hay empleados registrados."
                    );
                    return;
                }

                double suma = 0;

                for (Empleado empleado : empleados) {
                    suma += empleado.getSalario();
                }

                double promedio = suma / empleados.size();

                etiquetaTotal.setText(
                        String.format("Total de salarios: Q%.2f", suma)
                );

                etiquetaPromedio.setText(
                        String.format("Promedio de salarios: Q%.2f", promedio)
                );

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error: " + ex.getMessage()
                );
            }
        });

        setVisible(true);
    }
}