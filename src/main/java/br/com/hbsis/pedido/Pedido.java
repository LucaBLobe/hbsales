package br.com.hbsis.pedido;


import br.com.hbsis.fornecedor.Fornecedor;
import br.com.hbsis.produto.Produto;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pedido")
public class Pedido {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod_pedido", unique = true, nullable = false, length = 10)
    private String codPedido;

    @Column(name = "status", unique = true, nullable = false, length = 25)
    private StatusPedido status;

    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produtoId;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", referencedColumnName = "id")
    private Fornecedor fornecedorId;

    @Column(name = "criacao_pedido", nullable = false, length = 8)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate criacaoPedido;


    public Pedido() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(String codPedido) {
        this.codPedido = codPedido;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public Fornecedor getFornecedorId() {
        return fornecedorId;
    }

    public Produto getProdutoId() { return produtoId; }

    public void setProdutoId(Produto produtoId) { this.produtoId = produtoId; }

    public void setFornecedorId(Fornecedor fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public LocalDate getCriacaoPedido() { return criacaoPedido; }

    public void setCriacaoPedido(LocalDate criacaoPedido) {
        this.criacaoPedido = criacaoPedido;
    }

    @Override
    public String toString() {
        return"Pedido{"+
                "ID:"+id +
                "Codigo Pedido: "+ codPedido +'\''+
                ", Status:'"+ status +'\''+
                ", Criação Pedido:'"+ criacaoPedido +'\''+
                ", Produto ID:'"+ produtoId +'\''+
                ", Forncededor ID:'"+ fornecedorId +'\''+
                '}';
    }
}