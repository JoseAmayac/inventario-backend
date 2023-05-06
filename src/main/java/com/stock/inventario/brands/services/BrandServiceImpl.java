package com.stock.inventario.brands.services;

import com.stock.inventario.brands.dto.BasicBrandDTO;
import com.stock.inventario.brands.dto.BrandCreationDTO;
import com.stock.inventario.brands.interfaces.BrandService;
import com.stock.inventario.brands.mappers.BrandMapper;
import com.stock.inventario.brands.models.Brand;
import com.stock.inventario.brands.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private BrandMapper brandMapper;
    @Override
    public List<BasicBrandDTO> getAllBrands() {
        return this.brandRepository.findAll()
                .stream()
                .map(brand -> this.brandMapper.toBasicDTO(brand))
                .toList();
    }

    @Override
    public BasicBrandDTO getBrandById(String id) throws ChangeSetPersister.NotFoundException {
        Brand brand = this.brandRepository.findById(id).orElseThrow(()-> new ChangeSetPersister.NotFoundException());
        return this.brandMapper.toBasicDTO(brand);
    }

    @Override
    public BasicBrandDTO createBrand(BrandCreationDTO brandCreationDTO) {
        Brand brand = this.brandRepository.save(this.brandMapper.toEntity(brandCreationDTO));
        return this.brandMapper.toBasicDTO(this.brandRepository.save(brand));
    }

    @Override
    public BasicBrandDTO updateBrand(String id, BrandCreationDTO brandCreationDTO) throws ChangeSetPersister.NotFoundException {
        Brand brand = this.brandMapper.toEntity(this.getBrandById(id));
        brand.setId(id);
        Brand brandToUpdate = this.brandMapper.toEntity(brandCreationDTO);
        brandToUpdate.setId(brand.getId());
        return this.brandMapper.toBasicDTO(this.brandRepository.save(brandToUpdate));
    }

    @Override
    public void deleteBrand(String id) throws ChangeSetPersister.NotFoundException {
        BasicBrandDTO brandDTO = this.getBrandById(id);
        this.brandRepository.deleteById(brandDTO.getId());
    }
}
