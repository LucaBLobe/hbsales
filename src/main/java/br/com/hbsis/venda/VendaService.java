package br.com.hbsis.venda;


import br.com.hbsis.fornecedor.Fornecedor;
import br.com.hbsis.fornecedor.FornecedorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(br.com.hbsis.venda.VendaService.class);

    private final FornecedorService fornecedorService;
    private IVendaRepository iVendaRepository;

    public VendaService(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }


    public VendaDTO save(VendaDTO vendaDTO) {

        LOGGER.info("Salvando Periodo de vendas");
        LOGGER.debug("Venda: {}", vendaDTO.getId());

        Venda venda = new Venda();
        venda.setInicioVendas(vendaDTO.getInicioVendas());
        venda.setFimVendas(vendaDTO.getFimVrendas());
        venda.setFornecedorId(fornecedorService.findFornecedorById(vendaDTO.getFornecedorId()));
        venda.setRetiradaPedido(vendaDTO.getRetiradaPedido());
        venda.setDescricao(vendaDTO.getDescricao());


        return vendaDTO.of(venda);
    }

    public VendaDTO findById(Long id) {
        Optional<Venda> vendaOptional = this.iVendaRepository.findById(id);
        if (vendaOptional.isPresent()) {
            Venda vendaDTO = vendaOptional.get();

            LOGGER.info("Recebendo find by ID... id: [{}]", VendaDTO.of(vendaOptional.get()));

            return VendaDTO.of(vendaOptional.get());
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));

    }

    public Object findAll() {
        List<Venda> vendaOptional = this.iVendaRepository.findAll();
        return vendaOptional;
    }

    public VendaDTO update(VendaDTO vendaDTO, Long id) {
        Optional<Venda> vendaExistenteOptional = this.iVendaRepository.findById(id);

        if (vendaExistenteOptional.isPresent()) {
            Venda vendaExistente = vendaExistenteOptional.get();

            Venda venda = new Venda();
            venda.setInicioVendas(vendaDTO.getInicioVendas());
            venda.setFimVendas(vendaDTO.getFimVrendas());
            venda.setFornecedorId(fornecedorService.findFornecedorById(vendaDTO.getFornecedorId()));
            venda.setRetiradaPedido(vendaDTO.getRetiradaPedido());
            venda.setDescricao(vendaDTO.getDescricao());

            return vendaDTO;
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public void delete(Long id) {
        LOGGER.info("Executando delete para fornecedor de ID: [{}]", id);
        this.iVendaRepository.deleteById(id);
    }
}
