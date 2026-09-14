package edu.umg.programacion2.empleados.modelo;

import java.time.LocalDate;

public class Empleado {
	private int id;
	private String nombre;
	private String departamento;
	private double salario;
	private LocalDate fechaContratacion;
	private boolean activo;
	
	public Empleado(int id, String nombre, String departamento, double salario, LocalDate fechaContratacion,
			boolean activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.departamento = departamento;
		this.salario = salario;
		this.fechaContratacion = fechaContratacion;
		this.activo = activo;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public LocalDate getFechaContratacion() {
		return fechaContratacion;
	}
	public void setFechaContratacion(LocalDate fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}


	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", departamento=" + departamento + ", salario=" + salario
				+ ", fechaContratacion=" + fechaContratacion + ", activo=" + activo + "]";
	}


	
	
	
}
