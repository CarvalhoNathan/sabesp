# 🤝 Guia de Contribuição e Padrões de Desenvolvimento

Este documento estabelece as diretrizes de colaboração, padronização de código, fluxo de branches e convenção de commits adotados pela equipe de desenvolvimento (**Nathan Carvalho** & **Andrey Celestino**).

---

## 📌 Índice
- [Fluxo de Branches (Git Flow Simplificado)](#-fluxo-de-branches)
- [Padrão de Mensagens de Commit (Conventional Commits)](#-padrão-de-mensagens-de-commit)
- [Diretrizes de Segurança e Boas Práticas](#-diretrizes-de-segurança-e-boas-práticas)
- [Checklist de Pull Request](#-checklist-de-pull-request)

---

## 🌿 Fluxo de Branches

Para manter a estabilidade do repositório e evitar conflitos de código durante o desenvolvimento simultâneo de telas e backend, utilizamos branches temáticas:

| Tipo de Branch | Padrão de Nomenclatura | Exemplo |
| :--- | :--- | :--- |
| **Branch Principal** | `main` | Código estável, homologado e documentado |
| **Novas Funcionalidades** | `feature/<escopo>` | `feature/schema-supabase`, `feature/auth-jwt` |
| **Correções de Bugs** | `fix/<problema>` | `fix/validacao-cpf`, `fix/soft-delete-query` |
| **Documentação & Design** | `docs/<assunto>` | `docs/atualiza-arquitetura`, `docs/guia-api` |

### Ciclo de Vida de uma Tarefa:
1. Atualize sua branch local com a principal:
   ```bash
   git checkout main
   git pull origin main
   ```
2. Crie uma branch para a sua tarefa:
   ```bash
   git checkout -b feature/minha-tarefa
   ```
3. Realize os commits seguindo o padrão abaixo e envie para o GitHub:
   ```bash
   git push origin feature/minha-tarefa
   ```

---

## ✍️ Padrão de Mensagens de Commit

Adotamos a especificação de **[Conventional Commits](https://www.conventionalcommits.org/)** para manter o histórico de alterações legível e automatizável:

| Prefixo | Significado | Exemplo Prático |
| :--- | :--- | :--- |
| `feat:` | Nova funcionalidade ou recurso | `feat: adiciona endpoint de criacao de chamados` |
| `fix:` | Correção de defeito ou bug | `fix: corrige calculo do prazo de 15 dias da lei 852` |
| `docs:` | Alterações exclusivamente em documentação | `docs: atualiza modelo ER no arquivo arquitetura-e-banco` |
| `style:` | Formatação, espaçamento ou ajustes visuais sem alteração de lógica | `style: padroniza estilizacao dos cards no figma` |
| `refactor:`| Refatoração de código sem mudar comportamento | `refactor: desacopla regras de negocio para camada de service` |
| `chore:` | Tarefas de configuração, dependências ou assets | `chore: adiciona logos svg oficiais da prefeitura` |

---

## 🔒 Diretrizes de Segurança e Boas Práticas

1. **Credenciais e Segredos:**
   * **Nunca** commite senhas de banco de dados, chaves de API ou tokens JWT no repositório.
   * Mantenha as configurações locais no `application.properties` e compartilhe apenas modelos vazios (`application.properties.example`).

2. **Alinhamento com a Documentação:**
   * Qualquer alteração em nomes de tabelas ou colunas deve ser previamente refletida no documento [docs/arquitetura-e-banco.md](docs/engenharia/arquitetura-e-banco.md).
   * Novas rotas REST devem ser documentadas em [docs/regras-e-api.md](docs/engenharia/regras-e-api.md).

3. **Imutabilidade e Soft Delete:**
   * Todas as consultas e operações de exclusão em `Cliente`, `Funcionario`, `Chamado` e `ComentarioChamado` devem respeitar a exclusão lógica (`excluido = true`).
   * A tabela `Logs` opera exclusivamente como *append-only* (somente inserções).

---

## ✅ Checklist de Pull Request / Merge

Antes de integrar alterações na branch `main`, certifique-se de que:
- [ ] O código compila sem erros via `mvn clean compile`.
- [ ] Não há arquivos temporários da IDE (`.idea/`, `target/`, `.vscode/`) sendo enviados.
- [ ] As mensagens de commit seguem o padrão `tipo: descricao`.
- [ ] O colega de equipe foi informado da mesclagem para sincronizar o ambiente local.
