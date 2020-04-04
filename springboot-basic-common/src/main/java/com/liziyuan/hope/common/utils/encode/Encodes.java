

package com.liziyuan.hope.common.utils.encode;

import com.liziyuan.hope.common.utils.exception.Exceptions;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


/**
 * 加密方法
 *
 * @author zhangqingzhou
 */
public class Encodes {
    private static Log log = LogFactory.getLog(Encodes.class);
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static final String UTF8_ENCODING = "UTF-8";

    private Encodes() {
    }

    public static String encodeHex(byte[] input) {
        return Hex.encodeHexString(input);
    }

    public static byte[] decodeHex(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException var2) {
            log.error("decodeHex(String)", var2);
            throw new IllegalStateException("Hex Decoder exception", var2);
        }
    }

    public static String encodeBase64(byte[] input) {
        return Base64.encodeBase64String(input);
    }

    public static String encodeUrlSafeBase64(byte[] input) {
        return Base64.encodeBase64URLSafeString(input);
    }

    public static byte[] decodeBase64(String input) {
        return Base64.decodeBase64(input);
    }

    public static String encodeBase62(long num) {
        return alphabetEncode(num, 62);
    }

    public static long decodeBase62(String str) {
        return alphabetDecode(str, 62);
    }

    private static String alphabetEncode(long num, int base) {
        num = Math.abs(num);

        StringBuilder sb;
        for (sb = new StringBuilder(); num > 0L; num /= (long) base) {
            sb.append("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".charAt((int) (num % (long) base)));
        }

        return sb.toString();
    }

    private static long alphabetDecode(String str, int base) {
        Validate.notBlank(str);
        long result = 0L;

        for (int i = 0; i < str.length(); ++i) {
            result = (long) ((double) result + (double) "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".indexOf(str.charAt(i)) * Math.pow((double) base, (double) i));
        }

        return result;
    }

    public static String escapeHtml(String html) {
        return StringEscapeUtils.escapeHtml4(html);
    }

    public static String unescapeHtml(String htmlEscaped) {
        return StringEscapeUtils.unescapeHtml4(htmlEscaped);
    }

    public static String escapeXml(String xml) {
        return StringEscapeUtils.escapeXml(xml);
    }

    public static String unescapeXml(String xmlEscaped) {
        return StringEscapeUtils.unescapeXml(xmlEscaped);
    }

    public static String escapeCsv(String csv) {
        return StringEscapeUtils.escapeCsv(csv);
    }

    public static String unescapeCsv(String csvEscaped) {
        return StringEscapeUtils.unescapeCsv(csvEscaped);
    }

    public static String urlEncode(String part) {
        try {
            return URLEncoder.encode(part, "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            log.error("urlEncode(String)", var2);
            throw Exceptions.unchecked(var2);
        }
    }

    public static String urlDecode(String part) {
        try {
            return URLDecoder.decode(part, "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            log.error("urlDecode(String)", var2);
            throw Exceptions.unchecked(var2);
        }
    }
}
