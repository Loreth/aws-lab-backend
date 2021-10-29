package pl.grupakpkpur.awslab.security;

import static org.apache.logging.log4j.util.Strings.isBlank;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
  private final JwtUtil jwtUtil;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    String authHeader = request.getHeader(AUTHORIZATION);
    String jwtToken = jwtUtil.parseJwtHeader(authHeader);

    if (isBlank(authHeader)) {
      chain.doFilter(request, response);
      return;
    }

    jwtUtil.validateToken(jwtToken);

    UsernamePasswordAuthenticationToken authentication = getAuthenticationToken(jwtToken);

    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(request, response);
  }

  private UsernamePasswordAuthenticationToken getAuthenticationToken(String jwtToken) {
    UserDetails userDetails =
        userDetailsService.loadUserByUsername(jwtUtil.getUsernameFromToken(jwtToken));

    return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword());
  }
}
