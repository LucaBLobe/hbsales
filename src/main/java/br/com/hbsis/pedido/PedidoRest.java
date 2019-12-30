package br.com.hbsis.pedido;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(br.com.hbsis.pedido.PedidoRest.class);

    private final PedidoService pedidoService;
    private final IPedidoRepository iPedidoRepository;


    @Autowired
    public PedidoRest(PedidoService pedidoService, IPedidoRepository iPedidoRepository) {
        this.pedidoService = pedidoService;
        this.iPedidoRepository = iPedidoRepository;
    }
    @PostMapping("/{id}")
    public PedidoDTO save(@PathVariable("id") PedidoDTO pedidoDTO) {
        LOGGER.info("Resebendo Solicitação de persistencia do Pedido...");
        LOGGER.debug("Payload {}", pedidoDTO);

        return this.pedidoService.save(pedidoDTO);
    }
    @GetMapping("/{id}")
    public PedidoDTO find(@PathVariable("id") Long id) {

        LOGGER.info("Recebendo find by ID... id: [{}]", id);
        return this.pedidoService.findById(id);

    }
    @GetMapping
    public List<Pedido> findAll() {
        LOGGER.info("Recebendp find by ID... id: [{}]");
        return iPedidoRepository.findAll();
    }
    @PutMapping("/{id}")
    public PedidoDTO update(@PathVariable("id") Long id, @RequestBody PedidoDTO pedidoDTO) {
        LOGGER.info("Recebendo Upadate para Pedido de ID: {}", id);
        LOGGER.debug("Payload {}", pedidoDTO);
        return this.pedidoService.update(pedidoDTO, id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("Recebendo Delete para Pedido de ID: {}", id);
        this.pedidoService.delete(id);
    }
}
