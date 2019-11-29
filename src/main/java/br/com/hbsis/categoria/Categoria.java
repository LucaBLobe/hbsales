package br.com.hbsis.categoria;

import br.com.hbsis.fornecedor.Fornecedor;

import javax.persistence.*;

@Entity
@Table(name = "cad_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_categoria", unique = true, nullable = false,length = 10)
    private String codigoCategoria;

    @Column(name = "nome_categoria", unique = true, nullable = false,length = 255)
    private String nomeCategoria;


    @ManyToOne
    @JoinColumn(name = "fornecedor_categoria", referencedColumnName = "id")
    private Fornecedor fornecedorCategoria;

    public Categoria(){

    };

    public Categoria(String codigoCategoria, String nomeCategoria, Fornecedor fornecedorCategoria) {
        this.codigoCategoria = codigoCategoria;
        this.nomeCategoria = nomeCategoria;
        this.fornecedorCategoria = fornecedorCategoria;
    }

    public Categoria(Long id, String codigoCategoria, String nomeCategoria, Long id_forcenedor, Fornecedor fornecedorCategoria) {
        this.id = id;
        this.codigoCategoria = codigoCategoria;
        this.nomeCategoria = nomeCategoria;
        this.fornecedorCategoria = fornecedorCategoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(String codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }


    public Fornecedor getFornecedorCategoria() {
        return fornecedorCategoria;
    }

    public void setFornecedorCategoria(Fornecedor fornecedorCategoria) {
        this.fornecedorCategoria = fornecedorCategoria;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "ID:"+ id+
                "Codigo Categoria= " + codigoCategoria + '\'' +
                ", Fornecedor Categoria='" + fornecedorCategoria + '\'' +
                ", Nome Categoria ='" + nomeCategoria + '\'' +
                '}';
    }
}
