package br.gov.sp.santos.semam.saneamento.model;

/**
 * Regiões/zonas de Santos usadas nos filtros de acompanhamento e operação.
 */
public enum ZonaSantos {
    ORLA_INTERMEDIARIA("Orla/Intermediária"),
    CENTRO_HISTORICO("Centro Histórico"),
    ZONA_NOROESTE("Zona Noroeste"),
    MORROS("Morros"),
    CONTINENTAL("Continental");

    private final String descricao;

    ZonaSantos(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
