package com.stock.inventario.sales.interfaces;

import com.stock.inventario.sales.dto.BasicSaleDTO;
import com.stock.inventario.sales.dto.SaleCreationDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface SaleService {

    public List<BasicSaleDTO> getSales();
    public BasicSaleDTO getSaleById(String id) throws ChangeSetPersister.NotFoundException;
    public BasicSaleDTO createSale(SaleCreationDTO saleCreation) throws ChangeSetPersister.NotFoundException;
    public BasicSaleDTO updateSale(String id, SaleCreationDTO saleCreation) throws ChangeSetPersister.NotFoundException;
    public void deleteSale(String id) throws ChangeSetPersister.NotFoundException;
}
