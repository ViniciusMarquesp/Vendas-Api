package viniciusmarquesp.com.github.vendas.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.patterns.PerObject;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUTOS")
public class Produto {

    @Id
    @Getter
    @Setter
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "descricao", length = 100)
    private String descricao;

    @Getter
    @Column(name = "preco_unitario")
    private BigDecimal preco;
}
