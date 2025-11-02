package vistula.andriitsyhaniuk.office.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vistula.andriitsyhaniuk.office.auth.dto.LoginRequestDto;
import vistula.andriitsyhaniuk.office.auth.dto.LoginResponseDto;
import vistula.andriitsyhaniuk.office.auth.dto.RegisterRequestDto;
import vistula.andriitsyhaniuk.office.config.jwt.JwtProvider;
import vistula.andriitsyhaniuk.office.employee.entity.Employee;
import vistula.andriitsyhaniuk.office.employee.entity.EmployeeRole;
import vistula.andriitsyhaniuk.office.employee.entity.Role;
import vistula.andriitsyhaniuk.office.employee.repository.EmployeeRepository;
import vistula.andriitsyhaniuk.office.employee.repository.RoleRepository;
import vistula.andriitsyhaniuk.office.employee.security.EmployeePrincipal;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public LoginResponseDto register(RegisterRequestDto request) {
        if (employeeRepository.existsByLogin(request.getLogin())) {
            throw new RuntimeException("Login already exists");
        }

        Set<EmployeeRole> employeeRoles = request.getRoles();
        if (employeeRoles == null || employeeRoles.isEmpty()) {
            employeeRoles = Set.of(EmployeeRole.USER);
        }

        Set<Role> roles = employeeRoles.stream()
                .map(userRole -> roleRepository.findByName(userRole)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + userRole)))
                .collect(Collectors.toSet());

        var user = Employee.builder()
                .name(request.getName())
                .firstName(request.getFirstName())
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .createdAt(LocalDateTime.now())
                .build();
        employeeRepository.save(user);

        var jwtToken = jwtProvider.generateToken(new EmployeePrincipal(user));
        return LoginResponseDto.builder().accessToken(jwtToken).build();
    }

    public LoginResponseDto login(LoginRequestDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        var user = employeeRepository.findByLogin(request.getLogin())
                .orElseThrow(() -> new UsernameNotFoundException(request.getLogin()));
        var jwtToken = jwtProvider.generateToken(new EmployeePrincipal(user));
        return LoginResponseDto.builder().accessToken(jwtToken).build();
    }

    public LoginResponseDto refreshToken(String refreshToken) {
        String login;
        try {
            login = jwtProvider.extractUsername(refreshToken);
        } catch (Exception e) {
            throw new RuntimeException("Invalid refresh token");
        }

        var user = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!jwtProvider.validateToken(refreshToken, new EmployeePrincipal(user))) {
            throw new RuntimeException("Invalid refresh token");
        }

        String newAccessToken = jwtProvider.generateToken(new EmployeePrincipal(user));

        return LoginResponseDto.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
