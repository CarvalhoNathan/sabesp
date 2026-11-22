package br.gov.sp.santos.semam.saneamento.dto;

import br.gov.sp.santos.semam.saneamento.model.TipoProblema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class NovoChamadoForm {

    @NotBlank(message = "Informe o endereço da ocorrência.")
    @Size(max = 255, message = "O endereço deve ter no máximo 255 caracteres.")
    private String endereco;

    private Double latitude;

    private Double longitude;

    @NotNull(message = "Selecione o tipo de problema.")
    private TipoProblema tipoProblema;

    @NotBlank(message = "Descreva os detalhes visíveis do problema.")
    @Size(min = 10, max = 2000, message = "A descrição deve ter entre 10 e 2000 caracteres.")
    private String descricao;

    private List<MultipartFile> fotos;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public List<MultipartFile> getFotos() {
        return fotos;
    }

    public void setFotos(List<MultipartFile> fotos) {
        this.fotos = fotos;
    }
}
