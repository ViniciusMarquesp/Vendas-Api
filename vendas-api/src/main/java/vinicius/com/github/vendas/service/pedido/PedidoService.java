package vinicius.com.github.vendas.service.pedido;

import java.util.Optional;

import vinicius.com.github.vendas.dto.PedidoDto;
import vinicius.com.github.vendas.enums.StatusPedido;
import vinicius.com.github.vendas.model.entity.Pedido;

public interface PedidoService {
    
    Pedido incluir(PedidoDto pedidoDto);

    Optional<Pedido> pedidoCompleto(Long id);

    void alterarStatus(Long id, StatusPedido statusPedido);
    
}
