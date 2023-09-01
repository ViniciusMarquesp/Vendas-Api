package viniciusmarquesp.com.github.vendas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import viniciusmarquesp.com.github.vendas.model.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
