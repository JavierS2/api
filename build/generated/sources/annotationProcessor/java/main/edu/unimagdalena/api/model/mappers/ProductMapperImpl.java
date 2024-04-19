package edu.unimagdalena.api.model.mappers;

import edu.unimagdalena.api.model.dto.ProductDTO;
import edu.unimagdalena.api.model.dto_save.ProductToSaveDto;
import edu.unimagdalena.api.model.entities.Product;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-18T21:10:16-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.2 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        Float price = null;
        Integer stock = null;

        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        stock = product.getStock();

        ProductDTO productDTO = new ProductDTO( id, name, price, stock );

        return productDTO;
    }

    @Override
    public Product productDtoToProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( productDTO.id() );
        product.name( productDTO.name() );
        product.price( productDTO.price() );
        product.stock( productDTO.stock() );

        return product.build();
    }

    @Override
    public Product productToSaveDtoToProduct(ProductToSaveDto productToSaveDto) {
        if ( productToSaveDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( productToSaveDto.name() );
        product.price( productToSaveDto.price() );
        product.stock( productToSaveDto.stock() );

        return product.build();
    }
}
