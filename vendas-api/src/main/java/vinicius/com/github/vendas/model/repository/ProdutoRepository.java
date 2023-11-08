package vinicius.com.github.vendas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vinicius.com.github.vendas.model.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
