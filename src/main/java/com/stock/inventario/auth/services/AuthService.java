package com.stock.inventario.auth.services;

import com.stock.inventario.auth.dto.AuthenticationResponseDTO;
import com.stock.inventario.auth.dto.CredentialsDTO;
import com.stock.inventario.users.dto.BasicUserDTO;
import com.stock.inventario.users.mappers.UserMapper;
import com.stock.inventario.users.models.User;
import com.stock.inventario.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public BasicUserDTO getById(String id){
        return this.userMapper.toBasicDTO(this.userRepository.findById(id).orElse(null));
    }

    public User getUserByUsername(String username){
        return this.userRepository.findByUsername(username).orElse(null);
    }
    public AuthenticationResponseDTO login(CredentialsDTO credentials){
        User user = this.getUserByUsername(credentials.getUsername());
        if (user == null || !passwordEncoder.matches(credentials.getPassword(), user.getPassword())){
            throw new BadCredentialsException("Nombre de usuario o contraseña incorrectos");
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = this.jwtService.generateToken(user.getId());

        return new AuthenticationResponseDTO(token, this.userMapper.toBasicDTO(user));
    }
}
