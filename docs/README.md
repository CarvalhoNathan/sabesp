[← Voltar ao Repositório Principal](../README.md)

# 📚 Central de Documentação Técnica e Acadêmica (Docs Hub)
### Guia estrutural dos 4 pilares de concepção, engenharia e metodologia

> 💡 **Em Resumo:** Este diretório reúne toda a especificação técnica, fundamentação jurídica e modelagem de software do projeto acadêmico de fiscalização e auditoria do saneamento de Santos. Os documentos estão organizados em **4 pilares temáticos** para orientar desenvolvedores, designers e avaliadores acadêmicos.

---

## 🧭 Mapa Estrutural dos 4 Pilares

```text
docs/
├── 🏛️ 1. institucional/   → Contexto territorial, leis municipais, triagem inteligente e glossário
├── 🎨 2. design-ux/       → Personas de usuários, arquitetura de informação e design system
├── ⚙️ 3. engenharia/      → Requisitos de software, modelagem relacional, API REST e riscos
└── 🎓 4. academico/       → Metodologia científica, justificativa tríplice e monografia ABNT
```

---

## 🏛️ Pilar 1 · Institucional, Governança e Regras de Negócio

Aborda o ecossistema da cidade de Santos, os episódios reais de contaminação e solapamento viário, o arcabouço normativo das leis municipais e o vocabulário técnico padronizado:

| Documento | Descrição e Finalidade | Acesso |
| :--- | :--- | :---: |
| [`contexto-e-legislacao.md`](institucional/contexto-e-legislacao.md) | Diagnóstico territorial de Santos, contrato de concessão Sabesp (LC nº 707/2010), caso Rua Goiás, FMSBA (Lei nº 4.525/2024), CMSBS e LC nº 852/2014. | [Abrir Documento →](institucional/contexto-e-legislacao.md) |
| [`triagem-inteligente.md`](institucional/triagem-inteligente.md) | Árvore lógica de decisão para distinguir vazamentos de esgoto da Sabesp de águas pluviais/chuva da Prefeitura (Seserp/Saturnino de Brito). | [Abrir Documento →](institucional/triagem-inteligente.md) |
| [`glossario.md`](institucional/glossario.md) | Glossário formal estruturado para monografias acadêmicas com definição técnica e jurídica de siglas e termos de saneamento (PV, EEE, EPC, FMSBA, etc.). | [Abrir Documento →](institucional/glossario.md) |

---

## 🎨 Pilar 2 · Design Thinking, Telas e Experiência do Usuário (UX)

Focado na humanização do software, nas necessidades dos cidadãos santistas e na interface intuitiva construída com a identidade visual oficial do município:

| Documento | Descrição e Finalidade | Acesso |
| :--- | :--- | :---: |
| [`personas-e-cenarios.md`](design-ux/personas-e-cenarios.md) | Perfis de usuários humanizados (Dona Helena, Fiscal Carlos da Sepref, Roberto da Sabesp e Dra. Renata da Semam) e seus cenários práticos de uso. | [Abrir Documento →](design-ux/personas-e-cenarios.md) |
| [`jornada-e-ux.md`](design-ux/jornada-e-ux.md) | Sitemap dos 3 módulos (Munícipe, Prefeitura e Sabesp), wireframe textual em 3 passos para abertura de chamados e linha do tempo (Stepper). | [Abrir Documento →](design-ux/jornada-e-ux.md) |
| [`design-system.md`](design-ux/design-system.md) | Diretrizes visuais oficiais da Prefeitura de Santos: tipografia Montserrat, paleta de cores, catálogo de logos vetoriais em `.svg` e link do Figma. | [Abrir Documento →](design-ux/design-system.md) |

---

## ⚙️ Pilar 3 · Engenharia de Software, Arquitetura e Riscos

Contempla as decisões técnicas de backend, banco de dados em nuvem, controle de acessos e mitigação de vulnerabilidades:

