package vistula.andriitsyhaniuk.office.employee.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vistula.andriitsyhaniuk.office.employee.repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class EmployeeUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var employee = employeeRepository.findByLoginWithRoles(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new EmployeePrincipal(employee);
    }
}

