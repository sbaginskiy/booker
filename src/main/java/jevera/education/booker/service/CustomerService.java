package jevera.education.booker.service;

import static java.nio.charset.StandardCharsets.UTF_8;

import jevera.education.booker.domain.Customer;
import jevera.education.booker.domain.dto.CustomerDto;
import jevera.education.booker.exceptions.EntityAlreadyExsist;
import jevera.education.booker.exceptions.UncorrectGrant;
import jevera.education.booker.repository.CustomerRepository;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;


    public Customer save(Customer employee) {
        return customerRepository.saveAndFlush(employee);
    }

    public Customer register(CustomerDto customerDto) {
        if (isEmployeeExist(customerDto.getLogin())) {
            throw new EntityAlreadyExsist();
        }
        Customer employee = modelMapper.map(customerDto, Customer.class);
        employee.setPasswordHash(encryptPassword(customerDto.getPassword()));
        return save(employee);
    }

    public Customer login(String login, String password) {
        return customerRepository.findByLogin(login)
                .filter(customer -> checkPassword(password, customer))
                .orElseThrow(UncorrectGrant::new);
    }

    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }


    //---------------------------------------------------------

    private boolean isEmployeeExist(String customer) {
        return customerRepository.findByLogin(customer).isPresent();
    }

    private boolean checkPassword(String password, Customer customer) {
        String encryptPassword = encryptPassword(password);
        return encryptPassword.equals(customer.getPasswordHash());
    }

    @SneakyThrows
    private static String encryptPassword(String password) {
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes(UTF_8));
            return new BigInteger(1, crypt.digest()).toString(16);
        } catch (NoSuchAlgorithmException exception) {
        }
        return null;
    }
}
