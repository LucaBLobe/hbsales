package br.com.hbsis.funcionario;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FuncionarioService.class);

    private final IFuncionarioRepository iFuncionarioRepository;
    private final EmployeeSavingDTO employeeSavingDTO;

    public FuncionarioService(IFuncionarioRepository iFuncionarioRepository, EmployeeSavingDTO employeeSavingDTO) { this.iFuncionarioRepository = iFuncionarioRepository;
        this.employeeSavingDTO = employeeSavingDTO;
    }

    public FuncionarioDTO save(FuncionarioDTO funcionarioDTO) {

        this.validate(funcionarioDTO);

        LOGGER.info("Salvando funcionário");
        LOGGER.debug("Funcionário: {}", funcionarioDTO);

        Funcionario funcionario = new Funcionario();
        funcionario.setNomeFuncionario(funcionarioDTO.getNomeFuncionario());
        funcionario.setEmail(funcionarioDTO.getEmail());
        funcionario.setUuid(employeeSavingDTO.getEmployeeUuid());

        funcionario = this.iFuncionarioRepository.save(funcionario);

        return funcionarioDTO.of(funcionario);

    }

    private void validate(FuncionarioDTO funcionarioDTO) {
        LOGGER.info("Validando funcionário");

        if (funcionarioDTO == null) {
            throw new IllegalArgumentException("FuncionarioDTO não deve ser nulo");
        }

        if (StringUtils.isEmpty(funcionarioDTO.getEmail())) {
            throw new IllegalArgumentException("Senha não deve ser nula/vazia");
        }

        if (StringUtils.isEmpty(funcionarioDTO.getNomeFuncionario())) {
            throw new IllegalArgumentException("Nome do Funcionario não deve ser nulo/vazio");
        }
    }

    public FuncionarioDTO findById(Long id) {
        Optional<Funcionario> funcionarioOptional = this.iFuncionarioRepository.findById(id);

        if (funcionarioOptional.isPresent()){
            return FuncionarioDTO.of(funcionarioOptional.get());
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public FuncionarioDTO update(FuncionarioDTO funcionarioDTO, Long id) {
        Optional<Funcionario> funcionarioExistenteOptional = this.iFuncionarioRepository.findById(id);

        if (funcionarioExistenteOptional.isPresent()){
            Funcionario funcionarioExistente = funcionarioExistenteOptional.get();

            LOGGER.info("Atualizando Funcionário... id: [{}]",funcionarioExistente.getId());
            LOGGER.debug("Payload: {}", funcionarioDTO);
            LOGGER.debug("Funcionario Existente: {}", funcionarioExistente);

            funcionarioExistente.setNomeFuncionario(funcionarioDTO.getNomeFuncionario());
            funcionarioExistente.setEmail(funcionarioDTO.getEmail());
            funcionarioExistente.setUuid(funcionarioDTO.getUuid());

            funcionarioExistente = this.iFuncionarioRepository.save(funcionarioExistente);

            return funcionarioDTO.of(funcionarioExistente);

        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public void delete(Long id) {
        LOGGER.info("Executando delete para usuário de ID: [{}]", id);

        this.iFuncionarioRepository.deleteById(id);
    }
}
