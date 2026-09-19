
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.time.LocalDate;

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

public class VentanaActualizar extends JFrame {

    private JTextField campoId;
    private JTextField campoNombre;
    private JTextField campoDepartamento;
    private JTextField campoSalario;
    private JTextField campoFecha;
    private JCheckBox checkActivo;
    private JComboBox<String> comboTipoContrato;

    private EmpleadoDAO dao = new EmpleadoDAO();

    private Empleado empleadoActual;

    public VentanaActualizar() {

        setTitle("Actualizar empleado");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        crearFormulario();
    }

    private void crearFormulario() {

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(new Color(236, 240, 241));
        panelPrincipal.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setBackground(new Color(236, 240, 241));

        panelBusqueda.add(new JLabel("ID del empleado:"));

        campoId = new JTextField(8);
        panelBusqueda.add(campoId);

        JButton botonBuscar = new JButton("Buscar");
        panelBusqueda.add(botonBuscar);
        botonBuscar.setBackground(new Color(52, 152, 219));
        botonBuscar.setForeground(Color.WHITE);
        botonBuscar.setFont(new Font("Arial", Font.BOLD, 14));
        botonBuscar.setFocusPainted(false);

        panelPrincipal.add(panelBusqueda, BorderLayout.NORTH);

        JPanel panelCampos = new JPanel(new GridLayout(6, 2, 5, 5));
        panelCampos.setBackground(new Color(236, 240, 241));
        

        panelCampos.add(new JLabel("Nombre:"));
        campoNombre = new JTextField();
        panelCampos.add(campoNombre);

        panelCampos.add(new JLabel("Departamento:"));
        campoDepartamento = new JTextField();
        panelCampos.add(campoDepartamento);

        panelCampos.add(new JLabel("Salario:"));
        campoSalario = new JTextField();
        panelCampos.add(campoSalario);

        panelCampos.add(new JLabel("Fecha contratación:"));
        campoFecha = new JTextField();
        panelCampos.add(campoFecha);
        
        panelCampos.add(new JLabel("Estado:"));
        checkActivo = new JCheckBox("Empleado activo");
        panelCampos.add(checkActivo);
        
        panelCampos.add(new JLabel("Tipo de contrato:"));
        comboTipoContrato = new JComboBox<>(
            new String[] {
                "Temporal",
                "Permanente",
                "Por hora"
            }
        );
        panelCampos.add(comboTipoContrato);
        
        
        
        panelCampos.add(checkActivo);

        panelPrincipal.add(panelCampos, BorderLayout.CENTER);

        JButton botonActualizar = new JButton("Actualizar");
        botonActualizar.setBackground(new Color(46, 204, 113));
        botonActualizar.setForeground(Color.WHITE);
        botonActualizar.setFont(new Font("Arial", Font.BOLD, 14));
        botonActualizar.setFocusPainted(false);
        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.setBackground(new Color(231, 76, 60));
        botonCancelar.setForeground(Color.WHITE);
        botonCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        botonCancelar.setFocusPainted(false);
        

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(236, 240, 241));
        panelBotones.add(botonActualizar);
        panelBotones.add(botonCancelar);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        botonBuscar.addActionListener(e -> buscarEmpleado());

        botonActualizar.addActionListener(e -> actualizarEmpleado());

        botonCancelar.addActionListener(e -> dispose());

        add(panelPrincipal);

        bloquearCampos();
    }

    private void bloquearCampos() {

        campoNombre.setEnabled(false);
        campoDepartamento.setEnabled(false);
        campoSalario.setEnabled(false);
        campoFecha.setEnabled(false);
        checkActivo.setEnabled(false);
        comboTipoContrato.setEnabled(false);
    }

    private void habilitarCampos() {

        campoNombre.setEnabled(true);
        campoDepartamento.setEnabled(true);
        campoSalario.setEnabled(true);
        campoFecha.setEnabled(true);
        checkActivo.setEnabled(true);
        comboTipoContrato.setEnabled(true);
    }

    private void buscarEmpleado() {

        try {

            int id = Integer.parseInt(campoId.getText().trim());

            empleadoActual = dao.buscarPorId(id);

            if (empleadoActual == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "No se encontró el empleado.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            campoNombre.setText(empleadoActual.getNombre());
            campoDepartamento.setText(empleadoActual.getDepartamento());
            campoSalario.setText(String.valueOf(empleadoActual.getSalario()));
            campoFecha.setText(empleadoActual.getFechaContratacion().toString() );
            checkActivo.setSelected(empleadoActual.isActivo());
            habilitarCampos();

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
                    "Error al buscar empleado: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void actualizarEmpleado() {

        if (empleadoActual == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Primero busque un empleado.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            String nombre = campoNombre.getText().trim();
            String departamento = campoDepartamento.getText().trim();
            double salario = Double.parseDouble(
                    campoSalario.getText().trim()
            );
            LocalDate fecha = LocalDate.parse(
                    campoFecha.getText().trim()
            );

            empleadoActual.setNombre(nombre);
            empleadoActual.setDepartamento(departamento);
            empleadoActual.setSalario(salario);
            empleadoActual.setFechaContratacion(fecha);
            empleadoActual.setActivo(checkActivo.isSelected());

            boolean actualizado = dao.actualizar(empleadoActual);

            if (actualizado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Empleado actualizado correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE
                );

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo actualizar el empleado.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "El salario debe ser un número válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (java.time.format.DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "La fecha debe tener el formato AAAA-MM-DD.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al actualizar empleado: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}