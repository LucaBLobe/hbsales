package br.com.hbsis.produto;

import br.com.hbsis.linhaCategoria.LinhaCategoriaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")

public class ProdutoRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(br.com.hbsis.produto.ProdutoRest.class);

    private ProdutoService produtoService;

    @Autowired
    public ProdutoRest(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
    @PostMapping
    public ProdutoDTO save(@RequestBody ProdutoDTO produtoDTO) {
        LOGGER.info("Resebendo Solicitação de persistencia de categoria...");
        LOGGER.debug("Payload {}", produtoDTO);

        return this.produtoService.save(produtoDTO);
    }

    @GetMapping("/{id}")
    public ProdutoDTO find(@PathVariable("id") Long id) {

        LOGGER.info("Recebendo find by ID... id: [{}]", id);
        return this.produtoService.findById(id);
    }
    @RequestMapping("/all")
    public ProdutoDTO findAll() {

        LOGGER.info("Recebendp find by ID... id: [{}]");
        return (ProdutoDTO) this.produtoService.findAll();
    }
    @PutMapping("/{id}")
    public ProdutoDTO update(@PathVariable("id") Long id, @RequestBody ProdutoDTO produtoDTO) {
        LOGGER.info("Recebendo Upadate para linha de categoria de ID: {}", id);
        LOGGER.debug("Payload {}", produtoDTO);

        return this.produtoService.update(produtoDTO, id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("Recebendo Delete para linha de categoria de ID: {}", id);
        this.produtoService.delete(id);
    }


}
