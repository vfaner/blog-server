package com.rgh.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author: rgh
 * @description: JJWT加密工具类
 */
@Data
@Component
@ConfigurationProperties(prefix = "rgh.jwt")
public class JJwtUtil {
    private String header;
    private long expire;
    private String secret;

    // 创建token密钥的key,并且使用 HMAC-SHA-256 加密算法
    private SecretKey key;

    public void setSecret(String secret) {
        this.secret = secret;
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        if (keyBytes.length < 32) {
            byte[] padded = new byte[32];
            System.arraycopy(keyBytes, 0, padded, 0, keyBytes.length);
            keyBytes = padded;
        }
        // 用 jjwt 的 Keys 工具类创建密钥，兼容 JDK 17
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 生成Token
     * @param username 用户名
     * @return Token
     */
    public String generateToken(String username){
        //过期时间
        Date nowDate=new Date();
        Date expireDate = new Date(nowDate.getTime() + expire);
        JwtBuilder jwtBuilder = Jwts.builder();

        // 设置签发时间
        jwtBuilder.issuedAt(nowDate);

        // 设置签名算法及密钥
        jwtBuilder.signWith(key);

        // 设置过期时间
        jwtBuilder.expiration(expireDate);

        // 设置主题
        jwtBuilder.subject(username);

        return jwtBuilder.compact();
    }

    public Claims extractClaims(String token){
        try {
            Jws<Claims> claimsJws = Jwts.parser().
                    verifyWith(key).
                    build().
                    parseSignedClaims(token);
            // 获取载荷的一些数据信息
            return claimsJws.getPayload(); // payload 为一个map对象
        } catch (Exception se) {
            throw new JwtException("token无效");
        }
    }

    public String getUserName(String token) {
        return extractClaims(token).getSubject();
    }

    /**
     * 解析判断是否过期
     * @return 是否过期
     */
    public boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }

    /**
     * 校验token是否正确
     * @param token token
     * @param username  用户名
     * @return 是否正确
     */
    public boolean validateToken(String token,String username){
        return !isTokenExpired(token) && username.equals(getUserName(token));
    }
}
