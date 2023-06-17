package com.stock.inventario.productStatus.services;

import com.stock.inventario.productStatus.dto.BasicProductStatusDTO;
import com.stock.inventario.productStatus.dto.ProductStatusCreationDTO;
import com.stock.inventario.productStatus.interfaces.ProductStatusService;
import com.stock.inventario.productStatus.mappers.ProductStatusMapper;
import com.stock.inventario.productStatus.models.ProductStatus;
import com.stock.inventario.productStatus.repositories.ProductStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductStatusServiceImpl implements ProductStatusService {

    @Autowired
    ProductStatusRepository productStatusRepository;
    @Autowired
    ProductStatusMapper productStatusMapper;

    @Override
    public List<BasicProductStatusDTO> getProductsStatus() {
        return this.productStatusRepository.findAll()
                .stream()
                .map(productStatus -> this.productStatusMapper.toBasicDTO(productStatus))
                .toList();
    }

    @Override
    public BasicProductStatusDTO getProductStatusById(String statusId) throws ChangeSetPersister.NotFoundException {
        ProductStatus productStatus = this.productStatusRepository.findById(statusId).orElseThrow( ()-> new ChangeSetPersister.NotFoundException());
        return this.productStatusMapper.toBasicDTO( productStatus );
    }

    @Override
    public BasicProductStatusDTO createProductStatus(ProductStatusCreationDTO productStatusCreationDTO) {
        ProductStatus productStatus = this.productStatusRepository.save( this.productStatusMapper.toEntity( productStatusCreationDTO ));
        return this.productStatusMapper.toBasicDTO( productStatus );
    }

    @Override
    public BasicProductStatusDTO updateProductStatus(ProductStatusCreationDTO productStatus, String statusId) throws ChangeSetPersister.NotFoundException {
        this.getProductStatusById( statusId );
        ProductStatus productStatusToUpdate = this.productStatusMapper.toEntity( productStatus );
        productStatusToUpdate.setId( statusId );
        return this.productStatusMapper.toBasicDTO( this.productStatusRepository.save( productStatusToUpdate ));
    }

    @Override
    public void deleteProductStatus(String statusId) throws ChangeSetPersister.NotFoundException {
        this.getProductStatusById( statusId );
        this.productStatusRepository.deleteById( statusId );
    }
}
