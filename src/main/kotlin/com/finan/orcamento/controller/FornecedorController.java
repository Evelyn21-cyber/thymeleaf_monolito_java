package com.finan.orcamento.controller;

import com.finan.orcamento.model.FornecedorModel;
import com.finan.orcamento.service.FornecedorService; // Importe o NOVO Service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService service; // Agora injetamos o Service

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("fornecedores", service.listarTodos());
        return "fornecedor/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("fornecedor", new FornecedorModel());
        return "fornecedor/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("fornecedor") FornecedorModel fornecedor) {
        service.salvar(fornecedor);
        return "redirect:/fornecedores";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/fornecedores";
    }
}