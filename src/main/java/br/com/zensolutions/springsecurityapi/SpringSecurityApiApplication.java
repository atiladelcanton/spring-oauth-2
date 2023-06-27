package br.com.zensolutions.springsecurityapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringSecurityApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApiApplication.class, args);
    }


}

@RestController
class HttpController {
    @GetMapping("/public")
    String publicRoute() {
        return "<h1>Public route, fell free to look arround!</h1>";
    }

    @GetMapping("/private")
    String privateRoute(@AuthenticationPrincipal OidcUser principal) {

        return String.format(
                """
                        <h1>Private route, only authorized personal!</h1>
                        <h3>Principal: %s</h3>
                        <h3>Email attribute: %s</h3>
                        <h3>Authorities: %s</h3>
                        <h3>JWT: %s
                        """,
                principal, principal.getAttribute("email"), principal.getAuthorities(),
                principal.getIdToken().getTokenValue()
        );
    }

}