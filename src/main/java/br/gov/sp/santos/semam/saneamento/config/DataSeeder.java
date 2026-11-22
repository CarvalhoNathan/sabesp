package br.gov.sp.santos.semam.saneamento.config;

import br.gov.sp.santos.semam.saneamento.model.Chamado;
import br.gov.sp.santos.semam.saneamento.model.ChamadoComentario;
import br.gov.sp.santos.semam.saneamento.model.StatusChamado;
import br.gov.sp.santos.semam.saneamento.model.TipoProblema;
import br.gov.sp.santos.semam.saneamento.model.ZonaSantos;
import br.gov.sp.santos.semam.saneamento.repository.ChamadoRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Popula chamados iniciais em ambiente local (H2) para facilitar testes dos painéis Sabesp e Prefeitura.
 */
@Component
@Profile("!supabase")
public class DataSeeder implements CommandLineRunner {

    private final ChamadoRepository chamadoRepository;

    public DataSeeder(ChamadoRepository chamadoRepository) {
        this.chamadoRepository = chamadoRepository;
    }

    @Override
    public void run(String... args) {
        if (chamadoRepository.count() > 0) {
            return;
        }

        Chamado c1 = criar(
                "SAN-2026-001240",
                "Av. Ana Costa, 482 - Gonzaga",
                ZonaSantos.ORLA_INTERMEDIARIA,
                TipoProblema.VAZAMENTO_VIA,
                "Vazamento contínuo de esgoto próximo à guia, causando mau cheiro na calçada.",
                StatusChamado.PENDENTE);

        Chamado c2 = criar(
                "SAN-2026-001239",
                "Rua João Pessoa, 87 - Centro",
                ZonaSantos.CENTRO_HISTORICO,
                TipoProblema.TAMPA_BUEIRO_DANIFICADA,
                "Tampa do poço de visita trincada e afundando com a passagem de ônibus.",
                StatusChamado.PENDENTE);

        Chamado c3 = criar(
                "SAN-2026-001238",
                "Av. Hugo Maia, 215 - Rádio Clube",
                ZonaSantos.ZONA_NOROESTE,
                TipoProblema.ROMPIMENTO_TUBULACAO,
                "Rompimento visível na rede coletora em frente à praça, com acúmulo na via.",
                StatusChamado.EM_OBRA);
        adicionarComentario(c3, "SABESP", "Equipe Operacional Sabesp",
                "Equipe em campo realizando escavação e substituição do trecho danificado.",
                StatusChamado.APROVADO, StatusChamado.EM_OBRA);

        Chamado c4 = criar(
                "SAN-2026-001237",
                "Rua São Judas Tadeu, 84 - Marapé",
                ZonaSantos.MORROS,
                TipoProblema.REFLUXO_ESGOTO,
                "Refluxo na rede após chuvas intensas, retornando pelo ralo da garagem.",
                StatusChamado.APROVADO);

        Chamado c5 = criar(
                "SAN-2026-001236",
                "Rua Andrade Soares, 120 - Caruara",
                ZonaSantos.CONTINENTAL,
                TipoProblema.TAMPA_BUEIRO_DANIFICADA,
                "Bueiro sem tampa oferecendo risco a pedestres e ciclistas.",
                StatusChamado.CONCLUIDO);
        adicionarComentario(c5, "SABESP", "Equipe Operacional Sabesp",
                "Nova tampa instalada e área sinalizada e liberada.",
                StatusChamado.EM_OBRA, StatusChamado.CONCLUIDO);

        Chamado c6 = criar(
                "SAN-2026-001235",
                "Av. Pedro Lessa, 1980 - Aparecida",
                ZonaSantos.ORLA_INTERMEDIARIA,
                TipoProblema.ROMPIMENTO_TUBULACAO,
                "Afundo no asfalto com vazamento de água servida próximo ao cruzamento.",
                StatusChamado.EM_OBRA);

        chamadoRepository.saveAll(List.of(c1, c2, c3, c4, c5, c6));
    }

    private Chamado criar(
            String protocolo,
            String endereco,
            ZonaSantos zona,
            TipoProblema tipo,
            String descricao,
            StatusChamado status) {
        Chamado c = new Chamado();
        c.setProtocolo(protocolo);
        c.setEndereco(endereco);
        c.setZona(zona);
        c.setTipoProblema(tipo);
        c.setDescricao(descricao);
        c.setStatus(status);
        return c;
    }

    private void adicionarComentario(
            Chamado chamado,
            String perfil,
            String nome,
            String texto,
            StatusChamado anterior,
            StatusChamado novo) {
        ChamadoComentario com = new ChamadoComentario();
        com.setAutorPerfil(perfil);
        com.setAutorNome(nome);
        com.setComentario(texto);
        com.setStatusAnterior(anterior);
        com.setStatusNovo(novo);
        chamado.adicionarComentario(com);
    }
}
