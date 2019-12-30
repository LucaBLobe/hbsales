package br.com.hbsis.linhaCategoria;

public class LinhaCategoriaDTO {

    private Long id;
    private String codLinhaCategoria;
    private String nomeLinhaCategoria;
    private Long categoriaId;

    public LinhaCategoriaDTO() {
    }

    public LinhaCategoriaDTO(Long id, String codLinhaCategoria, Long categoriaId, String nomeLinhaCategoria) {
        this.id = id;
        this.codLinhaCategoria = codLinhaCategoria;
        this.nomeLinhaCategoria = nomeLinhaCategoria;
        this.categoriaId = categoriaId;
    }


    public static LinhaCategoriaDTO of(LinhaCategoria linhaCategoria){

        return new LinhaCategoriaDTO(
                linhaCategoria.getId(),
                linhaCategoria.getCodLinhaCategoria(),
                linhaCategoria.getCategoriaId().getId(),
                linhaCategoria.getNomeLinhaCategoria()

        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodLinhaCategoria() {
        return codLinhaCategoria;
    }

    public void setCodLinhaCategoria(String codLinhaCategoria) {
        this.codLinhaCategoria = codLinhaCategoria;
    }

    public String getNomeLinhaCategoria() {
        return nomeLinhaCategoria;
    }

    public void setNomeLinhaCategoria(String nomeLinhaCategoria) {
        this.nomeLinhaCategoria = nomeLinhaCategoria;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
    @Override
    public String toString() {
        return "Linha Categoria{" +
                "ID:" + id +
                "Codigo Linha Categoria= " + codLinhaCategoria + '\'' +
                ", Categoria Id ='" + categoriaId + '\'' +
                ", Nome Linha Categoria='" + nomeLinhaCategoria + '\'' +
                '}';
    }
}
