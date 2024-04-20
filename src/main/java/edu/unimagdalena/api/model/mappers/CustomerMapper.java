package edu.unimagdalena.api.model.mappers;

import edu.unimagdalena.api.model.dto_save.CustomerToSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.unimagdalena.api.model.entities.Customer;
import edu.unimagdalena.api.model.dto.CustomerDTO;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDto(Customer customer);

    @Mapping(target = "orders", ignore = true)
    Customer customerDtoToCustomer(CustomerDTO customerDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Customer customerToSaveDtoToCustomer(CustomerToSaveDto customerToSaveDto);

    CustomerToSaveDto customerToCustomerToSaveDto(Customer customer);

}
