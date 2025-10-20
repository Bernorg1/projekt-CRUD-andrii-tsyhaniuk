package vistula.andriitsyhaniuk.office.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vistula.andriitsyhaniuk.office.dto.EmployeeRequestDto;
import vistula.andriitsyhaniuk.office.dto.EmployeeResponseDto;
import vistula.andriitsyhaniuk.office.entity.Employee;
import vistula.andriitsyhaniuk.office.service.EmployeeService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping()
    public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody EmployeeRequestDto employeeRequest) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok((employeeService.getAllEmployees()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable long id) {
        return ResponseEntity.ok((employeeService.getEmployeeById(id)));
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
