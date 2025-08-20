package com.qiubai.headline.test;

import com.qiubai.headline.util.JwtHelper;
import org.junit.Test;

public class TestJwtHelper {

    @Test
    public void testAllMethod() throws InterruptedException {
        String token = JwtHelper.createToken(1L);
        System.out.println(token);

        Long userId = JwtHelper.getUserId(token);
        System.out.println(userId);

        System.out.println(JwtHelper.isExpiration(token));

        Thread.sleep(6000);
        System.out.println(JwtHelper.isExpiration(token));

    }
}
