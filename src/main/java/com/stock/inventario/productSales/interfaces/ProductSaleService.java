package com.stock.inventario.productSales.interfaces;

import com.stock.inventario.productSales.dto.BasicProductSaleDTO;
import com.stock.inventario.productSales.dto.ProductSaleCreationDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ProductSaleService {
    List<BasicProductSaleDTO> getProductSales(String productId);
    List<BasicProductSaleDTO> getSaleProducts(String saleId);
    BasicProductSaleDTO getProductSaleById(String productSaleId) throws ChangeSetPersister.NotFoundException;
    BasicProductSaleDTO createProductSale(ProductSaleCreationDTO productSaleCreationDTO);
    BasicProductSaleDTO updateProductSale(ProductSaleCreationDTO productSaleCreationDTO, String productSaleId) throws ChangeSetPersister.NotFoundException;
    void deleteProductSale(String productSaleId) throws ChangeSetPersister.NotFoundException;
}
