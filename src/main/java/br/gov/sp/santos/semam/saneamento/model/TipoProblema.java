package br.gov.sp.santos.semam.saneamento.model;

/**
 * Tipos de problema na rede de esgoto reportáveis pelo cidadão.
 */
public enum TipoProblema {
    VAZAMENTO_VIA("Vazamento na Via", "droplet"),
    REFLUXO_ESGOTO("Refluxo de Esgoto", "waves"),
    ROMPIMENTO_TUBULACAO("Rompimento de Tubulação", "wrench"),
    TAMPA_BUEIRO_DANIFICADA("Tampa de Bueiro Danificada", "alert-circle");

    private final String descricao;
    private final String icone;

    TipoProblema(String descricao, String icone) {
        this.descricao = descricao;
        this.icone = icone;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getIcone() {
        return icone;
    }
}
