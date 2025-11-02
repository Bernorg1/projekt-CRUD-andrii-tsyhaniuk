package vistula.andriitsyhaniuk.office.employee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vistula.andriitsyhaniuk.office.employee.dto.EmployeeRequestDto;
import vistula.andriitsyhaniuk.office.employee.dto.EmployeeResponseDto;
import vistula.andriitsyhaniuk.office.employee.entity.Employee;
import vistula.andriitsyhaniuk.office.employee.entity.EmployeeRole;
import vistula.andriitsyhaniuk.office.employee.service.EmployeeRoleService;
import vistula.andriitsyhaniuk.office.employee.service.EmployeeService;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeRoleService employeeRoleService;

    @PostMapping()
    public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody EmployeeRequestDto employeeRequest) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable long id) {
        return ResponseEntity.ok((employeeService.getEmployeeById(id)));
    }
    @GetMapping("/{userId}/roles")
    public ResponseEntity<Set<EmployeeRole>> getUserRoles(@PathVariable Long userId) {
        return ResponseEntity.ok(employeeRoleService.getEmployeeRoles(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable long id, @RequestBody EmployeeRequestDto employeeRequest) {
        return ResponseEntity.ok(employeeService.updateUser(id, employeeRequest));
    }
}
