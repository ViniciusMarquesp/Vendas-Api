package ViniciusMarquesp.com.github.vendas.service.pedido;

import java.util.Optional;

import ViniciusMarquesp.com.github.vendas.dto.PedidoDto;
import ViniciusMarquesp.com.github.vendas.enums.StatusPedido;
import ViniciusMarquesp.com.github.vendas.model.entity.Pedido;

public interface PedidoService {
    
    Pedido incluir(PedidoDto pedidoDto);

    Optional<Pedido> pedidoCompleto(Long id);

    void alterarStatus(Long id, StatusPedido statusPedido);
    
}
