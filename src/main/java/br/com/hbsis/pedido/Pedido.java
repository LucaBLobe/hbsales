package br.com.hbsis.pedido;


import javax.persistence.*;

@Entity
@Table(name = "pedido")
public class Pedido {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "cod_pedido", unique = true, nullable = false, length = 10)
    private String codPedido;

    @Column(name = "status", unique = true, nullable = false, length = 200)
    private String status;




}
