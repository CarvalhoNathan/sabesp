[← Voltar ao README Principal](README.md)

# 🤝 Guia de Contribuição e Padrões de Desenvolvimento

Este documento estabelece as diretrizes de colaboração, padronização de código, fluxo de branches e convenção de commits adotados no projeto de Trabalho de Conclusão de Curso (TCC) em **Tecnologia em Sistemas para Internet** da **Faculdade de Tecnologia da Baixada Santista Rubens Lara (FATEC Rubens Lara)**.

---

## 📌 Índice
- [Fluxo de Branches](#-fluxo-de-branches)
- [Padrão de Mensagens de Commit (Conventional Commits)](#-padrão-de-mensagens-de-commit)
- [Diretrizes de Segurança e Boas Práticas](#-diretrizes-de-segurança-e-boas-práticas)
- [Checklist de Integração e Pull Request](#-checklist-de-integração-e-pull-request)

---

## 🌿 Fluxo de Branches

Para manter a estabilidade do repositório e evitar conflitos de código durante o desenvolvimento simultâneo de design, documentação e futuro backend, utilizamos branches temáticas:

| Tipo de Branch | Padrão de Nomenclatura | Exemplo Prático |
| :--- | :--- | :--- |
| **Branch Principal** | `main` | Código estável, homologado e documentado |
| **Documentação & Pesquisa** | `docs/<assunto>` | `docs/contexto-legislacao`, `docs/requisitos-sistema` |
| **Design & UI/UX** | `design/<tela>` | `design/mockup-municipe`, `design/fluxo-prefeitura-figma` |
| **Novas Funcionalidades (Fases 6/7)** | `feature/<escopo>` | `feature/schema-postgresql`, `feature/auth-jwt-spring` |
| **Correções de Bugs** | `fix/<problema>` | `fix/validacao-cpf`, `fix/ajuste-links-docs` |

### Ciclo de Vida de uma Tarefa:
1. Atualize sua branch local com a principal:
   ```bash
   git checkout main
   git pull origin main
   ```
2. Crie uma branch para a sua tarefa:
   ```bash
   git checkout -b docs/minha-tarefa
   ```
3. Realize os commits seguindo a convenção e envie para o GitHub:
   ```bash
   git push origin docs/minha-tarefa
   ```

---

## ✍️ Padrão de Mensagens de Commit

Adotamos a especificação de **[Conventional Commits](https://www.conventionalcommits.org/)** para manter o histórico de alterações legível e rastreável:

| Prefixo | Significado | Exemplo Prático |
| :--- | :--- | :--- |
| `docs:` | Alterações exclusivamente em documentação | `docs: refinar prazos da Lei Complementar nº 852` |
| `style:` | Ajustes visuais, diagramas e design system | `style: padronizar mockups e vetores oficiais svg` |
| `feat:` | Nova funcionalidade ou recurso implementado | `feat: adicionar schema relacional de logs no supabase` |
| `fix:` | Correção de defeito, bug ou apontamento | `fix: corrigir calculo de prazos na camada de servico` |
| `refactor:`| Refatoração de código sem mudar comportamento | `refactor: desacoplar regras de triagem para service` |
| `chore:` | Tarefas de configuração, dependências ou assets | `chore: atualizar links relativos e badges do readme` |

---

## 🔒 Diretrizes de Segurança e Boas Práticas

1. **Credenciais e Segredos:**
   * **Nunca** comite senhas de banco de dados, chaves de API, credenciais do Supabase ou tokens JWT no repositório.
   * Mantenha configurações e variáveis locais em arquivos `.env` ou `application.properties` (que devem constar no `.gitignore`), compartilhando apenas modelos de exemplo (`.env.example`).

2. **Alinhamento com a Documentação:**
   * Qualquer alteração no modelo de dados deve ser previamente refletida no documento [Arquitetura e Banco de Dados](docs/engenharia/arquitetura-e-banco.md).
   * Novas regras de negócio e rotas REST devem ser documentadas em [Regras de Negócio & API](docs/engenharia/regras-e-api.md).

3. **Padrão Append-Only e Soft Delete:**
   * Tabelas de negócio (`Cliente`, `Funcionario`, `Chamado`) devem respeitar a exclusão lógica (`excluido = true`).
   * A tabela `Logs` opera estritamente no modelo *append-only* (somente registros sequenciais de inclusão e consulta), preservando a integridade da auditoria.

---

## ✅ Checklist de Integração e Pull Request

Antes de integrar alterações na branch `main`, certifique-se de que:
- [ ] **Documentação e Links:** Todos os links relativos apontam para arquivos existentes e sem caminhos quebrados.
- [ ] **Segurança:** Não há credenciais, arquivos `.env`, chaves privadas ou temporários (`.idea/`, `.vscode/`, `target/`) sendo enviados.
- [ ] **Padrão de Commit:** As mensagens seguem o formato `tipo: descricao` do Conventional Commits.
- [ ] **Backend (Fase 7 - quando aplicável):** O código compila localmente sem erros (`mvn clean test`).
