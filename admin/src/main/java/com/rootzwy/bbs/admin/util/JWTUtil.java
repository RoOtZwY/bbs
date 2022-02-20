package com.rootzwy.bbs.admin.util;

import cn.hutool.core.bean.BeanUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author zwy
 * @date 2022/1/31
 */
public class JWTUtil {

    private final String KEY;

    private final InnerJWTHandler handler = new InnerJWTHandler();

    private JWTUtil(String key) {
        this.KEY = key;
    }

    public static InnerJWTHandler handler(String privateKey) {
        return new JWTUtil(privateKey).handler;
    }

    public class InnerJWTHandler {

        public String createTokenByObject(Object object, int expireDate) {
            return createToken(BeanUtil.beanToMap(object), expireDate);
        }

        public String createToken(Map<String, Object> privatePayLoad, int expireDate) {
            JWTCreator.Builder tokenBuilder = JWT.create();
            // Header
            // 此处使用 JWT 的默认头部：{"alg": "HS256","typ": "JWT"}
            // 使用 Builder.withHeader() 设置头部信息

            // payLoad
            privatePayLoad.forEach((key, value) -> {
                Class<?> valueClass = value.getClass();
                if (valueClass.equals(Boolean.class)) {
                    tokenBuilder.withClaim(key, (Boolean) value);
                } else if (valueClass.equals(Integer.class)) {
                    tokenBuilder.withClaim(key, (Integer) value);
                } else if (valueClass.equals(Long.class)) {
                    tokenBuilder.withClaim(key, (Long) value);
                } else if (valueClass.equals(Double.class)) {
                    tokenBuilder.withClaim(key, (Double) value);
                } else if (valueClass.equals(String.class)) {
                    tokenBuilder.withClaim(key, (String) value);
                } else if (valueClass.equals(Date.class)) {
                    tokenBuilder.withClaim(key, (Date) value);
                } else {
                    throw new UnsupportedOperationException("暂不支持 " + valueClass + " 类型字段生成 token");
                }
            });

            // 过期时间和签名加密
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, expireDate);
            return tokenBuilder
                    .withExpiresAt(calendar.getTime())
                    .sign(Algorithm.HMAC256(KEY));
        }

        public DecodedJWT verifyAndGetDecodedToken(String token) {
            return JWT.require(Algorithm.HMAC256(KEY)).build().verify(token);
        }

    }

}
