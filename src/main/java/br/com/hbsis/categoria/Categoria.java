package br.com.hbsis.categoria;

import br.com.hbsis.fornecedor.Fornecedor;

import javax.persistence.*;

import java.util.List;


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
    private Fornecedor fornecedorId;

    public Categoria(){

    };



    public Categoria(Long id, String codigoCategoria, Fornecedor fornecedorId) {
        this.id = id;
        this.codigoCategoria = codigoCategoria;
        this.fornecedorId = fornecedorId;
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


    public Fornecedor getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Fornecedor fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "ID:"+ id+
                "Codigo Categoria= " + codigoCategoria + '\'' +
                ", Fornecedor Id='" + fornecedorId + '\'' +
                ", Nome Categoria ='" + nomeCategoria + '\'' +
                '}';
    }
}
