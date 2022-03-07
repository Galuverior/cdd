package com.github.galuverior.cdd.security;

import com.github.galuverior.cdd.client.dto.AuthDTO;
import com.github.galuverior.cdd.client.service.ClientService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserResource {

    private final ClientService clientService;

    public UserResource(ClientService clientService) {

        this.clientService = clientService;
    }

    @PostMapping("user")
    public ResponseEntity<AuthDTO> login(@RequestParam("user") String email, @RequestParam("password") String senha) {


        try {
            clientService.validateClientCredentials(email, senha);
            String token = getJWTToken(email, senha);
            AuthDTO authDTO = new AuthDTO();
            authDTO.setEmail(email);
            authDTO.setToken(token);
            authDTO.setSenha(senha);
            return ResponseEntity.ok().body(authDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    private String getJWTToken(String email, String senha) {
        String secretKey = "myKeyIsSecret";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("bill")
                .setSubject(email + senha)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return token;
    }
}
