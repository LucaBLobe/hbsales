package br.com.hbsis.fornecedor;

import br.com.hbsis.categoria.ICategoriaRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FornecedorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FornecedorService.class);

    private final IFornecedorRepository iFornecedorRepository;
    private final ICategoriaRepository iCategoriaRepository;



    public FornecedorService(IFornecedorRepository iFornecedorRepository, ICategoriaRepository iCategoriaRepository) {
        this.iFornecedorRepository = iFornecedorRepository;

        this.iCategoriaRepository = iCategoriaRepository;
    }

    public FornecedorDTO save(FornecedorDTO fornecedorDTO) {
        this.validate(fornecedorDTO);

        LOGGER.info("Salvando fornecedor");
        LOGGER.debug("Usuario: {}", fornecedorDTO);

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setRazaoSocial(fornecedorDTO.getRazaoSocial());
        fornecedor.setCnpj(fornecedorDTO.getCnpj());
        fornecedor.setEmail(fornecedorDTO.getEmail());
        fornecedor.setNomeFantasia(fornecedorDTO.getNomeFantasia());
        fornecedor.setEndereco(fornecedorDTO.getEndereco());
        fornecedor.setTelefoneContato(fornecedorDTO.getTelefoneContato());

        fornecedor = this.iFornecedorRepository.save(fornecedor);

        return fornecedorDTO.of(fornecedor);
    }

    private void validate(FornecedorDTO fornecedorDTO) {
        LOGGER.info("Validando Fornecedor");

        if (fornecedorDTO == null) {
            throw new IllegalArgumentException("FornecedorDTO não deve ser nulo");
        }
        if (StringUtils.isEmpty(fornecedorDTO.getRazaoSocial())) {
            throw new IllegalArgumentException("Razão Social não deve ser nula/vazia");
        }
        if (StringUtils.isEmpty(fornecedorDTO.getNomeFantasia())) {
            throw new IllegalArgumentException("Nome Fantasia não deve ser nula/vazia");
        }
        if (StringUtils.isEmpty(fornecedorDTO.getEndereco())) {
            throw new IllegalArgumentException("Endereço não deve ser nula/vazia");
        }
        if (StringUtils.isEmpty(fornecedorDTO.getCnpj())) {
            throw new IllegalArgumentException("CNPJ não deve ser nula/vazia");
        }
        if (!(StringUtils.isNumeric(fornecedorDTO.getCnpj()))) {
            throw new IllegalArgumentException("CNPJ deve ser apenas numeros");
        }
        if (fornecedorDTO.getCnpj().length() != 14) {
            throw new IllegalArgumentException("CNPJ deve ter 14 digitos");
        }
        if (StringUtils.isEmpty(fornecedorDTO.getEmail())) {
            throw new IllegalArgumentException("E-mail não deve ser nula/vazia");
        }
        if (StringUtils.isEmpty(fornecedorDTO.getTelefoneContato())) {
            throw new IllegalArgumentException("Telefone de contato não deve ser nula/vazia");
        }
        if (!(StringUtils.isNumeric(fornecedorDTO.getTelefoneContato()))) {
            throw new IllegalArgumentException("Telefone não pode conter letras.");
        }

        if (!(Integer.parseInt(String.valueOf(fornecedorDTO.getTelefoneContato().charAt(4))) == 9)) {

            throw new IllegalArgumentException("Telefone de contato deve ser numero de celular.");
        }
        if (fornecedorDTO.getTelefoneContato().length() > 14) {
            throw new IllegalArgumentException("Telefone com numerção incorreta");
        }
    }
    public FornecedorDTO findById(Long id) {
        Optional<Fornecedor> fornecedorOptional = this.iFornecedorRepository.findById(id);
        if (fornecedorOptional.isPresent()) {
            Fornecedor fornecedor = fornecedorOptional.get();
            LOGGER.info("Recebendo find by ID... id: [{}]",FornecedorDTO.of(fornecedor));
            return FornecedorDTO.of(fornecedor);
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public Fornecedor findFornecedorById(Long id) {
        Optional<Fornecedor> fornecedorOptional = this.iFornecedorRepository.findById(id);
        if (fornecedorOptional.isPresent()) {
            return fornecedorOptional.get();
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }
    public FornecedorDTO update(FornecedorDTO fornecedorDTO, Long id) {
        Optional<Fornecedor> fornecedorExistenteOptional = this.iFornecedorRepository.findById(id);


        if (fornecedorExistenteOptional.isPresent()) {
            this.validate(fornecedorDTO);

            Fornecedor fornecedorExistente = fornecedorExistenteOptional.get();

            LOGGER.info("Atualizado fornecedor... id: [{}]", fornecedorExistente.getId());
            LOGGER.debug("Payload: {}", fornecedorDTO);
            LOGGER.debug("Fornecedor Existente: {}", fornecedorExistente);

            fornecedorExistente.setRazaoSocial(fornecedorDTO.getRazaoSocial());
            fornecedorExistente.setNomeFantasia(fornecedorDTO.getNomeFantasia());
            fornecedorExistente.setEndereco(fornecedorDTO.getEndereco());
            fornecedorExistente.setTelefoneContato(fornecedorDTO.getTelefoneContato());
            fornecedorExistente.setCnpj(fornecedorDTO.getCnpj());
            fornecedorExistente.setEmail(fornecedorDTO.getEmail());
            fornecedorExistente = this.iFornecedorRepository.save(fornecedorExistente);

            return FornecedorDTO.of(fornecedorExistente);
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public void delete(Long id) {
        LOGGER.info("Executando delete para fornecedor de ID: [{}]", id);
        this.iFornecedorRepository.deleteById(id);
    }

    public Fornecedor findByCnpj(String cnpj) {
        Optional<Fornecedor> fornecedorOptional = Optional.ofNullable(this.iFornecedorRepository.findByCnpj(cnpj));
        if (fornecedorOptional.isPresent()) {
            return fornecedorOptional.get();
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", cnpj));
    }
}
