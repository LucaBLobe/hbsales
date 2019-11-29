package br.com.hbsis.categoria;


import br.com.hbsis.fornecedor.Fornecedor;


public class CategoriaDTO {

    private Long id;
    private String codigoCategoria;
    // private Fornecedor fornecedorCategoria;
    private Fornecedor fornecedorId;
    private String nomeCategoria;


    public CategoriaDTO() {
    }


    public CategoriaDTO(Long id, String codigoCategoria, Fornecedor fornecedorId, String nomeCategoria) {
        this.id = id;
        this.codigoCategoria = codigoCategoria;
        this.fornecedorId = fornecedorId;
        this.nomeCategoria = nomeCategoria;
    }


    public static CategoriaDTO of(Categoria categoria) {

        return new CategoriaDTO(
                categoria.getId(),
                categoria.getCodigoCategoria(),
                categoria.getFornecedorId(),
                categoria.getNomeCategoria()
        );
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

    public Fornecedor getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Fornecedor fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    @Override
    public String toString() {
        return "CategoriaDTO{" +
                "id=" + id +
                ", Codigo da Categoria ='" + codigoCategoria + '\'' + "" +
                ", Nome da categoria ='" + nomeCategoria + '\'' +
                ", Fornecedor ID ='" + fornecedorId + '\'' +
                '}';
    }
}