package cc.anr.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Timer;
import java.util.TimerTask;

public class TimeClientHandler extends ChannelHandlerAdapter {

	ChannelHandlerContext ctx;
	public TimeClientHandler() {		
		
		//定时给服务器发消息 10 秒
		Timer timer = new Timer();
		long delay = 0;
		long intevalPeriod = 10 * 1000;
		// schedules the task to be run in an interval
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);
		
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		this.ctx=ctx;

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {

		ByteBuf m = (ByteBuf) msg;
		String message = m.toString(io.netty.util.CharsetUtil.UTF_8);
		System.out.println("Netty-Client:Receive Message:" + message);

		m.release();

	}

	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		super.channelReadComplete(ctx);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
	
	
	public void sendClientMsg(ChannelHandlerContext ctx) {
		if (ctx == null || !ctx.channel().isActive()) {
			return;
		}
		String msg="A voice from afar:"+ctx.channel().id().asShortText();
		byte[] sdf =msg.getBytes();
		ByteBuf contentBuf = ctx.alloc().buffer(sdf.length);
		contentBuf.writeBytes(sdf);
		ctx.writeAndFlush(contentBuf);
	}

	
	  TimerTask task = new TimerTask() {  
          @Override  
          public void run() {  
        		  sendClientMsg(ctx);        	  
          }  
      };
}
