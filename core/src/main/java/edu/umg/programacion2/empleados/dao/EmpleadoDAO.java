package edu.umg.programacion2.empleados.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.umg.programacion2.empleados.modelo.Empleado;

public class EmpleadoDAO {

	private static final String URL = "jdbc:mariadb://localhost:3306/empleados_db";
	private static final String USUARIO = "root";
	private static final String PASSWORD = "J4viermadrid";

	public int crear(Empleado empleado) throws SQLException {

		String sql = "INSERT INTO empleados " + "(nombre, departamento, salario, fecha_contratacion, activo) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement statement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			statement.setString(1, empleado.getNombre());
			statement.setString(2, empleado.getDepartamento());
			statement.setDouble(3, empleado.getSalario());
			statement.setDate(4, java.sql.Date.valueOf(empleado.getFechaContratacion()));
			statement.setBoolean(5, empleado.isActivo());

			statement.executeUpdate();

			try (ResultSet claves = statement.getGeneratedKeys()) {

				if (claves.next()) {
					return claves.getInt(1);
				}

				return -1;
			}
		}
	}

	public List<Empleado> listarTodos() throws SQLException {

		List<Empleado> empleados = new ArrayList<>();

		String sql = "SELECT id, nombre, departamento, salario, fecha_contratacion, activo " + "FROM empleados "
				+ "ORDER BY id";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement statement = conexion.prepareStatement(sql);
				ResultSet resultado = statement.executeQuery()) {

			while (resultado.next()) {

				Empleado empleado = new Empleado(resultado.getInt("id"), resultado.getString("nombre"),
						resultado.getString("departamento"), resultado.getDouble("salario"),
						resultado.getDate("fecha_contratacion").toLocalDate(), resultado.getBoolean("activo"));

				empleados.add(empleado);
			}
		}

		return empleados;
	}

	public boolean actualizar(Empleado empleado) throws SQLException {

		String sql = "UPDATE empleados SET " + "nombre = ?, " + "departamento = ?, " + "salario = ?, "
				+ "fecha_contratacion = ?, " + "activo = ? " + "WHERE id = ?";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement statement = conexion.prepareStatement(sql)) {

			statement.setString(1, empleado.getNombre());
			statement.setString(2, empleado.getDepartamento());
			statement.setDouble(3, empleado.getSalario());
			statement.setDate(4, java.sql.Date.valueOf(empleado.getFechaContratacion()));
			statement.setBoolean(5, empleado.isActivo());
			statement.setInt(6, empleado.getId());

			return statement.executeUpdate() > 0;
		}
	}

	public boolean eliminar(int id) throws SQLException {

		String sql = "DELETE FROM empleados WHERE id = ?";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement statement = conexion.prepareStatement(sql)) {

			statement.setInt(1, id);

			return statement.executeUpdate() > 0;
		}
	}

}
