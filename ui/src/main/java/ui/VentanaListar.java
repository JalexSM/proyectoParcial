package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.umg.programacion2.empleados.dao.EmpleadoDAO;
import edu.umg.programacion2.empleados.modelo.Empleado;

public class VentanaListar extends JFrame {

    private JTable tablaEmpleados;

    private EmpleadoDAO dao = new EmpleadoDAO();

    public VentanaListar() {

        setTitle("Lista de empleados");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        crearTabla();
        crearBotonActualizar();
        cargarEmpleados();
    }

    private void crearTabla() {

        String[] columnas = {
            "ID",
            "Nombre",
            "Departamento",
            "Salario",
            "Fecha contratación",
            "Estado"
        };

        DefaultTableModel modelo =
                new DefaultTableModel(columnas, 0) {

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaEmpleados = new JTable(modelo);

        JScrollPane scroll =
                new JScrollPane(tablaEmpleados);

        add(scroll, BorderLayout.CENTER);
    }

    private void cargarEmpleados() {

        try {

            List<Empleado> empleados =
                    dao.listarTodos();

            DefaultTableModel modelo =
                    (DefaultTableModel) tablaEmpleados.getModel();

            modelo.setRowCount(0);

            for (Empleado empleado : empleados) {

                Object[] fila = {
                    empleado.getId(),
                    empleado.getNombre(),
                    empleado.getDepartamento(),
                    empleado.getSalario(),
                    empleado.getFechaContratacion(),
                    empleado.isActivo() ? "Activo" : "Inactivo"
                };

                modelo.addRow(fila);
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al listar empleados: "
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    private void crearBotonActualizar() {

        JButton botonActualizar = new JButton("Actualizar lista");
        botonActualizar.setPreferredSize(new Dimension(150, 50));

        botonActualizar.addActionListener(e -> {
            cargarEmpleados();
        });

        add(botonActualizar, BorderLayout.SOUTH);
    }









}






