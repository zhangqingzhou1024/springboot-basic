package com.liziyuan.hope.util.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zqz
 * @date 2024-02-20 20:27
 */
public class PDFExtracter {

    public static void main(String[] args) {
        String directoryPath = "XXX";
        List<File> pdfFiles = new ArrayList<>();
        findPDFFiles(new File(directoryPath), pdfFiles);
        System.out.println("pdf nums ==> " + pdfFiles.size());
        for (File pdfFile : pdfFiles) {
            System.out.println(pdfFile.getName());
        }

      /*  if(pdfFiles.size() > 10){
            return;
        }*/
        int count = 0;
        ArrayList<FileDetail> fileDetails = new ArrayList<>(pdfFiles.size());
        for (File pdfFile : pdfFiles) {
            count++;
            if (count > 200) {
                return;
            }
            String parent = pdfFile.getParent();
            //System.out.println(parent);
            String textFromPDF = extractTextFromPDF(pdfFile);
            List<String> ydNoteUrls = ContextFilter.getYDNoteUrls(textFromPDF);
            if (!CollectionUtils.isEmpty(ydNoteUrls)) {
                //System.out.println(pdfFile.getName() + " ==> " + ydNoteUrls);
                fileDetails.add(FileDetail.builder().containurls(ydNoteUrls).fileName(pdfFile.getName()).filePath(parent).build());
            }
        }

        for (FileDetail fileDetail : fileDetails) {
            System.out.println(fileDetail);
        }

    }

    public static void findPDFFiles(File directory, List<File> pdfFiles) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    findPDFFiles(file, pdfFiles); // 递归调用以处理子目录
                } else if (file.isFile() && file.getName().toLowerCase().endsWith(".pdf")) {
                    pdfFiles.add(file);
                }
            }
        }
    }

    public static String extractTextFromPDF(File pdfFile) {
        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);

            //System.out.println("Text extracted from " + pdfFile.getName() + ":\n" + text);
            return text;
        } catch (Exception e) {
            System.err.println("An error occurred while extracting text from " + pdfFile.getName() + ": " + e.getMessage());
        }

        return null;
    }
}
