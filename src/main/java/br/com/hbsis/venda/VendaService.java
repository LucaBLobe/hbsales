package br.com.hbsis.venda;


import br.com.hbsis.fornecedor.FornecedorService;
import org.apache.commons.lang.StringUtils;
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

    public VendaService(FornecedorService fornecedorService, IVendaRepository iVendaRepository) {
        this.fornecedorService = fornecedorService;
        this.iVendaRepository = iVendaRepository;
    }


    public VendaDTO save(VendaDTO vendaDTO) {
        this.validate(vendaDTO);
        LOGGER.info("Salvando Periodo de vendas");
        LOGGER.debug("Venda: {}", vendaDTO.getId());

        Venda venda = new Venda();
        venda.setInicioVendas(vendaDTO.getInicioVendas());
        venda.setFimVendas(vendaDTO.getFimVendas());
        venda.setFornecedorId(fornecedorService.findFornecedorById(vendaDTO.getFornecedorId()));
        venda.setRetiradaPedido(vendaDTO.getRetiradaPedido());
        venda.setDescricao(vendaDTO.getDescricao());

        venda = this.iVendaRepository.save(venda);

        return vendaDTO.of(venda);


    }

    private void validate(VendaDTO vendaDTO) {
        LOGGER.info("Validando Fornecedor");

        if (vendaDTO == null) {
            throw new IllegalArgumentException("VendaDTO não deve ser nulo");
        }
        if (vendaDTO.getInicioVendas().isAfter(vendaDTO.getFimVendas())) {
            throw new IllegalArgumentException("Data inicio não pode ser posterior a data final de vendas.");
        }
        if (StringUtils.isEmpty(String.valueOf(vendaDTO.getInicioVendas().isAfter(vendaDTO.getRetiradaPedido())))) {
            throw new IllegalArgumentException("Data inicio não pode ser posterior a data de retirada.");
        }
        if (StringUtils.isEmpty(String.valueOf(vendaDTO.getFimVendas().isAfter(vendaDTO.getRetiradaPedido())))) {
            throw new IllegalArgumentException("Data fim de vendas não pode ser posterior a data de retirada.");
        }
      //  if (StringUtils.isEmpty(fornecedorService.findFornecedorById(vendaDTO.getFornecedorId())(vendaDTO.getFornecedorId()))) {
//
       //     throw new IllegalArgumentException("Nome Fantasia não deve ser nula/vazia");
       // }
      //  if (StringUtils.isEmpty(fornecedorDTO.getEndereco())) {
     //       throw new IllegalArgumentException("Endereço não deve ser nula/vazia");
      //  }
      //  if (StringUtils.isEmpty(fornecedorDTO.getCnpj())) {
     //       throw new IllegalArgumentException("CNPJ não deve ser nula/vazia");
      //  }



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
            venda.setFimVendas(vendaDTO.getFimVendas());
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
