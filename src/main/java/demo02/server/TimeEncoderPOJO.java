package demo02.server;

import demo02.Time;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Auther: 0
 * @Date: 2018/12/19 15:02
 * @Description:
 */

public class TimeEncoderPOJO extends MessageToByteEncoder<Time> {

    //发送数据时调用
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Time time, ByteBuf byteBuf) throws Exception {
        //只传输当前时间，精确到秒
        byteBuf.writeInt((int)time.value());
    }
}
