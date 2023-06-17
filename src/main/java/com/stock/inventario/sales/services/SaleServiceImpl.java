package com.stock.inventario.sales.services;

import com.stock.inventario.clients.models.Client;
import com.stock.inventario.clients.repositories.ClientRepository;
import com.stock.inventario.sales.dto.BasicSaleDTO;
import com.stock.inventario.sales.dto.SaleCreationDTO;
import com.stock.inventario.sales.interfaces.SaleService;
import com.stock.inventario.sales.mappers.SaleMapper;
import com.stock.inventario.sales.models.Sale;
import com.stock.inventario.sales.repositories.SaleRepository;
import com.stock.inventario.users.models.User;
import com.stock.inventario.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SaleMapper saleMapper;
    @Override
    public List<BasicSaleDTO> getSales() {
        return this.saleRepository.findAll()
                                  .stream()
                                  .map(sale -> this.saleMapper.toBasicDTO(sale)).toList();
    }

    @Override
    public BasicSaleDTO getSaleById(String id) throws ChangeSetPersister.NotFoundException {
        Sale sale = this.saleRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        return this.saleMapper.toBasicDTO(sale);
    }

    @Override
    public BasicSaleDTO createSale(SaleCreationDTO saleCreation) throws ChangeSetPersister.NotFoundException {
        Client client = this.clientRepository.findById(saleCreation.getClientId()).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        User salesman = this.userRepository.findById(saleCreation.getSalesmanId()).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        Sale sale = this.saleMapper.toEntity(saleCreation);
        sale.setClient(client);
        sale.setSalesman(salesman);
        return this.saleMapper.toBasicDTO(this.saleRepository.save(sale));
    }

    @Override
    public BasicSaleDTO updateSale(String id, SaleCreationDTO saleCreation) throws ChangeSetPersister.NotFoundException {
        this.getSaleById(id);
        Client client = this.clientRepository.findById(saleCreation.getClientId()).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        User salesman = this.userRepository.findById(saleCreation.getSalesmanId()).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        Sale sale = this.saleMapper.toEntity(saleCreation);
        sale.setId(id);
        sale.setClient(client);
        sale.setSalesman(salesman);
        return this.saleMapper.toBasicDTO(this.saleRepository.save(sale));
    }

    @Override
    public void deleteSale(String id) throws ChangeSetPersister.NotFoundException {
        this.getSaleById(id);
        this.saleRepository.deleteById(id);
    }
}
