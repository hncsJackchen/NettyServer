package main;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;

/**
 * Created by Administrator on 2016/11/18.
 */
public class MsgHandler extends SimpleChannelHandler {

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelConnected(ctx, e);
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        System.out.println("接收到客户端的消息...");
        ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
        if (buffer != null) {
            byte[] b = new byte[buffer.array().length];
            while (buffer.readable()) {
                buffer.getBytes(0, b);
            }
            System.out.println("接收到的信息为："+new String(b));
        }else {
            System.out.println("接收到的信息为 buffer==null");
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        e.getCause().printStackTrace();
    }
}
