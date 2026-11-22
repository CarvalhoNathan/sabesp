package br.gov.sp.santos.semam.saneamento.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chamados")
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 32)
    private String protocolo;

    @Column(nullable = false, length = 255)
    private String endereco;

    @Enumerated(EnumType.STRING)
    @Column(length = 40)
    private ZonaSantos zona = ZonaSantos.ORLA_INTERMEDIARIA;

    private Double latitude;

    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_problema", nullable = false, length = 50)
    private TipoProblema tipoProblema;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private StatusChamado status = StatusChamado.PENDENTE;

    @Column(name = "data_hora_ocorrencia", nullable = false)
    private OffsetDateTime dataHoraOcorrencia;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private OffsetDateTime criadoEm;

    @OneToMany(mappedBy = "chamado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChamadoFoto> fotos = new ArrayList<>();

    @OneToMany(mappedBy = "chamado", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("criadoEm DESC")
    private List<ChamadoComentario> comentarios = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        OffsetDateTime agora = OffsetDateTime.now();
        if (this.criadoEm == null) {
            this.criadoEm = agora;
        }
        if (this.dataHoraOcorrencia == null) {
            this.dataHoraOcorrencia = agora;
        }
        if (this.status == null) {
            this.status = StatusChamado.PENDENTE;
        }
        if (this.zona == null) {
            this.zona = ZonaSantos.ORLA_INTERMEDIARIA;
        }
    }

    public void adicionarFoto(ChamadoFoto foto) {
        foto.setChamado(this);
        this.fotos.add(foto);
    }

    public void adicionarComentario(ChamadoComentario comentario) {
        comentario.setChamado(this);
        this.comentarios.add(comentario);
    }

    public Long getId() {
        return id;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ZonaSantos getZona() {
        return zona;
    }

    public void setZona(ZonaSantos zona) {
        this.zona = zona;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public TipoProblema getTipoProblema() {
        return tipoProblema;
    }

    public void setTipoProblema(TipoProblema tipoProblema) {
        this.tipoProblema = tipoProblema;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public void setStatus(StatusChamado status) {
        this.status = status;
    }

    public OffsetDateTime getDataHoraOcorrencia() {
        return dataHoraOcorrencia;
    }

    public void setDataHoraOcorrencia(OffsetDateTime dataHoraOcorrencia) {
        this.dataHoraOcorrencia = dataHoraOcorrencia;
    }

    public OffsetDateTime getCriadoEm() {
        return criadoEm;
    }

    public List<ChamadoFoto> getFotos() {
        return fotos;
    }

    public List<ChamadoComentario> getComentarios() {
        return comentarios;
    }
}
