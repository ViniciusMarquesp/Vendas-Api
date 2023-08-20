package viniciusmarquesp.com.github.vendas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import viniciusmarquesp.com.github.vendas.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
