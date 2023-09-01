package viniciusmarquesp.com.github.vendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import viniciusmarquesp.com.github.vendas.model.entity.Cliente;
import viniciusmarquesp.com.github.vendas.model.repository.ClienteRepository;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ClienteService {

    private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente pesquisarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, CLIENTE_NAO_ENCONTRADO));
    }

    public List<Cliente> pesquisarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente incluir(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Cliente cliente, Long id) {
//        utilizando forma tradicional de desenvolvimento

//        Cliente encontrado = pesquisarPorId(id);
//
//        cliente.setId(encontrado.getId());
//        return clienteRepository.save(cliente);

//        utilizando API stream
        return clienteRepository.findById(id).map(m -> {
            cliente.setId(m.getId());
            return clienteRepository.save(cliente);
        }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, CLIENTE_NAO_ENCONTRADO));
    }

    public void deletar(Long id) {
//        utilizando forma tradicional de desenvolvimento
//        Cliente encontrado = pesquisarPorId(id);
//        clienteRepository.delete(encontrado);

        clienteRepository.findById(id).map(m -> {
            clienteRepository.delete(m);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, CLIENTE_NAO_ENCONTRADO));
    }
}
