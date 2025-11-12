package com.example.demo.controlador;

import com.example.demo.entidades.Role;
import com.example.demo.entidades.UsuarioVet;
import com.example.demo.repositorio.RepositorioRoles;
import com.example.demo.repositorio.RepositorioUsuarioVet;
import com.example.demo.security.JwtUtil;
// Avoid Lombok constructor annotation to keep IDE/editor compatibility
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

  private final AuthenticationManager authManager;
  private final JwtUtil jwtUtil;
  private final RepositorioUsuarioVet userRepo;
  private final RepositorioRoles roleRepo;
  private final PasswordEncoder encoder;

  // Explicit constructor so we don't depend on Lombok-generated constructor
  public AuthController(AuthenticationManager authManager,
                        JwtUtil jwtUtil,
                        RepositorioUsuarioVet userRepo,
                        RepositorioRoles roleRepo,
                        PasswordEncoder encoder) {
    this.authManager = authManager;
    this.jwtUtil = jwtUtil;
    this.userRepo = userRepo;
    this.roleRepo = roleRepo;
    this.encoder = encoder;
  }

  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody SignupRequest req){
    if (userRepo.existsByUsername(req.getUsername())) {
      return ResponseEntity.badRequest().body("Usuario ya existe");
    }

  String roleName = (req.getRole() == null) ? "usuario" : req.getRole().toLowerCase();
  Role role = roleRepo.findByName(roleName).orElseGet(() -> roleRepo.save(new Role(roleName)));
    UsuarioVet u = new UsuarioVet();
    u.setUsername(req.getUsername());
    u.setPassword(encoder.encode(req.getPassword()));
    List<Role> roles = new ArrayList<>();
    roles.add(role);
    u.setRoles(roles);
    userRepo.save(u);
    return ResponseEntity.ok("creado");
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req){
    Authentication auth = authManager.authenticate(
      new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
    );
    
    // Get user details from database
    UsuarioVet usuarioVet = userRepo.findByUsername(req.getUsername())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    
    // Determine role name (use first role)
    String roleName = usuarioVet.getRoles().isEmpty() ? "usuario" : usuarioVet.getRoles().get(0).getName();
    
    // Generate JWT token
    String token = jwtUtil.generate(auth);
    
    // Create response with token, role, and id
    LoginResponse response = new LoginResponse(token, roleName, usuarioVet.getId());
    
    return ResponseEntity.ok(response);
  }

  public static class LoginRequest {
    private String username;
    private String password;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
  }

  public static class LoginResponse {
    private String token;
    private String role;
    private Long id;

    public LoginResponse(String token, String role, Long id) {
      this.token = token;
      this.role = role;
      this.id = id;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
  }

  public static class SignupRequest {
    private String username;
    private String password;
    private String role;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
  }
}
