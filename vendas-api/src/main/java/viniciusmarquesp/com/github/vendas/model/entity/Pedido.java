package viniciusmarquesp.com.github.vendas.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.collection.internal.PersistentIdentifierBag;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PEDIDOS")
public class Pedido {

    @Id
    @Getter
    @Setter
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private  Cliente cliente;

    @Getter
    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Getter
    @Column(name = "total")
    private BigDecimal total;

    @Getter
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;
}