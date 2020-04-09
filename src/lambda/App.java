package lambda;

public class App {
    public static void main(String[] args) {
        /*
        1.默认方法(default)
         */
        UserCredential userCredential = new UserCredentialImpl();
        System.out.println(userCredential.verifyUser("admin"));
        System.out.println(userCredential.getCredential("admin"));

        /*
        2.静态方法(static)
         */
        String msg = "hello world";
        if (MessageFormat.verifyMessage(msg)) {
            MessageFormat messageFormat = new MessageFormatImpl();
            messageFormat.format(msg, "json");
        }

        /*
        匿名内部类，实现接口的抽象方法
         */
        UserCredential userCredential1 = new UserCredential() {
            @Override
            public String verifyUser(String username) {
                return "admin".equals(username) ? "管理员" : "会员";
            }
        };
        System.out.println(userCredential1.verifyUser("manager"));
        System.out.println(userCredential1.verifyUser("admin"));

        /*
        lambda表达式
         */

    }
}
