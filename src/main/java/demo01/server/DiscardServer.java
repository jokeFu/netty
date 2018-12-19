package demo01.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Auther: 0
 * @Date: 2018/12/19 09:02
 * @Description:
 */

public class DiscardServer {
    private int port;

    public DiscardServer(int port) {
        this.port = port;

    }

    public static void main(String[] args) {
       int  port = 8080;

        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        new DiscardServer(port).run();

    }

    private void run() {
        EventLoopGroup bossGrop = new NioEventLoopGroup();
        EventLoopGroup workerGrop = new NioEventLoopGroup();

        System.out.println("准备运行端口：" + port);
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGrop, workerGrop)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DiscardServerHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture sync = b.bind(port).sync();
            sync.channel().closeFuture().sync();
        } catch (Exception e) {
            workerGrop.shutdownGracefully();
            bossGrop.shutdownGracefully();
            e.printStackTrace();
        }


    }

}
