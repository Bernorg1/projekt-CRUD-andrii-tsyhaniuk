package vistula.andriitsyhaniuk.office.employee.service;

import vistula.andriitsyhaniuk.office.employee.dto.EmployeeRequestDto;
import vistula.andriitsyhaniuk.office.employee.dto.EmployeeResponseDto;
import vistula.andriitsyhaniuk.office.employee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequest);

    void deleteEmployee(long id);

    EmployeeResponseDto getEmployeeById(long id);

    EmployeeResponseDto updateUser(Long id, EmployeeRequestDto employeeRequest);

    List<Employee> getAllEmployees();
}
