--Clientes
insert into CLIENTEENTITY (nombre, metodopago) values ('Luis Alberto Rodriguez', 'Efectivo');
insert into CLIENTEENTITY (nombre, metodopago) values ('Maria Paula Barreto', 'Efectivo');
insert into CLIENTEENTITY (nombre, metodopago) values ('Jorge Ortiz', 'Efectivo');
insert into CLIENTEENTITY (nombre, metodopago) values ('Luz Marina Lopez', 'Tarjeta');
insert into CLIENTEENTITY (nombre, metodopago) values ('Carlos Robles', 'Tarjeta');
insert into CLIENTEENTITY (nombre, metodopago) values ('Pablo Hernandez', 'Tarjeta');
insert into CLIENTEENTITY (nombre, metodopago) values ('Julian Jaramillo', 'Tarjeta');
insert into CLIENTEENTITY (nombre, metodopago) values ('Alfonso Sanchez', 'Tarjeta');
insert into CLIENTEENTITY (nombre, metodopago) values ('Isabel Garcia', 'Tarjeta');

--Sucursales
insert into SucursalEntity (direccion) values ('AV. Carrera 68 N° 72 - 43');
insert into SucursalEntity (direccion) values ('Calle 98 # 51 B - 91 Sector Buenavista');
insert into SucursalEntity (direccion) values ('Calle 22 Nº 6-28');


--Platos
insert into PLATOENTITY (nombre, precio) values ('Ajiaco', 17500);
insert into PLATOENTITY (nombre, precio) values ('Bandeja Paisa', 20000);
insert into PLATOENTITY (nombre, precio) values ('Empanaditas', 5000);
insert into PLATOENTITY (nombre, precio) values ('Mondongo', 12000);
insert into PLATOENTITY (nombre, precio) values ('Arepa Santanderiana', 6000);
insert into PLATOENTITY (nombre, precio) values ('Costillitas', 22000);
insert into PLATOENTITY (nombre, precio) values ('Embueltos', 5000);
insert into PLATOENTITY (nombre, precio) values ('Patacón', 7000);
insert into PLATOENTITY (nombre, precio) values ('Mute', 17000);
insert into PLATOENTITY (nombre, precio) values ('Postre tres leches', 8000);

--Mesas
insert into MesaEntity (numero, sucursal_id) values (199, 1);
insert into MesaEntity (numero, sucursal_id) values (280, 3);
insert into MesaEntity (numero, sucursal_id) values (289, 2);
insert into MesaEntity (numero, sucursal_id) values (180, 3);
insert into MesaEntity (numero, sucursal_id) values (206, 3);
insert into MesaEntity (numero, sucursal_id) values (156, 1);
insert into MesaEntity (numero, sucursal_id) values (232, 3);
insert into MesaEntity (numero, sucursal_id) values (148, 2);
insert into MesaEntity (numero, sucursal_id) values (175, 3);
insert into MesaEntity (numero, sucursal_id) values (202, 2);
insert into MesaEntity (numero, sucursal_id) values (224, 2);
insert into MesaEntity (numero, sucursal_id) values (220, 3);
insert into MesaEntity (numero, sucursal_id) values (292, 3);
insert into MesaEntity (numero, sucursal_id) values (105, 3);
insert into MesaEntity (numero, sucursal_id) values (219, 3);
insert into MesaEntity (numero, sucursal_id) values (120, 1);
insert into MesaEntity (numero, sucursal_id) values (267, 1);
insert into MesaEntity (numero, sucursal_id) values (136, 3);
insert into MesaEntity (numero, sucursal_id) values (108, 2);
insert into MesaEntity (numero, sucursal_id) values (164, 2);
insert into MesaEntity (numero, sucursal_id) values (128, 3);
insert into MesaEntity (numero, sucursal_id) values (273, 3);
insert into MesaEntity (numero, sucursal_id) values (203, 1);
insert into MesaEntity (numero, sucursal_id) values (146, 3);
insert into MesaEntity (numero, sucursal_id) values (152, 2);
insert into MesaEntity (numero, sucursal_id) values (145, 3);
insert into MesaEntity (numero, sucursal_id) values (113, 1);
insert into MesaEntity (numero, sucursal_id) values (171, 3);
insert into MesaEntity (numero, sucursal_id) values (290, 2);
insert into MesaEntity (numero, sucursal_id) values (192, 2);

--Reservas
insert into RESERVAENTITY (hora, cantidadPersonas) values ('2018-02-12 06:34:20', 5);
insert into RESERVAENTITY (hora, cantidadPersonas) values ('2018-08-08 20:34:25', 5);
insert into RESERVAENTITY (hora, cantidadPersonas) values ('2018-06-30 04:24:35', 2);
insert into RESERVAENTITY (hora, cantidadPersonas) values ('2018-03-10 13:40:39', 4);
insert into RESERVAENTITY (hora, cantidadPersonas) values ('2018-10-05 02:04:31', 3);
insert into RESERVAENTITY (hora, cantidadPersonas) values ('2018-09-02 22:02:17', 4);
insert into RESERVAENTITY (hora, cantidadPersonas) values ('2017-11-27 12:45:22', 2);
insert into RESERVAENTITY (hora, cantidadPersonas) values ('2017-11-07 13:37:43', 1);
insert into RESERVAENTITY (hora, cantidadPersonas) values ('2018-03-28 19:49:52', 4);
insert into RESERVAENTITY (hora, cantidadPersonas) values ('2018-04-16 12:11:09', 4);
