package br.com.hbsis.periodovenda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPeriodoVendaRepository extends JpaRepository<PeriodoVenda, Long> {

    Optional<PeriodoVenda> findFornecedorById(Long fornecedorId);

 //  @Query("SELECT u FROM User u WHERE u.status = 1")
 //   Collection<Venda> findAllActiveUsers();

}
