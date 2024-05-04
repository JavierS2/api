package edu.unimagdalena.api.model.mappers;

import edu.unimagdalena.api.model.dto.CustomerDTO;
import edu.unimagdalena.api.model.dto_save.CustomerToSaveDto;
import edu.unimagdalena.api.model.entities.Customer;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-03T19:36:41-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.10 (Oracle Corporation)"
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
        String roles = null;
        String address = null;

        id = customer.getId();
        name = customer.getName();
        email = customer.getEmail();
        roles = customer.getRoles();
        address = customer.getAddress();

        CustomerDTO customerDTO = new CustomerDTO( id, name, email, roles, address );

        return customerDTO;
    }

    @Override
    public Customer customerDtoToCustomer(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.id( customerDTO.id() );
        customer.name( customerDTO.name() );
        customer.email( customerDTO.email() );
        customer.address( customerDTO.address() );
        customer.roles( customerDTO.roles() );

        return customer.build();
    }

    @Override
    public Customer customerToSaveDtoToCustomer(CustomerToSaveDto customerToSaveDto) {
        if ( customerToSaveDto == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.name( customerToSaveDto.name() );
        customer.password( customerToSaveDto.password() );
        customer.email( customerToSaveDto.email() );
        customer.address( customerToSaveDto.address() );
        customer.roles( customerToSaveDto.roles() );

        return customer.build();
    }

    @Override
    public CustomerToSaveDto customerToCustomerToSaveDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        String name = null;
        String password = null;
        String email = null;
        String roles = null;
        String address = null;

        name = customer.getName();
        password = customer.getPassword();
        email = customer.getEmail();
        roles = customer.getRoles();
        address = customer.getAddress();

        CustomerToSaveDto customerToSaveDto = new CustomerToSaveDto( name, password, email, roles, address );

        return customerToSaveDto;
    }
}
