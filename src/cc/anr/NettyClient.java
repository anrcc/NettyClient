package cc.anr;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import cc.anr.handlers.TimeClientHandler;

public class NettyClient {

	public static void main(String[] args) throws Exception {
		  String host = "107.191.52.253";
		 // String host = "127.0.0.1";
	      int port = 58888;
	      EventLoopGroup workerGroup = new NioEventLoopGroup();
	        try {
	            Bootstrap b = new Bootstrap(); // (1)
	            b.group(workerGroup); // (2)
	            b.channel(NioSocketChannel.class); // (3)
	            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
	            b.handler(new ChannelInitializer<SocketChannel>() {
	                @Override
	                public void initChannel(SocketChannel ch) throws Exception {
	                    ch.pipeline().addLast(new TimeClientHandler());
	                }
	            });
	            // Start the client.
	            ChannelFuture f = b.connect(host, port).sync(); // (5)
	            // Wait until the connection is closed.
	            
	            boolean connet=true;
	           
	            while (connet) {
					Thread.sleep(10000);
				} 
//	            
	            f.channel().closeFuture().sync();
	        } finally {
	            workerGroup.shutdownGracefully();
	        }
	}

}
