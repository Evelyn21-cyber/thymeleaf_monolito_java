package com.finan.orcamento.service;

import com.finan.orcamento.model.FornecedorModel;
import com.finan.orcamento.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<FornecedorModel> buscarTodos() {
        return fornecedorRepository.findAll();
    }

    public FornecedorModel buscaId(Long id) {
        Optional<FornecedorModel> obj = fornecedorRepository.findById(id);
        if (obj.isPresent()) {
            return obj.get();
        } else {
            throw new RuntimeException("Fornecedor não encontrado com id: " + id);
        }
    }

    public FornecedorModel cadastrarFornecedor(FornecedorModel fornecedorModel) {
        return fornecedorRepository.save(fornecedorModel);
    }

    public FornecedorModel atualizarFornecedor(FornecedorModel fornecedorModel, Long id) {
        FornecedorModel existing = buscaId(id);
        existing.setNomeFornecedor(fornecedorModel.getNomeFornecedor());
        existing.setCnpj(fornecedorModel.getCnpj());
        return fornecedorRepository.save(existing);
    }

    public void deletarFornecedor(Long id) {
        fornecedorRepository.deleteById(id);
    }
}