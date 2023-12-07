package ViniciusMarquesp.com.github.vendas.controller;

import ViniciusMarquesp.com.github.vendas.dto.CredentialDto;
import ViniciusMarquesp.com.github.vendas.dto.TokenDto;
import ViniciusMarquesp.com.github.vendas.exception.SenhaInvalidaException;
import ViniciusMarquesp.com.github.vendas.model.entity.Usuario;
import ViniciusMarquesp.com.github.vendas.security.jwt.JwtService;
import ViniciusMarquesp.com.github.vendas.service.usuario.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    public Usuario save(@RequestBody @Valid Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioService.save(usuario);
    }

    @PostMapping("/auth")
    public TokenDto authenticate(@RequestBody CredentialDto credentialDto) {
        try {
            Usuario usuario = Usuario.builder()
                    .login(credentialDto.getLogin())
                    .senha(credentialDto.getSenha())
                    .build();

            UserDetails usuarioAutenticado = usuarioService.authenticate(usuario);
            String token = jwtService.gerarToken(usuario);
            TokenDto tokenDto = new TokenDto();
            tokenDto.setLogin(usuario.getLogin());
            tokenDto.setSenha(token);

            return tokenDto;

        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
