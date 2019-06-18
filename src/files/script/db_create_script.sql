CREATE DATABASE  IF NOT EXISTS `servicesAndServers`;
USE `servicesAndServers`;

CREATE TABLE service(
service_id INT AUTO_INCREMENT PRIMARY KEY,
service_name VARCHAR(100) NOT NULL,
url VARCHAR(100) NOT NULL,
port VARCHAR(6) NOT NULL,
description VARCHAR(14000)
);

CREATE TABLE server(
server_id INT AUTO_INCREMENT PRIMARY KEY,
server_name VARCHAR(100) NOT NULL,
op_sys VARCHAR(100),
cpu VARCHAR(100),
ram int,
ip VARCHAR(25),
db VARCHAR(1000),
file_db VARCHAR(100),
ifleet_web VARCHAR(100),
description VARCHAR(14000),
url VARCHAR(100),
external_ip boolean
);

CREATE TABLE reserved(
reserved_id INT AUTO_INCREMENT PRIMARY KEY,
port_number VARCHAR(6) NOT NULL 
);

CREATE TABLE server_service(
id INT AUTO_INCREMENT PRIMARY KEY,
server_id int NOT NULL,
service_id int NOT NULL,
FOREIGN KEY(`server_id`) REFERENCES `server` (`server_id`),
FOREIGN KEY(`service_id`) REFERENCES `service` (`service_id`)
);
