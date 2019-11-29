package br.com.hbsis.categoria;

import br.com.hbsis.fornecedor.FornecedorService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaService.class);

    private final ICategoriaRepositoy iCategoriaRepositoy;
    private  final FornecedorService fornecedorService;

    public CategoriaService(ICategoriaRepositoy iCategoriaRepositoy, FornecedorService fornecedorService) {
        this.iCategoriaRepositoy = iCategoriaRepositoy;
        this.fornecedorService = fornecedorService;
    }

    public CategoriaDTO save(CategoriaDTO categoriaDTO) {

        categoriaDTO.setFornecedorCategoria(fornecedorService.findFornecedorById(categoriaDTO.getFornecedorCategoria().getId()));
        this.validate(categoriaDTO);

        LOGGER.info("Salvando categoria");
        LOGGER.debug("Usuario: {}", categoriaDTO.getFornecedorCategoria().getNomeFantasia());

        Categoria categoria = new Categoria();
        categoria.setCodigoCategoria(categoriaDTO.getCodigoCategoria());
        categoria.setFornecedorCategoria(categoriaDTO.getFornecedorCategoria());
        categoria.setNomeCategoria(categoriaDTO.getNomeCategoria());




        categoria = this.iCategoriaRepositoy.save(categoria);

        return categoriaDTO.of(categoria);

    }

    private void validate(CategoriaDTO categoriaDTO){
        LOGGER.info("Validando Categoria");

        if (categoriaDTO == null){
            throw new IllegalArgumentException("CategoriaDTO não deve ser nulo");
        }
        if (StringUtils.isEmpty(categoriaDTO.getCodigoCategoria())){
            throw new IllegalArgumentException("Codigo de Categoria não deve ser nula/vazia");
        }

        if (StringUtils.isEmpty(categoriaDTO.getNomeCategoria())){
            throw new IllegalArgumentException("Nome da categoria não deve ser nulo");
        }
    }

    public CategoriaDTO findById(Long id) {
        Optional<Categoria>categoriaOptional = this.iCategoriaRepositoy.findById(id);
        if(categoriaOptional.isPresent()){
            LOGGER.info("Recebendo find by ID... id: [{}]",CategoriaDTO.of(categoriaOptional.get()));
            return CategoriaDTO.of(categoriaOptional.get());
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }


    public CategoriaDTO update(CategoriaDTO categoriaDTO,Long id) {
        Optional<Categoria> categoriaExistenteOptional = this.iCategoriaRepositoy.findById(id);
        categoriaDTO.setFornecedorCategoria(fornecedorService.findFornecedorById(categoriaDTO.getFornecedorCategoria().getId()));

        if (categoriaExistenteOptional.isPresent()){
            Categoria categoriaExistente = categoriaExistenteOptional.get();

            LOGGER.info("Atualizado categoria... id: [{}]", categoriaExistente.getId());
            LOGGER.debug("Payload: {}", categoriaDTO);
            LOGGER.debug("Categoria Existente: {}", categoriaExistente);

            categoriaExistente.setCodigoCategoria(categoriaDTO.getCodigoCategoria());
            categoriaExistente.setFornecedorCategoria(categoriaDTO.getFornecedorCategoria());
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
}
