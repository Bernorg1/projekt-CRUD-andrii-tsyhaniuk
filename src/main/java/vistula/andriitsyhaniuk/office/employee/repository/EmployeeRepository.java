package vistula.andriitsyhaniuk.office.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vistula.andriitsyhaniuk.office.employee.entity.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByLogin(String login);
    boolean existsByLogin(String login);
}
