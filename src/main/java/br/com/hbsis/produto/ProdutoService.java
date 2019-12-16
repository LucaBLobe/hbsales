package br.com.hbsis.produto;

import br.com.hbsis.categoria.Categoria;
import br.com.hbsis.categoria.CategoriaService;
import br.com.hbsis.categoria.ICategoriaRepositoy;
import br.com.hbsis.fornecedor.Fornecedor;
import br.com.hbsis.fornecedor.FornecedorService;
import br.com.hbsis.linhaCategoria.ILinhaCategoriaRepository;
import br.com.hbsis.linhaCategoria.LinhaCategoria;
import br.com.hbsis.linhaCategoria.LinhaCategoriaService;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(br.com.hbsis.produto.ProdutoService.class);

    private final IProdutoRepository iProdutoRepository;
    private final LinhaCategoriaService LinhaCategoriaService;
    private final FornecedorService fornecedorService;
    private final ICategoriaRepositoy iCategoriaRepositoy;
    private final CategoriaService categoriaService;
    private final ILinhaCategoriaRepository iLinhaCategoriaRepository;


    public ProdutoService(IProdutoRepository iProdutoRepository, br.com.hbsis.linhaCategoria.LinhaCategoriaService linhaCategoriaService, FornecedorService fornecedorService, CategoriaService categoriaService, ICategoriaRepositoy iCategoriaRepositoy, CategoriaService categoriaService1, ILinhaCategoriaRepository iLinhaCategoriaRepository) {
        this.iProdutoRepository = iProdutoRepository;
        this.LinhaCategoriaService = linhaCategoriaService;
        this.fornecedorService = fornecedorService;
        this.iCategoriaRepositoy = iCategoriaRepositoy;
        this.categoriaService = categoriaService1;
        this.iLinhaCategoriaRepository = iLinhaCategoriaRepository;
    }

    public ProdutoDTO save(ProdutoDTO produtoDTO) {


        this.validate(produtoDTO);

        LOGGER.info("Salvando produto");
        LOGGER.debug("Linha categoria: {}", produtoDTO.getLinhaCategoriaId());

        String codProduto = new String();
        codProduto = produtoDTO.getCodProduto().toUpperCase();

        String codProdutoFinal = StringUtils.leftPad(codProduto, 10, "0");

        Produto produto = new Produto();
        produto.setCodProduto(codProdutoFinal);
        produto.setNomeProduto(produtoDTO.getNomeProduto());
        produto.setPrecoProduto(produtoDTO.getPrecoProduto());
        produto.setLinhaCategoriaId(LinhaCategoriaService.findLinhaCategoriaById(produtoDTO.getLinhaCategoriaId()));
        produto.setUnidadesCaixa(produtoDTO.getUnidadesCaixa());
        produto.setPesoUnitario(produtoDTO.getPesoUnitario());
        produto.setUnidadeMedida(produtoDTO.getUnidadeMedida());
        produto.setValidadeProduto(produtoDTO.getValidadeProduto());

        produto = this.iProdutoRepository.save(produto);

        return produtoDTO.of(produto);

    }

    private void validate(ProdutoDTO produtoDTO) {
        LOGGER.info("Validando Categoria");

        if (produtoDTO == null) {
            throw new IllegalArgumentException("produtoDTO não deve ser nulo");
        }
        if (StringUtils.isEmpty(produtoDTO.getCodProduto())) {
            throw new IllegalArgumentException("Codigo da Linha de Categoria não deve ser nula/vazia");
        }
        if (StringUtils.isEmpty(String.valueOf(produtoDTO.getPrecoProduto()))) {
            throw new IllegalArgumentException("Preço do produto não deve ser nulo");
        }
        if (StringUtils.isEmpty(String.valueOf(produtoDTO.getLinhaCategoriaId()))) {
            throw new IllegalArgumentException("Nome da categoria não deve ser nulo");
        }
        if (StringUtils.isEmpty(String.valueOf(produtoDTO.getUnidadesCaixa()))) {
            throw new IllegalArgumentException("A quantidade de caixas não deve ser nulo");
        }
        if (StringUtils.isEmpty(String.valueOf(produtoDTO.getPesoUnitario()))) {
            throw new IllegalArgumentException("O peso unitario não deve ser nulo");
        }
        if (StringUtils.isEmpty((produtoDTO.getUnidadeMedida()))) {
            throw new IllegalArgumentException("A unidade de medida não deve ser nulo");
        }
        if ((!(produtoDTO.getUnidadeMedida()).equalsIgnoreCase("kg")) && (!(produtoDTO.getUnidadeMedida()).equalsIgnoreCase("mg"))
                && (!(produtoDTO.getUnidadeMedida()).equalsIgnoreCase("g"))) {
            throw new IllegalArgumentException("Unidade de medida invalida, deve ser mg, g ou kg");
        }
        if (StringUtils.isEmpty((String.valueOf(produtoDTO.getValidadeProduto())))) {
            throw new IllegalArgumentException("A validade não deve ser nula não deve ser nulo");
        }
    }

    public ProdutoDTO findById(Long id) {
        Optional<Produto> produtoOptional = this.iProdutoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            LOGGER.info("Recebendo find by ID... id: [{}]", ProdutoDTO.of(produtoOptional.get()));
            return ProdutoDTO.of(produtoOptional.get());
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public Produto findByCodProduto(String codProduto) {
        Optional<Produto> produtoOptional = this.iProdutoRepository.findByCodProduto(codProduto);
        if (produtoOptional.isPresent()) {
            LOGGER.info("Recebendo find by Codigo do produto... id: [{}]", produtoOptional.get());
            return produtoOptional.get();
        }
        System.out.println(String.format("ID %s não existe", codProduto));
        return null;
    }


    public IProdutoRepository findAll() {
        List<Produto> produtoOptional = this.iProdutoRepository.findAll();
        return iProdutoRepository;
    }

    public ProdutoDTO update(ProdutoDTO produtoDTO, Long id) {
        Optional<Produto> produtoExistenteOptional = this.iProdutoRepository.findById(id);

        this.validate(produtoDTO);

        if (produtoExistenteOptional.isPresent()) {
            Produto produtoExistente = produtoExistenteOptional.get();


            LOGGER.info("Atualizado Linha Categoria... id: [{}]", produtoExistente.getId());
            LOGGER.debug("Payload: {}", produtoDTO);
            LOGGER.debug("Categoria Existente: {}", produtoExistente);

            String codProdutoAt = new String();
            codProdutoAt = produtoDTO.getCodProduto().toUpperCase();

            String codProdutoFinalAt = StringUtils.leftPad(codProdutoAt, 10, "0");

            produtoExistente.setCodProduto(codProdutoFinalAt);
            produtoExistente.setNomeProduto(produtoDTO.getNomeProduto());
            produtoExistente.setPrecoProduto(produtoDTO.getPrecoProduto());
            produtoExistente.setLinhaCategoriaId(LinhaCategoriaService.findLinhaCategoriaById(produtoDTO.getLinhaCategoriaId()));
            produtoExistente.setUnidadesCaixa(produtoDTO.getUnidadesCaixa());
            produtoExistente.setPesoUnitario(produtoDTO.getPesoUnitario());
            produtoExistente.setUnidadeMedida(produtoDTO.getUnidadeMedida());
            produtoExistente.setValidadeProduto(produtoDTO.getValidadeProduto());

            produtoExistente = this.iProdutoRepository.save(produtoExistente);

            return produtoDTO.of(produtoExistente);
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public void delete(Long id) {
        LOGGER.info("Executando delete para categoria de ID: [{}]", id);
        this.iProdutoRepository.deleteById(id);
    }

    public void exportCsv(HttpServletResponse response) throws IOException, ParseException {


        String file = "produto.csv";

        response.setContentType("text/csv");


        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", file);
        response.setHeader(headerKey, headerValue);


        List<Produto> lista = iProdutoRepository.findAll();

        ICSVWriter csvWriter = new CSVWriterBuilder(response.getWriter()).withSeparator(';').build();

        String[] header = {"Codigo Produto", "Nome Produto", "Preço R$", "Quantidade de Caixas", "Peso", "Validade",
                "Código linha Categoria", "Nome Linha Categoria", "Código da Categoria", "Nome da Categoria",
                "CNPJ do fornecedor", "Razão Social do Fornecedor"};

        MaskFormatter mascaraCnpj = new MaskFormatter("##.###.###/####-##");
        mascaraCnpj.setValueContainsLiteralCharacters(false);


        csvWriter.writeNext(header);

        for (Produto produto : lista) {
            csvWriter.writeNext(new String[]{produto.getCodProduto(), produto.getNomeProduto(), "R$ " + produto.getPrecoProduto().toString(),
                    String.valueOf(produto.getUnidadesCaixa()), produto.getPesoUnitario() + produto.getUnidadeMedida(), produto.getValidadeProduto().toString()
                    , produto.getLinhaCategoriaId().getCodLinhaCategoria(), produto.getLinhaCategoriaId().getNomeLinhaCategoria(), produto.getLinhaCategoriaId().getCategoriaId().getCodigoCategoria(),
                    produto.getLinhaCategoriaId().getCategoriaId().getNomeCategoria(), mascaraCnpj.valueToString(produto.getLinhaCategoriaId().getCategoriaId().getFornecedorId().getCnpj()),
                    produto.getLinhaCategoriaId().getCategoriaId().getFornecedorId().getRazaoSocial()});
        }

        csvWriter.close();
    }

    public void importCsv(MultipartFile file) throws IOException, CsvException {


        Reader reader = new InputStreamReader(file.getInputStream());
        CSVReader read = new CSVReaderBuilder(reader).withSkipLines(1).build();
        List<String[]> lista = read.readAll();
        List<Produto> saveLista = new ArrayList<>();


        for (String[] produto : lista) {
            try {
                String[] produtoColuna = produto[0].replaceAll("\"", "").split(";");
                Optional<Produto> produtoExistente = iProdutoRepository.findByCodProduto(produtoColuna[0]);


                if (produtoExistente.isPresent()) {
                    continue;
                }
                Produto produtoImport = new Produto();
                produtoImport.setCodProduto(produtoColuna[0]);
                produtoImport.setNomeProduto(produtoColuna[1]);
                produtoImport.setPrecoProduto(Double.parseDouble(produtoColuna[2].substring(3)));
                produtoImport.setUnidadesCaixa(Integer.parseInt(produtoColuna[3]));
                produtoImport.setUnidadeMedida(produtoColuna[4].replaceAll("\\d", "").replace(".", ""));
                produtoImport.setPesoUnitario(Double.parseDouble(produtoColuna[4].replaceAll("k", "").replaceAll("g", "").replaceAll("m", "")));
                produtoImport.setValidadeProduto(LocalDate.parse(produtoColuna[5].replaceAll("/", "-"), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                LinhaCategoria linhaCategoria = new LinhaCategoria();
                linhaCategoria = LinhaCategoriaService.findByCodLinhaCategoria(produtoColuna[6]);
                if (linhaCategoria == null) {
                    linhaCategoria.setCodLinhaCategoria(produtoColuna[0]);
                    continue;
                }
                produtoImport.setLinhaCategoriaId(linhaCategoria);
                saveLista.add(produtoImport);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }
        this.iProdutoRepository.saveAll(saveLista);
    }

    public void importFornecedor(MultipartFile file, Long id) throws IOException, CsvException {

        Fornecedor fornecedorExistente = fornecedorService.findFornecedorById(id);
        if (fornecedorExistente.getCnpj() == null) {
            LOGGER.info("Fornecedor não existe: [{}]", fornecedorExistente.getId());
        } else {

            Reader reader = new InputStreamReader(file.getInputStream());
            CSVReader read = new CSVReaderBuilder(reader).withSkipLines(1).build();
            List<String[]> lista = read.readAll();
            List<Produto> saveLista = new ArrayList<>();

            for (String[] produto : lista) {
                try {

                    String[] produtoColuna = produto[0].replaceAll("\"", "").split(";");

                    //    Optional<Produto> produtoExistente = iProdutoRepository.findByCodProduto(produtoColuna[0]);

                    //   if (produtoExistente.isPresent()) {
                    //       continue;
                    //  }
                    //   if (!(produtoExistente.get().getLinhaCategoriaId().getCategoriaId().getFornecedorId().equals(id))){
                    //        continue;
                    //  }

                    Optional<Categoria> categoriaExistente = iCategoriaRepositoy.findByCodigoCategoria(produtoColuna[8]);


                    if (!categoriaExistente.isPresent()) {

                        Categoria categoriaImport = new Categoria();
                        categoriaImport.setCodigoCategoria(produtoColuna[8]);
                        categoriaImport.setNomeCategoria(produtoColuna[9]);
                        categoriaImport.setFornecedorId(fornecedorExistente);

                        iCategoriaRepositoy.save(categoriaImport);


                    } else if (categoriaExistente.isPresent()) {

                        Categoria categoriaImport = categoriaExistente.get();
                        categoriaImport.setFornecedorId(fornecedorExistente);
                        iCategoriaRepositoy.save(categoriaImport);
                    }


                    Optional<LinhaCategoria> linhaCategoriaExistente = iLinhaCategoriaRepository.findByCodLinhaCategoria(produtoColuna[6]);

                    if (!linhaCategoriaExistente.isPresent()) {
                        LinhaCategoria linhaCategoriaImport = new LinhaCategoria();
                        linhaCategoriaImport.setCodLinhaCategoria(produtoColuna[6]);
                        linhaCategoriaImport.setNomeLinhaCategoria(produtoColuna[7]);
                        linhaCategoriaImport.setCategoriaId(categoriaService.findCategoriaById(categoriaService.findByCodigoCategoria(produtoColuna[8]).getId()));
                        iLinhaCategoriaRepository.save(linhaCategoriaImport);
                    } else if (linhaCategoriaExistente.isPresent()) {
                        LinhaCategoria linhaCategoriaImport = linhaCategoriaExistente.get();
                        linhaCategoriaImport.setCategoriaId(categoriaService.findCategoriaById(categoriaService.findByCodigoCategoria(produtoColuna[8]).getId()));
                        iLinhaCategoriaRepository.save(linhaCategoriaImport);
                    }
                    Optional<Produto> produtoExistente = iProdutoRepository.findByCodProduto(produtoColuna[0]);

                    if (produtoExistente.isPresent()) {

                    Produto produtoImport = new Produto();
                    produtoImport.setCodProduto(produtoColuna[0]);
                    produtoImport.setNomeProduto(produtoColuna[1]);
                    produtoImport.setPrecoProduto(Double.parseDouble(produtoColuna[2].substring(3)));
                    produtoImport.setUnidadesCaixa(Integer.parseInt(produtoColuna[3]));
                    produtoImport.setUnidadeMedida(produtoColuna[4].replaceAll("\\d", "").replace(".", ""));
                    produtoImport.setPesoUnitario(Double.parseDouble(produtoColuna[4].replaceAll("k", "").replaceAll("g", "").replaceAll("m", "")));
                    produtoImport.setValidadeProduto(LocalDate.parse(produtoColuna[5].replaceAll("/", "-"), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    LinhaCategoria linhaCategoria = new LinhaCategoria();
                    linhaCategoria = LinhaCategoriaService.findByCodLinhaCategoria(produtoColuna[6]);

                    produtoImport.setLinhaCategoriaId(linhaCategoria);
                    saveLista.add(produtoImport);

                    }

                    if (produtoExistente.isPresent()) {
                        Produto produtoImport = produtoExistente.get();
                        LinhaCategoria linhaCategoria = new LinhaCategoria();
                        linhaCategoria = LinhaCategoriaService.findByCodLinhaCategoria(produtoColuna[6]);
                        produtoImport.setLinhaCategoriaId(linhaCategoria);
                        saveLista.add(produtoImport);

                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            this.iProdutoRepository.saveAll(saveLista);
        }
    }
}
