package br.com.hbsis.produto;

import br.com.hbsis.linhaCategoria.LinhaCategoria;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cad_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod_produto", unique = true, nullable = false, length = 10)
    private String codProduto;

    @Column(name = "nome_produto", unique = true, nullable = false, length = 200)
    private String nomeProduto;

    @Column(name = "preco_produto", nullable = false, length = 25)
    private Double precoProduto;

    @ManyToOne
    @JoinColumn(name = "linha_categoria_id", referencedColumnName = "id")
    private LinhaCategoria linhaCategoriaId;

    @Column(name = "unidades_caixa", nullable = false, length = 25)
    private int unidadesCaixa;

    @Column(name = "peso_unitario", nullable = false, length = 25)
    private Double pesoUnitario;

    @Column(name = "unidade_medida", nullable = false, length = 2)
    private String unidadeMedida;

    @Column(name = "validade_produto", nullable = false, length = 8)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate validadeProduto;

    public Produto() {
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getCodProduto() { return codProduto; }

    public void setCodProduto(String codProduto) { this.codProduto = codProduto; }

    public String getNomeProduto() { return nomeProduto; }

    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public Double getPrecoProduto() { return precoProduto; }

    public void setPrecoProduto(Double precoProduto) { this.precoProduto = precoProduto; }

    public LinhaCategoria getLinhaCategoriaId() { return linhaCategoriaId; }

    public void setLinhaCategoriaId(LinhaCategoria linhaCategoriaId) { this.linhaCategoriaId = linhaCategoriaId; }

    public int getUnidadesCaixa() { return unidadesCaixa; }

    public void setUnidadesCaixa(int unidadesCaixa) { this.unidadesCaixa = unidadesCaixa; }

    public Double getPesoUnitario() { return pesoUnitario; }

    public void setPesoUnitario(Double pesoUnitario) { this.pesoUnitario = pesoUnitario; }

    public String getUnidadeMedida() { return unidadeMedida; }

    public void setUnidadeMedida(String unidadeMedida) { this.unidadeMedida = unidadeMedida; }

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
