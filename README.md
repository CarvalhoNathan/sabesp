# 💧 Sabesp & Prefeitura de Santos
### Gestão inteligente e auditoria pública na rede de esgoto municipal

<p align="center">
  <img src="https://img.shields.io/badge/Java_17-ED8B00?style=flat-square&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/Spring_Boot_3-6DB33F?style=flat-square&logo=spring-boot&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=flat-square&logo=postgresql&logoColor=white" alt="PostgreSQL" />
  <img src="https://img.shields.io/badge/Supabase-3ECF8E?style=flat-square&logo=supabase&logoColor=white" alt="Supabase" />
  <img src="https://img.shields.io/badge/Figma-F24E1E?style=flat-square&logo=figma&logoColor=white" alt="Figma" />
  <img src="https://img.shields.io/badge/Licen%C3%A7a-MIT-blue?style=flat-square" alt="Licença MIT" />
</p>

<p align="center">
  <a href="https://www.figma.com/design/Tb4SDE9A4oEpLaXfN7aAhe/Projeto-%7C-Sabesp?node-id=0-1&t=yQExxpGpTtvoIWHk-1" target="_blank">
    <img src="assets/preview-mockup.svg" alt="Preview da Interface no Figma" width="850px" />
  </a>
</p>

> **A Visão:** Transformar a fiscalização urbana do município de Santos substituindo a lentidão e fragmentação da ouvidoria tradicional (onde chamados levam de 30 a 60 dias) por uma **esteira digital única, transparente e auditável**, garantindo o cumprimento do prazo legal de 15 dias da Lei Complementar nº 852/2014 e conectando o munícipe, a Prefeitura (Sepref / Semam / Seserp) e a Sabesp em tempo real.

---

## 📊 O Cenário Real em Números

* **99% de Cobertura Urbana:** Conforme o PMISB (Relatório R4), o desafio de Santos não é a falta de rede, mas a manutenção preventiva e corretiva de uma malha centenária e o fechamento do déficit de 1% em palafitas (como o projeto de 2.500 ligações no **Dique da Vila Gilda**).
* **15 Dias Corridos:** Prazo legal máximo improrrogável estabelecido pela **Lei Complementar nº 852/2014** para a Sabesp concluir reparos na via pública após notificação.
* **30 a 60 Dias na Ouvidoria:** O descompasso da Ouvidoria tradicional (SOM Web / 162), que considera "normal" prazos de até 30 dias enquanto a lei exige solução em 15.
* **+190 Intimações / Mês:** Volume real de intimações e notificações emitidas pelos fiscais técnicos das Prefeituras Regionais (Sepref) para cobrar concessionárias.
* **R$ 818 Mil em Multa:** Sanção histórica aplicada pela SEMAM contra a Sabesp após vazamento que engoliu a Rua Goiás, recurso que hoje alimenta o **Fundo Municipal de Saneamento Básico e Ambiental (FMSBA / Lei nº 4.525/2024)** sob controle do Conselho Municipal (**CMSBS**).

---

## 💎 Os Três Pilares da Solução

| 📱 Para o Munícipe | 🏛️ Para a Prefeitura (Sepref / Semam) | 💧 Para a Sabesp |
| :--- | :--- | :--- |
| **Voz Ativa & Agilidade** | **Controle & Fiscalização** | **Operação & Rastreabilidade** |
| Abertura em segundos com upload de fotos e localização. Acompanhamento transparente do status sem depender de ligações no 162 ou prazos lentos de 60 dias. | Painel centralizado com triagem nas 5 Zonas de Santos, despacho automático de intimações e auditoria do prazo de 15 dias da Lei nº 852/2014. | Recepção de ordens já pré-validadas tecnicamente, registro de pareceres de campo e comprovação fotográfica das intervenções na via. |

---

## 🧭 Central de Documentação (Docs Hub)

Aprofunde-se nos detalhes de engenharia, arquitetura e governança pública:

