package aptech.dating.security.admin;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import aptech.dating.DTO.AdminDTO;
import aptech.dating.service.AdminDetailsImpl;
import aptech.dating.service.AdminService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private String jwtSecret = "bezKoderSecretKeybezKoderSecretKeybezKoderSecretKeybezKoderSecretKey";

    @Value("${bezkoder.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Autowired
    private AdminService adminService;

    public long getExpirationMs() {
	return jwtExpirationMs;
    }

    public String generateToken(Authentication authentication) {
	AdminDetailsImpl userPrincipal = (AdminDetailsImpl) authentication.getPrincipal();
	AdminDTO amdin = adminService.getAdmin(userPrincipal.getId());
	return Jwts.builder().setClaims(claimsMap(amdin)).setSubject(userPrincipal.getUsername())
		.setAudience(amdin.getRole()).setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + this.getExpirationMs()))
		// .signWith(SignatureAlgorithm.HS512, this.getSecret().getBytes(UTF_8))
		.signWith(this.key()).compact();
    }

    private Key key() {
	return Keys.hmacShaKeyFor(this.jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String getUsernameFromToken(String token) {
	Claims claims = Jwts.parser().setSigningKey(this.key()).parseClaimsJws(token).getBody();
	return claims.getSubject();
    }

    public boolean isTokenExpired(String token) {
	Date expirationDate = Jwts.parser().setSigningKey(this.key()).parseClaimsJws(token).getBody().getExpiration();
	return expirationDate.before(new Date());
    }

    private boolean isTokenDateExpired(Date expirationDate) {
	return expirationDate.before(new Date());
    }

    private boolean isValidClaim(Claims claims) {
	return claims.containsKey("user");
    }

    private Map<String, Object> claimsMap(AdminDTO admin) {
	Map<String, Object> map = new HashMap<>();
	map.put("roles", admin.getRole());
	return map;
    }

    public String getUserNameFromJwtToken(String token) {
	return Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes(Charset.forName("UTF-8"))).build()
		.parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
	try {
	    Jws<Claims> claimsJws = Jwts.parser().setSigningKey(this.key()).parseClaimsJws(authToken);
	    Claims claims = claimsJws.getBody();
	    return true;
	} catch (MalformedJwtException e) {
	    logger.error("Invalid JWT token: {}", e.getMessage());
	} catch (ExpiredJwtException e) {
	    logger.error("JWT token is expired: {}", e.getMessage());
	} catch (UnsupportedJwtException e) {
	    logger.error("JWT token is unsupported: {}", e.getMessage());
	} catch (IllegalArgumentException e) {
	    logger.error("JWT claims string is empty: {}", e.getMessage());
	}
	return false;
    }
}
