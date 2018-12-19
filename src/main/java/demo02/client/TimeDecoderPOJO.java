package demo02.client;

import demo02.Time;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Auther: 0
 * @Date: 2018/12/19 15:31
 * @Description:
 */
        //自定义客户端数据解码类
public class TimeDecoderPOJO extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 4) {
            System.out.println(byteBuf);
            return;
        }

        //list添加对象这表示解码成功
        list.add(new Time(byteBuf.readUnsignedInt()));
    }

    //有新数据的时调用
    //为了防止分包现象，现将数据放到缓存中，到达满意条件之后再进行解码


}
