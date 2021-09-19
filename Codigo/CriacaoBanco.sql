-- Database: restaurante

-- DROP DATABASE restaurante;

CREATE DATABASE restaurante
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- Table: public.Movimentacao

-- DROP TABLE public."Movimentacao";

CREATE TABLE IF NOT EXISTS public."Movimentacao"
(
    "Id_movimenta" smallint NOT NULL,
    "Valor" double precision NOT NULL,
    id_usuario smallint,
    CONSTRAINT "Movimentacao_pkey" PRIMARY KEY ("Id_movimenta"),
    CONSTRAINT id_usuario FOREIGN KEY (id_usuario)
        REFERENCES public."Usuario" ("Id_usuario") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public."Movimentacao"
    OWNER to postgres;

-- Table: public.Ordem

-- DROP TABLE public."Ordem";

CREATE TABLE IF NOT EXISTS public."Ordem"
(
    "Id_ordem" smallint NOT NULL,
    "Entrada" "char" NOT NULL,
    "Saida" "char" NOT NULL,
    "Id_movimenta" smallint,
    CONSTRAINT "Ordem_pkey" PRIMARY KEY ("Id_ordem"),
    CONSTRAINT "Id_movimenta" FOREIGN KEY ("Id_movimenta")
        REFERENCES public."Movimentacao" ("Id_movimenta") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public."Ordem"
    OWNER to postgres;

-- Table: public.Produto

-- DROP TABLE public."Produto";

CREATE TABLE IF NOT EXISTS public."Produto"
(
    "Id_produto" smallint NOT NULL,
    "Nome_produto" "char" NOT NULL,
    "Qtd" bigint NOT NULL,
    id_movimentacao smallint,
    CONSTRAINT "Produto_pkey" PRIMARY KEY ("Id_produto"),
    CONSTRAINT id_movimentacao FOREIGN KEY (id_movimentacao)
        REFERENCES public."Movimentacao" ("Id_movimenta") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public."Produto"
    OWNER to postgres;

-- Table: public.Usuario

-- DROP TABLE public."Usuario";

CREATE TABLE IF NOT EXISTS public."Usuario"
(
    "Email" "char" NOT NULL,
    "Funcao" "char" NOT NULL,
    "Id_usuario" smallint NOT NULL,
    "Senha" "char" NOT NULL,
    "Nome" "char" NOT NULL,
    CONSTRAINT "Usuario_pkey" PRIMARY KEY ("Id_usuario")
)

TABLESPACE pg_default;

ALTER TABLE public."Usuario"
    OWNER to postgres;