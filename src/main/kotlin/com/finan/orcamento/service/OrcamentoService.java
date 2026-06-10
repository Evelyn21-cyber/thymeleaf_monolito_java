package com.finan.orcamento.service;

import com.finan.orcamento.model.FornecedorModel;
import com.finan.orcamento.model.OrcamentoModel;
import com.finan.orcamento.model.UsuarioModel;
import com.finan.orcamento.model.enums.IcmsEstados;
import com.finan.orcamento.repositories.FornecedorRepository;
import com.finan.orcamento.repositories.OrcamentoRepository;
import com.finan.orcamento.repositories.UsuarioRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Data
@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<OrcamentoModel> buscarCadastro() {
        return orcamentoRepository.findAll();
    }

    public OrcamentoModel buscaId(Long id) {
        Optional<OrcamentoModel> obj = orcamentoRepository.findById(id);
        if (obj.isPresent()) {
            return obj.get();
        } else {
            throw new RuntimeException("Orçamento não encontrado com id: " + id);
        }
    }

    /**
     * Cadastra orçamento recebendo os IDs de usuário e fornecedor vindos do formulário Thymeleaf.
     * Resolve as entidades pelo repositório antes de salvar.
     */
    public OrcamentoModel cadastrarOrcamento(OrcamentoModel orcamentoModel,
                                             Long usuarioId,
                                             Long fornecedorId) {
        UsuarioModel usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + usuarioId));

        FornecedorModel fornecedor = fornecedorRepository.findById(fornecedorId)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com id: " + fornecedorId));

        orcamentoModel.setUsuario(usuario);
        orcamentoModel.setFornecedor(fornecedor);
        orcamentoModel.calcularIcms();

        return orcamentoRepository.save(orcamentoModel);
    }

    public OrcamentoModel atualizaCadastro(OrcamentoModel orcamentoModel, Long id) {
        OrcamentoModel existing = buscaId(id);
        existing.setValorOrcamento(orcamentoModel.getValorOrcamento());
        existing.setIcmsEstados(orcamentoModel.getIcmsEstados());
        existing.calcularIcms();
        return orcamentoRepository.save(existing);
    }

    public void deletaOrcamento(Long id) {
        orcamentoRepository.deleteById(id);
    }
}
