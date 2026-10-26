# 🔐 Regras de Negócio, Perfis e API REST

Este documento detalha o controle de acesso baseado em papéis (*Role-Based Access Control - RBAC*), as regras de negócio do sistema e o catálogo de endpoints da API REST.

---

## 📌 Índice
- [Funcionalidades por Perfil](#-funcionalidades-por-perfil)
- [Controle de Acesso Baseado em Perfis (RBAC)](#-controle-de-acesso-baseado-em-perfis-rbac)
- [Validações de Domínio](#-validações-de-domínio)
- [Catálogo de Endpoints da API REST](#-catálogo-de-endpoints-da-api-rest)

---

## 👥 Funcionalidades por Perfil

### 📱 Munícipe (Pessoa Comum)
- Cadastro e login seguro na plataforma (`usuario_cliente` / `senha_cliente`).
- Abertura de chamados com os seguintes dados:
  - Endereço completo da ocorrência;
  - Tipo do problema (classificação técnica);
  - Descrição textual detalhada;
  - Registro de data e hora;
  - Upload de imagem comprobatória (evidência fotográfica).
- Acompanhamento em tempo real do status da solicitação e consulta ao histórico de solicitações próprias.

### 🏛️ Prefeitura / Semam (Gestão e Fiscalização)
- Acompanhamento panorâmico de todas as demandas abertas no município de Santos.
- **Filtros avançados** para gestão e triagem (por status, tipo de chamado, endereço/bairro e período).
- Validação técnica da solicitação (aprovação ou rejeição formal com justificativa).
- Emissão oficial de requisições de serviço para a Sabesp e fiscalização dos prazos da concessionária.

### 💧 Sabesp (Operação e Execução Técnica)
- Visualização exclusiva de chamados já aprovados e despachados pela prefeitura.
- Gestão do fluxo operacional: transição de status para `EM_EXECUCAO` e `CONCLUIDO`.
- Registro de comentários técnicos e histórico de andamento (`ComentarioChamado`) com evidências fotográficas da obra finalizada.

---

## 🔐 Controle de Acesso Baseado em Perfis (RBAC)

Para garantir a privacidade dos munícipes, a integridade da fiscalização pública e a segurança operacional, o backend implementa as seguintes diretrizes de segurança:

1. **Isolamento de Dados do Munícipe:**
   - O munícipe autenticado possui permissão de leitura exclusivamente sobre os chamados abertos por ele mesmo (`id_cliente = usuario_autenticado`).
   - Não possui visibilidade sobre chamados ou dados pessoais de outros munícipes.

2. **Poder Fiscalizador da Prefeitura (`orgao_funcionario = 'prefeitura'`):**
   - Acesso irrestrito a todos os chamados registrados no município.
   - Permissão exclusiva para aprovação (despacho oficial para a Sabesp) ou rejeição de chamados com justificativa técnica.
   - Acesso aos relatórios gerenciais e filtros por zonas administrativas de Santos.

3. **Operação Restrita da Sabesp (`orgao_funcionario = 'sabesp'`):**
   - Visualização restrita apenas a chamados com status `APROVADO`. Chamados rejeitados ou ainda pendentes de triagem não são visíveis para a concessionária.
   - Permissão para alterar o status para `EM_EXECUCAO` e `CONCLUIDO`.
   - Permissão para registrar laudos e pareceres em `ComentarioChamado`.

---

## ⚙️ Validações de Domínio

* **Validação de CPF:** Verificação de integridade algorítmica para munícipes e funcionários.
* **Classificação Padronizada de Ocorrências:**
  * *Vazamento de Esgoto na Via Pública*
  * *Refluxo de Esgoto em Ramal Predial*
  * *Rompimento de Tubulação / Coletor Tronco*
  * *Tampa de Poço de Visita (Bueiro) Danificada ou Faltante*
* **Upload Seguro de Imagens:** Limitação a formatos de imagem reconhecidos (PNG, JPG, JPEG) com controle rigoroso de tamanho de payload (máximo 5MB por arquivo).

---

## 🔌 Catálogo de Endpoints da API REST

A documentação interativa com Swagger UI pode ser acessada em:
```
http://localhost:8080/swagger-ui.html
```

| Método  | Rota                             | Descrição                                              | Acesso Permitido       |
| :------ | :------------------------------- | :----------------------------------------------------- | :--------------------- |
| `POST`  | `/api/chamados`                  | Cadastro de novo chamado com upload de evidência       | Munícipe               |
| `GET`   | `/api/chamados`                  | Listagem paginada e filtrada de chamados               | Autenticado            |
| `GET`   | `/api/chamados/{id}`             | Detalhes completos do chamado e histórico de pareceres | Autenticado            |
| `PATCH` | `/api/chamados/{id}/status`      | Alteração do status do chamado (Triagem ou Obra)       | Prefeitura / Sabesp    |
| `POST`  | `/api/chamados/{id}/comentarios` | Inserção de parecer ou comentário técnico              | Prefeitura / Sabesp    |
| `GET`   | `/api/logs`                      | Consulta da trilha de auditoria do sistema             | Prefeitura (Auditoria) |
