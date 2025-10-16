package vistula.andriitsyhaniuk.office.service;

import vistula.andriitsyhaniuk.office.dto.EmployeeRequestDto;
import vistula.andriitsyhaniuk.office.dto.EmployeeResponseDto;

public interface EmployeeService {
    EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequest);

    void deleteEmployee(long id);

    EmployeeResponseDto getEmployeeById(long id);

    EmployeeResponseDto updateUser(Long id, EmployeeRequestDto employeeRequest);

}
