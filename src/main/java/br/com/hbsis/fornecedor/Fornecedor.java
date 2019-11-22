package br.com.hbsis.fornecedor;

import javax.persistence.*;

@Entity
@Table(name = "seg_fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "razao_social", unique = true, nullable = false, length = 255)
    private String razaoSocial;
    @Column(name = "CNPJ", unique = true, nullable = false, length = 25)
    private String CNPJ;
    @Column(name = "nome_fantasia", nullable = false, length = 100)
    private String nomeFantasia;
    @Column(name = "endereco", nullable = false, length = 255)
    private String endereco;
    @Column(name = "telefone_de_contato", nullable = false, length = 14)
    private Long telefoneContato;
    @Column(name = "E-mail", nullable = false, length = 100)
    private String email;

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

    public Long getTelefoneContato() {
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

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefoneContato(Long telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "Razão Social= " + razaoSocial + '\'' +
                ", CNPJ='" + CNPJ + '\'' +
                ", Nome Fantasia ='" + nomeFantasia + '\'' +
                ", Endereço ='" + endereco + '\'' +
                ", Telefone de Contato ='" + telefoneContato + '\'' +
                ", E-mail ='" + email + '\'' +
                '}';
    }
}
