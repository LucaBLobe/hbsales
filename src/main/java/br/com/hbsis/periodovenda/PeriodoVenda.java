package br.com.hbsis.periodovenda;


import br.com.hbsis.fornecedor.Fornecedor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "cad_venda")
public class PeriodoVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "inicio_vendas", nullable = false, length = 8)
    private LocalDate inicioVendas;

    @Column(name = "fim_vendas", nullable = false, length = 8)
    private LocalDate fimVendas;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", referencedColumnName = "id")
    private Fornecedor fornecedorId;

    @Column(name = "retirada_pedido", nullable = false, length = 8)
    private LocalDate retiradaPedido;

    @Column(name = "descricao", nullable = false, length = 50)
    private String descricao;

    public PeriodoVenda(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getInicioVendas() {
        return inicioVendas;
    }

    public void setInicioVendas(LocalDate inicioVendas) {
        this.inicioVendas = inicioVendas;
    }

    public LocalDate getFimVendas() {
        return fimVendas;
    }

    public void setFimVendas(LocalDate fimVendas) { this.fimVendas = fimVendas; }

    public Fornecedor getFornecedorId() {  return fornecedorId; }

    public void setFornecedorId(Fornecedor fornecedorId) { this.fornecedorId = fornecedorId; }

    public LocalDate getRetiradaPedido() {
        return retiradaPedido;
    }

    public void setRetiradaPedido(LocalDate retiradaPedido) { this.retiradaPedido = retiradaPedido; }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodoVenda periodoVenda = (PeriodoVenda) o;
        return Objects.equals(id, periodoVenda.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, inicioVendas, fimVendas, fornecedorId, retiradaPedido, descricao);
    }
}