# 💧 Sabesp & Semam · Santos
### Gestão inteligente e auditoria pública na rede de esgoto

<p align="center">
  <img src="https://img.shields.io/badge/Java_17-ED8B00?style=flat-square&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/Spring_Boot_3-6DB33F?style=flat-square&logo=spring-boot&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=flat-square&logo=postgresql&logoColor=white" alt="PostgreSQL" />
  <img src="https://img.shields.io/badge/Supabase-3ECF8E?style=flat-square&logo=supabase&logoColor=white" alt="Supabase" />
  <img src="https://img.shields.io/badge/Figma-F24E1E?style=flat-square&logo=figma&logoColor=white" alt="Figma" />
</p>

<p align="center">
  <a href="https://www.figma.com/design/Tb4SDE9A4oEpLaXfN7aAhe/Projeto-%7C-Sabesp?node-id=0-1&t=yQExxpGpTtvoIWHk-1" target="_blank"><b>🎨 Protótipo no Figma</b></a> •
  <a href="#-central-de-documentação-docs-hub"><b>🧭 Documentação</b></a> •
  <a href="#-início-rápido-quickstart"><b>⚡ Início Rápido</b></a> •
  <a href="#-roadmap-de-entrega"><b>🗺️ Roadmap</b></a>
</p>

<p align="center">
  <a href="https://www.figma.com/design/Tb4SDE9A4oEpLaXfN7aAhe/Projeto-%7C-Sabesp?node-id=0-1&t=yQExxpGpTtvoIWHk-1" target="_blank">
    <img src="assets/preview-mockup.svg" alt="Preview da Interface no Figma" width="850px" />
  </a>
</p>

> **A Visão:** Transformar a fiscalização urbana do município de Santos substituindo a burocracia da ouvidoria tradicional por uma **esteira digital única, transparente e auditável**, conectando o cidadão, o órgão regulador ambiental (Semam) e a concessionária (Sabesp) em tempo real.

---

## 💎 Os Três Pilares da Solução

| 📱 Para o Munícipe | 🏛️ Para a Prefeitura (Semam) | 💧 Para a Sabesp |
| :--- | :--- | :--- |
| **Voz Ativa & Agilidade** | **Controle & Fiscalização** | **Operação & Rastreabilidade** |
| Abertura em segundos com upload de fotos e localização. Acompanhamento transparente do status sem depender de ligações no 162. | Painel centralizado com triagem nas 5 Zonas de Santos e auditoria em tempo real do cumprimento da Lei Complementar nº 852/2014. | Recepção de ordens já pré-validadas tecnicamente, registro de pareceres e comprovação fotográfica das intervenções em campo. |

---

## 🧭 Central de Documentação (Docs Hub)

Aprofunde-se nos detalhes de engenharia e regras de negócio:

| Módulo | Foco & Conteúdo | Link |
| :--- | :--- | :---: |
| **🏛️ Contexto & Legislação** | O diagnóstico real de Santos, a Ouvidoria 162, a Lei 852/2014, as 5 Zonas e os ODS da ONU. | [Explorar →](docs/contexto-e-legislacao.md) |
| **🗄️ Arquitetura & Banco** | Ciclo de vida do chamado, matriz de status, modelo ER (Proto SQL) e Soft Delete. | [Explorar →](docs/arquitetura-e-banco.md) |
| **🔐 Regras de Negócio & API** | Controle de acesso por papéis (RBAC), validações de domínio e catálogo de rotas REST / Swagger. | [Explorar →](docs/regras-e-api.md) |
| **🎨 Design System & UI/UX** | Tipografia Montserrat, catálogo de logos SVG oficiais e acesso ao protótipo no Figma. | [Explorar →](docs/design-system.md) |

---

## 🛠️ Stack Tecnológica

* **Core Backend:** Java 17 • Spring Boot 3 (Data JPA, Web, Validation) • Hibernate
* **Banco de Dados & Nuvem:** PostgreSQL • Supabase
* **Design & Experiência:** Figma • Identidade Visual Oficial de Santos
* **Padronização:** RESTful API • OpenAPI 3 (Swagger UI) • Git & GitHub

---

## ⚡ Início Rápido (Quickstart)

```bash
# 1. Clone o repositório
git clone https://github.com/CarvalhoNathan/sabesp.git
cd sabesp

# 2. Configure o Supabase em src/main/resources/application.properties
# spring.datasource.url=jdbc:postgresql://<HOST_SUPABASE>:5432/<DATABASE>
# spring.datasource.username=postgres
# spring.datasource.password=<SUA_SENHA>

# 3. Execute a aplicação
mvn spring-boot:run
```

> Acesse a aplicação em `http://localhost:8080` e a documentação interativa em `http://localhost:8080/swagger-ui.html`.

---

## 🗺️ Roadmap de Entrega

- [x] **Fase 1 · Concepção:** Fundamentação legal (Lei 852/2014), diagnóstico municipal e esteira de valor.
- [x] **Fase 2 · Modelagem:** Proto SQL, regras de auditoria imutável (Logs) e Soft Delete.
- [x] **Fase 3 · Identidade:** Padronização dos vetores oficiais `.svg` e tipografia Montserrat.
- [ ] **Fase 4 · UI/UX:** Prototipagem interativa das telas no Figma (Munícipe, Gestão e Operação).
- [ ] **Fase 5 · Banco em Nuvem:** Implementação do schema relacional no PostgreSQL (Supabase).
- [ ] **Fase 6 · Backend:** Desenvolvimento da API RESTful em Spring Boot com validações e RBAC.

---

## 👥 Engenharia & Autoria

Projeto acadêmico concebido e desenvolvido por:

* **Nathan Carvalho** — [@CarvalhoNathan](https://github.com/CarvalhoNathan)
* **Andrey Celestino** — [@DreyCelestino](https://github.com/DreyCelestino)

> 🎨 **Protótipo Interativo:** [Acessar Telas no Figma](https://www.figma.com/design/Tb4SDE9A4oEpLaXfN7aAhe/Projeto-%7C-Sabesp?node-id=0-1&t=yQExxpGpTtvoIWHk-1)

---

## 📄 Licença

Este projeto é desenvolvido para fins acadêmicos e educacionais. Consulte o arquivo de licença para maiores detalhes.
