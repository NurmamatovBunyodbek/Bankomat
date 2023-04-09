package uz.pdp.bankomat.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.pdp.bankomat.entity.Role;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {

    private static final long expireDate = 1000 * 60 * 60 * 24;
    private static final String secretKey = "bumaxfiysozhechkimbilmasin";

    public String generateToken(String username, Set<Role> roles) {
        Date date = new Date(System.currentTimeMillis() + expireDate);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(date)
                .claim("roles", roles)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        return token;
    }

    public  String getUserNameFromToken(String token){
        try {
            String email = Jwts.
                    parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return email;

        }catch (Exception e){
             return null;
        }
    }

}
