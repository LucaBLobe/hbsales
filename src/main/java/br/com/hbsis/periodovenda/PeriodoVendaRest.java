package br.com.hbsis.periodovenda;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/periodo_vendas")
public class PeriodoVendaRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodoVendaRest.class);

    private PeriodoVendaService periodoVendaService;
    private final IPeriodoVendaRepository iPeriodoVendaRepository;


    @Autowired
    public PeriodoVendaRest(PeriodoVendaService periodoVendaService, IPeriodoVendaRepository iPeriodoVendaRepository) {
        this.periodoVendaService = periodoVendaService;
        this.iPeriodoVendaRepository = iPeriodoVendaRepository;
    }
    @PostMapping
    public PeriodoVendaDTO save(@RequestBody PeriodoVendaDTO periodoVendaDTO) {
        LOGGER.info("Resebendo Solicitação de persistencia de Venda...");
        LOGGER.debug("Payload {}", periodoVendaDTO);

        return this.periodoVendaService.save(periodoVendaDTO);
    }
    @GetMapping("/{id}")
    public PeriodoVendaDTO find(@PathVariable("id") Long id) {

        LOGGER.info("Recebendo find by ID... id: [{}]", id);
        return this.periodoVendaService.findById(id);
    }

    @GetMapping
    public List<PeriodoVenda> findAll() {
        return iPeriodoVendaRepository.findAll();
    }

    @PutMapping("/{id}")
    public PeriodoVendaDTO update(@PathVariable("id") Long id, @RequestBody PeriodoVendaDTO periodoVendaDTO) {
        LOGGER.info("Recebendo Upadate para linha de categoria de ID: {}", id);
        LOGGER.debug("Payload {}", periodoVendaDTO);
        return this.periodoVendaService.update(periodoVendaDTO, id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("Recebendo Delete para linha de categoria de ID: {}", id);
        this.periodoVendaService.delete(id);
    }
}
