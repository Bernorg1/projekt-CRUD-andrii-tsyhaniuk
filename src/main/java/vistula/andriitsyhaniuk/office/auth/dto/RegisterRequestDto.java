package vistula.andriitsyhaniuk.office.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("roles")
    private Set<EmployeeRole> roles;
}
