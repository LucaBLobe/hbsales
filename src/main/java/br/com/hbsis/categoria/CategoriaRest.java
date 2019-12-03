package br.com.hbsis.categoria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/categorias")
public class CategoriaRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaRest.class);

    private CategoriaService categoriaService;

    @Autowired
    public CategoriaRest(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
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

    @RequestMapping("/all")
    public CategoriaDTO findAll() {

        LOGGER.info("Recebendp find by ID... id: [{}]");
        return (CategoriaDTO) this.categoriaService.findAll();
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

    @RequestMapping(value = "/export-categoria")
    public void downloadCSV(HttpServletResponse response) throws IOException {

        String csvFileName = "categorias.csv";

        response.setContentType("text/csv");


        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);


        List<Categoria> lista = categoriaService.findAll();


        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        String[] header = {"id", "codigoCategoria", "nomeCategoria", "fornecedorId"};

        csvWriter.writeHeader(header);

        for (Categoria categoria : lista) {
            csvWriter.write(categoria, header);
        }

        csvWriter.close();
    }

    @PostMapping("/import_csv")
    public void importCsv(@RequestParam("file") MultipartFile file) throws IOException {
        LOGGER.info("Recebendo Arquivo CSV para Categoria de ID: {}");
        this.categoriaService.importCsv(categoriaService.readAll);
    }


    /*@PostMapping("/import_csv")
    public static void readCsv() throws IOException {

        ICsvBeanReader beanReader = null;
        try {
            beanReader = new CsvBeanReader(new FileReader(csvFileName), CsvPreference.STANDARD_PREFERENCE);


            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();

            List<Categoria> lista = categoriaService.findAll();
            }


        } finally {
            if (beanReader != null) {
                beanReader.close();
            }
        }
    }

    private static CellProcessor[] getProcessors() {
        return new CellProcessor[]{
                new UniqueHashCode(),
                new NotNull(),
                new NotNull(),
                new NotNull(),
        };

    }
    /*@PostMapping("/import_csv")
    public static void ImportCSV() throws IOException {

        List<Categoria> lista = new ArrayList<Categoria>();
        ICsvBeanReader beanReader = new CsvBeanReader(new FileReader("categoria.csv"), CsvPreference.STANDARD_PREFERENCE);


        final String[] nameMapping = new String[]{"id", "codigoCategoria", "nomeCategoria", "fornecedorId"};

        final CellProcessor[] processors = getProcessors();
        Categoria categoria;
        while ((categoria = beanReader.read(Categoria.class, nameMapping, processors)) != null) {
            lista.add(categoria);
        }
        System.out.println(lista);
        beanReader.close();

    }

    private static CellProcessor[] getProcessors() {
        final CellProcessor[] processors = new CellProcessor[]{
                new UniqueHashCode(),
                new NotNull(),
                new NotNull(),
                new NotNull(),
        };
        return processors;


    }*/

}

