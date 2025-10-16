package vistula.andriitsyhaniuk.office.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto {
    private String name;
    private String firstName;
    private int age;
    private String title;
}
