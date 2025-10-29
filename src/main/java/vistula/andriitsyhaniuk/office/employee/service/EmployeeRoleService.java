package vistula.andriitsyhaniuk.office.employee.service;

import vistula.andriitsyhaniuk.office.employee.entity.Employee;
import vistula.andriitsyhaniuk.office.employee.entity.EmployeeRole;

import java.util.Set;

public interface EmployeeRoleService {
    Employee createEmployeeWithRoles(String login, String password, String fullName, Set<EmployeeRole> employeeRoles);

    void addRoleToUser(Long userId, EmployeeRole userRole);

    void removeRoleFromUser(Long userId, EmployeeRole userRole);

    boolean userHasRole(Long userId, EmployeeRole userRole);

    Set<EmployeeRole> getEmployeeRoles(Long userId);
}
