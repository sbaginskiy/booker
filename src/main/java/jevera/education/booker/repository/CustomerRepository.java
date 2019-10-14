package jevera.education.booker.repository;

import jevera.education.booker.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Optional<Customer> findByLogin(String login);

}
