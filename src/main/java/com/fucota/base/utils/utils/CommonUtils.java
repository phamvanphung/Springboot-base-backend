package com.fucota.base.utils.utils;

import com.emv.qrcode.model.mpm.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class CommonUtils {

    private final static Random rand = new Random();

    /**
     * Returns all duplicates that are in the list as a new {@link Set} thread-safe.
     * <p>
     * Usually the Set will contain only the last duplicate, however the decision
     * what elements are equal depends on the implementation of the {@link List}. An
     * exotic implementation of {@link List} might decide two elements are "equal",
     * in this case multiple duplicates might be returned.
     *
     * @param <X>  The type of element to compare.
     * @param list The list that contains the elements, never <code>null</code>.
     * @return A set of all duplicates in the list. Returns only the last duplicate.
     */
    public static <X extends Object> Set<X> findDuplicates(List<X> list) {
        Set<X> dups = new LinkedHashSet<>(list.size());
        synchronized (list) {
            for (X x : list) {
                if (list.indexOf(x) != list.lastIndexOf(x)) {
                    dups.add(x);
                }
            }
        }
        return dups;
    }

    public static String generateFileName() {
        return RandomStringUtils.randomAlphanumeric(20) + "_" + System.currentTimeMillis();
    }


    public static String markAndFormat(String cardNumber) {
        int len = cardNumber.length();
        int markLen = len - 8;
        if (markLen < 0) {
            return cardNumber;
        }
        StringBuilder mark = new StringBuilder(StringUtils.repeat('*', markLen));

        int spaceCount = 0;
        for (int i = 0; i < markLen; i += 4) {
            mark.insert(i + spaceCount, ' ');
            spaceCount++;
        }

        return cardNumber.substring(0, 4) + mark + " " + cardNumber.substring(len - 4, len);
    }


    public static int getCheckDigit(String number) {
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {

            // Get the digit at the current position.
            int digit = Integer.parseInt(number.substring(i, (i + 1)));

            if ((i % 2) == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }

        // The check digit is the number required to make the sum a multiple of
        // 10.
        int mod = sum % 10;
        return ((mod == 0) ? 0 : 10 - mod);
    }

    public static String generateVirtualAccount(String prefix) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyMMdd");
        long number = ThreadLocalRandom.current().nextInt(999999);
        String numberFormat = String.format("%06d", number);
        StringBuilder res = new StringBuilder();
        res.append(prefix);
        res.append(LocalDate.now().format(dateTimeFormatter));
        res.append(numberFormat);
        res.append(CommonUtils.getCheckDigit(res.toString()));
        return res.toString();
    }

    private static MerchantAccountInformationTemplate getMerchanAccountInformationReservedAdditional(String bin, String accountNo) {
        final MerchantAccountInformationReservedAdditional merchantAccountInformationValue = new MerchantAccountInformationReservedAdditional();
        MerchantAccountInformationReservedAdditional additional = new MerchantAccountInformationReservedAdditional();
        additional.setGloballyUniqueIdentifier(bin);
        additional.addPaymentNetworkSpecific("01", accountNo);
        merchantAccountInformationValue.setGloballyUniqueIdentifier("A000000727");
        merchantAccountInformationValue.addPaymentNetworkSpecific("01", additional.toString());
        merchantAccountInformationValue.addPaymentNetworkSpecific("02", "QRIBFTTA");
        return new MerchantAccountInformationTemplate("38", merchantAccountInformationValue);
    }

    public static String normalizeTerminalLocation(String input) {
        if (input == null) {
            return "";
        }
        return input.substring(0, Math.min(input.length(), 70));
    }

    public static String generateQRCode(String bin, String targetAccount, Long amount, String content) {
        MerchantPresentedMode code = new MerchantPresentedMode();
        code.setCountryCode("VN");
        code.setTransactionCurrency("704");
        code.setPayloadFormatIndicator("01");
        if (amount != null) {
            code.setTransactionAmount(String.valueOf(amount));
        }
        code.setPointOfInitiationMethod("12");
        if (content != null) {
            AdditionalDataField additionalDataField = new AdditionalDataField();
            additionalDataField.setPurposeTransaction(content);
            AdditionalDataFieldTemplate additionalDataFieldTemplate = new AdditionalDataFieldTemplate();
            additionalDataFieldTemplate.setValue(additionalDataField);
            code.setAdditionalDataField(additionalDataFieldTemplate);
        }
        code.addMerchantAccountInformation(getMerchanAccountInformationReservedAdditional(bin, targetAccount));
        return code.toString();
    }

    public static String generateQRCode(String bin, String targetAccount) {
        return generateQRCode(bin, targetAccount, null);
    }

    public static String generateQRCode(String bin, String targetAccount, String content) {
        return generateQRCode(bin, targetAccount, null, content);
    }

    public static String getSequenceTransaction(long seq) {
        return "P" + String.format("%011d", seq);
    }


    public static String concatWithFixLength(String input) {
        return input == null ? "" : input.substring(0, Math.min(100, input.length()));
    }

    public static String concatWithFixLength(String input, int length) {
        return input == null ? "" : input.substring(0, Math.min(length, input.length()));
    }


    public static String getPrefixByAccount(String account) {
        if (account == null || account.length() < 4) {
            return null;
        }
        return account.substring(0, 4);
    }

    public static boolean isExpired(Long since) {
        Long iNow = Instant.now().getEpochSecond() * 1000;
        return iNow - since > 0;
    }

    public static long convertToMilli(ZonedDateTime time) {
        if (time == null) {
            return 0;
        }
//        ZonedDateTime zdt = ZonedDateTime.of(time, ZoneId.systemDefault());
        return time.toInstant().toEpochMilli();
    }

    public static String getMerchantCode() {
        int upperbound = 999999;
        int int_random = rand.nextInt(upperbound);
        return "TT" + String.format("%06d", int_random);
    }


//    public static long getInvoiceCode(Merchant merchant) {
//        long invoiceCode = merchant.getInvoiceCode() == null ? 0L : merchant.getInvoiceCode();
//        invoiceCode = invoiceCode + 1;
//        if (invoiceCode > 999999) {
//            invoiceCode = 1;
//        }
//        return invoiceCode;
//    }

//    public static String getHeaderExcel(LanguageCode code, TranslateExcel translateExcel) {
//        switch (code) {
//            case vi:
//                return translateExcel.getContentVi();
//            case en:
//                return translateExcel.getContentEn();
//            default:
//                return translateExcel.getContentVi();
//        }
//    }


    public static String markAccountNumber(String valueString) {
        if (StringUtils.isBlank(valueString)) {
            return null;
        }
        if (valueString.length() <= 4) {
            return valueString;
        }
        return "******" + valueString.substring((valueString.length() - 4));
    }
}
