package adilsonarc.portfolio.blog.auth;

import adilsonarc.portfolio.blog.config.JwtService;
import adilsonarc.portfolio.blog.person.Person;
import adilsonarc.portfolio.blog.person.PersonRepository;
import adilsonarc.portfolio.blog.person.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var person = Person.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        personRepository.save(person);
        String jwtToken = jwtService.generateToken(person);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));
        Person person = personRepository.findPersonByEmail(request.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(person);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
