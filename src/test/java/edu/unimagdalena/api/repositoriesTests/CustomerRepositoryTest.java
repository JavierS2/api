package edu.unimagdalena.api.repositoriesTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.Assertions.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import edu.unimagdalena.api.AbstractIntegrationDBTest;
import edu.unimagdalena.api.model.entities.Customer;
import edu.unimagdalena.api.repository.CustomerRepository;


class CustomerRepositoryTest extends AbstractIntegrationDBTest{
    
    CustomerRepository customerRepository;

    @Autowired
    public CustomerRepositoryTest(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    Customer customer1 = Customer.builder()
            .id(1l)
            .name("pepe")
            .email("pepe@example.co")
            .address("1234")
            .orders(null)
            .build();
            
    Customer customer2 = Customer.builder()
            .name("pedro")
            .email("pedro@example.co")
            .address("1234")
            .orders(null)
            .build();
    
    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }
    
    @Test
    @DisplayName("test save")
    void givenAnCustomer_WhenSave_ThenCustomerWithId(){
        //given
        Customer customerSaved = customerRepository.save(customer1);
        //then
        assertThat(customerSaved.getId()).isNotNull();
    }

    @Test
    @DisplayName("test read")
    void givenCustomers_ThenGetAll(){
        //given
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        List<Customer> cusromers = customerRepository.findAll();
        //then
        assertThat(cusromers.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("test delete")
    void givenIdCustomer_thenDeleteIt(){
        //given
        customerRepository.save(customer1);
        Long id = customer1.getId();
        //when
        customerRepository.deleteById(id);
        //then
        assertThat(customerRepository.findById(id)).isEmpty();
    }

    @Test
    @DisplayName("test update")
    void givenAnCustomer_ThenUpdateThis(){
        //given
        Long id = customerRepository.save(customer2).getId();
        String newNombre = "jairo";
        //when
        Customer customer = customerRepository.findById(id).orElseThrow(null);
        if(customer!= null){
            customer.setName(newNombre);
            Customer customerUpdate = customerRepository.save(customer);
            //then
            assertThat(customerUpdate.getName()).isEqualTo(newNombre);
        }
    }

    @Test
    @DisplayName("test findByEmail")
    void givenCustomer_ThenFindByEmail(){
        //given
        String email = customer1.getEmail();
        customerRepository.save(customer1);
        Customer findCustomer = customerRepository.findByEmail(email);
        //then
        assertThat(findCustomer.getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("test findByAddress")
    void givenCustomer_ThenFindByAdress(){
        //given
        String adress = customer1.getAddress();
        customerRepository.save(customer1);
        List<Customer> findCustomer = customerRepository.findByAddress(adress);
        //then
        assertThat(findCustomer.get(0).getAddress()).isEqualTo(adress);
    }

    @Test
    @DisplayName("test findByAddress")
    void givenCustomer_ThenfindByNameStartsWith(){
        //given
        String name = customer1.getName();
        customerRepository.save(customer1);
        List<Customer> findCustomer = customerRepository.findByNameStartsWith(name);
        //then
        assertThat(findCustomer.get(0).getName()).isEqualTo(name);
    }
}
