package br.com.hbsis.pedido;


import br.com.hbsis.venda.VendaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(br.com.hbsis.pedido.PedidoRest.class);

    private PedidoService pedidoService;

    @Autowired
    public PedidoRest(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
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
