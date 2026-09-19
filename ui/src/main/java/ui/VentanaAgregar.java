package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.umg.programacion2.empleados.dao.EmpleadoDAO;
import edu.umg.programacion2.empleados.modelo.Empleado;

public class VentanaAgregar extends JFrame {

	private JTextField campoNombre;
	private JComboBox<String> comboDepartamento;
	private JTextField campoSalario;
	private JTextField campoFecha;
	private JCheckBox checkActivo;
	private JComboBox<String> comboTipoContrato;

	private EmpleadoDAO dao = new EmpleadoDAO();

	public VentanaAgregar() {

		setTitle("Agregar empleado");
		setSize(450, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		crearFormulario();
	}

	private void crearFormulario() {

		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(7, 2, 10, 10));
		Color fondo = new Color(236, 240, 241);
		Color azul = new Color(52, 152, 219);
		Color rojo = new Color(231, 76, 60);
		Color texto = new Color(44, 62, 80);
		panel.setBackground(fondo);
		panel.setBorder(
			    javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20)
			);

		JLabel etiquetaNombre = new JLabel("Nombre:");
		campoNombre = new JTextField();

		JLabel etiquetaDepartamento = new JLabel("Departamento:");

		String[] departamentos = { "Sistemas", "Ventas", "Contabilidad", "Recursos Humanos", "Marketing", "Logística" };

		comboDepartamento = new JComboBox<>(departamentos);

		JLabel etiquetaSalario = new JLabel("Salario:");
		campoSalario = new JTextField();

		JLabel etiquetaFecha = new JLabel("Fecha contratación:");
		campoFecha = new JTextField();

		JLabel etiquetaActivo = new JLabel("Estado:");
		checkActivo = new JCheckBox("Activo");
		checkActivo.setSelected(true);
		
		
		JLabel lblTipoContrato = new JLabel("Tipo de contrato:");
		comboTipoContrato = new JComboBox<>(
		    new String[] {
		        "Temporal",
		        "Permanente",
		        "Por hora"
		    }
		);

		JButton botonGuardar = new JButton("Guardar");
		JButton botonCancelar = new JButton("Cancelar");
		

		panel.add(etiquetaNombre);
		panel.add(campoNombre);

		panel.add(etiquetaDepartamento);
		panel.add(comboDepartamento);

		panel.add(etiquetaSalario);
		panel.add(campoSalario);

		panel.add(etiquetaFecha);
		panel.add(campoFecha);

		panel.add(etiquetaActivo);
		panel.add(checkActivo);
		
		panel.add(lblTipoContrato);
		panel.add(comboTipoContrato);

		panel.add(botonGuardar);
		panel.add(botonCancelar);

		add(panel);

		botonGuardar.addActionListener(e -> guardarEmpleado());

		botonCancelar.addActionListener(e -> dispose());
		
	
		Font fuente = new Font("Arial", Font.BOLD, 14);


		botonGuardar.setFont(fuente);
		botonGuardar.setBackground(azul);
		botonGuardar.setForeground(Color.WHITE);
		botonGuardar.setFocusPainted(false);


		botonCancelar.setFont(fuente);
		botonCancelar.setBackground(rojo);
		botonCancelar.setForeground(Color.WHITE);
		botonCancelar.setFocusPainted(false);
		
		etiquetaNombre.setFont(fuente);
		etiquetaDepartamento.setFont(fuente);
		etiquetaSalario.setFont(fuente);
		etiquetaFecha.setFont(fuente);
		etiquetaActivo.setFont(fuente);

		etiquetaNombre.setForeground(texto);
		etiquetaDepartamento.setForeground(texto);
		etiquetaSalario.setForeground(texto);
		etiquetaFecha.setForeground(texto);
		etiquetaActivo.setForeground(texto);
		
		checkActivo.setBackground(fondo);
		checkActivo.setForeground(texto);
		
	}
	
	
	
	

	private void guardarEmpleado() {

		try {

			String nombre = campoNombre.getText().trim();

			String departamento = (String) comboDepartamento.getSelectedItem();

			String salarioTexto = campoSalario.getText().trim();

			String fechaTexto = campoFecha.getText().trim();

			boolean activo = checkActivo.isSelected();
			
			String tipoContrato = (String) comboTipoContrato.getSelectedItem();

			if (nombre.isEmpty()) {

				JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.");

				return;
			}

			if (salarioTexto.isEmpty()) {

				JOptionPane.showMessageDialog(this, "Debe ingresar el salario.");

				return;
			}

			if (fechaTexto.isEmpty()) {

				JOptionPane.showMessageDialog(this, "Debe ingresar la fecha de contratación.");

				return;
			}

			double salario = Double.parseDouble(salarioTexto);

			if (salario <= 0) {
				JOptionPane.showMessageDialog(this, "El salario debe ser mayor que cero.");
				return;
			}

			LocalDate fechaContratacion = LocalDate.parse(fechaTexto);

			if (fechaContratacion.isAfter(LocalDate.now())) {

				JOptionPane.showMessageDialog(this, "La fecha no puede ser futura.");

				return;
			}

			Empleado empleado = new Empleado(nombre, departamento, salario, fechaContratacion, activo, tipoContrato);

			dao.crear(empleado);

			JOptionPane.showMessageDialog(this, "Empleado agregado correctamente.");

			limpiarCampos();

		} catch (NumberFormatException e) {

			JOptionPane.showMessageDialog(this, "El salario debe ser un número válido.");

		} catch (DateTimeParseException e) {

			JOptionPane.showMessageDialog(this, "La fecha debe tener el formato: AAAA-MM-DD.");

		} catch (Exception e) {

			JOptionPane.showMessageDialog(this, "Error al guardar el empleado: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarCampos() {

		campoNombre.setText("");
		campoSalario.setText("");
		campoFecha.setText("");
		comboDepartamento.setSelectedIndex(0);
		checkActivo.setSelected(true);
	}
}