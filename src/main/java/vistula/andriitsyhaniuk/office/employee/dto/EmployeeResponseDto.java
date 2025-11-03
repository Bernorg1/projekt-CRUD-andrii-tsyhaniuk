package vistula.andriitsyhaniuk.office.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vistula.andriitsyhaniuk.office.employee.entity.EmployeeRole;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDto {
    private Long id;
    private String login;
    private String name;
    private String firstName;
    private int age;
    private String title;
    private Set<EmployeeRole> roles;
    private LocalDateTime createdAt;
}
