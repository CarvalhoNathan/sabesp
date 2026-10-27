[← Voltar ao Hub Principal](../../README.md)

# 🛡️ Matriz de Análise e Gestão de Riscos
### Identificação de ameaças técnicas, operacionais, jurídicas e planos de mitigação

> 💡 **Em Resumo:** Este documento mapeia sistematicamente os riscos associados ao ciclo de vida e à implantação do sistema segundo as diretrizes das normas **ISO 31000** e **PMBOK**, garantindo segurança jurídica perante a LGPD, sustentabilidade operacional e integridade probatória para a aplicação de penalidades.

---

## 📌 Navegação Rápida
- [Metodologia de Avaliação de Riscos](#-metodologia-de-avaliação-de-riscos)
- [Matriz Consolidada de Riscos (Probabilidade vs. Impacto)](#-matriz-consolidada-de-riscos-probabilidade-vs-impacto)
- [Detalhamento dos Riscos e Planos de Mitigação](#-detalhamento-dos-riscos-e-planos-de-mitigação)
  - [Risco 1 · Trotes e Fotos Falsas da Internet](#-risco-1--trotes-e-fotos-falsas-da-internet)
  - [Risco 2 · Conflito de Atribuição entre Concessionárias](#-risco-2--conflito-de-atribuição-entre-concessionárias)
  - [Risco 3 · Exposição de Dados Pessoais (LGPD)](#-risco-3--exposição-de-dados-pessoais-lgpd)
  - [Risco 4 · Resistência à Mudança Cultural na Prefeitura](#-risco-4--resistência-à-mudança-cultural-na-prefeitura)
  - [Risco 5 · Interferência de Maré Alta na Zona Noroeste](#-risco-5--interferência-de-maré-alta-na-zona-noroeste)
  - [Risco 6 · Sobrecarga de Armazenamento e Conexão em Nuvem](#-risco-6--sobrecarga-de-armazenamento-e-conexão-em-nuvem)

---

## 📐 Metodologia de Avaliação de Riscos

Os riscos são categorizados e ponderados segundo a fórmula de Severidade:
$$\text{Severidade} = \text{Probabilidade} \times \text{Impacto}$$

* **Probabilidade:** Baixa (1), Média (2), Alta (3).
* **Impacto:** Baixo (1), Médio (2), Alto (3).
* **Classificação:**
  * 🟢 **Baixo (1 a 2):** Monitoramento contínuo.
  * 🟡 **Médio (3 a 4):** Ação preventiva recomendada.
  * 🔴 **Crítico (6 a 9):** Mitigação obrigatória e plano de contingência ativo.

---

## 📊 Matriz Consolidada de Riscos (Probabilidade vs. Impacto)

| ID | Categoria | Descrição do Risco | Probabilidade | Impacto | Severidade | Classificação |
| :---: | :--- | :--- | :---: | :---: | :---: | :---: |
| **R01** | Operacional | Trotes, fotos falsas da internet ou denúncias fora de Santos. | Alta (3) | Médio (2) | 6 | 🔴 **Crítico** |
| **R02** | Jurídico | Concessionária alegar que o problema foi causado por terceiros (CPFL, Comgás ou chuva). | Alta (3) | Alto (3) | 9 | 🔴 **Crítico** |
| **R03** | Segurança | Vazamento de dados de munícipes (LGPD) para operários de rua. | Baixa (1) | Alto (3) | 3 | 🟡 **Médio** |
| **R04** | Adoção | Fiscais da Sepref resistirem ao meio digital e manterem o papel. | Média (2) | Médio (2) | 4 | 🟡 **Médio** |
| **R05** | Geotécnico | Atrasos na Zona Noroeste (Vila Gilda) decorrentes de maré e mangue. | Alta (3) | Médio (2) | 6 | 🔴 **Crítico** |
| **R06** | Técnico | Lentidão por upload de fotos pesadas e queda de conexão com nuvem. | Média (2) | Médio (2) | 4 | 🟡 **Médio** |

---

## 🛡️ Detalhamento dos Riscos e Planos de Mitigação

### 🎭 Risco 1 · Trotes e Fotos Falsas da Internet
* **O Problema:** Usuários de má-fé abrirem chamados fictícios utilizando imagens de buracos baixadas do Google Imagens para gerar deslocamento desnecessário de fiscais e equipes da concessionária.
* **Plano de Mitigação Arquitetural:**
  1. **Autenticação com CPF Válido:** Todo chamado é vinculado a um usuário autenticado, com verificação de algoritmo de CPF e controle de abuso por IP.
  2. **Validação de Imagem:** Validação de extensões estritas (`.png`, `.jpg`, `.jpeg`) e limite de payload (5MB).
  3. **Triagem Humana Obrigatória:** O chamado nunca é despachado diretamente para a Sabesp. Os fiscais técnicos da Sepref/Semam conferem as fotos e a consistência do local antes de expedir a intimação formal.

---

### ⚡ Risco 2 · Conflito de Atribuição entre Concessionárias
* **O Problema:** A Sabesp ser intimada com prazo de 15 dias da Lei nº 852/2014, mas a equipe de campo constatar que o solapamento viário foi causado por vazamento na tubulação de gás (Comgás), cabeamento subterrâneo (CPFL) ou galeria pluvial da Prefeitura.
* **Plano de Mitigação Arquitetural:**
  1. **Status de Contestação Técnica:** A Sabesp pode submeter um laudo técnico fundamentado com fotos da escavação apontando interferência de terceiros.
  2. **Mediação Fiscalizatória:** O fiscal da Prefeitura analisa a contestação. Se confirmada a culpa de terceiros, a intimação contra a Sabesp é encerrada sem aplicação de multa, e o sistema permite **redespachar a ordem para a concessionária competente**.

---

### 🔒 Risco 3 · Exposição de Dados Pessoais (LGPD)
* **O Problema:** Dados sensíveis de munícipes (nome completo, CPF, telefone) ficarem expostos a prestadores de serviços e terceirizados da Sabesp que atuam na operação de asfalto.
* **Plano de Mitigação Arquitetural:**
  1. **Isolamento de Dados no Backend (RBAC):** Os endpoints consumidos pela Sabesp (`GET /api/v1/chamados` para perfil Sabesp) **não serializam nem retornam** campos como `nome_cliente`, `cpf_cliente` ou `telefone`.
  2. **Anonimização Visual:** O operador da concessionária enxerga apenas o endereço da ocorrência, a data do despacho e as evidências fotográficas do dano na via pública.

---

### 🏛️ Risco 4 · Resistência à Mudança Cultural na Prefeitura
* **O Problema:** Servidores públicos veteranos da Sepref acostumados ao preenchimento de ofícios e memorandos manuais resistirem ao uso da nova plataforma.
* **Plano de Mitigação Arquitetural:**
  1. **Design Mobile-First Simplificado:** Painel ultraleve com aprovação de chamados em apenas 1 clique.
  2. **Eliminação Real de Burocracia:** O sistema gera automaticamente o texto da intimação formal com número de processo, economizando as cerca de 2 horas diárias que o fiscal gastava preenchendo papel.

---

### 🌊 Risco 5 · Interferência de Maré Alta na Zona Noroeste
* **O Problema:** A Sabesp não conseguir cumprir o prazo legal de 15 dias corridos em obras complexas sobre solo de manguezal e palafitas no Dique da Vila Gilda devido a ressacas marinhas e tábua de marés.
* **Plano de Mitigação Arquitetural:**
  1. **Pausa Justificada de SLA por Força Maior:** O sistema prevê a possibilidade de a concessionária solicitar prorrogação excepcional fundamentada em relatórios meteorológicos/marítimos oficiais da Defesa Civil de Santos.
  2. **Validação da SEMAM:** O prazo só é pausado ou prorrogado após homologação expressa da Secretaria de Meio Ambiente no sistema.

---

### ☁️ Risco 6 · Sobrecarga de Armazenamento e Conexão em Nuvem
* **O Problema:** O envio de múltiplas fotos de alta resolução por centenas de munícipes degradar o desempenho do banco de dados relacional e esgotar a cota de tráfego.
* **Plano de Mitigação Arquitetural:**
  1. **Segregação de Armazenamento:** Imagens nunca são salvas em formato binário (`BLOB`) diretamente nas tabelas relacionais do PostgreSQL. Apenas as URLs seguras geradas no Supabase Storage / CDN são armazenadas.
  2. **Compressão Client-Side:** Validação no frontend com redimensionamento de imagens antes do upload para garantir arquivos leves e carregamento instantâneo.

---

[← Voltar ao Hub Principal](../../README.md)