package br.com.hbsis.categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepositoy extends JpaRepository<Categoria, Long> {

    Categoria findByCodigoCategoria(String codigoCategoria);
}
