package com.fucota.base.utils.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class FileUtil {
    public static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex != -1 && lastDotIndex < fileName.length() - 1) {
            return fileName.substring(lastDotIndex + 1);
        }
        return "";
    }

    public static String calculateMD5(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             DigestInputStream dis = new DigestInputStream(fis, MessageDigest.getInstance("MD5"))) {

            byte[] buffer = new byte[8192];
            while (dis.read(buffer) != -1) {
                // Reading the entire file to update the digest
            }

            byte[] digest = dis.getMessageDigest().digest();
            return bytesToHex(digest);
        } catch (Exception e) {
//            throw new RuntimeException("Error calculating MD5 hash", e);
            return null;
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
