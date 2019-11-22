package br.com.hbsis.fornecedor;

import br.com.hbsis.usuario.UsuarioDTO;
import br.com.hbsis.usuario.UsuarioService;
import com.sun.javafx.logging.JFRInputEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/fornecedores")
public class FornecedorRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(FornecedorRest.class);

    private FornecedorService fornecedorService = null;

    @Autowired
    public FornecedorRest(FornecedorService fornecedorService) {this.fornecedorService = fornecedorService;}

    @PostMapping
    public FornecedorDTO save(@RequestBody FornecedorDTO fornecedorDTO){
        LOGGER.info("Resebendo solicitação de persistência de fornecedor...");
        LOGGER.debug("Payaload {}", fornecedorDTO);

        return this.fornecedorService.save(fornecedorDTO);
    }

    @GetMapping("/{id}")
    public FornecedorDTO find(@PathVariable("id") Long id) {

        LOGGER.info("Recebendo find by ID... id: [{}]",id);
        return this.fornecedorService.findById(id);
    }

    @PutMapping("/{id}")
    public FornecedorDTO update(@PathVariable("id") Long id @RequestBody FornecedorDTO fornecedorDTO) {
        LOGGER.info("Recebendo Update para Dornecedor de ID: {}", id);
        LOGGER.debug("Payload: {}", fornecedorDTO);
        return this.fornecedorService.update(fornecedorDTO, id);

    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("Recebendo Delete para Usuário de ID: {}", id);

        this.fornecedorService.delete(id);
    }
}
