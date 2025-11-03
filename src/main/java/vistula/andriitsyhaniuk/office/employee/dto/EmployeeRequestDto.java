package vistula.andriitsyhaniuk.office.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vistula.andriitsyhaniuk.office.employee.entity.EmployeeRole;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto {
    private String login;
    private String name;
    private String firstName;
    private int age;
    private String title;
    private String password;
    private Set<EmployeeRole> roles;
}
