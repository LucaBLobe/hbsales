package br.com.hbsis.categoria;

import br.com.hbsis.fornecedor.Fornecedor;
import br.com.hbsis.fornecedor.FornecedorService;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaService.class);

    private final ICategoriaRepositoy iCategoriaRepositoy;
    private final FornecedorService fornecedorService;

    public CategoriaService(ICategoriaRepositoy iCategoriaRepositoy, FornecedorService fornecedorService) {
        this.iCategoriaRepositoy = iCategoriaRepositoy;
        this.fornecedorService = fornecedorService;
    }

    public CategoriaDTO save(CategoriaDTO categoriaDTO) {


        this.validate(categoriaDTO);

        LOGGER.info("Salvando categoria");
        LOGGER.debug("Categoria: {}", categoriaDTO.getFornecedorId());

        Categoria categoria = new Categoria();
        categoria.setCodigoCategoria(categoriaDTO.getCodigoCategoria());
        categoria.setFornecedorId(fornecedorService.findFornecedorById(categoriaDTO.getFornecedorId()));
        categoria.setNomeCategoria(categoriaDTO.getNomeCategoria());


        categoria = this.iCategoriaRepositoy.save(categoria);

        return categoriaDTO.of(categoria);

    }

    private void validate(CategoriaDTO categoriaDTO) {
        LOGGER.info("Validando Categoria");

        if (categoriaDTO == null) {
            throw new IllegalArgumentException("CategoriaDTO não deve ser nulo");
        }
        if (StringUtils.isEmpty(categoriaDTO.getCodigoCategoria())) {
            throw new IllegalArgumentException("Codigo de Categoria não deve ser nula/vazia");
        }

        if (StringUtils.isEmpty(categoriaDTO.getNomeCategoria())) {
            throw new IllegalArgumentException("Nome da categoria não deve ser nulo");
        }
    }

    public CategoriaDTO findById(Long id) {
        Optional<Categoria> categoriaOptional = this.iCategoriaRepositoy.findById(id);
        if (categoriaOptional.isPresent()) {
            LOGGER.info("Recebendo find by ID... id: [{}]", CategoriaDTO.of(categoriaOptional.get()));
            return CategoriaDTO.of(categoriaOptional.get());
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }


    public CategoriaDTO update(CategoriaDTO categoriaDTO, Long id) {
        Optional<Categoria> categoriaExistenteOptional = this.iCategoriaRepositoy.findById(id);


        if (categoriaExistenteOptional.isPresent()) {
            Categoria categoriaExistente = categoriaExistenteOptional.get();


            LOGGER.info("Atualizado categoria... id: [{}]", categoriaExistente.getId());
            LOGGER.debug("Payload: {}", categoriaDTO);
            LOGGER.debug("Categoria Existente: {}", categoriaExistente);

            categoriaExistente.setCodigoCategoria(categoriaDTO.getCodigoCategoria());
            categoriaExistente.setFornecedorId(fornecedorService.findFornecedorById(categoriaDTO.getFornecedorId()));
            categoriaExistente.setNomeCategoria(categoriaDTO.getNomeCategoria());

            categoriaExistente = this.iCategoriaRepositoy.save(categoriaExistente);

            return categoriaDTO.of(categoriaExistente);
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public void delete(Long id) {
        LOGGER.info("Executando delete para categoria de ID: [{}]", id);
        this.iCategoriaRepositoy.deleteById(id);
    }


    public List<Categoria> findAll() {


        List<Categoria> categoriaOptional = this.iCategoriaRepositoy.findAll();
        return categoriaOptional;
    }


    public void importCsv(MultipartFile file) throws IOException, CsvException {

        Reader reader = new InputStreamReader(file.getInputStream());
        CSVReader read = new CSVReaderBuilder(reader).withSkipLines(1).build();
        List<String[]> lista = read.readAll();
        Categoria categoriaImport = new Categoria();


        for (String[] categoria : lista) {
            String[] colunacategoria = categoria[0].replaceAll("\"", "").split(";");


            categoriaImport.setCodigoCategoria(colunacategoria[1]);
            categoriaImport.setNomeCategoria(colunacategoria[2]);
            Fornecedor fornecedor = new Fornecedor();
            fornecedor = fornecedorService.findFornecedorById(Long.parseLong(colunacategoria[3]));
            categoriaImport.setFornecedorId(fornecedor);


            this.iCategoriaRepositoy.save(categoriaImport);

        }


    }
}


