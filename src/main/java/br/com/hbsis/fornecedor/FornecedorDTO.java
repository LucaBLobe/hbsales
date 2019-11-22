package br.com.hbsis.fornecedor;

public class FornecedorDTO {

    private Long id;
    private String razaoSocial;
    private String CNPJ;
    private String nomeFantasia;
    private String endereco;
    private Long telefoneContado;
    private String email;

    public FornecedorDTO() {
    }

    public FornecedorDTO(Long id, Long cnpj, String nomeFantasia, String razaoSocial, String endereco, String email) {
    }

    public FornecedorDTO(Long id, String cnpj, String nomeFantasia, String razaoSocial, String endereco, String email) {
    }

    public static FornecedorDTO of(Fornecedor fornecedor) {
        return new FornecedorDTO(
                fornecedor.getId(),
                fornecedor.getCNPJ(),
                fornecedor.getNomeFantasia(),
                fornecedor.getRazaoSocial(),
                fornecedor.getEndereco(),
                fornecedor.getEmail()
        );
    }

    public Long getId() {
        return id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getEndereco() {
        return endereco;
    }

    public Long getTelefoneContado() {
        return telefoneContado;
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

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefoneContado(Long telefoneContado) {
        this.telefoneContado = telefoneContado;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "FornecedorDTO{" +
                "id=" + id +
                ", Razão Social='" + razaoSocial + '\'' +
                ", CNPJ ='" + CNPJ + '\'' +
                ", Nome Fantasia ='" + nomeFantasia + '\'' +
                ", Endereço ='" + endereco + '\'' +
                ", Telefone de contato ='" + telefoneContado + '\'' +
                ", E=mail ='" + email + '\'' +
                '}';
    }
}
