package group.bridge.web.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Calendar;
import java.util.Date;

public class JwtBuilder {
    public static String buildToken(String userName){
        Calendar calendar=Calendar.getInstance();
        //过期时间
        Calendar expire=Calendar.getInstance();
        expire.add(Calendar.DATE,StaticData.TokenExpiredDay);

        SecretKey secretKey=buildSecret();
        io.jsonwebtoken.JwtBuilder jwt=Jwts.builder().setHeaderParam("type","JWT")
                .setHeaderParam("alg","HS256")
                .setIssuedAt(calendar.getTime())
                .claim("name",userName)
                .setIssuer("group4")
                .setExpiration(expire.getTime())
                .signWith(SignatureAlgorithm.HS256,secretKey);
        return jwt.compact();
    }
    public static Claims getClaims(String token){
        SecretKey secretKey=buildSecret();
        Claims claims=null;
        try{
            Jws<Claims> jws=Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            claims=jws.getBody();
        }
        catch (Exception ex){
            claims=null;
            System.out.println("token illegal.");
        }

        return claims;
    }
    public static SecretKey buildSecret(){
        byte[] encodedKey = Base64.decodeBase64(StaticData.SecretKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
}
