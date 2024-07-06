CREATE DATABASE Reserva_Hotel_DB;
GO

USE Reserva_Hotel_DB;
GO

CREATE TABLE Usuarios (
    UsuarioID INT PRIMARY KEY IDENTITY(1,1),
    Correo NVARCHAR(100) UNIQUE NOT NULL CHECK (Correo LIKE '%_@__%.__%'),
    Contrasena NVARCHAR(255) NOT NULL, -- La contraseña almacenada debe ser un hash
    Rol NVARCHAR(50) NOT NULL CHECK (Rol IN ('Administrador', 'Recepcionista', 'Gerente')) -- Roles para control de acceso
);

CREATE TABLE Clientes (
    ClienteID INT PRIMARY KEY IDENTITY(1,1),
    Nombre NVARCHAR(50),
    Apellido NVARCHAR(50),
    DNI NVARCHAR(15) UNIQUE,
    Email NVARCHAR(100)
);

CREATE TABLE Habitaciones (
    Numero INT PRIMARY KEY,
    Tipo NVARCHAR(50),
    Precio DECIMAL(10, 2),
    Estado NVARCHAR(50) CHECK (Estado IN ('Disponible', 'Ocupada', 'En limpieza'))
);

CREATE TABLE Reservas (
    ReservaID INT PRIMARY KEY IDENTITY(1,1),
    ClienteID INT,
    HabitacionNumero INT,
    FechaInicio DATE,
    FechaFin DATE,
    Estado NVARCHAR(50) CHECK (Estado IN ('Activa', 'Finalizada', 'Cancelada')),
    CONSTRAINT FK_Reservas_Clientes FOREIGN KEY (ClienteID) REFERENCES Clientes (ClienteID),
    CONSTRAINT FK_Reservas_Habitaciones FOREIGN KEY (HabitacionNumero) REFERENCES Habitaciones (Numero)
);

CREATE INDEX idx_habitacion_estado ON Habitaciones(Estado);
CREATE INDEX idx_reserva_estado ON Reservas(Estado);
CREATE INDEX idx_reserva_fechas ON Reservas(FechaInicio, FechaFin);
CREATE INDEX idx_usuario_correo ON Usuarios(Correo);

SELECT * FROM Usuarios