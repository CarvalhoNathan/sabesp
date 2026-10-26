[← Voltar ao Hub Principal](../README.md)

# 🔐 Regras de Negócio, Perfis e API REST
### Especificação de controle de acesso (RBAC), validações e catálogo de endpoints

> 💡 **Em Resumo:** Este documento detalha a política de segurança baseada em papéis (*Role-Based Access Control*), as regras de negócio de triagem municipal e o catálogo de rotas REST com documentação interativa via Swagger UI.

---

## 📌 Navegação Rápida
- [Funcionalidades por Perfil](#-funcionalidades-por-perfil)
- [Controle de Acesso Baseado em Perfis (RBAC)](#-controle-de-acesso-baseado-em-perfis-rbac)
- [Validações de Domínio](#-validações-de-domínio)
- [Catálogo de Endpoints da API REST](#-catálogo-de-endpoints-da-api-rest)

---

## 👥 Funcionalidades por Perfil

### 📱 Munícipe (Cidadão)
* **Autenticação:** Cadastro simplificado e login com `usuario_cliente` e `senha_cliente`.
* **Abertura de Ocorrência:**
  * Endereço completo com suporte a geolocalização;
  * Classificação técnica padronizada do problema;
  * Descrição detalhada;
  * Registro automático de data e hora;
  * Upload de imagem comprobatória (evidência fotográfica).
* **Acompanhamento Transparente:** Consulta em tempo real do status da solicitação e histórico de ocorrências abertas pelo próprio munícipe.

### 🏛️ Prefeitura / Semam (Gestão e Fiscalização)
* **Visão Panorâmica:** Acompanhamento centralizado de todas as demandas do município.
* **Filtros Avançados:** Gestão e triagem por status, tipo de chamado, prefeitura regional (5 Zonas de Santos) e período cronológico.
* **Triagem Técnica:** Aprovação oficial (emissão de requisição de serviço para a Sabesp) ou rejeição formal com justificativa técnica registrada.
* **Fiscalização de SLA:** Auditoria e contagem dos 15 dias de prazo previstos na Lei Complementar nº 852/2014.

### 💧 Sabesp (Operação Técnica em Campo)
* **Visualização Restrita:** Acesso exclusivo a demandas já validadas e aprovadas pela Prefeitura.
* **Gestão de Campo:** Atualização do status para `EM_EXECUCAO` e conclusão para `CONCLUIDO`.
* **Comprovação dos Serviços:** Inserção de laudos técnicos e fotos da obra finalizada na esteira de `ComentarioChamado`.

---

## 🛡️ Controle de Acesso Baseado em Perfis (RBAC)

O backend implementa isolamento de dados e restrição de privilégios para garantir a segurança da informação e a conformidade com a LGPD:

> 🔒 **1. Isolamento de Dados do Cidadão (Munícipe):**  
> O munícipe autenticado possui permissão de leitura exclusivamente sobre os chamados abertos por ele mesmo (`WHERE id_cliente = usuario_autenticado`). Ele não tem visibilidade nem acesso aos dados cadastrais de outros munícipes.

> 🏛️ **2. Prerrogativa Fiscalizatória da Prefeitura (`orgao_funcionario = 'prefeitura'`):**  
> Acesso irrestrito de leitura a todas as demandas do município. Permissão exclusiva para transição de status de triagem (`APROVADO` ou `REJEITADO`).

> 💧 **3. Operação Restrita da Sabesp (`orgao_funcionario = 'sabesp'`):**  
> Visualização restrita a chamados com status `APROVADO`. Chamados rejeitados ou ainda pendentes de triagem pela Prefeitura não aparecem na esteira da concessionária. Permissão de escrita restrita a comentários técnicos e transições operacionais.

---

## ⚙️ Validações de Domínio

* **Validação de CPF:** Verificação de integridade de dígitos verificadores para munícipes e funcionários.
* **Classificação Padronizada de Ocorrências:**
  * *Vazamento de Esgoto na Via Pública*
  * *Refluxo de Esgoto em Ramal Predial*
  * *Rompimento de Tubulação / Coletor Tronco*
  * *Tampa de Poço de Visita (Bueiro) Danificada ou Faltante*
* **Upload Seguro de Evidências:** Aceite restrito a extensões seguras (PNG, JPG, JPEG) com payload máximo de até 5MB por arquivo.

---

## 🔌 Catálogo de Endpoints da API REST

A interface interativa do **Swagger UI / OpenAPI** pode ser acessada em ambiente local através de:
```text
http://localhost:8080/swagger-ui.html
```

| Método | Rota | Descrição | Acesso Autorizado |
| :---: | :--- | :--- | :--- |
| `POST` | `/api/chamados` | Cadastro de nova solicitação com evidência fotográfica | Munícipe |
| `GET` | `/api/chamados` | Listagem paginada com filtros por zona e status | Autenticado |
| `GET` | `/api/chamados/{id}` | Consulta detalhada do chamado e histórico de pareceres | Autenticado |
| `PATCH` | `/api/chamados/{id}/status` | Transição de status do chamado (Triagem ou Execução) | Prefeitura / Sabesp |
| `POST` | `/api/chamados/{id}/comentarios` | Inserção de laudo técnico ou atualização de andamento | Prefeitura / Sabesp |
| `GET` | `/api/logs` | Consulta detalhada da trilha imutável de auditoria | Prefeitura (Fiscalização) |

---

[← Voltar ao Hub Principal](../README.md)
