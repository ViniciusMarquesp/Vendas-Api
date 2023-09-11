package viniciusmarquesp.com.github.vendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import viniciusmarquesp.com.github.vendas.model.entity.Produto;
import viniciusmarquesp.com.github.vendas.model.repository.ProdutoRepository;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ProdutoService {

    private static final String PRODUTO_NAO_ENCONTRADO = "Produto não encontrado";

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto pesquisarPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, PRODUTO_NAO_ENCONTRADO));
    }

    public List<Produto> pesquisarTodos() {
        return produtoRepository.findAll();
    }

    public Produto incluir(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Produto produto, Long id) {
        return produtoRepository.findById(id).map(m -> {
            produto.setId(m.getId());
            return produtoRepository.save(produto);
        }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, PRODUTO_NAO_ENCONTRADO));
    }

    public void deletar(Long id) {
        produtoRepository.findById(id).map(m -> {
            produtoRepository.delete(m);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, PRODUTO_NAO_ENCONTRADO));
    }
}
