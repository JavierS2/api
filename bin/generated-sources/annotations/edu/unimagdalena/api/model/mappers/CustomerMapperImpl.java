package edu.unimagdalena.api.model.mappers;

import edu.unimagdalena.api.model.dto.CustomerDTO;
import edu.unimagdalena.api.model.dto_save.CustomerToSaveDto;
import edu.unimagdalena.api.model.entities.Customer;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-20T13:25:44-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240325-1403, environment: Java 17.0.10 (Eclipse Adoptium)"
)
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO customerToCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String email = null;
        String address = null;

        id = customer.getId();
        name = customer.getName();
        email = customer.getEmail();
        address = customer.getAddress();

        CustomerDTO customerDTO = new CustomerDTO( id, name, email, address );

        return customerDTO;
    }

    @Override
    public Customer customerDtoToCustomer(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.address( customerDTO.address() );
        customer.email( customerDTO.email() );
        customer.id( customerDTO.id() );
        customer.name( customerDTO.name() );

        return customer.build();
    }

    @Override
    public Customer customerToSaveDtoToCustomer(CustomerToSaveDto customerToSaveDto) {
        if ( customerToSaveDto == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.address( customerToSaveDto.address() );
        customer.email( customerToSaveDto.email() );
        customer.name( customerToSaveDto.name() );

        return customer.build();
    }

    @Override
    public CustomerToSaveDto customerToCustomerToSaveDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        String name = null;
        String email = null;
        String address = null;

        name = customer.getName();
        email = customer.getEmail();
        address = customer.getAddress();

        CustomerToSaveDto customerToSaveDto = new CustomerToSaveDto( name, email, address );

        return customerToSaveDto;
    }
}
