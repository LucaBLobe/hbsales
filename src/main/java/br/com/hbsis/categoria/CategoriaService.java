package br.com.hbsis.categoria;

import br.com.hbsis.fornecedor.Fornecedor;
import br.com.hbsis.fornecedor.FornecedorDTO;
import br.com.hbsis.fornecedor.FornecedorService;
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
import javax.swing.text.MaskFormatter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
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

        FornecedorDTO fornecedorDTO = new FornecedorDTO();
        Categoria categoria = new Categoria();
        categoria.setFornecedorId(fornecedorService.findFornecedorById(categoriaDTO.getFornecedorId()));
        String categoriaDigito;
        if (categoriaDTO.getCodigoCategoria().length() == 3){
            categoriaDigito = categoriaDTO.getCodigoCategoria();
        }
        if (categoriaDTO.getCodigoCategoria().length()== 2){
            categoriaDigito = "0"+categoriaDTO.getCodigoCategoria();
        }
        if (categoriaDTO.getCodigoCategoria().length()== 1){
            categoriaDigito = "00"+categoriaDTO.getCodigoCategoria();
        }else { throw new IllegalArgumentException("Nome da categoria não deve ser nulo");}

        categoria.setCodigoCategoria("CAT"+categoria.getFornecedorId().getCnpj().substring(10,14)+categoriaDigito);
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
    public Categoria findCategoriaById(Long id) {
        Optional<Categoria> categoriaOptional = this.iCategoriaRepositoy.findById(id);
        if (categoriaOptional.isPresent()) {
            return categoriaOptional.get();
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
        List<Categoria> saveLista = new ArrayList<>();


        for (String[] categoria : lista) {
            try {


            String[] colunaCategoria = categoria[0].replaceAll("\"", "").split(";");
            Categoria categoriaImport = new Categoria();

            categoriaImport.setCodigoCategoria(colunaCategoria[0]);
            categoriaImport.setNomeCategoria(colunaCategoria[1]);
            Fornecedor fornecedor = new Fornecedor();
            fornecedor = fornecedorService.findByCnpj(colunaCategoria[3].replaceAll("\\D", ""));
            categoriaImport.setFornecedorId(fornecedor);

            saveLista.add(categoriaImport);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
         this.iCategoriaRepositoy.saveAll(saveLista);
    }

    public void exportCsv(HttpServletResponse response) throws IOException, ParseException {


        String file = "categorias.csv";

        response.setContentType("text/csv");


        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", file);
        response.setHeader(headerKey, headerValue);


        List<Categoria> lista = iCategoriaRepositoy.findAll();

        ICSVWriter csvWriter = new CSVWriterBuilder(response.getWriter()).withSeparator(';').build();

        String[] header = {"Codigo de categoria", "Categoria", "Razão Social Fornecedor","CNPJ Forncedor"};

        MaskFormatter mascaraCnpj = new MaskFormatter("##.###.###/####-##");
        mascaraCnpj.setValueContainsLiteralCharacters(false);


        csvWriter.writeNext(header);

        for (Categoria categoria : lista) {
            csvWriter.writeNext(new String[]{categoria.getCodigoCategoria(),categoria.getNomeCategoria(),categoria.getFornecedorId().getRazaoSocial(),mascaraCnpj.valueToString(categoria.getFornecedorId().getCnpj())});
        }

        csvWriter.close();
    }
}