| Módulo | Foco & Conteúdo | Link |
| :--- | :--- | :---: |
| **🏛️ Contexto & Legislação** | Diagnóstico real de Santos, caso Rua Goiás, Vila Gilda, FMSBA (Lei 4.525), CMSBS, atuação Sepref/Semam/Seserp e ODS. | [Explorar →](docs/contexto-e-legislacao.md) |
| **📋 Requisitos & Regras** | Matriz MoSCoW (RF01 a RF13, RNF01 a RNF07), regras de negócio e tipologias oficiais do PMISB (Relatório R4). | [Explorar →](docs/requisitos-do-sistema.md) |
| **🗄️ Arquitetura & Banco** | Ciclo de vida do chamado, matriz de status, modelo ER (Proto SQL), Soft Delete e Logs imutáveis (*Append-Only*). | [Explorar →](docs/arquitetura-e-banco.md) |
| **🔐 Regras de Negócio & API** | Controle de acesso por papéis (RBAC), validações de domínio e catálogo de rotas RESTful com Swagger UI. | [Explorar →](docs/regras-e-api.md) |
| **🎨 Design System & UI/UX** | Tipografia Montserrat, catálogo de logos vetoriais SVG da Prefeitura de Santos e acesso ao protótipo no Figma. | [Explorar →](docs/design-system.md) |
| **🎓 Metodologia TCC** | Tema, problema de pesquisa, objetivos, justificativa tríplice, metodologia científica e mapeamento para monografia ABNT. | [Explorar →](docs/metodologia-tcc.md) |

---

## 🛠️ Stack Tecnológica

* **Core Backend:** Java 17 • Spring Boot 3 (Data JPA, Security, Web, Validation) • Hibernate • Lombok
* **Banco de Dados & Nuvem:** PostgreSQL • Supabase
* **Design & Experiência:** Figma • Identidade Visual Oficial da Prefeitura de Santos (Montserrat)
* **Padronização:** RESTful API • OpenAPI 3 (Swagger UI) • Git & GitHub

---

## ⚡ Início Rápido (Quickstart)

```bash
# 1. Clone o repositório
git clone https://github.com/CarvalhoNathan/sabesp.git
cd sabesp

# 2. Configure as variáveis de conexão com o Supabase em src/main/resources/application.properties
# spring.datasource.url=jdbc:postgresql://<HOST_SUPABASE>:5432/<DATABASE>
# spring.datasource.username=postgres
# spring.datasource.password=<SUA_SENHA>

# 3. Execute a aplicação
mvn spring-boot:run
```

> Acesse a aplicação em `http://localhost:8080` e a documentação interativa em `http://localhost:8080/swagger-ui.html`.

---

## 🗺️ Roadmap de Entrega

- [x] **Fase 1 · Concepção e Marco Legal:** Diagnóstico municipal, Lei 852/2014, FMSBA (Lei 4.525/2024), PMISB R4 e esteira de valor.
- [x] **Fase 2 · Modelagem e Arquitetura:** Proto SQL, regras de auditoria imutável (Logs), Soft Delete e matriz de requisitos (RF01–RF13).
- [x] **Fase 3 · Identidade Visual:** Padronização dos vetores oficiais `.svg` da Prefeitura de Santos e tipografia Montserrat.
- [ ] **Fase 4 · Prototipagem UI/UX:** Telas interativas no Figma (Munícipe, Fiscalização Municipal e Operação Sabesp).
- [ ] **Fase 5 · Banco em Nuvem:** Implementação do schema relacional no PostgreSQL (Supabase).
- [ ] **Fase 6 · Backend Spring Boot:** Desenvolvimento da API RESTful em Spring Boot 3 com autenticação, validações e RBAC.

---

## 👥 Engenharia & Autoria

Projeto concebido e desenvolvido para o Trabalho de Conclusão de Curso (TCC) por:

* **Nathan Carvalho** — [@CarvalhoNathan](https://github.com/CarvalhoNathan)
* **Andrey Celestino** — [@DreyCelestino](https://github.com/DreyCelestino)

> 🎨 **Protótipo Interativo:** [Acessar Telas no Figma](https://www.figma.com/design/Tb4SDE9A4oEpLaXfN7aAhe/Projeto-%7C-Sabesp?node-id=0-1&t=yQExxpGpTtvoIWHk-1)  
> 🤝 **Padrões de Desenvolvimento & Git:** [Consulte o Guia de Contribuição (CONTRIBUTING.md)](CONTRIBUTING.md)

---

## 📄 Licença

Este projeto é desenvolvido para fins acadêmicos e educacionais sob os termos da **[Licença MIT](LICENSE)**. Consulte o arquivo para maiores detalhes.  
&copy; 2026 Nathan Carvalho & Andrey Celestino.
