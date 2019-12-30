package br.com.hbsis.venda;


import br.com.hbsis.categoria.Categoria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(br.com.hbsis.venda.VendaRest.class);

    private VendaService vendaService;
    private final IVendaRepository iVendaRepository;


    @Autowired
    public VendaRest(VendaService vendaService, IVendaRepository iVendaRepository) {
        this.vendaService = vendaService;
        this.iVendaRepository = iVendaRepository;
    }
    @PostMapping
    public VendaDTO save(@RequestBody VendaDTO vendaDTO) {
        LOGGER.info("Resebendo Solicitação de persistencia de Venda...");
        LOGGER.debug("Payload {}", vendaDTO);

        return this.vendaService.save(vendaDTO);
    }
    @GetMapping("/{id}")
    public VendaDTO find(@PathVariable("id") Long id) {

        LOGGER.info("Recebendo find by ID... id: [{}]", id);
        return this.vendaService.findById(id);
    }

    @GetMapping
    public List<Venda> findAll() {
        return iVendaRepository.findAll();
    }

    @PutMapping("/{id}")
    public VendaDTO update(@PathVariable("id") Long id, @RequestBody VendaDTO vendaDTO) {
        LOGGER.info("Recebendo Upadate para linha de categoria de ID: {}", id);
        LOGGER.debug("Payload {}", vendaDTO);
        return this.vendaService.update(vendaDTO, id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("Recebendo Delete para linha de categoria de ID: {}", id);
        this.vendaService.delete(id);
    }
}
