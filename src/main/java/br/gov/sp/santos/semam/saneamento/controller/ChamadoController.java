package br.gov.sp.santos.semam.saneamento.controller;

import br.gov.sp.santos.semam.saneamento.dto.NovoChamadoForm;
import br.gov.sp.santos.semam.saneamento.model.Chamado;
import br.gov.sp.santos.semam.saneamento.model.TipoProblema;
import br.gov.sp.santos.semam.saneamento.service.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ChamadoController {

    private final ChamadoService chamadoService;

    public ChamadoController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @GetMapping("/")
    public String selecionarPerfil() {
        return "index";
    }

    @GetMapping("/chamados/novo")
    public String exibirFormulario(Model model) {
        if (!model.containsAttribute("chamadoForm")) {
            model.addAttribute("chamadoForm", new NovoChamadoForm());
        }
        model.addAttribute("tiposProblema", TipoProblema.values());
        return "chamados/novo";
    }

    @PostMapping("/chamados/novo")
    public String enviarSolicitacao(
            @Valid @ModelAttribute("chamadoForm") NovoChamadoForm form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tiposProblema", TipoProblema.values());
            return "chamados/novo";
        }

        try {
            Chamado criado = chamadoService.registrarChamado(form);
            redirectAttributes.addFlashAttribute("protocoloGerado", criado.getProtocolo());
            return "redirect:/chamados/novo";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("erroUpload", ex.getMessage());
            model.addAttribute("tiposProblema", TipoProblema.values());
            return "chamados/novo";
        }
    }
}
