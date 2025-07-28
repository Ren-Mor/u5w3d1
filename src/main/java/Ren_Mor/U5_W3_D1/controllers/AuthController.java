package Ren_Mor.U5_W3_D1.controllers;

import Ren_Mor.U5_W3_D1.payloads.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Ren_Mor.U5_W3_D1.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginRequest) {
        String token = authService.authenticate(loginRequest);
        return ResponseEntity.ok(token);
    }
}