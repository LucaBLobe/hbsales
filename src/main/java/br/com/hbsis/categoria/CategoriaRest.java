package br.com.hbsis.categoria;

import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/categorias")
public class CategoriaRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaRest.class);

    private final CategoriaService categoriaService;
    private final ICategoriaRepositoy iCategoriaRepositoy;


    @Autowired
    public CategoriaRest(CategoriaService categoriaService, ICategoriaRepositoy iCategoriaRepositoy) {
        this.categoriaService = categoriaService;
        this.iCategoriaRepositoy = iCategoriaRepositoy;
    }

    @PostMapping
    public CategoriaDTO save(@RequestBody CategoriaDTO categoriaDTO) {
        LOGGER.info("Resebendo Solicitação de persistencia de categoria...");
        LOGGER.debug("Payload {}", categoriaDTO);

        return this.categoriaService.save(categoriaDTO);
    }

    @GetMapping("/{id}")
    public CategoriaDTO find(@PathVariable("id") Long id) {

        LOGGER.info("Recebendo find by ID... id: [{}]", id);
        return this.categoriaService.findById(id);
    }
    @GetMapping
    public List<Categoria> findAll() {

        LOGGER.info("Recebendp find by ID... id: [{}]");
        return iCategoriaRepositoy.findAll();
    }
    @PutMapping("/{id}")
    public CategoriaDTO update(@PathVariable("id") Long id, @RequestBody CategoriaDTO categoriaDTO) {
        LOGGER.info("Recebendo Upadate para categoria de ID: {}", id);
        LOGGER.debug("Payload {}", categoriaDTO);

        return this.categoriaService.update(categoriaDTO, id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("Recebendo Delete para Categoria de ID: {}", id);
        this.categoriaService.delete(id);
    }
    @GetMapping(value = "/export_csv")
    public void downloadCSV(HttpServletResponse response) throws IOException, ParseException {
        LOGGER.info("Recebendo Delete para Categoria de ID: {}", response);
        this.categoriaService.exportCsv(response);
    }
    @PostMapping("/import_csv")
    public void importCsv(@RequestParam("file") MultipartFile file) throws IOException, CsvException {
        LOGGER.info("Recebendo Arquivo CSV para Categoria de ID: {}", file);
        categoriaService.importCsv(file);
    }
}

