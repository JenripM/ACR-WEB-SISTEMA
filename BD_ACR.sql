-- Crear la base de datos ACR_BD
CREATE DATABASE ACR_BD;

-- Usar la base de datos ACR_BD
USE ACR_BD;

-- Crear la tabla Cargos
CREATE TABLE Cargos (
    CargoID INT AUTO_INCREMENT PRIMARY KEY,
    Descripción VARCHAR(255) NOT NULL
);

-- Crear la tabla Rol de usuarios
CREATE TABLE Rol_users (
    Rol_userID       INT AUTO_INCREMENT PRIMARY KEY,
    Descripción      VARCHAR(255) NOT NULL,
    clientes         TINYINT(1) not null,
    trabajadores     TINYINT(1) not null,
    casos            TINYINT(1) not null,
    tareas           TINYINT(1) not null,
    docum_corres     TINYINT(1) not null,
    accesos          TINYINT(1) not null
);

-- Crear la tabla Usuarios
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30),
    password VARCHAR(30),
    Rol_userID INT,
    FOREIGN KEY (Rol_userID) REFERENCES Rol_users(Rol_userID)
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


-- Crear la tabla documento
create table Documento (
    documentoid int auto_increment primary key,
    casoid int,
    tipo varchar(60) not null,
    fecha_subida date not null,
    ruta varchar(200) not null,
    foreign key (casoid) references casos(casoid)
);

-- Crear la tabla correspondencia
create table Correspondencia (
    correspondenciaid int auto_increment primary key,
    Casoid int,
    tipo varchar(60) not null,
    fecha date not null,
    descripcion varchar(255) not null,
    ruta varchar(200),
    foreign key (Casoid) references Casos(Casoid)
);