| Documento | Descrição e Finalidade | Acesso |
| :--- | :--- | :---: |
| [`requisitos-do-sistema.md`](engenharia/requisitos-do-sistema.md) | Matriz formal MoSCoW com Requisitos Funcionais (RF01 a RF13), Requisitos Não-Funcionais (RNF01 a RNF07) e tipologias oficiais do PMISB R4. | [Abrir Documento →](engenharia/requisitos-do-sistema.md) |
| [`arquitetura-e-banco.md`](engenharia/arquitetura-e-banco.md) | Ciclo de vida dos chamados, matriz de transição de status, modelo ER relacional (Proto SQL), padrão Soft Delete e trilha de auditoria com modelo Append-Only. | [Abrir Documento →](engenharia/arquitetura-e-banco.md) |
| [`regras-e-api.md`](engenharia/regras-e-api.md) | Controle de acesso baseado em papéis (RBAC), validações de domínio (CPF, fotos), especificação das rotas RESTful e Swagger UI. | [Abrir Documento →](engenharia/regras-e-api.md) |
| [`matriz-de-riscos.md`](engenharia/matriz-de-riscos.md) | Análise de riscos segundo as normas ISO 31000 e PMBOK: contingências para trotes, vazamento de dados (LGPD), interferência de redes e marés. | [Abrir Documento →](engenharia/matriz-de-riscos.md) |

---

## 🎓 Pilar 4 · Metodologia Científica e Estrutura de Monografia (TCC)

Direcionado aos critérios de rigor metodológico acadêmico exigidos para a aprovação do Trabalho de Conclusão de Curso em cursos de Tecnologia da Informação e Engenharia de Software:

| Documento | Descrição e Finalidade | Acesso |
| :--- | :--- | :---: |
| [`metodologia-tcc.md`](academico/metodologia-tcc.md) | Estrutura do TCC (FATEC Rubens Lara · Sistemas para Internet), procedimentos metodológicos, objetivos, justificativa e normas aplicáveis (ABNT NBR 14724). | [Abrir Documento →](academico/metodologia-tcc.md) |

---

## 📚 Mapeamento para os Capítulos da Monografia (ABNT)

Para a redação da monografia final, os documentos deste repositório correlacionam-se da seguinte forma com os capítulos acadêmicos:

* **Capítulo 1 · Introdução e Justificativa:** [`academico/metodologia-tcc.md`](academico/metodologia-tcc.md) e [`institucional/contexto-e-legislacao.md`](institucional/contexto-e-legislacao.md).
* **Capítulo 2 · Referencial Teórico e Marco Legal:** [`institucional/contexto-e-legislacao.md`](institucional/contexto-e-legislacao.md) e [`institucional/glossario.md`](institucional/glossario.md).
* **Capítulo 3 · Engenharia de Requisitos e Design Thinking:** [`design-ux/personas-e-cenarios.md`](design-ux/personas-e-cenarios.md), [`institucional/triagem-inteligente.md`](institucional/triagem-inteligente.md) e [`engenharia/requisitos-do-sistema.md`](engenharia/requisitos-do-sistema.md).
* **Capítulo 4 · Modelagem Arquitetural, Banco e Gestão de Riscos:** [`engenharia/arquitetura-e-banco.md`](engenharia/arquitetura-e-banco.md), [`engenharia/regras-e-api.md`](engenharia/regras-e-api.md) e [`engenharia/matriz-de-riscos.md`](engenharia/matriz-de-riscos.md).
* **Capítulo 5 · Design de Interação e Prototipagem:** [`design-ux/jornada-e-ux.md`](design-ux/jornada-e-ux.md) e [`design-ux/design-system.md`](design-ux/design-system.md).
* **Capítulo 6 · Considerações Finais:** Monografia final com base nos resultados e testes.

---

[← Voltar ao Repositório Principal](../README.md)