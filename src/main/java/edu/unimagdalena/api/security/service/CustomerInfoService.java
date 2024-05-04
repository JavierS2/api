package edu.unimagdalena.api.security.service;

//public class  {

import edu.unimagdalena.api.model.dto_save.CustomerToSaveDto;
import edu.unimagdalena.api.model.entities.Customer;
import edu.unimagdalena.api.model.mappers.CustomerMapper;
import edu.unimagdalena.api.repository.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomerInfoService implements UserDetailsService {
    private final CustomerRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerInfoService(CustomerRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> userDetail = Optional.ofNullable(userRepository.findByEmail(email));
        return userDetail.map(CustomerInfoDetail::new)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    public CustomerToSaveDto addUser(CustomerToSaveDto userInfo) {
        Customer user = Customer.builder()
                .name(userInfo.name())
                .password(passwordEncoder.encode(userInfo.password()))
                .email(userInfo.email())
                .address(userInfo.address())
                .roles(userInfo.roles())
                .build();
        user = userRepository.save(user);
        return CustomerMapper.INSTANCE.customerToCustomerToSaveDto(user);

    }
}