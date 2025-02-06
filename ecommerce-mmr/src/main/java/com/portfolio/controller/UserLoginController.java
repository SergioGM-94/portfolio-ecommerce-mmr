package com.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.model.EncryptionUtils;
import com.portfolio.model.LoginRequest;
import com.portfolio.model.UserLogin;
import com.portfolio.service.UserLoginService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"http://localhost:4200", "https://portfolio-angular-ecommerce-mmr.vercel.app"})
public class UserLoginController {
	@Autowired
    private UserLoginService userLoginService;

    @GetMapping
    public ResponseEntity<?> listUserLogin() {
        return ResponseEntity.ok(userLoginService.listUserLogin());
    }
    
    @GetMapping("/{id}")
	public ResponseEntity<UserLogin> getUser(@PathVariable int id) {
		return ResponseEntity.ok(userLoginService.listUserLoginId(id)) ;
	}

    @PostMapping
    public ResponseEntity<?> saveUserLogin(@RequestBody UserLogin login) {
        login.setPassword(EncryptionUtils.hashPassword(login.getPassword()));
        UserLogin newUser = userLoginService.saveUserLogin(login);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserLogin(@PathVariable int id, @RequestBody UserLogin login) {
        /*login.setPassword(EncryptionUtils.hashPassword(login.getPassword())); // Encriptar la contraseña*/
        UserLogin updatedLogin = userLoginService.editUserLogin(id, login);
        return ResponseEntity.ok(updatedLogin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserLogin(@PathVariable int id) {
        userLoginService.deleteUserLogin(id);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUserLogin(@RequestBody LoginRequest loginRequest) {
        try {
            UserLogin userLogin = userLoginService.authenticateUserLogin(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(userLogin);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
        }
    }
}
