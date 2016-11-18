package main;

/**
 * Created by Administrator on 2016/11/18.
 * 程序入口
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("进入main...");
        Server server = new Server(ServerConfig.port);
        server.startServer(new MsgHandler());
    }
}
