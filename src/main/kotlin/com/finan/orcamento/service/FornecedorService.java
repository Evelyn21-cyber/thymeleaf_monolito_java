package com.finan.orcamento.service;

import com.finan.orcamento.model.FornecedorModel;
import com.finan.orcamento.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    public List<FornecedorModel> listarTodos() {
        return repository.findAll();
    }

    public FornecedorModel salvar(FornecedorModel fornecedor) {
        return repository.save(fornecedor);
    }

    public FornecedorModel buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}