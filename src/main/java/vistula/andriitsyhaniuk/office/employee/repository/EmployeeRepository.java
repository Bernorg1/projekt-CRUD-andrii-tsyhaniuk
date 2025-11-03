package vistula.andriitsyhaniuk.office.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vistula.andriitsyhaniuk.office.employee.entity.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByLogin(String login);
    boolean existsByLogin(String login);

    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.roles WHERE e.login = :login")
    Optional<Employee> findByLoginWithRoles(@Param("login") String login);
}
