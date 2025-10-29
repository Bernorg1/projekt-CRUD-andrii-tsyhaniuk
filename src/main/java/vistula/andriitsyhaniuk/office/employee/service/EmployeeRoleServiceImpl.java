package vistula.andriitsyhaniuk.office.employee.service;

import org.springframework.stereotype.Service;
import vistula.andriitsyhaniuk.office.employee.entity.Employee;
import vistula.andriitsyhaniuk.office.employee.entity.EmployeeRole;

import java.util.Set;

@Service
public class EmployeeRoleServiceImpl implements  EmployeeRoleService {
    @Override
    public Employee createEmployeeWithRoles(String login, String password, String fullName, Set<EmployeeRole> employeeRoles) {
        return null;
    }

    @Override
    public void addRoleToUser(Long userId, EmployeeRole userRole) {

    }

    @Override
    public void removeRoleFromUser(Long userId, EmployeeRole userRole) {

    }

    @Override
    public boolean userHasRole(Long userId, EmployeeRole userRole) {
        return false;
    }

    @Override
    public Set<EmployeeRole> getEmployeeRoles(Long userId) {
        return Set.of();
    }
}
