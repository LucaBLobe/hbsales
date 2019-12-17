package br.com.hbsis.venda;


import br.com.hbsis.fornecedor.FornecedorService;
import br.com.hbsis.produto.Produto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VendaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(br.com.hbsis.venda.VendaService.class);

    private final FornecedorService fornecedorService;

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
    }

    public Object findAll() {
    }

    public VendaDTO update(VendaDTO vendaDTO, Long id) {
        Optional<Venda> vendaExistenteOptional = this.iVendaRepository.findById(id);
    }

    public void delete(Long id) {
    }
}
