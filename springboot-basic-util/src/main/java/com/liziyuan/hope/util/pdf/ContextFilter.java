package com.liziyuan.hope.util.pdf;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zqz
 * @date 2024-02-20 20:37
 */
public class ContextFilter {
    static final String YD_NOTE_REGEX = "(https?:\\S*)";
    // static final String YD_NOTE_REGEX = "(https?://note\\.youdao\\.com\\S*)";
    static final Pattern ID_NOTE_pattern = Pattern.compile(YD_NOTE_REGEX);

    public static List<String> getYDNoteUrls(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        // content  = content.replaceAll("http://note.youdao.com/noteshare\\?\\n","http://note.youdao.com/noteshare?");
        content = content.replaceAll("\\n+", "").replaceAll("\\r", "");
        List<String> list = new ArrayList<>(4);
        Matcher matcher = ID_NOTE_pattern.matcher(content);
        while (matcher.find()) {
            String group = matcher.group();
            String extractUrls = extractUrls(group);
            /*int firstChineseIndex = findFirstChineseIndex(group);
            String extractUrls = group;
            if (firstChineseIndex != -1) {
                //extractUrls = group.substring(0, firstChineseIndex).trim();
                extractUrls = group.substring(0, firstChineseIndex).replaceAll("\\s+$", "");
            }*/
            if (null == extractUrls) {
                continue;
            }
            // System.out.println("Found URL: " + extractUrls);
            list.add(extractUrls);
        }
        return list;
    }

    private static int findFirstChineseIndex(String text) {
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.start();
        } else {
            return -1; // If no Chinese character is found
        }
    }

    public static String extractUrls(String text) {
        List<String> containedUrls = new ArrayList<String>();
        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        while (urlMatcher.find()) {
            // containedUrls.add(text.substring(urlMatcher.start(0), urlMatcher.end(0)));
            return text.substring(urlMatcher.start(0), urlMatcher.end(0));
        }

        return null;
    }

    /*private static String extractUrls(String text) {
        List<String> containedUrls = new ArrayList<String>();//[\\u4e00-\\u9fa5]+
        // [^\\u4e00-\\u9fa5]+
        String urlRegex = "^[\\u4e00-\\u9fa5]+";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);
        //
        while (urlMatcher.find()) {
            return urlMatcher.group();
            //containedUrls.add(urlMatcher.group());
        }

        return null;
    }*/

}
