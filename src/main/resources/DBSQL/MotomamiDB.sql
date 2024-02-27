create database if not exists motomamidb;
use motomamidb;
ALTER DATABASE motomamidb CHARACTER SET 'UTF8MB4';
create table if not exists mm_intcustomers (
	id INT primary key auto_increment,
	idProv VARCHAR (25),
	idExternal VARCHAR (10),
	contJson BLOB,
	creationDate DATETIME,
	lastUpdate DATETIME,
	createdBy VARCHAR (50),
	updatedBy VARCHAR (50),
	statusProcess VARCHAR(1),
	msgError VARCHAR(50),
	codeError VARCHAR(50),
	operation VARCHAR(50) default 'new'
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

ALTER TABLE mm_intcustomers MODIFY COLUMN msgError longtext;

create table if not exists mm_intvehicles(
	id INT primary key auto_increment,
	idProv VARCHAR (25),
	idExternal VARCHAR (10),
	contJson BLOB (65535),
	creationDate DATETIME,
	lastUpdate DATETIME,
	createdBy VARCHAR (50),
	updatedBy VARCHAR (50),
	statusProcess VARCHAR(1),
	msgError VARCHAR(50),
	codeError VARCHAR(50),
	operation VARCHAR(50) default 'new'
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

create table if not exists mm_intparts(
	id INT primary key auto_increment,
	idProv VARCHAR (25),
	idExternal VARCHAR (10),
	contJson BLOB (65535),
	creationDate DATETIME,
	lastUpdate DATETIME,
	createdBy VARCHAR (50),
	updatedBy VARCHAR (50),
	statusProcess VARCHAR(1),
	msgError VARCHAR(50),
	codeError VARCHAR(50),
	operation VARCHAR(50) default 'new'
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE if not exists mm_address (
    id INT AUTO_INCREMENT PRIMARY KEY,
    calle VARCHAR(255),
    numero INT,
    ciudad VARCHAR(50),
    codPostal VARCHAR(50)
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE if not exists mm_customer (
    dni VARCHAR(20) primary key,
    nombre VARCHAR(50),
    apellido1 VARCHAR(50),
    apellido2 VARCHAR(50),
    email VARCHAR(100),
    fecha_nacimiento DATE,
    telefono VARCHAR(20),
    sexo VARCHAR(10),
    direccionId INT,
    foreign key (direccionId) references mm_address(id)
);

CREATE TABLE mm_vehicle (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customerDni VARCHAR(20),
    idVehicleExternal int,
    numberPlate VARCHAR(20),
    vehicleType VARCHAR(50),
    brand VARCHAR(50),
    model VARCHAR(50),
    color VARCHAR(20),
    serialNumber VARCHAR(50),
    FOREIGN KEY (customerDni) REFERENCES mm_customer(dni)
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE mm_part (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vehicleId INT,
    datePartExternal DATE,
    descriptionPartExternal TEXT,
    codeDamageExternal VARCHAR(50),
    codeDamage VARCHAR(50),
    identityCode VARCHAR(20), 
    idExternal VARCHAR(50),
    FOREIGN KEY (vehicleId) REFERENCES mm_vehicle(id),
    FOREIGN KEY (identityCode) REFERENCES mm_customer(dni)
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE mm_invoice (
    id INT NOT NULL AUTO_INCREMENT,
    invoice_num BIGINT,
    invoice_date date,
    provider_name varchar(100),
    people_quantity int,
    unit_price DECIMAL(4, 2),
    tax int default 21,
    PRIMARY KEY (id)
)


-- insert into mm_intcustomers 
-- (idProv, contJson, creationDate, lastUpdate, createdBy, updatedBy) 
-- values 
-- ('bbva','{ }',current_timestamp(),current_timestamp(),'mm_app','mm_app');

