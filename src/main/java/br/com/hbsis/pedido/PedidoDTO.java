package br.com.hbsis.pedido;

import java.time.LocalDate;

public class PedidoDTO {

    private Long id;
    private String codPedido;
    private StatusPedido status;
    private Long produtoId;
    private Long fornecedorId;
    private LocalDate criacaoPedido;

    public PedidoDTO() {
    }

    public static PedidoDTO of(Pedido pedido) {
        return new PedidoDTO(

        );

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

    public Long getProdutoId() { return produtoId; }

    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public LocalDate getCriacaoPedido() {
        return criacaoPedido;
    }

    public void setCriacaoPedido(LocalDate criacaoPedido) {
        this.criacaoPedido = criacaoPedido;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "ID:" + id +
                "Codigo Pedido: " + codPedido + '\'' +
                ", Status:'" + status + '\'' +
                ", Criação Pedido:'" + criacaoPedido + '\'' +
                ", Forncededor ID:'" + fornecedorId + '\'' +
                '}';
    }
}
