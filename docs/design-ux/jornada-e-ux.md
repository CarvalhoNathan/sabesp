[← Voltar ao Hub Principal](../../README.md)

# 🧭 Arquitetura de Informação, Telas e Jornada do Usuário (UX)
### Mapa do ecossistema, wireframe textual e fluxos de navegação Mobile-First

> 💡 **Em Resumo:** Este documento detalha a arquitetura de telas e a experiência do usuário (UX) para os três grandes módulos da aplicação: **Munícipe**, **Fiscalização Municipal (Prefeitura)** e **Operação de Campo (Sabesp)**, orientando o desenvolvimento visual no Figma e no Frontend.

---

## 📌 Navegação Rápida
- [Sitemap Geral e Arquitetura de Informação](#-sitemap-geral-e-arquitetura-de-informação)
- [Jornada do Munícipe (Mobile-First)](#-jornada-do-munícipe-mobile-first)
- [Jornada da Prefeitura (Painel de Gestão e Triagem)](#-jornada-da-prefeitura-painel-de-gestão-e-triagem)
- [Jornada da Concessionária (Fila Operacional Sabesp)](#-jornada-da-concessionária-fila-operacional-sabesp)
- [Design dos Estados e Linha do Tempo (Stepper)](#-design-dos-estados-e-linha-do-tempo-stepper)

---

## 🗺️ Sitemap Geral e Arquitetura de Informação

```text
Plataforma Sabesp & Prefeitura de Santos
├── 1. Módulo Público / Munícipe (Mobile-First)
│   ├── Home / Boas-vindas
│   ├── Autenticação (Login / Cadastro de Cidadão com CPF)
│   ├── Novo Chamado (Fluxo Guiado em 3 Passos)
│   │   ├── Passo 1: Localização & Bairro (GPS integrado)
│   │   ├── Passo 2: Triagem Inteligente (Esgoto vs. Chuva)
│   │   └── Passo 3: Fotos & Descrição
│   ├── Confirmação com Protocolo e SLA de 15 Dias
│   ├── Meus Chamados (Listagem com Badges de Status)
│   └── Detalhes do Chamado (Timeline com Pareceres e Fotos de Reparo)
│
├── 2. Módulo Prefeitura (Sepref / Semam / Seserp)
│   ├── Dashboard Executivo (KPIs de SLA, Chamados por Zona, Multas Potenciais)
│   ├── Mapa de Calor das 5 Zonas de Santos
│   ├── Fila de Triagem Técnica (Chamados 'PENDENTE')
│   │   ├── Modal de Aprovação (Emissão de Intimação Formal à Sabesp)
│   │   └── Modal de Rejeição (Justificativa Técnica Obrigatória)
│   ├── Fila de Acompanhamento de SLA (Lei Complementar nº 852/2014)
│   └── Relatórios de Auditoria (Exportação consolidada para o CMSBS e FMSBA)
│
└── 3. Módulo Sabesp (Operação Técnica)
    ├── Fila de Ordens de Serviço Validadas ('APROVADO')
    ├── Detalhes Técnicos da Demanda (Fotos originais, histórico, dados do coletor)
    ├── Ação Operacional: Assumir / Iniciar Reparo ('EM_EXECUCAO')
    └── Ação de Encerramento: Conclusão com Laudo e Foto da Via Recomposta ('CONCLUIDO')
```

---

<a id="fluxo-mobile-municipe"></a>
<a id="-fluxo-mobile-do-munícipe-3-passos"></a>
<a id="fluxo-mobile-do-munícipe-3-passos"></a>
## 📱 Jornada do Munícipe (Mobile-First)

Projetada para que qualquer cidadão, como a **Dona Helena (62 anos)**, consiga registrar um problema na rua em menos de 2 minutos:

```mermaid
flowchart LR
    A["Tela 1: Home<br/>Botão 'Reportar Vazamento'"] --> B["Tela 2: Passo 1<br/>Endereço & GPS"]
    B --> C["Tela 3: Passo 2<br/>Triagem Guiada"]
    C --> D["Tela 4: Passo 3<br/>Fotos & Descrição"]
    D --> E["Tela 5: Sucesso<br/>Protocolo & SLA 15 Dias"]
    E --> F["Tela 6: Detalhe<br/>Linha do Tempo ao Vivo"]
```

### Wireframe Textual dos 3 Passos

#### Passo 1 · Onde está ocorrendo o problema?
* Campo de busca de logradouro com autocompletar da base de ruas de Santos.
* Botão *"Usar minha localização atual"* (captura coordenadas de latitude/longitude).
* Seleção automática ou manual da **Zona Administrativa** (*Orla/Intermediária, Centro, Zona Noroeste, Morros, Continental*).

#### Passo 2 · O que está acontecendo?
* Cards visuais interativos da **Triagem Inteligente**:
  * `[ ] Vazamento contínuo com mau cheiro de esgoto`
  * `[ ] Tampa redonda de ferro fundido danificada ou afundada`
  * `[ ] Asfalto afundando / buraco abrindo ao redor da água`
  * `[ ] Refluxo de esgoto voltando pelo ralo da calçada`

#### Passo 3 · Fotos e confirmação
* Área de upload com botão de acesso direto à câmera do smartphone (até 3 fotos).
* Campo opcional de texto para referências (ex: *"Em frente à padaria, perto da esquina"*).
* Botão em destaque: **"Enviar Chamado"**.

---

## 🏛️ Jornada da Prefeitura (Painel de Gestão e Triagem)

Projetada para atender a demanda de **Carlos (Fiscal da Sepref)**, processando com eficiência o volume de mais de 190 intimações mensais:

### 1. Dashboard Principal
* **Cards de Indicadores de SLA:**
  * Chamados Dentro do Prazo (`< 7 dias` · Verde)
  * Chamados em Alerta (`8 a 12 dias` · Amarelo)
  * Chamados em Risco Iminente (`13 a 15 dias` · Vermelho)
  * Chamados Fora do Prazo Legal (`> 15 dias` · Roxo · Passíveis de Multa de R$ 17.045,05)
* **Filtros por Região:** Alternância rápida entre as 5 Prefeituras Regionais de Santos.

### 2. Ação de Triagem Técnica em 1 Clique
Ao clicar em uma ocorrência pendente, o fiscal visualiza:
* A foto enviada pelo munícipe ampliada em alta resolução;
* As respostas dadas no quiz de triagem;
* O histórico de vazamentos anteriores no mesmo trecho da rua;
* **Câmera CCO Mais Próxima (Telefiscalização):** Atalho contextual para a câmera do Centro de Controle Operacional (CCO / SigSantosWeb) mais próxima do cruzamento, permitindo inspecionar o local ao vivo antes de despachar equipes ou intimar a Sabesp;
* **Ações Disponíveis:**
  * **Botão "Aprovar & Intimar Sabesp":** Gera a intimação formal instantânea, dispara e-mail/notificação para a concessionária e inicia o cronômetro do prazo legal de 15 dias.
  * **Botão "Rejeitar":** Abre obrigatoriamente um campo de texto para justificativa técnica (visível ao munícipe) para evitar decisões arbitrárias.

### 3. Auditoria de Presença da Concessionária
* Durante a vigência do chamado em execução (`EM_EXECUCAO`), o fiscal pode utilizar a consulta às câmeras do CCO para verificar a efetiva presença das viaturas operacionais da Sabesp no endereço, eliminando divergências sobre comparecimento ou atraso no início das obras.

---

## 💧 Jornada da Concessionária (Fila Operacional Sabesp)

Focada na praticidade de campo para **Roberto (Encarregado Sabesp)**:

### 1. Fila de Ordens de Serviço (Kanban / Lista Priorizada)
* Exibe somente ordens com status `APROVADO` ou `EM_EXECUCAO`.
* As ordens são ordenadas automaticamente por **criticidade e dias restantes de SLA**, garantindo que as equipes atendam primeiro as ordens próximas de estourar o prazo de 15 dias.

### 2. Fluxo de Execução e Fechamento
1. Ao despachar a equipe para a rua, o operador clica em **"Iniciar Atendimento"** (status transiciona para `EM_EXECUCAO`).
2. Concluído o reparo no coletor e o recapeamento asfáltico da via, o operador acessa o chamado pelo smartphone.
3. **Upload Obrigatório de Encerramento:** Anexa foto da via recomposta e digita breve parecer (ex: *"Substituído ramal cerâmico de 150mm rompido e realizada recomposição asfáltica com CBUQ"*).
4. Clica em **"Concluir Ordem de Serviço"** (status passa para `CONCLUIDO`).

---

<a id="stepper-visual"></a>
<a id="-linha-do-tempo-de-acompanhamento-stepper-visual"></a>
<a id="linha-do-tempo-de-acompanhamento-stepper-visual"></a>
## 🚦 Design dos Estados e Linha do Tempo (Stepper)

A experiência do munícipe é orientada por uma linha do tempo vertical visual que traz transparência absoluta e elimina a necessidade de ligar para a ouvidoria:

```text
Linha do Tempo Visual do Chamado:

[●] 1. CHAMADO RECEBIDO
    26/10/2026 às 08:15 · Registrado pelo munícipe com 2 fotos anexadas.
    │
[●] 2. VISTORIADO & INTIMADO PELA PREFEITURA
    26/10/2026 às 10:40 · Aprovado pelo fiscal Carlos (Sepref - Regional Orla).
    Intimação nº 2026/0892 expedida para a Sabesp.
    ⏱️ Prazo Legal da Lei nº 852/2014: 15 dias corridos (Vencimento: 10/11/2026).
    │
[●] 3. EM EXECUÇÃO PELA SABESP
    28/10/2026 às 14:00 · Equipe operacional de redes iniciou a escavação no local.
    │
[○] 4. REPARO CONCLUÍDO & VIA RECOMPOSTA
    Aguardando laudo técnico e foto final do pavimento.
```

---

[← Voltar ao Hub Principal](../../README.md)