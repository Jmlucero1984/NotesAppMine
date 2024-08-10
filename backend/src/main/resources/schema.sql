DROP TABLE IF EXISTS USUARIOS CASCADE;
DROP TABLE IF EXISTS NOTAS CASCADE;
DROP TABLE IF EXISTS CATEGORIAS CASCADE;
DROP TABLE IF EXISTS notas_categorias CASCADE;



CREATE TABLE IF NOT EXISTS USUARIOS (
    id serial PRIMARY KEY,
    nombre varchar(50) NOT NULL,
    email varchar(50) NOT NULL UNIQUE,
    contraseña varchar(150) NOT NULL
    );

CREATE TABLE IF NOT EXISTS NOTAS (
    id serial PRIMARY KEY,
    usuario_id bigint NOT NULL,
    titulo varchar(50) NOT NULL,
    cuerpo varchar(500) NOT null,
    archivado BOOLEAN NOT null
    );

CREATE TABLE IF NOT EXISTS CATEGORIAS (
    id serial PRIMARY KEY,
    usuario_id bigint NOT NULL,
    titulo varchar(100) NOT NULL,
    color varchar(10) NOT NULL
    );

CREATE TABLE IF NOT EXISTS notas_categorias (
    nota_id bigint NOT NULL,
    categoria_id bigint NOT NULL,
    FOREIGN KEY (nota_id) REFERENCES notas(id),
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);