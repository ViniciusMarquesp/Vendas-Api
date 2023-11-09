package ViniciusMarquesp.com.github.vendas.service.pedido;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ViniciusMarquesp.com.github.vendas.dto.ItemPedidoDto;
import ViniciusMarquesp.com.github.vendas.dto.PedidoDto;
import ViniciusMarquesp.com.github.vendas.enums.StatusPedido;
import ViniciusMarquesp.com.github.vendas.exception.ResourceNotFoundException;
import ViniciusMarquesp.com.github.vendas.model.entity.Cliente;
import ViniciusMarquesp.com.github.vendas.model.entity.ItemPedido;
import ViniciusMarquesp.com.github.vendas.model.entity.Pedido;
import ViniciusMarquesp.com.github.vendas.model.entity.Produto;
import ViniciusMarquesp.com.github.vendas.model.repository.ClienteRepository;
import ViniciusMarquesp.com.github.vendas.model.repository.ItemPedidoRepository;
import ViniciusMarquesp.com.github.vendas.model.repository.PedidoRepository;
import ViniciusMarquesp.com.github.vendas.model.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    public Pedido incluir(PedidoDto pedidoDto) {
        Cliente cliente = findCliente(pedidoDto);
        Pedido pedido = newPedido(pedidoDto, cliente);
        List<ItemPedido> itensPedido = convertItemPedido(pedido, pedidoDto.getItens());

        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itensPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> pedidoCompleto(Long id) {
        return pedidoRepository.findByIdFetchItens(id);
    }

    @Override
    public void alterarStatus(Long id, StatusPedido statusPedido) {
        pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidoRepository.save(pedido);
                }).orElseThrow(() -> new ResourceNotFoundException("Código de pedido inválido"));
    }

    private Cliente findCliente(PedidoDto pedidoDto) {
        Long idCliente = pedidoDto.getCliente();
        return clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Código de cliente inválido"));
    }

    private Pedido newPedido(PedidoDto pedidoDto, Cliente cliente) {
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDate.now());
        pedido.setTotal(pedidoDto.getTotal());
        pedido.setStatus(StatusPedido.REALIZADO);
        return pedido;
    }

    private List<ItemPedido> convertItemPedido(Pedido pedido, List<ItemPedidoDto> itensPedidoDto) {
        if (itensPedidoDto.isEmpty()) {
            throw new ResourceNotFoundException("Não é possível realizar um pedido sem itens.");
        }

        return itensPedidoDto.stream()
                .map(itemPedidoDto -> {
                    Long idProduto = itemPedidoDto.getProduto();
                    Produto produto = produtoRepository.findById(idProduto)
                            .orElseThrow(() -> new ResourceNotFoundException("Código de produto inválido."));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    itemPedido.setQuantidade(itemPedidoDto.getQuantidade());

                    return itemPedido;
                })
                .collect(Collectors.toList());
    }

}
