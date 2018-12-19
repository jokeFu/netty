package demo02.client;

import demo02.server.TimeEncoderPOJO;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * @Auther: 0
 * @Date: 2018/12/19 15:41
 * @Description:
 */

public class TimeClientPOJO {

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 8080;

        EventLoopGroup workerGrop = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap(); //与serverbootstrap 继承同一个接口
            b.group(workerGrop);           //客户端不需要boss

            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new TimeDecoderPOJO(), new TimeClientHandlerPOJO());
                }
            });
            //启动客户端，，客户端用Connect连接
            ChannelFuture sync = b.connect(host, port).sync();
            //等待连接关闭
            sync.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            workerGrop.shutdownGracefully();
        }
    }
}
