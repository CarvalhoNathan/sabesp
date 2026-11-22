package br.gov.sp.santos.semam.saneamento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "chamado_comentarios")
public class ChamadoComentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chamado_id", nullable = false)
    private Chamado chamado;

    @Column(name = "autor_perfil", nullable = false, length = 30)
    private String autorPerfil;

    @Column(name = "autor_nome", nullable = false, length = 120)
    private String autorNome;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comentario;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_anterior", length = 40)
    private StatusChamado statusAnterior;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_novo", length = 40)
    private StatusChamado statusNovo;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private OffsetDateTime criadoEm;

    @PrePersist
    protected void onCreate() {
        if (this.criadoEm == null) {
            this.criadoEm = OffsetDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public Chamado getChamado() {
        return chamado;
    }

    public void setChamado(Chamado chamado) {
        this.chamado = chamado;
    }

    public String getAutorPerfil() {
        return autorPerfil;
    }

    public void setAutorPerfil(String autorPerfil) {
        this.autorPerfil = autorPerfil;
    }

    public String getAutorNome() {
        return autorNome;
    }

    public void setAutorNome(String autorNome) {
        this.autorNome = autorNome;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public StatusChamado getStatusAnterior() {
        return statusAnterior;
    }

    public void setStatusAnterior(StatusChamado statusAnterior) {
        this.statusAnterior = statusAnterior;
    }

    public StatusChamado getStatusNovo() {
        return statusNovo;
    }

    public void setStatusNovo(StatusChamado statusNovo) {
        this.statusNovo = statusNovo;
    }

    public OffsetDateTime getCriadoEm() {
        return criadoEm;
    }
}
