package br.com.hbsis.linhaCategoria;

import br.com.hbsis.categoria.CategoriaDTO;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/linha_categorias")

public class LinhaCategoriaRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(br.com.hbsis.linhaCategoria.LinhaCategoriaRest.class);

    private LinhaCategoriaService linhaCategoriaService;
    private LinhaCategoriaService LinhaCategoriaService;

    @Autowired
    public LinhaCategoriaRest(LinhaCategoriaService linhaCategoriaService) {
        this.linhaCategoriaService = linhaCategoriaService;
    }

    @PostMapping
    public LinhaCategoriaDTO save(@RequestBody LinhaCategoriaDTO linhaCategoriaDTO) {
        LOGGER.info("Resebendo Solicitação de persistencia de categoria...");
        LOGGER.debug("Payload {}", linhaCategoriaDTO);

        return this.LinhaCategoriaService.save(linhaCategoriaDTO);
    }
    @GetMapping("/{id}")
    public LinhaCategoriaDTO find(@PathVariable("id") Long id) {

        LOGGER.info("Recebendo find by ID... id: [{}]", id);
        return this.linhaCategoriaService.findById(id);
    }
    @RequestMapping("/all")
    public LinhaCategoriaDTO findAll() {

        LOGGER.info("Recebendp find by ID... id: [{}]");
        return (LinhaCategoriaDTO) this.linhaCategoriaService.findAll();
    }
    @PutMapping("/{id}")
    public CategoriaDTO update(@PathVariable("id") Long id, @RequestBody CategoriaDTO categoriaDTO) {
        LOGGER.info("Recebendo Upadate para categoria de ID: {}", id);
        LOGGER.debug("Payload {}", categoriaDTO);

        return this.linhaCategoriaService.update(categoriaDTO, id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("Recebendo Delete para Categoria de ID: {}", id);
        this.linhaCategoriaService.delete(id);
    }
    @RequestMapping(value = "/export-categoria")
    public void downloadCSV(HttpServletResponse response) throws IOException {
        LOGGER.info("Recebendo Delete para Categoria de ID: {}", response);
        this.linhaCategoriaService.exportCsv(response);

    }
    @PostMapping("/import_csv")
    public void importCsv(@RequestParam("file") MultipartFile file) throws IOException, CsvException {
        LOGGER.info("Recebendo Arquivo CSV para Categoria de ID: {}", file);
        linhaCategoriaService.importCsv(file);
    }

}
