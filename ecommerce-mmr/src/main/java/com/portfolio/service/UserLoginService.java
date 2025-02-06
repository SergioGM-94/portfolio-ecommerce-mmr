package com.portfolio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.model.EncryptionUtils;
import com.portfolio.model.UserLogin;
import com.portfolio.repository.IUserLoginRepository;

@Service
public class UserLoginService {
	 @Autowired
	    private IUserLoginRepository userLoginRepository;

	    // Listar todos los usuarios
	    public List<UserLogin> listUserLogin() {
	        return userLoginRepository.findAll();
	    }

	    // Buscar usuario por ID
	    public UserLogin listUserLoginId(int id) {
	        return userLoginRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + id));
	    }

	    // Guardar nuevo usuario
	    public UserLogin saveUserLogin(UserLogin login) {
	        return userLoginRepository.save(login);
	    }

	    public UserLogin editUserLogin(int id, UserLogin login) {
	        return userLoginRepository.findById(id)
	                .map(existingLogin -> {
	                    existingLogin.setUser(login.getUser());
	                    existingLogin.setPassword(login.getPassword());
	                    existingLogin.setEmail(login.getEmail());// Se encripta antes de guardar
	                    existingLogin.setType(login.getType());
	                    return userLoginRepository.save(existingLogin);
	                })
	                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + id));
	    }


	    // Eliminar un usuario por ID
	    public void deleteUserLogin(int id) {
	        if (userLoginRepository.existsById(id)) {
	            userLoginRepository.deleteById(id);
	        } else {
	            throw new IllegalArgumentException("Usuario no encontrado con id: " + id);
	        }
	    }
	    
	    public UserLogin authenticateUserLogin(String email, String password) {
	        Optional<UserLogin> optionalUser = userLoginRepository.findByEmail(email);
	        if (optionalUser.isPresent()) {
	            UserLogin userLogin = optionalUser.get();
	            if (EncryptionUtils.verifyPassword(password, userLogin.getPassword())) {
	                return userLogin;
	            }
	        }
	        throw new IllegalArgumentException("Email o contraseña incorrectos");
	    }
}
