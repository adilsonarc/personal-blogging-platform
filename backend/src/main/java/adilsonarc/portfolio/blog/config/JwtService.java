package adilsonarc.portfolio.blog.config;

import adilsonarc.portfolio.blog.person.Person;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "TgiHTQjqSL77bUQqgMn1QiyWlCYhUVN4UkDY9jHEMJ8qKzfgQVyPi9cLWDCeL3AL";
    public static final int EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(Person person) {
        return generateToken(Map.of(), person);
    }

    public String generateToken(Map<String, Object> extractClaims,
                                Person person) {
        var issuedTime = new Date();
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(person.getEmail())
                .setIssuedAt(issuedTime)
                .setExpiration(new Date(issuedTime.getTime() + EXPIRATION_TIME))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token,
                                UserDetails userDetails) {
        final Date expirationDate = extractExpirationDate(token);
        final String email = extractEmail(token);

        final boolean correctEmail = StringUtils.equals(userDetails.getUsername(), email);
        final boolean notExpiredToken = DateUtils.truncatedCompareTo(new Date(), expirationDate, Calendar.MINUTE) <= 0;

        return correctEmail && notExpiredToken;

    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
