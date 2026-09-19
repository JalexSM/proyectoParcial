package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
		crearBotonRegresar();
		cargarEmpleados();
	}

	private void crearTabla() {

		String[] columnas = { "ID", "Nombre", "Departamento", "Salario", "Fecha contratación", "Estado","Tipo de Contrato" };

		DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {

			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};

		tablaEmpleados = new JTable(modelo);
		
		
		tablaEmpleados.setBackground(Color.WHITE);


		tablaEmpleados.setForeground(new Color(44, 62, 80));


		tablaEmpleados.setFont(new Font("Arial", Font.PLAIN, 14));

		tablaEmpleados.setRowHeight(30);

	
		tablaEmpleados.getTableHeader().setBackground(new Color(41, 128, 185));
		tablaEmpleados.getTableHeader().setForeground(Color.WHITE);
		tablaEmpleados.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

		JScrollPane scroll = new JScrollPane(tablaEmpleados);

		add(scroll, BorderLayout.CENTER);
	}

	private void cargarEmpleados() {

		try {

			List<Empleado> empleados = dao.listarTodos();

			DefaultTableModel modelo = (DefaultTableModel) tablaEmpleados.getModel();

			modelo.setRowCount(0);

			for (Empleado empleado : empleados) {

				Object[] fila = { empleado.getId(), empleado.getNombre(), empleado.getDepartamento(),
						empleado.getSalario(), empleado.getFechaContratacion(),
						empleado.isActivo() ? "Activo" : "Inactivo", empleado.getTipoContrato() };

				modelo.addRow(fila);
			}

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(this, "Error al listar empleados: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void crearBotonActualizar() {

		JButton botonActualizar = new JButton("Actualizar lista");
		botonActualizar.setPreferredSize(new Dimension(150, 50));

		botonActualizar.addActionListener(e -> {
			cargarEmpleados();
		});

		add(botonActualizar, BorderLayout.SOUTH);
		botonActualizar.setBackground(new Color(52, 152, 219));
		botonActualizar.setForeground(Color.WHITE);
		botonActualizar.setFont(new Font("Arial", Font.BOLD, 14));
		botonActualizar.setFocusPainted(false);
	}

	private void crearBotonRegresar() {

		JButton botonRegresar = new JButton("Regresar al menú");
		botonRegresar.setPreferredSize(new Dimension(100, 50));
		botonRegresar.addActionListener(e -> {

			dispose();

			VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
			ventanaPrincipal.setVisible(true);
		});

		add(botonRegresar, BorderLayout.NORTH);
		botonRegresar.setBackground(new Color(44, 62, 80));
		botonRegresar.setForeground(Color.WHITE);
		botonRegresar.setFont(new Font("Arial", Font.BOLD, 14));
		botonRegresar.setFocusPainted(false);
	}

}
