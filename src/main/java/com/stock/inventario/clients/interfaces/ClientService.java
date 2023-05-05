package com.stock.inventario.clients.interfaces;

import com.stock.inventario.clients.dto.BasicClientDTO;
import com.stock.inventario.clients.dto.ClientCreationDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ClientService {
    public List<BasicClientDTO> getClients();
    public BasicClientDTO getClientById(String id) throws ChangeSetPersister.NotFoundException;
    public BasicClientDTO createClient(ClientCreationDTO clientCreationDTO);
    public BasicClientDTO updateClient(String id, ClientCreationDTO clientCreationDTO) throws ChangeSetPersister.NotFoundException;
    public void deleteClient(String id) throws ChangeSetPersister.NotFoundException;
}
