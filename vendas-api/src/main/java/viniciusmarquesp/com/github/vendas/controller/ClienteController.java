package viniciusmarquesp.com.github.vendas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import viniciusmarquesp.com.github.vendas.model.entity.Cliente;
import viniciusmarquesp.com.github.vendas.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public Cliente pesquisarPorId(@PathVariable Long id){
        return clienteService.pesquisarPorId(id);
    }

    @PostMapping
    public Cliente incluir(@RequestBody Cliente cliente) {
        return clienteService.incluir(cliente);
    }

    @GetMapping
    public List<Cliente> pesquisarTodos() {
        return clienteService.pesquisarTodos();
    }
    @PutMapping("/{id}")
    public Cliente atualizar(@RequestBody Cliente cliente, @PathVariable Long id) {
        return clienteService.atualizar(cliente, id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        clienteService.deletar(id);
    }
}
