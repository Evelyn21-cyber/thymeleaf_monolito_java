package com.finan.orcamento.controller;

import com.finan.orcamento.model.UsuarioModel;
import com.finan.orcamento.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /** GET /usuarios → exibe formulário vazio + lista de usuários */
    @GetMapping
    public String getUsuarioPage(Model model) {
        model.addAttribute("usuarioModel", new UsuarioModel());
        model.addAttribute("usuarios", usuarioService.buscarUsuario());
        return "usuarioPage";
    }

    /** POST /usuarios → salva e redireciona de volta para a listagem */
    @PostMapping
    public String cadastraUsuario(@ModelAttribute UsuarioModel usuarioModel,
                                  RedirectAttributes redirectAttributes) {
        usuarioService.cadastrarUsuario(usuarioModel);
        redirectAttributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
        return "redirect:/usuarios";
    }

    /** POST /usuarios/deletar/{id} → deleta e redireciona */
    @PostMapping("/deletar/{id}")
    public String deletarUsuario(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        usuarioService.deletaUsuario(id);
        redirectAttributes.addFlashAttribute("mensagem", "Usuário removido com sucesso!");
        return "redirect:/usuarios";
    }
}