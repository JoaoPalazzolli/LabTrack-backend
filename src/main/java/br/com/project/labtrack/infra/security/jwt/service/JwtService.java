package br.com.project.labtrack.infra.security.jwt.service;

import br.com.project.labtrack.dto.TokenDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length}")
    private long expireLength;

    public String extrairEmail(String token){
        return extrairClaim(Claims::getSubject, token);
    }

    public TokenDTO criarToken(UserDetails userDetails){
        return criarToken(new HashMap<>(), userDetails);
    }

    public TokenDTO criarToken(Map<String, Object> extraClaims, UserDetails userDetails){
           return TokenDTO.builder()
                   .accessToken(gerarToken(extraClaims, userDetails, (expireLength * 3)))
                   .refreshToken(gerarToken(extraClaims, userDetails, (expireLength * 6)))
                   .build();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        var email = extrairEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpirated(token));
    }

    private boolean isTokenExpirated(String token){
        return extrairExpiracao(token).before(new Date());
    }

    private Date extrairExpiracao(String token){
        return extrairClaim(Claims::getExpiration, token);
    }

    private String gerarToken(Map<String, Object> extraClaims, UserDetails userDetails, Long tempoExpiracao){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tempoExpiracao))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private <T> T extrairClaim(Function<Claims, T> claimsResolver, String token){
        Claims claims = extrairTodosClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extrairTodosClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
