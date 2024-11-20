package config;

public enum ConfigConstants {
    REMOTE_URL("http://0.0.0.0:4723"),
    APPIUM_LOCAL_URL("http://127.0.0.1:4723"),
    WINDOWS_KEY("Windows"),
    ANDROID_KEY("Android");

    private final String mText;

    ConfigConstants(final String text) {
        mText = text;
    }

    @Override
    public String toString() {
        return mText;
    }
}
