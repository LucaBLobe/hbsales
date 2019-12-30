package br.com.hbsis.produto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ProdutoDTO {

    private Long id;
    private String codProduto;
    private String nomeProduto;
    private Double precoProduto;
    private Long linhaCategoriaId;
    private int unidadesCaixa;
    private String unidadeMedida;
    private Double pesoUnitario;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate validadeProduto;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Long id, String codProduto, String nomeProduto, Double precoProduto, Long linhaCategoriaId, int unidadesCaixa, String unidadeMedida, Double pesoUnitario, LocalDate validadeProduto) {
        this.id = id;
        this.codProduto = codProduto;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.linhaCategoriaId = linhaCategoriaId;
        this.unidadesCaixa = unidadesCaixa;
        this.unidadeMedida = unidadeMedida;
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
                produto.getUnidadeMedida(),
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

    public String getUnidadeMedida() { return unidadeMedida; }

    public void setUnidadeMedida(String unidadeMedida) { this.unidadeMedida = unidadeMedida; }

    public Double getPesoUnitario() { return pesoUnitario; }

    public void setPesoUnitario(Double pesoUnitario) { this.pesoUnitario = pesoUnitario; }

    public LocalDate getValidadeProduto() { return validadeProduto; }

    public void setValidadeProduto(LocalDate validadeProduto) { this.validadeProduto = validadeProduto; }

    @Override
    public String toString() {
    return"Produto{"+
            "ID:"+id +
            "Codigo Produto: "+ codProduto +'\''+
            ", Nome Produto:'"+ nomeProduto +'\''+
            ", Preço Produto:'"+ precoProduto +'\''+
            ", Linha da Categoria Id:'"+ linhaCategoriaId +'\''+
            ", Unidades por caixa:'"+ unidadesCaixa +'\''+
            ", Unidade de medida:'"+ unidadeMedida +'\''+
            ", Peso Unitário:'"+ pesoUnitario +'\''+
            ", Validade do :'"+ validadeProduto +'\''+
            '}';
}

}
