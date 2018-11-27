--Limpieza
DELETE FROM CALIFICACIONENTITY;
ALTER TABLE CALIFICACIONENTITY ALTER COLUMN id RESTART WITH 1;

DELETE FROM PLATOENTITY;
ALTER TABLE PLATOENTITY ALTER COLUMN id RESTART WITH 1;

DELETE FROM PUNTOENTITY;
ALTER TABLE PUNTOENTITY ALTER COLUMN id RESTART WITH 1;

DELETE FROM TARJETAENTITY;
ALTER TABLE TARJETAENTITY ALTER COLUMN id RESTART WITH 1;

DELETE FROM DOMICILIOENTITY;
ALTER TABLE DOMICILIOENTITY ALTER COLUMN id RESTART WITH 1;

DELETE FROM RESERVAENTITY;
ALTER TABLE RESERVAENTITY ALTER COLUMN id RESTART WITH 1;

DELETE FROM MESAENTITY;
ALTER TABLE MESAENTITY ALTER COLUMN id RESTART WITH 1;

DELETE FROM SUCURSALENTITY_CLIENTEENTITY;

DELETE FROM CLIENTEENTITY_SUCURSALENTITY;

DELETE FROM CLIENTEENTITY;
ALTER TABLE CLIENTEENTITY ALTER COLUMN id RESTART WITH 1;

DELETE FROM SUCURSALENTITY;
ALTER TABLE SUCURSALENTITY ALTER COLUMN id RESTART WITH 1;


--Clientes
insert into CLIENTEENTITY (nombre, metodopago, identificacion) values ('Luis Alberto Rodriguez', 'Efectivo', 9568867532);
insert into CLIENTEENTITY (nombre, metodopago, identificacion) values ('Maria Paula Barreto', 'Efectivo', , 6605763793);
insert into CLIENTEENTITY (nombre, metodopago, identificacion) values ('Jorge Ortiz', 'Efectivo', 9968230489);
insert into CLIENTEENTITY (nombre, metodopago, identificacion) values ('Luz Marina Lopez', 'Tarjeta', 9440301878);
insert into CLIENTEENTITY (nombre, metodopago, identificacion) values ('Carlos Robles', 'Tarjeta', 9240605585);
insert into CLIENTEENTITY (nombre, metodopago, identificacion) values ('Pablo Hernandez', 'Tarjeta', 4002755452);
insert into CLIENTEENTITY (nombre, metodopago, identificacion) values ('Julian Jaramillo', 'Tarjeta', 9353472503);
insert into CLIENTEENTITY (nombre, metodopago, identificacion) values ('Alfonso Sanchez', 'Tarjeta', 7337206811);
insert into CLIENTEENTITY (nombre, metodopago, identificacion) values ('Isabel Garcia', 'Tarjeta', 4814206860);

--Sucursales
insert into SucursalEntity (direccion) values ('AV. Carrera 68 N° 72 - 43');
insert into SucursalEntity (direccion) values ('Calle 98 # 51 B - 91 Sector Buenavista');
insert into SucursalEntity (direccion) values ('Calle 22 Nº 6-28');


