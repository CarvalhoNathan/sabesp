# Santos Saneamento · SEMAM / Sabesp

Sistema Java Web com **Spring Boot 3 (Java 17)** para registro e acompanhamento de chamados de problemas na rede de esgoto de Santos.

## Perfis disponíveis (sem login nesta etapa)

- **Pessoa comum (`/chamados/novo`)**: registra endereço (com CEP automático via ViaCEP, autocomplete em Santos e GPS), tipo de problema, descrição e até 5 fotos.
- **Sabesp (`/sabesp/chamados`)**: visualiza chamados, aplica filtros, abre fotos em destaque, altera status e escreve comentários.
- **Prefeitura / SEMAM (`/prefeitura/chamados`)**: acompanha chamados e realiza filtros (somente leitura).

---

## Como rodar localmente (banco em memória H2)

1. No Eclipse, importe via **File > Import > Maven > Existing Maven Projects**.
2. Execute `SaneamentoApplication.java` como **Java Application**.
3. Acesse `http://localhost:8080`.

---

## Como conectar ao Supabase (para uso compartilhado)

### 1. Banco de Dados (PostgreSQL)
1. Crie um projeto no [Supabase](https://supabase.com/).
2. Abra o **SQL Editor** e execute o conteúdo de `supabase-schema.sql`.
3. Em **Project Settings > Database > Connection string**, selecione **Session pooler** (porta `5432`) e anote a URL JDBC, usuário e senha.

### 2. Armazenamento de Fotos (Supabase Storage)
1. No menu **Storage**, crie um bucket chamado `chamados-fotos` e marque como **Public** (para que as fotos apareçam nos painéis da Sabesp e Prefeitura).
2. Em **Project Settings > API**, copie a `Project URL` e a chave `service_role`.

### 3. Configurar variáveis de ambiente no Eclipse
1. Clique com o botão direito em `SaneamentoApplication.java` > **Run As > Run Configurations...**
2. Na aba **Environment**, adicione as variáveis listadas em `.env.example` (incluindo `SPRING_PROFILES_ACTIVE=supabase`).
3. Execute novamente o projeto.
