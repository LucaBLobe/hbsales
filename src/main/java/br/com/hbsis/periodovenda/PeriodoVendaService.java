package br.com.hbsis.periodovenda;


import br.com.hbsis.fornecedor.FornecedorService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodoVendaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodoVendaService.class);

    private final FornecedorService fornecedorService;
    private final IPeriodoVendaRepository iPeriodoVendaRepository;

    public PeriodoVendaService(FornecedorService fornecedorService, IPeriodoVendaRepository iPeriodoVendaRepository) {
        this.fornecedorService = fornecedorService;
        this.iPeriodoVendaRepository = iPeriodoVendaRepository;
    }


    public PeriodoVendaDTO save(PeriodoVendaDTO periodoVendaDTO) {
        this.validate(periodoVendaDTO);
        LOGGER.info("Salvando Periodo de vendas");
        LOGGER.debug("Venda: {}", periodoVendaDTO.getId());

        PeriodoVenda periodoVenda = new PeriodoVenda();
        periodoVenda.setInicioVendas(periodoVendaDTO.getInicioVendas());
        periodoVenda.setFimVendas(periodoVendaDTO.getFimVendas());
        periodoVenda.setFornecedorId(fornecedorService.findFornecedorById(periodoVendaDTO.getFornecedorId()));
        periodoVenda.setRetiradaPedido(periodoVendaDTO.getRetiradaPedido());
        periodoVenda.setDescricao(periodoVendaDTO.getDescricao());

        periodoVenda = this.iPeriodoVendaRepository.save(periodoVenda);

        return periodoVendaDTO.of(periodoVenda);
    }


    private void validate(PeriodoVendaDTO periodoVendaDTO) {
        LOGGER.info("Validando vendas");

        if (periodoVendaDTO == null) {
            throw new IllegalArgumentException("VendaDTO não deve ser nulo");
        }
        if (StringUtils.isEmpty(periodoVendaDTO.getInicioVendas().toString())) {
            throw new IllegalArgumentException("Inicio vendas não deve ser nula/vazia");
        }
        if (StringUtils.isEmpty(periodoVendaDTO.getFimVendas().toString())) {
            throw new IllegalArgumentException("Fim vendas não deve ser nula/vazia");
        }
        if (StringUtils.isEmpty(periodoVendaDTO.getRetiradaPedido().toString())) {
            throw new IllegalArgumentException("Retirada pedido não deve ser nula/vazia");
        }
        if (StringUtils.isEmpty(periodoVendaDTO.getFornecedorId().toString())) {
            throw new IllegalArgumentException("fornecedor Id não deve ser nula/vazia");
        }
        if (StringUtils.isEmpty(periodoVendaDTO.getDescricao())) {
            throw new IllegalArgumentException("Descrição não deve ser nula/vazia");
        }
        if (periodoVendaDTO.getInicioVendas().isAfter(periodoVendaDTO.getFimVendas())) {
            throw new IllegalArgumentException("Data inicio não pode ser posterior a data final de vendas.");
        }
        if (periodoVendaDTO.getInicioVendas().isAfter(periodoVendaDTO.getRetiradaPedido())) {
            throw new IllegalArgumentException("Data inicio não pode ser posterior a data de retirada.");
        }
        if (StringUtils.isEmpty(String.valueOf(periodoVendaDTO.getFimVendas().isAfter(periodoVendaDTO.getRetiradaPedido())))) {
            throw new IllegalArgumentException("Data fim de vendas não pode ser posterior a data de retirada.");
        }


        List<PeriodoVenda> listaExistente = iPeriodoVendaRepository.findAll();
        for (PeriodoVenda periodoVenda : listaExistente) {
            LOGGER.info(periodoVenda.getFornecedorId().toString());
            LOGGER.info(periodoVenda.getInicioVendas().toString());
            LOGGER.info(periodoVenda.getFimVendas().toString());
            LOGGER.info(periodoVendaDTO.getFornecedorId().toString());
            if (periodoVenda.getFornecedorId().getId().equals(periodoVendaDTO.getFornecedorId())) {
                if (!(periodoVendaDTO.getInicioVendas().isBefore(periodoVenda.getInicioVendas()) && periodoVendaDTO.getFimVendas().isBefore(periodoVenda.getInicioVendas())) && !(periodoVendaDTO.getInicioVendas().isAfter(periodoVenda.getFimVendas()) && periodoVendaDTO.getFimVendas().isAfter(periodoVenda.getFimVendas()))) {
                    throw new IllegalArgumentException("Periodo invalido para cadastrar Periodo para fornecedor");
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }
    }

    public PeriodoVendaDTO findById(Long id) {
        Optional<PeriodoVenda> vendaOptional = this.iPeriodoVendaRepository.findById(id);
        if (vendaOptional.isPresent()) {
            PeriodoVenda periodoVendaDTO = vendaOptional.get();

            LOGGER.info("Recebendo find by ID... id: [{}]", PeriodoVendaDTO.of(vendaOptional.get()));

            return PeriodoVendaDTO.of(vendaOptional.get());
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));

    }



    public PeriodoVendaDTO update(PeriodoVendaDTO periodoVendaDTO, Long id) {
        Optional<PeriodoVenda> vendaExistenteOptional = this.iPeriodoVendaRepository.findById(id);

        if (vendaExistenteOptional.isPresent()) {
            PeriodoVenda periodoVendaExistente = vendaExistenteOptional.get();

            periodoVendaExistente.setInicioVendas(periodoVendaDTO.getInicioVendas());
            periodoVendaExistente.setFimVendas(periodoVendaDTO.getFimVendas());
            periodoVendaExistente.setFornecedorId(fornecedorService.findFornecedorById(periodoVendaDTO.getFornecedorId()));
            periodoVendaExistente.setRetiradaPedido(periodoVendaDTO.getRetiradaPedido());
            periodoVendaExistente.setDescricao(periodoVendaDTO.getDescricao());

            return periodoVendaDTO.of(periodoVendaExistente);
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }
    public void delete(Long id) {
        LOGGER.info("Executando delete para fornecedor de ID: [{}]", id);
        this.iPeriodoVendaRepository.deleteById(id);
    }
}
