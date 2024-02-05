package com.fucota.base.utils.utils;

import java.util.Arrays;

public class VNCharacterUtils {
    private static final char[] SOURCE_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É',
        'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â',
        'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý',
        'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ',
        'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ',
        'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ',
        'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ',
        'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
        'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ',
        'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ',
        'ữ', 'Ự', 'ự',};

    private static final char[] DESTINATION_CHARACTERS = {'A', 'A', 'A', 'A', 'E',
        'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a',
        'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u',
        'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u',
        'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
        'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e',
        'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
        'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
        'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
        'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
        'U', 'u', 'U', 'u',};

    public static char removeAccent(char ch) {
        int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
        if (index >= 0) {
            ch = DESTINATION_CHARACTERS[index];
        }
        return ch;
    }

    public static String removeAccent(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }

    public static String compound2Unicode(String str) {
        str = str.replace("\u0065\u0309", "\u1EBB"); //ẻ
        str = str.replace("\u0065\u0301", "\u00E9"); //é
        str = str.replace("\u0065\u0300", "\u00E8"); //è
        str = str.replace("\u0065\u0323", "\u1EB9"); //ẹ
        str = str.replace("\u0065\u0303", "\u1EBD"); //ẽ
        str = str.replace("\u00EA\u0309", "\u1EC3"); //ể
        str = str.replace("\u00EA\u0301", "\u1EBF"); //ế
        str = str.replace("\u00EA\u0300", "\u1EC1"); //ề
        str = str.replace("\u00EA\u0323", "\u1EC7"); //ệ
        str = str.replace("\u00EA\u0303", "\u1EC5"); //ễ
        str = str.replace("\u0079\u0309", "\u1EF7"); //ỷ
        str = str.replace("\u0079\u0301", "\u00FD"); //ý
        str = str.replace("\u0079\u0300", "\u1EF3"); //ỳ
        str = str.replace("\u0079\u0323", "\u1EF5"); //ỵ
        str = str.replace("\u0079\u0303", "\u1EF9"); //ỹ
        str = str.replace("\u0075\u0309", "\u1EE7"); //ủ
        str = str.replace("\u0075\u0301", "\u00FA"); //ú
        str = str.replace("\u0075\u0300", "\u00F9"); //ù
        str = str.replace("\u0075\u0323", "\u1EE5"); //ụ
        str = str.replace("\u0075\u0303", "\u0169"); //ũ
        str = str.replace("\u01B0\u0309", "\u1EED"); //ử
        str = str.replace("\u01B0\u0301", "\u1EE9"); //ứ
        str = str.replace("\u01B0\u0300", "\u1EEB"); //ừ
        str = str.replace("\u01B0\u0323", "\u1EF1"); //ự
        str = str.replace("\u01B0\u0303", "\u1EEF"); //ữ
        str = str.replace("\u0069\u0309", "\u1EC9"); //ỉ
        str = str.replace("\u0069\u0301", "\u00ED"); //í
        str = str.replace("\u0069\u0300", "\u00EC"); //ì
        str = str.replace("\u0069\u0323", "\u1ECB"); //ị
        str = str.replace("\u0069\u0303", "\u0129"); //ĩ
        str = str.replace("\u006F\u0309", "\u1ECF"); //ỏ
        str = str.replace("\u006F\u0301", "\u00F3"); //ó
        str = str.replace("\u006F\u0300", "\u00F2"); //ò
        str = str.replace("\u006F\u0323", "\u1ECD"); //ọ
        str = str.replace("\u006F\u0303", "\u00F5"); //õ
        str = str.replace("\u01A1\u0309", "\u1EDF"); //ở
        str = str.replace("\u01A1\u0301", "\u1EDB"); //ớ
        str = str.replace("\u01A1\u0300", "\u1EDD"); //ờ
        str = str.replace("\u01A1\u0323", "\u1EE3"); //ợ
        str = str.replace("\u01A1\u0303", "\u1EE1"); //ỡ
        str = str.replace("\u00F4\u0309", "\u1ED5"); //ổ
        str = str.replace("\u00F4\u0301", "\u1ED1"); //ố
        str = str.replace("\u00F4\u0300", "\u1ED3"); //ồ
        str = str.replace("\u00F4\u0323", "\u1ED9"); //ộ
        str = str.replace("\u00F4\u0303", "\u1ED7"); //ỗ
        str = str.replace("\u0061\u0309", "\u1EA3"); //ả
        str = str.replace("\u0061\u0301", "\u00E1"); //á
        str = str.replace("\u0061\u0300", "\u00E0"); //à
        str = str.replace("\u0061\u0323", "\u1EA1"); //ạ
        str = str.replace("\u0061\u0303", "\u00E3"); //ã
        str = str.replace("\u0103\u0309", "\u1EB3"); //ẳ
        str = str.replace("\u0103\u0301", "\u1EAF"); //ắ
        str = str.replace("\u0103\u0300", "\u1EB1"); //ằ
        str = str.replace("\u0103\u0323", "\u1EB7"); //ặ
        str = str.replace("\u0103\u0303", "\u1EB5"); //ẵ
        str = str.replace("\u00E2\u0309", "\u1EA9"); //ẩ
        str = str.replace("\u00E2\u0301", "\u1EA5"); //ấ
        str = str.replace("\u00E2\u0300", "\u1EA7"); //ầ
        str = str.replace("\u00E2\u0323", "\u1EAD"); //ậ
        str = str.replace("\u00E2\u0303", "\u1EAB"); //ẫ
        str = str.replace("\u0045\u0309", "\u1EBA"); //Ẻ
        str = str.replace("\u0045\u0301", "\u00C9"); //É
        str = str.replace("\u0045\u0300", "\u00C8"); //È
        str = str.replace("\u0045\u0323", "\u1EB8"); //Ẹ
        str = str.replace("\u0045\u0303", "\u1EBC"); //Ẽ
        str = str.replace("\u00CA\u0309", "\u1EC2"); //Ể
        str = str.replace("\u00CA\u0301", "\u1EBE"); //Ế
        str = str.replace("\u00CA\u0300", "\u1EC0"); //Ề
        str = str.replace("\u00CA\u0323", "\u1EC6"); //Ệ
        str = str.replace("\u00CA\u0303", "\u1EC4"); //Ễ
        str = str.replace("\u0059\u0309", "\u1EF6"); //Ỷ
        str = str.replace("\u0059\u0301", "\u00DD"); //Ý
        str = str.replace("\u0059\u0300", "\u1EF2"); //Ỳ
        str = str.replace("\u0059\u0323", "\u1EF4"); //Ỵ
        str = str.replace("\u0059\u0303", "\u1EF8"); //Ỹ
        str = str.replace("\u0055\u0309", "\u1EE6"); //Ủ
        str = str.replace("\u0055\u0301", "\u00DA"); //Ú
        str = str.replace("\u0055\u0300", "\u00D9"); //Ù
        str = str.replace("\u0055\u0323", "\u1EE4"); //Ụ
        str = str.replace("\u0055\u0303", "\u0168"); //Ũ
        str = str.replace("\u01AF\u0309", "\u1EEC"); //Ử
        str = str.replace("\u01AF\u0301", "\u1EE8"); //Ứ
        str = str.replace("\u01AF\u0300", "\u1EEA"); //Ừ
        str = str.replace("\u01AF\u0323", "\u1EF0"); //Ự
        str = str.replace("\u01AF\u0303", "\u1EEE"); //Ữ
        str = str.replace("\u0049\u0309", "\u1EC8"); //Ỉ
        str = str.replace("\u0049\u0301", "\u00CD"); //Í
        str = str.replace("\u0049\u0300", "\u00CC"); //Ì
        str = str.replace("\u0049\u0323", "\u1ECA"); //Ị
        str = str.replace("\u0049\u0303", "\u0128"); //Ĩ
        str = str.replace("\u004F\u0309", "\u1ECE"); //Ỏ
        str = str.replace("\u004F\u0301", "\u00D3"); //Ó
        str = str.replace("\u004F\u0300", "\u00D2"); //Ò
        str = str.replace("\u004F\u0323", "\u1ECC"); //Ọ
        str = str.replace("\u004F\u0303", "\u00D5"); //Õ
        str = str.replace("\u01A0\u0309", "\u1EDE"); //Ở
        str = str.replace("\u01A0\u0301", "\u1EDA"); //Ớ
        str = str.replace("\u01A0\u0300", "\u1EDC"); //Ờ
        str = str.replace("\u01A0\u0323", "\u1EE2"); //Ợ
        str = str.replace("\u01A0\u0303", "\u1EE0"); //Ỡ
        str = str.replace("\u00D4\u0309", "\u1ED4"); //Ổ
        str = str.replace("\u00D4\u0301", "\u1ED0"); //Ố
        str = str.replace("\u00D4\u0300", "\u1ED2"); //Ồ
        str = str.replace("\u00D4\u0323", "\u1ED8"); //Ộ
        str = str.replace("\u00D4\u0303", "\u1ED6"); //Ỗ
        str = str.replace("\u0041\u0309", "\u1EA2"); //Ả
        str = str.replace("\u0041\u0301", "\u00C1"); //Á
        str = str.replace("\u0041\u0300", "\u00C0"); //À
        str = str.replace("\u0041\u0323", "\u1EA0"); //Ạ
        str = str.replace("\u0041\u0303", "\u00C3"); //Ã
        str = str.replace("\u0102\u0309", "\u1EB2"); //Ẳ
        str = str.replace("\u0102\u0301", "\u1EAE"); //Ắ
        str = str.replace("\u0102\u0300", "\u1EB0"); //Ằ
        str = str.replace("\u0102\u0323", "\u1EB6"); //Ặ
        str = str.replace("\u0102\u0303", "\u1EB4"); //Ẵ
        str = str.replace("\u00C2\u0309", "\u1EA8"); //Ẩ
        str = str.replace("\u00C2\u0301", "\u1EA4"); //Ấ
        str = str.replace("\u00C2\u0300", "\u1EA6"); //Ầ
        str = str.replace("\u00C2\u0323", "\u1EAC"); //Ậ
        str = str.replace("\u00C2\u0303", "\u1EAA"); //Ẫ
        return str;
    }
}
