import clientmode.Client;
import servermode.Server;
import toolmode.Tool;

public class App {
    public static void main(String[] args) {
        try {
            Tool.log(String.format("%s\n%s", "作为服务器:1", "作为客户端:2"));
            int select = Tool.scanner.nextInt();
            if (select == 1) {
                Tool.log("你选择的是服务器");
                new Server();
            } else {
                Tool.log("你选择的是客户端");
                new Client();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Tool.loge("出错了");
        }
    }
}