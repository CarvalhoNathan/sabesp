[← Voltar ao Hub Principal](../README.md)

# 👥 Personas e Cenários de Uso (Design Thinking)
### Perfis humanizados, dores reais e fluxos de interação com o ecossistema

> 💡 **Em Resumo:** Este documento fundamenta a concepção do produto sob a ótica de **Design Thinking**, definindo arquétipos reais de usuários (Munícipe, Fiscal Municipal, Operador Concessionário e Gestora Ambiental) para direcionar as decisões de interface e regras de negócio do software.

---

## 📌 Navegação Rápida
- [Persona 1 · A Munícipe (Dona Helena Silveira)](#-persona-1--a-munícipe-dona-helena-silveira)
- [Persona 2 · O Fiscal Municipal da Sepref (Carlos Eduardo Mendes)](#-persona-2--o-fiscal-municipal-da-sepref-carlos-eduardo-mendes)
- [Persona 3 · O Encarregado de Campo da Sabesp (Roberto Almeida)](#-persona-3--o-encarregado-de-campo-da-sabesp-roberto-almeida)
- [Persona 4 · A Gestora de Políticas Ambientais (Dra. Renata Vasconcelos)](#-persona-4--a-gestora-de-políticas-ambientais-dra-renata-vasconcelos)
- [Matriz de Necessidades vs. Soluções Entregues](#-matriz-de-necessidades-vs-soluções-entregues)

---

## 👵 Persona 1 · A Munícipe (Dona Helena Silveira)

```text
Dona Helena Silveira · 62 anos
Comerciante e moradora no Boqueirão (Santos/SP)
Nível de Letramento Digital: Básico (usa WhatsApp e redes sociais no smartphone)
```

### 👤 Perfil & Contexto
Helena é proprietária de uma cafeteria de rua próxima à Avenida Conselheiro Nébias. Ela preza pela limpeza e circulação de pedestres na calçada em frente ao seu comércio. No ano passado, presenciou o asfalto ceder na Rua Goiás e vive com receio de acidentes similares.

### 😣 Dores & Frustrações
* **Burocracia do 162:** Já tentou ligar na Ouvidoria Municipal, mas ficou 18 minutos esperando atendimento e recebeu um número de protocolo longo que perdeu dias depois.
* **Falta de Retorno:** Quando ligou para a Sabesp, foi informada de que "a equipe iria verificar", mas ninguém apareceu e o vazamento de esgoto continuou espalhando mau cheiro por três semanas, espantando clientes.
* **Linguagem Difícil:** Não sabe termos técnicos como *"Poço de Visita"* ou *"Coletor Tronco"*; só sabe que a rua está afundando com água preta borbulhando.

### 🌟 Cenário de Uso no Sistema
1. **Identificação do Problema:** Às 07h30, ao abrir a cafeteria, Helena nota que uma poça com forte odor de esgoto começou a minar sob o pavimento da rua.
2. **Abertura em 3 Passos:** Pelo celular, acessa a plataforma, tira 2 fotos diretamente da câmera, digita seu endereço (preenchido com auxílio do GPS) e responde a 3 perguntas simples do quiz de triagem.
3. **Paz de Espírito:** Recebe confirmação imediata com protocolo curto e o prazo legal de 15 dias corridos na tela, visualizando o status em tempo real sem precisar ligar para ninguém.

---

## 👷‍♂️ Persona 2 · O Fiscal Municipal da Sepref (Carlos Eduardo Mendes)

```text
Carlos Eduardo Mendes · 39 anos
Engenheiro Civil e Fiscal Técnico da Prefeitura Regional da Orla/Intermediária (Sepref)
Nível de Letramento Digital: Avançado (usa tablets corporativos, GIS e planilhas)
```

### 👤 Perfil & Contexto
Carlos realiza rondas diárias de vistoria pelas ruas de Santos. Ele é o responsável direto por emitir parte das mais de **190 intimações mensais** que a Prefeitura envia às concessionárias de serviços públicos para reparo de pavimentação.

### 😣 Dores & Frustrações
* **Retrabalho em Papel:** Perde até 2 horas do seu expediente no escritório preenchendo ofícios impressos e planilhas para formalizar cobranças à Sabesp.
* **Chamados Inválidos:** Mais de 30% das reclamações que chegam via ouvidoria são trotes, fotos de internet ou água de chuva da galeria pluvial que deveriam ser tratadas por outra secretaria.
* **Dificuldade de Cobrar Multas:** Sem um registro automatizado do dia exato da notificação, a concessionária alega que o ofício demorou para chegar, dificultando a aplicação da multa da Lei nº 852/2014.

### 🌟 Cenário de Uso no Sistema
1. **Triagem Ágil:** Durante a ronda matinal, Carlos abre o painel municipal no tablet e filtra os chamados da Zona da Orla com status `PENDENTE`.
2. **Validação Técnica:** Analisa as fotos enviadas por Helena. Constata pelo quiz e pela imagem que se trata de esgoto rompido na via.
3. **Despacho em 1 Clique:** Com um toque no botão "Aprovar e Intimar", o sistema despacha a OS formal para a Sabesp e dispara a contagem regressiva irrevogável dos **15 dias de SLA**.

---

## 🛠️ Persona 3 · O Encarregado de Campo da Sabesp (Roberto Almeida)

```text
Roberto Almeida · 46 anos
Encarregado de Manutenção de Redes · Sabesp (Unidade Baixada Santista)
Nível de Letramento Digital: Intermediário (usa app de ordens de serviço e GPS)
```

### 👤 Perfil & Contexto
Roberto lidera uma equipe operacional com caminhão combinado (hidrojato/sucção) e retroescavadeira. Sua missão é localizar o ponto exato da obstrução ou colapso da tubulação centenária, realizar o reparo e refazer o asfalto.

### 😣 Dores & Frustrações
* **Endereços Imprecisos:** Muitas notificações tradicionais vinham apenas com "Rua Goiás", sem número aproximado ou foto do ponto exato, obrigando a equipe a rodar a rua inteira procurando o problema.
* **Demandas Desnecessárias:** Deslocar caminhão pesado para chegar no local e descobrir que a água era de calha residencial de chuva gera desperdício de diesel e tempo de equipe.
* **Risco de Penalidade Injusta:** Se a obra for concluída mas o papel demorar a ser protocolado no Paço Municipal, a concessionária corre o risco de tomar multa de R$ 17 mil.

### 🌟 Cenário de Uso no Sistema
1. **Fila Limpa:** Roberto acessa o painel operacional da Sabesp e visualiza apenas ordens já pré-validadas tecnicamente pelo fiscal municipal, com foto nítida e geolocalização exata.
2. **Execução:** Ao chegar no Boqueirão, altera o status para `EM_EXECUCAO`. A equipe isola a via, faz a sucção e substitui o tubo de manilha danificado.
3. **Fechamento Auditado:** Após a recomposição do pavimento asfáltico, Roberto tira uma foto da via limpa e finalizada, digita o parecer técnico e altera para `CONCLUIDO`. O munícipe é notificado na hora e o prazo de SLA é congelado com fé pública.

---

## 📊 Persona 4 · A Gestora de Políticas Ambientais (Dra. Renata Vasconcelos)

```text
Dra. Renata Vasconcelos · 43 anos
Diretora do DEPCAM / SEMAM e Membro do Conselho Gestor do FMSBA
Nível de Letramento Digital: Avançado (BI, relatórios governamentais e compliance)
```

### 👤 Perfil & Contexto
Renata monitora a qualidade ambiental da cidade e a balneabilidade das praias de Santos. Ela participa das reuniões do **CMSBS (Conselho Municipal de Saneamento Básico)** e do **Conselho Gestor do FMSBA (Fundo Municipal de Saneamento Básico e Ambiental - Lei 4.525/2024)**.

### 😣 Dores & Frustrações
* **Prestação de Contas Difusa:** A Lei nº 4.525/2024 exige prestação de contas bimestral ao CMSBS, mas consolidar dados de notificações e multas espalhados em papéis consome semanas de trabalho.
* **Falta de Indicadores Espaciais:** Dificuldade em identificar se os vazamentos recorrentes estão concentrados em um coletor antigo específico ou nas palafitas da Zona Noroeste (Vila Gilda).

### 🌟 Cenário de Uso no Sistema
1. **Relatório Gerencial:** Antes da reunião do conselho, Renata clica em "Relatórios de Auditoria" e extrai o índice de cumprimento de SLA da Sabesp por zona administrativa.
2. **Controle Financeiro do Fundo:** O sistema lista todas as ocorrências que ultrapassaram 15 dias, gerando a relação exata de autos de infração que devem ser cobrados da concessionária e revertidos para o **FMSBA** financiar novas ligações de esgoto em comunidades vulneráveis.

---

## 🎯 Matriz de Necessidades vs. Soluções Entregues

| Ator | Principal Necessidade | Solução Arquitetural no Sistema |
| :--- | :--- | :--- |
| **Helena (Munícipe)** | Simplicidade extrema e acompanhamento sem burocracia. | Interface Mobile-First, quiz visual intuitivo e linha do tempo de status (sem jargões técnicos). |
| **Carlos (Fiscal Sepref)** | Eliminar papel e ter base probatória para autuar atrasos. | Painel integrado de triagem com geolocalização e carimbo temporal imutável para contagem de 15 dias. |
| **Roberto (Sabesp)** | Informação técnica precisa e comprovação ágil de entrega. | Fila restrita a ordens validadas com upload obrigatório de foto final do asfalto recomposto. |
| **Renata (SEMAM/FMSBA)** | Relatórios auditáveis para o CMSBS e conformidade legal. | Módulo de relatórios automatizados (RF13) com cálculo de multas da Lei 852 e destinação ao fundo. |

---

[← Voltar ao Hub Principal](../README.md)