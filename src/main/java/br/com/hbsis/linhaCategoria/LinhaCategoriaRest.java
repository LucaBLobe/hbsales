package br.com.hbsis.linhaCategoria;

import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/linha_categorias")

public class LinhaCategoriaRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(br.com.hbsis.linhaCategoria.LinhaCategoriaRest.class);

    private final LinhaCategoriaService linhaCategoriaService;
    private final ILinhaCategoriaRepository iLinhaCategoriaRepository;


    @Autowired
    public LinhaCategoriaRest(LinhaCategoriaService linhaCategoriaService, ILinhaCategoriaRepository iLinhaCategoriaRepository) {
        this.linhaCategoriaService = linhaCategoriaService;
        this.iLinhaCategoriaRepository = iLinhaCategoriaRepository;
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

    @GetMapping
    public List<LinhaCategoria> findAll() {
        return iLinhaCategoriaRepository.findAll();
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
        LOGGER.info("Exportando CSV da linha de categoria de ID: {}", response);
        this.linhaCategoriaService.exportCsv(response);

    }
    @PostMapping("/import_csv")
    public void importCsv(@RequestParam("file") MultipartFile file) throws IOException, CsvException {
        LOGGER.info("Recebendo Arquivo CSV para linha de ategoria de ID: {}", file);
        linhaCategoriaService.importCsv(file);
    }

}
