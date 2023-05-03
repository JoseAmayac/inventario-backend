package com.stock.inventario.users.services;

import com.stock.inventario.users.dto.BasicUserDTO;
import com.stock.inventario.users.dto.UserCreationDTO;
import com.stock.inventario.users.interfaces.UserService;
import com.stock.inventario.users.mappers.UserMapper;
import com.stock.inventario.users.models.User;
import com.stock.inventario.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<BasicUserDTO> getUsers() {
        return this.userRepository.findAll()
                                    .stream()
                                    .map(user -> this.userMapper.toBasicDTO(user))
                                    .toList();
    }

    @Override
    public BasicUserDTO getUserById(String id) throws ChangeSetPersister.NotFoundException {
        User user = this.userRepository.findById(id).orElse(null);
        if ( user == null ) throw new ChangeSetPersister.NotFoundException();

        return this.userMapper.toBasicDTO(user);
    }

    @Override
    public BasicUserDTO createUser(UserCreationDTO userCreationDTO) {
        userCreationDTO.setPassword(this.hashPassword(userCreationDTO.getPassword()));
        User userDB = this.userRepository.save(this.userMapper.toEntity(userCreationDTO));
        return this.userMapper.toBasicDTO(userDB);
    }

    @Override
    public BasicUserDTO updateUser(String id, UserCreationDTO userCreationDTO) throws ChangeSetPersister.NotFoundException {
        try {
            BasicUserDTO userDTO = this.getUserById(id); // Usado para saber si el usuario sí existe con ese id

            User user = this.userMapper.toEntity(userCreationDTO);
            user.setId(userDTO.getId());

            return this.userMapper.toBasicDTO(this.userRepository.save(user));
        }catch (ChangeSetPersister.NotFoundException ex){
            throw new ChangeSetPersister.NotFoundException();
        }

    }

    @Override
    public void deleteUser(String id) throws ChangeSetPersister.NotFoundException {
        try {
            BasicUserDTO userDTO = this.getUserById(id);
            this.userRepository.deleteById(userDTO.getId());
        }catch (ChangeSetPersister.NotFoundException ex){
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    private String hashPassword(String plainPassword){
        return this.passwordEncoder.encode(plainPassword);
    }
}
