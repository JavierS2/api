package edu.unimagdalena.api.service.implementations;

import java.util.List;
import java.util.Objects;

import edu.unimagdalena.api.model.dto_save.CustomerToSaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unimagdalena.api.model.entities.Customer;
import edu.unimagdalena.api.model.dto.CustomerDTO;
import edu.unimagdalena.api.exceptions.NotAbleToDeleteException;
import edu.unimagdalena.api.exceptions.ObjectNotFoundException;
import edu.unimagdalena.api.repository.CustomerRepository;
import edu.unimagdalena.api.service.services.CustomerService;
import edu.unimagdalena.api.model.mappers.CustomerMapper;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO create(CustomerToSaveDto customerToSave) {
        Customer customer = CustomerMapper.INSTANCE.customerToSaveDtoToCustomer(customerToSave);
        Customer customerSaved = customerRepository.save(customer);
        return CustomerMapper.INSTANCE.customerToCustomerDto(customerSaved);
    }

    @Override
    public CustomerDTO getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ObjectNotFoundException("Customer not found"));
        return CustomerMapper.INSTANCE.customerToCustomerDto(customer);
    }

    @Override
    public CustomerDTO update(CustomerToSaveDto customerToSaveDto, Long customerId) {
        Customer customerInDb = customerRepository.findById(customerId)
                .orElseThrow(() -> new ObjectNotFoundException("Customer not found"));
        customerInDb.setName(customerToSaveDto.name());
        customerInDb.setEmail(customerToSaveDto.email());
        customerInDb.setAddress(customerToSaveDto.address());
        return CustomerMapper.INSTANCE.customerToCustomerDto(customerRepository.save(customerInDb));
    }

    @Override
    public void delete(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotAbleToDeleteException("Customer not found, not able to delete"));
        customerRepository.delete(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(CustomerMapper.INSTANCE::customerToCustomerDto)
                .toList();
    }

    @Override
    public CustomerDTO getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email);
        if (Objects.isNull(customer))
            throw new ObjectNotFoundException("Customer not found");
        return CustomerMapper.INSTANCE.customerToCustomerDto(customer);
    }

    @Override
    public List<CustomerDTO> getCustomersByAddress(String address) {
        List<Customer> customers = customerRepository.findByAddress(address);
        return customers.stream()
                .map(CustomerMapper.INSTANCE::customerToCustomerDto)
                .toList();
    }

    @Override
    public List<CustomerDTO> getCustomersByNameStartsWith(String nombre) {
        List<Customer> customers = customerRepository.findByNameStartsWith(nombre);
        return customers.stream()
                .map(CustomerMapper.INSTANCE::customerToCustomerDto)
                .toList();
    }

}
