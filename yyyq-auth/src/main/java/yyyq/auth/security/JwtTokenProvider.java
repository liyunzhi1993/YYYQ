package yyyq.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import yyyq.common.exception.CustomException;
import yyyq.auth.service.UserService;
import yyyq.common.constants.SecurityConstants;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:YYYQ-SK}")
    private String secretKey;

    /**
     * 1 days
     */
    @Value("${security.jwt.token.expire-length:86400000}")
    private long validityInMilliseconds = 86400000 ;

    @Autowired
    private UserService userService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String userId, String userName,long validityInMilliseconds) {
        if (validityInMilliseconds == 0) {
            validityInMilliseconds=this.validityInMilliseconds;
        }
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("auth", userName);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        yyyq.auth.entity.User user=userService.getUserByUserId(Long.valueOf(getUserId(token)));
        return new UsernamePasswordAuthenticationToken(user,user.password, null);
    }

    public long getUserId(String token) {
        return Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());
    }

    public String getUserName(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("auth").toString();
    }

    public String resolveToken(HttpServletRequest req) {
        return req.getHeader(SecurityConstants.HEADER_STRING);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException("Token过期或不可用");
        }
    }
}
