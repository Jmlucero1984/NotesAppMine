CREATE TABLE IF NOT EXISTS NOTAS (
    id serial PRIMARY KEY,
    titulo varchar(50) NOT NULL,
    categoria varchar(50) NOT NULL,
    cuerpo varchar(500) NOT null
    );

INSERT INTO NOTAS(titulo, categoria, cuerpo) values ('Docker2','Programafsdfcion','Estsdfso es el cuerpo de la nota');