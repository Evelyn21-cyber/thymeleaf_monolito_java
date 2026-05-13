package com.finan.orcamento.repositories;

import com.finan.orcamento.model.FornecedorModel; // Importe o modelo correto
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// CORREÇÃO: Mude Fornecedor para FornecedorModel
public interface FornecedorRepository extends JpaRepository<FornecedorModel, Long> {
}