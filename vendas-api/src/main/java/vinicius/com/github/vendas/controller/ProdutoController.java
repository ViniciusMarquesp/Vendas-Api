package vinicius.com.github.vendas.controller;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import vinicius.com.github.vendas.exception.ApiErros;
import vinicius.com.github.vendas.model.entity.Produto;
import vinicius.com.github.vendas.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Obter detalhes de um produto.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Produto encontrado."),
            @ApiResponse(code = 404, message = "Produto não encontrado para o Id informado.", response = ApiErros.class)
    })
    public Produto pesquisarPorId(@PathVariable Long id) {
        return produtoService.pesquisarPorId(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Lista todos os peodutos cadastrados.")
    @ApiResponses({@ApiResponse(code = 200, message = "Lista de produtos cadastrados.")})
    public List<Produto> pesquisarTodos() {
        return produtoService.pesquisarTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Salva um novo produto.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Produto salvo com sucesso."),
            @ApiResponse(code = 400, message = "Erro(s) de validação", response = ApiErros.class)
    })
    public Produto incluir(@Valid @RequestBody Produto produto) {
        return produtoService.incluir(produto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Atualiza um produto existente.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Produto atualizado com sucesso."),
            @ApiResponse(code = 400, message = "Erro(s) de validação.", response = ApiErros.class),
            @ApiResponse(code = 404, message = "Produto não encontrado.", response = ApiErros.class)
    })
    public Produto atualizar(@Valid @RequestBody Produto produto, @PathVariable Long id) {
        return produtoService.atualizar(produto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Exclui um produto existente.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Produto excluído com sucesso."),
            @ApiResponse(code = 404, message = "Produto não encontrado.", response = ApiErros.class)
    })
    public void excluir(@PathVariable Long id) {
        produtoService.excluir(id);
    }

}
