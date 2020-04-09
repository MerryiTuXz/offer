package lambda;

public class MessageFormatImpl implements MessageFormat {
    @Override
    public String format(String message, String format) {
        System.out.println("消息转换...");
        return message;
    }
}
