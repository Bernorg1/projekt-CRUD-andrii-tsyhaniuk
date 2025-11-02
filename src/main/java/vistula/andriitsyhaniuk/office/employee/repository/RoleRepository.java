package vistula.andriitsyhaniuk.office.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vistula.andriitsyhaniuk.office.employee.entity.EmployeeRole;
import vistula.andriitsyhaniuk.office.employee.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(EmployeeRole name);
}

