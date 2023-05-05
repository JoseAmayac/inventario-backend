package com.stock.inventario.clients.services;

import com.stock.inventario.clients.dto.BasicClientDTO;
import com.stock.inventario.clients.dto.ClientCreationDTO;
import com.stock.inventario.clients.interfaces.ClientService;
import com.stock.inventario.clients.mappers.ClientMapper;
import com.stock.inventario.clients.models.Client;
import com.stock.inventario.clients.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;
    @Override
    public List<BasicClientDTO> getClients() {
        return this.clientRepository.findAll()
                                    .stream()
                                    .map(client -> this.clientMapper.toBasicDTO(client))
                                    .toList();
    }

    @Override
    public BasicClientDTO getClientById(String id) throws ChangeSetPersister.NotFoundException {
        Client client = this.clientRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        return this.clientMapper.toBasicDTO(client);
    }

    @Override
    public BasicClientDTO createClient(ClientCreationDTO clientCreationDTO) {
        Client client = this.clientRepository.save(this.clientMapper.toEntity(clientCreationDTO));
        return this.clientMapper.toBasicDTO(client);
    }

    @Override
    public BasicClientDTO updateClient(String id, ClientCreationDTO clientCreationDTO) throws ChangeSetPersister.NotFoundException {
        Client client = this.clientMapper.toEntity(this.getClientById(id));
        Client clientToUpdate = this.clientMapper.toEntity(clientCreationDTO);
        clientToUpdate.setId(client.getId());

        return this.clientMapper.toBasicDTO(this.clientRepository.save(clientToUpdate));
    }

    @Override
    public void deleteClient(String id) throws ChangeSetPersister.NotFoundException {
        BasicClientDTO client = this.getClientById(id);
        this.clientRepository.deleteById(client.getId());
    }
}
