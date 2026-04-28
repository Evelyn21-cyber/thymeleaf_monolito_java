package com.finan.orcamento.controller;

import com.finan.orcamento.model.UsuarioModel;
import com.finan.orcamento.repositories.UsuarioRepository;
import com.finan.orcamento.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ── GET: abre a tela com formulário vazio ─────────────────────
    @GetMapping
    public String getUsuarioPage(Model model) {
        model.addAttribute("usuarioModel", new UsuarioModel());
        return "usuarioPage";
    }

    // ── GET pesquisa: busca todos e popula a tabela ───────────────
    @GetMapping("pesquisa")
    public String listarUsuarios(Model model) {
        List<UsuarioModel> usuarios = usuarioService.buscarUsuario();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuarioModel", new UsuarioModel());
        return "usuarioPage";
    }

    // ── POST: recebe o form e salva o usuário ─────────────────────
    // O @ModelAttribute vincula automaticamente todos os campos do
    // form HTML (nomeUsuario, rg, cpf, nomeMae) ao objeto Java.
    // Nenhuma alteração extra é necessária aqui.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UsuarioModel> cadastraUsuario(
            @ModelAttribute UsuarioModel usuarioModel) {
        return ResponseEntity.ok(
                usuarioService.cadastrarUsuario(usuarioModel));
    }
}