package com.stock.inventario.productStatus.interfaces;

import com.stock.inventario.productStatus.dto.BasicProductStatusDTO;
import com.stock.inventario.productStatus.dto.ProductStatusCreationDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ProductStatusService {
    public List<BasicProductStatusDTO> getProductsStatus();
    public BasicProductStatusDTO getProductStatusById(String statusId) throws ChangeSetPersister.NotFoundException;
    public BasicProductStatusDTO createProductStatus(ProductStatusCreationDTO productStatusCreationDTO);
    public BasicProductStatusDTO updateProductStatus(ProductStatusCreationDTO productStatus, String statusId) throws ChangeSetPersister.NotFoundException;
    public void deleteProductStatus(String statusId) throws ChangeSetPersister.NotFoundException;
}
