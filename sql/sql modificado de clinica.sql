drop database myclinicplus;
create database myClinicPlus;
use myClinicPlus;

create table usuarios (
 id int primary key auto_increment,
 nombre varchar(20),
 apellido varchar(20),
 username varchar(20),
 pass varchar(254),
 id_rol int 
);
#password de este usuario 123456
#insert into usuarios (nombre,apellido,username,pass,id_rol) value ('administrador','administrador','admin','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',2);

create table rol (
	id_rol int primary key auto_increment,
    rol varchar(15)
);


#select * from rol;
alter table usuarios add constraint fk_usuario_rol foreign key (id_rol) references rol(id_rol) on update restrict on delete restrict; 

create table tipo_producto (
	id_tipo int primary key auto_increment,
    tipo_producto varchar(20),
    descripcion varchar(200)
);

create table proveedores(
	id_proveedor int primary key auto_increment,
	nombre_proveedor varchar(20),
	correo_proveedor varchar(20),
	telefono_proveedor varchar(20)
);
create table producto (
	id_producto int primary key auto_increment,
    nombre varchar(30),
    costo_por_unidad double,
    costo_publico double ,
    ganancia double,
    cantidad int ,
    id_tipo_producto int,
    id_proveedor int
);

alter table producto add constraint fk_producto_tipo_producto foreign key (id_tipo_producto) references tipo_producto(id_tipo) on update restrict on delete restrict;
alter table producto add constraint fk_producto_proveedor foreign key (id_proveedor) references proveedores(id_proveedor) on update restrict on delete restrict;

create table ventas (
	id_venta int primary key auto_increment,
    fecha_venta varchar(30),
    monto double,
    descuento int,
    id_usuario int 
);

alter table ventas add constraint fk_ventas_usuario foreign key (id_usuario) references usuarios(id)  on update restrict on delete restrict ;

create table detalle_venta(
	id_detalle int primary key auto_increment,
    id_venta int ,
    id_producto int,
    cantidad int,
    precioVenta double
);

alter table detalle_venta add constraint fk_venta_detalle foreign key (id_venta) references ventas (id_venta) on update restrict on delete restrict;
alter table detalle_venta add constraint fk_producto_detalle foreign key (id_producto) references producto (id_producto) on update restrict on delete restrict;

#creacion de rol
insert into rol (rol) value ('medico');
insert into rol (rol) value ('administrador');

#creacion de usuario
#password de este usuario 123456
insert into usuarios (nombre,apellido,username,pass,id_rol) value ('administrador','administrador','admin','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',2);

#select * from proveedores;
#creacion de proveedores
insert into proveedores (nombre_proveedor,correo_proveedor,telefono_proveedor) value ('proveedor 1','proveedor1@mail.com','2222222');
insert into proveedores (nombre_proveedor,correo_proveedor,telefono_proveedor) value ('proveedor 2','proveedor2@mail.com','2222222');
insert into proveedores (nombre_proveedor,correo_proveedor,telefono_proveedor) value ('proveedor 2','proveedor2@mail.com','2222222');

#select * from tipo_producto;

insert into tipo_producto (tipo_producto,descripcion) value ('categoria 1','descripcion de la categoria 1');
insert into tipo_producto (tipo_producto,descripcion) value ('categoria 2','descripcion de la categoria 2');
insert into tipo_producto (tipo_producto,descripcion) value ('categoria 3','descripcion de la categoria 3');
insert into tipo_producto (tipo_producto,descripcion) value ('categoria 4','descripcion de la categoria 4');

#creacion de productos
#select * from producto;
insert into producto (nombre,costo_por_unidad,costo_publico,ganancia,cantidad,id_tipo_producto,id_proveedor) value ('producto1',11.0,10.0,1,22,1,1);
insert into producto (nombre,costo_por_unidad,costo_publico,ganancia,cantidad,id_tipo_producto,id_proveedor) value ('producto2',11,10,22,1,2,2);
insert into producto (nombre,costo_por_unidad,costo_publico,ganancia,cantidad,id_tipo_producto,id_proveedor) value ('producto3',11,10,22,1,3,3);
insert into producto (nombre,costo_por_unidad,costo_publico,ganancia,cantidad,id_tipo_producto,id_proveedor) value ('producto4',11,10,22,1,1,1);
insert into producto (nombre,costo_por_unidad,costo_publico,ganancia,cantidad,id_tipo_producto,id_proveedor) value ('producto5',11,10,22,1,1,1);
insert into producto (nombre,costo_por_unidad,costo_publico,ganancia,cantidad,id_tipo_producto,id_proveedor) value ('producto6',11,10,22,1,1,1);
insert into producto (nombre,costo_por_unidad,costo_publico,ganancia,cantidad,id_tipo_producto,id_proveedor) value ('producto7',11,10,22,1,1,1);
insert into producto (nombre,costo_por_unidad,costo_publico,ganancia,cantidad,id_tipo_producto,id_proveedor) value ('producto8',11,10,22,1,1,1);
insert into producto (nombre,costo_por_unidad,costo_publico,ganancia,cantidad,id_tipo_producto,id_proveedor) value ('producto9',11,10,22,1,1,1);
insert into producto (nombre,costo_por_unidad,costo_publico,ganancia,cantidad,id_tipo_producto,id_proveedor) value ('producto10',11,10,22,1,1,1);
insert into producto (nombre,costo_por_unidad,costo_publico,ganancia,cantidad,id_tipo_producto,id_proveedor) value ('producto11',11,10,22,1,1,1);
insert into producto (nombre,costo_por_unidad,costo_publico,ganancia,cantidad,id_tipo_producto,id_proveedor) value ('producto12',11,10,22,1,1,1);
insert into producto (nombre,costo_por_unidad,costo_publico,ganancia,cantidad,id_tipo_producto,id_proveedor) value ('producto13',11,10,22,1,1,1);
insert into producto (nombre,costo_por_unidad,costo_publico,ganancia,cantidad,id_tipo_producto,id_proveedor) value ('producto14',11,10,22,1,1,1);
insert into producto (nombre,costo_por_unidad,costo_publico,ganancia,cantidad,id_tipo_producto,id_proveedor) value ('producto15',11,10,22,1,1,1);

select * from producto where nombre like '%pro%';
select * from ventas;
