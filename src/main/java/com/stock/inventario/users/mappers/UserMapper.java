package com.stock.inventario.users.mappers;

import com.stock.inventario.users.dto.BasicUserDTO;
import com.stock.inventario.users.dto.UserCreationDTO;
import com.stock.inventario.users.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    public BasicUserDTO toBasicDTO(User user);
    public User toEntity(UserCreationDTO userCreationDTO);
}
