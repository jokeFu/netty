package demo02.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Auther: 0
 * @Date: 2018/12/19 15:15
 * @Description:
 */

public class TimeServerPOJO {
    private int port;

    public TimeServerPOJO(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        int port = 8080;

        new TimeServerPOJO(port).run();
    }

    private void run() {
        //用于接收进来的连接
        EventLoopGroup boss = new NioEventLoopGroup();

        //用于处理已经接进来的连接
        EventLoopGroup work = new NioEventLoopGroup();
        System.out.println(work);
        System.out.println("准备运行端口：" + port);

        try {
            ServerBootstrap b = new ServerBootstrap(); //启动NIO服务的辅助启动类
            b.group(boss, work)
                    .channel(NioServerSocketChannel.class) //这里告诉channel如何接收新的连接
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //自定义处理顺序
                            //注意添加顺序
                            socketChannel.pipeline().addLast(new TimeEncoderPOJO(), new TimeServerHandlerPOJO());

                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            //绑定接口，开始接收进来的连接
            ChannelFuture sync = b.bind(port).sync();
            //等待服务器socket关闭
            sync.channel().closeFuture().sync();

        } catch (Exception e) {
            work.shutdownGracefully();
            boss.shutdownGracefully();
        }


    }

}
