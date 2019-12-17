package br.com.hbsis.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByCodProduto(String codProduto);

}
