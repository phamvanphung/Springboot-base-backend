package com.fucota.base.utils.constants;

import java.time.LocalTime;

public class RegexUtils {
    /**
     * Email regex
     */
    public static final String EMAIL_REGEX = "(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))";
    /**
     * Password regex
     */
    public static final String PASSWORD_REGEX = "^(?!.* )(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    /**
     * Vietnamese regex
     */
    public static final String VIETNAMESE_REGEX = "^([^ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ])+$";
    /**
     * Id card regex
     */
    public static final String ID_CARD_REGEX = "^[a-zA-Z0-9]{6,15}$";
    /**
     * Otp regex
     */
    public static final String OTP_REGEX = "^[0-9]{6}$";
    /**
     * UUID regex
     */
    public static final String UUID_REGEX = "^[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}$";
    /**
     * Code name regex
     */
    public static final String CODE_NAME_REGEX = "^[a-zA-Z0-9_-]*$";

    public RegexUtils() {

    }
}
