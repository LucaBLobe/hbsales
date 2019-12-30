package br.com.hbsis.periodovenda;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PeriodoVendaDTO {

    private Long id;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate inicioVendas;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fimVendas;
    private Long fornecedorId;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate retiradaPedido;
    private String descricao;

    public PeriodoVendaDTO() {
    }

    public PeriodoVendaDTO(Long id, LocalDate inicioVendas, LocalDate fimVrendas, Long fornecedorId, LocalDate retiradaPedido, String descricao) {
        this.id = id;
        this.inicioVendas = inicioVendas;
        this.fimVendas = fimVrendas;
        this.fornecedorId = fornecedorId;
        this.retiradaPedido = retiradaPedido;
        this.descricao = descricao;
    }

    public static PeriodoVendaDTO of(PeriodoVenda periodoVenda){

        return new PeriodoVendaDTO(
                periodoVenda.getId(),
                periodoVenda.getInicioVendas(),
                periodoVenda.getFimVendas(),
                periodoVenda.getFornecedorId().getId(),
                periodoVenda.getRetiradaPedido(),
                periodoVenda.getDescricao()
        );
    }

    public Long getId() {   return id; }

    public void setId(Long id) { this.id = id; }

    public LocalDate getInicioVendas() { return inicioVendas; }

    public void setInicioVendas(LocalDate inicioVendas) { this.inicioVendas = inicioVendas; }

    public LocalDate getFimVendas() { return fimVendas; }

    public void setFimVendas(LocalDate fimVendas) { this.fimVendas = fimVendas; }

    public Long getFornecedorId() { return fornecedorId; }

    public void setFornecedorId(Long fornecedorId) { this.fornecedorId = fornecedorId; }

    public LocalDate getRetiradaPedido() { return retiradaPedido; }

    public void setRetiradaPedido(LocalDate retiradaPedido) { this.retiradaPedido = retiradaPedido; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }


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
