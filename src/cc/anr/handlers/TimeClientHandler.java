package cc.anr.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler extends ChannelHandlerAdapter {

	private ByteBuf clientMessage;
	ChannelHandlerContext ctx;
	public TimeClientHandler() {

		byte[] req = "Call-User-Service".getBytes();
		clientMessage = Unpooled.buffer(req.length);
		clientMessage.writeBytes(req);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		this.ctx=ctx;
		System.out.println("====TimeClientHandler===channelActive=======");
		sendClientMsg();
//		new Send().run();

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {

		ByteBuf m = (ByteBuf) msg;
		String content = m.toString(io.netty.util.CharsetUtil.UTF_8);
		System.out.println(content);
		m.release();

	}

	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		sendClientMsg();
		super.channelReadComplete(ctx);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
	
	
	
	public void sendClientMsg() {
		if (ctx == null || !ctx.channel().isActive()) {
			return;
		}
		ctx.writeAndFlush(clientMessage);
	}

	class Send implements Runnable {

		@Override
		public void run() {
			while (true) {
				sendClientMsg();
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
}
