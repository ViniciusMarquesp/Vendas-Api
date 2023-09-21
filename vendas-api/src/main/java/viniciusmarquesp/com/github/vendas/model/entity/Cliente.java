package viniciusmarquesp.com.github.vendas.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CLIENTES")
public class Cliente {
    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "nome")
    @NotEmpty(message = "Campo NOME deve ser informado")
    private String nome;

    @Getter
    @Column(name = "endereco")
    private String endereco;

    @Getter
    @Column(name = "email")
    private String email;

    @Getter
    @Column(name = "telefone")
    private String telefone;

    @Getter
    @Column(name = "cpf")
    @NotEmpty(message = "Campo CPF deve ser informado")
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

//    public Cliente() {
//    }

//    public Cliente(Long id, String nome, String endereco, String email, String telefone, String cpf, Set<Pedido> pedidos) {
//        this.id = id;
//        this.nome = nome;
//        this.endereco = endereco;
//        this.email = email;
//        this.telefone = telefone;
//        this.cpf = cpf;
//        this.pedidos = pedidos;
//    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//    public String getEndereco() {
//        return endereco;
//    }
//
//    public void setEndereco(String endereco) {
//        this.endereco = endereco;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getTelefone() {
//        return telefone;
//    }
//
//    public void setTelefone(String telefone) {
//        this.telefone = telefone;
//    }
//
//    public String getCpf() {
//        return cpf;
//    }
//
//    public void setCpf(String cpf) {
//        this.cpf = cpf;
//    }
//
//    public Set<Pedido> getPedidos() {
//        return pedidos;
//    }
//
//    public void setPedidos(Set<Pedido> pedidos) {
//        this.pedidos = pedidos;
//    }
}
