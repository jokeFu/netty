package demo02.server;

import demo02.Time;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther: 0
 * @Date: 2018/12/19 15:06
 * @Description:
 */

public class TimeServerHandlerPOJO extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //发送当前时间信息
        ChannelFuture f = ctx.writeAndFlush(new Time());
        //发送完毕后关闭channel
        f.addListener(ChannelFutureListener.CLOSE);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //出现错误断开连接
        cause.printStackTrace();
        ctx.close();
    }
}
