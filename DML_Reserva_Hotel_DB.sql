USE Reserva_Hotel_DB;
GO

-- Inserción en la tabla Usuarios
INSERT INTO Usuarios (Correo, Contrasena, Rol) VALUES
('admin@hotel.com', 'password123', 'Administrador'),
('recepcion@hotel.com', 'password123', 'Recepcionista'),
('gerente@hotel.com', 'password123', 'Gerente');

-- Inserción en la tabla Clientes
INSERT INTO Clientes (Nombre, Apellido, DNI, Email) VALUES
('Juan', 'Pérez', '12345678', 'juan.perez@example.com'),
('Ana', 'García', '87654321', 'ana.garcia@example.com');

-- Inserción en la tabla Habitaciones
INSERT INTO Habitaciones (Numero, Tipo, Precio, Estado) VALUES
(101, 'Simple', 100.00, 'Disponible'),
(102, 'Doble', 150.00, 'Ocupada'),
(103, 'Suite', 300.00, 'En limpieza');

-- Inserción en la tabla Reservas
INSERT INTO Reservas (ClienteID, HabitacionNumero, FechaInicio, FechaFin, Estado) VALUES
(1, 101, '2024-06-24', '2024-06-26', 'Activa'),
(2, 102, '2024-06-20', '2024-06-22', 'Finalizada');

