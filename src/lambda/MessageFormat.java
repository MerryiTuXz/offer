package lambda;

@FunctionalInterface
public interface MessageFormat {
    /**
     * 消息转换方法
     *
     * @param message 要转换的消息
     * @param format  转换的格式[xml/json...]
     * @return 返回转换后的数据
     */
    String format(String message, String format);

    String toString();

    static boolean verifyMessage(String msg) {
        if (msg != null) {
            return true;
        }
        return false;
    }
}
