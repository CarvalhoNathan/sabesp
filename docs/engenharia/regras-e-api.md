[← Voltar ao Hub Principal](../../README.md)

# 🔐 Regras de Negócio, Perfis de Usuário e Especificação de API
### Matriz de controle de acesso (RBAC), catálogo de rotas RESTful e validações de domínio

> 💡 **Em Resumo:** Este documento detalha as fronteiras de autorização do sistema, os três grandes perfis de acesso, as validações de integridade cadastral e a especificação da API RESTful desenvolvida com Spring Boot 3.

---

## 📌 Navegação Rápida
- [Perfis de Usuário e Suas Jornadas](#-perfis-de-usuário-e-suas-jornadas)
- [Controle de Acesso Baseado em Perfis (RBAC)](#-controle-de-acesso-baseado-em-perfis-rbac)
- [Validações de Domínio](#-validações-de-domínio)
- [Catálogo de Endpoints da API REST](#-catálogo-de-endpoints-da-api-rest)
- [Documentação Interativa (Swagger UI)](#-documentação-interativa-swagger-ui)

---

## 👥 Perfis de Usuário e Suas Jornadas

### 📱 Munícipe (Cidadão)
* **Autenticação:** Cadastro simplificado e login com `usuario_cliente` e `senha_cliente`.
* **Abertura de Ocorrência:**
  * Endereço completo com suporte a geolocalização e seleção da Região Administrativa;
  * Classificação técnica padronizada do problema (alinhada ao PMISB);
  * Descrição detalhada;
  * Registro automático de data e hora;
  * Upload de imagem comprobatória (evidência fotográfica).
* **Acompanhamento Transparente:** Consulta em tempo real do status da solicitação (`PENDENTE` ➔ `APROVADO` ➔ `EM_EXECUCAO` ➔ `CONCLUIDO`), histórico de ocorrências abertas pelo próprio munícipe e laudos técnicos registrados pela concessionária.

### 🏛️ Prefeitura (Sepref / Semam / Seserp - Gestão e Fiscalização)
* **Visão Panorâmica:** Acompanhamento centralizado de todas as demandas das 5 Zonas de Santos.
* **Filtros Avançados:** Gestão e triagem por status, tipo de chamado, prefeitura regional (Sepref), criticidade (Seserp / Urgência Urbana) e período cronológico.
* **Triagem Técnica:** Aprovação oficial (emissão automática de intimação/ordem de serviço para a Sabesp) ou rejeição formal com justificativa técnica registrada.
* **Monitoramento de Prazos Legais:** Acompanhamento do prazo de 15 dias previsto na Lei Complementar Municipal nº 852/2014 para recomposição viária após notificação formal, subsidiando a atuação fiscalizatória nos termos das normas aplicáveis.

### 💧 Sabesp (Operação Técnica em Campo)
* **Visualização Restrita:** Acesso exclusivo a demandas já validadas e aprovadas pela Prefeitura.
* **Gestão de Campo:** Atualização do status para `EM_EXECUCAO` e conclusão para `CONCLUIDO`.
* **Comprovação dos Serviços:** Inserção de laudos técnicos e fotos da via recomposta na esteira de `ComentarioChamado`.

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
* **Classificação Padronizada de Ocorrências (PMISB):**
  * *Vazamento de Esgoto na Via Pública*
  * *Refluxo de Esgoto em Ramal Predial*
  * *Rompimento de Tubulação / Coletor Tronco*
  * *Tampa de Poço de Visita (Bueiro) Danificada ou Rebaixada*
* **Upload Seguro de Evidências:** Aceite restrito a extensões seguras (PNG, JPG, JPEG) com payload máximo de até 5MB por arquivo.

---

## 🔌 Catálogo de Endpoints da API REST

A API foi projetada segundo as melhores práticas RESTful, utilizando verbos HTTP padronizados:

| Método | Endpoint | Perfil Autorizado | Finalidade |
| :---: | :--- | :---: | :--- |
| `POST` | `/api/v1/auth/login` | Público | Autenticação unificada de munícipes e funcionários |
| `POST` | `/api/v1/clientes` | Público | Cadastro inicial de novos munícipes |
| `GET` | `/api/v1/chamados` | Todos (Filtrado) | Listagem paginada de chamados conforme papel RBAC |
| `POST` | `/api/v1/chamados` | Munícipe | Abertura de novo chamado de saneamento |
| `GET` | `/api/v1/chamados/{id}` | Todos (Validado) | Consulta detalhada do chamado e anexos |
| `PATCH` | `/api/v1/chamados/{id}/triagem` | Prefeitura | Despacho técnico: `APROVADO` ou `REJEITADO` |
| `PATCH` | `/api/v1/chamados/{id}/status` | Sabesp | Transição de execução: `EM_EXECUCAO` ou `CONCLUIDO` |
| `POST` | `/api/v1/chamados/{id}/comentarios` | Prefeitura / Sabesp | Inserção de laudo técnico, despacho ou foto de reparo |
| `GET` | `/api/v1/relatorios/auditoria` | Prefeitura | Exportação de indicadores de SLA para CMSBS / FMSBA |

---

## 📖 Documentação Interativa (Swagger UI)

A API conta com especificação OpenAPI 3.0 integrada. Ao inicializar o backend Spring Boot, a documentação interativa e os testes de payload em tempo real ficam disponíveis em:

```text
http://localhost:8080/swagger-ui.html
http://localhost:8080/v3/api-docs
```

---

[← Voltar ao Hub Principal](../../README.md)