package vistula.andriitsyhaniuk.office.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vistula.andriitsyhaniuk.office.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


}
