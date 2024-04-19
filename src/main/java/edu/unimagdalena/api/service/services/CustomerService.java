package edu.unimagdalena.api.service.services;

import java.util.List;

import edu.unimagdalena.api.model.dto.CustomerDTO;
import edu.unimagdalena.api.model.dto_save.CustomerToSaveDto;

public interface CustomerService {

    // CRUD

    CustomerDTO create(CustomerToSaveDto customerToSaveDto);

    CustomerDTO getCustomerById(Long customerId);

    CustomerDTO update(CustomerToSaveDto customerToSaveDto, Long customerId);

    void delete(Long customerId);

    // Others methods

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerByEmail(String email);

    List<CustomerDTO> getCustomersByAddress(String address);

    List<CustomerDTO> getCustomersByNameStartsWith(String productName);

}
