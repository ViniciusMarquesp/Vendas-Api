package viniciusmarquesp.com.github.vendas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import viniciusmarquesp.com.github.vendas.model.entity.Cliente;
import viniciusmarquesp.com.github.vendas.service.ClienteService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente pesquisarPorId(@PathVariable Long id){
        return clienteService.pesquisarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente incluir(@RequestBody @Valid Cliente cliente) {
        return clienteService.incluir(cliente);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> pesquisarTodos() {
        return clienteService.pesquisarTodos();
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente atualizar(@RequestBody @Valid Cliente cliente, @PathVariable Long id) {
        return clienteService.atualizar(cliente, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        clienteService.deletar(id);
    }
}
