CREATE DATABASE empleados_db;

USE empleados_db;


CREATE TABLE empleados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    departamento VARCHAR(100) NOT NULL,
    salario DECIMAL(10,2) NOT NULL,
    fecha_contratacion DATE NOT NULL,
    activo BOOLEAN NOT NULL
);
SHOW TABLES;
INSERT INTO empleados
(nombre, departamento, salario, fecha_contratacion, activo)
VALUES
('Ana Lucía Pérez', 'Sistemas', 8500.00, '2024-03-15', TRUE),
('Carlos Roberto Mux', 'Ventas', 6200.00, '2023-08-10', TRUE),
('Diana Sofía Cabrera', 'Contabilidad', 7100.00, '2022-01-20', FALSE);
SELECT * FROM empleados; 'Contabilidad', 7100.00, '2022-01-20', FALSE);