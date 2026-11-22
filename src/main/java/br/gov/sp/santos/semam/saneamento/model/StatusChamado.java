package br.gov.sp.santos.semam.saneamento.model;

/**
 * Ciclo de vida do chamado entre Cidadão, Prefeitura (SEMAM) e Sabesp.
 */
public enum StatusChamado {
    PENDENTE("Pendente", "badge-pendente"),
    APROVADO("Aprovado", "badge-aprovado"),
    EM_OBRA("Em Obra", "badge-em-obra"),
    CONCLUIDO("Concluído", "badge-concluido"),
    REJEITADO("Rejeitado", "badge-rejeitado");

    private final String descricao;
    private final String cssClass;

    StatusChamado(String descricao, String cssClass) {
        this.descricao = descricao;
        this.cssClass = cssClass;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCssClass() {
        return cssClass;
    }
}
