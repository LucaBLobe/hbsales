package br.com.hbsis.funcionario;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class FuncionarioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FuncionarioService.class);

    private final IFuncionarioRepository iFuncionarioRepository;


    public FuncionarioService(IFuncionarioRepository iFuncionarioRepository) {
        this.iFuncionarioRepository = iFuncionarioRepository;

    }

    public FuncionarioDTO save(FuncionarioDTO funcionarioDTO) {

        this.validate(funcionarioDTO);
        this.createEmployee(funcionarioDTO);

        LOGGER.info("Salvando funcionário");
        LOGGER.debug("Funcionário: {}", funcionarioDTO);

        Funcionario funcionario = new Funcionario();
        funcionario.setNomeFuncionario(funcionarioDTO.getNome());
        funcionario.setEmail(funcionarioDTO.getEmail());
        funcionario.setUuid(UUID.randomUUID().toString());



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

        if (StringUtils.isEmpty(funcionarioDTO.getNome())) {
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

            funcionarioExistente.setNomeFuncionario(funcionarioDTO.getNome());
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

    private void createEmployee(FuncionarioDTO funcionarioDTO){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "f59ff1b4-1b67-11ea-978f-2e728ce88125");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(funcionarioDTO, headers);
        ResponseEntity<EmployeeSavingDTO> resultadoEmployee = restTemplate.exchange(
                "http://10.2.54.25:9999/api/employees",  HttpMethod.POST, entity, EmployeeSavingDTO.class);
        funcionarioDTO.setUuid(Objects.requireNonNull(resultadoEmployee.getBody().getEmployeeUuid()));
    }
}
