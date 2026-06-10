package com.finan.orcamento.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "fornecedor")
public class FornecedorModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nome_fornecedor")
    private String nomeFornecedor;

    @NotBlank
    @Column(name = "cnpj", unique = true)
    private String cnpj;

    @JsonIgnore
    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL)
    private List<OrcamentoModel> orcamentos = new ArrayList<>();

    public FornecedorModel() {}

    public FornecedorModel(Long id, String nomeFornecedor, String cnpj) {
        this.id = id;
        this.nomeFornecedor = nomeFornecedor;
        this.cnpj = cnpj;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeFornecedor() { return nomeFornecedor; }
    public void setNomeFornecedor(String nomeFornecedor) { this.nomeFornecedor = nomeFornecedor; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public List<OrcamentoModel> getOrcamentos() { return orcamentos; }
    public void setOrcamentos(List<OrcamentoModel> orcamentos) { this.orcamentos = orcamentos; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FornecedorModel that = (FornecedorModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}