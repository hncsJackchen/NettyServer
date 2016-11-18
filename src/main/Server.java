package main;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/11/18.
 */
public class Server {

    private int port;

    /**
     * 构造函数
     * @param port 需要绑定到的端口号
     */
    public Server(int port) {
        this.port = port;
    }

    /**
     * 开启服务
     * @param callbackHandler 回调Handler，处理消息
     */
    public void startServer(SimpleChannelHandler callbackHandler) {
        ChannelFactory factory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
        ServerBootstrap bootstrap = new ServerBootstrap(factory);
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(callbackHandler);
            }
        });
        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);
        bootstrap.bind(new InetSocketAddress(port));
        System.out.println("已经绑定到端口：" + port);
    }
}