--Platos
insert into PLATOENTITY (nombre, precio, imagen) values ('Ajiaco', 17500,'https://t1.uc.ltmcdn.com/images/5/7/6/img_como_hacer_ajiaco_42675_600.jpg');
insert into PLATOENTITY (nombre, precio, imagen) values ('Bandeja Paisa', 20000,'https://imagesvc.timeincapp.com/v3/mm/image?url=https%3A%2F%2Fcdn-image.myrecipes.com%2Fsites%2Fdefault%2Ffiles%2Fstyles%2Fmedium_2x%2Fpublic%2Fbandejapaisa.jpg%3Fitok%3Dp6WzxIDI&w=1000&c=sc&poi=face&q=70');
insert into PLATOENTITY (nombre, precio, imagen ) values ('Empanaditas', 5000, 'https://antojandoando.com/wp-content/uploads/2015/08/empanadas-detalle-pq3.jpg');
insert into PLATOENTITY (nombre, precio, imagen ) values ('Mondongo', 12000, 'https://t2.rg.ltmcdn.com/es/images/6/5/4/img_mondongo_criollo_venezolano_34456_600.jpg');
insert into PLATOENTITY (nombre, precio, imagen ) values ('Arepa Santanderiana', 6000, 'https://antojandoando.com/wp-content/uploads/2015/10/arepas-plato-pq.jpg');
insert into PLATOENTITY (nombre, precio, imagen ) values ('Costillitas', 22000, 'https://cocinayrecetas-static.hola.com/cocinothai/files/2016/07/Costillas-de-cerdo-al-horno-Thai-Feath1.jpg');
insert into PLATOENTITY (nombre, precio, imagen) values ('Envueltos', 5000, 'https://static.vix.com/es/sites/default/files/styles/large/public/imj/elgranchef/R/Receta-de-envueltos-tipicos-2.jpg?itok=i_Z3WGHk');
insert into PLATOENTITY (nombre, precio, imagen) values ('Patacón', 7000,'http://haitiancooking.com/wp-content/uploads/2013/12/plantains-575x262.jpg');
insert into PLATOENTITY (nombre, precio, imagen ) values ('Mute', 17000,'http://buenomuybueno.com/wp-content/uploads/2017/05/mute2.jpg');
insert into PLATOENTITY (nombre, precio, imagen ) values ('Postre tres leches', 8000, 'https://www.elespectador.com/sites/default/files/cromos/image_main/jiji.jpg');

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
insert into RESERVAENTITY (hora, cantidadPersonas, sucursal_id, mesa_id, cliente_id) values ('2018-02-12 06:34:20', 5, 1, 1, 2);
insert into RESERVAENTITY (hora, cantidadPersonas, sucursal_id, mesa_id, cliente_id) values ('2018-08-08 20:34:25', 5, 1, 2, 1);
insert into RESERVAENTITY (hora, cantidadPersonas, sucursal_id, mesa_id, cliente_id) values ('2018-06-30 04:24:35', 2, 1, 3, 3);
insert into RESERVAENTITY (hora, cantidadPersonas, sucursal_id, mesa_id, cliente_id) values ('2018-03-10 13:40:39', 4, 2, 4, 5);
insert into RESERVAENTITY (hora, cantidadPersonas, sucursal_id, mesa_id, cliente_id) values ('2018-10-05 02:04:31', 3, 2, 5, 2);
insert into RESERVAENTITY (hora, cantidadPersonas, sucursal_id, mesa_id, cliente_id) values ('2018-09-02 22:02:17', 4, 2, 1, 1);
insert into RESERVAENTITY (hora, cantidadPersonas, sucursal_id, mesa_id, cliente_id) values ('2017-11-27 12:45:22', 2, 2, 2, 6);
insert into RESERVAENTITY (hora, cantidadPersonas, sucursal_id, mesa_id, cliente_id) values ('2017-11-07 13:37:43', 1, 3, 3, 7);
insert into RESERVAENTITY (hora, cantidadPersonas, sucursal_id, mesa_id, cliente_id) values ('2018-03-28 19:49:52', 4, 3, 4, 4);
insert into RESERVAENTITY (hora, cantidadPersonas, sucursal_id, mesa_id, cliente_id) values ('2018-04-16 12:11:09', 4, 3, 5, 4);

--Calificaciones
insert into CALIFICACIONENTITY (ID, PUNTAJE, CLIENTE_ID, SUCURSAL_ID) values (1, 5, 9, 1);
insert into CALIFICACIONENTITY (ID, PUNTAJE, CLIENTE_ID, SUCURSAL_ID) values (2, 4, 1, 2);
insert into CALIFICACIONENTITY (ID, PUNTAJE, CLIENTE_ID, SUCURSAL_ID) values (3, 4, 2, 2);
insert into CALIFICACIONENTITY (ID, PUNTAJE, CLIENTE_ID, SUCURSAL_ID) values (4, 2, 8, 3);
insert into CALIFICACIONENTITY (ID, PUNTAJE, CLIENTE_ID, SUCURSAL_ID) values (5, 5, 5, 1);
insert into CALIFICACIONENTITY (ID, PUNTAJE, CLIENTE_ID, SUCURSAL_ID) values (6, 3, 1, 3);
insert into CALIFICACIONENTITY (ID, PUNTAJE, CLIENTE_ID, SUCURSAL_ID) values (7, 4, 7, 1);
insert into CALIFICACIONENTITY (ID, PUNTAJE, CLIENTE_ID, SUCURSAL_ID) values (8, 5, 8, 3);
insert into CALIFICACIONENTITY (ID, PUNTAJE, CLIENTE_ID, SUCURSAL_ID) values (9, 2, 5, 2);
insert into CALIFICACIONENTITY (ID, PUNTAJE, CLIENTE_ID, SUCURSAL_ID) values (10, 4, 6, 2);