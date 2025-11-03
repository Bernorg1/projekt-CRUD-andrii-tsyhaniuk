package vistula.andriitsyhaniuk.office.employee.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vistula.andriitsyhaniuk.office.employee.dto.EmployeeRequestDto;
import vistula.andriitsyhaniuk.office.employee.dto.EmployeeResponseDto;
import vistula.andriitsyhaniuk.office.employee.entity.Employee;
import vistula.andriitsyhaniuk.office.employee.entity.EmployeeRole;
import vistula.andriitsyhaniuk.office.employee.entity.Role;
import vistula.andriitsyhaniuk.office.employee.repository.EmployeeRepository;
import vistula.andriitsyhaniuk.office.employee.repository.RoleRepository;
import vistula.andriitsyhaniuk.office.exception.DuplicateLoginException;
import vistula.andriitsyhaniuk.office.exception.EmployeeNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequest) {
        validatePassword(employeeRequest.getPassword());

        Optional<Employee> existing = employeeRepository.findByLogin(employeeRequest.getLogin());
        if (existing.isPresent()) {
            throw new DuplicateLoginException("Login already exists: " + employeeRequest.getLogin());
        }

        Set<EmployeeRole> employeeRoles = employeeRequest.getRoles();
        if (employeeRoles == null || employeeRoles.isEmpty()) {
            employeeRoles = Set.of(EmployeeRole.USER);
        }

        Set<Role> roles = employeeRoles.stream()
                .map(userRole -> roleRepository.findByName(userRole)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + userRole)))
                .collect(Collectors.toSet());

        Employee employee = modelMapper.map(employeeRequest, Employee.class);
        employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
        employee.setRoles(roles);
        employee.setCreatedAt(LocalDateTime.now());
        Employee savedEmployee = employeeRepository.save(employee);

        return modelMapper.map(savedEmployee, EmployeeResponseDto.class);
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeResponseDto getEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
        return modelMapper.map(employee, EmployeeResponseDto.class);
    }

    @Override
    public EmployeeResponseDto updateUser(Long id, EmployeeRequestDto employeeRequest) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

        String newLogin = employeeRequest.getLogin();
        if (newLogin != null && !newLogin.equals(employee.getLogin())) {
            Optional<Employee> byLogin = employeeRepository.findByLogin(newLogin);
            if (byLogin.isPresent()) {
                throw new DuplicateLoginException("Login already exists: " + newLogin);
            }
        }

        modelMapper.map(employeeRequest, employee);
        Employee savedEmployee = employeeRepository.save(employee);

        return modelMapper.map(savedEmployee, EmployeeResponseDto.class);
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    private void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password must not be empty");
        }
    }

    private EmployeeResponseDto mapToResponseDto(Employee employee) {
        EmployeeResponseDto dto = new EmployeeResponseDto();
        dto.setId(employee.getId());
        dto.setLogin(employee.getLogin());
        dto.setName(employee.getName());
        dto.setFirstName(employee.getFirstName());
        dto.setAge(employee.getAge());
        dto.setTitle(employee.getTitle());
        dto.setCreatedAt(employee.getCreatedAt());

        if (employee.getRoles() != null) {
            Set<EmployeeRole> roleNames = employee.getRoles().stream()
                    .map(Role::getName)
                    .collect(Collectors.toSet());
            dto.setRoles(roleNames);
        }

        return dto;
    }
}