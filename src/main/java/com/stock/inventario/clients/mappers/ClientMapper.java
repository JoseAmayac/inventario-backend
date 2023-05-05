package com.stock.inventario.clients.mappers;

import com.stock.inventario.clients.dto.BasicClientDTO;
import com.stock.inventario.clients.dto.ClientCreationDTO;
import com.stock.inventario.clients.models.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    public Client toEntity(ClientCreationDTO clientCreationDTO);
    public BasicClientDTO toBasicDTO(Client client);
}
