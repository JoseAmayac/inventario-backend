package com.stock.inventario.users.interfaces;

import com.stock.inventario.users.dto.BasicUserDTO;
import com.stock.inventario.users.dto.UserCreationDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface UserService {
    public List<BasicUserDTO> getUsers();
    public BasicUserDTO getUserById(String id) throws ChangeSetPersister.NotFoundException;
    public BasicUserDTO createUser(UserCreationDTO userCreationDTO);
    public BasicUserDTO updateUser(String id, UserCreationDTO userCreationDTO) throws ChangeSetPersister.NotFoundException;
    public void deleteUser(String id) throws ChangeSetPersister.NotFoundException;
}
