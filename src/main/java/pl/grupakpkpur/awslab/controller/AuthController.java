package pl.grupakpkpur.awslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.grupakpkpur.awslab.model.administration.AuthReponse;
import pl.grupakpkpur.awslab.model.administration.AuthRequest;
import pl.grupakpkpur.awslab.model.administration.User;
import pl.grupakpkpur.awslab.security.JwtUtil;

@RestController
@RequiredArgsConstructor
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  @PostMapping("${rest.mapping.login}")
  public ResponseEntity<AuthReponse> login(AuthRequest request) {
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(request.username(), request.password());
    Authentication authentication = authenticationManager.authenticate(authenticationToken);
    User user = (User) authentication.getPrincipal();

    return ResponseEntity.ok().body(new AuthReponse(jwtUtil.generateToken(user)));
  }
}
