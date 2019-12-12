package br.com.hbsis.produto;

import br.com.hbsis.linhaCategoria.LinhaCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProdutoRepository extends JpaRepository<Produto, Long> {



}
