package br.com.hbsis.venda;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendas")
public class VendaRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(br.com.hbsis.venda.VendaRest.class);

    private VendaService vendaService;

    @Autowired
    public VendaRest(VendaRest vendaRest) {
        this.vendaService = vendaService;
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
    @RequestMapping("/all")
    public VendaDTO findAll() {

        LOGGER.info("Recebendp find by ID... id: [{}]");
        return (VendaDTO) this.vendaService.findAll();
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
