package ViniciusMarquesp.com.github.vendas.model.repository;

import ViniciusMarquesp.com.github.vendas.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String Login);
}
