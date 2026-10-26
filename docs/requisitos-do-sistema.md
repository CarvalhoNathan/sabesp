[← Voltar ao Hub Principal](../README.md)

# 📋 Especificação de Requisitos e Regras de Negócio
### Matriz formal de Requisitos Funcionais (RF), Não-Funcionais (RNF) e Regras de Domínio

> 💡 **Em Resumo:** Este documento formaliza a engenharia de requisitos do software sob o rigor metodológico de Engenharia de Software para o TCC, alinhando as necessidades dos munícipes, os protocolos do **PMISB (Relatório R4 / Ações de Emergência)**, os prazos legais da **Lei Complementar nº 852/2014** e o controle social do **CMSBS / FMSBA (Lei nº 4.525/2024)**.

---

## 📌 Navegação Rápida
- [Metodologia de Priorização (MoSCoW)](#-metodologia-de-priorização-moscow)
- [Requisitos Funcionais (RF)](#-requisitos-funcionais-rf)
- [Requisitos Não-Funcionais (RNF)](#-requisitos-não-funcionais-rnf)
- [Regras de Negócio de Domínio (RN)](#-regras-de-negócio-de-domínio-rn)
- [Classificação Técnica das Ocorrências (PMISB)](#-classificação-técnica-das-ocorrências-pmisb)

---

## 🎯 Metodologia de Priorização (MoSCoW)

Os requisitos são classificados conforme a metodologia internacional **MoSCoW**:
* **Must Have (Essencial):** Obrigatório para o funcionamento do MVP e conformidade legal.
* **Should Have (Importante):** Relevante para otimização da esteira operacional e relatórios governamentais.
* **Could Have (Desejável):** Recurso de valor agregado para etapas futuras.

---

## ⚙️ Requisitos Funcionais (RF)

| ID | Nome do Requisito | Descrição Detalhada | Ator Primário | Prioridade |
| :---: | :--- | :--- | :---: | :---: |
| **RF01** | Autenticação e Cadastro | Permitir cadastro e login seguro de munícipes com validação de CPF e senha criptografada. | Munícipe | **Must** |
| **RF02** | Abertura de Chamado | Permitir que o cidadão registre uma ocorrência informando endereço, tipo do problema, descrição e data/hora. | Munícipe | **Must** |
| **RF03** | Upload de Evidência Fotográfica | Permitir envio de fotos (PNG, JPG, JPEG até 5MB) para comprovação visual do vazamento ou dano na via pública. | Munícipe | **Must** |
| **RF04** | Acompanhamento de Chamados | Exibir ao munícipe a esteira de evolução do chamado em tempo real (`PENDENTE` ➔ `APROVADO` ➔ `EM_EXECUCAO` ➔ `CONCLUIDO`). | Munícipe | **Must** |
| **RF05** | Painel Geral de Fiscalização | Disponibilizar dashboard para a gestão municipal com métricas de chamados abertos, em triagem e concluídos. | Prefeitura (SEMAM) | **Must** |
| **RF06** | Filtros Regionais (5 Zonas de Santos) | Permitir filtrar chamados por Zona (*Orla, Centro, Zona Noroeste, Morros, Continental*), status e período. | Prefeitura (SEMAM) | **Must** |
| **RF07** | Triagem Técnica de Solicitações | Permitir ao fiscal da Prefeitura aprovar (despachar para Sabesp) ou rejeitar chamados com justificativa obrigatória. | Prefeitura (SEMAM) | **Must** |
| **RF08** | Fila Operacional da Concessionária | Exibir para os operadores da Sabesp exclusivamente os chamados já validados e com status `APROVADO`. | Sabesp | **Must** |
| **RF09** | Gestão de Status Operacional | Permitir à Sabesp transicionar o chamado para `EM_EXECUCAO` e posteriormente para `CONCLUIDO`. | Sabesp | **Must** |
| **RF10** | Registro de Pareceres Técnicos | Permitir inserção de comentários e laudos técnicos (`ComentarioChamado`) com fotos comprobatórias do reparo finalizado. | Sabesp / Prefeitura | **Must** |
| **RF11** | Auditoria e Contagem de SLA (Lei 852/2014) | Calcular automaticamente os dias decorridos a partir da aprovação da Prefeitura, alertando prazos próximos de 15 dias. | Sistema | **Must** |
| **RF12** | Trilha Imutável de Auditoria | Registrar em log indelével toda alteração de status, inserção de parecer e dados de auditoria (*quem, quando e o que alterou*). | Sistema | **Must** |
| **RF13** | Relatórios de Auditoria (CMSBS / FMSBA) | Gerar relatórios consolidados com taxa de cumprimento de SLA, reincidência por região e autuações potenciais para subsidiar o Conselho Municipal de Saneamento (CMSBS) e o FMSBA. | Prefeitura (SEMAM) | **Should** |

---

## 🛡️ Requisitos Não-Funcionais (RNF)

| ID | Categoria | Descrição do Requisito Não-Funcional | Métrica / Critério |
| :---: | :--- | :--- | :--- |
| **RNF01** | **Segurança & Privacidade** | Conformidade com a LGPD (Lei 13.709/2018). Dados pessoais e CPF de munícipes não devem ser expostos publicamente. | Mascaramento de dados e isolamento multilocatário |
| **RNF02** | **Integridade de Dados** | Adoção do padrão *Soft Delete* (`excluido: Boolean, default false`) em todas as entidades cadastrais para garantir histórico público. | Nenhuma execução de `DELETE FROM` em dados de negócio |
| **RNF03** | **Imutabilidade** | A tabela `Logs` opera estritamente no modelo *Append-Only*, sem suporte a exclusão lógica ou física. | Permissões exclusivas de `INSERT` e `SELECT` |
| **RNF04** | **Usabilidade & Acessibilidade** | Interface intuitiva projetada sob o conceito *Mobile-First*, com tipografia oficial `Montserrat` e contraste acessível (WCAG AA). | Conformidade visual com o Design System oficial |
| **RNF05** | **Controle de Acesso (RBAC)** | Restrição rígida de privilégios de endpoints baseada em papéis (*Munícipe*, *Operador Prefeitura* e *Operador Sabesp*). | Bloqueio de rotas não autorizadas com HTTP 403 Forbidden |
| **RNF06** | **Desempenho** | Tempo de resposta para consultas paginadas e operações de leitura na API REST inferior a 250 milissegundos. | Latência < 250ms sob carga padrão |
| **RNF07** | **Disponibilidade** | Arquitetura desacoplada com persistência relacional em nuvem (Supabase / PostgreSQL) com alta tolerância a falhas. | Uptime estimado de 99.5% |

---

## ⚖️ Regras de Negócio de Domínio (RN)

> 🔒 **RN01 · Isolamento de Consulta do Munícipe:**  
> O munícipe autenticado só tem permissão para visualizar e listar os chamados originados por seu próprio cadastro (`id_cliente = usuario_logado`).

> 📝 **RN02 · Justificativa Obrigatória em Rejeições:**  
> A Prefeitura não pode recusar um chamado de munícipe sem preencher formalmente o campo de justificativa técnica (ex: *fora de escopo, duplicidade, endereço inexistente*), que ficará registrado no histórico da solicitação.

> ⏱️ **RN03 · Início do SLA da Lei Complementar nº 852/2014:**  
> A contagem do prazo legal de **15 dias corridos** inicia-se no exato timestamp em que o status transiciona de `PENDENTE` para `APROVADO`.
> * **SLA Verde (< 7 dias):** Dentro da normalidade operacional.
> * **SLA Amarelo (8 a 12 dias):** Alerta de atenção para despacho de equipe.
> * **SLA Vermelho (13 a 15 dias):** Risco iminente de autuação.
> * **SLA Roxo (> 15 dias):** Prazo legal estourado / Base para aplicação de multa de R$ 17.045,05 destinada ao FMSBA.

> 📸 **RN04 · Comprovação Obrigatória de Conclusão:**  
> Para alterar o status de `EM_EXECUCAO` para `CONCLUIDO`, a Sabesp deve obrigatoriamente anexar um parecer técnico de encerramento e evidência fotográfica da intervenção realizada na via.

> 🚫 **RN05 · Invisibilidade de Demandas não Triadas:**  
> A Sabesp não tem acesso a chamados pendentes de análise ou rejeitados pela Prefeitura, assegurando a prerrogativa fiscalizatória exclusiva da SEMAM.

---

## 🏷️ Classificação Técnica das Ocorrências (PMISB)

Em estrita conformidade com o **Plano Municipal Integrado de Saneamento Básico de Santos (Relatório R4, Pág. 85)**, os chamados são classificados nas seguintes tipologias técnicas padronizadas:

```text
Classificação de Ocorrências (PMISB · Relatório R4)
├── 1. ROMPIMENTO_TUBULACAO      → Rompimento de coletor tronco ou rede por erosão/talude
├── 2. EXTRAVASAMENTO_VIA        → Vazamento e extravasamento de esgoto bruto em via pública
├── 3. EXTRAVASAMENTO_ELEVATORIA → Transbordamento decorrente de falha eletromecânica em bomba
└── 4. POCO_VISITA_DANIFICADO    → Abatimento asfáltico, tampa de PV quebrada ou rebaixada (Lei 852)
```

---

[← Voltar ao Hub Principal](../README.md)