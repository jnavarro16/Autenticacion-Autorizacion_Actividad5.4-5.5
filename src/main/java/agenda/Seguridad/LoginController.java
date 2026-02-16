package agenda.Seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.FORBIDDEN;

//Controlador
@RestController
public class LoginController
{
    @Autowired
    JWTAuthenticationConfig jwtAuthenticationConfig;

    @PostMapping("/login")
    public String login (@RequestParam("user") String username,
                         @RequestParam("encryptedPass") String encryptedPass) {

        if(!(username.equals(Constans.USER) && encryptedPass.equals(Constans.PASSWORD))) {
            throw new ResponseStatusException(FORBIDDEN, "Credenciales incorrectas");
        }

        return jwtAuthenticationConfig.getJWTToken(username);
    }
}
