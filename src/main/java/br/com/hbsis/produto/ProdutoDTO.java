package br.com.hbsis.produto;

import br.com.hbsis.linhaCategoria.LinhaCategoriaDTO;

import java.time.LocalDate;

public class ProdutoDTO {

    private Long id;
    private String codProduto;
    private String nomeProduto;
    private Double precoProduto;
    private Long linhaCategoriaId;
    private int unidadesCaixa;
    private Double pesoUnitario;
    private LocalDate validadeProduto;

    public ProdutoDTO() {

    }

    public ProdutoDTO(Long id, String codProduto, String nomeProduto, Double precoProduto, Long linhaCategoriaId, int unidadesCaixa, Double pesoUnitario, LocalDate validadeProduto) {
        this.id = id;
        this.codProduto = codProduto;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.linhaCategoriaId = linhaCategoriaId;
        this.unidadesCaixa = unidadesCaixa;
        this.pesoUnitario = pesoUnitario;
        this.validadeProduto = validadeProduto;
    }

    public static ProdutoDTO of(Produto produto){

        return new ProdutoDTO(
                produto.getId(),
                produto.getCodProduto(),
                produto.getNomeProduto(),
                produto.getPrecoProduto(),
                produto.getLinhaCategoriaId().getId(),
                produto.getUnidadesCaixa(),
                produto.getPesoUnitario(),
                produto.getValidadeProduto()
        );

    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getCodProduto() { return codProduto; }

    public void setCodProduto(String codProduto) { this.codProduto = codProduto; }

    public String getNomeProduto() { return nomeProduto; }

    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public Double getPrecoProduto() { return precoProduto; }

    public void setPrecoProduto(Double precoProduto) { this.precoProduto = precoProduto; }

    public Long getLinhaCategoriaId() { return linhaCategoriaId; }

    public void setLinhaCategoriaId(Long linhaCategoriaId) { this.linhaCategoriaId = linhaCategoriaId; }

    public int getUnidadesCaixa() { return unidadesCaixa; }

    public void setUnidadesCaixa(int unidadesCaixa) { this.unidadesCaixa = unidadesCaixa; }

    public Double getPesoUnitario() { return pesoUnitario; }

    public void setPesoUnitario(Double pesoUnitario) { this.pesoUnitario = pesoUnitario; }

    public LocalDate getValidadeProduto() { return validadeProduto; }

    public void setValidadeProduto(LocalDate validadeProduto) { this.validadeProduto = validadeProduto; }

    @Override
    public String toString() {
    return"Linha Categoria{"+
            "ID:"+id +
            "Codigo Produto: "+ codProduto +'\''+
            ", Nome Produto:'"+ nomeProduto +'\''+
            ", Preço Produto:'"+ precoProduto +'\''+
            ", Linha da Categoria Id:'"+ linhaCategoriaId +'\''+
            ", Unidades por caixa:'"+ unidadesCaixa +'\''+
            ", Peso Unitário:'"+ pesoUnitario +'\''+
            ", Validade do :'"+ validadeProduto +'\''+
            '}';
}

}
