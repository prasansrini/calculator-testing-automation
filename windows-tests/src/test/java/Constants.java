public enum Constants {
    REMOTE_URL("http://0.0.0.0:4723"), APPIUM_LOCAL_URL("http://127.0.0.1:4723");

    private final String mText;

    Constants(final String text) {
        mText = text;
    }

    @Override
    public String toString() {
        return mText;
    }
}
