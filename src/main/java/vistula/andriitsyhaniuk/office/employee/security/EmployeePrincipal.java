package vistula.andriitsyhaniuk.office.employee.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import vistula.andriitsyhaniuk.office.employee.entity.Employee;

import java.util.Collection;

@Getter
@RequiredArgsConstructor
public class EmployeePrincipal implements UserDetails {

    private final Employee employee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return employee.getRoles().stream()
                .map(role -> (GrantedAuthority) () -> "ROLE_" + role.getName())
                .toList();
    }

    @Override
    public String getPassword() {
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        return employee.getLogin();
    }


    public Long getId() {
        return employee.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Employee getUser() {
        return employee;
    }
}