# 💧 Sabesp & Semam - Gestão e Auditoria de Obras na Rede de Esgoto (Santos)

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL" />
  <img src="https://img.shields.io/badge/Supabase-3ECF8E?style=for-the-badge&logo=supabase&logoColor=white" alt="Supabase" />
  <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white" alt="Hibernate" />
  <img src="https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white" alt="Figma" />
</p>

Um sistema web desenvolvido para modernizar e conferir transparência ao fluxo de requisições e obras na rede de esgoto do município de Santos. O projeto integra a população, a Secretaria de Meio Ambiente (Semam) e a Sabesp em uma única plataforma centralizada de auditoria, fiscalização e controle operacional.

A plataforma substitui o modelo burocrático e manual da ouvidoria tradicional por uma **esteira digital 24/7 auditável**, automatizando a triagem técnica por regiões de Santos e o controle de prazos legais de atendimento da concessionária.

---

## 📚 Documentação do Projeto

Para manter a documentação organizada e acessível, os detalhes técnicos foram organizados em módulos na pasta [`docs/`](docs/):

| Módulo | Descrição | Link de Acesso |
| :--- | :--- | :--- |
| **🏛️ Contexto e Legislação** | O problema real em Santos, Lei Complementar nº 852/2014, as 5 Zonas Regionais e ODS da ONU. | [Acessar Documento](docs/contexto-e-legislacao.md) |
| **🗄️ Arquitetura e Banco** | Ciclo de vida do chamado, matriz de status, modelo ER (Proto SQL), Soft Delete e estrutura Spring Boot. | [Acessar Documento](docs/arquitetura-e-banco.md) |
| **🔐 Regras de Negócio e API** | Perfis de acesso (RBAC), isolamento de dados, validações e catálogo de endpoints REST / Swagger. | [Acessar Documento](docs/regras-e-api.md) |
| **🎨 Design System e UI/UX** | Tipografia Montserrat, catálogo de logos SVG oficiais e link para o protótipo no Figma. | [Acessar Documento](docs/design-system.md) |

---

## 🛠️ Tecnologias Utilizadas

- **Backend:** Java 17+, Spring Boot (Data JPA, Web, Validation), Hibernate, RESTful API
- **Banco de Dados:** PostgreSQL hospedado via Supabase
- **Prototipagem:** Figma
- **Controle de Versão & Documentação:** Git, GitHub & Markdown

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
* **Java JDK 17** ou superior
* **Apache Maven 3.8+** (ou o wrapper `./mvnw`)
* Instância ativa do **PostgreSQL** ou projeto no **Supabase**

### Passo a Passo

1. **Clonar o repositório:**
   ```bash
   git clone https://github.com/CarvalhoNathan/sabesp.git
   cd sabesp
   ```

2. **Configurar as credenciais do banco:**
   No arquivo `src/main/resources/application.properties`, configure as credenciais do Supabase/PostgreSQL:
   ```properties
   spring.datasource.url=jdbc:postgresql://<HOST_SUPABASE>:5432/<DATABASE>
   spring.datasource.username=postgres
   spring.datasource.password=<SUA_SENHA_DO_SUPABASE>
   
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```

3. **Executar a aplicação:**
   ```bash
   mvn spring-boot:run
   ```
   A aplicação estará disponível em `http://localhost:8080` e o Swagger em `http://localhost:8080/swagger-ui.html`.

---

## 🗺️ Roadmap de Desenvolvimento

- [x] Levantamento de requisitos e fundamentação legal (Lei Complementar nº 852/2014)
- [x] Mapeamento das 5 Zonas Administrativas de Santos e esteira de atendimento
- [x] Modelagem do Banco de Dados (Proto SQL & Imutabilidade de Auditoria)
- [x] Coleta e padronização dos assets vetoriais oficiais (`assets/logo-prefeitura`)
- [ ] Prototipagem completa das interfaces no Figma (Munícipe, Gestão Semam e Operação Sabesp)
- [ ] Criação do esquema e tabelas relacionais no PostgreSQL (Supabase)
- [ ] Setup do projeto Spring Boot com dependências Maven
- [ ] Implementação das entidades JPA, DTOs e Repositories
- [ ] Desenvolvimento dos Services e Controllers REST com validações de negócio
- [ ] Implementação de autenticação e autorização por perfil (RBAC)
- [ ] Integração com serviço de armazenamento de arquivos em nuvem para fotos de evidência
- [ ] Cobertura de testes unitários e de integração

---

## 👨‍💻 Equipe de Desenvolvimento

Projeto acadêmico desenvolvido por:

- **Nathan Carvalho** — [GitHub](https://github.com/CarvalhoNathan)
- **Andrey Celestino** — [GitHub](https://github.com/DreyCelestino)

---

## 📄 Licença

Este projeto é desenvolvido para fins acadêmicos e educacionais. Consulte o arquivo de licença para maiores detalhes.
