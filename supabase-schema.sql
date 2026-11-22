-- Script para PostgreSQL (Supabase) - Alinhado com as entidades JPA do Spring Boot
-- Sem obrigatoriedade (sem NOT NULL) por enquanto, para permitir testes sem login.

CREATE TABLE IF NOT EXISTS cliente (
    id_cliente BIGSERIAL PRIMARY KEY,
    cpf_cliente VARCHAR(11),
    nome_cliente VARCHAR(255),
    email_cliente VARCHAR(255),
    usuario_cliente VARCHAR(255),
    senha_cliente VARCHAR(255),
    excluido_cliente BOOLEAN DEFAULT FALSE,

    CONSTRAINT uq_usuario_cliente UNIQUE (usuario_cliente),
    CONSTRAINT uq_email_cliente UNIQUE (email_cliente)
);

CREATE TABLE IF NOT EXISTS funcionario (
    id_funcionario BIGSERIAL PRIMARY KEY,
    cpf_funcionario VARCHAR(11),
    nome_funcionario VARCHAR(255),
    email_funcionario VARCHAR(255),
    usuario_funcionario VARCHAR(255),
    senha_funcionario VARCHAR(255),
    orgao_funcionario VARCHAR(50), -- Ex: 'PREFEITURA' ou 'SABESP'
    tipo_funcionario VARCHAR(50),
    excluido_funcionario BOOLEAN DEFAULT FALSE,

    CONSTRAINT uq_usuario_funcionario UNIQUE (usuario_funcionario),
    CONSTRAINT uq_email_funcionario UNIQUE (email_funcionario)
);

-- Tabela principal usada por Chamado.java
CREATE TABLE IF NOT EXISTS chamados (
    id BIGSERIAL PRIMARY KEY,
    id_cliente BIGINT REFERENCES cliente(id_cliente),
    protocolo VARCHAR(32) UNIQUE,
    endereco VARCHAR(255),
    zona VARCHAR(40),
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    tipo_problema VARCHAR(50),
    descricao TEXT,
    status VARCHAR(40) DEFAULT 'PENDENTE',
    data_hora_ocorrencia TIMESTAMPTZ DEFAULT NOW(),
    criado_em TIMESTAMPTZ DEFAULT NOW(),

    CONSTRAINT ck_tipo_problema CHECK (
        tipo_problema IN (
            'VAZAMENTO_VIA',
            'REFLUXO_ESGOTO',
            'ROMPIMENTO_TUBULACAO',
            'TAMPA_BUEIRO_DANIFICADA'
        )
    ),
    CONSTRAINT ck_status_chamado CHECK (
        status IN (
            'PENDENTE',
            'APROVADO',
            'EM_OBRA',
            'CONCLUIDO',
            'REJEITADO'
        )
    )
);

CREATE INDEX IF NOT EXISTS idx_chamados_status ON chamados (status);
CREATE INDEX IF NOT EXISTS idx_chamados_zona ON chamados (zona);
CREATE INDEX IF NOT EXISTS idx_chamados_criado_em ON chamados (criado_em DESC);

-- Tabela de fotos usada por ChamadoFoto.java (URLs do Supabase Storage)
CREATE TABLE IF NOT EXISTS chamado_fotos (
    id BIGSERIAL PRIMARY KEY,
    chamado_id BIGINT REFERENCES chamados(id) ON DELETE CASCADE,
    nome_arquivo VARCHAR(255),
    url_storage VARCHAR(500),
    content_type VARCHAR(100),
    tamanho_bytes BIGINT
);

-- Tabela de comentários usada por ChamadoComentario.java (Sabesp)
CREATE TABLE IF NOT EXISTS chamado_comentarios (
    id BIGSERIAL PRIMARY KEY,
    chamado_id BIGINT REFERENCES chamados(id) ON DELETE CASCADE,
    autor_perfil VARCHAR(30),
    autor_nome VARCHAR(120),
    comentario TEXT,
    status_anterior VARCHAR(40),
    status_novo VARCHAR(40),
    criado_em TIMESTAMPTZ DEFAULT NOW()
);

-- Tabela de auditoria/logs (opcional para evolução futura)
CREATE TABLE IF NOT EXISTS logs (
    id_logs BIGSERIAL PRIMARY KEY,
    chamado_id BIGINT REFERENCES chamados(id) ON DELETE CASCADE,
    id_funcionario BIGINT REFERENCES funcionario(id_funcionario),
    operacao_logs VARCHAR(255),
    data_logs TIMESTAMPTZ DEFAULT NOW()
);
