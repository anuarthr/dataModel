package com.data.tallermodelodatos.api;

import com.data.tallermodelodatos.dto.JwtResponse;
import com.data.tallermodelodatos.dto.LoginRequest;
import com.data.tallermodelodatos.dto.SignupRequest;
import com.data.tallermodelodatos.entities.Cliente;
import com.data.tallermodelodatos.entities.User;
import com.data.tallermodelodatos.repositories.ClienteRepository;
import com.data.tallermodelodatos.repositories.UserRepository;
import com.data.tallermodelodatos.security.jwt.JwtUtil;
import com.data.tallermodelodatos.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtUtil.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwtToken, "Bearer", userDetails.getUsername(), roles));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest sRequest) {
        Cliente cliente = new Cliente();
        cliente.setNombre(sRequest.nombre());
        cliente.setApellido(sRequest.apellido());
        cliente.setDireccion(sRequest.direccion());
        cliente.setTelefono(sRequest.telefono());
        cliente.setEmail(sRequest.email());
        cliente.setPassword(passwordEncoder.encode(sRequest.password()));
        Cliente newCliente = clienteRepository.save(cliente);

        User user = new User();
        user.setUsername(sRequest.email());
        user.setPassword(passwordEncoder.encode(sRequest.password()));
        user.setEmail(sRequest.email());
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(newCliente);
    }
}