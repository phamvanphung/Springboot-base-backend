package com.fucota.base.utils.utils;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.regex.Pattern;

public class StringUtil {

    private static final SecureRandom secureRandom = new SecureRandom();
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    private StringUtil() {

    }

    /**
     * Convert utf-8 to normal text
     *
     * @param string string (chữ cái)
     * @return string (chu cai)
     */
    public static String normalizeString(String string) {
        String nfdNormalizedString = Normalizer.normalize(string, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString)
            .replaceAll("")
            .replace("đ", "d")
            .replace("Đ", "D");
    }

    /**
     * Convert text to Camel Case
     *
     * @param text (chu cai)
     * @return string (chuCai)
     */
    public static String textToCamelCase(String text) {
        text = normalizeString(text);
        String[] words = text.split("[\\W_]+");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (i == 0) {
                word = word.isEmpty() ? word : word.toLowerCase();
            } else {
                word = word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
            }
            builder.append(word);
        }
        return builder.toString();
    }

    /**
     * Convert text to Snake Case
     *
     * @param text (chu cai)
     * @return string (chu_cai)
     */
    public static String textToSnakeCase(String text) {
        text = textToCamelCase(text);
        StringBuilder snakeCase = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if ('A' <= c && c <= 'Z') {
                c += 32;
                if (i != 0) {
                    snakeCase.append('_');
                }
            }
            snakeCase.append(c);
        }
        return snakeCase.toString();
    }

    /**
     * Generate 6 Digits Code
     *
     * @return string
     */
    public static String generate6DigitsCode() {
        return generateDigitsCode(6);
    }

    /**
     * Generate  Digits Code
     *
     * @param number generate size
     * @return string
     */
    public static String generateDigitsCode(long number) {
        StringBuilder ab = new StringBuilder();
        long value;
        if (number < 18) {
            for (long i = 1; i <= number; i++) {
                ab.append(9L);
            }
            value = Long.parseLong(ab.toString());
        } else {
            value = Long.MAX_VALUE;
        }
        String format = "%0longd".replace("long", String.valueOf(number));
        return String.format(format, secureRandom.nextLong(value));
    }

    /**
     * UpperCase without space
     *
     * @param message (abc xyz)
     * @return string (ABCXYZ)
     */
    public static String normalizeUpperCaseString(String message) {
        return normalizeString(message).toUpperCase().replaceAll("\\s+", "");
    }

    /**
     * UpperCase with space
     *
     * @param message (abc xyz)
     * @return string (ABC XYZ)
     */
    public static String normalizeSpaceUpperCaseString(String message) {
        return normalizeString(message).toUpperCase().replaceAll(" +", " ");
    }

    /**
     * UpperCase snakeCase with utf-8
     *
     * @param message (chữ cái)
     * @return string (CHỮ_CÁI)
     */
    public static String normalizeUpperSnakeCase(String message) {
        return message == null ? null : message.toUpperCase().replaceAll("\\s", "_");
    }



    public static String fromByteArray(byte[] data) {
        char[] hexChars = new char[data.length * 2];
        for (int j = 0; j < data.length; j++) {
            int v = data[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String asciiFromByteArray(byte[] data) {
        return hexToAscii(fromByteArray(data));
    }

    //it's come from http://www.baeldung.com/java-convert-hex-to-ascii
    public static String asciiToHex(String asciiStr) {
        char[] chars = asciiStr.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (char ch : chars) {
            hex.append(Integer.toHexString(ch));
        }

        return hex.toString();
    }

    //it's come from http://www.baeldung.com/java-convert-hex-to-ascii
    public static String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }

    public static byte[] asciiToHex(byte[] data) {

        char[] hexChars = new char[data.length * 2];
        for (int j = 0; j < data.length; j++) {
            int v = data[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }

        byte[] res = new byte[hexChars.length];
        for (int i = 0; i < hexChars.length; i++) {
            res[i] = (byte) hexChars[i];
        }

        Arrays.fill(hexChars, '\u0000');
        hexChars = null;

        return res;
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        boolean padd = false;
        if (len % 2 != 0) {
            s = "0" + s;
            len++;
            padd = true;
        }

        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                + Character.digit(s.charAt(i + 1), 16));
        }

        return data;
    }

    public static byte[] hexStringTrack2(String s) {
        int len = s.length();
        boolean padd = false;
        if (len % 2 != 0) {
            s = s + "F";
            len++;
            padd = true;
        }

        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                + Character.digit(s.charAt(i + 1), 16));
        }

        return data;
    }

    public static String fromByteBuffer(ByteBuffer readBuffer) {

        return fromByteArray(Arrays.copyOfRange(readBuffer.array(), 0, readBuffer.position()));
    }


    public static String intToHexString(int value) {
        String hs = Integer.toHexString(value);
        if (hs.length() % 2 != 0)
            hs = "0" + hs;
        hs = hs.toUpperCase();
        return hs;
    }

    public static byte[] asciiToByteArray(byte[] bytes) {
        return StringUtil.hexStringToByteArray(StringUtil.hexToAscii(StringUtil.fromByteArray(bytes)));
    }

    public static String toHexString(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            sb.append(toHexString(str.charAt(i)));
        }
        return sb.toString();
    }

    /**
     * convert into Hexadecimal notation of Unicode.<br>
     * example)a?\u0061
     *
     * @param ch
     * @return
     */
    public static String toHexString(char ch) {
        String hex = Integer.toHexString(ch);
        while (hex.length() < 4) {
            hex = "0" + hex;
        }
        hex = "\\u" + hex;
        return hex;
    }

    public static String getValueFormat(String value, int number) {
        if (value.length() >= number)
            return value;
        return String.format("%0" + (number - value.length()) + "d%s", 0, value);
    }



}
