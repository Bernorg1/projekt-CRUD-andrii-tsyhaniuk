package vistula.andriitsyhaniuk.office.service;

import vistula.andriitsyhaniuk.office.dto.EmployeeRequestDto;
import vistula.andriitsyhaniuk.office.dto.EmployeeResponseDto;
import vistula.andriitsyhaniuk.office.entity.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequest);

    void deleteEmployee(long id);

    EmployeeResponseDto getEmployeeById(long id);

    EmployeeResponseDto updateUser(Long id, EmployeeRequestDto employeeRequest);

    List<Employee> getAllEmployees();
}
