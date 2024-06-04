-- Crear la base de datos ACR_BD
CREATE DATABASE ACR_BD;

-- Usar la base de datos ACR_BD
USE ACR_BD;

-- Crear la tabla Cargos
CREATE TABLE Cargos (
    CargoID INT AUTO_INCREMENT PRIMARY KEY,
    Descripción VARCHAR(255) NOT NULL
);

-- Crear la tabla Trabajadores
CREATE TABLE Trabajadores (
    TrabajadorID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(35) NOT NULL,
    Apellidos VARCHAR(35) NOT NULL,
    Dirección VARCHAR(255) NOT NULL,
    Teléfono VARCHAR(20),
    Email VARCHAR(255),
    Cargo INT,
    FechaContratación DATE,
    FOREIGN KEY (Cargo) REFERENCES Cargos(CargoID)
);
