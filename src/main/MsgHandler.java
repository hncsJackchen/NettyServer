package main;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;

/**
 * Created by Administrator on 2016/11/18.
 * 服务器消息处理类
 */
public class MsgHandler extends SimpleChannelHandler {

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelConnected(ctx, e);
        System.out.println(Thread.currentThread().getName()+"--channelConnected...");
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelDisconnected(ctx, e);
        System.out.println(Thread.currentThread().getName()+"--channelDisconnected...");
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        System.out.println(Thread.currentThread().getName()+"--接收到客户端的消息...");

        //解析消息
        ChannelBuffer buf = ChannelBuffers.dynamicBuffer();
        ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
        byte[] b = new byte[0];
        if(buffer!=null){
            buf.writeBytes(buffer);
            b = new byte[buf.readableBytes()];
            buf.readBytes(b);
            System.out.println("接收到的信息为："+new String(b));
        }else {
            System.out.println("接收到的信息为 buffer==null");
        }

        //发送回执
        System.out.println("回复客户端...begin");
        ChannelBuffer channelBuffer = ChannelBuffers.dynamicBuffer();
        String reply = ("回复："+new String(b));
        channelBuffer.writeBytes(reply.getBytes());
        e.getChannel().write(channelBuffer);
        System.out.println("回复客户端...end:"+reply);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        e.getCause().printStackTrace();
        System.out.println(Thread.currentThread().getName()+"--exceptionCaught...");
    }
}
