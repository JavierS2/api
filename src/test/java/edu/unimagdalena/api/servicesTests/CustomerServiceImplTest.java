package edu.unimagdalena.api.servicesTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import edu.unimagdalena.api.model.entities.Customer;
import edu.unimagdalena.api.model.dto.CustomerDTO;
import edu.unimagdalena.api.model.mappers.CustomerMapper;
import edu.unimagdalena.api.repository.CustomerRepository;
import edu.unimagdalena.api.service.implementations.CustomerServiceImpl;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    Customer customer1 = Customer.builder()
            .id(1l)
            .name("pepe")
            .email("pepe@example.co")
            .address("1234")
            .build();
    
    Customer customer2 = Customer.builder()
            .name("juan")
            .email("juan@example.co")
            .address("1234")
            .build();
    
    CustomerDTO customerDTO = CustomerMapper.INSTANCE.customerToCustomerDto(customer1);  
    
    @BeforeEach
    void setUp() {
        when(customerRepository.save(any())).thenReturn(customer1);
        when(customerRepository.findAll()).thenReturn(List.of(customer1));
        when(customerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(customer1));
    }
    
    @Test
    void testCreate() {
        //when
        CustomerDTO saved = customerService.create(customerDTO);
        //then
        assertThat(saved).isNotNull();
        assertThat(saved.id()).isEqualTo(1l);
    }

    @Test
    void testDelete() {
        //when
        CustomerDTO saved = customerService.create(customerDTO);
        when(customerRepository.count()).thenReturn(1L);
        assertEquals(1L, customerRepository.count());
        //then
        customerService.delete(saved.id());
        when(customerRepository.count()).thenReturn(0L);
        assertEquals(0L, customerRepository.count());
    }

    @Test
    void testGetAllCustomers() {
        //when
        customerService.create(customerDTO);
        customerService.create(customerDTO);
        //then
        assertThat(customerService.getAllCustomers().size()).isEqualTo(1);
    }

    @Test
    void testGetCustomerByEmail() {
        //when
        customerService.create(customerDTO);
        when(customerRepository.findByEmail(anyString())).thenReturn(customer1);
        CustomerDTO customerFind = customerService.getCustomerByEmail("address1");
        //then
        assertThat(customerFind.id()).isEqualTo(1l);
    }

    @Test
    void testGetCustomerById() {
        //when
        customerService.create(customerDTO);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(customer1));
        CustomerDTO customerFind = customerService.getCustomerById(1l);
        //then
        assertThat(customerFind.id()).isEqualTo(1l);
    }

    @Test
    void testGetCustomersByAddress() {
        //when
        customerService.create(customerDTO);
        when(customerRepository.findByAddress(anyString())).thenReturn(List.of(customer1, customer2));
        List<CustomerDTO> customerFind = customerService.getCustomersByAddress("1234");
        //then
        assertThat(customerFind.size()).isEqualTo(2);
    }

    @Test
    void testGetCustomersByNameStartsWith() {
        //when
        when(customerRepository.findByNameStartsWith(anyString())).thenReturn(List.of(customer1));
        customerService.create(customerDTO);
        //then
        assertEquals(1,customerService.getCustomersByNameStartsWith("pepe").size());
    }

    @Test
    void testUpdate() {
        //when
        customerService.create(customerDTO);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(customer1));
        CustomerDTO customerFind = customerService.update(customerDTO, 1l);
        //then
        assertEquals("pepe@example.co", customerFind.email());
    }
}
