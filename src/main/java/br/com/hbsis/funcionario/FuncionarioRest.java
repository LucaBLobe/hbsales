package br.com.hbsis.funcionario;

import br.com.hbsis.categoria.Categoria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FuncionarioRest.class);

    private final FuncionarioService funcionarioService;
    private final IFuncionarioRepository iFuncionarioRepository;

    @Autowired
    public FuncionarioRest(FuncionarioService funcionarioService, IFuncionarioRepository iFuncionarioRepository) {
        this.funcionarioService = funcionarioService;
        this.iFuncionarioRepository = iFuncionarioRepository;
    }

    @PostMapping
    public FuncionarioDTO save(@RequestBody FuncionarioDTO funcionarioDTO) {
        LOGGER.info("Recebendo solicitação de persistência de funcionário...");
        LOGGER.debug("Payaload: {}", funcionarioDTO);

        return this.funcionarioService.save(funcionarioDTO);
    }
    @GetMapping("/{id}")
    public FuncionarioDTO find(@PathVariable("id") Long id) {

        LOGGER.info("Recebendo find by ID... id: [{}]", id);

        return this.funcionarioService.findById(id);
    }
    @GetMapping
    public List<Funcionario> findAll() {
        return iFuncionarioRepository.findAll();
    }
    @PutMapping("/{id}")
    public FuncionarioDTO udpate(@PathVariable("id") Long id, @RequestBody FuncionarioDTO funcionarioDTO) {
        LOGGER.info("Recebendo Update para Funcionário de ID: {}", id);
        LOGGER.debug("Payload: {}", funcionarioDTO);

        return this.funcionarioService.update(funcionarioDTO, id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("Recebendo Delete para Funcionário de ID: {}", id);

        this.funcionarioService.delete(id);
    }


}
