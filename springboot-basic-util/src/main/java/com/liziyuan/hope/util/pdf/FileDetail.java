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
        return filePath + "/" + fileName + " ==> " + containurls.get(0);
    }
}
