package edu.unimagdalena.api.model.mappers;

import edu.unimagdalena.api.model.dto_save.ProductToSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.unimagdalena.api.model.entities.Product;
import edu.unimagdalena.api.model.dto.ProductDTO;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO productToProductDto(Product product);

    @Mapping(target = "orderItems", ignore = true)
    Product productDtoToProduct(ProductDTO productDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Product productToSaveDtoToProduct(ProductToSaveDto productToSaveDto);

}
