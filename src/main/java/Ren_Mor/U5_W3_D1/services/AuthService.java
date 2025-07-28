package Ren_Mor.U5_W3_D1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import Ren_Mor.U5_W3_D1.payloads.LoginDTO;
import Ren_Mor.U5_W3_D1.tools.JWTTools;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserDetailsService userDetailsService;

    public String authenticate(LoginDTO loginRequest) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
        );
        var user = (Ren_Mor.U5_W3_D1.entities.Dipendente) auth.getPrincipal();
        return jwtTools.createToken(user);
    }
}
