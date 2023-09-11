package viniciusmarquesp.com.github.vendas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import viniciusmarquesp.com.github.vendas.model.entity.Cliente;
import viniciusmarquesp.com.github.vendas.model.entity.Produto;
import viniciusmarquesp.com.github.vendas.service.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/{id}")
    public Produto pesquisarPorId(@PathVariable Long id){
        return produtoService.pesquisarPorId(id);
    }

    @PostMapping
    public Produto incluir(@RequestBody Produto produto) {
        return produtoService.incluir(produto);
    }

    @GetMapping
    public List<Produto> pesquisarTodos() {
        return produtoService.pesquisarTodos();
    }
    @PutMapping("/{id}")
    public Produto atualizar(@RequestBody Produto produto, @PathVariable Long id) {
        return produtoService.atualizar(produto, id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoService.deletar(id);
    }
}
