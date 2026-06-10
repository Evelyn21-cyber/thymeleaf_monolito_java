package com.finan.orcamento.controller;

import com.finan.orcamento.model.OrcamentoModel;
import com.finan.orcamento.model.enums.IcmsEstados;
import com.finan.orcamento.service.FornecedorService;
import com.finan.orcamento.service.OrcamentoService;
import com.finan.orcamento.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/orcamentos")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FornecedorService fornecedorService;

    /** GET /orcamentos → exibe formulário com selects populados + lista de orçamentos */
    @GetMapping
    public String getOrcamentoPage(Model model) {
        model.addAttribute("orcamentoModel", new OrcamentoModel());
        model.addAttribute("orcamentos", orcamentoService.buscarCadastro());
        model.addAttribute("usuarios", usuarioService.buscarUsuario());
        model.addAttribute("fornecedores", fornecedorService.buscarTodos());
        model.addAttribute("icmsEstados", IcmsEstados.values());
        return "orcamentoPage";
    }

    /**
     * POST /orcamentos → recebe o formulário.
     * Os IDs de usuário e fornecedor chegam como @RequestParam pois Thymeleaf
     * não vincula objetos aninhados via @ModelAttribute com apenas o ID.
     */
    @PostMapping
    public String cadastrarOrcamento(@ModelAttribute OrcamentoModel orcamentoModel,
                                     @RequestParam("usuarioId") Long usuarioId,
                                     @RequestParam("fornecedorId") Long fornecedorId,
                                     RedirectAttributes redirectAttributes) {
        orcamentoService.cadastrarOrcamento(orcamentoModel, usuarioId, fornecedorId);
        redirectAttributes.addFlashAttribute("mensagem", "Orçamento cadastrado com sucesso!");
        return "redirect:/orcamentos";
    }

    /** POST /orcamentos/deletar/{id} → deleta e redireciona */
    @PostMapping("/deletar/{id}")
    public String deletarOrcamento(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        orcamentoService.deletaOrcamento(id);
        redirectAttributes.addFlashAttribute("mensagem", "Orçamento removido!");
        return "redirect:/orcamentos";
    }
}