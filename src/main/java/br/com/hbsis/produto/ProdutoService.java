package br.com.hbsis.produto;

import br.com.hbsis.linhaCategoria.LinhaCategoria;
import br.com.hbsis.linhaCategoria.LinhaCategoriaService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(br.com.hbsis.produto.ProdutoService.class);

    private final IProdutoRepository iProdutoRepository;
    private final LinhaCategoriaService LinhaCategoriaService;
    private ProdutoService iLinhaCategoriaRepository;


    public ProdutoService(IProdutoRepository iProdutoRepository, br.com.hbsis.linhaCategoria.LinhaCategoriaService linhaCategoriaService) {
        this.iProdutoRepository = iProdutoRepository;
        this.LinhaCategoriaService = linhaCategoriaService;
    }

    public ProdutoDTO save(ProdutoDTO produtoDTO) {


        this.validate(produtoDTO);

        LOGGER.info("Salvando produto");
        LOGGER.debug("Linha categoria: {}", produtoDTO.getLinhaCategoriaId());

        Produto produto = new Produto();
        produto.setCodProduto(produtoDTO.getCodProduto());
        produto.setNomeProduto(produtoDTO.getNomeProduto());
        produto.setPrecoProduto(produtoDTO.getPrecoProduto());
        produto.setLinhaCategoriaId(LinhaCategoriaService.findLinhaCategoriaById(produtoDTO.getLinhaCategoriaId()));
        produto.setUnidadesCaixa(produtoDTO.getUnidadesCaixa());
        produto.setPesoUnitario(produtoDTO.getPesoUnitario());
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
        if (StringUtils.isEmpty(produtoDTO.getNomeProduto())) {
            throw new IllegalArgumentException("Nome da categoria não deve ser nulo");
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

    public IProdutoRepository findAll() {
        List<Produto> produtoOptional = this.iProdutoRepository.findAll();
        return iProdutoRepository;
    }

    public ProdutoDTO update(ProdutoDTO produtoDTO, Long id) {
        Optional<Produto> produtoExistenteOptional = this.iProdutoRepository.findById(id);

        if (produtoExistenteOptional.isPresent()) {
            Produto produtoExistente = produtoExistenteOptional.get();


            LOGGER.info("Atualizado Linha Categoria... id: [{}]", produtoExistente.getId());
            LOGGER.debug("Payload: {}", produtoDTO);
            LOGGER.debug("Categoria Existente: {}", produtoExistente);

            produtoExistente.setCodProduto(produtoDTO.getCodProduto());
            produtoExistente.setNomeProduto(produtoDTO.getNomeProduto());
            produtoExistente.setPrecoProduto(produtoDTO.getPrecoProduto());
            produtoExistente.setLinhaCategoriaId(LinhaCategoriaService.findLinhaCategoriaById(produtoDTO.getLinhaCategoriaId()));
            produtoExistente.setUnidadesCaixa(produtoDTO.getUnidadesCaixa());
            produtoExistente.setPesoUnitario(produtoDTO.getPesoUnitario());
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


}