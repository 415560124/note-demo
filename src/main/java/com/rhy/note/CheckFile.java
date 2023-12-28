package com.rhy.note;

import java.io.File;

public class CheckFile {
    public static void main(String[] args) {
        String directoryPath = "D:\\迅雷下载";
        findFoldersWithMP4Files(new File(directoryPath));
    }

    public static void findFoldersWithMP4Files(File directory) {
        if (!directory.isDirectory()) {
            return;
        }

        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                findFoldersWithMP4Files(file); // Recursive call for subdirectories
            } else if (file.isFile() && file.getName().toLowerCase().endsWith(".mp4")) {
                System.out.println("MP4 file found in: " + directory.getAbsolutePath());
                break; // No need to continue searching in this folder if an .mp4 file is found
            }
        }
    }
}
