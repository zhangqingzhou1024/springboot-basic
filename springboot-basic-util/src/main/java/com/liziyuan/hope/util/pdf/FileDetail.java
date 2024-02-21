package com.liziyuan.hope.util.pdf;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author zqz
 * @date 2024-02-20 21:37
 */
@Data
@Builder
public class FileDetail {
    String filePath;
    String fileName;
    List<String> containurls;


    @Override
    public String toString() {
     /*   return "FileDetail{" +
              //  "filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", containurls=" + containurls +
                '}';*/
        StringBuffer stringBuffer = new StringBuffer();
        if (containurls != null) {
            for (String containurl : containurls) {
                stringBuffer.append("\n").append(fileName + " ==> " + containurl);
            }
        }
        // return fileName + " ==> " + containurls.get(0);
        return stringBuffer.toString().replaceFirst("\n", "");
    }

    public static void main(String[] args) {
        String a = "aaa\nbbb\nccc";
        System.out.println(a);
        System.out.println(a.replaceFirst("\n",""));
    }
}
