package br.com.hbsis.linhaCategoria;

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


    @Autowired
    public LinhaCategoriaRest(LinhaCategoriaService linhaCategoriaService) {
        this.linhaCategoriaService = linhaCategoriaService;
    }

    @PostMapping
    public LinhaCategoriaDTO save(@RequestBody LinhaCategoriaDTO linhaCategoriaDTO) {
        LOGGER.info("Resebendo Solicitação de persistencia de categoria...");
        LOGGER.debug("Payload {}", linhaCategoriaDTO);

        return this.linhaCategoriaService.save(linhaCategoriaDTO);
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
    public LinhaCategoriaDTO update(@PathVariable("id") Long id, @RequestBody LinhaCategoriaDTO linhaCategoriaDTO) {
        LOGGER.info("Recebendo Upadate para linha de categoria de ID: {}", id);
        LOGGER.debug("Payload {}", linhaCategoriaDTO);

        return this.linhaCategoriaService.update(linhaCategoriaDTO, id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("Recebendo Delete para linha de categoria de ID: {}", id);
        this.linhaCategoriaService.delete(id);
    }
    @RequestMapping(value = "/export_csv")
    public void downloadCSV(HttpServletResponse response) throws IOException {
        LOGGER.info("Recebendo Delete para linha de categoria de ID: {}", response);
        this.linhaCategoriaService.exportCsv(response);

    }
    @PostMapping("/import_csv")
    public void importCsv(@RequestParam("file") MultipartFile file) throws IOException, CsvException {
        LOGGER.info("Recebendo Arquivo CSV para linha de ategoria de ID: {}", file);
        linhaCategoriaService.importCsv(file);
    }

}
