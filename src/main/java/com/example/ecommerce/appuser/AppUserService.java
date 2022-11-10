package com.example.ecommerce.appuser;

import lombok.AllArgsConstructor;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

/**
 * @author vivek
 */

@AllArgsConstructor
@Service
public class AppUserService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s is not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService service;

    @Autowired
    Environment environment;

    public AppUser loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> appUser = appUserRepository.findByEmail(email);
        if (appUser.isPresent()) {
            return appUser.get();
        } else {
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
        }
    }

    public String signUpUser(AppUser appUser)
    {
        Optional<AppUser> optionalAppUser = appUserRepository.findByEmail(appUser.getEmail());
        if(optionalAppUser.isPresent()){
            return "email already taken";
        }

        String encodePassword = bCryptPasswordEncoder.encode(appUser.getPassword());
    }
}
