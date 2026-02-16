package agenda.Seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.FORBIDDEN;

//Controlador
@RestController
public class LoginController
{
    @Autowired
    JWTAuthenticationConfig jwtAuthenticationConfig;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @PostMapping("/login")
    public String login (@RequestParam("user") String username,
                         @RequestParam("encryptedPass") String encryptedPass)
    {
        List<Usuario> usuarios = usuarioRepositorio.getUsuarios();
        Usuario usuarioEncontrado = null;

        for (Usuario usuario : usuarios) {
            if(usuario.getUsername().equals(username) &&
                PasswordEncryptor.decrypt(usuario.getEncryptedPass()).equals(encryptedPass)) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        if(usuarioEncontrado == null) {
            throw new ResponseStatusException(FORBIDDEN, "Credenciales incorrectas");
        }

        return jwtAuthenticationConfig.getJWTToken(
                usuarioEncontrado.getUsername(),
                usuarioEncontrado.getRol()
        );
    }
}
