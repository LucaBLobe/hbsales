package br.com.hbsis.linhaCategoria;

import br.com.hbsis.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILinhaCategoriaRepository extends JpaRepository<Categoria, Long> {
}
