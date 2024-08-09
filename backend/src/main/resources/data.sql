INSERT INTO USUARIOS(nombre,email,contraseña) values ('commonuser','user@user.com','$2a$10$oVORPhWa0Utu/tI1zeLsf.pAfzlx.pmcAOdP4Y/ZqGKaTwzpESIVO');
INSERT INTO CATEGORIAS(usuario_id,titulo) values (1,'General'), (1,'Importante'),(1,'Superado');
INSERT INTO NOTAS(usuario_id,titulo,categoria_id,cuerpo,archivado) values
 (1,'Fullstack container',3,'Lograr conectar los 3 componentes, front, back y db y tomar como punto de partida',true),
 (1,'Establecer la ORM',3,'Definir las entidades en Spring y que al menos se pueda insertar un registro',true),
 (1,'Verficar CORS',2,'Para este mínimo entregable verificar que no se bloqueen las solicitudes',true),
 (1,'Dar estilo general',1,'Que quede visualmente agradable y sencillo de operar',false),
 (1,'Hacer el readme',2,'No te olvides de hacer el readme!!!',false);