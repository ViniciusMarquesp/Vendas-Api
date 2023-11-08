package vinicius.com.github.vendas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vinicius.com.github.vendas.model.entity.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

}
