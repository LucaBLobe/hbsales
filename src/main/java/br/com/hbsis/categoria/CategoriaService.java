package br.com.hbsis.categoria;

import br.com.hbsis.fornecedor.Fornecedor;
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

    private final ICategoriaRepository iCategoriaRepository;
    private final FornecedorService fornecedorService;


    public CategoriaService(ICategoriaRepository iCategoriaRepository, FornecedorService fornecedorService) {
        this.iCategoriaRepository = iCategoriaRepository;
        this.fornecedorService = fornecedorService;
    }

    public CategoriaDTO save(CategoriaDTO categoriaDTO) {


        this.validate(categoriaDTO);

        LOGGER.info("Salvando categoria");
        LOGGER.debug("Categoria: {}", categoriaDTO.getFornecedorId());


        Categoria categoria = new Categoria();
        categoria.setFornecedorId(fornecedorService.findFornecedorById(categoriaDTO.getFornecedorId()));
//        String categoriaDigito = StringUtils.leftPad(categoriaDTO.getCodigoCategoria(), 3, "0");
//        String codigoFormatado;
//        codigoFormatado = "CAT" + categoria.getFornecedorId().getCnpj().substring(10) + categoriaDigito;
        categoria.setCodigoCategoria(this.codigoCategoriaCompleto(fornecedorService.findFornecedorById(categoriaDTO.getFornecedorId()),categoriaDTO));
        categoria.setNomeCategoria(categoriaDTO.getNomeCategoria());


        categoria = this.iCategoriaRepository.save(categoria);

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

    public String codigoCategoriaCompleto(Fornecedor cnpj, CategoriaDTO codigo){
        String codigoFormatado = "CAT" + cnpj.getCnpj().substring(10) + StringUtils.leftPad(codigo.getCodigoCategoria(), 3, "0");
        return codigoFormatado;
    }


    public CategoriaDTO findById(Long id) {
        Optional<Categoria> categoriaOptional = this.iCategoriaRepository.findById(id);
        if (categoriaOptional.isPresent()) {
            LOGGER.info("Recebendo find by ID... id: [{}]", CategoriaDTO.of(categoriaOptional.get()));
            return CategoriaDTO.of(categoriaOptional.get());
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }



    public Categoria findCategoriaById(Long id) {
        Optional<Categoria> categoriaOptional = this.iCategoriaRepository.findById(id);
        if (categoriaOptional.isPresent()) {
            return categoriaOptional.get();
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }
    public CategoriaDTO update(CategoriaDTO categoriaDTO, Long id) {
        Optional<Categoria> categoriaExistenteOptional = this.iCategoriaRepository.findById(id);


        if (categoriaExistenteOptional.isPresent()) {
            Categoria categoriaExistente = categoriaExistenteOptional.get();
            this.validate(categoriaDTO);


            LOGGER.info("Atualizado categoria... id: [{}]", categoriaExistente.getId());
            LOGGER.debug("Payload: {}", categoriaDTO);
            LOGGER.debug("Categoria Existente: {}", categoriaExistente);

            categoriaExistente.setFornecedorId(fornecedorService.findFornecedorById(categoriaDTO.getFornecedorId()));
//            String categoriaDigito = StringUtils.leftPad(categoriaDTO.getCodigoCategoria(), 3, "0");
//            String codigoFormatado;
//            codigoFormatado = "CAT" + categoriaExistente.getFornecedorId().getCnpj().substring(10) + categoriaDigito;
            categoriaExistente.setCodigoCategoria(codigoCategoriaCompleto(fornecedorService.findFornecedorById(categoriaDTO.getFornecedorId()),categoriaDTO));
            categoriaExistente.setNomeCategoria(categoriaDTO.getNomeCategoria());

            categoriaExistente = this.iCategoriaRepository.save(categoriaExistente);

            return categoriaDTO.of(categoriaExistente);
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public void delete(Long id) {
        LOGGER.info("Executando delete para categoria de ID: [{}]", id);
        this.iCategoriaRepository.deleteById(id);
    }

    public void importCsv(MultipartFile file) throws IOException, CsvException {

        Reader reader = new InputStreamReader(file.getInputStream());
        CSVReader read = new CSVReaderBuilder(reader).withSkipLines(1).build();
        List<String[]> lista = read.readAll();
        List<Categoria> saveLista = new ArrayList<>();


        for (String[] categoria : lista) {
            try {

                String[] colunaCategoria = categoria[0].replaceAll("\"", "").split(";");
                Optional<Categoria> categoriaExistente = iCategoriaRepository.findByCodigoCategoria(colunaCategoria[0]);

                if (categoriaExistente.isPresent()) {
                    continue;
                }

                Categoria categoriaImport = new Categoria();
                categoriaImport.setCodigoCategoria(colunaCategoria[0]);
                categoriaImport.setNomeCategoria(colunaCategoria[1]);

                Fornecedor fornecedor = fornecedorService.findByCnpj(colunaCategoria[3].replaceAll("\\D", ""));
                categoriaImport.setFornecedorId(fornecedor);

                saveLista.add(categoriaImport);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.iCategoriaRepository.saveAll(saveLista);
    }

    public void exportCsv(HttpServletResponse response) throws IOException, ParseException {


        String file = "categorias.csv";

        response.setContentType("text/csv");


        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", file);
        response.setHeader(headerKey, headerValue);


        List<Categoria> lista = iCategoriaRepository.findAll();

        ICSVWriter csvWriter = new CSVWriterBuilder(response.getWriter()).withSeparator(';').build();

        String[] header = {"Codigo de categoria", "Categoria", "Razão Social Fornecedor", "CNPJ Forncedor"};

        MaskFormatter mascaraCnpj = new MaskFormatter("##.###.###/####-##");
        mascaraCnpj.setValueContainsLiteralCharacters(false);


        csvWriter.writeNext(header);

        for (Categoria categoria : lista) {
            csvWriter.writeNext(new String[]{categoria.getCodigoCategoria(), categoria.getNomeCategoria(), categoria.getFornecedorId().getRazaoSocial(), mascaraCnpj.valueToString(categoria.getFornecedorId().getCnpj())});
        }

        csvWriter.close();
    }

    public Categoria findByCodigoCategoria(String codigoCategoria) {
        Optional<Categoria> categoriaOptional = this.iCategoriaRepository.findByCodigoCategoria(codigoCategoria);
        if (categoriaOptional.isPresent()) {
            return categoriaOptional.get();
        }
        throw new IllegalArgumentException(String.format("ID %s não esxiste", codigoCategoria));

    }
}


