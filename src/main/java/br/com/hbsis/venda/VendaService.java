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

    public Venda findFornecedorById(Long fornecedorId) {
        Optional<Venda> vendaOptional = this.iVendaRepository.findFornecedorById(fornecedorId);
        if (vendaOptional.isPresent()) {
            return vendaOptional.get();
        }
        throw new IllegalArgumentException(String.format("ID %s não esxiste", fornecedorId));

    }

    private void validate(VendaDTO vendaDTO) {
        LOGGER.info("Validando vendas");

        if (vendaDTO == null) {
            throw new IllegalArgumentException("VendaDTO não deve ser nulo");
        }
        if (StringUtils.isEmpty(vendaDTO.getInicioVendas().toString())) {
            throw new IllegalArgumentException("Inicio vendas não deve ser nula/vazia");
        }
        if (StringUtils.isEmpty(vendaDTO.getFimVendas().toString())) {
            throw new IllegalArgumentException("Fim vendas não deve ser nula/vazia");
        }
        if (StringUtils.isEmpty(vendaDTO.getRetiradaPedido().toString())) {
            throw new IllegalArgumentException("Retirada pedido não deve ser nula/vazia");
        }
        if (StringUtils.isEmpty(vendaDTO.getFornecedorId().toString())) {
            throw new IllegalArgumentException("fornecedor Id não deve ser nula/vazia");
        }
        if (StringUtils.isEmpty(vendaDTO.getDescricao())) {
            throw new IllegalArgumentException("Descrição não deve ser nula/vazia");
        }
        if (vendaDTO.getInicioVendas().isAfter(vendaDTO.getFimVendas())) {
            throw new IllegalArgumentException("Data inicio não pode ser posterior a data final de vendas.");
        }
        if (vendaDTO.getInicioVendas().isAfter(vendaDTO.getRetiradaPedido())) {
            throw new IllegalArgumentException("Data inicio não pode ser posterior a data de retirada.");
        }
        if (StringUtils.isEmpty(String.valueOf(vendaDTO.getFimVendas().isAfter(vendaDTO.getRetiradaPedido())))) {
            throw new IllegalArgumentException("Data fim de vendas não pode ser posterior a data de retirada.");
        }


        List<Venda> listaExistente = iVendaRepository.findAll();
        for (Venda venda : listaExistente) {
            LOGGER.info(venda.getFornecedorId().toString());
            LOGGER.info(venda.getInicioVendas().toString());
            LOGGER.info(venda.getFimVendas().toString());
            LOGGER.info(vendaDTO.getFornecedorId().toString());
            if (venda.getFornecedorId().getId().equals(vendaDTO.getFornecedorId())) {
                if (!(vendaDTO.getInicioVendas().isBefore(venda.getInicioVendas()) && vendaDTO.getFimVendas().isBefore(venda.getInicioVendas())) && !(vendaDTO.getInicioVendas().isAfter(venda.getFimVendas()) && vendaDTO.getFimVendas().isAfter(venda.getFimVendas()))) {
                    throw new IllegalArgumentException("Periodo invalido para cadastrar Periodo para fornecedor");
                } else {
                    continue;
                }
            } else {
                continue;
            }

        }

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
