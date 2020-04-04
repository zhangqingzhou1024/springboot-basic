package com.liziyuan.hope.common.utils.file;


import com.liziyuan.hope.common.utils.encode.Encodes;

import java.io.IOException;
import java.net.URLEncoder;

public class FileUtils {
    public FileUtils() {
    }

    public static String encodeFilename(String filename, String agent) throws IOException {
        if (agent.contains("Firefox")) {
            filename = "=?UTF-8?B?" + Encodes.encodeBase64(filename.getBytes("utf-8")) + "?=";
            filename = filename.replaceAll("\r\n", "");
        } else {
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        }

        return filename;
    }
}
