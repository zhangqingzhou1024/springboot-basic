package com.liziyuan.hope.common.utils.http;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 加减密工具类
 *
 * @author zhangqingzhou
 */
public class EncryptUtils {
    protected static final Log LOG = LogFactory.getLog(EncryptUtils.class);
    private static final String ENC = "utf-8";

    public EncryptUtils() {
    }

    public static String sha256HMACEncode(String params, String secret) {
        String result = "";

        try {
            Mac sha256HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256HMAC.init(secretKey);
            byte[] sha256HMACBytes = sha256HMAC.doFinal(params.getBytes());
            String hash = Base64.encodeBase64String(sha256HMACBytes);
            if (LOG.isDebugEnabled()) {
                LOG.debug("sha256HMAC encode = " + hash);
            }

            return hash;
        } catch (Exception var7) {
            LOG.error("sha256HMACEncode failed.", var7);
            return result;
        }
    }

    public static String generateSignature(String params, String appSecret) {
        String result = md5(sha256HMACEncode(params, appSecret)).substring(5, 15);
        if (LOG.isDebugEnabled()) {
            LOG.debug("generateSignature result = " + result);
        }

        return result;
    }

    public static String md5s(String plainText) {
        String str = "";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();
            StringBuffer buf = new StringBuffer("");

            for (int offset = 0; offset < b.length; ++offset) {
                int i = b[offset];
                if (i < 0) {
                    i += 256;
                }

                if (i < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(i));
            }

            str = buf.toString();
            str = buf.toString();
        } catch (NoSuchAlgorithmException var7) {
        }

        return str;
    }

    public static String md5(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] e = md.digest(value.getBytes());
            return byteToHexString(e);
        } catch (NoSuchAlgorithmException var3) {
            return null;
        }
    }

    public static String byteToHexString(byte[] salt) {
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < salt.length; ++i) {
            String hex = Integer.toHexString(salt[i] & 255);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }

            hexString.append(hex.toLowerCase());
        }

        return hexString.toString();
    }

    public static String md5new(String pwd) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] bs = digest.digest(pwd.getBytes());
            String hexString = "";
            byte[] var4 = bs;
            int var5 = bs.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                byte b = var4[var6];
                int temp = b & 255;
                if (temp < 16 && temp >= 0) {
                    hexString = hexString + "0" + Integer.toHexString(temp);
                } else {
                    hexString = hexString + Integer.toHexString(temp);
                }
            }

            LOG.info("md5 = {}" + hexString);
            return hexString;
        } catch (NoSuchAlgorithmException var9) {
            LOG.info("md5 = {}", var9);
            var9.printStackTrace();
            return "";
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String mapToFormatString(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        String result = "";
        if (map.size() > 0) {
            Iterator var3 = map.entrySet().iterator();

            while (var3.hasNext()) {
                Entry<String, Object> entry = (Entry) var3.next();
                sb.append((String) entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append("&");
            }

            result = sb.replace(sb.length() - 1, sb.length(), "").toString();
        }

        try {
            result = URLEncoder.encode(result, "utf-8").replace("%3D", "=").replace("%26", "&");
        } catch (UnsupportedEncodingException var5) {
            var5.printStackTrace();
            LOG.warn("Encrept Encode Error,UnsupportedEncodingException e = {}", var5);
            return "";
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("mapToFormatString result = " + result);
        }

        return result;
    }

    public static String mapToFormatString(Map<String, Object> map, boolean fixed) {
        String result = "";
        if (!fixed) {
            result = mapToFormatString(map);
        } else {
            ArrayList ss = new ArrayList();

            try {
                Iterator var4 = map.entrySet().iterator();

                while (var4.hasNext()) {
                    Entry<String, Object> entry = (Entry) var4.next();
                    String key = (String) entry.getKey();
                    String encodedValue = URLEncoder.encode(String.valueOf(entry.getValue()), "utf-8");
                    ss.add(key + "=" + encodedValue);
                    System.out.println(String.format("Key: %s Value: %s", key, encodedValue));
                }
            } catch (UnsupportedEncodingException var8) {
                var8.printStackTrace();
            }

            result = String.join("&", ss);
        }

        return result;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static TreeMap<String, Object> signature(String appkey, String appsecret, TreeMap<String, Object> map) {
        long expires = System.currentTimeMillis() / 1000L + 60L;
        String nonce = String.valueOf(RandomUtils.nextLong(1L, 999999L));
        map.put("appkey", appkey);
        map.put("expires", expires);
        map.put("nonce", nonce);
        String signature = generateSignature(mapToFormatString(map), appsecret);
        map.put("signature", signature);
        return map;
    }

    public static TreeMap<String, Object> signature(String appkey, String appsecret, TreeMap<String, Object> map, boolean customzied) {
        if (!customzied) {
            return signature(appkey, appsecret, map);
        } else {
            long expires = System.currentTimeMillis() / 1000L + 60L;
            String nonce = String.valueOf(RandomUtils.nextLong(1L, 999999L));
            map.put("appkey", appkey);
            map.put("expires", expires);
            map.put("nonce", nonce);
            String signature = generateSignature(mapToFormatString(map, true), appsecret);
            map.put("signature", signature);
            return map;
        }
    }
}
