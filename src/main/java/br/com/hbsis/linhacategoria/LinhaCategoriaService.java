package br.com.hbsis.linhacategoria;

import br.com.hbsis.categoria.Categoria;
import br.com.hbsis.categoria.CategoriaService;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LinhaCategoriaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(br.com.hbsis.linhacategoria.LinhaCategoriaService.class);

    private final ILinhaCategoriaRepository iLinhaCategoriaRepository;
    private final CategoriaService categoriaService;


    public LinhaCategoriaService(ILinhaCategoriaRepository iLinhaCategoriaRepository, CategoriaService categoriaService) {
        this.iLinhaCategoriaRepository = iLinhaCategoriaRepository;
        this.categoriaService = categoriaService;
    }

    public LinhaCategoriaDTO save(LinhaCategoriaDTO linhaCategoriaDTO) {


        this.validate(linhaCategoriaDTO);

        LOGGER.info("Salvando linha de categoria");
        LOGGER.debug("Linha categoria: {}", linhaCategoriaDTO.getCategoriaId());

        LinhaCategoria linhaCategoria = new LinhaCategoria();
        String codLinha = new String();
        codLinha = linhaCategoriaDTO.getCodLinhaCategoria().toUpperCase();

        String codLinhaFinal = StringUtils.leftPad(codLinha, 10, "0");

        linhaCategoria.setCodLinhaCategoria(codLinhaFinal);
        linhaCategoria.setCategoriaId(categoriaService.findCategoriaById(linhaCategoriaDTO.getCategoriaId()));
        linhaCategoria.setNomeLinhaCategoria(linhaCategoriaDTO.getNomeLinhaCategoria());


        linhaCategoria = this.iLinhaCategoriaRepository.save(linhaCategoria);

        return linhaCategoriaDTO.of(linhaCategoria);

    }

    private void validate(LinhaCategoriaDTO linhaCategoriaDTO) {
        LOGGER.info("Validando Categoria");

        if (linhaCategoriaDTO == null) {
            throw new IllegalArgumentException("LinhaCategoriaDTO não deve ser nulo");
        }
        if (StringUtils.isEmpty(linhaCategoriaDTO.getCodLinhaCategoria())) {
            throw new IllegalArgumentException("Codigo da Linha de Categoria não deve ser nula/vazia");
        }

        if (StringUtils.isEmpty(linhaCategoriaDTO.getNomeLinhaCategoria())) {
            throw new IllegalArgumentException("Nome da categoria não deve ser nulo");
        }
    }

    public LinhaCategoriaDTO findById(Long id) {
        Optional<LinhaCategoria> linhaCategoriaOptional = this.iLinhaCategoriaRepository.findById(id);
        if (linhaCategoriaOptional.isPresent()) {
            LOGGER.info("Recebendo find by ID... id: [{}]", LinhaCategoriaDTO.of(linhaCategoriaOptional.get()));
            return LinhaCategoriaDTO.of(linhaCategoriaOptional.get());
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public LinhaCategoria findLinhaCategoriaById(Long id) {
        Optional<LinhaCategoria> linhaCategoriaOptional = this.iLinhaCategoriaRepository.findById(id);
        if (linhaCategoriaOptional.isPresent()) {
            return linhaCategoriaOptional.get();
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public LinhaCategoriaDTO update(LinhaCategoriaDTO linhaCategoriaDTO, Long id) {
        Optional<LinhaCategoria> linhaCategoriaExistenteOptional = this.iLinhaCategoriaRepository.findById(id);


        if (linhaCategoriaExistenteOptional.isPresent()) {
            LinhaCategoria linhaCategoriaExistente = linhaCategoriaExistenteOptional.get();


            LOGGER.info("Atualizado Linha Categoria... id: [{}]", linhaCategoriaExistente.getId());
            LOGGER.debug("Payload: {}", linhaCategoriaDTO);
            LOGGER.debug("Categoria Existente: {}", linhaCategoriaExistente);

            linhaCategoriaExistente.setCodLinhaCategoria(linhaCategoriaDTO.getCodLinhaCategoria());
            linhaCategoriaExistente.setCategoriaId(categoriaService.findCategoriaById(linhaCategoriaDTO.getCategoriaId()));
            linhaCategoriaExistente.setNomeLinhaCategoria(linhaCategoriaDTO.getNomeLinhaCategoria());

            linhaCategoriaExistente = this.iLinhaCategoriaRepository.save(linhaCategoriaExistente);

            return linhaCategoriaDTO.of(linhaCategoriaExistente);
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public void delete(Long id) {
        LOGGER.info("Executando delete para categoria de ID: [{}]", id);
        this.iLinhaCategoriaRepository.deleteById(id);
    }

    public List<LinhaCategoria> findAll() {

        List<LinhaCategoria> linhaCategoriaOptional = this.iLinhaCategoriaRepository.findAll();
        return linhaCategoriaOptional;
    }

    public void exportCsv(HttpServletResponse response) throws IOException {


        String file = "linhas_categorias.csv";

        response.setContentType("text/csv");


        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", file);
        response.setHeader(headerKey, headerValue);


        List<LinhaCategoria> lista = iLinhaCategoriaRepository.findAll();

        ICSVWriter csvWriter = new CSVWriterBuilder(response.getWriter()).withSeparator(';').build();

        String[] header = {"Codigo Linha Categoria", "Nome Linha Categoria", "Codigo Categoria", "Nome da Categoria"};

        csvWriter.writeNext(header);

        for (LinhaCategoria linhaCategoria : lista) {
            csvWriter.writeNext(new String[]{linhaCategoria.getCodLinhaCategoria(), linhaCategoria.getNomeLinhaCategoria(), linhaCategoria.getCategoriaId().getCodigoCategoria(), linhaCategoria.getCategoriaId().getNomeCategoria()});
        }

        csvWriter.close();
    }

    public void importCsv(MultipartFile file) throws IOException, CsvException {

        Reader reader = new InputStreamReader(file.getInputStream());
        CSVReader read = new CSVReaderBuilder(reader).withSkipLines(1).build();
        List<String[]> lista = read.readAll();
        List<LinhaCategoria> saveLista = new ArrayList<>();


        for (String[] linhaCategoria : lista) {
            try {
                String[] linhaColunaCategoria = linhaCategoria[0].replaceAll("\"", "").split(";");
                Optional<LinhaCategoria> linhaCategoriaExistente = iLinhaCategoriaRepository.findByCodLinhaCategoria(linhaColunaCategoria[0]);

                if (linhaCategoriaExistente.isPresent()) {
                    continue;
                }

                LinhaCategoria linhaCategoriaImport = new LinhaCategoria();
                linhaCategoriaImport.setCodLinhaCategoria(linhaColunaCategoria[0]);
                linhaCategoriaImport.setNomeLinhaCategoria(linhaColunaCategoria[1]);
                Categoria categoria = new Categoria();
                categoria = categoriaService.findByCodigoCategoria(linhaColunaCategoria[2]);
                linhaCategoriaImport.setCategoriaId(categoria);

                saveLista.add(linhaCategoriaImport);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        this.iLinhaCategoriaRepository.saveAll(saveLista);
    }

    public LinhaCategoria findByCodLinhaCategoria(String codLinhaCategoria) {
        Optional<LinhaCategoria> linhaCategoriaOptional = this.iLinhaCategoriaRepository.findByCodLinhaCategoria(codLinhaCategoria);
        if (linhaCategoriaOptional.isPresent()) {
            return linhaCategoriaOptional.get();
        }
        System.out.println(String.format("Codigo de Linha de Categoria %s não esxiste", codLinhaCategoria));
        return null;
    }
}

