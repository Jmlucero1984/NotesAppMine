INSERT INTO USUARIOS(nombre,email,contraseña) values ('commonuser','user@user.com','$2a$10$oVORPhWa0Utu/tI1zeLsf.pAfzlx.pmcAOdP4Y/ZqGKaTwzpESIVO') ON CONFLICT (email) DO NOTHING;
INSERT INTO CATEGORIAS(usuario_id,titulo, color) values
(1,'General','#abdbe3'),
 (1,'Importante','#eab676'),
 (1,'Superado','#eeeee4'),
 (1,'Pendiente','#efcd85'),
 (1,'Delegado','#bfffd4');

INSERT INTO NOTAS(usuario_id,titulo,cuerpo,archivado) values
 (1,'Fullstack container','Lograr conectar los 3 componentes, front, back y db y tomar como punto de partida',true),
  (1,'Verficar Podcasts','Habia uno muy interesante sobre como planificar cosas implanificables',false),
    (1,'Corregir DB','SE encontraron problemas de integridad',false);
INSERT INTO NOTAS_CATEGORIAS(nota_id,categoria_id) values
 (1,2),
  (1,3),
   (1,5),
    (2,1),
     (2,4),
     (3,2);
