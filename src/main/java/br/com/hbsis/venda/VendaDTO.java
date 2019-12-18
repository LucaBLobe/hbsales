package br.com.hbsis.venda;

import java.time.LocalDate;

public class VendaDTO {

    private Long id;
    private LocalDate inicioVendas;
    private LocalDate fimVendas;
    private Long fornecedorId;
    private LocalDate retiradaPedido;
    private String descricao;

    public VendaDTO() {
    }

    public VendaDTO(Long id, LocalDate inicioVendas, LocalDate fimVrendas, Long fornecedorId, LocalDate retiradaPedido, String descricao) {
        this.id = id;
        this.inicioVendas = inicioVendas;
        this.fimVendas = fimVrendas;
        this.fornecedorId = fornecedorId;
        this.retiradaPedido = retiradaPedido;
        this.descricao = descricao;
    }

    public Long getId() {        return id; }

    public void setId(Long id) { this.id = id; }

    public LocalDate getInicioVendas() { return inicioVendas; }

    public void setInicioVendas(LocalDate inicioVendas) { this.inicioVendas = inicioVendas; }

    public LocalDate getFimVendas() { return fimVendas; }

    public void setFimVendas(LocalDate fimVrendas) { this.fimVendas = fimVrendas; }

    public Long getFornecedorId() { return fornecedorId; }

    public void setFornecedorId(Long fornecedorId) { this.fornecedorId = fornecedorId; }

    public LocalDate getRetiradaPedido() { return retiradaPedido; }

    public void setRetiradaPedido(LocalDate retiradaPedido) {
        this.retiradaPedido = retiradaPedido; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public static VendaDTO of(Venda venda){

        return new VendaDTO(
                venda.getId(),
                venda.getInicioVendas(),
                venda.getFimVendas(),
                venda.getFornecedorId().getId(),
                venda.getRetiradaPedido(),
                venda.getDescricao()
        );
    }
    @Override
    public String toString() {
        return "Produto{" +
                "ID:" + id +
                "Inicio Vendas= " + inicioVendas + '\'' +
                "Fim Vendas= " + fimVendas + '\'' +
                ", Fornecedor Id='" + fornecedorId + '\'' +
                ", Retirada Pedidos ='" + retiradaPedido + '\'' +
                ", Descrição ='" + descricao + '\'' +
                '}';
    }
}
