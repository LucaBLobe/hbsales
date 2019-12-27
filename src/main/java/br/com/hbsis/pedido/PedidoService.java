package br.com.hbsis.pedido;

import br.com.hbsis.fornecedor.FornecedorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PedidoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(br.com.hbsis.pedido.PedidoService.class);

    private final FornecedorService fornecedorService;
    private final IPedidoRepository iPedidoRepository;

    public PedidoService(FornecedorService fornecedorService, IPedidoRepository iPedidoRepository) {
        this.fornecedorService = fornecedorService;
        this.iPedidoRepository = iPedidoRepository;
    }

    public PedidoDTO save(PedidoDTO pedidoDTO) {


        Pedido pedido = new Pedido();
        pedido.setCodPedido(pedidoDTO.getCodPedido());
        pedido.setFornecedorId(fornecedorService.findFornecedorById(pedidoDTO.getFornecedorId()));
        pedido.setCriacaoPedido(LocalDate.now());
        pedido.setStatus(StatusPedido.ATIVO);

        pedido = this.iPedidoRepository.save(pedido);
        return pedidoDTO.of(pedido);
    }
    public PedidoDTO findById(Long id) {
        Optional<Pedido> pedidoOptional = this.iPedidoRepository.findById(id);
        if (pedidoOptional.isPresent()) {
            Pedido pedidoDTO = pedidoOptional.get();

            LOGGER.info("Recebendo find by ID... id: [{}]", PedidoDTO.of(pedidoOptional.get()));

            return PedidoDTO.of(pedidoOptional.get());
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public PedidoDTO update(PedidoDTO pedidoDTO, Long id) {
        Optional<Pedido> pedidoExistenteOptional = this.iPedidoRepository.findById(id);

        if (pedidoExistenteOptional.isPresent()) {
            Pedido pedidoExistente = pedidoExistenteOptional.get();

            pedidoExistente.setCodPedido(pedidoDTO.getCodPedido());
            pedidoExistente.setFornecedorId(fornecedorService.findFornecedorById(pedidoDTO.getFornecedorId()));
            pedidoExistente.setCriacaoPedido(LocalDate.now());

            pedidoExistente = this.iPedidoRepository.save(pedidoExistente);
            return pedidoDTO.of(pedidoExistente);
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }
    public void delete(Long id) {
        LOGGER.info("Executando delete para Pedido ID: [{}]", id);
        this.iPedidoRepository.deleteById(id);
    }
}
