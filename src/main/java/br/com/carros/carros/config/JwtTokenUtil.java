package br.com.carros.carros.config;
import java.util.Date;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import br.com.carros.carros.service.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.*;

@Component
public class JwtTokenUtil {
	
     
	    @Value("${carros.app.jwt.secret}")
	    private String SECRET_KEY;
	     
	    public String generateJwtToken(Authentication authentication) {

			UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

			return Jwts.builder()
					.setSubject((userPrincipal.getEmail()))
					.setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
					.compact();
		}
	    
	    public String getUserNameFromJwtToken(String token) {
			return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
		}
	    
	    public boolean validateJwtToken(String authToken) {
			try {
				Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
				return true;
			} catch (SignatureException e) {
//				logger.error("Invalid JWT signature: {}", e.getMessage());
			} catch (MalformedJwtException e) {
//				logger.error("Invalid JWT token: {}", e.getMessage());
			} catch (ExpiredJwtException e) {
//				logger.error("JWT token is expired: {}", e.getMessage());
			} catch (UnsupportedJwtException e) {
//				logger.error("JWT token is unsupported: {}", e.getMessage());
			} catch (IllegalArgumentException e) {
//				logger.error("JWT claims string is empty: {}", e.getMessage());
			}

			return false;
		}
}
