package com.rootzwy.bbs.admin.util;

import cn.hutool.crypto.SecureUtil;

import java.nio.charset.StandardCharsets;

/**
 * @author zwy
 * @date 2022/1/31
 */
public class MD5Util {

    private MD5Util() {

    }

    public static String md5(String string, String salt) {
        return SecureUtil.md5().digestHex(string + salt, StandardCharsets.UTF_8);
    }

}
