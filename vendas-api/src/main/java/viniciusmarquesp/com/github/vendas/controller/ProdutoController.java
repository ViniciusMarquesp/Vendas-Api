package viniciusmarquesp.com.github.vendas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import viniciusmarquesp.com.github.vendas.model.entity.Cliente;
import viniciusmarquesp.com.github.vendas.model.entity.Produto;
import viniciusmarquesp.com.github.vendas.service.ProdutoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Produto pesquisarPorId(@PathVariable Long id){
        return produtoService.pesquisarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto incluir(@RequestBody @Valid Produto produto) {
        return produtoService.incluir(produto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Produto> pesquisarTodos() {
        return produtoService.pesquisarTodos();
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Produto atualizar(@RequestBody @Valid Produto produto, @PathVariable Long id) {
        return produtoService.atualizar(produto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        produtoService.deletar(id);
    }
}
