package demo02.client;

import demo02.Time;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther: 0
 * @Date: 2018/12/19 15:39
 * @Description:
 */

public class TimeClientHandlerPOJO extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //直接将信息转换成Time类型输出即可
        Time msg1 = (Time)msg;
        System.out.println(msg1);
        ctx.close();


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }
}
