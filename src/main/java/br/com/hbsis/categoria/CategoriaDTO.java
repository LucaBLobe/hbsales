package br.com.hbsis.categoria;


import br.com.hbsis.fornecedor.Fornecedor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opencsv.bean.CsvBindByName,
import com.opencsv.bean.CsvBindByPosition;


public class CategoriaDTO {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaDTO.class);
    private Long id;
    private String codigoCategoria;
    private Fornecedor fornecedorCategoria;
    private String nomeCategoria;


    public CategoriaDTO() {
    }

    public CategoriaDTO(String codigoCategoria, Fornecedor fornecedorCategoria, String nomeCategoria) {
        this.codigoCategoria = codigoCategoria;
        this.fornecedorCategoria = fornecedorCategoria;
        this.nomeCategoria = nomeCategoria;
    }

    public CategoriaDTO(Long id, String codigoCategoria, Fornecedor fornecedorCategoria, String nomeCategoria) {
        this.id = id;
        this.codigoCategoria = codigoCategoria;
        this.fornecedorCategoria = fornecedorCategoria;
        this.nomeCategoria = nomeCategoria;
    }

    public static CategoriaDTO of(Categoria categoria) {

        return new CategoriaDTO(
                categoria.getId(),
                categoria.getCodigoCategoria(),
                categoria.getFornecedorCategoria(),
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

    public Fornecedor getFornecedorCategoria() {
        return fornecedorCategoria;
    }

    public void setFornecedorCategoria(Fornecedor fornecedorCategoria) {
        this.fornecedorCategoria = fornecedorCategoria;
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
                ", Codigo da Categoria ='" + codigoCategoria + '\'' +"" +
                ", Nome da categoria ='" + nomeCategoria + '\'' +
                ", Fornecedor da Categoria ='" + fornecedorCategoria + '\'' +
                '}';
    }
}
