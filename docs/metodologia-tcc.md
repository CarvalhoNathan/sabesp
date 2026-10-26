[← Voltar ao Hub Principal](../README.md)

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

> *Como modernizar e conferir transparência e auditoria ao fluxo de atendimento a vazamentos e obras na rede de esgotamento sanitário em Santos, assegurando o cumprimento dos prazos regulatórios estipulados pela Lei Complementar Municipal nº 852/2014?*

---

## 🎯 Objetivos

### Objetivo Geral
Desenvolver e validar uma plataforma web centralizada em arquitetura Java Spring Boot e PostgreSQL que digitalize a esteira de requisições de manutenção na rede de esgoto de Santos, integrando a denúncia cidadã, a triagem fiscalizatória da Secretaria de Meio Ambiente (SEMAM) e a execução operacional da concessionária Sabesp.

### Objetivos Específicos
1. **Mapear a esteira atual** de requisições municipais via Ouvidoria 162 e identificar os gargalos de comunicação entre os órgãos.
2. **Modelar um banco de dados relacional** com padrões de *Soft Delete* e tabelas de auditoria imutáveis (*Append-Only*).
3. **Projetar interfaces intuitivas e acessíveis no Figma** com base no manual de identidade visual de Santos para Munícipe, Fiscal e Operador.
4. **Implementar regras de negócio e controle de acesso (RBAC)** em API RESTful, automatizando a contagem de SLA de 15 dias corridos da legislação municipal.
5. **Avaliar a aderência da solução** aos Objetivos de Desenvolvimento Sustentável (ODS 6, 9 e 11 da ONU).

---

## 💡 Justificativa e Relevância Tríplice

> 🌿 **1. Relevância Ambiental:** A rede centenária de drenagem de Santos (canais) deságua na orla da praia. Vazamentos de esgoto não contidos contaminam o lençol freático e a balneabilidade das águas.

> ⚖️ **2. Relevância Jurídica e Fiscalizatória:** A Lei Complementar nº 852/2014 prevê multa de R$ 17.045,05 por atraso nos reparos da Sabesp. O sistema fornece à Prefeitura o respaldo probatório incontestável com registros cronológicos auditáveis.

> 💻 **3. Relevância Tecnológica:** Demonstra a aplicação prática do ecossistema corporativo Java (Spring Boot) com persistência JPA, controle transacional e computação em nuvem com Supabase.

---

## 🔬 Metodologia Científica e de Desenvolvimento

* **Classificação da Pesquisa:** Pesquisa aplicada de natureza tecnológica, baseada no método de **Estudo de Caso** centrado no município de Santos.
* **Ciclo de Desenvolvimento:** Metodologia Ágil com sprints incrementais:
  * **Etapa 1:** Análise de domínio e levantamento de requisitos com base em legislação e dados oficiais da Prefeitura.
  * **Etapa 2:** Prototipagem e design iterativo (Figma).
  * **Etapa 3:** Modelagem arquitetural e persistência em nuvem (PostgreSQL/Supabase).
  * **Etapa 4:** Desenvolvimento do backend em camadas (Spring Boot).
  * **Etapa 5:** Verificação e testes de integração de API.

---

## 📚 Mapeamento dos Capítulos da Monografia

Os documentos técnicos do repositório correspondem diretamente à estrutura de monografia no padrão ABNT:

| Capítulo da Monografia | Conteúdo Acadêmico | Documento Fonte no Repositório |
| :--- | :--- | :---: |
| **Capítulo 1 · Introdução** | Contextualização, problema, justificativa e objetivos. | [`metodologia-tcc.md`](metodologia-tcc.md) |
| **Capítulo 2 · Referencial Teórico** | Saneamento em Santos, Sabesp, SEMAM, Lei 852/2014 e ODS da ONU. | [`contexto-e-legislacao.md`](contexto-e-legislacao.md) |
| **Capítulo 3 · Engenharia de Requisitos** | Perfis de usuário, casos de uso, regras RBAC e especificação REST. | [`regras-e-api.md`](regras-e-api.md) |
| **Capítulo 4 · Modelagem e Arquitetura** | Modelo ER (Proto SQL), Soft Delete, Auditoria e camadas Spring. | [`arquitetura-e-banco.md`](arquitetura-e-banco.md) |
| **Capítulo 5 · Design e Prototipagem** | Identidade de Santos, tipografia Montserrat e telas no Figma. | [`design-system.md`](design-system.md) |
| **Capítulo 6 · Considerações Finais** | Resultados obtidos, limitações do estudo e trabalhos futuros. | Monografia Final |

---

[← Voltar ao Hub Principal](../README.md)
