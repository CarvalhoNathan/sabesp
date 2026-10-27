[← Voltar ao Hub Principal](../../README.md)

# 🎓 Diretrizes Metodológicas e Estrutura Acadêmica (TCC)
### Rigor científico, delimitação de pesquisa e mapeamento para monografia ABNT

> 💡 **Em Resumo:** Este documento formaliza o projeto sob as normas e exigências de um **Trabalho de Conclusão de Curso (TCC)** em Engenharia de Software e Sistemas de Informação, servindo de roteiro estrutural direto para a elaboração da monografia acadêmica.

---

## 📌 Navegação Rápida
- [Delimitação do Tema e Título Proposto](#-delimitação-do-tema-e-título-proposto)
- [Problema de Pesquisa](#-problema-de-pesquisa)
- [Objetivos](#-objetivos)
- [Justificativa e Relevância Tríplice](#-justificativa-e-relevância-tríplice)
- [Metodologia Científica e de Desenvolvimento](#-metodologia-científica-e-de-desenvolvimento)
- [Mapeamento dos Capítulos da Monografia](#-mapeamento-dos-capítulos-da-monografia)

---

## 🏷️ Delimitação do Tema e Título Proposto

* **Área de Concentração:** Engenharia de Software, Governança Pública Digital (GovTech) e Cidades Inteligentes.
* **Tema:** Auditoria e gestão digital de serviços públicos essenciais de saneamento básico.
* **Título Acadêmico:**  
  *“Desenvolvimento de Sistema Web para Gestão e Auditoria Pública de Requisições na Rede de Esgoto Municipal: Um Estudo de Caso em Santos com Integração entre Cidadão, SEMAM e Sabesp”*

---

## ❓ Problema de Pesquisa

> *Como modernizar e conferir transparência e auditoria ao fluxo de atendimento a vazamentos e obras na rede de esgotamento sanitário em Santos, assegurando o cumprimento das diretrizes do Plano Municipal de Saneamento Básico (PMISB), a responsabilização perante o Fundo Ambiental (FMSBA / Lei nº 4.525/2024) e os prazos estipulados pela Lei Complementar Municipal nº 852/2014?*

---

## 🎯 Objetivos

### Objetivo Geral
Desenvolver e validar uma plataforma web centralizada em arquitetura Java Spring Boot e PostgreSQL que digitalize a esteira de requisições de manutenção na rede de esgoto de Santos, integrando a denúncia cidadã, a triagem fiscalizatória da Secretaria de Meio Ambiente (SEMAM/Sepref) e a execução operacional da concessionária Sabesp.

### Objetivos Específicos
1. **Mapear a esteira atual** de requisições municipais via Ouvidoria 162 e identificar os gargalos de comunicação entre os órgãos com base no **PMISB (Relatório R4 / Ações de Emergência)**, no caso emblemático da **Rua Goiás (2023)** e nos projetos de universalização periférica no **Dique da Vila Gilda (2025–2026)**.
2. **Aplicar práticas de Design Thinking**, mapeando as personas de munícipes, fiscais e operadores, detalhando suas jornadas e criando um assistente de triagem inteligente (esgoto vs. água de chuva).
3. **Estruturar uma matriz de riscos formal** (ISO 31000 / PMBOK) cobrindo riscos de trotes, LGPD, interferências de rede e contingências de maré.
4. **Modelar um banco de dados relacional** com padrões de *Soft Delete* e tabelas de auditoria imutáveis (*Append-Only*).
5. **Projetar interfaces intuitivas e acessíveis no Figma** com base no manual de identidade visual de Santos para Munícipe, Fiscal e Operador.
6. **Implementar regras de negócio e controle de acesso (RBAC)** em API RESTful, automatizando a contagem de SLA de 15 dias corridos da legislação municipal e gerando insumos para o **CMSBS** e o **FMSBA**.
7. **Avaliar a aderência da solução** aos Objetivos de Desenvolvimento Sustentável (ODS 6, 9, 11 e 16 da ONU).

---

## 💡 Justificativa e Relevância Tríplice

> 🌿 **1. Relevância Ambiental e Territorial:** A rede centenária de drenagem de Santos (canais) deságua na orla da praia. Vazamentos de esgoto não contidos contaminam o lençol freático e a balneabilidade das águas. Concomitantemente, áreas vulneráveis como o Dique da Vila Gilda (Parque Palafitas) demandam monitoramento prioritário para consolidar as metas de universalização do saneamento.

> ⚖️ **2. Relevância Jurídica, Fiscalizatória e Social:** Episódios reais como o incidente da Rua Goiás em 2023 (cratera e contaminação de solo que resultou em multa de R$ 818 mil da SEMAM contra a Sabesp), o controle do prazo de 15 dias da Lei Complementar nº 852/2014 e a governança de recursos do FMSBA (Lei nº 4.525/2024 e Decretos 10.687/2025 e 11.328/2026) evidenciam a necessidade de auditoria pública digital. Ademais, com o cenário de privatização da concessionária (Lei Estadual nº 17.853/2023), o poder municipal assume papel regulador mandatório.

> 💻 **3. Relevância Tecnológica:** Demonstra a aplicação prática do ecossistema corporativo Java (Spring Boot) com persistência JPA, controle transacional e computação em nuvem com Supabase/PostgreSQL.

---

## 🔬 Metodologia Científica e de Desenvolvimento

* **Classificação da Pesquisa:** Pesquisa aplicada de natureza tecnológica, baseada no método de **Estudo de Caso** centrado no município de Santos.
* **Fontes Primárias:** Plano Municipal Integrado de Saneamento Básico de Santos (PMISB · Relatório R4), Leis Municipais nº 852/2014, 817/2013 e 4.525/2024 (FMSBA), Decretos Municipais nº 10.687/2025 e 11.328/2026 (CMSBS), Lei Estadual nº 17.853/2023, relatórios da SEMAM e dados da parceria Sabesp/Prefeitura na Vila Gilda.
* **Ciclo de Desenvolvimento:** Metodologia Ágil com sprints incrementais:
  * **Etapa 1:** Análise de domínio e levantamento de requisitos com base no PMISB e legislação municipal.
  * **Etapa 2:** Design Thinking, personas, cenários e árvore de triagem inteligente.
  * **Etapa 3:** Análise e gestão formal de riscos (ISO 31000 / PMBOK).
  * **Etapa 4:** Prototipagem e design iterativo (Figma).
  * **Etapa 5:** Modelagem arquitetural e persistência em nuvem (PostgreSQL/Supabase).
  * **Etapa 6:** Desenvolvimento do backend em camadas (Spring Boot).
  * **Etapa 7:** Verificação e testes de integração de API.

---

## 📚 Mapeamento dos Capítulos da Monografia

Os documentos técnicos do repositório correspondem diretamente à estrutura de monografia no padrão ABNT:

| Capítulo da Monografia | Conteúdo Acadêmico | Documento Fonte no Repositório |
| :--- | :--- | :---: |
| **Capítulo 1 · Introdução** | Contextualização, caso Rua Goiás, caso Vila Gilda, problema, justificativa e objetivos. | [`metodologia-tcc.md`](metodologia-tcc.md) |
| **Capítulo 2 · Referencial Teórico** | Saneamento em Santos, Sabesp (privatização), SEMAM/Sepref, PMISB R4, Lei 852/2014, FMSBA e CMSBS. | [`contexto-e-legislacao.md`](../institucional/contexto-e-legislacao.md) |
| **Capítulo 3 · Engenharia de Requisitos & Design Thinking** | Personas, cenários, árvore de triagem inteligente, matriz MoSCoW (RF/RNF) e RBAC. | [`personas-e-cenarios.md`](../design-ux/personas-e-cenarios.md) • [`triagem-inteligente.md`](../institucional/triagem-inteligente.md) • [`requisitos-do-sistema.md`](../engenharia/requisitos-do-sistema.md) • [`regras-e-api.md`](../engenharia/regras-e-api.md) |
| **Capítulo 4 · Modelagem, Arquitetura & Gestão de Riscos** | Modelo ER (Proto SQL), Soft Delete, Auditoria, camadas Spring e Matriz de Riscos ISO 31000. | [`arquitetura-e-banco.md`](../engenharia/arquitetura-e-banco.md) • [`matriz-de-riscos.md`](../engenharia/matriz-de-riscos.md) |
| **Capítulo 5 · Design de Interação e Prototipagem (UI/UX)** | Arquitetura de telas (Sitemap), wireframe textual, stepper de acompanhamento, tipografia Montserrat e Figma. | [`jornada-e-ux.md`](../design-ux/jornada-e-ux.md) • [`design-system.md`](../design-ux/design-system.md) |
| **Capítulo 6 · Considerações Finais** | Resultados obtidos, limitações do estudo e trabalhos futuros. | Monografia Final |

---

[← Voltar ao Hub Principal](../../README.md)