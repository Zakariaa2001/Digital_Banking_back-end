package com.EBANKBACKEND.EBANKBACKEND.sec;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtEncoder jwtEncoder;
    @GetMapping("/profile")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }
    @PostMapping("/login")
    public Map<String, String> login(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            // Get user role
            //String scope = authentication.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.joining(" "));
            String scope = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));

            // Set claims
            Instant instant = Instant.now();// get date
            JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                    .issuedAt(instant)
                    .expiresAt(instant.plus(10, ChronoUnit.MINUTES))
                    .subject(username)
                    .claim("scope", scope) // roles
                    .build();
            // Create claims
            JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
                    JwsHeader.with(MacAlgorithm.HS512).build(), jwtClaimsSet
            );

            String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
            return Map.of("access-token", jwt);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Return an error response or handle the exception appropriately
            return Map.of("error", "Authentication failed: " + e.getMessage());
        }
    }

}
