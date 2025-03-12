package com.example.CinemaCity.Controllers;

import com.example.CinemaCity.Dtos.AuthRequestDTO;
import com.example.CinemaCity.Dtos.AuthResponseDTO;
import com.example.CinemaCity.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequestDTO request) {
        return ResponseEntity.ok(userService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody AuthRequestDTO request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String>deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User-ul cu id-ul"+ id +" a fost sters");
    }
}