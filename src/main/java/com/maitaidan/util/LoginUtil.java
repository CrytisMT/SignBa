package com.maitaidan.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import static org.apache.commons.codec.digest.DigestUtils.sha256;

/**
 * Created by Crytis on 2016/3/14.
 * 22:57
 */
public class LoginUtil {

    private static final String token = "_#$$%mpq";

    public static byte[] encryptUsername(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return sha256(token + key);
    }

    public static String generateCookie(String input) {
        if (StringUtils.isBlank(input)) {
            return null;
        }
        byte[] bytes = Base64.encodeBase64(input.getBytes());
        return new String(bytes);
    }
}
