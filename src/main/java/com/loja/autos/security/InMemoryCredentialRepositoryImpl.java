package com.loja.autos.security;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.loja.autos.repository.UsuarioRepository;
import com.loja.autos.service.UsuarioServiceImpl;
import com.warrenstrange.googleauth.ICredentialRepository;

@Component
public class InMemoryCredentialRepositoryImpl implements ICredentialRepository {

    @Autowired
    private UsuarioRepository userRepository;
    
    @Autowired
    private UsuarioServiceImpl service;
    
    // remove o hashmap se preferir não manter este armazenamento em memória conforme necessário.
    private final HashMap<String, String> usersKey = new HashMap<>();

    @Override
    public String getSecretKey(String username) {
        UserSystem user = service.loadUserByUsername(username);
        return user != null ? user.getUsuario().getSecret2FA() : usersKey.get(username);
    }

    @Override
    public void saveUserCredentials(String username, String secretKey, int validationCode, List<Integer> scratchCodes) {
        // Persistindo os dados no banco associado ao usuário
        UserSystem user = service.loadUserByUsername(username);
        if (user != null) {
        	
        	var model = userRepository.findById(user.getUsuario().getId()).get();
        	
        	model.setSecret2FA(secretKey);
            userRepository.save(model);
        } else {
            usersKey.put(username, secretKey); // apenas como exemplo, deve ser evitado em prática
        }
    }
}
