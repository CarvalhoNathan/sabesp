package br.gov.sp.santos.semam.saneamento.controller;

import br.gov.sp.santos.semam.saneamento.model.Chamado;
import br.gov.sp.santos.semam.saneamento.model.StatusChamado;
import br.gov.sp.santos.semam.saneamento.model.ZonaSantos;
import br.gov.sp.santos.semam.saneamento.service.ChamadoService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PainelController {

    private final ChamadoService chamadoService;

    public PainelController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    // ==================== SABESP (Visualizar, Alterar Status e Comentar) ====================

    @GetMapping("/sabesp/chamados")
    public String listarSabesp(
            @RequestParam(required = false) ZonaSantos zona,
            @RequestParam(required = false) StatusChamado status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            @RequestParam(required = false) String busca,
            Model model) {
        carregarListaPainel(model, "SABESP", true, zona, status, dataInicio, dataFim, busca);
        return "painel/lista";
    }

    @GetMapping("/sabesp/chamados/{id}")
    public String detalheSabesp(@PathVariable Long id, Model model) {
        Chamado chamado = chamadoService.buscarPorId(id);
        model.addAttribute("chamado", chamado);
        model.addAttribute("perfil", "SABESP");
        model.addAttribute("podeEditar", true);
        model.addAttribute("baseUrl", "/sabesp/chamados");
        model.addAttribute("statusDisponiveis", StatusChamado.values());
        return "painel/detalhe";
    }

    @PostMapping("/sabesp/chamados/{id}/atualizar")
    public String atualizarChamadoSabesp(
            @PathVariable Long id,
            @RequestParam(required = false) StatusChamado novoStatus,
            @RequestParam(required = false) String comentario,
            @RequestParam(defaultValue = "Equipe Operacional Sabesp") String autorNome,
            RedirectAttributes redirectAttributes) {
        try {
            chamadoService.atualizarStatusEComentar(id, novoStatus, comentario, "SABESP", autorNome);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Chamado atualizado com sucesso.");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("mensagemErro", ex.getMessage());
        }
        return "redirect:/sabesp/chamados/" + id;
    }

    // ==================== PREFEITURA / SEMAM (Acompanhar e Filtrar) ====================

    @GetMapping("/prefeitura/chamados")
    public String listarPrefeitura(
            @RequestParam(required = false) ZonaSantos zona,
            @RequestParam(required = false) StatusChamado status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            @RequestParam(required = false) String busca,
            Model model) {
        carregarListaPainel(model, "PREFEITURA", false, zona, status, dataInicio, dataFim, busca);
        return "painel/lista";
    }

    @GetMapping("/prefeitura/chamados/{id}")
    public String detalhePrefeitura(@PathVariable Long id, Model model) {
        Chamado chamado = chamadoService.buscarPorId(id);
        model.addAttribute("chamado", chamado);
        model.addAttribute("perfil", "PREFEITURA");
        model.addAttribute("podeEditar", false);
        model.addAttribute("baseUrl", "/prefeitura/chamados");
        model.addAttribute("statusDisponiveis", StatusChamado.values());
        return "painel/detalhe";
    }

    private void carregarListaPainel(
            Model model,
            String perfil,
            boolean podeEditar,
            ZonaSantos zona,
            StatusChamado status,
            LocalDate dataInicio,
            LocalDate dataFim,
            String busca) {
        List<Chamado> chamados = chamadoService.listarComFiltros(zona, status, dataInicio, dataFim, busca);

        model.addAttribute("perfil", perfil);
        model.addAttribute("podeEditar", podeEditar);
        model.addAttribute("baseUrl", podeEditar ? "/sabesp/chamados" : "/prefeitura/chamados");

        model.addAttribute("chamados", chamados);
        model.addAttribute("zonas", ZonaSantos.values());
        model.addAttribute("statusLista", StatusChamado.values());

        model.addAttribute("filtroZona", zona);
        model.addAttribute("filtroStatus", status);
        model.addAttribute("filtroDataInicio", dataInicio);
        model.addAttribute("filtroDataFim", dataFim);
        model.addAttribute("filtroBusca", busca);

        model.addAttribute("totalChamados", chamadoService.contarTodos());
        model.addAttribute("totalPendentes", chamadoService.contarPorStatus(StatusChamado.PENDENTE));
        model.addAttribute("totalEmObra", chamadoService.contarPorStatus(StatusChamado.EM_OBRA));
        model.addAttribute("totalConcluidos", chamadoService.contarPorStatus(StatusChamado.CONCLUIDO));
    }
}
