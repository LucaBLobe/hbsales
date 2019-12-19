package br.com.hbsis.venda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVendaRepository extends JpaRepository<Venda, Long> {


}
