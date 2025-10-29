package vistula.andriitsyhaniuk.office.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vistula.andriitsyhaniuk.office.employee.entity.EmployeeRole;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    private String login;
    private String password;
    private String name;
    private String firstName;
    private Set<EmployeeRole> roles;
}
