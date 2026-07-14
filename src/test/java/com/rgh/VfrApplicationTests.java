package com.rgh;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SpringBootTest
class VfrApplicationTests {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Test
    @Transactional
    void contextLoads() {
        String s = UUID.randomUUID().toString();
        System.out.println(s);
    }
    @Test
    @Transactional
    public void ce(){
        String encode = bCryptPasswordEncoder.encode("123456");
        boolean matches = bCryptPasswordEncoder.matches( "123456",encode);
        System.out.println(matches);
        System.out.println(encode);
    }

}
