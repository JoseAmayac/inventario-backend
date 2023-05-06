package com.stock.inventario.brands.interfaces;

import com.stock.inventario.brands.dto.BasicBrandDTO;
import com.stock.inventario.brands.dto.BrandCreationDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface BrandService {
    public List<BasicBrandDTO> getAllBrands();
    public BasicBrandDTO getBrandById(String id) throws ChangeSetPersister.NotFoundException;
    public BasicBrandDTO createBrand(BrandCreationDTO brandCreationDTO);
    public BasicBrandDTO updateBrand(String id, BrandCreationDTO brandCreationDTO) throws ChangeSetPersister.NotFoundException;
    public void deleteBrand(String id) throws ChangeSetPersister.NotFoundException;
}
