package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {
    public static final SecurityUtil instance = new SecurityUtil();

    public static SecurityUtil getInstance() {
        return instance;
    }

    /**
     * 获取字符串对应MD5
     *
     * @param str 要处理的字符串
     * @return 返回MD5
     */
    public String getMD5(String str) {
        StringBuilder result = new StringBuilder();
        try {
            byte[] s = MessageDigest.getInstance("md5").digest(str.getBytes(StandardCharsets.UTF_8));
            for (byte b : s) {
                result.append(Integer.toHexString((0x000000FF & b) | 0xFFFFFF00).substring(6));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * 获取加盐MD5(安全性更高，迭代五次)
     *
     * @param str  要处理的字符串
     * @param salt 要加的盐
     * @return 返回加盐后的MD5
     */
    public String getSaltyMD5(String str, String salt) {
        String md5 = str;
        for (int i = 0; i < 5; i++) {
            md5 = getMD5(md5 + salt);
        }
        return md5;
    }
}