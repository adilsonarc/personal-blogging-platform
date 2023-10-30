package adilsonarc.portfolio.blog.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JtwAuthenticationFilter extends OncePerRequestFilter {

    public static final String JWT_TOKEN_BEARER_PREFIX = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final Optional<String> jtwToken = Optional
                .ofNullable(request.getHeader(HEADER_AUTHORIZATION))
                .filter(authHeader -> authHeader.startsWith(JWT_TOKEN_BEARER_PREFIX))
                .map(authHeader -> authHeader.substring(JWT_TOKEN_BEARER_PREFIX.length()));
        final Optional<String> email = jtwToken.map(token -> jwtService.extractEmail(token));
        final boolean notAuthenticated = SecurityContextHolder.getContext().getAuthentication() == null;

        if (notAuthenticated) {
            email.map(userDetailsService::loadUserByUsername)
                    .filter(userDetails -> jwtService.isTokenValid(jtwToken.get(), userDetails))
                    .map(userDetails -> new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()))
                    .ifPresent(authToken -> {
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    });
        }
        filterChain.doFilter(request, response);
    }
}
