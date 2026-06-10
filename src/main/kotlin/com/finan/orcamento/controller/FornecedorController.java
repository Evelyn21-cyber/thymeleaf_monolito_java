package com.finan.orcamento.controller;

import com.finan.orcamento.model.FornecedorModel;
import com.finan.orcamento.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    /** GET /fornecedores → exibe formulário + lista */
    @GetMapping
    public String getFornecedorPage(Model model) {
        model.addAttribute("fornecedorModel", new FornecedorModel());
        model.addAttribute("fornecedores", fornecedorService.buscarTodos());
        return "fornecedorPage";
    }

    /** POST /fornecedores → salva e redireciona */
    @PostMapping
    public String cadastrarFornecedor(@ModelAttribute FornecedorModel fornecedorModel,
                                      RedirectAttributes redirectAttributes) {
        fornecedorService.cadastrarFornecedor(fornecedorModel);
        redirectAttributes.addFlashAttribute("mensagem", "Fornecedor cadastrado com sucesso!");
        return "redirect:/fornecedores";
    }

    /** POST /fornecedores/deletar/{id} → deleta e redireciona */
    @PostMapping("/deletar/{id}")
    public String deletarFornecedor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        fornecedorService.deletarFornecedor(id);
        redirectAttributes.addFlashAttribute("mensagem", "Fornecedor removido!");
        return "redirect:/fornecedores";
    }
}