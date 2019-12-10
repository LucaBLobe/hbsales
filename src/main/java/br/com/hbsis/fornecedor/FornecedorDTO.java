package br.com.hbsis.fornecedor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FornecedorDTO {

    private static final Logger LOGGER = LoggerFactory.getLogger(FornecedorDTO.class);
    private Long id;
    private String razaoSocial;
    private String cnpj;
    private String nomeFantasia;
    private String endereco;
    private String telefoneContato;
    private String email;

    public FornecedorDTO() {
    }


    public FornecedorDTO(Long id, String razaoSocial, String cnpj, String nomeFantasia, String endereco, String telefoneContado, String email) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.endereco = endereco;
        this.telefoneContato = telefoneContado;
        this.email = email;
    }

    public static FornecedorDTO of(Fornecedor fornecedor) {

        return new FornecedorDTO(
                fornecedor.getId(),
                fornecedor.getRazaoSocial(),
                fornecedor.getCnpj(),
                fornecedor.getNomeFantasia(),
                fornecedor.getEndereco(),
                fornecedor.getTelefoneContato(),
                fornecedor.getEmail()
        );
    }

    public Long getId() {
        return id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefoneContato(String telefoneContado) {
        this.telefoneContato = telefoneContado;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "FornecedorDTO{" +
                "id=" + id +
                ", Razão Social='" + razaoSocial + '\'' +
                ", CNPJ ='" + cnpj + '\'' +
                ", Nome Fantasia ='" + nomeFantasia + '\'' +
                ", Endereço ='" + endereco + '\'' +
                ", Telefone de contato ='" + telefoneContato + '\'' +
                ", E=mail ='" + email + '\'' +
                '}';
    }
}
