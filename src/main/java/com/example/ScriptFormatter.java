package com.example;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ScriptFormatter {

    public static void main(String[] args) {
        int start = 16; // 시작 번호
        int end = 16;   // 끝 번호
        String filePrefix = "section_"; // 파일명 접두사
        String inputDir = "./origin/"; // 원본 파일이 위치한 디렉터리
        String outputDir = "./result/"; // 결과 파일을 저장할 디렉터리

        for (int i = start; i <= end; i++) {
            String filename = filePrefix + i + ".txt";
            Path inputPath = Paths.get(inputDir + filename);
            Path outputPath = Paths.get(outputDir + filename);

            try {
                List<String> lines = Files.readAllLines(inputPath);
                List<String> words = new ArrayList<>();

                // 파일 내용을 읽고 단어 단위로 분리
                for (String line : lines) {
                    words.addAll(Arrays.asList(line.split("\\s+")));
                }

                StringBuilder result = new StringBuilder();

                // 단어 끝이 '.'이면 줄바꿈 추가, 아니면 공백 포함하여 연결
                for (String word : words) {
                    if (word.endsWith(".")) {
                        result.append(word).append("\n\n");
                    } else {
                        result.append(word).append(" ");
                    }
                }

                // 결과 파일 저장
                Files.write(outputPath, result.toString().getBytes());
                System.out.println("Processed: " + filename);
            } catch (IOException e) {
                System.err.println("Error processing file: " + filename);
                e.printStackTrace();
            }
        }
    }
}