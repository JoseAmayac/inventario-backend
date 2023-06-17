package com.stock.inventario.productSales.services;

import com.stock.inventario.productSales.dto.BasicProductSaleDTO;
import com.stock.inventario.productSales.dto.ProductSaleCreationDTO;
import com.stock.inventario.productSales.interfaces.ProductSaleService;
import com.stock.inventario.productSales.mappers.ProductSaleMapper;
import com.stock.inventario.productSales.models.ProductSale;
import com.stock.inventario.productSales.repositories.ProductSalesRepository;
import com.stock.inventario.products.models.Product;
import com.stock.inventario.sales.models.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSaleServiceImpl implements ProductSaleService {
    @Autowired
    private ProductSalesRepository productSalesRepository;
    @Autowired
    private ProductSaleMapper productSaleMapper;

    @Override
    public List<BasicProductSaleDTO> getProductSales(String productId) {
        return this.productSalesRepository.findByProduct(productId)
                    .stream()
                    .map(productSale -> this.productSaleMapper.toBasicDTO( productSale ))
                    .toList();
    }

    @Override
    public List<BasicProductSaleDTO> getSaleProducts(String saleId) {
        return this.productSalesRepository.findBySale(saleId)
                .stream()
                .map(productSale -> this.productSaleMapper.toBasicDTO( productSale ))
                .toList();
    }

    @Override
    public BasicProductSaleDTO getProductSaleById(String productSaleId) throws ChangeSetPersister.NotFoundException {
        ProductSale productSale = this.productSalesRepository.findById( productSaleId ).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        return this.productSaleMapper.toBasicDTO( productSale );
    }

    @Override
    public BasicProductSaleDTO createProductSale(ProductSaleCreationDTO productSaleCreationDTO){
        ProductSale productSale = this.productSaleMapper.toEntity( productSaleCreationDTO );
        /**
         * Establece el producto del producto-venta
         */
        Product product = new Product();
        product.setId( productSaleCreationDTO.getProductId() );
        productSale.setProduct( product );

        /**
         * Establece la venta del producto-venta
         */
        Sale sale = new Sale();
        sale.setId( productSaleCreationDTO.getSaleId() );
        productSale.setSale( sale );

        productSale = this.productSalesRepository.save( productSale );
        return this.productSaleMapper.toBasicDTO( productSale );
    }

    @Override
    public BasicProductSaleDTO updateProductSale(ProductSaleCreationDTO productSaleCreationDTO, String productSaleId) throws ChangeSetPersister.NotFoundException {
        this.getProductSaleById( productSaleId );
        ProductSale productSale = this.productSaleMapper.toEntity( productSaleCreationDTO );
        productSale.setId( productSaleId );

        Product product = new Product();
        product.setId( productSaleCreationDTO.getProductId() );
        productSale.setProduct( product );

        Sale sale = new Sale();
        sale.setId( productSaleCreationDTO.getSaleId() );
        productSale.setSale( sale );

        productSale = this.productSalesRepository.save( productSale );
        return this.productSaleMapper.toBasicDTO( productSale );
    }

    @Override
    public void deleteProductSale(String productSaleId) throws ChangeSetPersister.NotFoundException {
        this.getProductSaleById( productSaleId );
        this.productSalesRepository.deleteById( productSaleId );
    }
}
