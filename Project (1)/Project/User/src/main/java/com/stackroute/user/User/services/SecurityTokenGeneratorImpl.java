package com.stackroute.user.User.services;

import com.stackroute.user.User.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator{

    @Override
    public Map<String, String> generateToken(User user) {
        Map<String,String> map = new HashMap<>();
        long currentTimeInMilli = System.currentTimeMillis();
        String jwtToken = Jwts.builder()
                .setSubject(user.getEmailId())
                .setIssuedAt(new Date(currentTimeInMilli))
                .signWith(SignatureAlgorithm.HS512,"mySecretKey")
//                .setExpiration(new Date(currentTimeInMilli+600000))       //token will be valid for 10 minutes.
                .compact();
        map.put("Token",jwtToken);
        map.put("Message","User successfully logged in");
        map.put("EmailId", user.getEmailId());
        map.put("Role", user.getRole());

        System.out.println(map);
        return map;
    }

}
