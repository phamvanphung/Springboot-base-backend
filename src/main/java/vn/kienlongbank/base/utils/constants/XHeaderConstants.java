package vn.kienlongbank.base.utils.constants;

public final class XHeaderConstants {
    /**
     * Example: Android | IOS, Web => Chrome | Safari
     */
    public static final String X_DEVICE = "X-Device";

    /**
     * Example: MyPhone
     */
    public static final String X_DEVICE_NAME = "X-Device-Name";

    /**
     * Example: 16.1
     */
    public static final String X_DEVICE_VERSION = "X-Device-Version";

    /**
     * Example: Samsung Note 11
     */
    public static final String X_DEVICE_BRAND = "X-Device-Brand";

    /**
     * Example: Wifi | 3G | 4G
     */
    public static final String X_DEVICE_NETWORK = "X-Device-Network";

    /**
     * Example: 1.0.0 (1020)
     */
    public static final String X_DEVICE_APP_VERSION = "X-AppVersion";

    /**
     * Example: 0905102031
     */
    public static final String X_USERNAME = "X-Username";

    /**
     * Example: 1498918293
     */
    public static final String X_TIMESTAMP = "X-Timestamp";

    /**
     * Ex: md5(username + new Guid())
     */
    public static final String X_REQUEST_ID = "X-RequestId";

    private XHeaderConstants() {
    }
}
