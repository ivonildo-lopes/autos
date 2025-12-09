package com.loja.autos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.loja.autos.service.UsuarioServiceImpl;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import com.warrenstrange.googleauth.IGoogleAuthenticator;

@Service
public class TwoFactorAuthService {

    private final IGoogleAuthenticator googleAuthenticator;
    
    @Autowired
    private UsuarioServiceImpl userRepository;
    
    @Value("${google.auth.name.app}")
    private String nameApplication;
    
    
    public TwoFactorAuthService(InMemoryCredentialRepositoryImpl credentialRepository) {
        this.googleAuthenticator = new GoogleAuthenticator();
        googleAuthenticator.setCredentialRepository(credentialRepository);
    }

    public String generateQRUrl(UserSystem user) {

        var secret = googleAuthenticator.createCredentials(user.getUsername());

        user.getUsuario().setSecret2FA(secret.getKey());

        return GoogleAuthenticatorQRGenerator.getOtpAuthURL(nameApplication, user.getUsername(), secret);
    }
    
    public boolean verify2FA(String username, int code) {
        
        UserSystem user = userRepository.loadUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }
        
        String secret = user.getUsuario().getSecret2FA();

        // Validação do código TOTP usando a biblioteca Google Authenticator
        return googleAuthenticator.authorize(secret, code);
    }
    
    public boolean verifyCode(UserSystem user, int code) {
        
        String secretKey = userRepository.findById(user.getUsuario().getId()).getSecret2FA();
        //valida de a chave secreta é ok
        return googleAuthenticator.authorize(secretKey, code);
    }
}
