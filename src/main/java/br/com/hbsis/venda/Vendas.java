package br.com.hbsis.venda;


import br.com.hbsis.fornecedor.Fornecedor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cad_vendas")
public class Vendas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "inicio_vendas", nullable = false, length = 8)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate inicioVendas;

    @Column(name = "fim_vendas", nullable = false, length = 8)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fimVendas;

    @ManyToOne
    @JoinColumn(name = "fornecedor_Id", referencedColumnName = "id")
    private Fornecedor fornecedorId;

    @Column(name = "Retirada_pedido", nullable = false, length = 8)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate RetiradaPedido;

    @Column(name = "Descricao", nullable = false, length = 50)
    private String descricao;

    public Vendas(){

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

    public void setFimVendas(LocalDate fimVendas) {
        this.fimVendas = fimVendas;
    }

    public Fornecedor getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Fornecedor fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public LocalDate getRetiradaPedido() {
        return RetiradaPedido;
    }

    public void setRetiradaPedido(LocalDate retiradaPedido) {
        RetiradaPedido = retiradaPedido;
    }

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
                "Codigo Categoria= " + ''codigoCategoria + '\'' +
                ", Fornecedor Id='" + fornecedorId + '\'' +
                ", Nome Categoria ='" + nomeCategoria + '\'' +
                '}';
    }

}
