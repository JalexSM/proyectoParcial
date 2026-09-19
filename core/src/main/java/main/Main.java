package main;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import edu.umg.programacion2.empleados.dao.EmpleadoDAO;
import edu.umg.programacion2.empleados.modelo.Empleado;

public class Main {

	private static final Scanner teclado = new Scanner(System.in);
	private static final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

	public static void main(String[] args) {

		int opcion;

		do {

			System.out.println("\n=================================");
			System.out.println("   SISTEMA DE GESTION DE EMPLEADOS");
			System.out.println("=================================");
			System.out.println("1. Listar empleados");
			System.out.println("2. Registrar empleado");
			System.out.println("3. Editar empleado");
			System.out.println("4. Eliminar empleado");
			System.out.println("5. Salir");
			System.out.print("Elige una opcion: ");

			opcion = teclado.nextInt();
			teclado.nextLine();

			switch (opcion) {

			case 1:
				listarEmpleados();
				break;

			case 2:
				registrarEmpleado();
				break;

			case 3:
				editarEmpleado();
				break;

			case 4:
				eliminarEmpleado();
				break;

			case 5:
				System.out.println("Hasta luego.");
				break;

			default:
				System.out.println("Opcion invalida.");
			}

		} while (opcion != 5);

		teclado.close();
	}

	private static String leerTexto(String mensaje) {

	    String texto;

	    do {
	        System.out.print(mensaje);
	        texto = teclado.nextLine().trim();

	        if (texto.isEmpty()) {
	            System.out.println("El campo no puede estar vacío.");
	        } else if (!texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
	            System.out.println("Solo se permiten letras y espacios.");
	        }

	    } while (texto.isEmpty()
	            || !texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"));

	    return texto;
	}

	private static String leerNombre() {
		String nombre;

		do {
			System.out.print("Nombre: ");
			nombre = teclado.nextLine().trim();

			if (nombre.isEmpty()) {
				System.out.println("El nombre no puede estar vacío.");
			} else if (nombre.length() < 3) {
				System.out.println("El nombre debe tener al menos 3 caracteres.");
			} else if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
				System.out.println("El nombre solo puede contener letras y espacios.");
			}

		} while (nombre.isEmpty() || nombre.length() < 3 || !nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"));

		return nombre;
	}

	private static double leerSalario() {
		double salario;

		while (true) {
			try {
				System.out.print("Salario: ");
				salario = Double.parseDouble(teclado.nextLine());

				if (salario <= 0) {
					System.out.println("El salario debe ser mayor que 0.");
				} else {
					return salario;
				}

			} catch (NumberFormatException e) {
				System.out.println("Ingrese un salario válido. Ejemplo: 8500.50");
			}
		}
	}

	private static LocalDate leerFecha() {
		while (true) {
			try {
				System.out.print("Fecha de contratación (AAAA-MM-DD): ");
				return LocalDate.parse(teclado.nextLine());

			} catch (Exception e) {
				System.out.println("Fecha inválida. Use el formato AAAA-MM-DD.");
			}
		}
	}

	private static boolean leerActivo() {
		while (true) {
			System.out.print("¿Empleado activo? (s/n): ");
			String respuesta = teclado.nextLine().trim().toLowerCase();

			if (respuesta.equals("s")) {
				return true;
			}

			if (respuesta.equals("n")) {
				return false;
			}

			System.out.println("Debe ingresar solamente 's' o 'n'.");
		}
	}

	// 1. LISTADO DE EMPLEADOS
	private static void listarEmpleados() {

		try {

			List<Empleado> empleados = empleadoDAO.listarTodos();

			if (empleados.isEmpty()) {
				System.out.println("No hay empleados registrados.");
				return;
			}

			for (Empleado empleado : empleados) {
				System.out.println(empleado);
			}

		} catch (SQLException e) {

			System.out.println("Error al listar empleados: " + e.getMessage());
		}
	}

	// 2. REGISTRAR EMPLEADO
	private static void registrarEmpleado() {

		try {
			String nombre = leerNombre();
			String departamento = leerTexto("Departamento: ");
			double salario = leerSalario();
			LocalDate fecha = leerFecha();
			boolean activo = leerActivo();
			String tipoContrato= leerTexto("tipo");

			Empleado empleado = new Empleado(0, nombre, departamento, salario, fecha, activo,tipoContrato);

			int id = empleadoDAO.crear(empleado);

			System.out.println("Empleado registrado correctamente.");
			System.out.println("ID asignado: " + id);

		} catch (SQLException e) {
			System.out.println("Error al registrar empleado: " + e.getMessage());
		}
	}

	// 3. EDITAR EMPLEADO
	private static void editarEmpleado() {

		System.out.println("\n=== EDITAR EMPLEADO ===");

		System.out.print("ID del empleado: ");
		int id = teclado.nextInt();
		teclado.nextLine();

		System.out.print("Nuevo nombre: ");
		String nombre = teclado.nextLine();

		System.out.print("Nuevo departamento: ");
		String departamento = teclado.nextLine();

		System.out.print("Nuevo salario: ");
		double salario = teclado.nextDouble();
		teclado.nextLine();

		System.out.print("Nueva fecha de contratacion (AAAA-MM-DD): ");
		LocalDate fechaContratacion = LocalDate.parse(teclado.nextLine());

		System.out.print("¿Activo? (true/false): ");
		boolean activo = teclado.nextBoolean();
		teclado.nextLine();
		
		
		System.out.print("tipo contrato: ");
		String tipoContrato = teclado.nextLine();

		Empleado empleado = new Empleado(id, nombre, departamento, salario, fechaContratacion, activo,tipoContrato);

		try {

			boolean actualizado = empleadoDAO.actualizar(empleado);

			if (actualizado) {
				System.out.println("Empleado actualizado correctamente.");
			} else {
				System.out.println("No existe un empleado con ese ID.");
			}

		} catch (SQLException e) {

			System.out.println("Error al actualizar empleado: " + e.getMessage());
		}
	}

	// 4. ELIMINAR EMPLEADO
	private static void eliminarEmpleado() {

		System.out.println("\n=== ELIMINAR EMPLEADO ===");

		System.out.print("ID del empleado: ");
		int id = teclado.nextInt();
		teclado.nextLine();

		System.out.print("¿Seguro que deseas eliminar este empleado? (s/n): ");

		String confirmacion = teclado.nextLine();

		if (!confirmacion.equalsIgnoreCase("s")) {

			System.out.println("Eliminacion cancelada.");
			return;
		}

		try {

			boolean eliminado = empleadoDAO.eliminar(id);

			if (eliminado) {

				System.out.println("Empleado eliminado correctamente.");

			} else {

				System.out.println("No existe un empleado con ese ID.");
			}

		} catch (SQLException e) {

			System.out.println("Error al eliminar empleado: " + e.getMessage());
		}
	}
}