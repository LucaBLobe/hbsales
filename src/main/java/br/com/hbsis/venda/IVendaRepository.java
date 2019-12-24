package br.com.hbsis.venda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface IVendaRepository extends JpaRepository<Venda, Long> {

    Optional<Venda> findFornecedorById(Long fornecedorId);

 //  @Query("SELECT u FROM User u WHERE u.status = 1")
 //   Collection<Venda> findAllActiveUsers();

}
